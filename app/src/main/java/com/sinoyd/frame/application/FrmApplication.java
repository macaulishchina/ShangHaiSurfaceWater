package com.sinoyd.frame.application;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.sinoyd.frame.action.FrmExceptionAction;
import com.sinoyd.frame.config.FileConfig;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * 作者： 王一凡
 * 创建时间： 2017/9/5
 * 版权： 江苏远大信息股份有限公司
 * 描述：Application 框架主入口、注册全局事件
 */
public class FrmApplication extends BaseApplication {
    private static Context context;
    private DbManager.DaoConfig daoConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取Context
        context = getApplicationContext();
        x.Ext.init(this);
        x.Ext.setDebug(false);// 是否输出debug日志，开启debug会影响性能
        //初始化Sqlite
        ininsqlit();
        //初始化文件夹创建
        FileConfig.initFolders();
        //初始化百度地图
        SDKInitializer.initialize(this);

        //初始化ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(3) // 线程池的大小 默认3
                .threadPriority(Thread.NORM_PRIORITY - 2)//设置线程优先级
                .denyCacheImageMultipleSizesInMemory() // 当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认会缓存多个不同的大小的相同图片
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)// 设置缓存的最大字节
                .tasksProcessingOrder(QueueProcessingType.LIFO)//设置图片下载和显示的工作队列排序
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000))// connectTimeout 超时时间
                .build();// 开始构建
        ImageLoader.getInstance().init(config);// 全局初始化此配置

        //系统异常捕获
        Thread.setDefaultUncaughtExceptionHandler(handler);

        //百度地图初始化
        SDKInitializer.initialize(getApplicationContext());
    }


    private void ininsqlit() {
        daoConfig = new DbManager.DaoConfig()
                .setDbName("water.db")//设置数据库的名称，默认是xutils.db
                .setAllowTransaction(true)//设置是否允许事务，默认true
                .setDbDir(new File("/sdcard")) // 设置数据库的存放路径, 默认存储在app的私有目录(数据库默认存储在/data/data/你的应用程序/database/xxx.db)
                .setDbVersion(1)//设置数据库的版本号
                //设置数据库打开的监听
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                //设置数据库更新的监听
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        // or
                        // db.dropDb();
                    }
                });


    }


    //返回
    public static Context getContext() {
        return context;
    }

    Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread thread, final Throwable ex) {
            ex.printStackTrace();
            FrmExceptionAction.saveCrashLog(ex);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    };

}

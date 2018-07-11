package com.sinoyd.Code.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.sinoyd.R;
import com.sinoyd.frame.actys.SinoBaseActivity;
import com.sinoyd.frame.util.ToastUtil;

/**
 * 作者： 王一凡
 * 创建时间： 2017/9/6
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.yunwei.actys 运维管理主页面
 */
public class DBS_MainActivity extends SinoBaseActivity {
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.dbs_main_activity);
        // 关闭当前界面的右滑关闭功能
        openSlideFinish(false);
        getNbBar().hide();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ExitApp();
        }
        return false;
    }

    public void ExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            ToastUtil.showShort(this, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            // finish();
            moveTaskToBack(false);
        }

    }


}

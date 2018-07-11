package com.sinoyd.frame.actys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sinoyd.Code.Activity.DBS_AboutActivity;
import com.sinoyd.Code.Until.HttpListener;
import com.sinoyd.R;
import com.sinoyd.frame.config.ResConfig;
import com.sinoyd.frame.config.ThemeConfig;
import com.sinoyd.frame.util.LogUtil;
import com.sinoyd.frame.util.ToastUtil;
import com.sinoyd.frame.views.LoadingDialog;
import com.sinoyd.showcase.actys.ShowCase_InitActivity;

import org.jetbrains.annotations.NotNull;
import org.xutils.http.annotation.HttpResponse;

/**
 * 作者： 王一凡
 * 创建时间： 2017/9/5
 * 版权： 江苏远大信息股份有限公司
 * 描述：业务基类 二次封装逻辑基类方法
 */
public class SinoBaseActivity extends BaseActivity implements HttpListener {
    FrameLayout baseContent;

    LoadingDialog loadingDialog;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //禁止设备横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setDefaultTitle();
        baseContent = (FrameLayout) findViewById(ResConfig.getBaseContentId());

        LogUtil.writeLogThread("进入模块Activity－>", getLocalClassName());


    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.writeLogThread("恢复进入模块Activity－>", getNbBar().nbTitle.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.writeLogThread("离开模块Activity－>", getNbBar().nbTitle.getText().toString());
    }

    @Override
    public void setLayout(View view) {
        super.setLayout(view);
    }

    /**
     * 设置默认标题，从上一个页面传递
     */
    public void setDefaultTitle() {
        String defaultTitle = getIntent().getStringExtra("viewtitle");
        if (defaultTitle != null) {
            getNbBar().setNBTitle(defaultTitle);
        }
    }

    /**
     * 显示网络超时状态视图
     */
    public void showNetStatus() {
        showStatus(ResConfig.getStatusWifiId(), "糟了，网络好像有问题...");
    }

    @Override
    public void initNB() {
        ThemeConfig.setDefaultViewConfig(getNbBar(), getActivity());
    }

    public BaseActivity getBaseActivity() {
        return this;
    }

    @Override
    public void showProgress() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.show(getContext(), "", true, true, null);
        } else if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        try {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoading() {
        showProgress();
    }

    @Override
    public void hideLoading() {
        hideProgress();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ExitApp();
        }
        return false;
    }

    private void ExitApp() {
//        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            ToastUtil.showShort(this, "再按一次退出程序");
//            exitTime = System.currentTimeMillis();
//        } else {
        SinoBaseActivity.this.finish();
        finish();

//        }

    }


    @Override
    public void requestSuccess(@NotNull String resData, @NotNull String TAG) {

    }

    @Override
    public void requestFailed(@NotNull String resData) {
//        Toast.makeText(this, resData, Toast.LENGTH_SHORT).show();

    }


    protected void setView() {
        View view = findViewById(R.id.iv_right);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DBS_AboutActivity.class));
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }
}

package com.sinoyd.Code.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.sinoyd.R;


/**
 * 关于弹出框 Copyright (c) 2015 江苏远大信息股份有限公司
 *
 * @类型名称：AboutDialog
 * @创建人：刘敏
 * @创建日期：2015-1-27
 * @维护人员：
 * @维护日期：
 * @功能摘要：
 */
public class AboutDialog extends AlertDialog implements android.view.View.OnClickListener {
    public AboutDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public AboutDialog(Context context, int theme) {
        super(context, theme);
    }

    public AboutDialog(Context context) {
        super(context);
    }


    private TextView tvtvtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_about);
        // StyleController.updateViewBgAttrId(findViewById(R.id.dialog_bg),
        // R.attr.dialog_bg);
        // ((ImageView)findViewById(R.id.dialog_bg)).setImageDrawable(MyApplication.getDrawableByIdName("dialog_bg"));
        tvtvtv = (TextView) findViewById(R.id.tvtvtv);
        tvtvtv.setText("版本号:" + getLocalVersion(getContext()));

    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    public static String getLocalVersion(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName+"";
//            LogUtil.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dismiss();
        return super.onTouchEvent(event);
    }
}

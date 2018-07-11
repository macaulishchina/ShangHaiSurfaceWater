package com.sinoyd.Code.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.sinoyd.Code.Until.BMapUtil;
import com.sinoyd.Code.View.ArrowView;
import com.sinoyd.Code.View.MyScrollLayout;
import com.sinoyd.Code.View.OnViewChangeListener;
import com.sinoyd.R;

/**
 * 帮助页面 Copyright (c) 2015 江苏远大信息股份有限公司
 *
 * @类型名称：HelpActivity
 * @创建人：刘敏
 * @创建日期：2015-1-26
 * @维护人员：
 * @维护日期：
 * @功能摘要：
 */
public class DBS_HelpActivity extends Activity implements OnViewChangeListener {
    /**
     * 帮助图片
     **/
    private static int[] backRes = {R.drawable.help_1, R.drawable.help_2, R.drawable.help_3, R.drawable.help_4};
    /**
     * 滑动控件
     **/
    private MyScrollLayout mScrollLayout;
    /**
     * 图片数
     **/
    private int count;
    /**
     * 当前滑动到了第几张
     **/
    private int currentItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initView();
    }

    @SuppressWarnings("deprecation")
    protected void initView() {
        mScrollLayout = findViewById(R.id.ScrollLayout);
        count = mScrollLayout.getChildCount();
        if (count > 2) {
            mScrollLayout.getChildAt(0).setBackgroundDrawable(BMapUtil.getDrawableFromCache(this, backRes[0]));
            mScrollLayout.getChildAt(1).setBackgroundDrawable(BMapUtil.getDrawableFromCache(this, backRes[1]));
        }
        mScrollLayout.getChildAt(0);
        setcurrentPoint(0);
        mScrollLayout.SetOnViewChangeListener(this);

    }

    @Override
    public void OnViewChange(int position) {
        setcurrentPoint(position);
    }

    @SuppressWarnings("deprecation")
    private void setcurrentPoint(int position) {
        currentItem = position;
        mScrollLayout.getChildAt(currentItem).setBackgroundDrawable(BMapUtil.getDrawableFromCache(this, backRes[currentItem]));
        ((ArrowView) findViewById(R.id.arrowView)).setCheck(currentItem);
    }
}

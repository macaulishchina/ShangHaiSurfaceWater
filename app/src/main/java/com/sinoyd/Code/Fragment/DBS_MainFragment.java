package com.sinoyd.Code.Fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.sinoyd.R;
import com.sinoyd.frame.model.FrmTabIconModel;
import com.sinoyd.frame.views.FrmTabbar;

/**
 * 作者： 王一凡
 * 创建时间： 2017/9/6
 * 版权： 江苏远大信息股份有限公司
 * 描述： 主页Fragment管理类
 */
public class


DBS_MainFragment extends Fragment implements FrmTabbar.FrmTabbarListener {
    private FrmTabbar tabbar;
    private int index = 0;

    public FrmTabIconModel[] tabModels = new FrmTabIconModel[]{
            new FrmTabIconModel("首页", R.drawable.icon_home, R.drawable.icon_home_select, new DBS_TabMainFragment()),
            new FrmTabIconModel("监控数据", R.drawable.icon_jiankong, R.drawable.icon_jiankong_select, new DBS_TabMonitoringdataFragment()),
            new FrmTabIconModel("水质趋势", R.drawable.icon_shuizhi, R.drawable.icon_shuizhi_select, new DBS_TabWaterqualitytrendFragment()),
            new FrmTabIconModel("水质周报", R.drawable.icon_zhoubao, R.drawable.icon_zhoubao_select, new DBS_TabWaterqualityweeklyFragment()),
            new FrmTabIconModel("GIS", R.drawable.icon_gis, R.drawable.icon_gis_select, new DBS_TabGisFragment()),
            new FrmTabIconModel("质控管理", R.drawable.icon_guanli, R.drawable.icon_guanli_select, new DBS_TQualitycontrolFragment()),
            new FrmTabIconModel("报警信息", R.drawable.icon_alarm, R.drawable.icon_alarm_select, new DBS_TabAlarmFragment())
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frmmainfragment, null);
        WindowManager wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width / 5, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout ll = view.findViewById(R.id.llTabbar);


        for (int i = 0; i < tabModels.length; i++) {
            ll.addView(inflater.inflate(R.layout.frmtabitem, null), lp);
        }

        tabbar = new FrmTabbar(ll, tabModels);
        tabbar.setNormalColor("#FFFFFF");//未选中字体颜色
        tabbar.setSelectedColor("#FEE61D");//选中字体颜色
        tabbar.setTabbarListener(this);
        tabbar.create();


        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index", 1);
        }
        //设置默认选择的tab页面为1
        setDefaultIndex(index);

        return view;
    }

    /**
     * 设置默认tab页面
     *
     * @param index
     */
    private void setDefaultIndex(int index) {
        setItem();
        tabbar.changeSelectedIcon(index);
    }

    /**
     * 更改tab图标角标
     *
     * @param index
     * @param value
     */
    public void changeTips(int index, String value) {
        tabbar.setItemTipsValue(index, value);
    }

    @Override
    public void tabbarItemClickListener(int index) {
        this.index = index;
        setItem();
    }

    /**
     * tab切换事件
     */
    private void setItem() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment tabFragment = tabModels[index].fragment;
        Fragment fragment = getFragmentManager().findFragmentByTag(tabFragment.getClass().getName());
        if (fragment == null) {
            transaction.add(R.id.frgContent, tabFragment, tabFragment.getClass().getName());
        } else {
            transaction.show(fragment);
        }

        for (int i = 0; i < tabModels.length; i++) {
            if (i != index) {
                Fragment otherFragment = getFragmentManager().findFragmentByTag(tabModels[i].fragment.getClass().getName());
                if (otherFragment != null) {
                    transaction.hide(otherFragment);
                }
            }
        }
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}

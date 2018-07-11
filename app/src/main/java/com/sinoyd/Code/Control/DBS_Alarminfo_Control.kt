package com.sinoyd.Code.Control

import android.app.Activity
import android.widget.ListView
import com.sinoyd.Code.Adapter.AlarmInfoAdapter
import com.sinoyd.Code.DataClass.AlarmInfo
import org.jetbrains.anko.toast

/**
 * 作者： scj
 * 创建时间： 2018/2/5
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Control
 */
var alarmInfolist: ArrayList<AlarmInfo.DataBean.ListBean> = ArrayList()
var alarmadapter: AlarmInfoAdapter? = null

fun showlvalarm(activty: Activity, lv_alarm: ListView, resData: String, pageNo: Int) {
    var alarmInfo: AlarmInfo = AlarmInfo()
    try {
        alarmInfo = gson.fromJson(resData, AlarmInfo::class.java)
    } catch (e: Exception) {
        activty.toast("alarmInfo_JSON解析失败")
        return
    }
    if (alarmInfo.data.list.size == 0) {
        activty.toast("暂无数据更新")
        return
    }
    if (pageNo == 1) {
        alarmInfolist.clear()
    }
    alarmInfolist.addAll(alarmInfo.data.list)
    alarmadapter = AlarmInfoAdapter(activty, alarmInfolist)
    lv_alarm.adapter = alarmadapter
}
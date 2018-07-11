package com.sinoyd.Code.Control

import android.app.Activity
import android.widget.ListView
import com.sinoyd.Code.Adapter.WaterStateReportAdapter
import com.sinoyd.Code.DataClass.Report
import com.sinoyd.Code.DataClass.WaterStateReport
import org.jetbrains.anko.toast

/**
 * 作者： scj
 * 创建时间： 2018/2/5
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Control
 */

fun showlvweekyly(activity: Activity, lv_weekly: ListView, reData: String) {
    var adapter: WaterStateReportAdapter? = null
    var waterStateReport: WaterStateReport = WaterStateReport()
    var list: ArrayList<Report> = ArrayList()
    try {
        waterStateReport = gson.fromJson(reData, WaterStateReport::class.java)
    } catch (e: Exception) {
        activity.toast("waterStateReport_JSON解析出错")
        return
    }
    for (item in waterStateReport.data.list) {
        val report = Report()
        report.name = item.name
        report.url = item.url
        list.add(report)
    }

    adapter = WaterStateReportAdapter(activity, list)
    lv_weekly.adapter = adapter
}
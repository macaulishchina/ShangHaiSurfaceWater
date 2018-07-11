package com.sinoyd.Code.Control

import android.app.Activity
import android.util.Log
import android.widget.ListView
import com.google.gson.Gson
import com.sinoyd.Code.Activity.DBS_Detailsofclass2Activity
import com.sinoyd.Code.Activity.DBS_DetailsofclassActivity
import com.sinoyd.Code.Adapter.DetailedClassificationStatisticsAdapter
import com.sinoyd.Code.Adapter.FirstPageStatisticDataAdapter
import com.sinoyd.Code.DataClass.DetailedClassificationStatistics
import com.sinoyd.Code.DataClass.FirstPageStatisticData
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 作者： scj
 * 创建时间： 2018/1/11
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Control
 */

var detailedClassificationStatisticslist: MutableList<DetailedClassificationStatistics.DataBean.ListBean> = ArrayList()

fun showlv_detail(activity: Activity, resData: String, lv_detail: ListView, pageNo: Int) {

    var detailedClassificationStatistics: DetailedClassificationStatistics = DetailedClassificationStatistics()
    var adapter: DetailedClassificationStatisticsAdapter? = null
    try {
        detailedClassificationStatistics = gson.fromJson(resData, DetailedClassificationStatistics::class.java)
    } catch (e: Exception) {
        activity.toast("DetailedClassificationStatistics_JSON解析失败")
        return
    }

    if (pageNo == 1) {
        detailedClassificationStatisticslist.clear()
    } else {
        if (detailedClassificationStatistics.data.list.size == 0) {
            activity.toast("暂无数据更新")
            return
        }
    }
    detailedClassificationStatisticslist.addAll(detailedClassificationStatistics.data.list)

    adapter = DetailedClassificationStatisticsAdapter(activity, detailedClassificationStatisticslist)
    lv_detail.adapter = adapter


    lv_detail.setOnItemClickListener { adapterView, view, i, l ->
        activity.startActivity<DBS_Detailsofclass2Activity>(
                "id" to detailedClassificationStatisticslist[i].id,
                "name" to detailedClassificationStatisticslist[i].name
        )
    }

}
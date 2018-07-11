package com.sinoyd.Code.Control

import android.app.Activity
import android.util.Log
import android.widget.ListView
import com.google.gson.Gson
import com.sinoyd.Code.Activity.DBS_DetailsofclassActivity
import com.sinoyd.Code.Adapter.FirstPageStatisticDataAdapter
import com.sinoyd.Code.DataClass.FirstPageStatisticData
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 作者： scj
 * 创建时间： 2018/1/11
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Control
 */
var gson: Gson = Gson()
var firstPageStatisticDatalist: ArrayList<FirstPageStatisticData.DataBean.ListBean> = ArrayList()
//首页 listview 数据显示
fun showlv_main(activity: Activity, resData: String, lv_main: ListView, lvlength: Int, pageNo: Int, dimension: String, beginTime: String, endTime: String) {

    var firstPageStatisticData: FirstPageStatisticData = FirstPageStatisticData()
    var adapter: FirstPageStatisticDataAdapter? = null
    try {
        firstPageStatisticData = gson.fromJson(resData, FirstPageStatisticData::class.java)
    } catch (e: Exception) {
        activity.toast("FirstPageStatisticData_JSON解析失败")
        return
    }

    if (firstPageStatisticData.data.list.size == 0) {
        activity.toast("暂无数据更新")
        return
    }
    if (pageNo == 1) {
        firstPageStatisticDatalist.clear()

    }
//    Log.i("scj", "图形总长度：" + lvlength)
    firstPageStatisticDatalist.addAll(firstPageStatisticData.data.list)

    adapter = FirstPageStatisticDataAdapter(activity, firstPageStatisticDatalist, lvlength)
    lv_main.adapter = adapter


    lv_main.setOnItemClickListener { adapterView, view, i, l ->

        activity.startActivity<DBS_DetailsofclassActivity>(
                "id" to firstPageStatisticDatalist[i].id,
                "areaname" to firstPageStatisticDatalist[i].name,
                "dimension" to dimension,
                "beginTime" to beginTime,
                "endTime" to endTime
        )

    }
}

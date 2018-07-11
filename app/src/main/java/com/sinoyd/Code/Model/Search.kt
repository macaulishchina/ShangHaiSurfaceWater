package com.sinoyd.Code.Model

import android.util.Log
import com.sinoyd.Code.Until.HttpListener
import com.sinoyd.Code.Until.Networkrequestaddress
import com.sinoyd.Code.Until.ShowLog
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x

/**
 * 作者： scj
 * 创建时间： 2018/2/1
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Model
 */

interface SearchModel {
    fun getSearch(loginid: String,
                  station_type_id: String,//站点类型id
                  region_id: String,//区域id
                  area_id: String,//片区id
                  basin_id: String,//流域id
                  control_type_id: String,//控制类型id
                  river_course_id: String,//区河道id
                  key: String,//关键字
                  tag: String,
                  listener: HttpListener)
}


class SearchImpl : SearchModel {
    override fun getSearch(loginid: String,
                           station_type_id: String,//站点类型id
                           region_id: String,//区域id
                           area_id: String,//片区id
                           basin_id: String,//流域id
                           control_type_id: String,//控制类型id
                           river_course_id: String,//河道id
                           key: String,//关键字
                           tag: String,
                           listener: HttpListener) {
        val params = RequestParams(Networkrequestaddress.GetSearch)
        params.addBodyParameter("loginId", loginid)
        if (key != "") {
            params.addBodyParameter("keyword", key)
        }
        if (station_type_id != "") {//站点类型id
            params.addBodyParameter("station_type_id", station_type_id)
        }
        if (region_id != "") {//区域id
            params.addBodyParameter("region_id", region_id)
        }
        if (area_id != "") {//片区id
            params.addBodyParameter("area_id", area_id)
        }
        if (basin_id != "") {//流域id
            params.addBodyParameter("basin_id", basin_id)
        }
        if (control_type_id != "") {//控制类型id
            params.addBodyParameter("control_type_id", control_type_id)
        }
        if (river_course_id != "") {//区河道id
            params.addBodyParameter("river_course_id", river_course_id)
        }
        x.http().get(params, object : Callback.CommonCallback<String> {
            override fun onFinished() {
            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                ShowLog("name").show("onError", params, ex!!.message!!)
                listener.requestFailed(ex!!.message.toString())
            }

            override fun onCancelled(cex: Callback.CancelledException?) {
            }

            override fun onSuccess(result: String?) {
                ShowLog("name").show("onSuccess", params, result!!)
                listener.requestSuccess(result!!, tag)
            }

        })
    }

}
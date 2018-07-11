package com.sinoyd.Code.Model

import android.util.Log
import com.baidu.location.d.j.S
import com.sinoyd.Code.Until.HttpListener
import com.sinoyd.Code.Until.Networkrequestaddress
import com.sinoyd.Code.Until.ShowLog
import org.xutils.common.Callback
import org.xutils.x
import org.xutils.http.RequestParams


/**
 * 作者： scj
 * 创建时间： 2018/1/30
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Model
 *
 * 测试网络请求
 */

interface MonitoringsearchModel {
    fun getMonitoringsearch(portIds: String, displayFactorIds: String,
                            evaluateFactorIds: String,
                            beginTime: String,
                            endTime: String,
                            results: String,
                            dataType: String,
                            pageSize: Int,
                            pageNo: Int,
                            tag: String, listener: HttpListener)
}


class MonitoringsearchImpl : MonitoringsearchModel {
    override fun getMonitoringsearch(portIds: String,
                                     displayFactorIds: String,
                                     evaluateFactorIds: String,
                                     beginTime: String,
                                     endTime: String,
                                     results: String,
                                     dataType: String,
                                     pageSize: Int,
                                     pageNo: Int,
                                     tag: String, listener: HttpListener) {
        val params = RequestParams(Networkrequestaddress.GetMonitoringsearch)
        params.addBodyParameter("portIds", portIds)
        params.addBodyParameter("displayFactorIds", displayFactorIds)
        params.addBodyParameter("evaluateFactorIds", evaluateFactorIds)
        params.addBodyParameter("beginTime", beginTime)
        params.addBodyParameter("endTime", endTime)
        params.addBodyParameter("results", results)
        params.addBodyParameter("dataType", dataType)
        params.addBodyParameter("pageSize", pageSize.toString())
        params.addBodyParameter("pageNo", pageNo.toString())


        x.http().get(params, object : Callback.CommonCallback<String> {
            override fun onSuccess(result: String?) {
                ShowLog("name").show("onsuccess", params, result!!)

                listener.requestSuccess(result!!, tag)
            }

            override fun onCancelled(cex: Callback.CancelledException?) {

            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                ShowLog("name").show("onError", params, ex!!.message!!)
                listener.requestFailed(ex!!.message.toString())
            }

            override fun onFinished() {
            }
        })


    }

}
package com.sinoyd.Code.Model

import android.util.Log
import com.example.shenchuanjiang.kotlin1013test.params
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

interface FactorsModel {
    fun getFactors(portId: String, tag: String, listener: HttpListener)
}


class FactorsImpl : FactorsModel {
    override fun getFactors(portId: String, tag: String, listener: HttpListener) {
        var params: RequestParams? = null
        params = if (portId == "") {
            RequestParams(Networkrequestaddress.Getfactors)
        } else {
            RequestParams(Networkrequestaddress.Getfactors + "?portId=$portId")
        }
        Log.i("scj", "获取所有因子:${params.uri}")

        x.http().get(params, object : Callback.CommonCallback<String> {
            override fun onFinished() {
            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                ShowLog("xhttp").show("onError", params, ex!!.message!!)
                listener.requestFailed(ex!!.message.toString())
            }

            override fun onCancelled(cex: Callback.CancelledException?) {
            }

            override fun onSuccess(result: String?) {
                ShowLog("xhttp").show("onSuccess", params, result!!)
                listener.requestSuccess(result!!, tag)
            }

        })
    }

}
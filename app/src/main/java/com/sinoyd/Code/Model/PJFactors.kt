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

interface PJFactorsModel {
    fun getPJFactors(id: String, tag: String, listener: HttpListener)
}


class PJFactorsImpl : PJFactorsModel {
    override fun getPJFactors(id: String, tag: String, listener: HttpListener) {
        var url = Networkrequestaddress.GetPJFactors
        val params = RequestParams(url)
        params.addBodyParameter("portIds", id)
        Log.i("scj", "参与评价因子接口:${params.uri}")
        for (item in params.bodyParams) {
            Log.i("scj", "参数:${item.key} 值：${item.value}")
        }
        x.http().get(params, object : Callback.CommonCallback<String> {
            override fun onFinished() {
            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                ShowLog("xhttp").show("onError", params, ex?.message)
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
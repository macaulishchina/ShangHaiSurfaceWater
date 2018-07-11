package com.sinoyd.Code.Model

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

interface SearchfortypeModel {
    fun getSearchfortype(loginId: String, type_id: String, tag: String, listener: HttpListener)
}


class SearchfortypeImpl : SearchfortypeModel {
    override fun getSearchfortype(loginId: String, type_id: String, tag: String, listener: HttpListener) {
        val params = RequestParams(Networkrequestaddress.Getsearchfortypeid + "?" +
                "loginId=$loginId&" +
                "station_type_id=$type_id&")

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
package com.sinoyd.Code.Model

import android.telecom.Call
import com.macaulish.top.velvet.util.Logger
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

interface TaskModelInterface {
    fun requestData(pageSize:Int,pageNo:Int,listener: HttpListener,tag: String)
}


class TaskModel : TaskModelInterface {

    override fun requestData(pageSize : Int, pageNo : Int,listener : HttpListener,tag : String) {
        val params = RequestParams("${Networkrequestaddress.GetTaskInfo}?pageSize=$pageSize&pageNo=$pageNo")
        x.http().get(params,object : Callback.CommonCallback<String>{
            override fun onFinished() {
                ShowLog("xhttp").show("onFinished", params,null)
            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                ShowLog("xhttp").show("onError", params, ex!!.message)
                listener.requestFailed(ex.message.toString())
            }

            override fun onCancelled(cex: Callback.CancelledException?) {
            }

            override fun onSuccess(result: String?) {
                ShowLog("xhttp").show("onSuccess", params, result)
                listener.requestSuccess(result?:"", tag)
            }
        })
    }

}
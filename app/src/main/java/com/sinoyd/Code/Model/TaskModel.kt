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
    fun requestTaskByPage(pageSize:Int, pageNo:Int, listener: HttpListener, tag: String)
    fun uploadTask(taskName:String,pointIds:String, finishDate: String, dealMan: String,publisher: String,publishTime: String,tag: String, listener: HttpListener)
    fun requestPointInfo(userGuid:String,listener:HttpListener,tag:String)
    fun requestCheckByPage(pageSize: Int,pageNo:Int,startTime:String,endTime:String,listener: HttpListener,tag: String)
}


class TaskModel : TaskModelInterface {
    override fun requestCheckByPage(pageSize: Int, pageNo: Int, startTime: String, endTime: String, listener: HttpListener, tag: String) {
        //http://192.168.90.177:8888/api/v1/mobile/app/EvatlSatic?pageSize=10&pageNo=1&stime=2018-7-1&etime=2018-7-10
        val params = RequestParams("${Networkrequestaddress.GetCheckInfo}?pageSize=$pageSize&pageNo=$pageNo&stime=$startTime&etime=$endTime")
        x.http().get(params,object :Callback.CommonCallback<String>{
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

    override fun uploadTask(taskName: String, pointIds: String, finishDate: String, dealMan: String, publisher: String, publishTime: String, tag: String, listener: HttpListener) {
        //?Task=&Id=&Time=&Resperson=&Creater=&CreatDateTime=
        val params = RequestParams("${Networkrequestaddress.UpLoadTask}?Task=$taskName&Id=$pointIds&Time=$finishDate&Resperson=$dealMan&Creater=$publisher&CreatDateTime=$publishTime")
        params.addBodyParameter("Task",taskName)
        params.addBodyParameter("Id",pointIds)
        params.addBodyParameter("Time",finishDate)
        params.addBodyParameter("Resperson",dealMan)
        params.addBodyParameter("Creater",publisher)
        params.addBodyParameter("CreatDateTime",publishTime)
        x.http().post(params,object :Callback.CommonCallback<String>{
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


    override fun requestTaskByPage(pageSize : Int, pageNo : Int, listener : HttpListener, tag : String) {
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

    override fun requestPointInfo(userGuid:String,listener:HttpListener,tag:String){
        val params = RequestParams("${Networkrequestaddress.GroupPointInfo}?Operator=$userGuid")
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

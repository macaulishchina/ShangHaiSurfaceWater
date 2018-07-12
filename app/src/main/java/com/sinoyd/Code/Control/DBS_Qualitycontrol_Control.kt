package com.sinoyd.Code.Control

import android.app.Activity
import android.widget.ListView
import com.sinoyd.Code.Adapter.AlarmInfoAdapter
import com.sinoyd.Code.Adapter.TaskInfoAdapter
import com.sinoyd.Code.DataClass.AlarmInfo
import com.sinoyd.Code.DataClass.TaskInfo
import com.sinoyd.Code.Model.LoginModel
import com.sinoyd.Code.Until.HttpListener
import com.sinoyd.Code.Until.Networkrequestaddress
import com.sinoyd.Code.Until.ShowLog
import com.sinoyd.R.id.lv_alarm
import org.jetbrains.anko.toast
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x

/**
 * 作者： scj
 * 创建时间： 2018/2/5
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Control
 */
var taskInfoList: ArrayList<TaskInfo.DataBean.Task> = ArrayList()

fun showTaskDate(activity: Activity, taskLV: ListView, resData: String, pageNo: Int) {
    var taskInfo:TaskInfo

    try {
        taskInfo = gson.fromJson(resData, TaskInfo::class.java)
    } catch (e: Exception) {
        activity.toast("taskInfo_JSON解析失败")
        return
    }
    if (taskInfo.data.list.isEmpty()) {
        activity.toast("暂无数据更新")
        return
    }
    if (pageNo == 1) {
        taskInfoList.clear()
    }
    taskInfoList.addAll(taskInfo.data.list)
    taskLV.adapter = TaskInfoAdapter(activity, taskInfoList)
}
package com.sinoyd.Code.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.macaulish.top.velvet.util.Logger
import com.sinoyd.Code.Activity.DBS_AddTaskActivity
import com.sinoyd.Code.Control.showCheckDate
import com.sinoyd.Code.Control.showTaskDate
import com.sinoyd.Code.Dialog.DateSelectDialog
import com.sinoyd.Code.Model.TaskModel
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.environmentsz.Kotlin.getSpecifiedDayAfter
import com.sinoyd.environmentsz.Kotlin.getSpecifiedDayBefore
import com.sinoyd.environmentsz.Kotlin.getToday
import com.sinoyd.environmentsz.Kotlin.getlastweekToday
import com.sinoyd.frame.frgs.SinoBaseFragment
import com.sinoyd.frame.views.FrmListFootView
import kotlinx.android.synthetic.main.chooselayout.*
import kotlinx.android.synthetic.main.dbs_quality_control_fragment.*
import kotlinx.android.synthetic.main.layout_date_select.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import java.util.*

/**
 * Created by Sinoyd on 2017/12/18.
 */
class DBS_TQualitycontrolFragment : SinoBaseFragment(),DateSelectDialog.DateSelectListener{

    var date: Date = Date()
    var beginTime: String = date.getlastweekToday()
    var endTime: String = date.getToday()

    val taskModel = TaskModel()

    /**
     * 任务管理分页数据
     */
    var pageTaskNo: Int = 1
    var pageTaskSize: Int = 10
    var lastTaskModel: Int = 0
    var totalTaskIndex: Int = 0
    /**
     * 考核管理分页数据
     */
    var pageCheckNo: Int = 1
    var pageCheckSize: Int = 10
    var lastCheckIndex: Int = 0
    var totalCheckIndex: Int = 0

    lateinit var footLoadView:FrmListFootView


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout(R.layout.dbs_quality_control_fragment)
        nbBar.hide()
        setView()
        setlisteners()
        Logger.i("质控管理界面 onActivityCreated()")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestData()
        Logger.i("质控管理页面")
    }

    fun requestData(){
        showdialog(activity, "loadshow")
        Logger.i("$beginTime--$endTime")
        taskModel.requestTaskByPage(pageTaskSize,pageTaskNo,this,TASK_INFO_GET)
        taskModel.requestCheckByPage(pageCheckSize,pageCheckNo,beginTime,endTime,this, CHECK_INFO_GET)
    }

    private fun setlisteners() {

        //日期箭头监听事件
        iv_date_arrowLeft.onClick { tv_date_startTime.performClick() }
        tv_date_startTime.onClick {
            tv_date_startTime.text = date.getSpecifiedDayBefore(tv_date_startTime.text.toString())
            beginTime = tv_date_startTime.text.toString()
            Logger.i("$beginTime--$endTime")
            showdialog(activity, "loadshow")
            taskModel.requestCheckByPage(pageCheckSize,pageCheckNo,beginTime,endTime,this, CHECK_INFO_GET)
            //Toast.makeText(this.context,"暂无统计数据",Toast.LENGTH_SHORT).show()

        }

        tv_date_flag.onClick {
            val dateSelectDialog = DateSelectDialog(activity, R.style.Theme_Dialog_Transparent)
            dateSelectDialog.setStartAndEndDate(beginTime, endTime)
            dateSelectDialog.setDateSelectListener(this)
            dateSelectDialog.show()
        }

        iv_date_arrowRight.onClick { tv_date_endTime.performClick() }
        tv_date_endTime.onClick {
            tv_date_endTime.text = date.getSpecifiedDayAfter(tv_date_endTime.text.toString())
            endTime = tv_date_endTime.text.toString()
            showdialog(activity, "loadshow")
            taskModel.requestCheckByPage(pageCheckSize,pageCheckNo,beginTime,endTime,this, CHECK_INFO_GET)
            //Toast.makeText(this.context,"暂无统计数据",Toast.LENGTH_SHORT).show()

        }

        //选择栏监听事件
        rg_pointchoose.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb_type -> {
                    ll_tongji.visibility = View.GONE
                    ll_task.visibility = View.VISIBLE
                    //发请求
                }
                R.id.rb_area -> {
                    ll_task.visibility = View.GONE
                    ll_tongji.visibility = View.VISIBLE
                    //发请求

                }
            }
        }

        //新建任务
        add_task.onClick {
            var intent = Intent()
            intent.setClass(activity, DBS_AddTaskActivity::class.java)
            startActivityForResult(intent, 1000)
        }

    }
    override fun selectDate(startDate: String, endDate: String) {
        tv_date_startTime.text = startDate
        tv_date_endTime.text = endDate
        beginTime = startDate
        endTime = endDate
        taskModel.requestCheckByPage(pageCheckSize,pageCheckNo,beginTime,endTime,this, CHECK_INFO_GET)
    }

    override fun setView() {
        super.setView()
        tv_date_startTime.text = date.getlastweekToday()
        tv_date_endTime.text = date.getToday()
        ll_tongji.visibility = View.GONE
        ll_task.visibility = View.VISIBLE
        titlename.text = "质控管理"
        iv_left.visibility = View.INVISIBLE
        rb_type.text = "任务收发"
        rb_area.text = "考核统计"
        rb_type.isChecked = true
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(activity, "loadsuccess")
        //footLoadView!!.visibility = View.GONE
        when (TAG) {
            TASK_INFO_GET -> {
                Logger.i("请求任务获得的数据：$resData")
                showTaskDate(activity,lv_qualitycontrol, resData,pageTaskNo)
            }
            CHECK_INFO_GET->{
                showCheckDate(activity,lv_check,resData,pageCheckNo)
            }
        }
    }

    override fun requestFailed(resData: String) {
        super.requestFailed(resData)
        showdialog(activity, "loadfail")
        //footLoadView!!.visibility = View.GONE
        lv_qualitycontrol.adapter = null
    }


    companion object {
        private val TASK_INFO_GET = "task information get"
        private val CHECK_INFO_GET = "check information get"
    }
}
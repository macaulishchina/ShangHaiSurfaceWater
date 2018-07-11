package com.sinoyd.Code.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.macaulish.top.velvet.util.Logger
import com.sinoyd.Code.Activity.DBS_AddTaskActivity
import com.sinoyd.Code.Dialog.DateSelectDialog
import com.sinoyd.Code.Model.AlarmInfoImpl
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.R.id.*
import com.sinoyd.environmentsz.Kotlin.getSpecifiedDayAfter
import com.sinoyd.environmentsz.Kotlin.getSpecifiedDayBefore
import com.sinoyd.environmentsz.Kotlin.getToday
import com.sinoyd.environmentsz.Kotlin.getlastweekToday
import com.sinoyd.frame.frgs.SinoBaseFragment
import com.sinoyd.frame.task.BaseTask
import com.sinoyd.frame.util.ToastUtil
import com.sinoyd.frame.views.FrmListFootView
import kotlinx.android.synthetic.main.chooselayout.*
import kotlinx.android.synthetic.main.dbs_alarm_fragment.*
import kotlinx.android.synthetic.main.dbs_quality_control_fragment.*
import kotlinx.android.synthetic.main.layout_date_select.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import java.util.*

/**
 * Created by Sinoyd on 2017/12/18.
 */
class DBS_TQualitycontrolFragment : SinoBaseFragment(),DateSelectDialog.DateSelectListener{

    var date: Date = Date()
    var beginTime: String = date.getlastweekToday()
    var endTime: String = date.getToday()

    var pageNo: Int = 1
    var pageSize: Int = 10
    var footLoadView: FrmListFootView? = null
    var last_index: Int = 0
    var total_index: Int = 0

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
        Logger.i("质控管理页面")
    }

    fun firstLoaddata(){
        showdialog(activity, "loadshow")

    }

    private fun setlisteners() {

        //日期箭头监听事件
        iv_date_arrowLeft.onClick { tv_date_startTime.performClick() }
        tv_date_startTime.onClick {
            tv_date_startTime.text = date.getSpecifiedDayBefore(tv_date_startTime.text.toString())
            beginTime = tv_date_startTime.text.toString()
            Toast.makeText(this.context,"暂无统计数据",Toast.LENGTH_SHORT).show()

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
            Toast.makeText(this.context,"暂无统计数据",Toast.LENGTH_SHORT).show()

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
        Toast.makeText(this.context,"暂无统计数据",Toast.LENGTH_SHORT).show()
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



}
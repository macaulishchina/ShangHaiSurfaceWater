package com.sinoyd.Code.Activity

import android.os.Bundle
import android.view.Gravity
import com.sinoyd.Code.Dialog.DateSelectDialog
import com.sinoyd.Code.Until.DisplayorhideSoftkeyboard
import com.sinoyd.R
import com.sinoyd.environmentsz.Kotlin.getToday
import com.sinoyd.environmentsz.Kotlin.getnextweekToday
import com.sinoyd.frame.actys.SinoBaseActivity
import kotlinx.android.synthetic.main.activity_dbs__add_task.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import java.util.*

class DBS_AddTaskActivity : SinoBaseActivity(), DateSelectDialog.DateSelectListener {
    override fun selectDate(startDate: String, endDate: String) {
        tv_stationtime.text = "$startDate--$endDate"
    }

    var date: Date = Date()
    var beginTime: String = date.getToday()
    var endTime: String = date.getnextweekToday()

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbs__add_task)
        setView()
        setlisteners()
    }

    override fun setView() {
        super.setView()
        titlename.text = "新建任务"
        titlename.gravity = Gravity.CENTER_VERTICAL
        tv_stationtime.text = "$beginTime--$endTime"
        DisplayorhideSoftkeyboard.hideSoftkeyboard(activity)
    }

    private fun setlisteners() {
        //返回按钮
        iv_left.onClick {
            finish()
        }
        iv_task_date.onClick {
            val dateSelectDialog = DateSelectDialog(activity, R.style.Theme_Dialog_Transparent)
            dateSelectDialog.setStartAndEndDate(beginTime, endTime)
            dateSelectDialog.setDateSelectListener(this)
            dateSelectDialog.show()
        }
    }

}

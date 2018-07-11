package com.sinoyd.Code.Dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.DatePicker
import android.widget.Toast

import org.jetbrains.anko.toast
import com.sinoyd.R

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * 起始结束日期选择弹出框 Copyright (c) 2015 江苏远大信息股份有限公司
 *
 * @类型名称：DateSelectDialog
 * @创建人：刘敏
 * @创建日期：2015-1-27
 * @维护人员：
 * @维护日期：
 * @功能摘要：
 */
@SuppressLint("NewApi")
class DateSelectDialog : Dialog, View.OnClickListener {

    private var dateSelectListener: DateSelectListener? = null
    private var startTimeStr: String? = null
    private var endTimeStr: String? = null

    private var startDatePicker: DatePicker? = null
    private var endDatePicker: DatePicker? = null

    interface DateSelectListener {
        fun selectDate(startDate: String, endDate: String)
    }

    fun setDateSelectListener(dateSelectListener: DateSelectListener) {
        this.dateSelectListener = dateSelectListener
    }

    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener) {
        initView()
    }

    constructor(context: Context, theme: Int) : super(context, R.style.Theme_Dialog_Transparent) {
        initView()
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    private fun initView() {
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.date_selelct_layout)
        findViewById<View>(R.id.ok_btn).setOnClickListener(this)
        findViewById<View>(R.id.cancel_btn).setOnClickListener(this)
        startDatePicker = findViewById<View>(R.id.start_date_picker) as DatePicker
        endDatePicker = findViewById<View>(R.id.end_date_picker) as DatePicker
    }

    override fun show() {
        super.show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ok_btn -> {
                if (dateSelectListener != null) {
                    val dateFormat = SimpleDateFormat("yyyy/MM/dd")
                    val calendar = Calendar.getInstance()
                    calendar.set(startDatePicker!!.year, startDatePicker!!.month, startDatePicker!!.dayOfMonth)
                    val startDate = dateFormat.format(calendar.time)
                    calendar.set(endDatePicker!!.year, endDatePicker!!.month, endDatePicker!!.dayOfMonth)
                    val endDate = dateFormat.format(calendar.time)
                    if (Integer.parseInt(startDate.replace("/", "")) >= Integer.parseInt(endDate.replace("/", ""))) {
                        context.toast("起始日期不能大于结束日期")
                        return
                    }
                    dateSelectListener!!.selectDate(startDate, endDate)
                }
                dismiss()
            }
            R.id.cancel_btn -> dismiss()
            else -> dismiss()
        }
    }

    /***
     * 设置日期
     *
     * @param startTimeStr
     * @param endTimeStr
     */
    fun setStartAndEndDate(startTimeStr: String?, endTimeStr: String?) {
        if (startTimeStr == null || endTimeStr == null)
            return
        this.startTimeStr = startTimeStr
        this.endTimeStr = endTimeStr
        val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        val calendar = Calendar.getInstance()
        val startDate: Date
        val endDate: Date
        try {
            if (android.os.Build.VERSION.SDK_INT >= 11) {
                startDate = dateFormat.parse(startTimeStr)
                calendar.time = startDate
                startDatePicker!!.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null)
                endDate = dateFormat.parse(endTimeStr)
                calendar.time = endDate
                endDatePicker!!.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

    }
}

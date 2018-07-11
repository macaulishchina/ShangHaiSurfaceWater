package com.sinoyd.Code.Fragment

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import com.sinoyd.Code.Control.showlvalarm
import com.sinoyd.Code.Dialog.DateSelectDialog
import com.sinoyd.Code.Model.AlarmInfoImpl
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.R.id.tv_date_startTime
import com.sinoyd.environmentsz.Kotlin.*
import com.sinoyd.frame.frgs.SinoBaseFragment
import com.sinoyd.frame.views.FrmListFootView
import kotlinx.android.synthetic.main.dbs_alarm_fragment.*
import kotlinx.android.synthetic.main.dbs_main_fragment.*
import kotlinx.android.synthetic.main.layout_date_select.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import java.util.*

/**
 * Created by Sinoyd on 2017/12/18.
 */
class DBS_TabAlarmFragment : SinoBaseFragment(), DateSelectDialog.DateSelectListener {

    override fun selectDate(startDate: String, endDate: String) {
        tv_date_startTime.text = startDate
        tv_date_endTime.text = endDate
        beginTime = startDate
        endTime = endDate
        sendAlarmInfo()
    }

    var date: Date = Date()
    var beginTime: String = date.getlastweekToday()
    var endTime: String = date.getToday()
    var alarmInfoImpl: AlarmInfoImpl = AlarmInfoImpl()
    var pageNo: Int = 1
    var pageSize: Int = 10
    var footLoadView: FrmListFootView? = null
    var last_index: Int = 0
    var total_index: Int = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout(R.layout.dbs_alarm_fragment)
        nbBar.hide()
        setView()
        setlisteners()
        sendAlarmInfo()

    }

    override fun setView() {
        super.setView()
        titlename.text = "报警信息"
        iv_left.visibility = View.INVISIBLE
        tv_date_startTime.text = date.getlastweekToday()
        tv_date_endTime.text = date.getToday()
        footLoadView = FrmListFootView(activity)
        lv_alarm.addFooterView(footLoadView)
        footLoadView!!.visibility = View.GONE
    }

    private fun setlisteners() {
        //日期箭头监听事件
        iv_date_arrowLeft.onClick { tv_date_startTime.performClick() }
        tv_date_startTime.onClick {
            tv_date_startTime.text = date.getSpecifiedDayBefore(tv_date_startTime.text.toString())
            beginTime = tv_date_startTime.text.toString()
            sendAlarmInfo()
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
            sendAlarmInfo()
        }

        //下拉刷新
        swipeRefreshLayout_alarm.setOnRefreshListener {
            swipeRefreshLayout_alarm.isRefreshing = false
            pageNo = 1
            sendAlarmInfo()
        }

        //上拉刷新
        lv_alarm.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(p0: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                last_index = firstVisibleItem + visibleItemCount
                total_index = totalItemCount
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if (last_index == total_index && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
                    //上拉加载更多
                    ++pageNo
                    footLoadView!!.visibility = View.VISIBLE
                    sendAlarmInfo()
                }
            }
        })

    }

    fun sendAlarmInfo() {
        showdialog(activity, "loadshow")
        alarmInfoImpl.getAlarmInfo(beginTime, endTime, pageNo, pageSize, "getAlarmInfo", this)
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(activity, "loadsuccess")
        footLoadView!!.visibility = View.GONE
        when (TAG) {
            "getAlarmInfo" -> {
                showlvalarm(activity,lv_alarm, resData,pageNo)
            }
        }
    }

    override fun requestFailed(resData: String) {
        super.requestFailed(resData)
        showdialog(activity, "loadfail")
        footLoadView!!.visibility = View.GONE
        lv_main.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        showdialog(activity, "loaddismiss")
    }
}
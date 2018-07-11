package com.sinoyd.Code.Fragment

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.AbsListView
import com.example.shenchuanjiang.kotlin1013test.listview
import com.sinoyd.Code.Control.showlv_main
import com.sinoyd.Code.Dialog.DateSelectDialog
import com.sinoyd.Code.Model.FirstPageStatisticDataImpl
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.environmentsz.Kotlin.getDateofYesterday
import com.sinoyd.environmentsz.Kotlin.getSpecifiedDayAfter
import com.sinoyd.environmentsz.Kotlin.getSpecifiedDayBefore
import com.sinoyd.environmentsz.Kotlin.getToday
import com.sinoyd.frame.frgs.SinoBaseFragment
import com.sinoyd.frame.views.FrmListFootView
import kotlinx.android.synthetic.main.dbs_main_fragment.*
import kotlinx.android.synthetic.main.layout_date_select.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.onScrollListener
import java.util.*

/**
 * Created by Sinoyd on 2017/12/18.
 */
class DBS_TabMainFragment : SinoBaseFragment(), DateSelectDialog.DateSelectListener {
    override fun selectDate(startDate: String, endDate: String) {
        tv_date_startTime.text = startDate
        tv_date_endTime.text = endDate
        beginTime = startDate!!
        endTime = endDate!!
        sendFirstPageStatisticData()
    }

    var date: Date = Date()
    var beginTime: String = date.getDateofYesterday()
    var endTime: String = date.getToday()
    //listview的长度
    var lvlength: Int = 0
    var footLoadView: FrmListFootView? = null
    var last_index: Int = 0
    var total_index: Int = 0

    /***参数**/
    var dimension = "area"
    var pageNo: Int = 1
    var pageSize: Int = 10
    var getFirstPageStatisticData: FirstPageStatisticDataImpl = FirstPageStatisticDataImpl()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout(R.layout.dbs_main_fragment)
        nbBar.hide()
        setView()
        listeners()
        //获取listview的长度
        view.post { lvlength = lv_main.width / 2 }
        sendFirstPageStatisticData()
    }

    //初始化界面的一些设置
    override fun setView() {
        super.setView()
        titlename.text = "首 页"
        iv_left.visibility = View.INVISIBLE
        rb_main_area.isChecked = true
        tv_date_startTime.text = beginTime
        tv_date_endTime.text = endTime
        footLoadView = FrmListFootView(activity)
        lv_main.addFooterView(footLoadView)
        footLoadView!!.visibility = GONE
    }

    //监听事件
    private fun listeners() {
        //被选对象
        rg_main.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.rb_main_area -> {
                    dimension = "area"
                    pageNo = 1
                }
                R.id.rb_main_basin -> {
                    dimension = "water"
                    pageNo = 1
                }
                R.id.rb_main_operation -> {
                    dimension = "corporation"
                    pageNo = 1
                }
                R.id.rb_main_attribute -> {
                    dimension = "property"
                    pageNo = 1
                }
            }
            sendFirstPageStatisticData()
        }
        //日期按钮监听
        iv_date_arrowLeft.onClick { tv_date_startTime.performClick() }
        tv_date_startTime.onClick {
            tv_date_startTime.text = date.getSpecifiedDayBefore(tv_date_startTime.text.toString())
            beginTime = tv_date_startTime.text.toString()
            sendFirstPageStatisticData()
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
            sendFirstPageStatisticData()
        }

        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            pageNo = 1
            sendFirstPageStatisticData()
        }

        //上拉刷新
        lv_main.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(p0: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                last_index = firstVisibleItem + visibleItemCount
                total_index = totalItemCount
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if (last_index == total_index && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
                    //上拉加载更多
                    ++pageNo
                    footLoadView!!.visibility = View.VISIBLE
                    sendFirstPageStatisticData()
                }
            }
        })
    }

    //发送请求 首页统计数据
    private fun sendFirstPageStatisticData() {
        showdialog(activity, "loadshow")
        getFirstPageStatisticData.getFirstPageStatisticData(dimension, beginTime, endTime, pageNo, pageSize, "getFirstPageStatisticData", this)
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(activity, "loadsuccess")
        footLoadView!!.visibility = GONE
        when (TAG) {
            "getFirstPageStatisticData" -> {
                showlv_main(activity, resData, lv_main, lvlength, pageNo, dimension, beginTime, endTime)
            }
        }
    }

    override fun requestFailed(resData: String) {
        super.requestFailed(resData)
        showdialog(activity, "loadfail")
        footLoadView!!.visibility = GONE
        lv_main.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        showdialog(activity, "loaddismiss")
    }
}
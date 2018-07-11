package com.sinoyd.Code.Activity

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AbsListView
import com.sinoyd.Code.Control.showlv_detail
import com.sinoyd.Code.Model.DetailedClassificationStatisticsImpl
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.frame.actys.SinoBaseActivity
import com.sinoyd.frame.views.FrmListFootView
import kotlinx.android.synthetic.main.activity_dbs__detailsofclass.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick

/**
 * 分类详情页面
 * */

class DBS_DetailsofclassActivity : SinoBaseActivity() {

    //参数
    var id: String = ""
    var dimension: String = ""
    var pageSize: Int = 10
    var pageNo: Int = 1
    var beginTime: String = ""
    var endTime: String = ""
    var areaname: String = ""
    var detailedClassificationStatisticsImpl: DetailedClassificationStatisticsImpl = DetailedClassificationStatisticsImpl()

    var footLoadView: FrmListFootView? = null
    var last_index: Int = 0
    var total_index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbs__detailsofclass)
        nbBar.hide()
        id = intent.getStringExtra("id")
        dimension = intent.getStringExtra("dimension")
        beginTime = intent.getStringExtra("beginTime")
        endTime = intent.getStringExtra("endTime")
        areaname = intent.getStringExtra("areaname")
        setView()
        setlisteners()
    }

    override fun onResume() {
        super.onResume()
        sendDetailedClassificationStatistics()
    }

    //初始化界面
    override fun setView() {
        super.setView()
        titlename.gravity = Gravity.CENTER_VERTICAL
        titlename.text = areaname

        footLoadView = FrmListFootView(activity)
        lv_detail.addFooterView(footLoadView)
        footLoadView!!.visibility = View.GONE
    }

    //控件的监听事件
    private fun setlisteners() {
        iv_left.onClick {
            finish()
        }

        //下拉刷新
        swipeRefreshLayout_detail.setOnRefreshListener {
            swipeRefreshLayout_detail.isRefreshing = false
            pageNo = 1
            sendDetailedClassificationStatistics()
        }

        //上拉加载
        lv_detail.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(p0: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                last_index = firstVisibleItem + visibleItemCount
                total_index = totalItemCount
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if (last_index == total_index && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
                    //上拉加载更多
                    ++pageNo
                    footLoadView!!.visibility = View.VISIBLE
                    sendDetailedClassificationStatistics()
                }
            }
        })


    }

    //发送网络请求  【分类详细统计数据】
    fun sendDetailedClassificationStatistics() {
        showdialog(this, "loadshow")
        detailedClassificationStatisticsImpl.getDetailedClassificationStatistics(id, dimension, beginTime, endTime, pageNo, pageSize, "detailedClassificationStatisticsImpl", this)
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        footLoadView!!.visibility = View.GONE
        showdialog(this, "loadsuccess")
        when (TAG) {
            "detailedClassificationStatisticsImpl" -> {
                showlv_detail(activity, resData, lv_detail, pageNo)
            }
        }
    }

    override fun requestFailed(resData: String) {
        footLoadView!!.visibility = View.GONE
        showdialog(this, "loadfail")
        super.requestFailed(resData)
    }

    override fun onDestroy() {
        showdialog(this, "loaddismiss")
        super.onDestroy()
    }
}

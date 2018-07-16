package com.sinoyd.Code.Fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.AbsListView
import com.sinoyd.Code.Control.showlvweekyly
import com.sinoyd.Code.Model.WaterStateReportImpl
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.frame.frgs.SinoBaseFragment
import com.sinoyd.frame.views.FrmListFootView
import kotlinx.android.synthetic.main.dbs_quality_control_fragment.*
import kotlinx.android.synthetic.main.dbs_waterqualityweekly_fragment.*
import kotlinx.android.synthetic.main.titlelayout.*

/**
 * Created by Sinoyd on 2017/12/18.
 */
class DBS_TabWaterqualityweeklyFragment : SinoBaseFragment() {

    /**
     * 任务管理分页数据
     */
    var pageNo: Int = 1
    var pageSize: Int = 10
    var lastIndex: Int = 0
    var totalIndex: Int = 0
    lateinit var footLoadView: FrmListFootView

    var waterStateReportImpl: WaterStateReportImpl = WaterStateReportImpl()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout(R.layout.dbs_waterqualityweekly_fragment)
        nbBar.hide()
        setView()
        setlisteners()
        loadWaterStateReport()
        requestPermissions()
    }


    override fun setView() {
        super.setView()
        titlename.text = "水质周报"
        iv_left.visibility = View.INVISIBLE
        footLoadView = FrmListFootView(activity)
        lv_weekly.addFooterView(footLoadView)
        footLoadView.visibility = View.GONE
    }

    private fun setlisteners() {
        //下拉刷新
        weekly_report_rf_report_list.setOnRefreshListener {
            weekly_report_rf_report_list.isRefreshing = false
            pageNo = 1
            loadWaterStateReport()
        }

        //上拉刷新
        lv_weekly.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(p0: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                lastIndex = firstVisibleItem + visibleItemCount
                totalIndex = totalItemCount
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if (lastIndex == totalIndex && (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)) {
                    //上拉加载更多
                    ++pageNo
                    loadWaterStateReport()
                }
            }
        })
    }


    //发送请求   水质周报
    private fun loadWaterStateReport() {
        showdialog(activity, "loadshow")
        footLoadView.visibility = View.VISIBLE
        waterStateReportImpl.getWaterStateReport(pageNo,pageSize,"waterStateReportImpl", this)
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(activity, "loadsuccess")
        footLoadView.visibility = View.GONE
        when (TAG) {
            "waterStateReportImpl" -> {
                showlvweekyly(activity, lv_weekly, resData,pageNo)
            }
        }
    }

    override fun requestFailed(resData: String) {
        super.requestFailed(resData)
        showdialog(activity, "loadfail")
        footLoadView.visibility = View.GONE
        lv_weekly.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        showdialog(activity, "loaddismiss")
    }

    /**
     * 请求相关运行时权限
     */
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this.activity, arrayOf( Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE_DEFAULT)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE_DEFAULT -> {

            }
        }
    }

    companion object {
        const val PERMISSION_REQUEST_CODE_DEFAULT = 0

        const val RESULT_ACTIVITY_MEDIA_CAMERA = 100

        const val SALMON_DIRECTORY_NAME = "salmon"
    }

}
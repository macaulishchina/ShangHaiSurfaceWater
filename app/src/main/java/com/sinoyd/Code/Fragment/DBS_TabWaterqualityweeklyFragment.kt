package com.sinoyd.Code.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sinoyd.Code.Activity.DBS_AboutActivity
import com.sinoyd.Code.Control.showlv_main
import com.sinoyd.Code.Control.showlvweekyly
import com.sinoyd.Code.Model.WaterStateReportImpl
import com.sinoyd.Code.Until.DownloadUtil
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.R.id.*
import com.sinoyd.frame.frgs.SinoBaseFragment
import com.sinoyd.frame.task.BaseTask
import com.sinoyd.showcase.actys.ShowCase_InitActivity.activity
import kotlinx.android.synthetic.main.dbs_main_fragment.*
import kotlinx.android.synthetic.main.dbs_waterqualityweekly_fragment.*
import kotlinx.android.synthetic.main.titlelayout.*

/**
 * Created by Sinoyd on 2017/12/18.
 */
class DBS_TabWaterqualityweeklyFragment : SinoBaseFragment() {


    var waterStateReportImpl: WaterStateReportImpl = WaterStateReportImpl()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout(R.layout.dbs_waterqualityweekly_fragment)
        nbBar.hide()
        setView()
        setlisteners()
        sendWaterStateReport()
    }


    override fun setView() {
        super.setView()
        titlename.text = "水质周报"
        iv_left.visibility = View.INVISIBLE
    }

    private fun setlisteners() {

    }


    //发送请求   水质周报
    private fun sendWaterStateReport() {
        showdialog(activity, "loadshow")
        waterStateReportImpl.getWaterStateReport("waterStateReportImpl", this)
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)

        showdialog(activity, "loadsuccess")
        when (TAG) {
            "waterStateReportImpl" -> {
                showlvweekyly(activity, lv_weekly, resData)
            }
        }
    }

    override fun requestFailed(resData: String) {
        super.requestFailed(resData)
        showdialog(activity, "loadfail")
        lv_weekly.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        showdialog(activity, "loaddismiss")
    }


}
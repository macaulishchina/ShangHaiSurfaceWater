package com.sinoyd.Code.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.sinoyd.Code.Control.gson
import com.sinoyd.Code.Control.showgismap
import com.sinoyd.Code.Control.showlist
import com.sinoyd.Code.DataClass.Gis
import com.sinoyd.Code.Model.GisImpl
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.frame.frgs.SinoBaseFragment
import kotlinx.android.synthetic.main.dbs_gis_fragment.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

/**
 * Created by Sinoyd on 2017/12/18.
 * Gis界面
 */
class DBS_TabGisFragment : SinoBaseFragment() {

    //请求
    var gisImpl: GisImpl = GisImpl()
    var gis: Gis = Gis()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout(R.layout.dbs_gis_fragment)
        nbBar.hide()
        setlisteners()
        setView()
        sendgis()
    }

    private fun setlisteners() {
        iv_off.onClick {
            if (ll_gis.visibility == View.GONE) {
                ll_gis.visibility = View.VISIBLE
                iv_off.setImageResource(R.drawable.icon_arrow_bot)
            } else {
                ll_gis.visibility = View.GONE
                iv_off.setImageResource(R.drawable.icon_arrow_to)
            }
        }

    }

    override fun setView() {
        super.setView()
        titlename.text = "GIS"
        iv_left.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        showdialog(activity, "loaddismiss")
        bmapView.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        bmapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        bmapView.onPause()
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(activity, "loadsuccess")
        when (TAG) {
            "gisImpl" -> {
                try {
                    gis = gson.fromJson(resData, Gis::class.java)
                } catch (e: Exception) {
                    toast("gis_JSON解析有误")
                    return
                }
                //地图显示站点
                showgismap(activity, bmapView, gis.data)
                //显示listview
                showlist(activity, lv_gis, gis.data, bmapView)
            }

        }
    }

    override fun requestFailed(resData: String) {
        super.requestFailed(resData)
        showdialog(activity, "loadfail")
    }



    //发送请求
    fun sendgis() {
        showdialog(activity, "loadshow")
        gisImpl.getgis("gisImpl", this)

    }

}
package com.sinoyd.Code.Fragment

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.sinoyd.Code.Activity.DBS_PointChooseActivity
import com.sinoyd.Code.Control.gson
import com.sinoyd.Code.Control.showlinechart
import com.sinoyd.Code.Control.showmiddle
import com.sinoyd.Code.DataClass.GVkeyvaluesimple
import com.sinoyd.Code.DataClass.Nearby
import com.sinoyd.Code.DataClass.Trend
import com.sinoyd.Code.Dialog.DateSelectDialog
import com.sinoyd.Code.Model.NearbyImpl
import com.sinoyd.Code.Model.TrendImpl
import com.sinoyd.Code.Until.Getpermission
import com.sinoyd.Code.Until.SharedPreferencesFactory
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.R.id.*
import com.sinoyd.environmentsz.Kotlin.*
import com.sinoyd.frame.frgs.SinoBaseFragment
import com.sinoyd.frame.util.AppUtil.getApplicationContext
import kotlinx.android.synthetic.main.dbs_waterqualitytrend_fragment.*
import kotlinx.android.synthetic.main.layout_date_select.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import java.util.*

/**
 * Created by Sinoyd on 2017/12/18.
 */
class DBS_TabWaterqualitytrendFragment : SinoBaseFragment(), DateSelectDialog.DateSelectListener {
    override fun selectDate(startDate: String, endDate: String) {
        tv_date_startTime.text = startDate
        tv_date_endTime.text = endDate
        beginTime = startDate!!
        endTime = endDate!!
        sendtrend()
    }

    var date: Date = Date()
    var beginTime: String = date.getlastweekToday()
    var endTime: String = date.getToday()

    //请求参数
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var nearbyImpl: NearbyImpl = NearbyImpl()
    var nearby: Nearby = com.sinoyd.Code.DataClass.Nearby()

    var trendImpl: TrendImpl = TrendImpl()
    var trend: Trend = Trend()

    var stationeid: Int = 0

    //定位
    var mLocationClient: LocationClient? = null

    val REQUESTCODE1 = 1


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout(R.layout.dbs_waterqualitytrend_fragment)
        nbBar.hide()
        setView()
        Getpermission.requestPermission(activity)
        setlisteners()
        requestLocation()
    }

    override fun onResume() {
        super.onResume()

    }


    override fun setView() {
        super.setView()

        titlename.text = "水质趋势"
        iv_left.visibility = View.INVISIBLE
        iv_left2.setImageResource(R.drawable.icon_et_more)
        iv_left2.visibility = View.INVISIBLE
        iv_right2.visibility = View.VISIBLE
        iv_right2.setImageResource(R.drawable.icon_location)
        rb_near7day.isChecked = true
        layoutdate.visibility = View.GONE
        tv_date_startTime.text = date.getDateofYesterday()
        tv_date_endTime.text = date.getToday()
        rb_zhuzhuangtu.isChecked = true
        barchart_waterquality.visibility = View.VISIBLE
        lineChart_waterquality.visibility = View.GONE
    }

    private fun setlisteners() {
        //日期选择
        rb_date.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb_near7day -> {
                    layoutdate.visibility = View.GONE
                    beginTime = date.getlastweekToday()
                    endTime = date.getToday()
                    sendtrend()
                }
                R.id.rb_near30day -> {
                    layoutdate.visibility = View.GONE
                    beginTime = date.getlastmonthToday()
                    endTime = date.getToday()
                    sendtrend()
                }
                R.id.rb_near_freestyle -> {
                    layoutdate.visibility = View.VISIBLE
                    beginTime = tv_date_startTime.text.toString()
                    endTime = tv_date_endTime.text.toString()
                    sendtrend()
                }

            }

        }
        //图标类型选择
        rg_tubiao.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb_quxiantu -> {
                    lineChart_waterquality.visibility = View.VISIBLE
                    barchart_waterquality.visibility = View.GONE
//                    toast("曲线图")
                }
                R.id.rb_zhuzhuangtu -> {
                    barchart_waterquality.visibility = View.VISIBLE
                    lineChart_waterquality.visibility = View.GONE
//                    toast("柱状图")
                }

            }
        }
        //日期箭头监听事件
        iv_date_arrowLeft.onClick { tv_date_startTime.performClick() }
        tv_date_startTime.onClick {
            tv_date_startTime.text = date.getSpecifiedDayBefore(tv_date_startTime.text.toString())
            beginTime = tv_date_startTime.text.toString()
            sendtrend()
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
            sendtrend()
        }

        //下拉刷新
        swipeRefreshLayout_waterquality.setOnRefreshListener {
            swipeRefreshLayout_waterquality.isRefreshing = false
            requestLocation()
        }


        //选择站点
        iv_right2.onClick {
            var intent = Intent(activity, DBS_PointChooseActivity::class.java)
            activity.startActivityFromFragment(this, intent, REQUESTCODE1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            REQUESTCODE1 -> {
                stationeid = data!!.getIntExtra("stationid", 0)
                titlename.text = Html.fromHtml("<font color='white'>${data!!.getStringExtra("stationname")}</font><font color='white' size='5'></font>")
                Log.i("scj", "拆包后的数据如下：")
                Log.i("scj", "stationname:    ${data!!.getStringExtra("stationname")}")
                Log.i("scj", "stationid:      ${stationeid}")
                sendtrend()
            }

        }
    }

    //定位当前位置
    inner class MyLocationListener : BDLocationListener {

        override fun onReceiveLocation(location: BDLocation) {
            Log.i("scj", "当前位置${location.latitude}")
            Log.i("scj", "当前位置${location.longitude}")

            if (location.locType == BDLocation.TypeGpsLocation || location.locType == BDLocation.TypeNetWorkLocation) {
                if (mLocationClient!!.isStarted) {
                    // 获得位置之后停止定位
                    mLocationClient!!.stop()
                    showdialog(activity, "loadsuccess")
                }
            }
            //获取附近站点
            sendNearby(location.latitude, location.longitude)
        }
    }

    //开始定位自己
    private fun requestLocation() {
        mLocationClient = LocationClient(getApplicationContext())
        mLocationClient!!.registerLocationListener(MyLocationListener())
        //设置定位的一些设置
        val option = LocationClientOption()
        option.setScanSpan(5000)//表示每5秒更新一下当前位置
        option.setIsNeedAddress(true)
        option.locationMode = LocationClientOption.LocationMode.Hight_Accuracy
        // Hight_Accuracy表示高精确度模式，会在GPS信号正常的情况下优先使用GPS定位，在无法接收GPS信号的时候使用网络定位。
        // Battery_Saving表示节电模式，只会使用网络进行定位。
        // Device_Sensors表示传感器模式，只会使用GPS进行定位。
        mLocationClient!!.locOption = option
        //启动定位
        Log.i("scj", "开始定位自己")
        mLocationClient!!.start()
        showdialog(activity, "loadshow")
    }

    //发送请求 获取最近的站点位置
    fun sendNearby(latitude: Double, longitude: Double) {
        showdialog(activity, "loadshow")
        nearbyImpl.getnearby(SharedPreferencesFactory.getdata(activity, "loginId"), latitude, longitude, "nearbyImpl", this)
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(activity, "loadsuccess")
        when (TAG) {
            "nearbyImpl" -> {
                try {
                    nearby = gson.fromJson(resData, Nearby::class.java)
                    if (nearby.status == "1") {
                        toast("${nearby.message}")
                    } else {
                        if (nearby.data.isNotEmpty()) {
                            titlename.text = Html.fromHtml("<font color='white'>${nearby.data[0].name}</font><font color='white' size='5'></font>")
                            stationeid = nearby.data[0].id
                        } else {
                            toast("未获取到信息")
                        }
                    }
                } catch (e: Exception) {
                    toast("nearby_JSON解析失败")
                    return
                }
                //发请求
                sendtrend()
            }
            "trendImpl" -> {
                try {
                    trend = gson.fromJson(resData, Trend::class.java)
                } catch (e: Exception) {
                    toast("trend_JSON解析失败")
                    return
                }
                //重组数据
                var datalist: java.util.ArrayList<GVkeyvaluesimple> = java.util.ArrayList()
                for (item in trend.data) {
                    var kv = when (item.grade) {
                        "I" -> 1
                        "II" -> 2
                        "III" -> 3
                        "IV" -> 4
                        "V" -> 5
                        "劣V" -> 6
                        else -> 0
                    }
                    datalist.add(GVkeyvaluesimple("", item.time, kv.toString()))
                }
                showmiddle(activity, barchart_waterquality, datalist)
                showlinechart(activity, lineChart_waterquality, datalist)
            }
        }
    }

    override fun requestFailed(resData: String) {
        super.requestFailed(resData)
        showdialog(activity, "loadfail")
    }

    override fun onDestroy() {
        super.onDestroy()
        showdialog(activity, "loaddismiss")
    }

    //发送请求 站点水质趋势
    fun sendtrend() {
        showdialog(activity, "loadshow")
        trendImpl.getTrend(beginTime, endTime, stationeid.toString(), "trendImpl", this)
    }


}
package com.sinoyd.Code.Control

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.sinoyd.Code.Activity.DBS_Detailsofclass2Activity
import com.sinoyd.Code.Adapter.GisAdapter
import com.sinoyd.Code.DataClass.Gis
import com.sinoyd.R
import kotlinx.android.synthetic.main.popup_layout.view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor

/**
 * 作者： scj
 * 创建时间： 2018/2/12
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Control
 */

fun showgismap(activity: Activity, bmapView: MapView, data: MutableList<Gis.DataBean>) {
    //1初始化百度地图设置
    // 获取地图控件引用
    // 获取BaiduMap实例
    var mbaiduMap = bmapView.map
    val msu = MapStatusUpdateFactory.zoomTo(5f)
    mbaiduMap.setMapStatus(msu)
    // 删除百度地图logo
    bmapView.removeViewAt(1)
    // 开启定位图层
    mbaiduMap.isMyLocationEnabled = true
    bmapView.showZoomControls(false)
    //2 显示地图上覆盖物
    var stationname: String = ""
    var grade: String = ""
    var id: Int = 0
    var llLatLng = LatLng(0.0, 0.0)
    //绘制每个覆盖物
    for (item in data) {
        stationname = item.name
        grade = item.grade
        id = item.id
        llLatLng = LatLng(item.latitude, item.longitude)

        //自定义覆盖物
        var ooa: OverlayOptions? = null
        val v_temp = LayoutInflater.from(activity.applicationContext).inflate(R.layout.marker_layout, null)
        val tv_temp = v_temp.findViewById(R.id.baidumap_custom_text) as TextView//获取自定义布局中的textview  baidumap_custom_img_bottom
        val tv_temp_bottom = v_temp.findViewById(R.id.baidumap_custom_tv_bottom) as TextView//获取自定义布局中的textview  baidumap_custom_img_bottom
        val img_temp = v_temp.findViewById(R.id.baidumap_custom_img) as ImageView//获取自定义布局中的imageview

        tv_temp.text = grade
        tv_temp_bottom.text = stationname
        when (grade) {
            "I" -> img_temp.setImageResource(R.drawable.water1)
            "II" -> img_temp.setImageResource(R.drawable.water1)
            "III" -> img_temp.setImageResource(R.drawable.water2)
            "IV" -> img_temp.setImageResource(R.drawable.water3)
            "V" -> img_temp.setImageResource(R.drawable.water4)
            "劣V" -> img_temp.setImageResource(R.drawable.water5)
            else -> {
                img_temp.setImageResource(R.drawable.water5)
                img_temp.setColorFilter(Color.GRAY)
            }
        }

        val bd_temp = BitmapDescriptorFactory.fromView(v_temp)
        ooa = MarkerOptions().position(llLatLng).icon(bd_temp).zIndex(9).draggable(false)
        val cMarker = bmapView.map.addOverlay(ooa) as Marker
        val cBundle = Bundle()
        cBundle.putString("stationname", stationname)
        cBundle.putString("grade", grade)
        cBundle.putString("id", id.toString())

        cMarker.extraInfo = cBundle
    }
    //覆盖物的单机事件
    bmapView.map.setOnMarkerClickListener { marker ->
        if (marker != null) {
            val ll = marker.position
            val a: String = try {
                marker.extraInfo.getString("stationname")
            } catch (e: Exception) {
                "--"
            }
            val b: String = try {
                marker.extraInfo.getString("grade")
            } catch (e: Exception) {
                "--"
            }
            val c: String = try {
                marker.extraInfo.getString("id")
            } catch (e: Exception) {
                "--"
            }
            val bubbleLayout = LayoutInflater.from(activity).inflate(R.layout.popup_layout, bmapView, false) as LinearLayout

            bubbleLayout.popup_tv_name.text = a
            bubbleLayout.popup_tv_watername.text = when (b) {
                "I" -> Html.fromHtml("<font color='#BADEEE'>水质等级：</font><font color='#4EFFFE'>$b</font>")
                "II" -> Html.fromHtml("<font color='#BADEEE'>水质等级：</font><font color='#4EFFFE'>$b</font>")
                "III" -> Html.fromHtml("<font color='#BADEEE'>水质等级：</font><font color='#4BFF00'>$b</font>")
                "IV" -> Html.fromHtml("<font color='#BADEEE'>水质等级：</font><font color='#FDFF04'>$b</font>")
                "V" -> Html.fromHtml("<font color='#BADEEE'>水质等级：</font><font color='#F67A13'>$b</font>")
                "劣V" -> Html.fromHtml("<font color='#BADEEE'>水质等级：</font><font color='#F30115'>$b</font>")
                else -> Html.fromHtml("<font color='#BADEEE'>水质等级：</font><font color='GRAY'>$b</font>")
            }

            bubbleLayout.popup_tv_yl.text = "因子"
            bubbleLayout.popup_tv_PrimaryPollutant.text = "因子"
            bubbleLayout.popup_tv_time.text = "详细信息>>"
            bubbleLayout.popup_tv_time.textColor = Color.rgb(234, 223, 48)
            bubbleLayout.popup_tv_time.onClick {
                activity.startActivity<DBS_Detailsofclass2Activity>(
                        "id" to c,
                        "name" to a
                )
            }
            val mInfoWindow = InfoWindow(bubbleLayout,
                    ll, -70)
            bmapView.map.showInfoWindow(mInfoWindow)
        }
        true
    }

    //平移百度地图
    moveBaidumap(llLatLng, bmapView)
}

fun showlist(activity: Activity, listview: ListView, data: MutableList<Gis.DataBean>, bmapView: MapView) {
    var adapter = GisAdapter(activity, data)
    listview.adapter = adapter
    listview.setOnItemClickListener { adapterView, view, i, l ->
        moveBaidumap(LatLng(data[i].latitude, data[i].longitude), bmapView)
    }
}

fun moveBaidumap(llLatLng: LatLng, mBaiduMap: MapView) {
    val mMapStatus = MapStatus.Builder()
            .target(llLatLng)
            .zoom(12f)
            .build()
    //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
    val mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus)
    //改变地图状态
    mBaiduMap.map.setMapStatus(mMapStatusUpdate)
}
package com.sinoyd.Code.Until

import android.content.Context
import android.util.DisplayMetrics
import android.widget.FrameLayout
import android.widget.GridView
import android.widget.LinearLayout
import com.sinoyd.Code.Adapter.LiftAdapter
import com.sinoyd.Code.Adapter.MiddleAdapter
import com.sinoyd.Code.DataClass.GVkeytime
import com.sinoyd.Code.DataClass.GVkeyvalue
import com.sinoyd.Code.View.SyncScrollView
import org.jetbrains.anko.windowManager
import java.util.*

/**
 * 作者： scj
 * 创建时间： 2018/1/12
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Until
 */

fun showgvkeyvalue(context: Context, lift: GridView, right: GridView, middel: GridView, list: List<GVkeyvalue>, syncScrollViewlift: SyncScrollView, syncScrollViewright: SyncScrollView) {
    //计算 行的数据
    var linevalue = ArrayList<GVkeytime>()
    linevalue = getlinevalue(list)

    //计算 列的数据
    var columnkey = ArrayList<GVkeytime>()
    columnkey = getcolumnvalue(list)

    //计算 中间的值
    var listkv = ArrayList<GVkeyvalue>()
    listkv = getkv(list, linevalue, columnkey)

    //设置right gridview的布局大小 并显示数据
    setrightgridview(context, linevalue, right)

    //设置lift gridview的布局大小 并显示数据
    setliftgridview(context, columnkey, lift)

    //设置middle gridview的布局大小  并显示数据
    setmiddlegridview(context, listkv, middel, linevalue)

    //lift 与 middle gridview 上下滑动监听绑定
    syncScrollViewlift.setScrollView(syncScrollViewright)
    syncScrollViewright.setScrollView(syncScrollViewlift)
}

/***
 * gridview 行数据显示 middle
 **/
fun setmiddlegridview(context: Context, listkv: ArrayList<GVkeyvalue>, middel: GridView, linevalue: ArrayList<GVkeytime>) {

    var size = linevalue.size
    val dm = DisplayMetrics()
    context.windowManager.defaultDisplay.getMetrics(dm)
    val density = dm.density
    val allWidth = (80 * size.toFloat() * density).toInt()   //80 是每一个item的宽度  转换成px
    val itemWidth = (80 * density).toInt()
    val params = FrameLayout.LayoutParams(allWidth, FrameLayout.LayoutParams.WRAP_CONTENT)

    middel.layoutParams = params// 设置GirdView布局参数
    middel.columnWidth = itemWidth// 列表项宽
//  middel.horizontalSpacing = 10// 列表项水平间距
    middel.stretchMode = GridView.NO_STRETCH
    middel.numColumns = size

    var madapter = MiddleAdapter(listkv, context)
    middel.adapter = madapter


}

/***
 * gridview 行数据显示 right
 **/
fun setrightgridview(context: Context, list: ArrayList<GVkeytime>, right: GridView) {

    var size = list.size
    val dm = DisplayMetrics()
    context.windowManager.defaultDisplay.getMetrics(dm)
    val density = dm.density
    val allWidth = (80 * size.toFloat() * density).toInt()   //80 是每一个item的宽度
    val itemWidth = (80 * density).toInt()
    val params = LinearLayout.LayoutParams(allWidth, LinearLayout.LayoutParams.WRAP_CONTENT)

    right.layoutParams = params// 设置GirdView布局参数
    right.columnWidth = itemWidth// 列表项宽
//  right.horizontalSpacing = 10// 列表项水平间距
    right.stretchMode = GridView.NO_STRETCH
    right.numColumns = size

    var ladapter = LiftAdapter(list, context)
    right.adapter = ladapter

}


/***
 * gridview 列数据显示 lift
 **/
fun setliftgridview(context: Context, list: ArrayList<GVkeytime>, lift: GridView) {
    var ladapter = LiftAdapter(list, context)
    lift.adapter = ladapter
}


//计算 行的数据
fun getlinevalue(list: List<GVkeyvalue>): ArrayList<GVkeytime> {
    var result = ArrayList<GVkeytime>()
    list.mapTo(result) { GVkeytime(it.valueunit.keyx, it.valueunit.timey) }
    var restltto = ArrayList<GVkeytime>()
    for (item in result) {
        if (!restltto.contains(item)) {
            restltto.add(item)
        }
    }
//    result.mapTo(restltto) { it }
    return restltto
}

//计算 列的数据
fun getcolumnvalue(list: List<GVkeyvalue>): ArrayList<GVkeytime> {
    var result = ArrayList<GVkeytime>()
    list.mapTo(result) { GVkeytime(it.keytime.keyx, it.keytime.timey) }
    var restltto = ArrayList<GVkeytime>()
    for (item in result) {
        if (!restltto.contains(item)) {
            restltto.add(item)
        }
    }
//    result.mapTo(restltto) { it }
    return restltto
}

//计算 中间的值
fun getkv(list: List<GVkeyvalue>, linevalue: ArrayList<GVkeytime>, columnkey: ArrayList<GVkeytime>): ArrayList<GVkeyvalue> {
    var result = ArrayList<GVkeyvalue>()
    for (itemx in columnkey) {
        for (itemy in linevalue) {
            var gVkeyvalue: GVkeyvalue = GVkeyvalue(GVkeytime("", ""), GVkeytime("", ""), "")
            gVkeyvalue.keytime.keyx = itemx.keyx
            gVkeyvalue.keytime.timey = itemx.timey
            gVkeyvalue.valueunit.keyx = itemy.keyx
            gVkeyvalue.valueunit.timey = itemy.timey
            gVkeyvalue.kv = "--"
            for (itemz in list) {
                if (itemx == itemz.keytime && itemy == itemz.valueunit) {
                    if (itemz.kv != "") {
                        gVkeyvalue.kv = itemz.kv
                    } else {
                        gVkeyvalue.kv = "--"
                    }
                }
            }
            result.add(gVkeyvalue)
        }
    }
    return result
}
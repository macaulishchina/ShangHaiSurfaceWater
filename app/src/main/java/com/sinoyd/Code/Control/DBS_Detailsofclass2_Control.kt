package com.sinoyd.Code.Control

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.sinoyd.Code.DataClass.Concentration
import com.sinoyd.Code.DataClass.StatisticsByPointId
import com.sinoyd.Code.DataClass.GVkeyvaluesimple
import com.sinoyd.Code.MpChart.*
import com.sinoyd.R
import java.util.ArrayList

/**
 * 作者： scj
 * 创建时间： 2018/2/5
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Control
 */

//显示最上面的UI
fun showup(tv_timestamp: TextView, iv_grade: ImageView, tv_targetGrade: TextView, tv_qualifiedStatus: TextView, tv_primaryPollute: TextView, tv_primaryPolluteValue: TextView, tv_polluteValue: TextView, data: StatisticsByPointId.DataBean) {
    //数据时间
    tv_timestamp.text = data.timestamp
    //当前水质类别
    iv_grade.visibility = View.VISIBLE
    when (data.targetGrade) {
        "I" -> iv_grade.setImageResource(R.drawable.icon_one)
        "II" -> iv_grade.setImageResource(R.drawable.icon_two)
        "III" -> iv_grade.setImageResource(R.drawable.icon_three)
        "IV" -> iv_grade.setImageResource(R.drawable.icon_four)
        "V" -> iv_grade.setImageResource(R.drawable.icon_five)
        "劣V" -> iv_grade.setImageResource(R.drawable.icon_five)
        else -> {
            iv_grade.visibility = View.INVISIBLE
        }
    }
    //目标水质类别
    tv_targetGrade.text = data.targetGrade
    //水质达标情况
    tv_qualifiedStatus.text = data.qualifiedStatus
    //首要污染物
    tv_primaryPollute.text = data.primaryPollute
    //首要污染物浓度值
    tv_primaryPolluteValue.text = data.primaryPolluteValue
    //综合污染指数
    tv_polluteValue.text = data.polluteValue
}

//显示中间的UI  [柱状图]
fun showmiddle(activity: Activity, mChart: com.github.mikephil.charting.charts.BarChart, datalist: ArrayList<GVkeyvaluesimple>) {

    if (datalist.size == 0) {
        mChart.setNoDataText("No Data")
        return
    }
    //缩放比例
//    var scale = ((mChart.width) / 8) / ((mChart.width) / (datalist.size)).toFloat()
//    mChart.zoom(scale, mChart.scaleY, 0f, 0f)
    mChart.zoomOut()
    mChart.setFitBars(true)
    mChart.isScaleXEnabled = true

    mChart.setDrawBarShadow(false)
    mChart.setDrawValueAboveBar(true)

    mChart.description.isEnabled = false

    mChart.isDragYEnabled = false
    mChart.isDragXEnabled = true
    mChart.isDragEnabled = false

    //设置chart是否可以触摸
    mChart.setTouchEnabled(true)
    //设置是否可以拖拽
    mChart.isDragEnabled = true
    //设置是否可以缩放 x和y，默认true
    mChart.setScaleEnabled(false)
    //是否缩放X轴
    mChart.isScaleXEnabled = false
    //是否缩放Y轴
    mChart.isScaleYEnabled = false


    // if more than 60 entries are displayed in the chart, no values will be
    // drawn
    mChart.setMaxVisibleValueCount(60)

    // scaling can now only be done on x- and y-axis separately
    mChart.setPinchZoom(false)

    mChart.setDrawGridBackground(false)
    // mChart.setDrawYLabels(false);

    mChart.isDoubleTapToZoomEnabled = false//双击缩放

    val xAxis = mChart.xAxis
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.setDrawGridLines(false)
    xAxis.isGranularityEnabled = true//是否显示间隔 ，x轴上
    xAxis.granularity = 1f
    xAxis.labelCount = 7
    xAxis.axisLineColor = Color.WHITE
    xAxis.labelRotationAngle = -20f//设置x轴字体显示角度
    xAxis.setValueFormatter { value, axis ->
        try {
            if (datalist[value.toInt()].value.contains("-")) {
                datalist[value.toInt()].value.substring(5, 10)
            } else {
                datalist[value.toInt()].value
            }

        } catch (e: Exception) {
            ""
        }
    }
    xAxis.textColor = Color.WHITE


    val leftAxis = mChart.axisLeft
    leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
    leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
    leftAxis.axisMaximum = 6f
    leftAxis.setDrawGridLines(false)
    leftAxis.granularity = 1f
    leftAxis.setValueFormatter { value, axis ->
        when (value) {
            0f -> "I"
            1f -> "II"
            2f -> "III"
            3f -> "IV"
            4f -> "V"
            5f -> "劣V"
            else -> ""
        }
    }
    leftAxis.textSize = 5f
    leftAxis.textColor = Color.WHITE



    mChart.rendererLeftYAxis = WQIYAxisRendererbar(mChart)
    mChart.axisRight.isEnabled = false
    mChart.legend.isEnabled = false


    setDatabar(mChart, datalist)
    mChart.setVisibleXRangeMaximum(8f)//需要在设置数据源后生效 一个界面最多多少个数据。
//        mChart.setVisibleXRangeMinimum(7f)//设置最少数量，不常用。
    mChart.animateX(200)


}


private fun setDatabar(mChart: BarChart, datalist: ArrayList<GVkeyvaluesimple>) {
    val yVals1 = ArrayList<BarEntry>()
    var colorlist = ArrayList<Int>()
    for ((i, item) in datalist.withIndex()) {
        yVals1.add(BarEntry(i.toFloat(), item.kv.toFloat()))
        when (item.kv) {
            "1" -> {
                colorlist.add(colorsbar[0])
            }
            "2" -> {
                colorlist.add(colorsbar[1])
            }
            "3" -> {
                colorlist.add(colorsbar[2])
            }
            "4" -> {
                colorlist.add(colorsbar[3])
            }
            "5" -> {
                colorlist.add(colorsbar[4])
            }
            "6" -> {
                colorlist.add(colorsbar[5])
            }
            else -> {
                colorlist.add(colorsbar[0])
            }
        }
    }
    val set1: BarDataSet
    set1 = BarDataSet(yVals1, "")
    set1.values = yVals1
    set1.setDrawIcons(false)
    set1.colors = colorlist
    set1.setDrawValues(false)
    val dataSets = ArrayList<IBarDataSet>()
    dataSets.add(set1)

    val data = BarData(dataSets)
    data.setValueTextSize(10f)
    data.barWidth = 0.9f
    mChart.data = data

}

//显示最下面的UI  【折线图】
fun showdown(activity: Activity, mChart: LineChart, data: List<Concentration.DataBean>) {

    if (data.size == 0) {
        mChart.setNoDataText("No Data")
        return
    }


    var datasource: ArrayList<GVkeyvaluesimple> = ArrayList()
    for (item in data) {
        var gvkv = GVkeyvaluesimple("", "", "")
        gvkv.key = item.time
        gvkv.value = item.value
        datasource.add(gvkv)
    }



    mChart.setDrawGridBackground(false)

    // no description text
    mChart.description.isEnabled = false

    // enable touch gestures
    mChart.setTouchEnabled(true)

    // enable scaling and dragging
    mChart.setScaleEnabled(true)


    // if disabled, scaling can be done on x- and y-axis separately
    mChart.setPinchZoom(false)


    val xAxis = mChart.xAxis
    xAxis.setDrawGridLines(false)
    xAxis.setDrawAxisLine(true)
    xAxis.granularity = 1f
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.setValueFormatter { value, axis ->
        datasource[value.toInt()].key.substring(5, datasource[value.toInt()].key.length - 3)
    }


    val leftAxis = mChart.axisLeft

    leftAxis.setDrawGridLines(false)
    leftAxis.isGranularityEnabled = false

    // limit lines are drawn behind data (and not on top)
    leftAxis.setDrawLimitLinesBehindData(false)

    mChart.axisRight.isEnabled = false


    // add data
    setDataline(mChart, datasource)

    mChart.legend.isEnabled = false


    mChart.animateX(200)
    //mChart.invalidate();

    // get the legend (only possible after setting data)
    val l = mChart.legend

    // modify the legend ...
    l.form = Legend.LegendForm.LINE

    // // dont forget to refresh the drawing
}

fun setDataline(mChart: LineChart, data: ArrayList<GVkeyvaluesimple>) {
    val values = java.util.ArrayList<Entry>()

    for ((i, item) in data.withIndex()) {
        values.add(Entry(i.toFloat(), item.value!!.toFloat()))
    }

    val set1: LineDataSet
    set1 = LineDataSet(values, "")
    set1.color = R.color.black
    set1.setCircleColorHole(Color.WHITE)
    set1.setCircleColor(Color.BLACK)
    set1.valueFormatter = IValueFormatter { value, entry, dataSetIndex, viewPortHandler ->

        values[value.toInt()].y.toString()
    }
    val dataSets = java.util.ArrayList<ILineDataSet>()
    dataSets.add(set1) // add the datasets
    val data = LineData(dataSets)
    mChart.data = data
    mChart.notifyDataSetChanged()
}


//显示折线图
fun showlinechart(activity: Activity, mChart: com.github.mikephil.charting.charts.LineChart, data: ArrayList<GVkeyvaluesimple>) {

    if (data.size == 0) {
        mChart.setNoDataText("No Data")
        return
    }


    var datasource: ArrayList<GVkeyvaluesimple> = ArrayList()
    for (item in data) {
        var gvkv = GVkeyvaluesimple("", "", "")
        gvkv.key = item.value
        gvkv.value = item.kv

        datasource.add(gvkv)
    }



    mChart.setDrawGridBackground(false)

    // no description text
    mChart.description.isEnabled = false

    // enable touch gestures
    mChart.setTouchEnabled(true)

    // enable scaling and dragging
//    mChart.isDragEnabled = true
    mChart.setScaleEnabled(true)


    // if disabled, scaling can be done on x- and y-axis separately
    mChart.setPinchZoom(false)


    //设置chart是否可以触摸
    mChart.setTouchEnabled(true)
    //设置是否可以拖拽
    mChart.isDragEnabled = true
    //设置是否可以缩放 x和y，默认true
    mChart.setScaleEnabled(false)
    //是否缩放X轴
    mChart.isScaleXEnabled = false
    //是否缩放Y轴
    mChart.isScaleYEnabled = false

    mChart.isDoubleTapToZoomEnabled = false;//双击缩放

    val xAxis = mChart.xAxis

    xAxis.setDrawGridLines(false)
    xAxis.setDrawAxisLine(true)
    xAxis.isGranularityEnabled = true//是否显示间隔 ，x轴上
    xAxis.granularity = 1f
    xAxis.labelCount = 7
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.labelRotationAngle = -20f//设置x轴字体显示角度
    xAxis.setValueFormatter { value, axis ->
        try {
            datasource[value.toInt()].key.substring(5, datasource[value.toInt()].key.length)
        } catch (e: Exception) {
            ""
        }
    }
    xAxis.textColor = Color.WHITE
    xAxis.axisLineColor = Color.WHITE

    val leftAxis = mChart.axisLeft
    leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
    leftAxis.axisMaximum = 6f
    leftAxis.granularity = 1f
    leftAxis.setDrawGridLines(false)
    leftAxis.isGranularityEnabled = false
    leftAxis.setValueFormatter { value, axis ->
        when (value) {
            0f -> "I"
            1f -> "II"
            2f -> "III"
            3f -> "IV"
            4f -> "V"
            5f -> "劣V"
            else -> ""
        }
    }

    leftAxis.textSize = 5f
    leftAxis.textColor = Color.WHITE


    // limit lines are drawn behind data (and not on top)
    leftAxis.setDrawLimitLinesBehindData(false)

    mChart.axisRight.isEnabled = false


    // add data
    setlineData(mChart, datasource)
    mChart.setVisibleXRangeMaximum(8f)//需要在设置数据源后生效 一个界面最多多少个数据。
//        mChart.setVisibleXRangeMinimum(7f)//设置最少数量，不常用。
    mChart.legend.isEnabled = false
    mChart.rendererLeftYAxis = WQIYAxisRendererline(mChart)

    mChart.animateX(200)
    //mChart.invalidate();

    // get the legend (only possible after setting data)
    val l = mChart.legend

    // modify the legend ...
    l.form = Legend.LegendForm.LINE

    // // dont forget to refresh the drawing


}

fun setlineData(mChart: LineChart, datasource: ArrayList<GVkeyvaluesimple>) {

    val values = java.util.ArrayList<Entry>()
    var colorlist = ArrayList<Int>()
    for ((i, item) in datasource.withIndex()) {
        values.add(Entry(i.toFloat(), item.value!!.toFloat()))
        when (item.value) {
            "1" -> {
                colorlist.add(colorsbar[0])
            }
            "2" -> {
                colorlist.add(colorsbar[1])
            }
            "3" -> {
                colorlist.add(colorsbar[2])
            }
            "4" -> {
                colorlist.add(colorsbar[3])
            }
            "5" -> {
                colorlist.add(colorsbar[4])
            }
            "6" -> {
                colorlist.add(colorsbar[5])
            }
            else -> {
                colorlist.add(colorsbar[0])
            }
        }
    }

    val set1: LineDataSet
    set1 = LineDataSet(values, "")
    set1.color = R.color.black
    set1.setCircleColorHole(Color.WHITE)
//    set1.setCircleColor(Color.BLACK)
    set1.circleColors = colorlist
    set1.valueFormatter = IValueFormatter { value, entry, dataSetIndex, viewPortHandler ->
        //        values[value.toInt()].y.toString()
        ""
    }

    val dataSets = java.util.ArrayList<ILineDataSet>()
    dataSets.add(set1) // add the datasets
    val data = LineData(dataSets)
    mChart.data = data
    mChart.notifyDataSetChanged()
}


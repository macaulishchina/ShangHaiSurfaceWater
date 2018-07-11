package com.sinoyd.Code.MpChart

import android.graphics.Canvas
import android.util.Log
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.renderer.YAxisRenderer

/**
 * 作者： scj
 * 创建时间： 2018/2/8
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.MpChart
 */

interface ColorfulYAxisRendererDataSourceline {
    fun numberOfSegements(renderer: ColorfulYAxisRendererline): Int
    fun renderercolor(renderer: ColorfulYAxisRendererline, colorForSegementAt: Int): Int
    fun renderervalue(renderer: ColorfulYAxisRendererline, colorForSegementAt: Int): Float
}


open class ColorfulYAxisRendererline(chart: BarLineChartBase<LineData>, dataSource: ColorfulYAxisRendererDataSourceline?) : YAxisRenderer(chart.viewPortHandler, chart.axisLeft, chart.getTransformer(YAxis.AxisDependency.LEFT)) {

    var dataSource: ColorfulYAxisRendererDataSourceline? = null

    init {
        this.dataSource = dataSource
    }

    override fun renderAxisLine(canvas: Canvas?) {
        super.renderAxisLine(canvas)
        val yAxis = this.mYAxis
        if (!yAxis.isEnabled || !yAxis.isDrawAxisLineEnabled) {
            return
        }

        canvas!!.save()
        mAxisLinePaint.strokeWidth = 15f

        var colorcount = dataSource!!.numberOfSegements(this)

        var value = 0f

//        var length = (mViewPortHandler.contentBottom() + mViewPortHandler.contentTop()) / colorcount
//        var start = mViewPortHandler.contentTop()
//        var end = 0f

        if (mYAxis.axisDependency == YAxis.AxisDependency.LEFT) {
            for (index in 0 until colorcount) {
                var lastValue = dataSource!!.renderervalue(this, index)     //结束点
                var positions = arrayListOf<Float>()
                positions.add(0f)
                positions.add(value)                    //开始点
                positions.add(0f)
                positions.add(lastValue)                //结束点

                var postionpostion = positions.toFloatArray()
                transformer.pointValuesToPixel(postionpostion)
                var x = mViewPortHandler.contentLeft()
                val start = postionpostion[1]          //开始像素
                var end = postionpostion[3]            //结束像素

                //画线
                mAxisLinePaint.color = dataSource!!.renderercolor(this, index)
                canvas.drawLine(x, start, x, end, mAxisLinePaint)

//                Log.i("scj", "开始坐标    value:${value}    x:${x}    y:$start")
//
//                Log.i("scj", "结束坐标    lastvalue:${lastValue}   x:${x}    y:$end")
//
//                Log.i("scj", "颜色   $index")

                value = lastValue
            }

            canvas!!.restore()

        }
    }

}

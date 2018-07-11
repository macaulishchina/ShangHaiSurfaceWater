package com.sinoyd.Code.MpChart

import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart

/**
 * 作者： scj
 * 创建时间： 2018/2/8
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.MpChart
 */


var colorsbar = arrayListOf(
        Color.rgb(78, 255, 255),
        Color.rgb(78, 255, 255),
        Color.rgb(75, 255, 0),
        Color.rgb(255, 255, 0),
        Color.rgb(246, 122, 19),
        Color.rgb(243, 1, 21)
)

class WQIYAxisRendererbar(chart: BarChart) : ColorfulYAxisRendererbar(chart, dataSource = null), ColorfulYAxisRendererDataSourcebar {




    init {
        this.dataSource = this
    }

    override fun numberOfSegements(renderer: ColorfulYAxisRendererbar): Int = 6

    override fun renderercolor(renderer: ColorfulYAxisRendererbar, index: Int): Int {
        return colorsbar[index]
    }

    override fun renderervalue(renderer: ColorfulYAxisRendererbar, index: Int): Float {
        return (index + 1).toFloat()
    }


    public override fun getTransformedPositions(): FloatArray {
        val yAxis = this.mYAxis
        val transformer = this.transformer

        var positions = arrayListOf<Float>()
        positions.ensureCapacity(yAxis.mEntryCount)

        var entries = yAxis.mEntries

        for (index in 0 until yAxis.mEntryCount) {
            positions.add(0f)
            positions.add(entries[index] + 0.5f)
        }
        var positionspositions = positions.toFloatArray()
        transformer.pointValuesToPixel(positionspositions)

        return positionspositions

    }


}
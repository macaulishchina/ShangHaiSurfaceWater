package com.sinoyd.Code.Adapter

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sinoyd.Code.DataClass.AlarmInfo
import com.sinoyd.Code.DataClass.Gis
import com.sinoyd.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.backgroundResource

/**
 * 作者： scj
 * 创建时间： 2018/2/2
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 */

class GisAdapter(var context: Context, var list: List<Gis.DataBean>) : BaseAdapter<Gis.DataBean>(context, list) {

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {

        var view: View? = null
        var holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_gis_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        holder.item_name!!.text = list[p0].name
        holder.item_level!!.text = list[p0].grade

        holder.item_level!!.backgroundColor = when (list[p0].grade) {
            "I" -> Color.rgb(77, 253, 254)
            "II" -> Color.rgb(77, 253, 254)
            "III" -> Color.rgb(75, 255, 0)
            "IV" -> Color.rgb(255, 255, 17)
            "V" -> Color.rgb(244, 121, 33)
            "劣V" -> Color.rgb(240, 14, 34)
            else -> Color.GRAY

        }



        if (p0 % 2 == 0) {
            view!!.backgroundResource = R.color.itemcolor2
        } else {
            view!!.backgroundResource = R.color.itemcolor
        }
        return view!!

    }

    internal inner class ViewHolder(view: View) {
        var item_name: TextView? = null
        var item_level: TextView? = null


        init {
            item_name = view.findViewById(R.id.tv_name)
            item_level = view.findViewById(R.id.tv_level)
        }

    }

}
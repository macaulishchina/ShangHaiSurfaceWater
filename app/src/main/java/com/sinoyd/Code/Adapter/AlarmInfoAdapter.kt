package com.sinoyd.Code.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sinoyd.Code.DataClass.AlarmInfo
import com.sinoyd.R
import org.jetbrains.anko.backgroundResource

/**
 * 作者： scj
 * 创建时间： 2018/2/2
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 */

class AlarmInfoAdapter(var context: Context, var list: ArrayList<AlarmInfo.DataBean.ListBean>) : BaseAdapter<AlarmInfo.DataBean.ListBean>(context, list) {

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {

        var view: View? = null
        var holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_alarm_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        holder.item_tdianwei_alarm!!.text = list[p0].port
        holder.item_time_alarm!!.text = list[p0].time
        holder.item_type_alarm!!.text = list[p0].type
        holder.item_content_alarm!!.text = list[p0].content


        if (p0 % 2 == 0) {
            view!!.backgroundResource = R.color.itemcolor2
        } else {
            view!!.backgroundResource = R.color.itemcolor
        }
        return view!!

    }

    internal inner class ViewHolder(view: View) {
        var item_tdianwei_alarm: TextView? = null
        var item_time_alarm: TextView? = null
        var item_type_alarm: TextView? = null
        var item_content_alarm: TextView? = null


        init {
            item_tdianwei_alarm = view.findViewById(R.id.dianwei_alarm)
            item_time_alarm = view.findViewById(R.id.time_alarm)
            item_type_alarm = view.findViewById(R.id.type_alarm)
            item_content_alarm = view.findViewById(R.id.content_alarm)
        }

    }

}
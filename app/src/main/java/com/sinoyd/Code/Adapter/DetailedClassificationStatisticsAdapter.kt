package com.sinoyd.Code.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sinoyd.Code.DataClass.DetailedClassificationStatistics
import com.sinoyd.R
import org.jetbrains.anko.backgroundResource

/**
 * 作者： scj
 * 创建时间： 2018/2/2
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 */

class DetailedClassificationStatisticsAdapter(var context: Context, var list: MutableList<DetailedClassificationStatistics.DataBean.ListBean>) : BaseAdapter<DetailedClassificationStatistics.DataBean.ListBean>(context, list) {

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {

        var view: View? = null
        var holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_detail_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        holder.item_tv_zhanidanname!!.text = list[p0].name
        holder.item_tv_onlinelv!!.text = list[p0].onlineRate
        holder.item_tv_hegelv!!.text = list[p0].qualifiedRate
        holder.item_tv_buhuolv!!.text = list[p0].captureRate


        if (p0 % 2 == 0) {
            view!!.backgroundResource = R.color.itemcolor2
        } else {
            view!!.backgroundResource = R.color.itemcolor
        }
        return view!!

    }

    internal inner class ViewHolder(view: View) {
        var item_tv_zhanidanname: TextView? = null
        var item_tv_onlinelv: TextView? = null
        var item_tv_hegelv: TextView? = null
        var item_tv_buhuolv: TextView? = null


        init {
            item_tv_zhanidanname = view.findViewById<TextView>(R.id.zhandianname)
            item_tv_onlinelv = view.findViewById<TextView>(R.id.onlinelv)
            item_tv_hegelv = view.findViewById<TextView>(R.id.hegelv)
            item_tv_buhuolv = view.findViewById<TextView>(R.id.buhuolv)
        }

    }

}
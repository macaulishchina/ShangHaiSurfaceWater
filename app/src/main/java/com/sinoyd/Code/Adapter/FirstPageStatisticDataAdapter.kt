package com.sinoyd.Code.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.sinoyd.Code.DataClass.FirstPageStatisticData
import com.sinoyd.Code.Until.dp2px
import com.sinoyd.Code.Until.px2dp
import com.sinoyd.R
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.px2dip
import org.jetbrains.anko.windowManager
import java.text.NumberFormat

/**
 * 作者： scj
 * 创建时间： 2018/2/1
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 */

class FirstPageStatisticDataAdapter(var context: Context, var list: MutableList<FirstPageStatisticData.DataBean.ListBean>, var length: Int) : BaseAdapter<FirstPageStatisticData.DataBean.ListBean>(context, list) {
    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
        var view: View? = null
        var holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_main_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        var paramsonline = holder.item_rl_online!!.layoutParams
        var paramshege = holder.item_rl_hege!!.layoutParams
        var paramsbuhuo = holder.item_rl_buhuo!!.layoutParams

        holder.item_tv_name!!.text = list[p0].name

        val numberFormat = NumberFormat.getInstance()
        numberFormat.isGroupingUsed = false
        numberFormat.maximumFractionDigits = 1

        var onlinebili = numberFormat.format((list[p0].onlineCount).toFloat() / (list[p0].totalCount).toFloat() * 100)
        paramsonline.width = (length * (onlinebili.toFloat() / 100)).toInt()
        holder.item_rl_online!!.layoutParams = paramsonline
        holder.item_tv_online!!.text = "$onlinebili%(${list[p0].onlineCount}/${list[p0].totalCount})"

        var hegebili = numberFormat.format((list[p0].qualifiedCount).toFloat() / (list[p0].totalCount).toFloat() * 100)
        paramshege.width = (length * (hegebili.toFloat() / 100)).toInt()
        holder.item_rl_hege!!.layoutParams = paramshege
        holder.item_tv_hege!!.text = "$hegebili%(${list[p0].qualifiedCount}/${list[p0].totalCount})"

        var buhuobili = numberFormat.format((list[p0].captureCount).toFloat() / (list[p0].expectDataCount).toFloat() * 100)
        paramsbuhuo.width = (length * (buhuobili.toFloat() / 100)).toInt()
        holder.item_rl_buhuo!!.layoutParams = paramsbuhuo
        holder.item_tv_buhuo!!.text = "$buhuobili%(${list[p0].captureCount}/${list[p0].expectDataCount})"




        if (p0 % 2 == 0) {
            view!!.backgroundResource = R.color.itemcolor
        } else {
            view!!.backgroundResource = R.color.itemcolor2
        }
        return view!!
    }

    internal inner class ViewHolder(view: View) {
        var item_tv_name: TextView? = null
        var item_tv_online: TextView? = null
        var item_tv_hege: TextView? = null
        var item_tv_buhuo: TextView? = null
        var item_rl_hege: RelativeLayout? = null
        var item_rl_buhuo: RelativeLayout? = null
        var item_rl_online: RelativeLayout? = null

        init {
            item_tv_name = view.findViewById<TextView>(R.id.item_tv_name)
            item_tv_online = view.findViewById<TextView>(R.id.item_tv_online)
            item_tv_hege = view.findViewById<TextView>(R.id.item_tv_hege)
            item_tv_buhuo = view.findViewById<TextView>(R.id.item_tv_buhuo)
            item_rl_hege = view.findViewById<RelativeLayout>(R.id.item_rl_hege)
            item_rl_buhuo = view.findViewById<RelativeLayout>(R.id.item_rl_buhuo)
            item_rl_online = view.findViewById<RelativeLayout>(R.id.item_rl_online)
        }

    }

}
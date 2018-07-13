package com.sinoyd.Code.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sinoyd.Code.DataClass.CheckInfo
import com.sinoyd.Code.DataClass.TaskInfo
import com.sinoyd.R
import org.jetbrains.anko.backgroundResource

/**
 * 作者： hu
 * 创建时间： 2018/7/12
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 */

class CheckInfoAdapter(var context: Context, var list: ArrayList<CheckInfo.DataBean.Check>) : BaseAdapter<CheckInfo.DataBean.Check>(context, list) {

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {

        val view: View?
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_check_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        holder.item_tv_check_integrator.text = list[p0].InstrumentIntegrator
        holder.item_tv_check_sampRate.text = list[p0].SampRate
        holder.item_tv_check_compRate.text = list[p0].CompRate

        if (p0 % 2 == 0) {
            view!!.backgroundResource = R.color.itemcolor2
        } else {
            view!!.backgroundResource = R.color.itemcolor
        }
        return view

    }

    internal inner class ViewHolder(view: View) {
        var item_tv_check_integrator: TextView = view.findViewById(R.id.item_tv_check_integrator)
        var item_tv_check_sampRate: TextView = view.findViewById(R.id.item_tv_check_sampRate)
        var item_tv_check_compRate: TextView = view.findViewById(R.id.item_tv_check_compRate)

    }

}
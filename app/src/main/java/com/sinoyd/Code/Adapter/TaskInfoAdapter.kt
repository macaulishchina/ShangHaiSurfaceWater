package com.sinoyd.Code.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sinoyd.Code.DataClass.TaskInfo
import com.sinoyd.R
import org.jetbrains.anko.backgroundResource

/**
 * 作者： hu
 * 创建时间： 2018/7/12
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 */

class TaskInfoAdapter(var context: Context, var list: ArrayList<TaskInfo.DataBean.Task>) : BaseAdapter<TaskInfo.DataBean.Task>(context, list) {

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {

        val view: View?
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_task_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        holder.item_tv_task_point_name.text = list[p0].MonitoringPointName
        holder.item_tv_task_name.text = list[p0].TaskName
        holder.item_tv_task_datetime.text = list[p0].Time
        holder.item_tv_task_dealman.text = list[p0].DealMan
        holder.item_tv_task_state.text = list[p0].TaskStatus

        if (p0 % 2 == 0) {
            view!!.backgroundResource = R.color.itemcolor2
        } else {
            view!!.backgroundResource = R.color.itemcolor
        }
        return view

    }

    internal inner class ViewHolder(view: View) {
        var item_tv_task_point_name: TextView = view.findViewById(R.id.item_tv_task_point_name)
        var item_tv_task_name: TextView = view.findViewById(R.id.item_tv_task_name)
        var item_tv_task_datetime: TextView = view.findViewById(R.id.item_tv_task_datetime)
        var item_tv_task_dealman: TextView = view.findViewById(R.id.item_tv_task_dealman)
        var item_tv_task_state: TextView = view.findViewById(R.id.item_tv_task_state)

    }

}
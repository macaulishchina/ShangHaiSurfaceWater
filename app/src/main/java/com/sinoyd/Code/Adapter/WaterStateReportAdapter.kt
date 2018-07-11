package com.sinoyd.Code.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter
import com.sinoyd.Code.DataClass.Report
import com.sinoyd.Code.Fragment.DBS_TabWaterqualityweeklyFragment
import com.sinoyd.Code.Until.DownloadUtil
import com.sinoyd.R
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.onClick

/**
 * 作者： scj
 * 创建时间： 2018/2/2
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 */

class WaterStateReportAdapter(var context: Context, var list: ArrayList<Report>) : BaseAdapter() {
    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0L
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {

        var view: View? = null
        var holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_wsr_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        holder.item_tv_weeklistname!!.text = list[p0].name

        holder.item_tv_weeklistname!!.onClick {
            //DownloadUtil(context, list[p0].url).start()
            val testUrl = "http://192.168.11.178/WebUI/Files/upload/柘林实样比对18070612228.pdf"
            DownloadUtil(context,testUrl).start()
        }

        if (p0 % 2 == 0) {
            view!!.backgroundResource = R.color.itemcolor
        } else {
            view!!.backgroundResource = R.color.itemcolor2
        }
        return view!!

    }

    internal inner class ViewHolder(view: View) {
        var item_tv_weeklistname: TextView? = null


        init {
            item_tv_weeklistname = view.findViewById(R.id.tv_weeklistname)

        }

    }

}
package com.sinoyd.Code.Adapter

import android.content.Context
import android.opengl.Visibility
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter
import android.widget.Button
import com.macaulish.top.velvet.util.StorageKits
import com.sinoyd.Code.DataClass.Report
import com.sinoyd.Code.Fragment.DBS_TabWaterqualityweeklyFragment
import com.sinoyd.Code.Until.DownloadUtil
import com.sinoyd.R
import com.sinoyd.frame.util.IOHelp
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.onClick
import java.io.File

/**
 * 作者： scj
 * 创建时间： 2018/2/2
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 */

class WaterStateReportAdapter(var context: Context, var list: ArrayList<Report>) : BaseAdapter() {

    private val DOWNLOAD_DIR = StorageKits.getExternalPublicDir(Environment.DIRECTORY_DOCUMENTS).toString()+ File.separator+"yd"

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

//        if(list[p0].downloaded){
//            holder.item_report_bt_download.visibility = View.GONE
            holder.item_report_bt_check.visibility = View.VISIBLE
//        }else{
            holder.item_report_bt_download.visibility = View.VISIBLE
//            holder.item_report_bt_check.visibility = View.GONE
//        }

        holder.item_tv_weeklistname.text = list[p0].name

        holder.item_tv_weeklistname.onClick {
            list[p0].uri = IOHelp.download(context,list[p0].url,DOWNLOAD_DIR)
            list[p0].downloaded = true
            //DownloadUtil(context, list[p0].url).start()
        }
        holder.item_report_bt_check.onClick {
            IOHelp.openFileByUri(context,list[p0].uri)
        }

        if (p0 % 2 == 0) {
            view!!.backgroundResource = R.color.itemcolor
        } else {
            view!!.backgroundResource = R.color.itemcolor2
        }
        return view!!

    }

    internal inner class ViewHolder(view: View) {
        var item_tv_weeklistname :TextView = view.findViewById(R.id.tv_weeklistname)
        var item_report_bt_download : Button = view.findViewById(R.id.item_report_bt_download)
        var item_report_bt_check : Button =  view.findViewById(R.id.item_report_bt_check)
    }

}
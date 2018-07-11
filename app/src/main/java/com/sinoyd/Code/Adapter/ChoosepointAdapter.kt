package com.sinoyd.Code.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sinoyd.Code.Activity.DBS_PointChooseActivity
import com.sinoyd.Code.Control.Orderdata
import com.sinoyd.Code.Control.smoothScrollToPosition
import com.sinoyd.R
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.onClick

/**
 * 作者： scj
 * 创建时间： 2018/2/2
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 */

class ChoosepointAdapter(var context: Context, var list: ArrayList<Orderdata>) : BaseAdapter<Orderdata>(context, list) {


    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {

        var view: View? = null
        var holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_choosepoint_layout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }


        when (list[p0].level) {
            "1" -> {
                holder.item_point!!.text = list[p0].data.name
                holder.item_love!!.visibility = View.INVISIBLE
            }
            "2" -> {
                holder.item_point!!.text = "        " + list[p0].data.name
                holder.item_love!!.visibility = View.VISIBLE
                if (list[p0].favorite) {
                    holder.item_love!!.setImageResource(R.drawable.icon_love2)
                } else {
                    holder.item_love!!.setImageResource(R.drawable.icon_love1)
                }
            }
        }


        //背景色
        if (p0 % 2 == 0) {
            view!!.backgroundResource = R.color.itemcolor
        } else {
            view!!.backgroundResource = R.color.itemcolor2
        }


        holder.item_love!!.onClick {
            smoothScrollToPosition = p0
            //            context.toast("发送请求")
            if (list[p0].favorite) {
                (context as DBS_PointChooseActivity).sendfavorite(list[p0].data.id, "0", "")
            } else {
                (context as DBS_PointChooseActivity).sendfavorite(list[p0].data.id, "1", "")
            }
        }


        return view!!

    }

    internal inner class ViewHolder(view: View) {
        var item_point: TextView? = null
        var item_love: ImageView? = null


        init {
            item_point = view.findViewById(R.id.item_point)
            item_love = view.findViewById(R.id.item_love)

        }

    }

}
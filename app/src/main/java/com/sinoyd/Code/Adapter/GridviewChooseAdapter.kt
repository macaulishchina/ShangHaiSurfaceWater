package com.sinoyd.Code.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.sinoyd.Code.DataClass.Keyvalue
import com.sinoyd.R
import java.util.HashMap

/**
 * 作者： scj
 * 创建时间： 2018/1/11
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Adapter
 *
 * @param only true:单选   false：多选
 * @param datas 数据源
 */


class GridviewChooseAdapter(var datas: List<Keyvalue>, var context: Context, var only: Boolean) : BaseAdapter() {

    var isSelected: HashMap<Keyvalue, Boolean> = HashMap()
    //默认第一个被选择状态
    var selectorPosition: Int = 0

    var listdata: List<Keyvalue> = ArrayList()

    init {
        listdata = datas
        listdata[0].isselected = true
    }

    fun setIsSelected(isSelected: HashMap<Keyvalue, Boolean>) {
        this.isSelected = isSelected
    }

    fun getIsSelected(): HashMap<Keyvalue, Boolean> = isSelected


    //重置girdview状态
    fun setChoiceType(type: Boolean) {
        selectorPosition = 0
        for ((n, ite) in datas.withIndex()) {
            isSelected[ite] = type
        }
        notifyDataSetChanged()
    }


    override fun getView(p0: Int, convertView: View?, p2: ViewGroup?): View {
        var view: View
        var holder: ViewHolder?
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_gvchooselayout, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        //显示数据
        holder.cbtext!!.text = datas[p0].valuename
        holder.cbtext!!.tag = datas[p0].valuename

        if (only) {
            //单选状态
            holder.cbyes!!.visibility = GONE
            if (selectorPosition == p0) {
                isSelected[datas[p0]] = true
                holder.cbll!!.setBackgroundColor(Color.parseColor("#FF9224"))
            } else {
                isSelected[datas[p0]] = false
                holder.cbll!!.setBackgroundColor(Color.parseColor("#33000000"))
            }
        } else {
            //多选状态
            if (selectorPosition == p0) {
                when {
                    getIsSelected()[datas[p0]] == null -> {
                        isSelected[datas[p0]] = true
                        holder.cbyes!!.visibility = VISIBLE
                        holder.cbll!!.setBackgroundResource(R.drawable.gvchoooseboder)
                    }
                    getIsSelected()[datas[p0]] == false -> {
                        isSelected[datas[p0]] = true
                        holder.cbyes!!.visibility = VISIBLE
                        holder.cbll!!.setBackgroundResource(R.drawable.gvchoooseboder)
                    }
                    getIsSelected()[datas[p0]] == true -> {
                        isSelected[datas[p0]] = false
                        holder.cbyes!!.visibility = GONE
                        holder.cbll!!.setBackgroundColor(Color.parseColor("#33000000"))
                    }
                }
            } else {
                when {
                    getIsSelected()[datas[p0]] == null -> {
                        isSelected[datas[p0]] = false
                        holder.cbyes!!.visibility = GONE
                        holder.cbll!!.setBackgroundColor(Color.parseColor("#33000000"))
                    }
                    getIsSelected()[datas[p0]] == false -> {
                        isSelected[datas[p0]] = false
                        holder.cbyes!!.visibility = GONE
                        holder.cbll!!.setBackgroundColor(Color.parseColor("#33000000"))
                    }
                    getIsSelected()[datas[p0]] == true -> {
                        isSelected[datas[p0]] = true
                        holder.cbyes!!.visibility = VISIBLE
                        holder.cbll!!.setBackgroundResource(R.drawable.gvchoooseboder)
                    }
                }
            }
        }
        setIsSelected(isSelected)
        return view
    }

    override fun getItem(p0: Int) = datas[p0]

    override fun getItemId(p0: Int) = 0L

    override fun getCount() = datas.size


    internal inner class ViewHolder(view: View) {
        var cbtext: TextView? = null
        var cbll: RelativeLayout? = null
        var cbyes: ImageView? = null

        init {
            cbtext = view.findViewById(R.id.cb_text)
            cbll = view.findViewById(R.id.cb_ll)
            cbyes = view.findViewById(R.id.cb_text_yes)
        }
    }


    //设置item对应的postion
    fun changeState(position: Int) {
        selectorPosition = position
        notifyDataSetChanged()
    }

}




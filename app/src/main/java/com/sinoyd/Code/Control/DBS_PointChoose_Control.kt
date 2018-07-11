package com.sinoyd.Code.Control

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import com.sinoyd.Code.Activity.DBS_PointChooseActivity
import com.sinoyd.Code.Adapter.ChoosepointAdapter
import com.sinoyd.Code.Adapter.ChoosepointrightAdapter
import com.sinoyd.Code.DataClass.Categories
import com.sinoyd.Code.DataClass.Search
import com.sinoyd.Code.DataClass.Searchfortype
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import org.jetbrains.anko.*
import java.util.*


/**
 * 作者： scj
 * 创建时间： 2018/2/13
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Control
 */


//我们需要的数据结构
data class Orderdata(var level: String, var father: String, var data: Categories.DataBean, var latitude: Double, var longitude: Double, var favorite: Boolean, var id: String, var stationname: String)

//移动listview到指定位置
var smoothScrollToPosition = 0

//显示listview
fun show_order_list(activity: Activity, listview: ListView, datafirst: ArrayList<Orderdata>, datalistsec: ArrayList<Orderdata>) {
    var data: ArrayList<Orderdata> = ArrayList()
    data = Recombinationsata(datafirst, datalistsec)
    var adapter = ChoosepointAdapter(activity, data)
    listview.adapter = adapter
    listview.setSelection(smoothScrollToPosition)
    Log.i("scj", "smoothScrollToPosition:$smoothScrollToPosition")

//listview 的单击事件
    listview.onItemClick { adapterView, view, p0, l ->
        //展开列表  item的单击事件
        smoothScrollToPosition = p0
        when (data[p0].level) {
        //单击一级列表的时候
            "1" -> {
                //判断有没有孩子
                var off: Boolean = judgeoff(data[p0], data)
                if (!off) {
                    //需要开  发请求
                    Log.i("scj", "开")
                    (activity as DBS_PointChooseActivity).sendsearchtype(data[p0].data.id)

                } else {
                    //需要关
                    Log.i("scj", "关")
                    (activity as DBS_PointChooseActivity).sendcategories()
                }
            }
        //单击二级列表的时候
            "2" -> {
                var intent = (activity as DBS_PointChooseActivity).intent

                intent.putExtra("stationname", data[p0].stationname)
                intent.putExtra("stationid", data[p0].id.toInt())

                Log.i("scj", "打包前的数据如下：")
                Log.i("scj", "stationname:    ${data[p0].stationname}")
                Log.i("scj", "stationid:      ${data[p0].id}")

                (activity as DBS_PointChooseActivity).setResult(1, intent)
                (activity as DBS_PointChooseActivity).finish()
            }
        }

    }


}

//重组一级数据
fun Recombinationfirstdata(data: MutableList<Categories.DataBean>): ArrayList<Orderdata> {
    var recombinationdata: ArrayList<Orderdata> = ArrayList()
    data.mapTo(recombinationdata) { Orderdata("1", "无", it, 0.0, 0.0, false, "无", "无") }
    return recombinationdata
}

//重组二级数据
fun Recombinationsecdata(data: List<Searchfortype.DataBean>, choose: String): ArrayList<Orderdata> {
    var recombinationdata: ArrayList<Orderdata> = ArrayList()
    data.mapTo(recombinationdata) {
        Orderdata("2",
                when (choose) {
                    "type" -> {
                        it.type.id
                    }
                    "area" -> {
                        it.area.id
                    }
                    else -> {
                        ""
                    }
                }
                ,
                Categories.DataBean(it.id.toString(), it.name),
                try {
                    it.latitude.toDouble()
                } catch (e: Exception) {
                    0.0
                },
                try {
                    it.longitude.toDouble()
                } catch (e: Exception) {
                    0.0
                },
                it.favorite,
                it.id.toString(),
                it.name
        )
    }
    return recombinationdata
}

//重组一级 和  二级数据
fun Recombinationsata(datafirst: ArrayList<Orderdata>, datalistsec: ArrayList<Orderdata>): ArrayList<Orderdata> {
    return if (datalistsec.size != 0) {
        var datanew: ArrayList<Orderdata> = ArrayList()
        var nodeid: String = datalistsec[0].father
        var index: Int = 0
        for ((n, item) in datafirst.withIndex()) {
            if (item.data.id.toLowerCase() == nodeid.toLowerCase()) {
                index = n
            }
        }
        datanew.addAll(datafirst)
        datanew.addAll(index + 1, datalistsec)
        datanew
    } else {
        datafirst
    }


}

//判断有没有孩子
fun judgeoff(data: Orderdata, dataall: ArrayList<Orderdata>): Boolean {
    return dataall.any { it.father.toLowerCase() == data.data.id.toLowerCase() }
}

/**********************************************************************搜索界面的布局逻辑***************************************************************************************************************/
data class searchdata(var py: String, var id: String, var name: String, var favorite: Boolean, var father: Boolean)

//搜索界面显示逻辑
fun show_search_lv(activity: Activity, data: MutableList<Search.DataBean>, listviewleft: ListView, listviewright: ListView) {
    //整理数据
    var zhenglidata: ArrayList<searchdata> = ArrayList()
    var rightpydata: ArrayList<String> = ArrayList()
    var rightpydataafter: ArrayList<String> = ArrayList()
    data.mapTo(zhenglidata) { searchdata(getFirstSpell(it.name.substring(0, 1)).toUpperCase(), it.id.toString(), it.name, it.isFavorite, false) }
    //对整理后对数据排序
    Collections.sort(zhenglidata) { p0, p1 -> p0!!.py.compareTo(p1!!.py) }
    /*********************右边列表的数据****************************/
    zhenglidata.mapTo(rightpydata) { it.py }
    for (item in rightpydata) {
        if (!rightpydataafter.contains(item)) {
            rightpydataafter.add(item)
        }
    }
    Collections.sort(rightpydataafter) { p0, p1 -> p0!!.compareTo(p1!!) }
    /*********************右边列表的数据****************************/
    Log.i("scj", "整理后的数据zhenglidata：${zhenglidata.size}")
    Log.i("scj", "整理后的数据rightpydataafter：${rightpydataafter.size}")
    //右边listview的显示
    var rightadapter = SimpleAdapter(rightpydataafter)
    listviewright.adapter = rightadapter


    //最终的显示的数据
    var enddata: ArrayList<searchdata> = ArrayList()
    for (item in rightpydataafter) {
        enddata.add(searchdata(item, "", "", false, true))
        for (it in zhenglidata) {
            if (item == it.py) {
                enddata.add(it)
            }
        }
    }
    Log.i("scj", "最终数据：${enddata.size}")


    var leftadapter: ChoosepointrightAdapter = ChoosepointrightAdapter(activity, enddata)
    listviewleft.adapter = leftadapter
    listviewleft.setSelection(smoothScrollToPosition)


//右边列表单击事件
    listviewright.onItemClick { adapterView, view, i, l ->
        //选中对应单py
        for ((n, item) in enddata.withIndex()) {
            if (item.father) {
                if (rightpydataafter[i] == item.py) {
                    listviewleft.setSelection(n)
                    Log.i("scj", "平移的位置是：${n}个数据")
                    Log.i("scj", "左边的拼音是：${enddata[n].py}")
                    Log.i("scj", "右边的拼音是：${rightpydataafter[i]}")

                }
            }
        }
    }

//左边列表单机时间
    listviewleft.onItemClick { adapterView, view, i, l ->
        if (enddata[i].father) {

        } else {
            var intent2 = (activity as DBS_PointChooseActivity).intent

            intent2.putExtra("stationid", enddata[i].id.toInt())
            intent2.putExtra("stationname", enddata[i].name)

            Log.i("scj", "打包前的数据如下：")
            Log.i("scj", "stationname:    ${enddata[i].name}")
            Log.i("scj", "stationid:      ${enddata[i].id.toInt()}")

            (activity as DBS_PointChooseActivity).setResult(1, intent2)
            (activity as DBS_PointChooseActivity).finish()
        }

    }

}

//获取中文的首字母
fun getFirstSpell(chinese: String): String {
    val pybf = StringBuffer()
    val arr = chinese.toCharArray()
    val defaultFormat = HanyuPinyinOutputFormat()
    defaultFormat.caseType = HanyuPinyinCaseType.LOWERCASE
    defaultFormat.toneType = HanyuPinyinToneType.WITHOUT_TONE
    for (i in arr.indices) {
        if (arr[i].toInt() > 128) {
            try {
                val temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)
                if (temp != null) {
                    pybf.append(temp[0][0])
                }
            } catch (e: BadHanyuPinyinOutputFormatCombination) {
                e.printStackTrace()
            }

        } else {
            pybf.append(arr[i])
        }
    }
    return pybf.toString().replace("\\W".toRegex(), "").trim { it <= ' ' }
}

//简单的一个适配器
class SimpleAdapter(val list: ArrayList<String>) : BaseAdapter() {
    override fun getView(i: Int, v: View?, parent: ViewGroup?): View {
        return with(parent!!.context) {
            //Layout for a list view item
            linearLayout {
                textView {
                    gravity = Gravity.CENTER
                    text = list[i]
                    textSize = 10f
//                    textColor = context.resources.getColor(R.color.titlelbue)
                    textColor = Color.parseColor("#FFFFFF")//字体显示的背景
                }.lparams(matchParent, wrapContent)
            }

        }

    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0L
    }

    override fun getCount(): Int {
        return list.size
    }

}


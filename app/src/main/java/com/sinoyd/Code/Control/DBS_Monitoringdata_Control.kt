package com.sinoyd.Code.Control

import android.util.Log
import android.widget.GridView
import com.sinoyd.Code.Adapter.GridviewChooseAdapter
import com.sinoyd.Code.Adapter.NewGridviewChooseAdapter
import com.sinoyd.Code.DataClass.*
import com.sinoyd.Code.Fragment.DBS_TabMonitoringdataFragment
import com.sinoyd.Code.Model.BaseKV
import com.sinoyd.Code.View.MyGridView

/**
 * 作者： scj
 * 创建时间： 2018/3/6
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Control
 */
//整理数据方法
fun <T : BaseKV> getkeyvaluenmae(areasByCategories: MutableList<T>): ArrayList<Keyvalue> {
    var list: ArrayList<Keyvalue> = ArrayList()

    if (areasByCategories.isEmpty()) {

    } else {
        for (item in areasByCategories) {
            list.add(Keyvalue(item.id, item.name, false))
        }
        list[0].isselected = true
    }


    return list
}

//第一行的配置  类型
var gvtypeadapter: NewGridviewChooseAdapter? = null

fun showtype(act: DBS_TabMonitoringdataFragment, areasByCategories: MutableList<AreasByCategories.DataBean>, gv_type: GridView) {
    var listkeyvaluename: ArrayList<Keyvalue> = ArrayList()
    //整理数据
    listkeyvaluename = getkeyvaluenmae(areasByCategories)
    gvtypeadapter = NewGridviewChooseAdapter(listkeyvaluename, act.activity, true)//true:单选
    gv_type.adapter = gvtypeadapter
    //gv_type item单击事件 类型  单选功能
    gv_type.setOnItemClickListener { adapterView, view, i, l ->
        //修改原始数据
        listkeyvaluename = changedata(i, listkeyvaluename, gvtypeadapter!!.only)
        gvtypeadapter!!.notifyDataSetChanged()
        Log.i("scj", "选中---类型---->>>>")
        act.sendareascategory_id(areasByCategories[i].id)
    }
}

//根据第一行的配置获取第二行的配置    根据类型获取对应数据  显示
var gvareabytypeadapter: NewGridviewChooseAdapter? = null

fun showareabytype(act: DBS_TabMonitoringdataFragment, data: MutableList<Categories.DataBean>, gv_areabytpye: GridView) {
    var listkeyvaluenameareabytype: ArrayList<Keyvalue> = ArrayList()
    listkeyvaluenameareabytype = getkeyvaluenmae(data)
    gvareabytypeadapter = NewGridviewChooseAdapter(listkeyvaluenameareabytype, act.activity, false)//false:多选
    gv_areabytpye.adapter = gvareabytypeadapter
    gv_areabytpye.setOnItemClickListener { adapterView, view, i, l ->
        listkeyvaluenameareabytype = changedata(i, listkeyvaluenameareabytype, gvareabytypeadapter!!.only)
        gvareabytypeadapter!!.notifyDataSetChanged()
        Log.i("scj", "选中---类型之地点---->>>>")
        //发请求，去取 点位名称  getparameter(gvareabytypeadapter!!)
        when (getparametername(gvtypeadapter!!)) {
            "流域" -> {
                act.sendsearch("", "", "", getparameter(gvareabytypeadapter!!), "", "", "")
            }
            "控制类型" -> {
                act.sendsearch("", "", "", "", getparameter(gvareabytypeadapter!!), "", "")
            }
            "片区" -> {
                act.sendsearch("", "", getparameter(gvareabytypeadapter!!), "", "", "", "")
            }
            "站点类型" -> {
                act.sendsearch(getparameter(gvareabytypeadapter!!), "", "", "", "", "", "")
            }
            "河道" -> {
                act.sendsearch("", "", "", "", "", getparameter(gvareabytypeadapter!!), "")
            }
            "区域" -> {
                act.sendsearch("", getparameter(gvareabytypeadapter!!), "", "", "", "", "")
            }

        }
    }
}

//展示因子
var gvshowfactorcategoryadapter: NewGridviewChooseAdapter? = null

fun showfactorcategory(act: DBS_TabMonitoringdataFragment, data: MutableList<Factors.DataBean>, gv_showfactorcategory: GridView, gv_showfactor: MyGridView) {
    var listkeyvaluenamefactorcategory: HashSet<Keyvalue> = HashSet()
    var listkeyvaluenamefactorcategorynew: ArrayList<Keyvalue> = ArrayList()
    var listkeyvaluenamefactorcategorylinshi: ArrayList<Keyvalue> = ArrayList()
    data.mapTo(listkeyvaluenamefactorcategory) { Keyvalue("", it.category, false) }

    for (item in listkeyvaluenamefactorcategory) {
        listkeyvaluenamefactorcategorylinshi.add(item)
    }

    for ((inedex, item) in listkeyvaluenamefactorcategorylinshi.withIndex()) {
        listkeyvaluenamefactorcategorynew.add(listkeyvaluenamefactorcategorylinshi[listkeyvaluenamefactorcategorylinshi.size - inedex - 1])
    }


    listkeyvaluenamefactorcategorynew[0].isselected = true

    gvshowfactorcategoryadapter = NewGridviewChooseAdapter(listkeyvaluenamefactorcategorynew, act.activity, true)//true:单选
    gv_showfactorcategory.adapter = gvshowfactorcategoryadapter
    showfactor(act, getfactorbycategory(listkeyvaluenamefactorcategorynew[0], data), gv_showfactor)
    gv_showfactorcategory.setOnItemClickListener { adapterView, view, i, l ->
        listkeyvaluenamefactorcategorynew = changedata(i, listkeyvaluenamefactorcategorynew, gvshowfactorcategoryadapter!!.only)
        gvshowfactorcategoryadapter!!.notifyDataSetChanged()
        Log.i("scj", "选中---展示因子---->>>>")
        showfactor(act, getfactorbycategory(listkeyvaluenamefactorcategorynew[i], data), gv_showfactor)
    }
}

//筛选因子
fun getfactorbycategory(condition: Keyvalue, data: MutableList<Factors.DataBean>): ArrayList<Keyvalue> {
    var listkeyvaluenamefactor: ArrayList<Keyvalue> = ArrayList()
    data
            .filter { condition.valuename == it.category }
            .mapTo(listkeyvaluenamefactor) { Keyvalue(it.id, it.name, false) }

    try {
        listkeyvaluenamefactor[0].isselected = true
        listkeyvaluenamefactor[1].isselected = true
        listkeyvaluenamefactor[2].isselected = true
        listkeyvaluenamefactor[3].isselected = true
        listkeyvaluenamefactor[4].isselected = true
        listkeyvaluenamefactor[5].isselected = true
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return listkeyvaluenamefactor
}

//显示对应的因子  【用来获取 displayFactorIds  参数】
var gvfactoradapter: NewGridviewChooseAdapter? = null

fun showfactor(act: DBS_TabMonitoringdataFragment, data: ArrayList<Keyvalue>, gv_showfactor: MyGridView) {
    var listkeyvaluenamedata: ArrayList<Keyvalue> = ArrayList()
    listkeyvaluenamedata = data
    gvfactoradapter = NewGridviewChooseAdapter(listkeyvaluenamedata, act.activity, false)//false:多选
    gv_showfactor.adapter = gvfactoradapter
    gv_showfactor.setOnItemClickListener { adapterView, view, i, l ->
        listkeyvaluenamedata = changedata(i, listkeyvaluenamedata, gvfactoradapter!!.only)
        gvfactoradapter!!.notifyDataSetChanged()
        Log.i("scj", "选中---筛选因子---->>>>")
    }
}

//显示 结果集
var gvresultsadapter: NewGridviewChooseAdapter? = null

fun showresutls(act: DBS_TabMonitoringdataFragment, data: MutableList<Results.DataBean>, gv_showresult: GridView) {
    var listkeyvalueresult: ArrayList<Keyvalue> = ArrayList()
    listkeyvalueresult = getkeyvaluenmae(data)
    gvresultsadapter = NewGridviewChooseAdapter(listkeyvalueresult, act.activity, true)//true:单选
    gv_showresult.adapter = gvresultsadapter
    gv_showresult.setOnItemClickListener { adapterView, view, i, l ->
        listkeyvalueresult = changedata(i, listkeyvalueresult, gvresultsadapter!!.only)
        gvresultsadapter!!.notifyDataSetChanged()
        Log.i("scj", "选中---结果集---->>>>")
    }
}

//显示 过滤条件
var filtrationconditionadapter: NewGridviewChooseAdapter? = null

fun showFiltrationcondition(act: DBS_TabMonitoringdataFragment, gv_Filtrationcondition: MyGridView) {
    var listkeyvaluefiltrationcondition: ArrayList<Keyvalue> = ArrayList()
    listkeyvaluefiltrationcondition.add(Keyvalue("hour", "时", true))
    listkeyvaluefiltrationcondition.add(Keyvalue("day", "日", false))
    listkeyvaluefiltrationcondition.add(Keyvalue("month", "月", false))
    listkeyvaluefiltrationcondition.add(Keyvalue("year", "年", false))
    filtrationconditionadapter = NewGridviewChooseAdapter(listkeyvaluefiltrationcondition, act.activity, true)//true:单选
    gv_Filtrationcondition.adapter = filtrationconditionadapter
    gv_Filtrationcondition.setOnItemClickListener { adapterView, view, i, l ->
        listkeyvaluefiltrationcondition = changedata(i, listkeyvaluefiltrationcondition, filtrationconditionadapter!!.only)
        filtrationconditionadapter!!.notifyDataSetChanged()
        Log.i("scj", "选中---过滤条件---->>>>")
    }
}

//显示 点位名称  【用来获取 portIds 参数】
var gvstationnameadapter: NewGridviewChooseAdapter? = null

fun showstationname(act: DBS_TabMonitoringdataFragment, data: MutableList<Search.DataBean>, gv_showstationname: MyGridView) {
    var listkeyvaluestationname: ArrayList<Keyvalue> = ArrayList()
    listkeyvaluestationname = getkeyvaluenmae(data)
    gvstationnameadapter = NewGridviewChooseAdapter(listkeyvaluestationname, act.activity, false)
    gv_showstationname.adapter = gvstationnameadapter
    gv_showstationname.setOnItemClickListener { adapterView, view, i, l ->
        listkeyvaluestationname = changedata(i, listkeyvaluestationname, gvstationnameadapter!!.only)
        gvstationnameadapter!!.notifyDataSetChanged()
        Log.i("scj", "选中---点位名称---->>>>")
    }
}

//显示 参与评价因子
var gvtestfactoradapter: NewGridviewChooseAdapter? = null

fun showtestfactor(act: DBS_TabMonitoringdataFragment, data: MutableList<PJFactors.DataBean>, gv_showtestfactor: MyGridView) {
    var listkeyvaluetestfactor: ArrayList<Keyvalue> = ArrayList()
    listkeyvaluetestfactor = getkeyvaluenmae(data)
    gvtestfactoradapter = NewGridviewChooseAdapter(listkeyvaluetestfactor, act.activity, false)
    gv_showtestfactor.adapter = gvtestfactoradapter
    gv_showtestfactor.setOnItemClickListener { adapterView, view, i, l ->
        listkeyvaluetestfactor = changedata(i, listkeyvaluetestfactor, gvtestfactoradapter!!.only)
        gvtestfactoradapter!!.notifyDataSetChanged()
    }
}

//获取 "http://localhost:8108/api/v1/mobile/app/monitoring/search"  所需要的各种参数
fun getparameter(adapter: NewGridviewChooseAdapter): String {
    var parameter = ""
    if (adapter!!.datas.isEmpty()) {
        parameter = ""
    }
    if (adapter!!.datas.size == 1) {
        parameter = adapter!!.datas[0].keyid
    }
    if (adapter!!.datas.size > 1) {
        for (item in adapter!!.datas) {
            if (item.isselected) {
                parameter = parameter + item.keyid + ","
            }
        }
        parameter = parameter.substring(0, parameter.length - 1)
    }
    return parameter
}


//获取 "http://localhost:8108/api/v1/mobile/app/monitoring/search"  所需要的各种参数
fun getparametername(adapter: NewGridviewChooseAdapter): String {
    var parameter = ""
    if (adapter!!.datas.isEmpty()) {
        parameter = ""
    }
    if (adapter!!.datas.size == 1) {
        parameter = adapter!!.datas[0].valuename
    }
    if (adapter!!.datas.size > 1) {
        for (item in adapter!!.datas) {
            if (item.isselected) {
                parameter = parameter + item.valuename + ","
            }
        }
        parameter = parameter.substring(0, parameter.length - 1)
    }
    return parameter
}





package com.sinoyd.Code.Fragment

import android.os.Bundle
import android.view.View
import com.sinoyd.Code.Control.*
import com.sinoyd.Code.DataClass.*
import com.sinoyd.Code.Dialog.DateSelectDialog
import com.sinoyd.Code.Model.*
import com.sinoyd.Code.Until.SharedPreferencesFactory
import com.sinoyd.Code.Until.showgvkeyvalue
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.environmentsz.Kotlin.*
import com.sinoyd.frame.frgs.SinoBaseFragment
import kotlinx.android.synthetic.main.dbs_monitoringdata_fragment.*
import kotlinx.android.synthetic.main.gvkeyvalue_layout.*
import kotlinx.android.synthetic.main.layout_date_select.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList
import android.util.Log


/**
 * Created by Sinoyd on 2017/12/18.
 */
class DBS_TabMonitoringdataFragment : SinoBaseFragment(), DateSelectDialog.DateSelectListener {
    override fun selectDate(startDate: String, endDate: String) {
        tv_date_startTime.text = startDate
        tv_date_endTime.text = endDate
    }

    var date: Date = Date()
    var beginTime: String = date.getlastweekToday()
    var endTime: String = date.getToday()
    //网络请求
    var areasByCategoriesImpl: AreasByCategoriesImpl = AreasByCategoriesImpl()
    var areasByCategories: AreasByCategories = AreasByCategories()

    var areasImpl: AreasImpl = AreasImpl()
    var categories: Categories = Categories()

    var factorsImpl: FactorsImpl = FactorsImpl()
    var factors: Factors = Factors()

    var resultsImpl: ResultsImpl = ResultsImpl()
    var resutls: Results = Results()

    var searchImpl: SearchImpl = SearchImpl()
    var search: Search = Search()

    var pJFactorsImpl: PJFactorsImpl = PJFactorsImpl()
    var pJFactors: PJFactors = com.sinoyd.Code.DataClass.PJFactors()

    var pageSize = 50
    var pageNo = 1

    var monitoringsearchImpl: MonitoringsearchImpl = com.sinoyd.Code.Model.MonitoringsearchImpl()
    var monitoringsearch: Monitoringsearch = com.sinoyd.Code.DataClass.Monitoringsearch()

    //中间表格需要显示的数据
    var endlistdata: ArrayList<GVkeyvalue> = ArrayList()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout(R.layout.dbs_monitoringdata_fragment)
        nbBar.hide()
        //初始化界面
        setView()
        //各种监听事件
        setlisteners()
        //配置信息显示  one by one
        showconfigure()

    }

    private fun showconfigure() {

        //显示点位名称
//        var liststationname = arrayListOf(keyvalue("1", "龙吴路"), keyvalue("1", "虹桥镇"), keyvalue("1", "长宁区"), keyvalue("1", "静安区"), keyvalue("1", "浦东新区"), keyvalue("1", "黄浦区"))
//        gvstationnnameadapter = GridviewChooseAdapter(liststationname, activity, false)
//        gv_stationname.adapter = gvstationnnameadapter

        //发送请求  显示 类型
        sendareascategories()
        //发送请求  显示 因子
        sendfactors()
        //发送请求  显示 结果集
        sendresults()
        /**这3个请求可以一起发送**/
        //显示过滤条件  时  日  月  年
        showFiltrationcondition(this@DBS_TabMonitoringdataFragment, gv_Filtrationcondition)

    }


    override fun setView() {
        super.setView()
        titlename.text = "监控数据"
        iv_left.visibility = View.INVISIBLE
        iv_right2.visibility = View.VISIBLE

        iv_date_arrowLeft.visibility = View.GONE
        iv_date_arrowRight.visibility = View.GONE
        tv_date_startTime.text = date.getlastweekToday()
        tv_date_endTime.text = date.getToday()


    }

    private fun setlisteners() {
        //搜索按钮
        iv_right2.onClick {
            //显示
            ll_configure.visibility = View.VISIBLE
        }
        //配置暗黑部分
        ll_configure_left.onClick {
            ll_configure.visibility = View.GONE
        }
        //重置按钮
        tv_cancel.onClick {
            gvstationnameadapter!!.setChoiceType(false)
            gvareabytypeadapter!!.setChoiceType(false)
            gvfactoradapter!!.setChoiceType(false)
            gvtestfactoradapter!!.setChoiceType(false)
            gvresultsadapter!!.setChoiceType(false)
            filtrationconditionadapter!!.setChoiceType(false)
            //展示因子
            gvshowfactorcategoryadapter!!.setChoiceType(false)
            //类型
            gvtypeadapter!!.setChoiceType(false)
        }
        //确定按钮
        tv_sure.onClick {
            //获取一系列配置信息
            Log.i("scj", "portIds:  点位  [选中]   " + getparameter(gvstationnameadapter!!))
            Log.i("scj", "portIds:  点位  [全部]   " + gvstationnameadapter!!.datas)
            Log.i("scj", "？？？？:  类型对应数据  [选中]   " + getparameter(gvareabytypeadapter!!))
            Log.i("scj", "？？？？:  类型对应数据  [全部]   " + gvareabytypeadapter!!.datas)
            Log.i("scj", "displayFactorIds:  展示因子  [选中]   " + getparameter(gvfactoradapter!!))
            Log.i("scj", "displayFactorIds:  展示因子  [全部]   " + gvfactoradapter!!.datas)
            Log.i("scj", "evaluateFactorIds： 参与评价因子  [选中] " + getparameter(gvtestfactoradapter!!))
            Log.i("scj", "evaluateFactorIds： 参与评价因子  [全部] " + gvtestfactoradapter!!.datas)
            Log.i("scj", "beginTime  开始时间  " + tv_date_startTime.text.toString())
            Log.i("scj", "endTime  结束时间  " + tv_date_endTime.text.toString())
            Log.i("scj", "results:  结果集  [选中]   " + getparameter(gvresultsadapter!!))
            Log.i("scj", "results:  结果集  [全部]   " + gvresultsadapter!!.datas)
            Log.i("scj", "dataType:  数据类型  [选中]   " + getparameter(filtrationconditionadapter!!))
            Log.i("scj", "dataType:  数据类型  [全部]   " + filtrationconditionadapter!!.datas)
            Log.i("scj", "***********************************************************")
            pageNo = 1
            sendmonitoringsearch(getparameter(gvstationnameadapter!!), getparameter(gvfactoradapter!!), getparameter(gvtestfactoradapter!!), tv_date_startTime.text.toString(), tv_date_endTime.text.toString(), getparameter(gvresultsadapter!!), getparameter(filtrationconditionadapter!!), pageSize, pageNo)
        }
        //日期选择
        tv_date_flag.onClick {
            val dateSelectDialog = DateSelectDialog(activity, R.style.Theme_Dialog_Transparent)
            dateSelectDialog.setStartAndEndDate(tv_date_startTime.text.toString(), tv_date_endTime.text.toString())
            dateSelectDialog.setDateSelectListener(this)
            dateSelectDialog.show()
        }
        //
        tv_date_endTime.onClick {
            tv_date_endTime.text = date.getSpecifiedDayAfter(tv_date_endTime.text.toString())
            endTime = tv_date_endTime.text.toString()
        }
        //
        tv_date_startTime.onClick {
            tv_date_startTime.text = date.getSpecifiedDayBefore(tv_date_startTime.text.toString())
            beginTime = tv_date_startTime.text.toString()
        }


        //上拉加载
        main_pull_refresh_view.setOnFooterRefreshListener {
            //            main_pull_refresh_view.onFooterRefreshComplete()
            pageNo++
            sendmonitoringsearch(getparameter(gvstationnameadapter!!), getparameter(gvfactoradapter!!), getparameter(gvtestfactoradapter!!), tv_date_startTime.text.toString(), tv_date_endTime.text.toString(), getparameter(gvresultsadapter!!), getparameter(filtrationconditionadapter!!), pageSize, pageNo)
            Log.i("scj", "上拉加载拉")
        }
        //下拉刷新
        main_pull_refresh_view.setOnHeaderRefreshListener {
            main_pull_refresh_view.onHeaderRefreshComplete("更新时间:" + Calendar.getInstance().time.toLocaleString())
            pageNo = 1
            sendmonitoringsearch(getparameter(gvstationnameadapter!!), getparameter(gvfactoradapter!!), getparameter(gvtestfactoradapter!!), tv_date_startTime.text.toString(), tv_date_endTime.text.toString(), getparameter(gvresultsadapter!!), getparameter(filtrationconditionadapter!!), pageSize, pageNo)
            Log.i("scj", "下拉刷新")
        }

//        main_pullrefresh_layout.setRefreshListener(object : PullRefreshLayout.OnRefreshListener {
//            override fun refreshFinished() {
//                pageNo = 1
//                sendmonitoringsearch(getparameter(gvstationnameadapter!!), getparameter(gvfactoradapter!!), getparameter(gvtestfactoradapter!!), tv_date_startTime.text.toString(), tv_date_endTime.text.toString(), getparameter(gvresultsadapter!!), getparameter(filtrationconditionadapter!!), pageSize, pageNo)
//            }
//
//            override fun loadMoreFinished() {
//
//                pageNo++
//                sendmonitoringsearch(getparameter(gvstationnameadapter!!), getparameter(gvfactoradapter!!), getparameter(gvtestfactoradapter!!), tv_date_startTime.text.toString(), tv_date_endTime.text.toString(), getparameter(gvresultsadapter!!), getparameter(filtrationconditionadapter!!), pageSize, pageNo)
//            }
//        })


    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(activity, "loadsuccess")
        when (TAG) {
        //类型
            "areasByCategoriesImpl" -> {
                try {
                    areasByCategories = gson.fromJson(resData, AreasByCategories::class.java)
                } catch (e: Exception) {
                    toast("areasByCategories_Json解析有误")
                    return
                }
                sendareascategory_id(areasByCategories.data[0].id)
                //显示类型配置
                showtype(this@DBS_TabMonitoringdataFragment, areasByCategories.data, gv_type)
            }
        //区域
            "areasImpl" -> {
                try {
                    categories = gson.fromJson(resData, Categories::class.java)
                } catch (e: Exception) {
                    toast("categories_JSON解析失败")
                    return
                }
                showareabytype(this@DBS_TabMonitoringdataFragment, categories.data, gv_areabytpye)

                //去拿点位名称  categories.data[0].id
                when (getparametername(gvtypeadapter!!)) {
                    "流域" -> {
                        sendsearch("", "", "", categories.data[0].id, "", "", "")
                    }
                    "控制类型" -> {
                        sendsearch("", "", "", "", categories.data[0].id, "", "")
                    }
                    "片区" -> {
                        sendsearch("", "", categories.data[0].id, "", "", "", "")
                    }
                    "站点类型" -> {
                        sendsearch(categories.data[0].id, "", "", "", "", "", "")
                    }
                    "河道" -> {
                        sendsearch("", "", "", "", "", categories.data[0].id, "")
                    }
                    "区域" -> {
                        sendsearch("", categories.data[0].id, "", "", "", "", "")
                    }
                }
            }
        //所有因子
            "factorsImpl" -> {
                try {
                    factors = gson.fromJson(resData, Factors::class.java)
                } catch (e: Exception) {
                    toast("factors_JSON解析失败")
                    return
                }
                showfactorcategory(this@DBS_TabMonitoringdataFragment, factors.data, gv_showfactorcategory, gv_showfactor)
            }
        //结果集
            "resultsImpl" -> {
                try {
                    resutls = gson.fromJson(resData, Results::class.java)
                } catch (e: Exception) {
                    toast("fresutls_JSON解析失败")
                    return
                }
                showresutls(this@DBS_TabMonitoringdataFragment, resutls.data, gv_showresult)
            }
        //点位名称
            "searchImpl" -> {
                try {
                    search = gson.fromJson(resData, Search::class.java)
                } catch (e: Exception) {
                    toast("search_JSON解析失败")
                    return
                }
                //显示 点位名称
                showstationname(this@DBS_TabMonitoringdataFragment, search.data, gv_showstationname)

                //去拿测评因子
                if (getparameter(gvstationnameadapter!!) == "") {
                    toast("无站点")
                } else {
                    sendtestfactor(getparameter(gvstationnameadapter!!))
                }
            }
            "pJFactorsImpl" -> {
                try {
                    pJFactors = gson.fromJson(resData, PJFactors::class.java)
                } catch (e: Exception) {
                    toast("pJFactors_JSON解析失败")
                    return
                }
                //显示参与评价因子
                showtestfactor(this@DBS_TabMonitoringdataFragment, pJFactors.data, gv_showtestfactor)

                tv_sure.performClick()

            }

            "monitoringsearchImpl" -> {
                try {
                    ll_configure.visibility = View.GONE
                    main_pull_refresh_view.onHeaderRefreshComplete()
                    main_pull_refresh_view.onFooterRefreshComplete()
//                    main_pullrefresh_layout.refreshFinished()
//                    main_pullrefresh_layout.loadMoreFinished()
//                    xrecyclerView.refreshComplete()
//                    xrecyclerView.loadMoreComplete()
                    monitoringsearch = gson.fromJson(resData, Monitoringsearch::class.java)
                } catch (e: Exception) {
                    toast("monitoringsearch_JSON解析失败")
                    return
                }
                //整理我需要的数据形式
                if (monitoringsearch.data.listdata == null || monitoringsearch.data.listdata.size == 0) {
                    toast("获取数据为空")
                } else {
                    if (pageNo == 1) {
                        endlistdata = ArrayList()
                    }
                    for (item in monitoringsearch.data.listdata) {
                        for (it in item.datas) {
                            endlistdata.add(GVkeyvalue(GVkeytime(item.portName, item.time), GVkeytime(it.factor, it.unit), it.value))
                        }
                    }
                }
                //显示出来
                showgvkeyvalue(activity, gvkeyvalue_left, gvkeyvalue_right, gvkeyvalue_middle, endlistdata, SyncScrollViewlift, SyncScrollViewright)

            }
        }
    }

    override fun requestFailed(resData: String) {
        super.requestFailed(resData)
        showdialog(activity, "loadfail")
    }

    override fun onDestroy() {
        super.onDestroy()
        showdialog(activity, "loaddismiss")
    }

    //发送请求  获取可选的区域类型
    fun sendareascategories() {
        showdialog(activity, "loadshow")
        areasByCategoriesImpl.getAreasByCategories("areasByCategoriesImpl", this)
    }

    //发送请求  根据id获取所有区域
    fun sendareascategory_id(id: String) {
        showdialog(activity, "loadshow")
        areasImpl.getAreas(id, "areasImpl", this)
    }

    //发送请求  获取所有因子
    fun sendfactors() {
        showdialog(activity, "loadshow")
        factorsImpl.getFactors("", "factorsImpl", this)
    }

    //发送请求  获取结果集
    fun sendresults() {
        showdialog(activity, "loadshow")
        resultsImpl.getResults("resultsImpl", this)
    }

    //发送请求  获取点位名称
    fun sendsearch(station_type_id: String,//站点类型id
                   region_id: String,//区域id
                   area_id: String,//片区id
                   basin_id: String,//流域id
                   control_type_id: String,//控制类型id
                   river_course_id: String,//区河道id
                   keyword: String//关键字
    ) {
        showdialog(activity, "loadshow")
        searchImpl.getSearch(SharedPreferencesFactory.getdata(activity, "loginId"),
                station_type_id,//站点类型id
                region_id,//区域id
                area_id,//片区id
                basin_id,//流域id
                control_type_id,//控制类型id
                river_course_id,//区河道id
                keyword, //关键字
                "searchImpl", this)
    }

    //发送请求  参与评价因子
    fun sendtestfactor(portid: String) {
        showdialog(activity, "loadshow")
        pJFactorsImpl.getPJFactors(portid, "pJFactorsImpl", this)
    }

    //发送请求   查询监控数据
    fun sendmonitoringsearch(portIds: String,
                             displayFactorIds: String,
                             evaluateFactorIds: String,
                             beginTime: String,
                             endTime: String,
                             results: String,
                             dataType: String,
                             pageSize: Int,
                             pageNo: Int) {
        showdialog(activity, "loadshow")
        monitoringsearchImpl.getMonitoringsearch(portIds, displayFactorIds, evaluateFactorIds, beginTime, endTime, results, dataType, pageSize, pageNo, "monitoringsearchImpl", this)

    }


}
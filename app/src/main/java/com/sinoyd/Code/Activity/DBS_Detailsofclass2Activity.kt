package com.sinoyd.Code.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import com.example.shenchuanjiang.kotlin1013test.CommonSelector
import com.sinoyd.Code.Control.gson
import com.sinoyd.Code.Control.showdown
import com.sinoyd.Code.Control.showmiddle
import com.sinoyd.Code.Control.showup
import com.sinoyd.Code.DataClass.*
import com.sinoyd.Code.Model.ConcentrationModelImpl
import com.sinoyd.Code.Model.FactorsImpl
import com.sinoyd.Code.Model.StatisticsByPointIdImpl
import com.sinoyd.Code.Until.getfactorlist
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.frame.actys.SinoBaseActivity
import kotlinx.android.synthetic.main.activity_dbs__detailsofclass2.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

class DBS_Detailsofclass2Activity : SinoBaseActivity() {
    var factorlist = getfactorlist()
    var factorliststirng: ArrayList<String> = ArrayList()
    var comm: CommonSelector? = null
    var portId: String = ""
    var portname: String = ""
    var type = " "

    //网络请求
    var statisticsByPointIdImpl: StatisticsByPointIdImpl = StatisticsByPointIdImpl()
    var statisticsByPointId: StatisticsByPointId = StatisticsByPointId()
    var factorsImpl: FactorsImpl = FactorsImpl()
    var factors: Factors = Factors()
    var factorsnamelist: ArrayList<String> = ArrayList()
    var concentrationModelImpl: ConcentrationModelImpl = ConcentrationModelImpl()
    var concentration: Concentration = com.sinoyd.Code.DataClass.Concentration()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbs__detailsofclass2)
        nbBar.hide()
        portId = intent.getStringExtra("id")
        portname = intent.getStringExtra("name")
        setView()
        setlisteners()
        //发送请求  站点水质数据
        sendstatisticsByPointId()
        //发送请求  获取所有因子
        sendfactors()
    }


    override fun setView() {
        super.setView()
        for (item in factorlist) {
            factorliststirng.add(item.name)
        }

        iv_left.onClick {
            finish()
        }
        iv_right.visibility = View.INVISIBLE

        titlename.text = Html.fromHtml("<font color='white'>$portname</font><font color='white' size='5'>v</font>")
    }

    private fun setlisteners() {

        //因子选择
        ll_choose_factor.onClick {
            if (factorsnamelist == null || factorsnamelist.size == 0) {
                toast("此站点无因子信息")
            } else {
                comm = CommonSelector(activity, ll_choose_factor, factorsnamelist, object : CommonSelector.OnSelectClickListener {
                    override fun onCommonItemSelect(postions: Int) {
                        tv_choose_factor.text = factorsnamelist[postions]
                        tv_choose_factor_unit.text = "单位:${factors.data[postions].unit}"
                        //发送请求
                        sendconcentration(portId, factors.data[postions].id, type)
                    }
                })
                comm!!.showPop()
            }

        }

        titlename.onClick {
            var intent = Intent()
            intent.setClass(activity, DBS_PointChooseActivity::class.java)
            activity.startActivityForResult(intent, 2)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            1 -> {
                portId = data!!.getIntExtra("stationid", 0).toString()
                titlename.text = Html.fromHtml("<font color='white'>${data!!.getStringExtra("stationname")}</font><font color='white' size='5'>v</font>")
                sendstatisticsByPointId()
                sendfactors()
            }
        }
    }

    //站点水质数据
    fun sendstatisticsByPointId() {
        showdialog(this, "loadshow")
        statisticsByPointIdImpl.getStatisticsByPointId(portId, "statisticsByPointIdImpl", this)
    }

    //获取所有因子
    private fun sendfactors() {
        showdialog(this, "loadshow")
        factorsImpl.getFactors(portId, "factorsImpl", this)
    }

    //因子浓度(24小时)
    private fun sendconcentration(portId: String, factorid: String, type: String) {
        showdialog(this, "loadshow")
        concentrationModelImpl.getConcentration(portId, factorid, type, "concentrationModelImpl", this)
    }


    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(this, "loadsuccess")
        when (TAG) {
            "statisticsByPointIdImpl" -> {
                try {
                    statisticsByPointId = gson.fromJson(resData, StatisticsByPointId::class.java)
                } catch (e: Exception) {
                    toast("statisticsByPointId_JSON解析出错")
                    return
                }
                showup(tv_timestamp, iv_grade, tv_targetGrade, tv_qualifiedStatus, tv_primaryPollute, tv_primaryPolluteValue, tv_polluteValue, statisticsByPointId.data)
                //重组数据
                var datalist: java.util.ArrayList<GVkeyvaluesimple> = java.util.ArrayList()
                if (statisticsByPointId.data.factors == null) {
//                    char1layout.removeAllViews()
                    showmiddle(activity, barchart, ArrayList())
                } else {
                    for (item in statisticsByPointId.data.factors) {
                        var kv = when (item.grade) {
                            "I" -> 1
                            "II" -> 2
                            "III" -> 3
                            "IV" -> 4
                            "V" -> 5
                            "劣V" -> 6
                            else -> 0
                        }
                        datalist.add(GVkeyvaluesimple(item.name, item.name + " " + item.value, kv.toString()))
                    }
                    showmiddle(activity, barchart, datalist)
                }
            }
            "factorsImpl" -> {
                try {
                    factors = gson.fromJson(resData, Factors::class.java)
                    factorsnamelist = ArrayList()
                    if (factors.data == null || factors.data.size == 0) {
                        toast("此站点无因子信息")
                        return
                    }
                    for (item in factors.data) {
                        factorsnamelist.add(item.name)
                    }
                    tv_choose_factor.text = factors.data[0].name
                    tv_choose_factor_unit.text = "单位:${factors.data[0].unit}"
                    sendconcentration(portId, factors.data[0].id, type)
                } catch (e: Exception) {
                    toast("factors_JSON解析出错")
                }
                //发请求   因子浓度(24小时)
            }
            "concentrationModelImpl" -> {
                try {
                    concentration = gson.fromJson(resData, Concentration::class.java)
                } catch (e: Exception) {
                    toast("concentration_JSON解析出错")
                }
                if (concentration.data.isEmpty()) {
//                    char2layout.removeAllViews()
                    showdown(activity, lineChart, ArrayList())
                } else {
                    showdown(activity, lineChart, concentration.data)
                }
            }
        }
    }


    override fun requestFailed(resData: String) {
        showdialog(this, "loadfail")
        super.requestFailed(resData)
    }

    override fun onDestroy() {
        showdialog(this, "loaddismiss")
        super.onDestroy()
    }
}

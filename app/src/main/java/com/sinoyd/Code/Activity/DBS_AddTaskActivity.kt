package com.sinoyd.Code.Activity

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.reflect.TypeToken
import com.jaygoo.selector.MultiSelectPopWindow
import com.macaulish.top.coconut.util.DateKits
import com.macaulish.top.velvet.util.Logger
import com.macaulish.top.velvet.util.Toaster
import com.sinoyd.Code.Control.gson
import com.sinoyd.Code.DataClass.IntegratorInfo
import com.sinoyd.Code.DataClass.PointInfo
import com.sinoyd.Code.Dialog.DateSelectDialog
import com.sinoyd.Code.Model.TaskModel
import com.sinoyd.Code.Until.DisplayorhideSoftkeyboard
import com.sinoyd.Code.Until.SharedPreferencesFactory
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.environmentsz.Kotlin.getToday
import com.sinoyd.environmentsz.Kotlin.getnextweekToday
import com.sinoyd.frame.actys.SinoBaseActivity
import kotlinx.android.synthetic.main.activity_dbs__add_task.*
import kotlinx.android.synthetic.main.dbs_quality_control_fragment.*
import kotlinx.android.synthetic.main.item_integrator_layout.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.onClick
import java.util.*

class DBS_AddTaskActivity : SinoBaseActivity(), DateSelectDialog.DateSelectListener {

    val dataManager = TaskModel()
    var pointsInfo = ArrayList<PointInfo>()
    var integratorArray = ArrayList<IntegratorInfo>()

    override fun selectDate(startDate: String, endDate: String) {
        task_add_tv_finish_date.text = "$startDate--$endDate"
    }

    var date: Date = Date()
    var beginTime: String = date.getToday()
    var endTime: String = date.getnextweekToday()
    var selectPointSId = ArrayList<Int>()

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbs__add_task)
        setView()
        setlisteners()
        getFormInfo()
    }

    fun getFormInfo(){
        showdialog(activity, "loadshow")
        dataManager.requestPointInfo(SharedPreferencesFactory.getdata(this,"loginId"),this,REQUEST_POINT_INFO)
        dataManager.requestIntergratorInfo(this, REQUEST_INTEGRATOR_INFO)
    }

    override fun setView() {
        super.setView()
        titlename.text = "新建任务"
        titlename.gravity = Gravity.CENTER_VERTICAL
        task_add_tv_finish_date.text = "$beginTime--$endTime"
        DisplayorhideSoftkeyboard.hideSoftkeyboard(activity)
        task_add_tv_publish_man.text = SharedPreferencesFactory.getdata(this,"userName")
        task_add_tv_publish_datetime.text = DateKits.getNow("yyyy/MM/dd hh:mm:ss")
    }

    private fun setlisteners() {
        //返回按钮
        iv_left.onClick {
            finish()
        }

        task_add_iv_point_name.onClick {
            val m = MultiSelectPopWindow.Builder(this)
                    .setNameArray(pointsInfo.run {
                        val pointsName = ArrayList<String>()
                        for (p in this) {
                            pointsName.add(p.MonitoringPointName)
                        }
                        pointsName
                    }
                    )
                    .setConfirmListener(object : MultiSelectPopWindow.OnConfirmClickListener {
                        override fun onClick(indexList: ArrayList<Int>, selectedList: ArrayList<String>) {
                            selectPointSId = ArrayList()
                            task_add_tv_point_name.text = ""
                            var names = ""
                            for (i in indexList) {
                                selectPointSId.add(pointsInfo[i].PointId)
                                names += pointsInfo[i].MonitoringPointName+" "
                            }
                            task_add_tv_point_name.text  = names
                        }
                    })
                    .setCancel("取消")
                    .setConfirm("完成")
                    .setTitle("站点列表")
                    .build()
            m.backgroundAlpha(1.0f)
            m.show(task_add_ll_point_name)
        }

        task_add_ll_finish_date.onClick {
            val dateSelectDialog = DateSelectDialog(activity, R.style.Theme_Dialog_Transparent)
            dateSelectDialog.setStartAndEndDate(beginTime, endTime)
            dateSelectDialog.setDateSelectListener(this)
            dateSelectDialog.show()
        }

        task_add_sp_deal_man.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Toaster.shortToast(this@DBS_AddTaskActivity,"position = $p2")
                task_add_tv_deal_man.text  = integratorArray[p2].name
            }

        }
        task_add_btn_submit.onClick {
            if(task_add_et_task_name.text.trim().isEmpty()
                    ||task_add_tv_point_name.text.trim().isEmpty()
                    ||task_add_tv_finish_date.text.trim().isEmpty()
                    ||task_add_tv_deal_man.text.trim().isEmpty()
                    ||task_add_tv_publish_man.text.trim().isEmpty()
                    ||task_add_tv_publish_datetime.text.trim().isEmpty()){
                Toaster.shortToast(this,"请输入全部数据")
            }else{
                val taskName = task_add_et_task_name.text.toString()
                var pointIds = ""
                for(i in selectPointSId){
                    pointIds += ",$i"
                }
                pointIds = pointIds.substring(1,pointIds.length)
                val finishDate = task_add_tv_finish_date.text.toString()
                val dealMan = task_add_tv_deal_man.text.toString()
                val publisher = task_add_tv_publish_man.text.toString()
                val publishTime = task_add_tv_publish_datetime.text.toString()
                showdialog(activity, "loadshow")
                dataManager.uploadTask(taskName,pointIds,finishDate,dealMan,publisher,publishTime, UPLOAD_NEW_TASK,this)
            }
        }
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(null,"loadsuccess")
        when(TAG){
            REQUEST_POINT_INFO ->{
                Logger.i("加载站点数据成功")
                try{
                    pointsInfo = gson.fromJson<ArrayList<PointInfo>>(resData, object : TypeToken<ArrayList<PointInfo>>() {}.type)
                }catch (e:Exception){
                    Toaster.shortToast(this,"站点信息JSON解析失败")
                }
            }
            REQUEST_INTEGRATOR_INFO ->{
                Logger.i("加载服务商数据成功")
                try{
                    integratorArray = gson.fromJson<ArrayList<IntegratorInfo>>(resData, object : TypeToken<ArrayList<IntegratorInfo>>(){}.type)
                    val adapter = ArrayAdapter<IntegratorInfo>(this,R.layout.item_integrator_layout,R.id.item_spinner_integrator, integratorArray)
                    adapter.setDropDownViewResource(R.layout.item_dropdown_style)
                    task_add_sp_deal_man.adapter = adapter
                }catch (e:Exception){
                    Toaster.shortToast(this,"服务商信息JSON解析失败")
                }
            }
            UPLOAD_NEW_TASK ->{
                Logger.i("新建任务上传成功")
                Toaster.shortToast(this,"新建成功")
            }
        }
    }

    override fun requestFailed(resData: String) {
        super.requestFailed(resData)
        Logger.i("加载站点数据失败")
        showdialog(null,"loadfail")
    }



    companion object {
        val REQUEST_POINT_INFO = "request for points"
        val UPLOAD_NEW_TASK = "update new task"
        val REQUEST_INTEGRATOR_INFO = "request for integrators"
    }
}

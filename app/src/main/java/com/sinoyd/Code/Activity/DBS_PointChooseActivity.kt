package com.sinoyd.Code.Activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import com.sinoyd.Code.Control.*
import com.sinoyd.Code.DataClass.*
import com.sinoyd.Code.Model.*
import com.sinoyd.Code.Until.SharedPreferencesFactory
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.frame.actys.SinoBaseActivity
import kotlinx.android.synthetic.main.activity_point_choose.*
import kotlinx.android.synthetic.main.chooselayout.*
import kotlinx.android.synthetic.main.titlelayout.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.onClick
import org.jetbrains.anko.padding
import org.jetbrains.anko.toast
import com.sinoyd.Code.Until.DisplayorhideSoftkeyboard


class DBS_PointChooseActivity : SinoBaseActivity() {

    //请求
    var categoriesImpl: CategoriesImpl = CategoriesImpl()
    var categories: Categories = Categories()

    var searchfortypeImpl: SearchfortypeImpl = SearchfortypeImpl()
    var searchfortype: Searchfortype = com.sinoyd.Code.DataClass.Searchfortype()

    var searchforareaImpl: SearchforareaImpl = SearchforareaImpl()

    var areasImpl: AreasImpl = AreasImpl()

    var favoriteImpl: FavoriteImpl = FavoriteImpl()
    var favorite: Favorite = com.sinoyd.Code.DataClass.Favorite()

    //一级数据
    var datalistfirst: ArrayList<Orderdata> = ArrayList()
    //二级数据
    var datalistsec: ArrayList<Orderdata> = ArrayList()
    //开关
    var off: Boolean = false
    //choose
    var choose = "type"

    var fatherid: String = ""

    /*******************************搜索*************************************/
    var searchImpl: SearchImpl = SearchImpl()
    var search: Search = Search()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_choose)
        smoothScrollToPosition = 0
        setView()
        setlisteners()
    }

    override fun setView() {
        super.setView()
//        titlename.gravity = Gravity.CENTER_VERTICAL
        iv_left.padding = 20
        iv_right2.padding = 15
        iv_right.visibility = View.GONE
        iv_right2.visibility = View.VISIBLE
        iv_right2.imageResource = R.drawable.icon_search
        titlename.text = "点位选择"
        rb_type.isChecked = true

    }

    override fun onResume() {
        super.onResume()
        sendcategories()
    }

    private fun setlisteners() {
        iv_left.onClick {
            finish()
        }
        //搜索按钮
        iv_right2.onClick {
            toast("搜索")
        }
        //选择栏
        rg_pointchoose.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb_type -> {
                    choose = "type"
                }
                R.id.rb_area -> {
                    choose = "area"
                }
            }
            sendcategories()
        }

        //搜索按钮
        iv_right2.onClick {
            if (ll_typearea.visibility == View.GONE) {
                rb_type.isChecked = true
                ll_typearea.visibility = View.VISIBLE
                choose = "type"
                ll_search.visibility = View.GONE
                sendcategories()
            } else {
                choose = "search"
                ll_typearea.visibility = View.GONE
                ll_search.visibility = View.VISIBLE
                //发请求 搜索点位(所有)
                sendsearch("", "", "", "", "", "", "")
            }
        }

        //软键盘的搜索按钮
        et_search.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                sendsearch("", "", "", "", "", "", et_search.text.toString())
                true
            }
            false
        }


    }

    //发送请求  获取站点类型
    fun sendcategories() {
        showdialog(this, "loadshow")
        when (choose) {
            "type" -> {
                categoriesImpl.getCategories("categoriesImpl", this)
            }
            "area" -> {
                areasImpl.getAreas("fe9a88a4-1560-4482-a815-a12a358bcc19", "categoriesImpl", this)
            }
            "search" -> {

            }
        }

    }

    //发送请求 搜索 站点 类型   区域
    fun sendsearchtype(id: String) {
        fatherid = id
        showdialog(this, "loadshow")
        when (choose) {
            "type" -> {
                searchfortypeImpl.getSearchfortype(SharedPreferencesFactory.getdata(activity, "loginId"), id, "searchfortypeImpl", this)
            }
            "area" -> {
                searchforareaImpl.getSearchforarea(SharedPreferencesFactory.getdata(activity, "loginId"), id, "searchfortypeImpl", this)
            }
            "search" -> {
                sendsearch("", "", "", "", "", "", "")
            }
        }

    }

    //发送请求 关注/取关点位
    fun sendfavorite(id: String, state: String, ch: String) {
        if (ch != "") {
            choose = ch
        }
        showdialog(this, "loadshow")
        favoriteImpl.getFavorite(SharedPreferencesFactory.getdata(activity, "loginId"), state, id, "favoriteImpl", this)
    }

    //发送请求  搜索点位(所有)
    fun sendsearch(station_type_id: String,//站点类型id
                   region_id: String,//区域id
                   area_id: String,//片区id
                   basin_id: String,//流域id
                   control_type_id: String,//控制类型id
                   river_course_id: String,//河道id
                   key: String//关键字

    ) {
        showdialog(this, "loadshow")
        searchImpl.getSearch(SharedPreferencesFactory.getdata(activity, "loginId"), "", "", "", "", "", "", key, "searchImpl", this)
    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(this, "loadsuccess")
        when (TAG) {
            "categoriesImpl" -> {
                try {
                    categories = gson.fromJson(resData, Categories::class.java)
                } catch (e: Exception) {
                    toast("categories_JSON解析失败")
                    return
                }
                //一级列表类型显示
                //整理数据
                datalistfirst = ArrayList()
                datalistsec = ArrayList()
                datalistfirst = Recombinationfirstdata(categories.data)
                show_order_list(activity, lv_orderlist, datalistfirst, datalistsec)

            }
            "searchfortypeImpl" -> {
                try {
                    searchfortype = gson.fromJson(resData, Searchfortype::class.java)
                } catch (e: Exception) {
                    toast("searchfortype_JSON解析失败")
                    return
                }
                //二级列表类型显示
                //整理数据
                datalistsec = ArrayList()
                datalistsec = Recombinationsecdata(searchfortype.data, choose)
                //显示
                show_order_list(activity, lv_orderlist, datalistfirst, datalistsec)
            }
            "favoriteImpl" -> {
                try {
                    favorite = gson.fromJson(resData, Favorite::class.java)
                } catch (e: Exception) {
                    toast("favorite_JSON解析失败")
                    return
                }
                when (favorite.status) {
                    "0" -> {
                        sendsearchtype(fatherid)
                    }
                    "1" -> {
                        toast(favorite.message)
                    }
                }
            }
            "searchImpl" -> {
                DisplayorhideSoftkeyboard.hideSoftkeyboard(activity)
                try {
                    search = gson.fromJson(resData, Search::class.java)
                } catch (e: Exception) {
                    toast("search_JSON解析失败")
                    return
                }
                show_search_lv(activity, search.data, lv_orderlist2, lv_orderlistright)
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

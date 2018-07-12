package com.sinoyd.Code.Activity

import android.os.Bundle
import android.widget.TextView
import com.sinoyd.Code.Control.gson
import com.sinoyd.Code.DataClass.Login
import com.sinoyd.Code.Model.LoginImpl
import com.sinoyd.Code.Until.DisplayorhideSoftkeyboard
import com.sinoyd.Code.Until.SharedPreferencesFactory
import com.sinoyd.Code.Until.showdialog
import com.sinoyd.R
import com.sinoyd.frame.actys.SinoBaseActivity
import kotlinx.android.synthetic.main.activity_dbs__login.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DBS_LoginActivity : SinoBaseActivity() {


    var username = ""
    var password = ""

    var loginImpl: LoginImpl = LoginImpl()
    var login: Login = com.sinoyd.Code.DataClass.Login()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nbBar.hide()
        setLayout(R.layout.activity_dbs__login)
        openSlideFinish(false)
        intitview()
        //默认不弹软键盘
        DisplayorhideSoftkeyboard.hideSoftkeyboard(this)
    }

    override fun onResume() {
        super.onResume()
        et_username.setText(SharedPreferencesFactory.getdata(activity, "phonenumber"), TextView.BufferType.EDITABLE)
        //移动光标到Edittext末尾
        et_username.setSelection(et_username.text.toString().length)
    }

    private fun intitview() {
        username = et_username.text.toString()
        password = et_password.text.toString()

        //发送短信验证码
        iv_send.onClick {


        }
        //登录
        bt_login.onClick {
            if (et_username.text.toString() == "") {
                toast("请输入手机号码")
                return@onClick
            }
            if (et_password.text.toString() == "") {
                toast("请输入密码")
                return@onClick
            }
            //发送登陆请求
            sendlogin(et_username.text.toString(), et_password.text.toString())
        }


    }

    override fun requestSuccess(resData: String, TAG: String) {
        super.requestSuccess(resData, TAG)
        showdialog(this, "loadsuccess")
        when (TAG) {
            "loginImpl" -> {
                try {
                    login = gson.fromJson(resData, Login::class.java)
                } catch (e: Exception) {
                    toast("login_JSON解析失败")
                    return
                }
                if (login.status == "0") {
                    toast("登陆成功")
                    SharedPreferencesFactory.savedata(activity, "loginId", login.data[0].id)
                    SharedPreferencesFactory.savedata(activity, "phonenumber", et_username.text.toString())
                    SharedPreferencesFactory.savedata(activity, "userName", login.data[0].name)
                    startActivity<DBS_MainActivity>()
                    this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                } else {
                    toast(login.message)
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


    //发送登陆请求
    fun sendlogin(mobile: String, captcha: String) {
        showdialog(this, "loadshow")
        loginImpl.getLogin(mobile, captcha, "loginImpl", this)
    }


}

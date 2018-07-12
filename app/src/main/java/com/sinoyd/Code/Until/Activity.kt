package com.sinoyd.Code.Until

import android.app.Activity
import android.content.Context
import android.graphics.Color

import com.xiasuhuei321.loadingdialog.view.LoadingDialog

/**
 * 作者： scj
 * 创建时间： 2017/11/28
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.environmentsz.Kotlin
 *
 * 使用方法
 * 1.先加载库
 * 2.  showdialog("loadshow")       显示dialog
 * 3.  showdialog("loadsuccess")    加载成功显示并消除
 * 4.  showdialog("loadfail")       加载失败显示并消除
 * 5.  showdialog("loaddismiss")    销毁dialog
 */


/**
 * allprojects {
 *               repositories {
 *                                ...
 *                              maven { url "https://jitpack.io" }
 *                            }
 *              }
 *
 * dependencies {
 *               compile 'com.github.ForgetAll:LoadingDialog:v1.0.4'
 *              }
 *
 */

var ld: LoadingDialog? = null

//发送请求dialog提示  Activity
fun showdialog(context: Context,str: String): Unit {
    if (ld == null) {
        ld = LoadingDialog(context)
        ld!!.setSuccessText("加载成功")
                .setFailedText("加载失败")
                .setInterceptBack(false)
                .setLoadSpeed(LoadingDialog.Speed.SPEED_ONE)
                .setRepeatCount(1)
                .setDrawColor(Color.WHITE)
    }
    when (str) {
        "loadshow" -> ld!!.show()
        "loadsuccess" -> {
            ld!!.loadSuccess()
            ld!!.close()
            ld = null
        }
        "loadfail" -> {
            ld!!.loadFailed()
            ld!!.close()
            ld = null
        }
        "loaddismiss" -> {
            ld!!.close()
            ld = null
        }
    }
}


//发送请求dialog提示  Activity
fun Activity.showdialog(dow: String?, str: String): Unit {
    if (ld == null) {
        ld = LoadingDialog(this)
        ld!!.setSuccessText("下载成功")
                .setFailedText("下载失败")
                .setInterceptBack(false)
                .setLoadSpeed(LoadingDialog.Speed.SPEED_ONE)
                .setRepeatCount(1)
                .setDrawColor(Color.WHITE)
    }
    when (str) {
        "loadshow" -> ld!!.show()
        "loadsuccess" -> {
            ld!!.loadSuccess()
            ld!!.close()
            ld = null
        }
        "loadfail" -> {
            ld!!.loadFailed()
            ld!!.close()
            ld = null
        }
        "loaddismiss" -> {
            ld!!.close()
            ld = null
        }
    }
}


fun dp2px(context: Context, dpValue: Float): Int {
    var scale: Float = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}


fun px2dp(context: Context, pxValue: Float): Int {
    var scale: Float = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}




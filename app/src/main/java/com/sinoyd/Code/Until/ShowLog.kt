package com.sinoyd.Code.Until

import android.util.Log
import org.xutils.http.RequestParams
import org.json.JSONException
import org.json.JSONArray
import org.json.JSONObject


/**
 * 作者： scj
 * 创建时间： 2018/3/26
 * 版权：
 * 描述： com.sinoyd.Code.Until     基于xutils的Log显示静态类
 * */

class ShowLog(var name: String) {
    val LINE_SEPARATOR = System.getProperty("line.separator")
    fun show(issuccess: String, params: RequestParams, result: String?) {
        var url = params.uri
        for ((n, item) in params.queryStringParams.withIndex()) {
            Log.i(name, "请求参数: ${item.key}=${item.value}")
            url = if (n == 0) {
                url + "?" + item.key + "=" + item.value
            } else {
                url + "&" + item.key + "=" + item.value
            }
        }
        Log.d(name, "请求地址：$url")
        Log.d(name, "返回状态：$issuccess")
        printJson("返回结果；", result ?: "")
        Log.d(name, "                                                                   ")
    }

    fun printJson(headString: String, msg: String) {
        var message: String
        try {
            if (msg.startsWith("{")) {
                var jsonObject: JSONObject = JSONObject(msg)
                message = jsonObject.toString(4)//最重要的方法,就一行,返回格式化的json字符串,其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                var jsonArray: JSONArray = JSONArray(msg);
                message = jsonArray.toString(4)
            } else {
                message = msg; }
        } catch (e: JSONException) {
            message = msg
        }
        printLine(true)
        message = headString + LINE_SEPARATOR + message
        var lines = message.split(LINE_SEPARATOR)
        for (line in lines) {
            Log.d(name, "║ " + line)
        }
        printLine(false)
    }

    fun printLine(isTop: Boolean) {
        if (isTop) {
            Log.d(name, "╔═══════════════════════════════════════════════════════════════════════════════════════")
        } else {
            Log.d(name, "╚═══════════════════════════════════════════════════════════════════════════════════════")
        }
    }
}
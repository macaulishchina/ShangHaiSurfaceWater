package com.sinoyd.Code.Model

import com.sinoyd.Code.Until.HttpListener
import com.sinoyd.Code.Until.Networkrequestaddress
import org.xutils.common.Callback
import org.xutils.x
import org.xutils.http.RequestParams


/**
 * 作者： scj
 * 创建时间： 2018/1/30
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Model
 *
 * 测试网络请求
 */

interface WeatherModel {
    fun getWeather(lat: String, lon: String, tag: String, listener: HttpListener)
}


class WeatherModelImpl : WeatherModel {
    override fun getWeather(lat: String, lon: String, tag: String, listener: HttpListener) {
        val params = RequestParams(Networkrequestaddress.GetWeather)
        params.addBodyParameter("lat", lat)
        params.addBodyParameter("lon", lon)
        params.addBodyParameter("token", "443847fa1ffd4e69d929807d42c2db1b")
        params.addHeader("Authorization", "APPCODE e300ec13a7ca48f5816698ad36476090")
        x.http().post(params, object : Callback.CommonCallback<String> {
            override fun onSuccess(result: String?) {
                listener.requestSuccess(result!!, tag)
            }

            override fun onCancelled(cex: Callback.CancelledException?) {

            }

            override fun onError(ex: Throwable?, isOnCallback: Boolean) {
                listener.requestFailed(ex!!.message.toString())
            }

            override fun onFinished() {
            }
        })


    }

}
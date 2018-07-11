package com.sinoyd.Code.Until


/**
 * 作者： scj
 * 创建时间： 2018/1/30
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Until
 */

interface HttpListener {
    /***
     * 请求网络成功
     *
     * @param resData
     */
    fun requestSuccess(resData: String, TAG: String)

    /***
     * 请求网络失败
     *
     * @param resData
     */
    fun requestFailed(resData: String)
}
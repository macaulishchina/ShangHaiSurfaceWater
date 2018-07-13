package com.sinoyd.Code.Until

/**
 * 作者： scj
 * 创建时间： 2018/1/30
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Until
 */

object Networkrequestaddress {
    var RELEASE: Boolean = false
    var SERVER_URL = ""
    var HOST_POST = ""

    val Formaladdress: String = "http://192.168.11.178:9527/api/v1/mobile/app"
    val HttpurlFormaladdress: String = "http://192.168.11.178:9527"

    val Testaddress: String = "http://192.168.90.177:8888/api/v1/mobile/app"
    val Httpurltest: String = "http://192.168.90.177:8888"

    init {
        SERVER_URL = if (RELEASE) Formaladdress else Testaddress
        HOST_POST = if (RELEASE) HttpurlFormaladdress else Httpurltest
    }


    /**天气预报接口***/
    val GetWeather = "http://apifreelat.market.alicloudapi.com/whapi/json/aliweather/briefforecast3days"

    /**首页统计数据***/
    val GetFirstPageStatisticData = SERVER_URL + "/statistics/categories"

    /**分类详细统计数据**/
    val GetDetailedClassificationStatistics = SERVER_URL + "/statistics/categories"

    /**站点水质数据**/
    val GetstatisticsByPointId = SERVER_URL + "/statistics/port"

    /**报警信息查询**/
    val GetAlarmInfo = SERVER_URL + "/alarms"

    /**水质周报**/
    val GetWaterStateReport = SERVER_URL + "/reports/week"

    /**获取所有因子**/
    val Getfactors = SERVER_URL + "/factors"

    /**因子浓度(24小时)**/
    val Getconcentration = SERVER_URL + "/statistics/port"

    /**获取附近站点**/
    val Getnearby = SERVER_URL + "/ports/nearby"

    /**站点水质趋势**/
    val Gettrend = SERVER_URL + "/statistics/port"

    /**获取站点类型**/
    val Getcategories = SERVER_URL + "/ports/categories"

    /**点位 GIS 数据**/
    val Getgis = SERVER_URL + "/ports/gis"

    /**按类型id 搜索站点**/
    val Getsearchfortypeid = SERVER_URL + "/ports/search"

    /**获取所有区域**/
    val Getareas = SERVER_URL + "/areas"

    /**关注/取关点位**/
    val Getfavorite = SERVER_URL + "/ports"

    /**搜索点位**/
    val GetSearch = SERVER_URL + "/ports/search"

    /**用户登录**/
    val Getlogin = SERVER_URL + "/auth/login"

    /**获取可选的区域类型**/
    val GetAreasByCategories = SERVER_URL + "/areas/categories"

    /**监控数据结果集**/
    val GetResults = SERVER_URL + "/monitoring/results"

    /**参与评价因子接口**/
    val GetPJFactors = HOST_POST + "/api/v1/MovePortApp/AppPort/PJFactors"

    /**查询监控数据**/
    val GetMonitoringsearch = SERVER_URL + "/monitoring/search"

    /**任务收发**/
    val GetTaskInfo = SERVER_URL + "/AppTaskInfo"    //?pageSize=10&pageNo=1

    /**上传任务**/
    val UpLoadTask = SERVER_URL + "/task/BuildTask"   //?Task=&Id=&Time=&Resperson=&Creater=&CreatDateTime=

    /**用户站点信息**/
    val GroupPointInfo = HOST_POST + "/api/v1/BaseData/PointInfoGroupByOperator"    //?Operator=e2e16546-d663-42e6-9996-9ba632665101

    /**考核统计获取**/
    val GetCheckInfo =     SERVER_URL + "/EvatlSatic"   //?pageSize=10&pageNo=1&stime=2018-7-1&etime=2018-7-10


}
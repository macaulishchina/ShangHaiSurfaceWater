package com.sinoyd.Code.Until

/**
 * 作者： scj
 * 创建时间： 2018/2/3
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.Until
 */

data class Factor(var id: String, var name: String, var category: String)


fun getfactorlist(): MutableList<Factor> {

    return listOf(
            Factor("w21011", "总磷", "常规监测项"),
            Factor("w21001", "总氮", "常规监测项"),
            Factor("w21003", "氨氮", "常规监测项"),
            Factor("w01020", "总有机碳", "常规监测项"),
            Factor("w01019", "高锰酸盐指数", "常规监测项"),
            Factor("w21017", "氟化物", "其他"),
            Factor("w21019", "硫化物", "其他")
    ) as MutableList

}
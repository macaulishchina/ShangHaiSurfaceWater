package com.sinoyd.Code.DataClass

import java.util.*

/**
 * created by macaulish
 * at 2018/7/7
 * in project salmon
 * description
 */
class CheckInfo {
    /**
    {
    "status": "0",
    "data": {
    "total": 12,
    "page": 1,
    "size": 10,
    "list": []
    }
    }
    */
    var status: String = ""
    var data: DataBean = DataBean()

    class DataBean {
        /**
        {
        "total": 12,
        "page": 1,
        "size": 10,
        "list": []
        }
        */
        var total: Int = 0
        var page: Int = 0
        var size: Int = 0
        var list: List<Check> = ArrayList()

        class Check {
            /**
            {
            "InstrumentIntegrator": "涓婃捣闆风",
            "SampRate": "0.00%",
            "CompRate": "--"
            }
             */

            var InstrumentIntegrator: String = ""
            var SampRate: String = ""
            var CompRate: String = ""

        }
    }

}
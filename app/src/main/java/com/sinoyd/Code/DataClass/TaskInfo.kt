package com.sinoyd.Code.DataClass

import java.util.*

/**
 * created by macaulish
 * at 2018/7/7
 * in project salmon
 * description
 */
class TaskInfo {
    /**
     {
      "status": "0",
      "data": {"total": 1,"page": 1,"size": 10,"list": [{"MonitoringPointName": "鍗楁腐澶фˉ","TaskName": "App娴嬭瘯鏁版嵁","Time": "2018/7/11 0:00:00","DealMan": "澶ц櫈鐜繚","TaskStatus": "宸蹭笅鍙\ufffd"}]}
     }
    */
    var status: String = ""
    var data: DataBean = DataBean()

    class DataBean {
        /**
        {
        "total": 1,
        "page": 1,
        "size": 10,
        "list": [{"MonitoringPointName": "鍗楁腐澶фˉ","TaskName": "App娴嬭瘯鏁版嵁","Time": "2018/7/11 0:00:00","DealMan": "澶ц櫈鐜繚","TaskStatus": "宸蹭笅鍙\ufffd"}]
        }
        */
        var total: Int = 0
        var page: Int = 0
        var size: Int = 0
        var list: List<Task> = ArrayList()

        class Task {
            /**
             * {
             * "MonitoringPointName": "鍗楁腐澶фˉ",
             * "TaskName": "App娴嬭瘯鏁版嵁",
             * "Time": "2018/7/11 0:00:00",
             * "DealMan": "澶ц櫈鐜繚",
             * "TaskStatus": "宸蹭笅鍙\ufffd"
             * }
             */

            var MonitoringPointName: String = ""
            var TaskName: String = ""
            var Time: String = ""
            var DealMan: String = ""
            var TaskStatus : String = ""
        }
    }

}
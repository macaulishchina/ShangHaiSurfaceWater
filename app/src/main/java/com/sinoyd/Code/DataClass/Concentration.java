package com.sinoyd.Code.DataClass;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/9
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class Concentration {

    /**
     * status : 0
     * data : [{"time":"2018/2/8 16:00:00","value":"0.335"},{"time":"2018/2/8 18:00:00","value":"0.376"},{"time":"2018/2/8 20:00:00","value":"0.380"},{"time":"2018/2/8 22:00:00","value":"0.343"},{"time":"2018/2/9 0:00:00","value":"0.387"},{"time":"2018/2/9 2:00:00","value":"0.369"},{"time":"2018/2/9 4:00:00","value":"0.370"}]
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * time : 2018/2/8 16:00:00
         * value : 0.335
         */

        private String time;
        private String value;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

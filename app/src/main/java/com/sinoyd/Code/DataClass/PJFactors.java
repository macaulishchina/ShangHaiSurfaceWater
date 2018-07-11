package com.sinoyd.Code.DataClass;

import com.sinoyd.Code.Model.BaseKV;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/3/8
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class PJFactors {

    /**
     * status : 0
     * data : [{"PollutantCode":"w01009","PollutantName":"溶解氧"},{"PollutantCode":"w21011","PollutantName":"总磷"},{"PollutantCode":"w21003","PollutantName":"氨氮"}]
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

    public static class DataBean implements BaseKV {
        /**
         * PollutantCode : w01009
         * PollutantName : 溶解氧
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

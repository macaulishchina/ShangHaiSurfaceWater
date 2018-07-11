package com.sinoyd.Code.DataClass;

import com.sinoyd.Code.Model.BaseKV;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/9
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class Factors {

    /**
     * status : 0
     * data : [{"id":"w98755","name":"叶绿素a(浅层)","unit":"μg/L","category":"生物指标"},{"id":"w21011","name":"总磷","unit":"mg/L","category":"常规监测项"},{"id":"w98756","name":"叶绿素a(深层)","unit":"μg/L","category":"生物指标"}]
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
         * id : w98755
         * name : 叶绿素a(浅层)
         * unit : μg/L
         * category : 生物指标
         */

        private String id;
        private String name;
        private String unit;
        private String category;

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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
}

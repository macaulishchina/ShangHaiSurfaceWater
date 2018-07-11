package com.sinoyd.Code.DataClass;

import com.sinoyd.Code.Model.BaseKV;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/3/7
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class Results {

    /**
     * status : 0
     * data : [{"id":"801b0a15-7a8e-4b98-86d0-afd32620a671","name":"浓度"},{"id":"139b3bcf-057c-493d-ac5d-62f5ebb955a5","name":"酚因子等级"},{"id":"8b503b03-adf2-476a-ad80-f9895e998951","name":"水质"}]
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
         * id : 801b0a15-7a8e-4b98-86d0-afd32620a671
         * name : 浓度
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

package com.sinoyd.Code.DataClass;

import com.sinoyd.Code.Model.BaseKV;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/11
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class Categories {

    /**
     * status : 0
     * data : [{"id":"b64574ae-e3d9-408f-9579-2ac0f95dbcb4","name":"固定站"},{"id":"c28ddef0-ef48-4774-b96e-41d2fbcb6f50","name":"岸边站"},{"id":"2787813d-cb0f-4ea4-854e-eed5878f5259","name":"浮标站"}]
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

        public DataBean(String id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * id : b64574ae-e3d9-408f-9579-2ac0f95dbcb4
         * name : 固定站
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

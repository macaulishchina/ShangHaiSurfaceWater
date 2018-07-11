package com.sinoyd.Code.DataClass;

import com.sinoyd.Code.Model.BaseKV;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/3/6
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class AreasByCategories {

    /**
     * status : 0
     * data : [{"id":"fe9a88a4-1560-4482-a815-a12a358bcc19","name":"区域"},{"id":"b166f488-d0bb-44d5-bbb0-4e6324d9d3c7","name":"片区"},{"id":"1107c6c1-14b9-4fa9-8310-438b6e00beda","name":"流域"},{"id":"b3f84ab2-2206-4326-9b2a-7ca248646972","name":"河道"},{"id":"7c91e408-a911-4527-a8e2-23882b902835","name":"站点类型"},{"id":"166adb7a-d47b-4ca9-b12a-a1f39ae47f17","name":"控制类型"}]
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

    public static class DataBean implements BaseKV{
        /**
         * id : fe9a88a4-1560-4482-a815-a12a358bcc19
         * name : 区域
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

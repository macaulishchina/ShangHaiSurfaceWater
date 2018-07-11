package com.sinoyd.Code.DataClass;

import android.app.Notification;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/3/6
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class Login {

    /**
     * status : 0
     * data : [{"id":"e1b0c9a0-ae71-48c8-9067-6e95b2557bdb","name":"程晨","phone":"18962211096"}]
     */

    private String status;
    private List<DataBean> data;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
         * id : e1b0c9a0-ae71-48c8-9067-6e95b2557bdb
         * name : 程晨
         * phone : 18962211096
         */

        private String id;
        private String name;
        private String phone;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}

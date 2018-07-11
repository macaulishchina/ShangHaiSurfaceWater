package com.sinoyd.Code.DataClass;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/2
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class DetailedClassificationStatistics {

    /**
     * status : 0
     * data : {"total":19,"page":10,"size":1,"list":[{"id":"91","name":"山阳镇","onlineRate":"100%","qualifiedRate":"0%","captureRate":"0%"}]}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 19
         * page : 10
         * size : 1
         * list : [{"id":"91","name":"山阳镇","onlineRate":"100%","qualifiedRate":"0%","captureRate":"0%"}]
         */

        private int total;
        private int page;
        private int size;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * id : 91
             * name : 山阳镇
             * onlineRate : 100%
             * qualifiedRate : 0%
             * captureRate : 0%
             */

            private String id;
            private String name;
            private String onlineRate;
            private String qualifiedRate;
            private String captureRate;

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

            public String getOnlineRate() {
                return onlineRate;
            }

            public void setOnlineRate(String onlineRate) {
                this.onlineRate = onlineRate;
            }

            public String getQualifiedRate() {
                return qualifiedRate;
            }

            public void setQualifiedRate(String qualifiedRate) {
                this.qualifiedRate = qualifiedRate;
            }

            public String getCaptureRate() {
                return captureRate;
            }

            public void setCaptureRate(String captureRate) {
                this.captureRate = captureRate;
            }
        }
    }
}

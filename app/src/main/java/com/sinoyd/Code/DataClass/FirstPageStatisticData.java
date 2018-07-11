package com.sinoyd.Code.DataClass;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/1
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class FirstPageStatisticData {

    /**
     * status : 0
     * data : {"total":32,"page":1,"size":100,"list":[{"id":"48C39322-8DAE-46A5-B116-11E4266A7B77","name":"普陀","onlineCount":"0","qualifiedCount":0,"totalCount":"7","captureCount":0,"expectDataCount":0},{"id":"45959C61-52EF-4AEC-9C7F-52B4390A34B4","name":"长宁","onlineCount":"0","qualifiedCount":0,"totalCount":"5","captureCount":0,"expectDataCount":0},{"id":"210FF0CD-CE8D-4701-A446-8358F9BE3F24","name":"松江","onlineCount":"0","qualifiedCount":0,"totalCount":"10","captureCount":0,"expectDataCount":0},{"id":"14852B88-D633-4D15-A80E-13399D1F204B","name":"嘉定","onlineCount":"0","qualifiedCount":0,"totalCount":"10","captureCount":0,"expectDataCount":0},{"id":"0AC26E50-960B-43C7-B996-868B7B989E57","name":"浦东新区","onlineCount":"0","qualifiedCount":0,"totalCount":"20","captureCount":0,"expectDataCount":0},{"id":"00F7AA71-259E-4A81-80C4-BFBAD36B5911","name":"徐汇","onlineCount":"0","qualifiedCount":0,"totalCount":"10","captureCount":0,"expectDataCount":0}]}
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
         * total : 32
         * page : 1
         * size : 100
         * list : [{"id":"48C39322-8DAE-46A5-B116-11E4266A7B77","name":"普陀","onlineCount":"0","qualifiedCount":0,"totalCount":"7","captureCount":0,"expectDataCount":0},{"id":"45959C61-52EF-4AEC-9C7F-52B4390A34B4","name":"长宁","onlineCount":"0","qualifiedCount":0,"totalCount":"5","captureCount":0,"expectDataCount":0},{"id":"210FF0CD-CE8D-4701-A446-8358F9BE3F24","name":"松江","onlineCount":"0","qualifiedCount":0,"totalCount":"10","captureCount":0,"expectDataCount":0},{"id":"14852B88-D633-4D15-A80E-13399D1F204B","name":"嘉定","onlineCount":"0","qualifiedCount":0,"totalCount":"10","captureCount":0,"expectDataCount":0},{"id":"0AC26E50-960B-43C7-B996-868B7B989E57","name":"浦东新区","onlineCount":"0","qualifiedCount":0,"totalCount":"20","captureCount":0,"expectDataCount":0},{"id":"00F7AA71-259E-4A81-80C4-BFBAD36B5911","name":"徐汇","onlineCount":"0","qualifiedCount":0,"totalCount":"10","captureCount":0,"expectDataCount":0}]
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

        public static class ListBean {
            /**
             * id : 48C39322-8DAE-46A5-B116-11E4266A7B77
             * name : 普陀
             * onlineCount : 0
             * qualifiedCount : 0
             * totalCount : 7
             * captureCount : 0
             * expectDataCount : 0
             */

            private String id;
            private String name;
            private String onlineCount;
            private int qualifiedCount;
            private String totalCount;
            private int captureCount;
            private int expectDataCount;

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

            public String getOnlineCount() {
                return onlineCount;
            }

            public void setOnlineCount(String onlineCount) {
                this.onlineCount = onlineCount;
            }

            public int getQualifiedCount() {
                return qualifiedCount;
            }

            public void setQualifiedCount(int qualifiedCount) {
                this.qualifiedCount = qualifiedCount;
            }

            public String getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(String totalCount) {
                this.totalCount = totalCount;
            }

            public int getCaptureCount() {
                return captureCount;
            }

            public void setCaptureCount(int captureCount) {
                this.captureCount = captureCount;
            }

            public int getExpectDataCount() {
                return expectDataCount;
            }

            public void setExpectDataCount(int expectDataCount) {
                this.expectDataCount = expectDataCount;
            }
        }
    }
}

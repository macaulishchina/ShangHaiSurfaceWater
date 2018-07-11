package com.sinoyd.Code.DataClass;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/5
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class WaterStateReport {

    /**
     * status : 0
     * data : {"total":2,"page":1,"size":20,"list":[{"name":"地表水环境质量监控周报第18期(2017年)","url":"http://192.168.90.188/report/周报/地表水环境质量监控周报第18期(2017年).docx"},{"name":"地表水环境质量监控周报第16期(2017年)","url":"http://192.168.90.188/report/周报/地表水环境质量监控周报第16期(2017年).docx"}]}
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
         * total : 2
         * page : 1
         * size : 20
         * list : [{"name":"地表水环境质量监控周报第18期(2017年)","url":"http://192.168.90.188/report/周报/地表水环境质量监控周报第18期(2017年).docx"},{"name":"地表水环境质量监控周报第16期(2017年)","url":"http://192.168.90.188/report/周报/地表水环境质量监控周报第16期(2017年).docx"}]
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
             * name : 地表水环境质量监控周报第18期(2017年)
             * url : http://192.168.90.188/report/周报/地表水环境质量监控周报第18期(2017年).docx
             */

            private String name;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}

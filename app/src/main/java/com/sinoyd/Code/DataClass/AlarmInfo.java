package com.sinoyd.Code.DataClass;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/5
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class AlarmInfo {

    /**
     * status : 0
     * data : {"total":22,"page":1,"size":10,"list":[{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"因子缺失","content":"01月02日14时，南港大桥点位浊度数据缺失"},{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"因子缺失","content":"01月02日14时，南港大桥点位电导率数据缺失"},{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"断线报警","content":"01月02日14时，南港大桥点位断线"},{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"因子缺失","content":"01月02日14时，南港大桥点位水温数据缺失"},{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"因子缺失","content":"01月02日14时，南港大桥点位ph值数据缺失"},{"port":"急水港","time":"2018/1/2 14:00:00","type":"断线报警","content":"01月02日14时，急水港点位断线"},{"port":"南港大桥","time":"2018/1/19 6:00:00","type":"因子缺失","content":"01月19日06时，南港大桥点位水温数据缺失"},{"port":"南港大桥","time":"2018/1/19 6:00:00","type":"因子缺失","content":"01月19日06时，南港大桥点位ph值数据缺失"},{"port":"南港大桥","time":"2018/1/19 6:00:00","type":"因子缺失","content":"01月19日06时，南港大桥点位浊度数据缺失"},{"port":"南港大桥","time":"2018/1/19 6:00:00","type":"因子缺失","content":"01月19日06时，南港大桥点位电导率数据缺失"}]}
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
         * total : 22
         * page : 1
         * size : 10
         * list : [{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"因子缺失","content":"01月02日14时，南港大桥点位浊度数据缺失"},{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"因子缺失","content":"01月02日14时，南港大桥点位电导率数据缺失"},{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"断线报警","content":"01月02日14时，南港大桥点位断线"},{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"因子缺失","content":"01月02日14时，南港大桥点位水温数据缺失"},{"port":"南港大桥","time":"2018/1/2 14:00:00","type":"因子缺失","content":"01月02日14时，南港大桥点位ph值数据缺失"},{"port":"急水港","time":"2018/1/2 14:00:00","type":"断线报警","content":"01月02日14时，急水港点位断线"},{"port":"南港大桥","time":"2018/1/19 6:00:00","type":"因子缺失","content":"01月19日06时，南港大桥点位水温数据缺失"},{"port":"南港大桥","time":"2018/1/19 6:00:00","type":"因子缺失","content":"01月19日06时，南港大桥点位ph值数据缺失"},{"port":"南港大桥","time":"2018/1/19 6:00:00","type":"因子缺失","content":"01月19日06时，南港大桥点位浊度数据缺失"},{"port":"南港大桥","time":"2018/1/19 6:00:00","type":"因子缺失","content":"01月19日06时，南港大桥点位电导率数据缺失"}]
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
             * port : 南港大桥
             * time : 2018/1/2 14:00:00
             * type : 因子缺失
             * content : 01月02日14时，南港大桥点位浊度数据缺失
             */

            private String port;
            private String time;
            private String type;
            private String content;

            public String getPort() {
                return port;
            }

            public void setPort(String port) {
                this.port = port;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}

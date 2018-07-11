package com.sinoyd.Code.DataClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/3/12
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class Monitoringsearch {

    /**
     * status : 0
     * data : {"total":185,"page":1,"size":10,"list:":[{"portName":"南港大桥","time":"2017/11/20 10:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.78"}]},{"portName":"南港大桥","time":"2017/11/20 8:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.77"}]},{"portName":"南港大桥","time":"2017/11/20 6:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.78"}]},{"portName":"南港大桥","time":"2017/11/20 4:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.76"}]},{"portName":"南港大桥","time":"2017/11/20 2:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.79"}]},{"portName":"南港大桥","time":"2017/11/20 0:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.81"}]},{"portName":"南港大桥","time":"2017/11/19 22:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.86"}]},{"portName":"南港大桥","time":"2017/11/19 20:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.86"}]},{"portName":"南港大桥","time":"2017/11/19 18:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.85"}]},{"portName":"南港大桥","time":"2017/11/19 16:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.75"}]}]}
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

    // FIXME generate failure  field _$List111
    public static class DataBean {
        /**
         * total : 185
         * page : 1
         * size : 10
         * list: : [{"portName":"南港大桥","time":"2017/11/20 10:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.78"}]},{"portName":"南港大桥","time":"2017/11/20 8:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.77"}]},{"portName":"南港大桥","time":"2017/11/20 6:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.78"}]},{"portName":"南港大桥","time":"2017/11/20 4:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.76"}]},{"portName":"南港大桥","time":"2017/11/20 2:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.79"}]},{"portName":"南港大桥","time":"2017/11/20 0:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.81"}]},{"portName":"南港大桥","time":"2017/11/19 22:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.86"}]},{"portName":"南港大桥","time":"2017/11/19 20:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.86"}]},{"portName":"南港大桥","time":"2017/11/19 18:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.85"}]},{"portName":"南港大桥","time":"2017/11/19 16:00:00","datas":[{"factor":"ph值","unit":"无量纲","value":"7.75"}]}]
         */

        private int total;
        private int page;
        private int size;
        @SerializedName("list")
        public List<DatasBeann> listdata;

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

        public List<DatasBeann> getListdata() {
            return listdata;
        }

        public void setListdata(List<DatasBeann> listdata) {
            this.listdata = listdata;
        }

        public static class DatasBeann {

            /**
             * portName : 南港大桥
             * time : 2017/11/20 10:00:00
             * datas : [{"factor":"ph值","unit":"无量纲","value":"7.78"}]
             */

            private String portName;
            private String time;
            private List<DatasBeannn> datas;

            public String getPortName() {
                return portName;
            }

            public void setPortName(String portName) {
                this.portName = portName;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public List<DatasBeannn> getDatas() {
                return datas;
            }

            public void setDatas(List<DatasBeannn> datas) {
                this.datas = datas;
            }

            public static class DatasBeannn {
                /**
                 * factor : ph值
                 * unit : 无量纲
                 * value : 7.78
                 */

                private String factor;
                private String unit;
                private String value;

                public String getFactor() {
                    return factor;
                }

                public void setFactor(String factor) {
                    this.factor = factor;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}

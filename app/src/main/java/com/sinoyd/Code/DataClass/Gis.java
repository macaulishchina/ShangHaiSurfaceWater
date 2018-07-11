package com.sinoyd.Code.DataClass;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/12
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class Gis {

    /**
     * status : 0
     * data : [{"id":15,"name":"淀峰","grade":"","qualifiedStatus":"","latitude":31.0897,"longitude":120.9792,"factors":[{"name":"水温","unit":"℃","value":"13.7"},{"name":"浊度","unit":"NTU","value":"31.4"},{"name":"电导率","unit":"μS/cm","value":"577.6"},{"name":"ph值","unit":"无量纲","value":"7.86"}]},{"id":22,"name":"苏州河梦清园（浮标）","grade":"II","qualifiedStatus":"正常","latitude":31.2533,"longitude":121.4372,"factors":[{"name":"总磷","unit":"mg/L","value":"0.102"},{"name":"氨氮","unit":"mg/L","value":"3.531"},{"name":"ph值","unit":"无量纲","value":"7.33"}]},{"id":24,"name":"金泽水文站","grade":"II","qualifiedStatus":"正常","latitude":31.0184,"longitude":120.9013,"factors":[{"name":"间对二甲苯","unit":"ppb","value":"1.3270"},{"name":"1,1-二氯乙烷","unit":"ppb","value":"0.0000"},{"name":"1,1-二氯乙烯","unit":"ppb","value":"55.1500"},{"name":"1,1-二氯丙烯","unit":"ppb","value":"0.0000"},{"name":"1,1,2-三氯乙烷","unit":"ppb","value":"0.0000"},{"name":"1,1,2,2-四氯乙烷","unit":"ppb","value":"0.2160"},{"name":"甲苯","unit":"ppb","value":"83.4900"},{"name":"反-1,2-二氯乙烯","unit":"ppb","value":"0.0000"},{"name":"苯乙烯","unit":"ppb","value":"0.8200"},{"name":"反-1,3-二氯丙烯","unit":"ppb","value":"0.9710"},{"name":"三氯乙烯","unit":"ppb","value":"0.0000"},{"name":"正丙苯","unit":"ppb","value":"0.1050"},{"name":"仲丁苯","unit":"ppb","value":"0.1100"},{"name":"六氯丁二烯","unit":"ppb","value":"0.0000"},{"name":"叔丁苯","unit":"ppb","value":"0.0160"},{"name":"四氯乙烯","unit":"ppb","value":"0.0000"},{"name":"正丁苯","unit":"ppb","value":"0.0000"},{"name":"顺-1,2-二氯乙烯","unit":"ppb","value":"0.0000"},{"name":"异丙苯","unit":"ppb","value":"0.0000"},{"name":"邻二甲苯","unit":"ppb","value":"0.5440"},{"name":"二氯甲烷","unit":"ppb","value":"135.0500"},{"name":"萘","unit":"ppb","value":"0.0000"},{"name":"对异丙基甲苯","unit":"ppb","value":"0.0000"},{"name":"溴苯","unit":"ppb","value":"0.0180"},{"name":"顺-1,3-二氯丙烯","unit":"ppb","value":"0.0000"},{"name":"二溴一氯甲烷","unit":"ppb","value":"0.0000"},{"name":"二溴甲烷","unit":"ppb","value":"0.0000"},{"name":"氯苯","unit":"ppb","value":"9302.0200"},{"name":"乙苯","unit":"ppb","value":"0.4380"},{"name":"溴氯甲烷","unit":"ppb","value":"0.0000"},{"name":"溴二氯甲烷","unit":"ppb","value":"0.0000"},{"name":"溴仿","unit":"ppb","value":"0.0000"},{"name":"四氯化碳","unit":"ppb","value":"0.0000"},{"name":"2-氯甲苯","unit":"ppb","value":"0.0000"},{"name":"氯仿","unit":"ppb","value":"0.0000"},{"name":"1,2-二氯丙烷","unit":"ppb","value":"0.0000"},{"name":"2,2-二氯丙烷","unit":"ppb","value":"0.0000"},{"name":"4-氯甲苯","unit":"ppb","value":"0.4130"},{"name":"苯","unit":"ppb","value":"13.8500"},{"name":"1,2,4-三甲苯","unit":"ppb","value":"0.0310"},{"name":"1,3-二氯苯","unit":"ppb","value":"0.0000"},{"name":"1,2,3-三氯丙烷","unit":"ppb","value":"0.0000"},{"name":"1,2,4-三氯苯","unit":"ppb","value":"0.0000"},{"name":"1,2-二氯苯","unit":"ppb","value":"0.0000"},{"name":"1,3-二氯丙烷","unit":"ppb","value":"0.0000"},{"name":"1,3,5-三甲苯","unit":"ppb","value":"0.2850"},{"name":"1,4-二氯苯","unit":"ppb","value":"0.0000"},{"name":"1,2-二溴-3-氯丙烷","unit":"ppb","value":"0.1960"},{"name":"1,2-二溴乙烷","unit":"ppb","value":"0.0000"},{"name":"1,2-二氯乙烷","unit":"ppb","value":"0.0000"},{"name":"1,1,1-三氯乙烷","unit":"ppb","value":"7.4700"},{"name":"1,1,1,2-四氯乙烷","unit":"ppb","value":"0.0000"},{"name":"1,2,3-三氯苯","unit":"ppb","value":"0.0000"}]},{"id":45,"name":"南港大桥","grade":"II","qualifiedStatus":"正常","latitude":31.117,"longitude":121.059,"factors":[{"name":"总磷","unit":"mg/L","value":"0.116"}]},{"id":49,"name":"龙吴路","grade":"III","qualifiedStatus":"正常","latitude":31.1305,"longitude":131.4551,"factors":[{"name":"水温","unit":"℃","value":"6.5"},{"name":"溶解氧","unit":"mg/L","value":"7.45"},{"name":"石油类","unit":"mg/L","value":"0.0280"},{"name":"挥发酚","unit":"mg/L","value":"0.0010"},{"name":"氨氮","unit":"mg/L","value":"2.110"},{"name":"总磷","unit":"mg/L","value":"0.370"},{"name":"苯乙烯","unit":"ppb","value":"0.0000"},{"name":"三氯乙烯","unit":"ppb","value":"0.0000"},{"name":"甲苯","unit":"ppb","value":"0.0004"},{"name":"电导率","unit":"μS/cm","value":"1206.4"},{"name":"ph值","unit":"无量纲","value":"7.52"},{"name":"乙苯","unit":"ppb","value":"0.0000"},{"name":"顺-1,2-二氯乙烯","unit":"ppb","value":"0.0000"},{"name":"邻二甲苯","unit":"ppb","value":"0.0000"},{"name":"二氯甲烷","unit":"ppb","value":"0.0000"},{"name":"异丙苯","unit":"ppb","value":"0.0000"},{"name":"四氯乙烯","unit":"ppb","value":"0.0000"},{"name":"1,2-二氯苯","unit":"ppb","value":"0.0000"},{"name":"1,4-二氯苯","unit":"ppb","value":"0.0000"},{"name":"1,2-二氯丙烷","unit":"ppb","value":"0.0000"},{"name":"苯","unit":"ppb","value":"0.0000"},{"name":"氯仿","unit":"ppb","value":"0.0000"},{"name":"氯苯","unit":"ppb","value":"0.0000"},{"name":"总有机碳","unit":"mg/L","value":"3.44"},{"name":"高锰酸盐指数","unit":"mg/L","value":"4.57"},{"name":"总氮","unit":"mg/L","value":"5.102"},{"name":"浊度","unit":"NTU","value":"120.7"},{"name":"间对二甲苯","unit":"ppb","value":"0.0000"},{"name":"1,2-二氯乙烷","unit":"ppb","value":"0.0000"}]},{"id":68,"name":"白泾河","grade":"III","qualifiedStatus":"正常","latitude":31.0064,"longitude":121.4874,"factors":[{"name":"水温","unit":"℃","value":"9.2"},{"name":"瞬时流量","unit":"m3/s","value":"0.171"},{"name":"石油类","unit":"mg/L","value":"0.2360"},{"name":"氨氮","unit":"mg/L","value":"2.210"},{"name":"总磷","unit":"mg/L","value":"0.179"},{"name":"溶解氧","unit":"mg/L","value":"12.09"},{"name":"ph值","unit":"无量纲","value":"8.27"},{"name":"邻二甲苯","unit":"ppb","value":"0.0000"},{"name":"甲苯","unit":"ppb","value":"0.1178"},{"name":"反-1,2-二氯乙烯","unit":"ppb","value":"0.1000"},{"name":"三氯乙烯","unit":"ppb","value":"0.0000"},{"name":"四氯乙烯","unit":"ppb","value":"0.0000"},{"name":"电导率","unit":"μS/cm","value":"1231.1"},{"name":"苯","unit":"ppb","value":"0.0000"},{"name":"顺-1,2-二氯乙烯","unit":"ppb","value":"0.7938"},{"name":"二氯甲烷","unit":"ppb","value":"0.2342"},{"name":"乙苯","unit":"ppb","value":"0.0000"},{"name":"异丙苯","unit":"ppb","value":"0.0000"},{"name":"苯乙烯","unit":"ppb","value":"0.0000"},{"name":"1,2-二氯苯","unit":"ppb","value":"0.0000"},{"name":"1,2-二氯乙烷","unit":"ppb","value":"0.9682"},{"name":"1,2-二氯丙烷","unit":"ppb","value":"0.0000"},{"name":"1,4-二氯苯","unit":"ppb","value":"0.0000"},{"name":"氯苯","unit":"ppb","value":"0.0000"},{"name":"氯仿","unit":"ppb","value":"0.0000"},{"name":"挥发酚","unit":"mg/L","value":"0.7437"},{"name":"总氮","unit":"mg/L","value":"7.440"},{"name":"总有机碳","unit":"mg/L","value":"7.20"},{"name":"高锰酸盐指数","unit":"mg/L","value":"6.45"},{"name":"浊度","unit":"NTU","value":"20.6"},{"name":"间对二甲苯","unit":"ppb","value":"0.7284"}]},{"id":76,"name":"金泽水源湖","grade":"III","qualifiedStatus":"正常","latitude":31.0266,"longitude":120.9583,"factors":[{"name":"生物毒性","unit":"无量纲","value":"1"}]},{"id":100,"name":"城桥水厂","grade":"II","qualifiedStatus":"正常","latitude":31.6475,"longitude":121.4219,"factors":[{"name":"水温","unit":"℃","value":"9.7"},{"name":"瞬时流量","unit":"m3/s","value":"-0.100"},{"name":"高锰酸盐指数","unit":"mg/L","value":"2.25"},{"name":"氨氮","unit":"mg/L","value":"0.208"},{"name":"氯化物","unit":"mg/L","value":"448.70"},{"name":"溶解氧","unit":"mg/L","value":"10.84"},{"name":"总氮","unit":"mg/L","value":"2.378"},{"name":"总磷","unit":"mg/L","value":"0.173"},{"name":"ORP","unit":"mv","value":"392.80"},{"name":"浊度","unit":"NTU","value":"411.0"},{"name":"电导率","unit":"μS/cm","value":"41.2"},{"name":"ph值","unit":"无量纲","value":"5.58"}]}]
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

    public static class DataBean {
        /**
         * id : 15
         * name : 淀峰
         * grade :
         * qualifiedStatus :
         * latitude : 31.0897
         * longitude : 120.9792
         * factors : [{"name":"水温","unit":"℃","value":"13.7"},{"name":"浊度","unit":"NTU","value":"31.4"},{"name":"电导率","unit":"μS/cm","value":"577.6"},{"name":"ph值","unit":"无量纲","value":"7.86"}]
         */

        private int id;
        private String name;
        private String grade;
        private String qualifiedStatus;
        private double latitude;
        private double longitude;
        private List<FactorsBean> factors;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getQualifiedStatus() {
            return qualifiedStatus;
        }

        public void setQualifiedStatus(String qualifiedStatus) {
            this.qualifiedStatus = qualifiedStatus;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public List<FactorsBean> getFactors() {
            return factors;
        }

        public void setFactors(List<FactorsBean> factors) {
            this.factors = factors;
        }

        public static class FactorsBean {
            /**
             * name : 水温
             * unit : ℃
             * value : 13.7
             */

            private String name;
            private String unit;
            private String value;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}

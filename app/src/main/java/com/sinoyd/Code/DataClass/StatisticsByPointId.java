package com.sinoyd.Code.DataClass;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/5
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class StatisticsByPointId {

    /**
     * status : 0
     * data : {"timestamp":"2017/11/20 10:00:00","grade":"","targetGrade":"III","qualifiedStatus":"","polluteValue":"","primaryPollute":"","primaryPolluteValue":"","factors":[{"id":"w01010","name":"水温","value":"13.5","grade":""},{"id":"w01026","name":"水位","value":"2.114","grade":""},{"id":"w01028","name":"瞬时流速","value":"0","grade":""},{"id":"w01029","name":"瞬时流量","value":"-5.802","grade":""},{"id":"w01009","name":"溶解氧","value":"7.89","grade":""},{"id":"w21011","name":"总磷","value":"0.092","grade":""},{"id":"w21001","name":"总氮","value":"1.658","grade":""},{"id":"w21003","name":"氨氮","value":"0.158","grade":""},{"id":"w22003","name":"水中油","value":"0.044","grade":""},{"id":"w99901","name":"水中有机物(UV COD)","value":"4.69","grade":""},{"id":"w99903","name":"ORP","value":"427.37","grade":""},{"id":"w01003","name":"浊度","value":"20.7","grade":""},{"id":"w01014","name":"电导率","value":"579.8","grade":""},{"id":"w01001","name":"ph值","value":"7.78","grade":""}]}
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
         * timestamp : 2017/11/20 10:00:00
         * grade :
         * targetGrade : III
         * qualifiedStatus :
         * polluteValue :
         * primaryPollute :
         * primaryPolluteValue :
         * factors : [{"id":"w01010","name":"水温","value":"13.5","grade":""},{"id":"w01026","name":"水位","value":"2.114","grade":""},{"id":"w01028","name":"瞬时流速","value":"0","grade":""},{"id":"w01029","name":"瞬时流量","value":"-5.802","grade":""},{"id":"w01009","name":"溶解氧","value":"7.89","grade":""},{"id":"w21011","name":"总磷","value":"0.092","grade":""},{"id":"w21001","name":"总氮","value":"1.658","grade":""},{"id":"w21003","name":"氨氮","value":"0.158","grade":""},{"id":"w22003","name":"水中油","value":"0.044","grade":""},{"id":"w99901","name":"水中有机物(UV COD)","value":"4.69","grade":""},{"id":"w99903","name":"ORP","value":"427.37","grade":""},{"id":"w01003","name":"浊度","value":"20.7","grade":""},{"id":"w01014","name":"电导率","value":"579.8","grade":""},{"id":"w01001","name":"ph值","value":"7.78","grade":""}]
         */

        private String timestamp;
        private String grade;
        private String targetGrade;
        private String qualifiedStatus;
        private String polluteValue;
        private String primaryPollute;
        private String primaryPolluteValue;
        private List<FactorsBean> factors;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getTargetGrade() {
            return targetGrade;
        }

        public void setTargetGrade(String targetGrade) {
            this.targetGrade = targetGrade;
        }

        public String getQualifiedStatus() {
            return qualifiedStatus;
        }

        public void setQualifiedStatus(String qualifiedStatus) {
            this.qualifiedStatus = qualifiedStatus;
        }

        public String getPolluteValue() {
            return polluteValue;
        }

        public void setPolluteValue(String polluteValue) {
            this.polluteValue = polluteValue;
        }

        public String getPrimaryPollute() {
            return primaryPollute;
        }

        public void setPrimaryPollute(String primaryPollute) {
            this.primaryPollute = primaryPollute;
        }

        public String getPrimaryPolluteValue() {
            return primaryPolluteValue;
        }

        public void setPrimaryPolluteValue(String primaryPolluteValue) {
            this.primaryPolluteValue = primaryPolluteValue;
        }

        public List<FactorsBean> getFactors() {
            return factors;
        }

        public void setFactors(List<FactorsBean> factors) {
            this.factors = factors;
        }

        public static class FactorsBean {
            /**
             * id : w01010
             * name : 水温
             * value : 13.5
             * grade :
             */

            private String id;
            private String name;
            private String value;
            private String grade;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }
        }
    }
}

package com.sinoyd.Code.DataClass;

import java.util.List;

/**
 * 作者： scj
 * 创建时间： 2018/2/11
 * 版权： 江苏远大信息股份有限公司
 * 描述： com.sinoyd.Code.DataClass
 */


public class Nearby {

    /**
     * status : 0
     * data : [{"id":49,"name":"龙吴路","type":"岸边站","area":"徐汇","favorite":"","latitude":31.1305,"longitude":131.4551}]
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
         * id : 49
         * name : 龙吴路
         * type : 岸边站
         * area : 徐汇
         * favorite :
         * latitude : 31.1305
         * longitude : 131.4551
         */

        private int id;
        private String name;
        private String type;
        private String area;
        private String favorite;
        private double latitude;
        private double longitude;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
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
    }
}

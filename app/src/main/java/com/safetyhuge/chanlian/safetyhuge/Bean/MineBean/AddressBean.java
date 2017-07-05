package com.safetyhuge.chanlian.safetyhuge.Bean.MineBean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/25 0025 21:31
 * 邮箱：995696826@qq.com
 */

public class AddressBean implements Serializable {
    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"y_id":"6","y_uid":"19","y_name":"深信服老司机","y_phone":"12365478901","y_address":"山东省青岛市崂山区中韩街道海尔路65号招商银行财富大厦","y_addresses":"606室","y_youbian":"0","is_mr":"0","lng":"120.458144","lat":"36.098908"},{"y_id":"9","y_uid":"19","y_name":"来咯哦哦哦","y_phone":"55555569","y_address":"山东省青岛市崂山区中韩街道苗岭路裕龙科技公寓","y_addresses":"","y_youbian":"0","is_mr":"0","lng":"120.461878","lat":"36.099472"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * y_id : 6
         * y_uid : 19
         * y_name : 深信服老司机
         * y_phone : 12365478901
         * y_address : 山东省青岛市崂山区中韩街道海尔路65号招商银行财富大厦
         * y_addresses : 606室
         * y_youbian : 0
         * is_mr : 0
         * lng : 120.458144
         * lat : 36.098908
         */

        private String y_id;
        private String y_uid;
        private String y_name;
        private String y_phone;
        private String y_address;
        private String y_addresses;
        private String y_youbian;
        private String is_mr;
        private String lng;
        private String lat;

        public String getY_id() {
            return y_id;
        }

        public void setY_id(String y_id) {
            this.y_id = y_id;
        }

        public String getY_uid() {
            return y_uid;
        }

        public void setY_uid(String y_uid) {
            this.y_uid = y_uid;
        }

        public String getY_name() {
            return y_name;
        }

        public void setY_name(String y_name) {
            this.y_name = y_name;
        }

        public String getY_phone() {
            return y_phone;
        }

        public void setY_phone(String y_phone) {
            this.y_phone = y_phone;
        }

        public String getY_address() {
            return y_address;
        }

        public void setY_address(String y_address) {
            this.y_address = y_address;
        }

        public String getY_addresses() {
            return y_addresses;
        }

        public void setY_addresses(String y_addresses) {
            this.y_addresses = y_addresses;
        }

        public String getY_youbian() {
            return y_youbian;
        }

        public void setY_youbian(String y_youbian) {
            this.y_youbian = y_youbian;
        }

        public String getIs_mr() {
            return is_mr;
        }

        public void setIs_mr(String is_mr) {
            this.is_mr = is_mr;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
}

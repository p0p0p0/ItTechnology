package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/5/18 0018 14:59
 * 邮箱：995696826@qq.com
 */

public class HelpBean implements Serializable {

    /**
     * code : 888
     * secess : true
     * data : [{"help_id":"34","help_type":"","uid":"84","username":"13668844170","phone":"13668844170","indus_id":"29","indus_pid":"3","title":"80000","price":"800.00","help_time":"10","unit_time":"小时","pic":"","content":"9000","on_time":"1497272870","help_status":"3","help_user":"20","help_name":"亿赛通","lng":"120.461808","lat":"36.099567","province_name":"山东省","city_name":"青岛市","area_name":"崂山区","seller_pic":"data/avatar/000/00/00/84_avatar_big.jpg","buyer_pic":"data/avatar/000/00/00/20_avatar_big.jpg"}]
     */

    private String code;
    private String secess;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSecess() {
        return secess;
    }

    public void setSecess(String secess) {
        this.secess = secess;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * help_id : 34
         * help_type :
         * uid : 84
         * username : 13668844170
         * phone : 13668844170
         * indus_id : 29
         * indus_pid : 3
         * title : 80000
         * price : 800.00
         * help_time : 10
         * unit_time : 小时
         * pic :
         * content : 9000
         * on_time : 1497272870
         * help_status : 3
         * help_user : 20
         * help_name : 亿赛通
         * lng : 120.461808
         * lat : 36.099567
         * province_name : 山东省
         * city_name : 青岛市
         * area_name : 崂山区
         * seller_pic : data/avatar/000/00/00/84_avatar_big.jpg
         * buyer_pic : data/avatar/000/00/00/20_avatar_big.jpg
         */

        private String help_id;
        private String help_type;
        private String uid;
        private String username;
        private String phone;
        private String indus_id;
        private String indus_pid;
        private String title;
        private String price;
        private String help_time;
        private String unit_time;
        private String pic;
        private String content;
        private String on_time;
        private String help_status;
        private String help_user;
        private String help_name;
        private String lng;
        private String lat;
        private String province_name;
        private String city_name;
        private String area_name;
        private String seller_pic;
        private String buyer_pic;

        public String getHelp_id() {
            return help_id;
        }

        public void setHelp_id(String help_id) {
            this.help_id = help_id;
        }

        public String getHelp_type() {
            return help_type;
        }

        public void setHelp_type(String help_type) {
            this.help_type = help_type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIndus_id() {
            return indus_id;
        }

        public void setIndus_id(String indus_id) {
            this.indus_id = indus_id;
        }

        public String getIndus_pid() {
            return indus_pid;
        }

        public void setIndus_pid(String indus_pid) {
            this.indus_pid = indus_pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getHelp_time() {
            return help_time;
        }

        public void setHelp_time(String help_time) {
            this.help_time = help_time;
        }

        public String getUnit_time() {
            return unit_time;
        }

        public void setUnit_time(String unit_time) {
            this.unit_time = unit_time;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOn_time() {
            return on_time;
        }

        public void setOn_time(String on_time) {
            this.on_time = on_time;
        }

        public String getHelp_status() {
            return help_status;
        }

        public void setHelp_status(String help_status) {
            this.help_status = help_status;
        }

        public String getHelp_user() {
            return help_user;
        }

        public void setHelp_user(String help_user) {
            this.help_user = help_user;
        }

        public String getHelp_name() {
            return help_name;
        }

        public void setHelp_name(String help_name) {
            this.help_name = help_name;
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

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getSeller_pic() {
            return seller_pic;
        }

        public void setSeller_pic(String seller_pic) {
            this.seller_pic = seller_pic;
        }

        public String getBuyer_pic() {
            return buyer_pic;
        }

        public void setBuyer_pic(String buyer_pic) {
            this.buyer_pic = buyer_pic;
        }
    }
}

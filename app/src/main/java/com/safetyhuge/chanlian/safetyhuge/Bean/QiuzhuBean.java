package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/6/22 0022 10:01
 * 邮箱：995696826@qq.com
 */

public class QiuzhuBean implements Serializable {
    /**
     * code : 888
     * secess : true
     * data : [{"help_id":"37","help_type":"","uid":"84","username":"13668844170","phone":"13668844170","indus_id":"29","indus_pid":"2","title":"在于我们","price":"100.00","help_time":"10","unit_time":"小时","pic":"","content":"我们","on_time":"1497335254","help_status":"2","help_user":"","help_name":"","lng":"120.461881","lat":"36.099450","province_name":"山东省","city_name":"青岛市","area_name":"崂山区","distance":20,"indus_pname":null,"indus_name":null,"show_pic":[""],"user_pic":"data/avatar/000/00/00/84_avatar_big.jpg"}]
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
         * help_id : 37
         * help_type :
         * uid : 84
         * username : 13668844170
         * phone : 13668844170
         * indus_id : 29
         * indus_pid : 2
         * title : 在于我们
         * price : 100.00
         * help_time : 10
         * unit_time : 小时
         * pic :
         * content : 我们
         * on_time : 1497335254
         * help_status : 2
         * help_user :
         * help_name :
         * lng : 120.461881
         * lat : 36.099450
         * province_name : 山东省
         * city_name : 青岛市
         * area_name : 崂山区
         * distance : 20
         * indus_pname : null
         * indus_name : null
         * show_pic : [""]
         * user_pic : data/avatar/000/00/00/84_avatar_big.jpg
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
        private int distance;
        private Object indus_pname;
        private Object indus_name;
        private String user_pic;
        private List<String> show_pic;

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

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public Object getIndus_pname() {
            return indus_pname;
        }

        public void setIndus_pname(Object indus_pname) {
            this.indus_pname = indus_pname;
        }

        public Object getIndus_name() {
            return indus_name;
        }

        public void setIndus_name(Object indus_name) {
            this.indus_name = indus_name;
        }

        public String getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(String user_pic) {
            this.user_pic = user_pic;
        }

        public List<String> getShow_pic() {
            return show_pic;
        }

        public void setShow_pic(List<String> show_pic) {
            this.show_pic = show_pic;
        }
    }
}

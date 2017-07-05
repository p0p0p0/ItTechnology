package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/5/24 0024 21:33
 * 邮箱：995696826@qq.com
 */

public class BangMangBean implements Serializable{
    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"indus_pid":"29","indus_id":"2","status":"0","task_desc":"速","phone":null,"id":"12","username":"亿赛通","uid":"20","task_title":"交换机维修","sub_time":"1483415804","lng":"120.461698","lat":"36.099374","save_name":"","task_cash":"2000.00","user_pic":"../data/uploads/imgtx/20170302175711.jpg"}]
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
         * indus_pid : 29
         * indus_id : 2
         * status : 0
         * task_desc : 速
         * phone : null
         * id : 12
         * username : 亿赛通
         * uid : 20
         * task_title : 交换机维修
         * sub_time : 1483415804
         * lng : 120.461698
         * lat : 36.099374
         * save_name :
         * task_cash : 2000.00
         * user_pic : ../data/uploads/imgtx/20170302175711.jpg
         */

        private String indus_pid;
        private String indus_id;
        private String status;
        private String task_desc;
        private Object phone;
        private String id;
        private String username;
        private String uid;
        private String task_title;
        private String sub_time;
        private String lng;
        private String lat;
        private String save_name;
        private String task_cash;
        private String user_pic;

        public String getIndus_pid() {
            return indus_pid;
        }

        public void setIndus_pid(String indus_pid) {
            this.indus_pid = indus_pid;
        }

        public String getIndus_id() {
            return indus_id;
        }

        public void setIndus_id(String indus_id) {
            this.indus_id = indus_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTask_desc() {
            return task_desc;
        }

        public void setTask_desc(String task_desc) {
            this.task_desc = task_desc;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTask_title() {
            return task_title;
        }

        public void setTask_title(String task_title) {
            this.task_title = task_title;
        }

        public String getSub_time() {
            return sub_time;
        }

        public void setSub_time(String sub_time) {
            this.sub_time = sub_time;
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

        public String getSave_name() {
            return save_name;
        }

        public void setSave_name(String save_name) {
            this.save_name = save_name;
        }

        public String getTask_cash() {
            return task_cash;
        }

        public void setTask_cash(String task_cash) {
            this.task_cash = task_cash;
        }

        public String getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(String user_pic) {
            this.user_pic = user_pic;
        }
    }
}

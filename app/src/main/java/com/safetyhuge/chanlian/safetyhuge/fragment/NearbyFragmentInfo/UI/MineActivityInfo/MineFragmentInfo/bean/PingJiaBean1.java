package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/5/19 0019 18:27
 * 邮箱：995696826@qq.com
 */

public class PingJiaBean1 implements Serializable{
    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"mark_content":"服务态度可以，就是做到一半想不做了这点不好","uid":"20","by_username":"深信服","mark_status":"2","mark_time":"1489643796","user_pic":"../data/uploads/imgtx/20170309193948.jpg"},{"mark_content":"呵呵","uid":"19","by_username":"亿赛通","mark_status":"2","mark_time":"1489643809","user_pic":"../data/uploads/imgtx/20170302175711.jpg"}]
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
         * mark_content : 服务态度可以，就是做到一半想不做了这点不好
         * uid : 20
         * by_username : 深信服
         * mark_status : 2
         * mark_time : 1489643796
         * user_pic : ../data/uploads/imgtx/20170309193948.jpg
         */

        private String mark_content;
        private String uid;
        private String by_username;
        private String mark_status;
        private String mark_time;
        private String user_pic;

        public String getMark_content() {
            return mark_content;
        }

        public void setMark_content(String mark_content) {
            this.mark_content = mark_content;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getBy_username() {
            return by_username;
        }

        public void setBy_username(String by_username) {
            this.by_username = by_username;
        }

        public String getMark_status() {
            return mark_status;
        }

        public void setMark_status(String mark_status) {
            this.mark_status = mark_status;
        }

        public String getMark_time() {
            return mark_time;
        }

        public void setMark_time(String mark_time) {
            this.mark_time = mark_time;
        }

        public String getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(String user_pic) {
            this.user_pic = user_pic;
        }
    }
}

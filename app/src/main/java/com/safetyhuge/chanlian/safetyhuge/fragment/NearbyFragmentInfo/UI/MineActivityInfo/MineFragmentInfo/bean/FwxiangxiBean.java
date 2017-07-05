package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/5/19 0019 17:56
 * 邮箱：995696826@qq.com
 */

public class FwxiangxiBean implements Serializable{
    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"indus_pid":"0","indus_id":"0","content":"1111","service_id":"767","uid":"84","username":"13668844170","title":"防火墙","pic":"../data/uploads/cpimg/cropped_1495015512845.jpg","price":"100.00","sale_num":"0","user_pic":null,"lng":"120.46181","lat":"36.099594"}]
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
         * indus_pid : 0
         * indus_id : 0
         * content : 1111
         * service_id : 767
         * uid : 84
         * username : 13668844170
         * title : 防火墙
         * pic : ../data/uploads/cpimg/cropped_1495015512845.jpg
         * price : 100.00
         * sale_num : 0
         * user_pic : null
         * lng : 120.46181
         * lat : 36.099594
         */

        private String indus_pid;
        private String indus_id;
        private String content;
        private String service_id;
        private String uid;
        private String username;
        private String title;
        private String pic;
        private String price;
        private String sale_num;
        private Object user_pic;
        private String lng;
        private String lat;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
        }

        public Object getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(Object user_pic) {
            this.user_pic = user_pic;
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

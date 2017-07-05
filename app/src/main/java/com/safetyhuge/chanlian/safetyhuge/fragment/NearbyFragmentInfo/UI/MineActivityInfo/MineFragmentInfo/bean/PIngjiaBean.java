package com.safetyhuge.chanlian.safetyhuge.fragment.NearbyFragmentInfo.UI.MineActivityInfo.MineFragmentInfo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/5/18 0018 22:23
 * 邮箱：995696826@qq.com
 */

public class PIngjiaBean implements Serializable {

    /**
     * code : 888
     * secess : true
     * data : [{"mark_id":"400","model_code":"service","origin_id":"831","obj_id":"1109","order_id":"","obj_cash":"970","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1496991385","uid":"20","username":"亿赛通","mark_max_time":"1497250585","by_uid":"84","by_username":"13668844170","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"970.00","mark_type":"2","mark_count":"1","plxxzt":"0","by_marker_pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"402","model_code":"service","origin_id":"831","obj_id":"1118","order_id":"","obj_cash":"970","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1497235944","uid":"20","username":"亿赛通","mark_max_time":"1497495144","by_uid":"84","by_username":"13668844170","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"970.00","mark_type":"2","mark_count":"1","plxxzt":"0","by_marker_pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"406","model_code":"service","origin_id":"831","obj_id":"1117","order_id":"","obj_cash":"970","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1497258557","uid":"20","username":"亿赛通","mark_max_time":"1497517757","by_uid":"84","by_username":"13668844170","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"970.00","mark_type":"2","mark_count":"1","plxxzt":"0","by_marker_pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"408","model_code":"service","origin_id":"831","obj_id":"1116","order_id":"","obj_cash":"970","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1497258610","uid":"20","username":"亿赛通","mark_max_time":"1497517810","by_uid":"84","by_username":"13668844170","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"970.00","mark_type":"2","mark_count":"1","plxxzt":"0","by_marker_pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"411","model_code":"service","origin_id":"833","obj_id":"1115","order_id":"","obj_cash":"1000","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1497325490","uid":"20","username":"亿赛通","mark_max_time":"1497584690","by_uid":"84","by_username":"13668844170","aid":"4,5","aid_star":"5.0,5.0","mark_value":"1000.00","mark_type":"1","mark_count":"1","plxxzt":"0","by_marker_pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"付款及时性","star":5,"count":1,"avg":"5.0"},{"aid_name":"合作愉快度","star":5,"count":1,"avg":"5.0"}]}]
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
         * mark_id : 400
         * model_code : service
         * origin_id : 831
         * obj_id : 1109
         * order_id :
         * obj_cash : 970
         * mark_status : 1
         * mark_content :
         * mark_contents :
         * mark_time : 1496991385
         * uid : 20
         * username : 亿赛通
         * mark_max_time : 1497250585
         * by_uid : 84
         * by_username : 13668844170
         * aid : 1,2,3
         * aid_star : 5.0,5.0,5.0
         * mark_value : 970.00
         * mark_type : 2
         * mark_count : 1
         * plxxzt : 0
         * by_marker_pic : data/avatar/000/00/00/20_avatar_big.jpg
         * aidinfo : [{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]
         */

        private String mark_id;
        private String model_code;
        private String origin_id;
        private String obj_id;
        private String order_id;
        private String obj_cash;
        private String mark_status;
        private String mark_content;
        private String mark_contents;
        private String mark_time;
        private String uid;
        private String username;
        private String mark_max_time;
        private String by_uid;
        private String by_username;
        private String aid;
        private String aid_star;
        private String mark_value;
        private String mark_type;
        private String mark_count;
        private String plxxzt;
        private String by_marker_pic;
        private List<AidinfoBean> aidinfo;
        private String marker_pic;

        public String getMark_id() {
            return mark_id;
        }

        public void setMark_id(String mark_id) {
            this.mark_id = mark_id;
        }

        public String getModel_code() {
            return model_code;
        }

        public void setModel_code(String model_code) {
            this.model_code = model_code;
        }

        public String getOrigin_id() {
            return origin_id;
        }

        public void setOrigin_id(String origin_id) {
            this.origin_id = origin_id;
        }

        public String getObj_id() {
            return obj_id;
        }

        public void setObj_id(String obj_id) {
            this.obj_id = obj_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getObj_cash() {
            return obj_cash;
        }

        public void setObj_cash(String obj_cash) {
            this.obj_cash = obj_cash;
        }

        public String getMark_status() {
            return mark_status;
        }

        public void setMark_status(String mark_status) {
            this.mark_status = mark_status;
        }

        public String getMark_content() {
            return mark_content;
        }

        public void setMark_content(String mark_content) {
            this.mark_content = mark_content;
        }

        public String getMark_contents() {
            return mark_contents;
        }

        public void setMark_contents(String mark_contents) {
            this.mark_contents = mark_contents;
        }

        public String getMark_time() {
            return mark_time;
        }

        public void setMark_time(String mark_time) {
            this.mark_time = mark_time;
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

        public String getMark_max_time() {
            return mark_max_time;
        }

        public void setMark_max_time(String mark_max_time) {
            this.mark_max_time = mark_max_time;
        }

        public String getBy_uid() {
            return by_uid;
        }

        public void setBy_uid(String by_uid) {
            this.by_uid = by_uid;
        }

        public String getBy_username() {
            return by_username;
        }

        public void setBy_username(String by_username) {
            this.by_username = by_username;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getAid_star() {
            return aid_star;
        }

        public void setAid_star(String aid_star) {
            this.aid_star = aid_star;
        }

        public String getMark_value() {
            return mark_value;
        }

        public void setMark_value(String mark_value) {
            this.mark_value = mark_value;
        }

        public String getMark_type() {
            return mark_type;
        }

        public void setMark_type(String mark_type) {
            this.mark_type = mark_type;
        }

        public String getMark_count() {
            return mark_count;
        }

        public void setMark_count(String mark_count) {
            this.mark_count = mark_count;
        }

        public String getPlxxzt() {
            return plxxzt;
        }

        public void setPlxxzt(String plxxzt) {
            this.plxxzt = plxxzt;
        }

        public String getBy_marker_pic() {
            return by_marker_pic;
        }

        public void setBy_marker_pic(String by_marker_pic) {
            this.by_marker_pic = by_marker_pic;
        }

        public List<AidinfoBean> getAidinfo() {
            return aidinfo;
        }

        public void setAidinfo(List<AidinfoBean> aidinfo) {
            this.aidinfo = aidinfo;
        }

        public String getMarker_pic() {
            return marker_pic;
        }

        public void setMarker_pic(String marker_pic) {
            this.marker_pic = marker_pic;
        }

        public static class AidinfoBean {
            /**
             * aid_name : 速度
             * star : 5
             * count : 1
             * avg : 5.0
             */

            private String aid_name;
            private int star;
            private int count;
            private String avg;

            public String getAid_name() {
                return aid_name;
            }

            public void setAid_name(String aid_name) {
                this.aid_name = aid_name;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getAvg() {
                return avg;
            }

            public void setAvg(String avg) {
                this.avg = avg;
            }
        }
    }
}

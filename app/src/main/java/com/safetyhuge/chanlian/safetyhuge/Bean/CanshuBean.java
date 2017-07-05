package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/14 0014 10:17
 * 邮箱：995696826@qq.com
 */

public class CanshuBean implements Serializable{
    /**
     * data : {"uid":"84","cycle":"10","submit":"1","task_id":"457","plan_title":[100],"city":"82","message":"100","area":"1280","work_hidden":"1","end_time":["2017-4-15"],"quote":"100","action":"BidTask","province":"3","start_time":["2017-4-13"],"plan_amount":[100]}
     * secess : true
     * code : 888
     */

    private DataBean data;
    private String secess;
    private String code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getSecess() {
        return secess;
    }

    public void setSecess(String secess) {
        this.secess = secess;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * uid : 84
         * cycle : 10
         * submit : 1
         * task_id : 457
         * plan_title : [100]
         * city : 82
         * message : 100
         * area : 1280
         * work_hidden : 1
         * end_time : ["2017-4-15"]
         * quote : 100
         * action : BidTask
         * province : 3
         * start_time : ["2017-4-13"]
         * plan_amount : [100]
         */

        private String uid;
        private String cycle;
        private String submit;
        private String task_id;
        private String city;
        private String message;
        private String area;
        private String work_hidden;
        private String quote;
        private String action;
        private String province;
        private List<Integer> plan_title;
        private List<String> end_time;
        private List<String> start_time;
        private List<Integer> plan_amount;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }

        public String getSubmit() {
            return submit;
        }

        public void setSubmit(String submit) {
            this.submit = submit;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getWork_hidden() {
            return work_hidden;
        }

        public void setWork_hidden(String work_hidden) {
            this.work_hidden = work_hidden;
        }

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public List<Integer> getPlan_title() {
            return plan_title;
        }

        public void setPlan_title(List<Integer> plan_title) {
            this.plan_title = plan_title;
        }

        public List<String> getEnd_time() {
            return end_time;
        }

        public void setEnd_time(List<String> end_time) {
            this.end_time = end_time;
        }

        public List<String> getStart_time() {
            return start_time;
        }

        public void setStart_time(List<String> start_time) {
            this.start_time = start_time;
        }

        public List<Integer> getPlan_amount() {
            return plan_amount;
        }

        public void setPlan_amount(List<Integer> plan_amount) {
            this.plan_amount = plan_amount;
        }
    }
}

package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/6/23 0023 11:26
 * 邮箱：995696826@qq.com
 */

public class FirmBean implements Serializable{
    /**
     * code : 200
     * message : 数据返回为空
     * data : [{"company":"","licen_num":"","legal":"","licen_pic":"","auth_status":"2"}]
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
         * company :
         * licen_num :
         * legal :
         * licen_pic :
         * auth_status : 2
         */

        private String company;
        private String licen_num;
        private String legal;
        private String licen_pic;
        private String auth_status;
        private String uid;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getLicen_num() {
            return licen_num;
        }

        public void setLicen_num(String licen_num) {
            this.licen_num = licen_num;
        }

        public String getLegal() {
            return legal;
        }

        public void setLegal(String legal) {
            this.legal = legal;
        }

        public String getLicen_pic() {
            return licen_pic;
        }

        public void setLicen_pic(String licen_pic) {
            this.licen_pic = licen_pic;
        }

        public String getAuth_status() {
            return auth_status;
        }

        public void setAuth_status(String auth_status) {
            this.auth_status = auth_status;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}

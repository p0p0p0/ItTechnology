package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chinalink on 2017/3/24 0024.
 */

public class TechnologyBean implements Serializable {

    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"picurl":"http://192.168.6.201/new/upload_files/article/101/1_20161010161046_z9nec.jpg","title":"智能安防技术资料","fid":"101","fname":" 物联网. ","softurl":"http://192.168.6.201/new/upload_files/download/101/1_20161010151010_xhu65.rar "}]
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
         * picurl : http://192.168.6.201/new/upload_files/article/101/1_20161010161046_z9nec.jpg
         * title : 智能安防技术资料
         * fid : 101
         * fname :  物联网.
         * softurl : http://192.168.6.201/new/upload_files/download/101/1_20161010151010_xhu65.rar
         */

        private String picurl;
        private String title;
        private String fid;
        private String fname;
        private String softurl;

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getSofturl() {
            return softurl;
        }

        public void setSofturl(String softurl) {
            this.softurl = softurl;
        }
    }
}

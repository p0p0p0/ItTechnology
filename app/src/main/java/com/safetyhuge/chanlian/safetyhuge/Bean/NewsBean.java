package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/10 0010 18:02
 * 邮箱：995696826@qq.com
 */

public class NewsBean implements Serializable {

    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"aid":"2626","fid":"3","posttime":"1488075281","title":"30家IT企业财报内容中的警示！","fname":"行业动态","picurl":"article/3/1_0gnsp___png.gif","content":""}]
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
         * aid : 2626
         * fid : 3
         * posttime : 1488075281
         * title : 30家IT企业财报内容中的警示！
         * fname : 行业动态
         * picurl : article/3/1_0gnsp___png.gif
         * content :
         */

        private String aid;
        private String fid;
        private String posttime;
        private String title;
        private String fname;
        private String picurl;
        private String content;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getPosttime() {
            return posttime;
        }

        public void setPosttime(String posttime) {
            this.posttime = posttime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

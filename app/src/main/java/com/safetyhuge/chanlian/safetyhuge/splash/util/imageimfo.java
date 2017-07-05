package com.safetyhuge.chanlian.safetyhuge.splash.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by F-57 on 2017/1/17.
 */

public class imageimfo implements Serializable {
    /**
     * msg : success
     * data : [{"img_id":"66","img_content":"","img_url":"images/201701/1484049026930768446.jpg","img_desc":"0","add_time":"1484049026","click_url":"http://www.noseneg.com/article.php?id=138"},{"img_id":"65","img_content":"","img_url":"images/201701/1484036629043589720.jpg","img_desc":"1","add_time":"1484036629","click_url":"http://www.noseneg.com/article.php?id=134"},{"img_id":"63","img_content":"","img_url":"images/201701/1484036569244698132.jpg","img_desc":"2","add_time":"1478678544","click_url":"http://www.noseneg.com/article.php?id=137"}]
     */

    private String msg;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * img_id : 66
         * img_content :
         * img_url : images/201701/1484049026930768446.jpg
         * img_desc : 0
         * add_time : 1484049026
         * click_url : http://www.noseneg.com/article.php?id=138
         */

        private String img_id;
        private String img_content;
        private String img_url;
        private String img_desc;
        private String add_time;
        private String click_url;

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getImg_content() {
            return img_content;
        }

        public void setImg_content(String img_content) {
            this.img_content = img_content;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getImg_desc() {
            return img_desc;
        }

        public void setImg_desc(String img_desc) {
            this.img_desc = img_desc;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getClick_url() {
            return click_url;
        }

        public void setClick_url(String click_url) {
            this.click_url = click_url;
        }
    }
}

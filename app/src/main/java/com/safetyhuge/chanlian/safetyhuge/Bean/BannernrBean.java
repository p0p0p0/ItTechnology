package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by F-57 on 2017/3/20.
 */

public class BannernrBean implements Serializable {
    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"id":"1","pic":"../data/uploads/banner/b1.png","model_id":"1"},{"id":"3","pic":"../data/uploads/banner/b2.png","model_id":"1"},{"id":"4","pic":"../data/uploads/banner/b4.png","model_id":"2"},{"id":"5","pic":"../data/uploads/banner/b6.png","model_id":"2"},{"id":"6","pic":"../data/uploads/banner/b7.png","model_id":"2"},{"id":"7","pic":"../data/uploads/banner/b3.png","model_id":"3"},{"id":"8","pic":"../data/uploads/banner/b8.png","model_id":"3"},{"id":"9","pic":"../data/uploads/banner/b9.png","model_id":"3"},{"id":"11","pic":"../data/uploads/banner/b0.jpg","model_id":"1"}]
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
         * id : 1
         * pic : ../data/uploads/banner/b1.png
         * model_id : 1
         */

        private String id;
        private String pic;
        private String model_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getModel_id() {
            return model_id;
        }

        public void setModel_id(String model_id) {
            this.model_id = model_id;
        }
    }
}

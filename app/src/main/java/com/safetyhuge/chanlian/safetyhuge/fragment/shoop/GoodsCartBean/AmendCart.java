package com.safetyhuge.chanlian.safetyhuge.fragment.shoop.GoodsCartBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by F-57 on 2017/2/18.
 */

public class AmendCart implements Serializable {

    /**
     * msg : success
     * AddCart : [{"goods_number":2}]
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
         * goods_number : 2
         */

        private int goods_number;

        public int getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(int goods_number) {
            this.goods_number = goods_number;
        }
    }
}

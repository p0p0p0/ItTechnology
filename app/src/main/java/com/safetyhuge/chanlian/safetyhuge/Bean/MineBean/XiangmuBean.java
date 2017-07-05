package com.safetyhuge.chanlian.safetyhuge.Bean.MineBean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/21 0021 16:41
 * 邮箱：995696826@qq.com
 */

public class XiangmuBean implements Serializable{

    /**
     * code : 888
     * secess : true
     * data : []
     */

    private String code;
    private String secess;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}

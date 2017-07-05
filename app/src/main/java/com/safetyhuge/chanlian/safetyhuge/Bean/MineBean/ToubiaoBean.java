package com.safetyhuge.chanlian.safetyhuge.Bean.MineBean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/22 0022 14:13
 * 邮箱：995696826@qq.com
 */

public class ToubiaoBean implements Serializable {
    /**
     * code : 888
     * secess : true
     * data : [{"uid":"84","id":"163","task_id":"314","ontime":"1492434318","status":"0","file":"","task_title":"求关于二氧化碳浓度的解决方案","model_id":"1","task_status":"2"},{"uid":"84","id":"162","task_id":"317","ontime":"1492434282","status":"0","file":"","task_title":"混合云云储存的任务","model_id":"1","task_status":"2"},{"uid":"84","id":"161","task_id":"315","ontime":"1492433921","status":"0","file":"","task_title":"关于DDOS网络安全的任务","model_id":"1","task_status":"2"},{"uid":"84","id":"160","task_id":"318","ontime":"1492433757","status":"0","file":"","task_title":"关于路由器系统集成的任务","model_id":"1","task_status":"2"},{"uid":"84","id":"159","task_id":"321","ontime":"1492424216","status":"0","file":"","task_title":"无线射频识别解决方案","model_id":"1","task_status":"2"},{"uid":"84","id":"158","task_id":"322","ontime":"1492424179","status":"0","file":"","task_title":"有关电子监控的解决问题","model_id":"1","task_status":"8"},{"uid":"84","id":"41","task_id":"563","ontime":"1492422302","status":"0","file":"0"}]
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
         * uid : 84
         * id : 163
         * task_id : 314
         * ontime : 1492434318
         * status : 0
         * file :
         * task_title : 求关于二氧化碳浓度的解决方案
         * model_id : 1
         * task_status : 2
         */

        private String uid;
        private String id;
        private String task_id;
        private String ontime;
        private String status;
        private String file;
        private String task_title;
        private String model_id;
        private String task_status;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getOntime() {
            return ontime;
        }

        public void setOntime(String ontime) {
            this.ontime = ontime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getTask_title() {
            return task_title;
        }

        public void setTask_title(String task_title) {
            this.task_title = task_title;
        }

        public String getModel_id() {
            return model_id;
        }

        public void setModel_id(String model_id) {
            this.model_id = model_id;
        }

        public String getTask_status() {
            return task_status;
        }

        public void setTask_status(String task_status) {
            this.task_status = task_status;
        }
    }
}

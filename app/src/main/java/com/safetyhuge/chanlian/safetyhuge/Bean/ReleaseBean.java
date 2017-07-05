package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/3/31 0031 13:24
 * 邮箱：995696826@qq.com
 */

public class ReleaseBean implements Serializable {
    /**
     * code : 888
     * secess : true
     * data : [{"name":"单人悬赏","id":"1","desc":"&lt;p&gt;计件悬赏任务的一般流程是：&lt;/p&gt;&lt;p&gt;1、雇主发布任务将任务金额托管到网站平台&lt;/p&gt;&lt;p&gt;2、众多威客参与并提交方案&lt;/p&gt;&lt;p&gt;3、雇主选择满意方案，设置方案入围状态，商议最终价格&lt;/p&gt;&lt;p&gt;4、雇主从入围方案中选择中标方案&lt;/p&gt;&lt;p&gt;5、方案中标发放赏金。如果议价金额小于托管金额网站返还雇主多余赏金。&lt;br/&gt;&lt;/p&gt;"}]
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
         * name : 单人悬赏
         * id : 1
         * desc : &lt;p&gt;计件悬赏任务的一般流程是：&lt;/p&gt;&lt;p&gt;1、雇主发布任务将任务金额托管到网站平台&lt;/p&gt;&lt;p&gt;2、众多威客参与并提交方案&lt;/p&gt;&lt;p&gt;3、雇主选择满意方案，设置方案入围状态，商议最终价格&lt;/p&gt;&lt;p&gt;4、雇主从入围方案中选择中标方案&lt;/p&gt;&lt;p&gt;5、方案中标发放赏金。如果议价金额小于托管金额网站返还雇主多余赏金。&lt;br/&gt;&lt;/p&gt;
         */

        private String name;
        private String id;
        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}

package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/6 0006 14:10
 * 邮箱：995696826@qq.com
 */

public class AreaBean implements Serializable{
    /**
     * code : 888
     * secess : true
     * data : [{"cid":"37","cname":"东城区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"53","cname":"密云县","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"52","cname":"平谷区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"51","cname":"怀柔区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"50","cname":"大兴区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"49","cname":"昌平区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"48","cname":"顺义区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"47","cname":"通州区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"46","cname":"房山区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"45","cname":"门头沟区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"44","cname":"海淀区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"43","cname":"石景山区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"42","cname":"丰台区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"41","cname":"朝阳区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"40","cname":"宣武区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"39","cname":"崇文区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"38","cname":"西城区","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]},{"cid":"54","cname":"延庆县","ccdata":[{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]}]
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
         * cid : 37
         * cname : 东城区
         * ccdata : [{"ccid":"567","ccname":"东华门街道"},{"ccid":"575","ccname":"景山街道"},{"ccid":"574","ccname":"建国门街道"},{"ccid":"573","ccname":"安定门街道"},{"ccid":"572","ccname":"和平里街道"},{"ccid":"571","ccname":"北新桥街道"},{"ccid":"570","ccname":"交道口街道"},{"ccid":"569","ccname":"东直门街道"},{"ccid":"568","ccname":"东四街道"},{"ccid":"576","ccname":"朝阳门街道"}]
         */

        private String cid;
        private String cname;
        private List<CcdataBean> ccdata;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public List<CcdataBean> getCcdata() {
            return ccdata;
        }

        public void setCcdata(List<CcdataBean> ccdata) {
            this.ccdata = ccdata;
        }

        public static class CcdataBean {
            /**
             * ccid : 567
             * ccname : 东华门街道
             */

            private String ccid;
            private String ccname;

            public String getCcid() {
                return ccid;
            }

            public void setCcid(String ccid) {
                this.ccid = ccid;
            }

            public String getCcname() {
                return ccname;
            }

            public void setCcname(String ccname) {
                this.ccname = ccname;
            }
        }
    }
}

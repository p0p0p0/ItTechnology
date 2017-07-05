package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/26 0026 21:59
 * 邮箱：995696826@qq.com
 */
public class HomeInfoBean implements Serializable{
    /**
     * code : 888
     * secess : true
     * data : {"indus":[{"pid":"2","pname":"网络安全","cdata":[{"cid":"29","cname":"防火墙"},{"cid":"30","cname":"DDOS"},{"cid":"31","cname":"上网行为管理"},{"cid":"32","cname":"链路优化"},{"cid":"33","cname":"IPS 入侵防御系统"},{"cid":"34","cname":"IDS 入侵检测系统"},{"cid":"35","cname":"ipsec-vpn"},{"cid":"457","cname":"ssl-vpn"},{"cid":"458","cname":"杀毒软件"},{"cid":"459","cname":"UTM 威胁管理系统"},{"cid":"460","cname":"桌面安全管理"},{"cid":"461","cname":"APT"},{"cid":"462","cname":"WAF"},{"cid":"467","cname":"其他"}]},{"pid":"3","pname":"系统集成","cdata":[{"cid":"9","cname":"负载平衡"},{"cid":"10","cname":"交换机"},{"cid":"11","cname":"路由器"},{"cid":"12","cname":"门禁对讲"},{"cid":"13","cname":"考勤系统"},{"cid":"14","cname":"集团电话"},{"cid":"16","cname":"电子监控"},{"cid":"27","cname":"综合布线"},{"cid":"41","cname":"背景音乐"},{"cid":"454","cname":"公共广播"},{"cid":"455","cname":"消防系统"},{"cid":"456","cname":"停车系统"},{"cid":"468","cname":"其他系统"}]},{"pid":"121","pname":"信息化安全","cdata":[{"cid":"37","cname":"桌面安全管理"},{"cid":"38","cname":"容灾备份"},{"cid":"39","cname":"数据加密"},{"cid":"122","cname":"堡垒机"},{"cid":"469","cname":"其他安全"}]},{"pid":"160","pname":"物联网","cdata":[{"cid":"162","cname":"烟感"},{"cid":"163","cname":"二氧化碳浓度"},{"cid":"164","cname":"氧气浓度"},{"cid":"165","cname":"照明"},{"cid":"166","cname":"温度控制"},{"cid":"453","cname":"湿度控制"},{"cid":"463","cname":"无线射频识别"},{"cid":"464","cname":"机房智能空调"},{"cid":"465","cname":"智能安防"},{"cid":"466","cname":"车联网"},{"cid":"535","cname":"探感物联（RFID）"},{"cid":"470","cname":"其他系统"}]},{"pid":"192","pname":"云存储","cdata":[{"cid":"194","cname":"私有云"},{"cid":"195","cname":"公有云"},{"cid":"196","cname":"混合云"},{"cid":"471","cname":"其他"}]},{"pid":"201","pname":"云计算","cdata":[{"cid":"203","cname":"桌面虚拟化"},{"cid":"204","cname":"服务器虚拟化"},{"cid":"205","cname":"私有云"},{"cid":"206","cname":"公有云"},{"cid":"207","cname":"混合云"},{"cid":"472","cname":"其他"}]},{"pid":"211","pname":"大数据分析","cdata":[{"cid":"213","cname":"应用系统数据分析"},{"cid":"214","cname":"非结构化数据分析"},{"cid":"215","cname":"互联网信息探索数据分析"},{"cid":"216","cname":"综合性数据分析"}]},{"pid":"218","pname":"安全相关服务","cdata":[{"cid":"475","cname":"安全策略制定优化"},{"cid":"476","cname":"系统安全加固"},{"cid":"477","cname":"系统漏洞审计与修补"},{"cid":"478","cname":"渗透攻击测试"},{"cid":"479","cname":"数据库安全管理与加固"},{"cid":"480","cname":"基线检查/设备核查"},{"cid":"481","cname":"紧急事件响应"},{"cid":"482","cname":"网络安全培训"},{"cid":"483","cname":"风险评估/风险管理"},{"cid":"484","cname":"日志分析平台"},{"cid":"485","cname":"白帽服务"},{"cid":"486","cname":"ISMS（ISO/IEC27001）安全体系认证"},{"cid":"487","cname":"等级保护服务"},{"cid":"474","cname":"其他安全服务"}]}],"region":[{"pid":"1","pname":"北京"},{"pid":"2","pname":"天津市"},{"pid":"3","pname":"河北省"},{"pid":"4","pname":"山西省"},{"pid":"5","pname":"内蒙古"},{"pid":"6","pname":"辽宁省"},{"pid":"7","pname":"吉林省"},{"pid":"8","pname":"黑龙江省"},{"pid":"9","pname":"上海市"},{"pid":"10","pname":"江苏省"},{"pid":"11","pname":"浙江省"},{"pid":"12","pname":"安徽省"},{"pid":"13","pname":"福建省"},{"pid":"14","pname":"江西省"},{"pid":"15","pname":"山东省"},{"pid":"16","pname":"河南省"},{"pid":"17","pname":"湖北省"},{"pid":"18","pname":"湖南省"},{"pid":"19","pname":"广东省"},{"pid":"20","pname":"广西"},{"pid":"21","pname":"海南省"},{"pid":"22","pname":"重庆市"},{"pid":"23","pname":"四川省"},{"pid":"24","pname":"贵州省"},{"pid":"25","pname":"云南省"},{"pid":"26","pname":"西藏"},{"pid":"27","pname":"陕西省"},{"pid":"28","pname":"甘肃省"},{"pid":"29","pname":"青海省"},{"pid":"30","pname":"宁夏"},{"pid":"31","pname":"新疆"},{"pid":"32","pname":"台湾省"},{"pid":"33","pname":"香港"},{"pid":"34","pname":"澳门"},{"pid":"35","pname":"海外"},{"pid":"36","pname":"其他"}],"brand":[{"bid":"1","bname":"华为","border":"1"},{"bid":"2","bname":"亿赛通","border":"2"},{"bid":"3","bname":"深信服","border":"3"},{"bid":"4","bname":"启明星辰","border":"4"},{"bid":"5","bname":"爱数","border":"5"},{"bid":"8","bname":"其他","border":"6"}],"max_file_size":"20"}
     */

    private String code;
    private String secess;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * indus : [{"pid":"2","pname":"网络安全","cdata":[{"cid":"29","cname":"防火墙"},{"cid":"30","cname":"DDOS"},{"cid":"31","cname":"上网行为管理"},{"cid":"32","cname":"链路优化"},{"cid":"33","cname":"IPS 入侵防御系统"},{"cid":"34","cname":"IDS 入侵检测系统"},{"cid":"35","cname":"ipsec-vpn"},{"cid":"457","cname":"ssl-vpn"},{"cid":"458","cname":"杀毒软件"},{"cid":"459","cname":"UTM 威胁管理系统"},{"cid":"460","cname":"桌面安全管理"},{"cid":"461","cname":"APT"},{"cid":"462","cname":"WAF"},{"cid":"467","cname":"其他"}]},{"pid":"3","pname":"系统集成","cdata":[{"cid":"9","cname":"负载平衡"},{"cid":"10","cname":"交换机"},{"cid":"11","cname":"路由器"},{"cid":"12","cname":"门禁对讲"},{"cid":"13","cname":"考勤系统"},{"cid":"14","cname":"集团电话"},{"cid":"16","cname":"电子监控"},{"cid":"27","cname":"综合布线"},{"cid":"41","cname":"背景音乐"},{"cid":"454","cname":"公共广播"},{"cid":"455","cname":"消防系统"},{"cid":"456","cname":"停车系统"},{"cid":"468","cname":"其他系统"}]},{"pid":"121","pname":"信息化安全","cdata":[{"cid":"37","cname":"桌面安全管理"},{"cid":"38","cname":"容灾备份"},{"cid":"39","cname":"数据加密"},{"cid":"122","cname":"堡垒机"},{"cid":"469","cname":"其他安全"}]},{"pid":"160","pname":"物联网","cdata":[{"cid":"162","cname":"烟感"},{"cid":"163","cname":"二氧化碳浓度"},{"cid":"164","cname":"氧气浓度"},{"cid":"165","cname":"照明"},{"cid":"166","cname":"温度控制"},{"cid":"453","cname":"湿度控制"},{"cid":"463","cname":"无线射频识别"},{"cid":"464","cname":"机房智能空调"},{"cid":"465","cname":"智能安防"},{"cid":"466","cname":"车联网"},{"cid":"535","cname":"探感物联（RFID）"},{"cid":"470","cname":"其他系统"}]},{"pid":"192","pname":"云存储","cdata":[{"cid":"194","cname":"私有云"},{"cid":"195","cname":"公有云"},{"cid":"196","cname":"混合云"},{"cid":"471","cname":"其他"}]},{"pid":"201","pname":"云计算","cdata":[{"cid":"203","cname":"桌面虚拟化"},{"cid":"204","cname":"服务器虚拟化"},{"cid":"205","cname":"私有云"},{"cid":"206","cname":"公有云"},{"cid":"207","cname":"混合云"},{"cid":"472","cname":"其他"}]},{"pid":"211","pname":"大数据分析","cdata":[{"cid":"213","cname":"应用系统数据分析"},{"cid":"214","cname":"非结构化数据分析"},{"cid":"215","cname":"互联网信息探索数据分析"},{"cid":"216","cname":"综合性数据分析"}]},{"pid":"218","pname":"安全相关服务","cdata":[{"cid":"475","cname":"安全策略制定优化"},{"cid":"476","cname":"系统安全加固"},{"cid":"477","cname":"系统漏洞审计与修补"},{"cid":"478","cname":"渗透攻击测试"},{"cid":"479","cname":"数据库安全管理与加固"},{"cid":"480","cname":"基线检查/设备核查"},{"cid":"481","cname":"紧急事件响应"},{"cid":"482","cname":"网络安全培训"},{"cid":"483","cname":"风险评估/风险管理"},{"cid":"484","cname":"日志分析平台"},{"cid":"485","cname":"白帽服务"},{"cid":"486","cname":"ISMS（ISO/IEC27001）安全体系认证"},{"cid":"487","cname":"等级保护服务"},{"cid":"474","cname":"其他安全服务"}]}]
         * region : [{"pid":"1","pname":"北京"},{"pid":"2","pname":"天津市"},{"pid":"3","pname":"河北省"},{"pid":"4","pname":"山西省"},{"pid":"5","pname":"内蒙古"},{"pid":"6","pname":"辽宁省"},{"pid":"7","pname":"吉林省"},{"pid":"8","pname":"黑龙江省"},{"pid":"9","pname":"上海市"},{"pid":"10","pname":"江苏省"},{"pid":"11","pname":"浙江省"},{"pid":"12","pname":"安徽省"},{"pid":"13","pname":"福建省"},{"pid":"14","pname":"江西省"},{"pid":"15","pname":"山东省"},{"pid":"16","pname":"河南省"},{"pid":"17","pname":"湖北省"},{"pid":"18","pname":"湖南省"},{"pid":"19","pname":"广东省"},{"pid":"20","pname":"广西"},{"pid":"21","pname":"海南省"},{"pid":"22","pname":"重庆市"},{"pid":"23","pname":"四川省"},{"pid":"24","pname":"贵州省"},{"pid":"25","pname":"云南省"},{"pid":"26","pname":"西藏"},{"pid":"27","pname":"陕西省"},{"pid":"28","pname":"甘肃省"},{"pid":"29","pname":"青海省"},{"pid":"30","pname":"宁夏"},{"pid":"31","pname":"新疆"},{"pid":"32","pname":"台湾省"},{"pid":"33","pname":"香港"},{"pid":"34","pname":"澳门"},{"pid":"35","pname":"海外"},{"pid":"36","pname":"其他"}]
         * brand : [{"bid":"1","bname":"华为","border":"1"},{"bid":"2","bname":"亿赛通","border":"2"},{"bid":"3","bname":"深信服","border":"3"},{"bid":"4","bname":"启明星辰","border":"4"},{"bid":"5","bname":"爱数","border":"5"},{"bid":"8","bname":"其他","border":"6"}]
         * max_file_size : 20
         */

        private String max_file_size;
        private List<IndusBean> indus;
        private List<RegionBean> region;
        private List<BrandBean> brand;

        public String getMax_file_size() {
            return max_file_size;
        }

        public void setMax_file_size(String max_file_size) {
            this.max_file_size = max_file_size;
        }

        public List<IndusBean> getIndus() {
            return indus;
        }

        public void setIndus(List<IndusBean> indus) {
            this.indus = indus;
        }

        public List<RegionBean> getRegion() {
            return region;
        }

        public void setRegion(List<RegionBean> region) {
            this.region = region;
        }

        public List<BrandBean> getBrand() {
            return brand;
        }

        public void setBrand(List<BrandBean> brand) {
            this.brand = brand;
        }

        public static class IndusBean {
            /**
             * pid : 2
             * pname : 网络安全
             * cdata : [{"cid":"29","cname":"防火墙"},{"cid":"30","cname":"DDOS"},{"cid":"31","cname":"上网行为管理"},{"cid":"32","cname":"链路优化"},{"cid":"33","cname":"IPS 入侵防御系统"},{"cid":"34","cname":"IDS 入侵检测系统"},{"cid":"35","cname":"ipsec-vpn"},{"cid":"457","cname":"ssl-vpn"},{"cid":"458","cname":"杀毒软件"},{"cid":"459","cname":"UTM 威胁管理系统"},{"cid":"460","cname":"桌面安全管理"},{"cid":"461","cname":"APT"},{"cid":"462","cname":"WAF"},{"cid":"467","cname":"其他"}]
             */

            private String pid;
            private String pname;
            private List<CdataBean> cdata;

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getPname() {
                return pname;
            }

            public void setPname(String pname) {
                this.pname = pname;
            }

            public List<CdataBean> getCdata() {
                return cdata;
            }

            public void setCdata(List<CdataBean> cdata) {
                this.cdata = cdata;
            }

            public static class CdataBean {
                /**
                 * cid : 29
                 * cname : 防火墙
                 */

                private String cid;
                private String cname;

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
            }
        }

        public static class RegionBean {
            /**
             * pid : 1
             * pname : 北京
             */

            private String pid;
            private String pname;

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getPname() {
                return pname;
            }

            public void setPname(String pname) {
                this.pname = pname;
            }
        }

        public static class BrandBean {
            /**
             * bid : 1
             * bname : 华为
             * border : 1
             */

            private String bid;
            private String bname;
            private String border;

            public String getBid() {
                return bid;
            }

            public void setBid(String bid) {
                this.bid = bid;
            }

            public String getBname() {
                return bname;
            }

            public void setBname(String bname) {
                this.bname = bname;
            }

            public String getBorder() {
                return border;
            }

            public void setBorder(String border) {
                this.border = border;
            }
        }
    }
}

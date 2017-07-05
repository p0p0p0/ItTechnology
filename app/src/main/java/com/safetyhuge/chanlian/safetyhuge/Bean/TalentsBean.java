package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chinalink on 2017/3/23 0023.
 */
public class TalentsBean implements Serializable {


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
         * uid : 88
         * username : 王于听
         * password : e10adc3949ba59abbe56e057f20f883e
         * sec_code : 15bba35ea5ee5fe431879a8a21d8fb50
         * email : 851424971@qq.com
         * user_pic :
         * group_id :
         * isvip :
         * status : 1
         * user_type : 1
         * sex : 男
         * marry :
         * hometown :
         * residency :
         * address : 阿萨德倒萨
         * birthday : 1993-10-18
         * truename : 王于听
         * idcard : 331004199310181817
         * idpic :
         * qq : 851424971
         * msn :
         * fax :
         * phone :
         * mobile : 13381186024
         * indus_id : 29
         * indus_pid : 2
         * skill_ids :
         * summary :
         * experience :
         * reg_time : 1492147719
         * reg_ip : 192.168.3.253
         * domain :
         * credit : 0.000
         * balance : 11190.849
         * balance_status :
         * pub_num : 0
         * take_num : 2
         * accepted_num : 2
         * vip_start_time :
         * vip_end_time :
         * pay_zfb :
         * pay_cft :
         * pay_bank :
         * score :
         * buyer_credit : 0
         * buyer_good_num : 0
         * buyer_level : a:8:{s:8:"score_id";s:1:"1";s:5:"value";s:1:"0";s:5:"title";s:6:"一级";s:5:"level";i:1;s:8:"level_up";i:10;s:10:"level_name";s:6:"等级";s:10:"grade_rate";s:4:"0.00";s:3:"pic";s:142:"<img src="data/uploads/sys/mark/1335458fdd2214f7ba.png" align="absmiddle" title="头衔 ：一级&#13;&#10;信誉值：0&#13;&#10;等级：1">";}
         * buyer_total_num : 0
         * seller_credit : 201194
         * seller_good_num : 5
         * seller_level : a:8:{s:8:"score_id";s:2:"13";s:5:"value";i:201194;s:5:"title";s:9:"十三级";s:5:"level";i:13;s:8:"level_up";i:298806;s:10:"level_name";s:6:"等级";s:10:"grade_rate";s:4:"0.40";s:3:"pic";s:151:"<img src="data/uploads/sys/mark/2753258f97de07a33e.png" align="absmiddle" title="头衔 ：十三级&#13;&#10;能力值：201194&#13;&#10;等级：13">";}
         * seller_total_num : 5
         * studio_id :
         * last_login_time : 1494576604
         * client_status :
         * recommend : 0
         * union : 0
         * union_assoc : 0
         * union_rid : 0
         * union_user :
         * province : 15
         * city : 224
         * area : 2526
         * is_mail : 0
         * is_perfect : 1
         * autoshop : 1
         * is_hongbao :
         * token :
         * indentity : 0
         * cx_status : 1
         * work_status : 1
         * show_pic : data/avatar/system/1_big.jpg
         * certificate_info : [{"id":"7","cid":"7","uid":"88","username":"王于听","authority":"亿塞通","pic":"data/uploads/2017/04/25/466258ff1e685b7ba.png","start_time":"1491321600","end_time":"1491408000","on_time":"1493049600","status":"1","activa_status":"1","name":"PMP认证"},{"id":"8","cid":"8","uid":"88","username":"王于听","authority":"爱数","pic":"data/uploads/2017/04/25/3011658ff1e8ede72d.png","start_time":"1490976000","end_time":"1492012800","on_time":"1493049600","status":"1","activa_status":"1","name":"IPMP认证"}]
         * creditLevel : {"score_id":"13","value":201194,"title":"十三级","level":13,"level_up":298806,"level_name":"等级","grade_rate":"0.40","pic":"<img src=\"data/uploads/sys/mark/2753258f97de07a33e.png\" align=\"absmiddle\" title=\"头衔 ：十三级&#13;&#10;能力值：201194&#13;&#10;等级：13\">"}
         */

        private String uid;
        private String cx_status;
        private String username;
        private String password;
        private String sec_code;
        private String email;
        private String user_pic;
        private String group_id;
        private String isvip;
        private String status;
        private String user_type;
        private String sex;
        private String marry;
        private String hometown;
        private String residency;
        private String address;
        private String birthday;
        private String truename;
        private String idcard;
        private String idpic;
        private String qq;
        private String msn;
        private String fax;
        private String phone;
        private String mobile;
        private String indus_id;
        private String indus_pid;
        private String skill_ids;
        private String summary;
        private String experience;
        private String reg_time;
        private String reg_ip;
        private String domain;
        private String credit;
        private String balance;
        private String balance_status;
        private String pub_num;
        private String take_num;
        private String accepted_num;
        private String vip_start_time;
        private String vip_end_time;
        private String pay_zfb;
        private String pay_cft;
        private String pay_bank;
        private String score;
        private String buyer_credit;
        private String buyer_good_num;
        private String buyer_level;
        private String buyer_total_num;
        private String seller_credit;
        private String seller_good_num;
        private String seller_level;
        private String seller_total_num;
        private String studio_id;
        private String last_login_time;
        private String client_status;
        private String recommend;
        private String union;
        private String union_assoc;
        private String union_rid;
        private String union_user;
        private String province;
        private String city;
        private String area;
        private String is_mail;
        private String is_perfect;
        private String autoshop;
        private String is_hongbao;
        private String token;
        private String indentity;
        private String work_status;
        private String show_pic;
        private CreditLevelBean creditLevel;
        private List<CertificateInfoBean> certificate_info;

        /*      "indus_pname": "系统集成",
                      "indus_name": null*/
        private String indus_pname;

        public String getIndus_pname() {
            return indus_pname;
        }

        public void setIndus_pname(String indus_pname) {
            this.indus_pname = indus_pname;
        }

        public String getIndus_name() {
            return indus_name;
        }

        public void setIndus_name(String indus_name) {
            this.indus_name = indus_name;
        }

        private String indus_name;
        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public String getCx_status() {
            return cx_status;
        }

        public void setCx_status(String cx_status) {
            this.cx_status = cx_status;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSec_code() {
            return sec_code;
        }

        public void setSec_code(String sec_code) {
            this.sec_code = sec_code;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(String user_pic) {
            this.user_pic = user_pic;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getIsvip() {
            return isvip;
        }

        public void setIsvip(String isvip) {
            this.isvip = isvip;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMarry() {
            return marry;
        }

        public void setMarry(String marry) {
            this.marry = marry;
        }

        public String getHometown() {
            return hometown;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        public String getResidency() {
            return residency;
        }

        public void setResidency(String residency) {
            this.residency = residency;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getIdpic() {
            return idpic;
        }

        public void setIdpic(String idpic) {
            this.idpic = idpic;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIndus_id() {
            return indus_id;
        }

        public void setIndus_id(String indus_id) {
            this.indus_id = indus_id;
        }

        public String getIndus_pid() {
            return indus_pid;
        }

        public void setIndus_pid(String indus_pid) {
            this.indus_pid = indus_pid;
        }

        public String getSkill_ids() {
            return skill_ids;
        }

        public void setSkill_ids(String skill_ids) {
            this.skill_ids = skill_ids;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getReg_ip() {
            return reg_ip;
        }

        public void setReg_ip(String reg_ip) {
            this.reg_ip = reg_ip;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getBalance_status() {
            return balance_status;
        }

        public void setBalance_status(String balance_status) {
            this.balance_status = balance_status;
        }

        public String getPub_num() {
            return pub_num;
        }

        public void setPub_num(String pub_num) {
            this.pub_num = pub_num;
        }

        public String getTake_num() {
            return take_num;
        }

        public void setTake_num(String take_num) {
            this.take_num = take_num;
        }

        public String getAccepted_num() {
            return accepted_num;
        }

        public void setAccepted_num(String accepted_num) {
            this.accepted_num = accepted_num;
        }

        public String getVip_start_time() {
            return vip_start_time;
        }

        public void setVip_start_time(String vip_start_time) {
            this.vip_start_time = vip_start_time;
        }

        public String getVip_end_time() {
            return vip_end_time;
        }

        public void setVip_end_time(String vip_end_time) {
            this.vip_end_time = vip_end_time;
        }

        public String getPay_zfb() {
            return pay_zfb;
        }

        public void setPay_zfb(String pay_zfb) {
            this.pay_zfb = pay_zfb;
        }

        public String getPay_cft() {
            return pay_cft;
        }

        public void setPay_cft(String pay_cft) {
            this.pay_cft = pay_cft;
        }

        public String getPay_bank() {
            return pay_bank;
        }

        public void setPay_bank(String pay_bank) {
            this.pay_bank = pay_bank;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getBuyer_credit() {
            return buyer_credit;
        }

        public void setBuyer_credit(String buyer_credit) {
            this.buyer_credit = buyer_credit;
        }

        public String getBuyer_good_num() {
            return buyer_good_num;
        }

        public void setBuyer_good_num(String buyer_good_num) {
            this.buyer_good_num = buyer_good_num;
        }

        public String getBuyer_level() {
            return buyer_level;
        }

        public void setBuyer_level(String buyer_level) {
            this.buyer_level = buyer_level;
        }

        public String getBuyer_total_num() {
            return buyer_total_num;
        }

        public void setBuyer_total_num(String buyer_total_num) {
            this.buyer_total_num = buyer_total_num;
        }

        public String getSeller_credit() {
            return seller_credit;
        }

        public void setSeller_credit(String seller_credit) {
            this.seller_credit = seller_credit;
        }

        public String getSeller_good_num() {
            return seller_good_num;
        }

        public void setSeller_good_num(String seller_good_num) {
            this.seller_good_num = seller_good_num;
        }

        public String getSeller_level() {
            return seller_level;
        }

        public void setSeller_level(String seller_level) {
            this.seller_level = seller_level;
        }

        public String getSeller_total_num() {
            return seller_total_num;
        }

        public void setSeller_total_num(String seller_total_num) {
            this.seller_total_num = seller_total_num;
        }

        public String getStudio_id() {
            return studio_id;
        }

        public void setStudio_id(String studio_id) {
            this.studio_id = studio_id;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getClient_status() {
            return client_status;
        }

        public void setClient_status(String client_status) {
            this.client_status = client_status;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getUnion() {
            return union;
        }

        public void setUnion(String union) {
            this.union = union;
        }

        public String getUnion_assoc() {
            return union_assoc;
        }

        public void setUnion_assoc(String union_assoc) {
            this.union_assoc = union_assoc;
        }

        public String getUnion_rid() {
            return union_rid;
        }

        public void setUnion_rid(String union_rid) {
            this.union_rid = union_rid;
        }

        public String getUnion_user() {
            return union_user;
        }

        public void setUnion_user(String union_user) {
            this.union_user = union_user;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getIs_mail() {
            return is_mail;
        }

        public void setIs_mail(String is_mail) {
            this.is_mail = is_mail;
        }

        public String getIs_perfect() {
            return is_perfect;
        }

        public void setIs_perfect(String is_perfect) {
            this.is_perfect = is_perfect;
        }

        public String getAutoshop() {
            return autoshop;
        }

        public void setAutoshop(String autoshop) {
            this.autoshop = autoshop;
        }

        public String getIs_hongbao() {
            return is_hongbao;
        }

        public void setIs_hongbao(String is_hongbao) {
            this.is_hongbao = is_hongbao;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIndentity() {
            return indentity;
        }

        public void setIndentity(String indentity) {
            this.indentity = indentity;
        }

        public String getWork_status() {
            return work_status;
        }

        public void setWork_status(String work_status) {
            this.work_status = work_status;
        }

        public String getShow_pic() {
            return show_pic;
        }

        public void setShow_pic(String show_pic) {
            this.show_pic = show_pic;
        }

        public CreditLevelBean getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(CreditLevelBean creditLevel) {
            this.creditLevel = creditLevel;
        }

        public List<CertificateInfoBean> getCertificate_info() {
            return certificate_info;
        }

        public void setCertificate_info(List<CertificateInfoBean> certificate_info) {
            this.certificate_info = certificate_info;
        }

        public static class CreditLevelBean {
            /**
             * score_id : 13
             * value : 201194
             * title : 十三级
             * level : 13
             * level_up : 298806
             * level_name : 等级
             * grade_rate : 0.40
             * pic : <img src="data/uploads/sys/mark/2753258f97de07a33e.png" align="absmiddle" title="头衔 ：十三级&#13;&#10;能力值：201194&#13;&#10;等级：13">
             */

            private String score_id;
            private double value;
            private String title;
            private int level;
            private double level_up;
            private String level_name;
            private String grade_rate;
            private String pic;

            public String getScore_id() {
                return score_id;
            }

            public void setScore_id(String score_id) {
                this.score_id = score_id;
            }

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public double getLevel_up() {
                return level_up;
            }

            public void setLevel_up(double level_up) {
                this.level_up = level_up;
            }

            public String getLevel_name() {
                return level_name;
            }

            public void setLevel_name(String level_name) {
                this.level_name = level_name;
            }

            public String getGrade_rate() {
                return grade_rate;
            }

            public void setGrade_rate(String grade_rate) {
                this.grade_rate = grade_rate;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class CertificateInfoBean {
            /**
             * id : 7
             * cid : 7
             * uid : 88
             * username : 王于听
             * authority : 亿塞通
             * pic : data/uploads/2017/04/25/466258ff1e685b7ba.png
             * start_time : 1491321600
             * end_time : 1491408000
             * on_time : 1493049600
             * status : 1
             * activa_status : 1
             * name : PMP认证
             */

            private String id;
            private String cid;
            private String uid;
            private String username;
            private String authority;
            private String pic;
            private String start_time;
            private String end_time;
            private String on_time;
            private String status;
            private String activa_status;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getOn_time() {
                return on_time;
            }

            public void setOn_time(String on_time) {
                this.on_time = on_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getActiva_status() {
                return activa_status;
            }

            public void setActiva_status(String activa_status) {
                this.activa_status = activa_status;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}

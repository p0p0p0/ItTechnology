package com.safetyhuge.chanlian.safetyhuge.Bean.MineBean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/21 0021 20:57
 * 邮箱：995696826@qq.com
 */

public class FananYsBean implements Serializable {
    /**
     * code : 888
     * secess : true
     * data : [{"order_id":"871","order_name":"互联网云政务服务平台解决方案","model_id":"13","seller_uid":"34","seller_username":"fenghaili","order_amount":"3.00","order_status":"confirm","order_time":"1492155390","detail_id":"645","detail_name":"互联网云政务服务平台解决方案","obj_type":"service","obj_id":"647","price":"3.00","num":"1","detail_type":"","service_id":"647","title":"互联网云政务服务平台解决方案","pic":"data/uploads/2017/04/10/392758eb26ffe22e3.png","submit_method":"inside","file_path":"data/uploads/2017/04/10/3131158eb2709ba60f.png","show_status":"交易完成","mark_status":"1","seller_info":{"uid":"34","username":"fenghaili","password":"e10adc3949ba59abbe56e057f20f883e","sec_code":"9b537703478db5e426b43133fed183cb","email":"1528731377@qq.com","user_pic":"","group_id":"1","isvip":"","status":"1","user_type":"","sex":"","marry":"","hometown":"","residency":"","address":"","birthday":"1994-05-19","truename":"","idcard":"","idpic":"","qq":"","msn":"","fax":"","phone":"","mobile":"","indus_id":"0","indus_pid":"0","skill_ids":"DDOS","summary":"","experience":"","reg_time":"1484534830","reg_ip":"192.168.6.159","domain":"","credit":"0.000","balance":"69973.400","balance_status":"","pub_num":"0","take_num":"2","accepted_num":"0","vip_start_time":"","vip_end_time":"","pay_zfb":"","pay_cft":"","pay_bank":"","score":"","buyer_credit":"39500","buyer_good_num":"5","buyer_level":"a:8:{s:8:\"score_id\";s:2:\"10\";s:5:\"value\";s:5:\"39500\";s:5:\"title\";s:6:\"十级\";s:5:\"level\";i:10;s:8:\"level_up\";i:10500;s:10:\"level_name\";s:6:\"等级\";s:10:\"grade_rate\";s:5:\"65.00\";s:3:\"pic\";s:146:\"<img src=\"data/uploads/sys/mark/556258f97d8455998.png\" align=\"absmiddle\" title=\"头衔 ：十级&#13;&#10;信誉值：39500&#13;&#10;等级：10\">\";}","buyer_total_num":"5","seller_credit":"6846","seller_good_num":"7","seller_level":"a:8:{s:8:\"score_id\";s:1:\"8\";s:5:\"value\";s:4:\"6846\";s:5:\"title\";s:6:\"八级\";s:5:\"level\";i:8;s:8:\"level_up\";i:3154;s:10:\"level_name\";s:6:\"等级\";s:10:\"grade_rate\";s:5:\"36.92\";s:3:\"pic\";s:144:\"<img src=\"data/uploads/sys/mark/271158f97d5c0af57.png\" align=\"absmiddle\" title=\"头衔 ：八级&#13;&#10;能力值：6846&#13;&#10;等级：8\">\";}","seller_total_num":"7","studio_id":"","last_login_time":"1492497633","client_status":"","recommend":"1","union":"0","union_assoc":"0","union_rid":"0","union_user":"","province":"0","city":"0","area":"0","is_mail":"0","is_perfect":"0","autoshop":"1","is_hongbao":"","token":"","indentity":"1","rand_code":"r85jha"}}]
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
         * order_id : 871
         * order_name : 互联网云政务服务平台解决方案
         * model_id : 13
         * seller_uid : 34
         * seller_username : fenghaili
         * order_amount : 3.00
         * order_status : confirm
         * order_time : 1492155390
         * detail_id : 645
         * detail_name : 互联网云政务服务平台解决方案
         * obj_type : service
         * obj_id : 647
         * price : 3.00
         * num : 1
         * detail_type :
         * service_id : 647
         * title : 互联网云政务服务平台解决方案
         * pic : data/uploads/2017/04/10/392758eb26ffe22e3.png
         * submit_method : inside
         * file_path : data/uploads/2017/04/10/3131158eb2709ba60f.png
         * show_status : 交易完成
         * mark_status : 1
         * seller_info : {"uid":"34","username":"fenghaili","password":"e10adc3949ba59abbe56e057f20f883e","sec_code":"9b537703478db5e426b43133fed183cb","email":"1528731377@qq.com","user_pic":"","group_id":"1","isvip":"","status":"1","user_type":"","sex":"","marry":"","hometown":"","residency":"","address":"","birthday":"1994-05-19","truename":"","idcard":"","idpic":"","qq":"","msn":"","fax":"","phone":"","mobile":"","indus_id":"0","indus_pid":"0","skill_ids":"DDOS","summary":"","experience":"","reg_time":"1484534830","reg_ip":"192.168.6.159","domain":"","credit":"0.000","balance":"69973.400","balance_status":"","pub_num":"0","take_num":"2","accepted_num":"0","vip_start_time":"","vip_end_time":"","pay_zfb":"","pay_cft":"","pay_bank":"","score":"","buyer_credit":"39500","buyer_good_num":"5","buyer_level":"a:8:{s:8:\"score_id\";s:2:\"10\";s:5:\"value\";s:5:\"39500\";s:5:\"title\";s:6:\"十级\";s:5:\"level\";i:10;s:8:\"level_up\";i:10500;s:10:\"level_name\";s:6:\"等级\";s:10:\"grade_rate\";s:5:\"65.00\";s:3:\"pic\";s:146:\"<img src=\"data/uploads/sys/mark/556258f97d8455998.png\" align=\"absmiddle\" title=\"头衔 ：十级&#13;&#10;信誉值：39500&#13;&#10;等级：10\">\";}","buyer_total_num":"5","seller_credit":"6846","seller_good_num":"7","seller_level":"a:8:{s:8:\"score_id\";s:1:\"8\";s:5:\"value\";s:4:\"6846\";s:5:\"title\";s:6:\"八级\";s:5:\"level\";i:8;s:8:\"level_up\";i:3154;s:10:\"level_name\";s:6:\"等级\";s:10:\"grade_rate\";s:5:\"36.92\";s:3:\"pic\";s:144:\"<img src=\"data/uploads/sys/mark/271158f97d5c0af57.png\" align=\"absmiddle\" title=\"头衔 ：八级&#13;&#10;能力值：6846&#13;&#10;等级：8\">\";}","seller_total_num":"7","studio_id":"","last_login_time":"1492497633","client_status":"","recommend":"1","union":"0","union_assoc":"0","union_rid":"0","union_user":"","province":"0","city":"0","area":"0","is_mail":"0","is_perfect":"0","autoshop":"1","is_hongbao":"","token":"","indentity":"1","rand_code":"r85jha"}
         */

        private String order_id;
        private String order_name;
        private String model_id;
        private String seller_uid;
        private String seller_username;
        private String order_amount;
        private String order_status;
        private String order_time;
        private String detail_id;
        private String detail_name;
        private String obj_type;
        private String obj_id;
        private String price;
        private String num;
        private String detail_type;
        private String service_id;
        private String title;
        private String pic;
        private String submit_method;
        private String file_path;
        private String show_status;
        private String mark_status;
        private SellerInfoBean seller_info;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_name() {
            return order_name;
        }

        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }

        public String getModel_id() {
            return model_id;
        }

        public void setModel_id(String model_id) {
            this.model_id = model_id;
        }

        public String getSeller_uid() {
            return seller_uid;
        }

        public void setSeller_uid(String seller_uid) {
            this.seller_uid = seller_uid;
        }

        public String getSeller_username() {
            return seller_username;
        }

        public void setSeller_username(String seller_username) {
            this.seller_username = seller_username;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getDetail_id() {
            return detail_id;
        }

        public void setDetail_id(String detail_id) {
            this.detail_id = detail_id;
        }

        public String getDetail_name() {
            return detail_name;
        }

        public void setDetail_name(String detail_name) {
            this.detail_name = detail_name;
        }

        public String getObj_type() {
            return obj_type;
        }

        public void setObj_type(String obj_type) {
            this.obj_type = obj_type;
        }

        public String getObj_id() {
            return obj_id;
        }

        public void setObj_id(String obj_id) {
            this.obj_id = obj_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getDetail_type() {
            return detail_type;
        }

        public void setDetail_type(String detail_type) {
            this.detail_type = detail_type;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getSubmit_method() {
            return submit_method;
        }

        public void setSubmit_method(String submit_method) {
            this.submit_method = submit_method;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getShow_status() {
            return show_status;
        }

        public void setShow_status(String show_status) {
            this.show_status = show_status;
        }

        public String getMark_status() {
            return mark_status;
        }

        public void setMark_status(String mark_status) {
            this.mark_status = mark_status;
        }

        public SellerInfoBean getSeller_info() {
            return seller_info;
        }

        public void setSeller_info(SellerInfoBean seller_info) {
            this.seller_info = seller_info;
        }

        public static class SellerInfoBean {
            /**
             * uid : 34
             * username : fenghaili
             * password : e10adc3949ba59abbe56e057f20f883e
             * sec_code : 9b537703478db5e426b43133fed183cb
             * email : 1528731377@qq.com
             * user_pic :
             * group_id : 1
             * isvip :
             * status : 1
             * user_type :
             * sex :
             * marry :
             * hometown :
             * residency :
             * address :
             * birthday : 1994-05-19
             * truename :
             * idcard :
             * idpic :
             * qq :
             * msn :
             * fax :
             * phone :
             * mobile :
             * indus_id : 0
             * indus_pid : 0
             * skill_ids : DDOS
             * summary :
             * experience :
             * reg_time : 1484534830
             * reg_ip : 192.168.6.159
             * domain :
             * credit : 0.000
             * balance : 69973.400
             * balance_status :
             * pub_num : 0
             * take_num : 2
             * accepted_num : 0
             * vip_start_time :
             * vip_end_time :
             * pay_zfb :
             * pay_cft :
             * pay_bank :
             * score :
             * buyer_credit : 39500
             * buyer_good_num : 5
             * buyer_level : a:8:{s:8:"score_id";s:2:"10";s:5:"value";s:5:"39500";s:5:"title";s:6:"十级";s:5:"level";i:10;s:8:"level_up";i:10500;s:10:"level_name";s:6:"等级";s:10:"grade_rate";s:5:"65.00";s:3:"pic";s:146:"<img src="data/uploads/sys/mark/556258f97d8455998.png" align="absmiddle" title="头衔 ：十级&#13;&#10;信誉值：39500&#13;&#10;等级：10">";}
             * buyer_total_num : 5
             * seller_credit : 6846
             * seller_good_num : 7
             * seller_level : a:8:{s:8:"score_id";s:1:"8";s:5:"value";s:4:"6846";s:5:"title";s:6:"八级";s:5:"level";i:8;s:8:"level_up";i:3154;s:10:"level_name";s:6:"等级";s:10:"grade_rate";s:5:"36.92";s:3:"pic";s:144:"<img src="data/uploads/sys/mark/271158f97d5c0af57.png" align="absmiddle" title="头衔 ：八级&#13;&#10;能力值：6846&#13;&#10;等级：8">";}
             * seller_total_num : 7
             * studio_id :
             * last_login_time : 1492497633
             * client_status :
             * recommend : 1
             * union : 0
             * union_assoc : 0
             * union_rid : 0
             * union_user :
             * province : 0
             * city : 0
             * area : 0
             * is_mail : 0
             * is_perfect : 0
             * autoshop : 1
             * is_hongbao :
             * token :
             * indentity : 1
             * rand_code : r85jha
             */

            private String uid;
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
            private String rand_code;

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

            public String getRand_code() {
                return rand_code;
            }

            public void setRand_code(String rand_code) {
                this.rand_code = rand_code;
            }
        }
    }
}

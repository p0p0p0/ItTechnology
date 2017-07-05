package com.safetyhuge.chanlian.safetyhuge.UIActivity.Activity.Thetenderlist.Fragment.DanrenFragment;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/15 0015 19:17
 * 邮箱：995696826@qq.com
 */

public class QuanbuBean implements Serializable {
    /**
     * code : 888
     * secess : true
     * data : {"task_info":[{"task_id":"532","work_id":"","model_id":"1","work_count":"","single_cash":"","profit_rate":"3","task_fail_rate":"0","task_status":"2","task_title":"我是你们的","task_desc":"我们是有些话","task_file":"","task_files":"","task_pic":"","indus_id":"30","indus_pid":"2","uid":"80","username":"123456","start_time":"1491898208","sub_time":"1492589408","end_time":"1492675808","sp_end_time":"","city":"52","task_cash":"200.00","real_cash":"194.00","task_cash_coverage":"","cash_cost":"200.00","credit_cost":"0.00","view_num":"39","work_num":"2","leave_num":"0","focus_num":"0","mark_num":"0","is_delineas":"0","kf_uid":"0","pay_item":"","att_cash":"0.00","contact":"111","unique_num":"","ext_time":"","ext_desc":"","task_union":"0","alipay_trust":"","is_delay":"1","r_task_id":"","is_trust":"0","trust_type":"","is_top":"0","is_auto_bid":"0","point":"","payitem_time":"b:0;","age_requirement":"0","seo_title":"","seo_keyword":"","seo_desc":"","province":"1","area":"844","tasktop":"0","urgent":"0","seohide":"0","workhide":"0","budget":"","teamwork":"0","is_back":"","goldtype":"","giftname":"","lng":"","lat":"","bond_cash":"0.00","bond_rate":"0.00"}],"work_info":[{"work_id":"153","task_uid":"0","task_id":"532","uid":"79","username":"13668844170","work_title":"我是你们的的资料","work_price":"0.000","work_desc":"456789","work_file":"","work_pic":"","work_time":"1492064173","hide_work":"","vote_num":"0","comment_num":"0","work_status":"0","is_view":"0","hasdel":"0","workhide":"0","hasyj":"1","yjje":"0","yjtime":"0","work_hidden":"2","seller_credit":"","seller_good_num":"","residency":"","seller_total_num":"","seller_level":"","attachment":[],"comment":[],"upic":"data/avatar/default/man_big.jpg"},{"work_id":"156","task_uid":"0","task_id":"532","uid":"19","username":"深信服","work_title":"我是你们的的资料","work_price":"0.000","work_desc":"aa","work_file":"","work_pic":"","work_time":"1492221533","hide_work":"","vote_num":"0","comment_num":"0","work_status":"0","is_view":"0","hasdel":"0","workhide":"0","hasyj":"1","yjje":"0","yjtime":"0","work_hidden":"2","seller_credit":"56621","seller_good_num":"10","residency":"","seller_total_num":"10","seller_level":"a:8:{s:8:\"score_id\";s:1:\"6\";s:5:\"value\";i:56621;s:5:\"title\";s:12:\"六级威客\";s:5:\"level\";i:6;s:8:\"level_up\";s:1:\"0\";s:10:\"level_name\";s:6:\"等级\";s:10:\"grade_rate\";s:6:\"100.00\";s:3:\"pic\";s:152:\"<img src=\"data/uploads/sys/mark/1813958b0f1b1a33bc.png\" align=\"absmiddle\" title=\"头衔 ：六级威客&#13;&#10;能力值：56621&#13;&#10;等级：6\">\";}","attachment":[],"comment":[],"upic":"data/avatar/system/4_big.jpg"}],"agree_id":0,"work_choose":null,"plan_complete":null,"plan_confirm":null}
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
         * task_info : [{"task_id":"532","work_id":"","model_id":"1","work_count":"","single_cash":"","profit_rate":"3","task_fail_rate":"0","task_status":"2","task_title":"我是你们的","task_desc":"我们是有些话","task_file":"","task_files":"","task_pic":"","indus_id":"30","indus_pid":"2","uid":"80","username":"123456","start_time":"1491898208","sub_time":"1492589408","end_time":"1492675808","sp_end_time":"","city":"52","task_cash":"200.00","real_cash":"194.00","task_cash_coverage":"","cash_cost":"200.00","credit_cost":"0.00","view_num":"39","work_num":"2","leave_num":"0","focus_num":"0","mark_num":"0","is_delineas":"0","kf_uid":"0","pay_item":"","att_cash":"0.00","contact":"111","unique_num":"","ext_time":"","ext_desc":"","task_union":"0","alipay_trust":"","is_delay":"1","r_task_id":"","is_trust":"0","trust_type":"","is_top":"0","is_auto_bid":"0","point":"","payitem_time":"b:0;","age_requirement":"0","seo_title":"","seo_keyword":"","seo_desc":"","province":"1","area":"844","tasktop":"0","urgent":"0","seohide":"0","workhide":"0","budget":"","teamwork":"0","is_back":"","goldtype":"","giftname":"","lng":"","lat":"","bond_cash":"0.00","bond_rate":"0.00"}]
         * work_info : [{"work_id":"153","task_uid":"0","task_id":"532","uid":"79","username":"13668844170","work_title":"我是你们的的资料","work_price":"0.000","work_desc":"456789","work_file":"","work_pic":"","work_time":"1492064173","hide_work":"","vote_num":"0","comment_num":"0","work_status":"0","is_view":"0","hasdel":"0","workhide":"0","hasyj":"1","yjje":"0","yjtime":"0","work_hidden":"2","seller_credit":"","seller_good_num":"","residency":"","seller_total_num":"","seller_level":"","attachment":[],"comment":[],"upic":"data/avatar/default/man_big.jpg"},{"work_id":"156","task_uid":"0","task_id":"532","uid":"19","username":"深信服","work_title":"我是你们的的资料","work_price":"0.000","work_desc":"aa","work_file":"","work_pic":"","work_time":"1492221533","hide_work":"","vote_num":"0","comment_num":"0","work_status":"0","is_view":"0","hasdel":"0","workhide":"0","hasyj":"1","yjje":"0","yjtime":"0","work_hidden":"2","seller_credit":"56621","seller_good_num":"10","residency":"","seller_total_num":"10","seller_level":"a:8:{s:8:\"score_id\";s:1:\"6\";s:5:\"value\";i:56621;s:5:\"title\";s:12:\"六级威客\";s:5:\"level\";i:6;s:8:\"level_up\";s:1:\"0\";s:10:\"level_name\";s:6:\"等级\";s:10:\"grade_rate\";s:6:\"100.00\";s:3:\"pic\";s:152:\"<img src=\"data/uploads/sys/mark/1813958b0f1b1a33bc.png\" align=\"absmiddle\" title=\"头衔 ：六级威客&#13;&#10;能力值：56621&#13;&#10;等级：6\">\";}","attachment":[],"comment":[],"upic":"data/avatar/system/4_big.jpg"}]
         * agree_id : 0
         * work_choose : null
         * plan_complete : null
         * plan_confirm : null
         */

        private int agree_id;
        private Object work_choose;
        private Object plan_complete;
        private Object plan_confirm;
        private List<TaskInfoBean> task_info;
        private List<WorkInfoBean> work_info;

        public int getAgree_id() {
            return agree_id;
        }

        public void setAgree_id(int agree_id) {
            this.agree_id = agree_id;
        }

        public Object getWork_choose() {
            return work_choose;
        }

        public void setWork_choose(Object work_choose) {
            this.work_choose = work_choose;
        }

        public Object getPlan_complete() {
            return plan_complete;
        }

        public void setPlan_complete(Object plan_complete) {
            this.plan_complete = plan_complete;
        }

        public Object getPlan_confirm() {
            return plan_confirm;
        }

        public void setPlan_confirm(Object plan_confirm) {
            this.plan_confirm = plan_confirm;
        }

        public List<TaskInfoBean> getTask_info() {
            return task_info;
        }

        public void setTask_info(List<TaskInfoBean> task_info) {
            this.task_info = task_info;
        }

        public List<WorkInfoBean> getWork_info() {
            return work_info;
        }

        public void setWork_info(List<WorkInfoBean> work_info) {
            this.work_info = work_info;
        }

        public static class TaskInfoBean {
            /**
             * task_id : 532
             * work_id :
             * model_id : 1
             * work_count :
             * single_cash :
             * profit_rate : 3
             * task_fail_rate : 0
             * task_status : 2
             * task_title : 我是你们的
             * task_desc : 我们是有些话
             * task_file :
             * task_files :
             * task_pic :
             * indus_id : 30
             * indus_pid : 2
             * uid : 80
             * username : 123456
             * start_time : 1491898208
             * sub_time : 1492589408
             * end_time : 1492675808
             * sp_end_time :
             * city : 52
             * task_cash : 200.00
             * real_cash : 194.00
             * task_cash_coverage :
             * cash_cost : 200.00
             * credit_cost : 0.00
             * view_num : 39
             * work_num : 2
             * leave_num : 0
             * focus_num : 0
             * mark_num : 0
             * is_delineas : 0
             * kf_uid : 0
             * pay_item :
             * att_cash : 0.00
             * contact : 111
             * unique_num :
             * ext_time :
             * ext_desc :
             * task_union : 0
             * alipay_trust :
             * is_delay : 1
             * r_task_id :
             * is_trust : 0
             * trust_type :
             * is_top : 0
             * is_auto_bid : 0
             * point :
             * payitem_time : b:0;
             * age_requirement : 0
             * seo_title :
             * seo_keyword :
             * seo_desc :
             * province : 1
             * area : 844
             * tasktop : 0
             * urgent : 0
             * seohide : 0
             * workhide : 0
             * budget :
             * teamwork : 0
             * is_back :
             * goldtype :
             * giftname :
             * lng :
             * lat :
             * bond_cash : 0.00
             * bond_rate : 0.00
             */

            private String task_id;
            private String work_id;
            private String model_id;
            private String work_count;
            private String single_cash;
            private String profit_rate;
            private String task_fail_rate;
            private String task_status;
            private String task_title;
            private String task_desc;
            private String task_file;
            private String task_files;
            private String task_pic;
            private String indus_id;
            private String indus_pid;
            private String uid;
            private String username;
            private String start_time;
            private String sub_time;
            private String end_time;
            private String sp_end_time;
            private String city;
            private String task_cash;
            private String real_cash;
            private String task_cash_coverage;
            private String cash_cost;
            private String credit_cost;
            private String view_num;
            private String work_num;
            private String leave_num;
            private String focus_num;
            private String mark_num;
            private String is_delineas;
            private String kf_uid;
            private String pay_item;
            private String att_cash;
            private String contact;
            private String unique_num;
            private String ext_time;
            private String ext_desc;
            private String task_union;
            private String alipay_trust;
            private String is_delay;
            private String r_task_id;
            private String is_trust;
            private String trust_type;
            private String is_top;
            private String is_auto_bid;
            private String point;
            private String payitem_time;
            private String age_requirement;
            private String seo_title;
            private String seo_keyword;
            private String seo_desc;
            private String province;
            private String area;
            private String tasktop;
            private String urgent;
            private String seohide;
            private String workhide;
            private String budget;
            private String teamwork;
            private String is_back;
            private String goldtype;
            private String giftname;
            private String lng;
            private String lat;
            private String bond_cash;
            private String bond_rate;

            public String getTask_id() {
                return task_id;
            }

            public void setTask_id(String task_id) {
                this.task_id = task_id;
            }

            public String getWork_id() {
                return work_id;
            }

            public void setWork_id(String work_id) {
                this.work_id = work_id;
            }

            public String getModel_id() {
                return model_id;
            }

            public void setModel_id(String model_id) {
                this.model_id = model_id;
            }

            public String getWork_count() {
                return work_count;
            }

            public void setWork_count(String work_count) {
                this.work_count = work_count;
            }

            public String getSingle_cash() {
                return single_cash;
            }

            public void setSingle_cash(String single_cash) {
                this.single_cash = single_cash;
            }

            public String getProfit_rate() {
                return profit_rate;
            }

            public void setProfit_rate(String profit_rate) {
                this.profit_rate = profit_rate;
            }

            public String getTask_fail_rate() {
                return task_fail_rate;
            }

            public void setTask_fail_rate(String task_fail_rate) {
                this.task_fail_rate = task_fail_rate;
            }

            public String getTask_status() {
                return task_status;
            }

            public void setTask_status(String task_status) {
                this.task_status = task_status;
            }

            public String getTask_title() {
                return task_title;
            }

            public void setTask_title(String task_title) {
                this.task_title = task_title;
            }

            public String getTask_desc() {
                return task_desc;
            }

            public void setTask_desc(String task_desc) {
                this.task_desc = task_desc;
            }

            public String getTask_file() {
                return task_file;
            }

            public void setTask_file(String task_file) {
                this.task_file = task_file;
            }

            public String getTask_files() {
                return task_files;
            }

            public void setTask_files(String task_files) {
                this.task_files = task_files;
            }

            public String getTask_pic() {
                return task_pic;
            }

            public void setTask_pic(String task_pic) {
                this.task_pic = task_pic;
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

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getSub_time() {
                return sub_time;
            }

            public void setSub_time(String sub_time) {
                this.sub_time = sub_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getSp_end_time() {
                return sp_end_time;
            }

            public void setSp_end_time(String sp_end_time) {
                this.sp_end_time = sp_end_time;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getTask_cash() {
                return task_cash;
            }

            public void setTask_cash(String task_cash) {
                this.task_cash = task_cash;
            }

            public String getReal_cash() {
                return real_cash;
            }

            public void setReal_cash(String real_cash) {
                this.real_cash = real_cash;
            }

            public String getTask_cash_coverage() {
                return task_cash_coverage;
            }

            public void setTask_cash_coverage(String task_cash_coverage) {
                this.task_cash_coverage = task_cash_coverage;
            }

            public String getCash_cost() {
                return cash_cost;
            }

            public void setCash_cost(String cash_cost) {
                this.cash_cost = cash_cost;
            }

            public String getCredit_cost() {
                return credit_cost;
            }

            public void setCredit_cost(String credit_cost) {
                this.credit_cost = credit_cost;
            }

            public String getView_num() {
                return view_num;
            }

            public void setView_num(String view_num) {
                this.view_num = view_num;
            }

            public String getWork_num() {
                return work_num;
            }

            public void setWork_num(String work_num) {
                this.work_num = work_num;
            }

            public String getLeave_num() {
                return leave_num;
            }

            public void setLeave_num(String leave_num) {
                this.leave_num = leave_num;
            }

            public String getFocus_num() {
                return focus_num;
            }

            public void setFocus_num(String focus_num) {
                this.focus_num = focus_num;
            }

            public String getMark_num() {
                return mark_num;
            }

            public void setMark_num(String mark_num) {
                this.mark_num = mark_num;
            }

            public String getIs_delineas() {
                return is_delineas;
            }

            public void setIs_delineas(String is_delineas) {
                this.is_delineas = is_delineas;
            }

            public String getKf_uid() {
                return kf_uid;
            }

            public void setKf_uid(String kf_uid) {
                this.kf_uid = kf_uid;
            }

            public String getPay_item() {
                return pay_item;
            }

            public void setPay_item(String pay_item) {
                this.pay_item = pay_item;
            }

            public String getAtt_cash() {
                return att_cash;
            }

            public void setAtt_cash(String att_cash) {
                this.att_cash = att_cash;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getUnique_num() {
                return unique_num;
            }

            public void setUnique_num(String unique_num) {
                this.unique_num = unique_num;
            }

            public String getExt_time() {
                return ext_time;
            }

            public void setExt_time(String ext_time) {
                this.ext_time = ext_time;
            }

            public String getExt_desc() {
                return ext_desc;
            }

            public void setExt_desc(String ext_desc) {
                this.ext_desc = ext_desc;
            }

            public String getTask_union() {
                return task_union;
            }

            public void setTask_union(String task_union) {
                this.task_union = task_union;
            }

            public String getAlipay_trust() {
                return alipay_trust;
            }

            public void setAlipay_trust(String alipay_trust) {
                this.alipay_trust = alipay_trust;
            }

            public String getIs_delay() {
                return is_delay;
            }

            public void setIs_delay(String is_delay) {
                this.is_delay = is_delay;
            }

            public String getR_task_id() {
                return r_task_id;
            }

            public void setR_task_id(String r_task_id) {
                this.r_task_id = r_task_id;
            }

            public String getIs_trust() {
                return is_trust;
            }

            public void setIs_trust(String is_trust) {
                this.is_trust = is_trust;
            }

            public String getTrust_type() {
                return trust_type;
            }

            public void setTrust_type(String trust_type) {
                this.trust_type = trust_type;
            }

            public String getIs_top() {
                return is_top;
            }

            public void setIs_top(String is_top) {
                this.is_top = is_top;
            }

            public String getIs_auto_bid() {
                return is_auto_bid;
            }

            public void setIs_auto_bid(String is_auto_bid) {
                this.is_auto_bid = is_auto_bid;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getPayitem_time() {
                return payitem_time;
            }

            public void setPayitem_time(String payitem_time) {
                this.payitem_time = payitem_time;
            }

            public String getAge_requirement() {
                return age_requirement;
            }

            public void setAge_requirement(String age_requirement) {
                this.age_requirement = age_requirement;
            }

            public String getSeo_title() {
                return seo_title;
            }

            public void setSeo_title(String seo_title) {
                this.seo_title = seo_title;
            }

            public String getSeo_keyword() {
                return seo_keyword;
            }

            public void setSeo_keyword(String seo_keyword) {
                this.seo_keyword = seo_keyword;
            }

            public String getSeo_desc() {
                return seo_desc;
            }

            public void setSeo_desc(String seo_desc) {
                this.seo_desc = seo_desc;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getTasktop() {
                return tasktop;
            }

            public void setTasktop(String tasktop) {
                this.tasktop = tasktop;
            }

            public String getUrgent() {
                return urgent;
            }

            public void setUrgent(String urgent) {
                this.urgent = urgent;
            }

            public String getSeohide() {
                return seohide;
            }

            public void setSeohide(String seohide) {
                this.seohide = seohide;
            }

            public String getWorkhide() {
                return workhide;
            }

            public void setWorkhide(String workhide) {
                this.workhide = workhide;
            }

            public String getBudget() {
                return budget;
            }

            public void setBudget(String budget) {
                this.budget = budget;
            }

            public String getTeamwork() {
                return teamwork;
            }

            public void setTeamwork(String teamwork) {
                this.teamwork = teamwork;
            }

            public String getIs_back() {
                return is_back;
            }

            public void setIs_back(String is_back) {
                this.is_back = is_back;
            }

            public String getGoldtype() {
                return goldtype;
            }

            public void setGoldtype(String goldtype) {
                this.goldtype = goldtype;
            }

            public String getGiftname() {
                return giftname;
            }

            public void setGiftname(String giftname) {
                this.giftname = giftname;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getBond_cash() {
                return bond_cash;
            }

            public void setBond_cash(String bond_cash) {
                this.bond_cash = bond_cash;
            }

            public String getBond_rate() {
                return bond_rate;
            }

            public void setBond_rate(String bond_rate) {
                this.bond_rate = bond_rate;
            }
        }

        public static class WorkInfoBean {
            public String getBid_id() {
                return bid_id;
            }

            public void setBid_id(String bid_id) {
                this.bid_id = bid_id;
            }

            public String getQuote() {
                return quote;
            }

            public void setQuote(String quote) {
                this.quote = quote;
            }

            public String getCycle() {
                return cycle;
            }

            public void setCycle(String cycle) {
                this.cycle = cycle;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getBid_status() {
                return bid_status;
            }

            public void setBid_status(String bid_status) {
                this.bid_status = bid_status;
            }

            public String getBid_time() {
                return bid_time;
            }

            public void setBid_time(String bid_time) {
                this.bid_time = bid_time;
            }

            public String getHidden_status() {
                return hidden_status;
            }

            public void setHidden_status(String hidden_status) {
                this.hidden_status = hidden_status;
            }

            public String getExt_status() {
                return ext_status;
            }

            public void setExt_status(String ext_status) {
                this.ext_status = ext_status;
            }

            /**
             * work_id : 153
             * task_uid : 0
             * task_id : 532
             * uid : 79
             * username : 13668844170
             * work_title : 我是你们的的资料
             * work_price : 0.000
             * work_desc : 456789
             * work_file :
             * work_pic :
             * work_time : 1492064173
             * hide_work :
             * vote_num : 0
             * comment_num : 0
             * work_status : 0
             * is_view : 0
             * hasdel : 0
             * workhide : 0
             * hasyj : 1
             * yjje : 0
             * yjtime : 0
             * work_hidden : 2
             * seller_credit :
             * seller_good_num :
             * residency :
             * seller_total_num :
             * seller_level :
             * attachment : []
             * comment : []
             * upic : data/avatar/default/man_big.jpg
             *
             *
             *
             *  "bid_id": "43",
             "task_id": "575",
             "uid": "95",
             "username": "15224418155",
             "quote": "100.00",
             "cycle": "2",
             "area": "北京,东城区,东华门街道",
             "message": "111",
             "bid_status": "0",
             "bid_time": "1492522810",
             "hidden_status": "",
             "ext_status": "",
             "comment_num": "0",
             "is_view": "0",
             "hasdel": "0",
             "workhide": "0",
             "work_hidden": "0",
             "seller_credit": "0",
             "seller_good_num": "0",
             "seller_total_num": "0",
             "seller_level": "",
             "comment": [],
             "upic": "data/avatar/default/man_big.jpg",
             "work_pirze": []
             */
            private String bid_id;
            private String quote;
            private String cycle;
            private String area;
            private String message;
            private String bid_status;
            private String bid_time;
            private String hidden_status;
            private String ext_status;
            //////////////////////////
            private String work_id;
            private String task_uid;
            private String task_id;
            private String uid;
            private String username;
            private String work_title;
            private String work_price;
            private String work_desc;
            private String work_file;
            private String work_pic;
            private String work_time;
            private String hide_work;
            private String vote_num;
            private String comment_num;
            private String work_status;
            private String is_view;
            private String hasdel;
            private String workhide;
            private String hasyj;
            private String yjje;
            private String yjtime;
            private String work_hidden;
            private String seller_credit;
            private String seller_good_num;
            private String residency;
            private String seller_total_num;
            private String seller_level;
            private String upic;
            private List<String> attachment;
            private List<String> comment;
            private List<String> work_pirze;


            public List<String> getWork_pirze() {
                return work_pirze;
            }

            public void setWork_pirze(List<String> work_pirze) {
                this.work_pirze = work_pirze;
            }


            public String getWork_id() {
                return work_id;
            }

            public void setWork_id(String work_id) {
                this.work_id = work_id;
            }

            public String getTask_uid() {
                return task_uid;
            }

            public void setTask_uid(String task_uid) {
                this.task_uid = task_uid;
            }

            public String getTask_id() {
                return task_id;
            }

            public void setTask_id(String task_id) {
                this.task_id = task_id;
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

            public String getWork_title() {
                return work_title;
            }

            public void setWork_title(String work_title) {
                this.work_title = work_title;
            }

            public String getWork_price() {
                return work_price;
            }

            public void setWork_price(String work_price) {
                this.work_price = work_price;
            }

            public String getWork_desc() {
                return work_desc;
            }

            public void setWork_desc(String work_desc) {
                this.work_desc = work_desc;
            }

            public String getWork_file() {
                return work_file;
            }

            public void setWork_file(String work_file) {
                this.work_file = work_file;
            }

            public String getWork_pic() {
                return work_pic;
            }

            public void setWork_pic(String work_pic) {
                this.work_pic = work_pic;
            }

            public String getWork_time() {
                return work_time;
            }

            public void setWork_time(String work_time) {
                this.work_time = work_time;
            }

            public String getHide_work() {
                return hide_work;
            }

            public void setHide_work(String hide_work) {
                this.hide_work = hide_work;
            }

            public String getVote_num() {
                return vote_num;
            }

            public void setVote_num(String vote_num) {
                this.vote_num = vote_num;
            }

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getWork_status() {
                return work_status;
            }

            public void setWork_status(String work_status) {
                this.work_status = work_status;
            }

            public String getIs_view() {
                return is_view;
            }

            public void setIs_view(String is_view) {
                this.is_view = is_view;
            }

            public String getHasdel() {
                return hasdel;
            }

            public void setHasdel(String hasdel) {
                this.hasdel = hasdel;
            }

            public String getWorkhide() {
                return workhide;
            }

            public void setWorkhide(String workhide) {
                this.workhide = workhide;
            }

            public String getHasyj() {
                return hasyj;
            }

            public void setHasyj(String hasyj) {
                this.hasyj = hasyj;
            }

            public String getYjje() {
                return yjje;
            }

            public void setYjje(String yjje) {
                this.yjje = yjje;
            }

            public String getYjtime() {
                return yjtime;
            }

            public void setYjtime(String yjtime) {
                this.yjtime = yjtime;
            }

            public String getWork_hidden() {
                return work_hidden;
            }

            public void setWork_hidden(String work_hidden) {
                this.work_hidden = work_hidden;
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

            public String getResidency() {
                return residency;
            }

            public void setResidency(String residency) {
                this.residency = residency;
            }

            public String getSeller_total_num() {
                return seller_total_num;
            }

            public void setSeller_total_num(String seller_total_num) {
                this.seller_total_num = seller_total_num;
            }

            public String getSeller_level() {
                return seller_level;
            }

            public void setSeller_level(String seller_level) {
                this.seller_level = seller_level;
            }

            public String getUpic() {
                return upic;
            }

            public void setUpic(String upic) {
                this.upic = upic;
            }

            public List<String> getAttachment() {
                return attachment;
            }

            public void setAttachment(List<String> attachment) {
                this.attachment = attachment;
            }

            public List<String> getComment() {
                return comment;
            }

            public void setComment(List<String> comment) {
                this.comment = comment;
            }
        }
    }
}

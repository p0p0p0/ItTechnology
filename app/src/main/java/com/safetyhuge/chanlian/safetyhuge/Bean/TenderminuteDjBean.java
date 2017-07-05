package com.safetyhuge.chanlian.safetyhuge.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/7/3 0003 14:06
 * 邮箱：995696826@qq.com
 */

public class TenderminuteDjBean implements Serializable{
    /**
     * code : 888
     * secess : true
     * data : {"task_info":[{"task_id":"632","work_id":"","model_id":"5","work_count":"","single_cash":"","profit_rate":"10","task_fail_rate":"100","task_status":"9","task_title":"室内适度控制系统安装实施调试","task_desc":"安装实施调试","task_file":"","task_files":"","task_pic":"","indus_id":"34","indus_pid":"160","uid":"91","username":"王逸之","start_time":"1498008270","sub_time":"1505957070","end_time":"1505957070","sp_end_time":"1498637705","city":"224","task_cash":"500000.00","real_cash":"0.30","task_cash_coverage":"41","cash_cost":"500000.00","credit_cost":"0.00","view_num":"20","work_num":"1","leave_num":"0","focus_num":"1","mark_num":"0","is_delineas":"0","kf_uid":"0","pay_item":"","att_cash":"0.00","contact":"18678982888","unique_num":"","ext_time":"","ext_desc":"","task_union":"0","alipay_trust":"","is_delay":"0","r_task_id":"","is_trust":"0","trust_type":"","is_top":"0","is_auto_bid":"0","point":"","payitem_time":"b:0;","age_requirement":"0","seo_title":"","seo_keyword":"","seo_desc":"","province":"15","area":"2526","tasktop":"0","urgent":"0","seohide":"0","workhide":"0","budget":"0.00","teamwork":"0","is_back":"","goldtype":"","giftname":"","lng":"","lat":"","bond_cash":"0.00","bond_rate":"0.00"}],"work_info":[{"bid_id":"60","task_id":"632","uid":"84","username":"13668844170","quote":"1000.00","cycle":"10","area":"北京,东城区,东华门街道","message":"000","bid_status":"4","bid_time":"1498010205","hidden_status":"","ext_status":"","comment_num":"0","is_view":"1","hasdel":"0","workhide":"0","work_hidden":"0","seller_credit":"3225","seller_good_num":"5","seller_total_num":"5","seller_level":"a:8:{s:8:\"score_id\";s:1:\"7\";s:5:\"value\";i:3225;s:5:\"title\";s:6:\"七级\";s:5:\"level\";i:7;s:8:\"level_up\";i:1775;s:10:\"level_name\";s:6:\"等级\";s:10:\"grade_rate\";s:5:\"40.83\";s:3:\"pic\";s:144:\"<img src=\"data/uploads/sys/mark/677958f97ca170aeb.png\" align=\"absmiddle\" title=\"头衔 ：七级&#13;&#10;能力值：3225&#13;&#10;等级：7\">\";}","cx_status":"","comment":[],"plan":[{"plan_id":"57","bid_id":"60","task_id":"632","plan_title":"000","plan_desc":"","plan_step":"1","plan_amount":"1000.00","plan_status":"0","start_time":"1498060800","end_time":"1498320000","over_time":""}],"upic":"data/avatar/000/00/00/84_avatar_big.jpg","work_pirze":[]}],"agree_id":0,"work_choose":null,"plan_complete":null,"plan_confirm":null}
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
         * task_info : [{"task_id":"632","work_id":"","model_id":"5","work_count":"","single_cash":"","profit_rate":"10","task_fail_rate":"100","task_status":"9","task_title":"室内适度控制系统安装实施调试","task_desc":"安装实施调试","task_file":"","task_files":"","task_pic":"","indus_id":"34","indus_pid":"160","uid":"91","username":"王逸之","start_time":"1498008270","sub_time":"1505957070","end_time":"1505957070","sp_end_time":"1498637705","city":"224","task_cash":"500000.00","real_cash":"0.30","task_cash_coverage":"41","cash_cost":"500000.00","credit_cost":"0.00","view_num":"20","work_num":"1","leave_num":"0","focus_num":"1","mark_num":"0","is_delineas":"0","kf_uid":"0","pay_item":"","att_cash":"0.00","contact":"18678982888","unique_num":"","ext_time":"","ext_desc":"","task_union":"0","alipay_trust":"","is_delay":"0","r_task_id":"","is_trust":"0","trust_type":"","is_top":"0","is_auto_bid":"0","point":"","payitem_time":"b:0;","age_requirement":"0","seo_title":"","seo_keyword":"","seo_desc":"","province":"15","area":"2526","tasktop":"0","urgent":"0","seohide":"0","workhide":"0","budget":"0.00","teamwork":"0","is_back":"","goldtype":"","giftname":"","lng":"","lat":"","bond_cash":"0.00","bond_rate":"0.00"}]
         * work_info : [{"bid_id":"60","task_id":"632","uid":"84","username":"13668844170","quote":"1000.00","cycle":"10","area":"北京,东城区,东华门街道","message":"000","bid_status":"4","bid_time":"1498010205","hidden_status":"","ext_status":"","comment_num":"0","is_view":"1","hasdel":"0","workhide":"0","work_hidden":"0","seller_credit":"3225","seller_good_num":"5","seller_total_num":"5","seller_level":"a:8:{s:8:\"score_id\";s:1:\"7\";s:5:\"value\";i:3225;s:5:\"title\";s:6:\"七级\";s:5:\"level\";i:7;s:8:\"level_up\";i:1775;s:10:\"level_name\";s:6:\"等级\";s:10:\"grade_rate\";s:5:\"40.83\";s:3:\"pic\";s:144:\"<img src=\"data/uploads/sys/mark/677958f97ca170aeb.png\" align=\"absmiddle\" title=\"头衔 ：七级&#13;&#10;能力值：3225&#13;&#10;等级：7\">\";}","cx_status":"","comment":[],"plan":[{"plan_id":"57","bid_id":"60","task_id":"632","plan_title":"000","plan_desc":"","plan_step":"1","plan_amount":"1000.00","plan_status":"0","start_time":"1498060800","end_time":"1498320000","over_time":""}],"upic":"data/avatar/000/00/00/84_avatar_big.jpg","work_pirze":[]}]
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
             * task_id : 632
             * work_id :
             * model_id : 5
             * work_count :
             * single_cash :
             * profit_rate : 10
             * task_fail_rate : 100
             * task_status : 9
             * task_title : 室内适度控制系统安装实施调试
             * task_desc : 安装实施调试
             * task_file :
             * task_files :
             * task_pic :
             * indus_id : 34
             * indus_pid : 160
             * uid : 91
             * username : 王逸之
             * start_time : 1498008270
             * sub_time : 1505957070
             * end_time : 1505957070
             * sp_end_time : 1498637705
             * city : 224
             * task_cash : 500000.00
             * real_cash : 0.30
             * task_cash_coverage : 41
             * cash_cost : 500000.00
             * credit_cost : 0.00
             * view_num : 20
             * work_num : 1
             * leave_num : 0
             * focus_num : 1
             * mark_num : 0
             * is_delineas : 0
             * kf_uid : 0
             * pay_item :
             * att_cash : 0.00
             * contact : 18678982888
             * unique_num :
             * ext_time :
             * ext_desc :
             * task_union : 0
             * alipay_trust :
             * is_delay : 0
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
             * province : 15
             * area : 2526
             * tasktop : 0
             * urgent : 0
             * seohide : 0
             * workhide : 0
             * budget : 0.00
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
            /**
             * bid_id : 60
             * task_id : 632
             * uid : 84
             * username : 13668844170
             * quote : 1000.00
             * cycle : 10
             * area : 北京,东城区,东华门街道
             * message : 000
             * bid_status : 4
             * bid_time : 1498010205
             * hidden_status :
             * ext_status :
             * comment_num : 0
             * is_view : 1
             * hasdel : 0
             * workhide : 0
             * work_hidden : 0
             * seller_credit : 3225
             * seller_good_num : 5
             * seller_total_num : 5
             * seller_level : a:8:{s:8:"score_id";s:1:"7";s:5:"value";i:3225;s:5:"title";s:6:"七级";s:5:"level";i:7;s:8:"level_up";i:1775;s:10:"level_name";s:6:"等级";s:10:"grade_rate";s:5:"40.83";s:3:"pic";s:144:"<img src="data/uploads/sys/mark/677958f97ca170aeb.png" align="absmiddle" title="头衔 ：七级&#13;&#10;能力值：3225&#13;&#10;等级：7">";}
             * cx_status :
             * comment : []
             * plan : [{"plan_id":"57","bid_id":"60","task_id":"632","plan_title":"000","plan_desc":"","plan_step":"1","plan_amount":"1000.00","plan_status":"0","start_time":"1498060800","end_time":"1498320000","over_time":""}]
             * upic : data/avatar/000/00/00/84_avatar_big.jpg
             * work_pirze : []
             */

            private String bid_id;
            private String task_id;
            private String uid;
            private String username;
            private String quote;
            private String cycle;
            private String area;
            private String message;
            private String bid_status;
            private String bid_time;
            private String hidden_status;
            private String ext_status;
            private String comment_num;
            private String is_view;
            private String hasdel;
            private String workhide;
            private String work_hidden;
            private String seller_credit;
            private String seller_good_num;
            private String seller_total_num;
            private String seller_level;
            private String cx_status;
            private String upic;
            private List<?> comment;
            private List<PlanBean> plan;
            private List<?> work_pirze;

            public String getBid_id() {
                return bid_id;
            }

            public void setBid_id(String bid_id) {
                this.bid_id = bid_id;
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

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
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

            public String getCx_status() {
                return cx_status;
            }

            public void setCx_status(String cx_status) {
                this.cx_status = cx_status;
            }

            public String getUpic() {
                return upic;
            }

            public void setUpic(String upic) {
                this.upic = upic;
            }

            public List<?> getComment() {
                return comment;
            }

            public void setComment(List<?> comment) {
                this.comment = comment;
            }

            public List<PlanBean> getPlan() {
                return plan;
            }

            public void setPlan(List<PlanBean> plan) {
                this.plan = plan;
            }

            public List<?> getWork_pirze() {
                return work_pirze;
            }

            public void setWork_pirze(List<?> work_pirze) {
                this.work_pirze = work_pirze;
            }

            public static class PlanBean implements Parcelable {
                /**
                 * plan_id : 57
                 * bid_id : 60
                 * task_id : 632
                 * plan_title : 000
                 * plan_desc :
                 * plan_step : 1
                 * plan_amount : 1000.00
                 * plan_status : 0
                 * start_time : 1498060800
                 * end_time : 1498320000
                 * over_time :
                 */

                private String plan_id;
                private String bid_id;
                private String task_id;
                private String plan_title;
                private String plan_desc;
                private String plan_step;
                private String plan_amount;
                private String plan_status;
                private String start_time;
                private String end_time;
                private String over_time;

                public String getPlan_id() {
                    return plan_id;
                }

                public void setPlan_id(String plan_id) {
                    this.plan_id = plan_id;
                }

                public String getBid_id() {
                    return bid_id;
                }

                public void setBid_id(String bid_id) {
                    this.bid_id = bid_id;
                }

                public String getTask_id() {
                    return task_id;
                }

                public void setTask_id(String task_id) {
                    this.task_id = task_id;
                }

                public String getPlan_title() {
                    return plan_title;
                }

                public void setPlan_title(String plan_title) {
                    this.plan_title = plan_title;
                }

                public String getPlan_desc() {
                    return plan_desc;
                }

                public void setPlan_desc(String plan_desc) {
                    this.plan_desc = plan_desc;
                }

                public String getPlan_step() {
                    return plan_step;
                }

                public void setPlan_step(String plan_step) {
                    this.plan_step = plan_step;
                }

                public String getPlan_amount() {
                    return plan_amount;
                }

                public void setPlan_amount(String plan_amount) {
                    this.plan_amount = plan_amount;
                }

                public String getPlan_status() {
                    return plan_status;
                }

                public void setPlan_status(String plan_status) {
                    this.plan_status = plan_status;
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

                public String getOver_time() {
                    return over_time;
                }

                public void setOver_time(String over_time) {
                    this.over_time = over_time;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.plan_id);
                    dest.writeString(this.bid_id);
                    dest.writeString(this.task_id);
                    dest.writeString(this.plan_title);
                    dest.writeString(this.plan_desc);
                    dest.writeString(this.plan_step);
                    dest.writeString(this.plan_amount);
                    dest.writeString(this.plan_status);
                    dest.writeString(this.start_time);
                    dest.writeString(this.end_time);
                    dest.writeString(this.over_time);
                }

                public PlanBean() {
                }

                protected PlanBean(Parcel in) {
                    this.plan_id = in.readString();
                    this.bid_id = in.readString();
                    this.task_id = in.readString();
                    this.plan_title = in.readString();
                    this.plan_desc = in.readString();
                    this.plan_step = in.readString();
                    this.plan_amount = in.readString();
                    this.plan_status = in.readString();
                    this.start_time = in.readString();
                    this.end_time = in.readString();
                    this.over_time = in.readString();
                }

                public static final Parcelable.Creator<PlanBean> CREATOR = new Parcelable.Creator<PlanBean>() {
                    @Override
                    public PlanBean createFromParcel(Parcel source) {
                        return new PlanBean(source);
                    }

                    @Override
                    public PlanBean[] newArray(int size) {
                        return new PlanBean[size];
                    }
                };
            }
        }
    }
}

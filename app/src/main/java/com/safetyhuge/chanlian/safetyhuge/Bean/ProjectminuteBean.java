package com.safetyhuge.chanlian.safetyhuge.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/11 0011 11:13
 * 邮箱：995696826@qq.com
 */

public class ProjectminuteBean implements  Parcelable {

    /**
     * code : 888
     * secess : true
     * data : [{"task_id":"427","work_id":"","model_id":"1","work_count":"","single_cash":"","profit_rate":"3","task_fail_rate":"0","task_status":"2","task_title":"带附件111","task_desc":"&lt;p&gt;111&lt;br/&gt;&lt;/p&gt;","task_file":"845,846,847,848","task_files":"","task_pic":"http://192.168.6.201/data/uploads/2017/03/23/1722058d3a965029a0.gif,http://192.168.6.201/data/uploads/2017/03/23/1416758d3a9685ea98.png,http://192.168.6.201/data/uploads/2017/03/23/844758d3a97185061.png","indus_id":"29","indus_pid":"2","uid":"20","username":"亿赛通","start_time":"1490266492","sub_time":"1498215292","end_time":"1498301692","sp_end_time":"1490942993","city":"37","task_cash":"333.00","real_cash":"323.01","task_cash_coverage":"","cash_cost":"333.00","credit_cost":"0.00","view_num":"18","work_num":"0","leave_num":"0","focus_num":"0","mark_num":"0","is_delineas":"0","kf_uid":"0","pay_item":"","att_cash":"0.00","contact":"18293798777","unique_num":"","ext_time":"","ext_desc":"","task_union":"0","alipay_trust":"","is_delay":"2","r_task_id":"","is_trust":"0","trust_type":"","is_top":"0","is_auto_bid":"0","point":"","payitem_time":"","age_requirement":"0","seo_title":"","seo_keyword":"","seo_desc":"","province":"1","area":"567","tasktop":"0","urgent":"0","seohide":"0","workhide":"0","budget":"","teamwork":"0","is_back":"","goldtype":"","giftname":"","lng":"","lat":"","bond_cash":"0.00","bond_rate":"0.00","upic":"data/avatar/system/14_big.jpg","out_cash":333,"work_hand":true,"delay":false,"task_pay":false,"file":[{"file_name":"畅联LOGO--透明.gif","file_path":"data/uploads/2017/03/23/1722058d3a965029a0.gif"},{"file_name":"002.png","file_path":"data/uploads/2017/03/23/1416758d3a9685ea98.png"},{"file_name":"img_carousel_ph.png","file_path":"data/uploads/2017/03/23/844758d3a97185061.png"},{"file_name":"信纸.txt","file_path":"data/uploads/2017/03/23/1536258d3a978ad313.txt"}],"show_status":"威客资料提交","province_name":"北京","city_name":"东城区","area_name":"东华门街道","favorite":false,"model_name":null,"show_cash":"￥ 333.00","delivery":0}]
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
         * task_id : 427
         * work_id :
         * model_id : 1
         * work_count :
         * single_cash :
         * profit_rate : 3
         * task_fail_rate : 0
         * task_status : 2
         * task_title : 带附件111
         * task_desc : &lt;p&gt;111&lt;br/&gt;&lt;/p&gt;
         * task_file : 845,846,847,848
         * task_files :
         * task_pic : http://192.168.6.201/data/uploads/2017/03/23/1722058d3a965029a0.gif,http://192.168.6.201/data/uploads/2017/03/23/1416758d3a9685ea98.png,http://192.168.6.201/data/uploads/2017/03/23/844758d3a97185061.png
         * indus_id : 29
         * indus_pid : 2
         * uid : 20
         * username : 亿赛通
         * start_time : 1490266492
         * sub_time : 1498215292
         * end_time : 1498301692
         * sp_end_time : 1490942993
         * city : 37
         * task_cash : 333.00
         * real_cash : 323.01
         * task_cash_coverage :
         * cash_cost : 333.00
         * credit_cost : 0.00
         * view_num : 18
         * work_num : 0
         * leave_num : 0
         * focus_num : 0
         * mark_num : 0
         * is_delineas : 0
         * kf_uid : 0
         * pay_item :
         * att_cash : 0.00
         * contact : 18293798777
         * unique_num :
         * ext_time :
         * ext_desc :
         * task_union : 0
         * alipay_trust :
         * is_delay : 2
         * r_task_id :
         * is_trust : 0
         * trust_type :
         * is_top : 0
         * is_auto_bid : 0
         * point :
         * payitem_time :
         * age_requirement : 0
         * seo_title :
         * seo_keyword :
         * seo_desc :
         * province : 1
         * area : 567
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
         * upic : data/avatar/system/14_big.jpg
         * out_cash : 333
         * work_hand : true
         * delay : false
         * task_pay : false
         * file : [{"file_name":"畅联LOGO--透明.gif","file_path":"data/uploads/2017/03/23/1722058d3a965029a0.gif"},{"file_name":"002.png","file_path":"data/uploads/2017/03/23/1416758d3a9685ea98.png"},{"file_name":"img_carousel_ph.png","file_path":"data/uploads/2017/03/23/844758d3a97185061.png"},{"file_name":"信纸.txt","file_path":"data/uploads/2017/03/23/1536258d3a978ad313.txt"}]
         * show_status : 威客资料提交
         * province_name : 北京
         * city_name : 东城区
         * area_name : 东华门街道
         * favorite : false
         * model_name : null
         * show_cash : ￥ 333.00
         * delivery : 0
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
        private String upic;
        private String out_cash;
        private boolean work_hand;
        private boolean delay;
        private boolean task_pay;
        private String show_status;
        private String province_name;
        private String city_name;
        private String area_name;
        private boolean favorite;
        private Object model_name;
        private String show_cash;
        private int delivery;
        private List<FileBean> file;

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

        public String getUpic() {
            return upic;
        }

        public void setUpic(String upic) {
            this.upic = upic;
        }

        public String getOut_cash() {
            return out_cash;
        }

        public void setOut_cash(String out_cash) {
            this.out_cash = out_cash;
        }

        public boolean isWork_hand() {
            return work_hand;
        }

        public void setWork_hand(boolean work_hand) {
            this.work_hand = work_hand;
        }

        public boolean isDelay() {
            return delay;
        }

        public void setDelay(boolean delay) {
            this.delay = delay;
        }
        public boolean getDelay(){
            return delay;
        }

        public boolean isTask_pay() {
            return task_pay;
        }

        public void setTask_pay(boolean task_pay) {
            this.task_pay = task_pay;
        }

        public String getShow_status() {
            return show_status;
        }

        public void setShow_status(String show_status) {
            this.show_status = show_status;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public Object getModel_name() {
            return model_name;
        }

        public void setModel_name(Object model_name) {
            this.model_name = model_name;
        }

        public String getShow_cash() {
            return show_cash;
        }

        public void setShow_cash(String show_cash) {
            this.show_cash = show_cash;
        }

        public int getDelivery() {
            return delivery;
        }

        public void setDelivery(int delivery) {
            this.delivery = delivery;
        }

        public List<FileBean> getFile() {
            return file;
        }

        public void setFile(List<FileBean> file) {
            this.file = file;
        }

        public static class FileBean implements Parcelable {
            /**
             * file_name : 畅联LOGO--透明.gif
             * file_path : data/uploads/2017/03/23/1722058d3a965029a0.gif
             */

            private String file_name;
            private String file_path;

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.file_name);
                dest.writeString(this.file_path);
            }

            public FileBean() {
            }

            protected FileBean(Parcel in) {
                this.file_name = in.readString();
                this.file_path = in.readString();
            }

            public static final Creator<FileBean> CREATOR = new Creator<FileBean>() {
                @Override
                public FileBean createFromParcel(Parcel source) {
                    return new FileBean(source);
                }

                @Override
                public FileBean[] newArray(int size) {
                    return new FileBean[size];
                }
            };
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.secess);
        dest.writeList(this.data);
    }

    public ProjectminuteBean() {
    }

    protected ProjectminuteBean(Parcel in) {
        this.code = in.readString();
        this.secess = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProjectminuteBean> CREATOR = new Parcelable.Creator<ProjectminuteBean>() {
        @Override
        public ProjectminuteBean createFromParcel(Parcel source) {
            return new ProjectminuteBean(source);
        }

        @Override
        public ProjectminuteBean[] newArray(int size) {
            return new ProjectminuteBean[size];
        }
    };
}

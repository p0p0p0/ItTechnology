package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chinalink on 2017/3/28 0028.
 */

public class GoodsBean implements Serializable {

    /**
     * code : 888
     * secess : true
     * data : {"service_id":"593","model_id":"6","service_type":"","shop_id":"2","uid":"1","username":"admin","fw_status":"0","phone":"","profit_rate":"1","indus_id":"9","indus_pid":"3","indus_path":"","cus_cate_id":"","num":"-1","title":"出租法拉利劳斯莱斯兰博基尼豪华跑车自驾商务出行","price":"8000.00","unite_price":"天","service_time":"","unit_time":"","obj_name":"","pic":"data/uploads/2017/03/31/1219858de1d7e22ed1.jpg,data/uploads/2017/03/31/1338658de1d81568b2.jpg","ad_pic":"","area_range":"","key_word":"","submit_method":"outside","file_path":"","content":"&lt;p&gt;出租法拉利劳斯莱斯兰博基尼豪华跑车自驾商务出行&lt;/p&gt;","on_time":"1490951563","is_stop":"0","sale_num":"23","focus_num":"1","mark_num":"14","leave_num":"0","views":"36","pay_item":"","att_cash":"0.00","refresh_time":"","unique_num":"","total_sale":"184000.00","service_status":"2","is_top":"0","point":"","city":"224","payitem_time":"a:1:{s:3:\"top\";i:7;}","exist_time":"","confirm_max":"99","seo_title":"","seo_keyword":"","seo_desc":"","province":"15","area":"2530","goodstop":"0","overdue_type":"","edit_status":"3","lng":"","lat":"","brand_id":"8","sales":"6","old_price":"","pay_day":"","favorite":false,"desired":false,"province_name":"山东省","city_name":"青岛市","area_name":"李沧区","indus_pname":"系统集成","indus_name":"负载平衡","shop_name":"超级管理员专卖店","show_pic":["data/uploads/2017/03/31/1219858de1d7e22ed1.jpg","data/uploads/2017/03/31/1338658de1d81568b2.jpg"],"seller_pic":"data/avatar/system/1_big.jpg","mark_info":[{"mark_id":"279","model_code":"goods","origin_id":"593","obj_id":"778","order_id":"","obj_cash":"7920","mark_status":"2","mark_content":"我们","mark_contents":"","mark_time":"1491905289","uid":"1","username":"admin","mark_max_time":"1492164489","by_uid":"19","by_username":"深信服","aid":"1,2,3","aid_star":"5.0,3.0,4.0","mark_value":"3960.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/19_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":3,"count":1,"avg":"3.0"},{"aid_name":"态度","star":4,"count":1,"avg":"4.0"}]},{"mark_id":"259","model_code":"goods","origin_id":"593","obj_id":"733","order_id":"","obj_cash":"15840","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491370181","uid":"1","username":"admin","mark_max_time":"1491629381","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"15840.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"257","model_code":"goods","origin_id":"593","obj_id":"730","order_id":"","obj_cash":"39600","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491369143","uid":"1","username":"admin","mark_max_time":"1491628343","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"39600.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"255","model_code":"goods","origin_id":"593","obj_id":"727","order_id":"","obj_cash":"23760","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491369092","uid":"1","username":"admin","mark_max_time":"1491628292","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"23760.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"251","model_code":"goods","origin_id":"593","obj_id":"725","order_id":"","obj_cash":"23760","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491364992","uid":"1","username":"admin","mark_max_time":"1491624192","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"23760.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"249","model_code":"goods","origin_id":"593","obj_id":"723","order_id":"","obj_cash":"15840","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491364781","uid":"1","username":"admin","mark_max_time":"1491623981","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"15840.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"247","model_code":"goods","origin_id":"593","obj_id":"719","order_id":"","obj_cash":"55440","mark_status":"1","mark_content":"很好 很强","mark_contents":"","mark_time":"1491363725","uid":"1","username":"admin","mark_max_time":"1491622925","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"55440.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]}]}
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
         * service_id : 593
         * model_id : 6
         * service_type :
         * shop_id : 2
         * uid : 1
         * username : admin
         * fw_status : 0
         * phone :
         * profit_rate : 1
         * indus_id : 9
         * indus_pid : 3
         * indus_path :
         * cus_cate_id :
         * num : -1
         * title : 出租法拉利劳斯莱斯兰博基尼豪华跑车自驾商务出行
         * price : 8000.00
         * unite_price : 天
         * service_time :
         * unit_time :
         * obj_name :
         * pic : data/uploads/2017/03/31/1219858de1d7e22ed1.jpg,data/uploads/2017/03/31/1338658de1d81568b2.jpg
         * ad_pic :
         * area_range :
         * key_word :
         * submit_method : outside
         * file_path :
         * content : &lt;p&gt;出租法拉利劳斯莱斯兰博基尼豪华跑车自驾商务出行&lt;/p&gt;
         * on_time : 1490951563
         * is_stop : 0
         * sale_num : 23
         * focus_num : 1
         * mark_num : 14
         * leave_num : 0
         * views : 36
         * pay_item :
         * att_cash : 0.00
         * refresh_time :
         * unique_num :
         * total_sale : 184000.00
         * service_status : 2
         * is_top : 0
         * point :
         * city : 224
         * payitem_time : a:1:{s:3:"top";i:7;}
         * exist_time :
         * confirm_max : 99
         * seo_title :
         * seo_keyword :
         * seo_desc :
         * province : 15
         * area : 2530
         * goodstop : 0
         * overdue_type :
         * edit_status : 3
         * lng :
         * lat :
         * brand_id : 8
         * sales : 6
         * old_price :
         * pay_day :
         * favorite : false
         * desired : false
         * province_name : 山东省
         * city_name : 青岛市
         * area_name : 李沧区
         * indus_pname : 系统集成
         * indus_name : 负载平衡
         * shop_name : 超级管理员专卖店
         * show_pic : ["data/uploads/2017/03/31/1219858de1d7e22ed1.jpg","data/uploads/2017/03/31/1338658de1d81568b2.jpg"]
         * seller_pic : data/avatar/system/1_big.jpg
         * mark_info : [{"mark_id":"279","model_code":"goods","origin_id":"593","obj_id":"778","order_id":"","obj_cash":"7920","mark_status":"2","mark_content":"我们","mark_contents":"","mark_time":"1491905289","uid":"1","username":"admin","mark_max_time":"1492164489","by_uid":"19","by_username":"深信服","aid":"1,2,3","aid_star":"5.0,3.0,4.0","mark_value":"3960.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/19_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":3,"count":1,"avg":"3.0"},{"aid_name":"态度","star":4,"count":1,"avg":"4.0"}]},{"mark_id":"259","model_code":"goods","origin_id":"593","obj_id":"733","order_id":"","obj_cash":"15840","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491370181","uid":"1","username":"admin","mark_max_time":"1491629381","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"15840.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"257","model_code":"goods","origin_id":"593","obj_id":"730","order_id":"","obj_cash":"39600","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491369143","uid":"1","username":"admin","mark_max_time":"1491628343","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"39600.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"255","model_code":"goods","origin_id":"593","obj_id":"727","order_id":"","obj_cash":"23760","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491369092","uid":"1","username":"admin","mark_max_time":"1491628292","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"23760.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"251","model_code":"goods","origin_id":"593","obj_id":"725","order_id":"","obj_cash":"23760","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491364992","uid":"1","username":"admin","mark_max_time":"1491624192","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"23760.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"249","model_code":"goods","origin_id":"593","obj_id":"723","order_id":"","obj_cash":"15840","mark_status":"1","mark_content":"","mark_contents":"","mark_time":"1491364781","uid":"1","username":"admin","mark_max_time":"1491623981","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"15840.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]},{"mark_id":"247","model_code":"goods","origin_id":"593","obj_id":"719","order_id":"","obj_cash":"55440","mark_status":"1","mark_content":"很好 很强","mark_contents":"","mark_time":"1491363725","uid":"1","username":"admin","mark_max_time":"1491622925","by_uid":"20","by_username":"亿赛通","aid":"1,2,3","aid_star":"5.0,5.0,5.0","mark_value":"55440.00","mark_type":"2","mark_count":"1","plxxzt":"0","pic":"data/avatar/000/00/00/20_avatar_big.jpg","aidinfo":[{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":5,"count":1,"avg":"5.0"},{"aid_name":"态度","star":5,"count":1,"avg":"5.0"}]}]
         */

        private String service_id;
        private String model_id;
        private String service_type;
        private String shop_id;
        private String uid;
        private String username;
        private String fw_status;
        private String phone;
        private String profit_rate;
        private String indus_id;
        private String indus_pid;
        private String indus_path;
        private String cus_cate_id;
        private int num;
        private String title;
        private double price;
        private String unite_price;
        private String service_time;
        private String unit_time;
        private String obj_name;
        private String pic;
        private String ad_pic;
        private String area_range;
        private String key_word;
        private String submit_method;
        private String file_path;
        private String content;
        private String on_time;
        private String is_stop;
        private String sale_num;
        private String focus_num;
        private String mark_num;
        private String leave_num;
        private String views;
        private String pay_item;
        private String att_cash;
        private String refresh_time;
        private String unique_num;
        private String total_sale;
        private String service_status;
        private String is_top;
        private String point;
        private String city;
        private String payitem_time;
        private String exist_time;
        private String confirm_max;
        private String seo_title;
        private String seo_keyword;
        private String seo_desc;
        private String province;
        private String area;
        private String goodstop;
        private String overdue_type;
        private String edit_status;
        private String lng;
        private String lat;
        private String brand_id;
        private String sales;
        private String old_price;
        private String pay_day;
        private boolean favorite;
        private boolean desired;
        private String province_name;
        private String city_name;
        private String area_name;
        private String indus_pname;
        private String indus_name;
        private String shop_name;
        private String seller_pic;
        private List<String> show_pic;
        private List<MarkInfoBean> mark_info;
        private String invoice;

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getModel_id() {
            return model_id;
        }

        public void setModel_id(String model_id) {
            this.model_id = model_id;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
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

        public String getFw_status() {
            return fw_status;
        }

        public void setFw_status(String fw_status) {
            this.fw_status = fw_status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProfit_rate() {
            return profit_rate;
        }

        public void setProfit_rate(String profit_rate) {
            this.profit_rate = profit_rate;
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

        public String getIndus_path() {
            return indus_path;
        }

        public void setIndus_path(String indus_path) {
            this.indus_path = indus_path;
        }

        public String getCus_cate_id() {
            return cus_cate_id;
        }

        public void setCus_cate_id(String cus_cate_id) {
            this.cus_cate_id = cus_cate_id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getUnite_price() {
            return unite_price;
        }

        public void setUnite_price(String unite_price) {
            this.unite_price = unite_price;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public String getUnit_time() {
            return unit_time;
        }

        public void setUnit_time(String unit_time) {
            this.unit_time = unit_time;
        }

        public String getObj_name() {
            return obj_name;
        }

        public void setObj_name(String obj_name) {
            this.obj_name = obj_name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getAd_pic() {
            return ad_pic;
        }

        public void setAd_pic(String ad_pic) {
            this.ad_pic = ad_pic;
        }

        public String getArea_range() {
            return area_range;
        }

        public void setArea_range(String area_range) {
            this.area_range = area_range;
        }

        public String getKey_word() {
            return key_word;
        }

        public void setKey_word(String key_word) {
            this.key_word = key_word;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOn_time() {
            return on_time;
        }

        public void setOn_time(String on_time) {
            this.on_time = on_time;
        }

        public String getIs_stop() {
            return is_stop;
        }

        public void setIs_stop(String is_stop) {
            this.is_stop = is_stop;
        }

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
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

        public String getLeave_num() {
            return leave_num;
        }

        public void setLeave_num(String leave_num) {
            this.leave_num = leave_num;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
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

        public String getRefresh_time() {
            return refresh_time;
        }

        public void setRefresh_time(String refresh_time) {
            this.refresh_time = refresh_time;
        }

        public String getUnique_num() {
            return unique_num;
        }

        public void setUnique_num(String unique_num) {
            this.unique_num = unique_num;
        }

        public String getTotal_sale() {
            return total_sale;
        }

        public void setTotal_sale(String total_sale) {
            this.total_sale = total_sale;
        }

        public String getService_status() {
            return service_status;
        }

        public void setService_status(String service_status) {
            this.service_status = service_status;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPayitem_time() {
            return payitem_time;
        }

        public void setPayitem_time(String payitem_time) {
            this.payitem_time = payitem_time;
        }

        public String getExist_time() {
            return exist_time;
        }

        public void setExist_time(String exist_time) {
            this.exist_time = exist_time;
        }

        public String getConfirm_max() {
            return confirm_max;
        }

        public void setConfirm_max(String confirm_max) {
            this.confirm_max = confirm_max;
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

        public String getGoodstop() {
            return goodstop;
        }

        public void setGoodstop(String goodstop) {
            this.goodstop = goodstop;
        }

        public String getOverdue_type() {
            return overdue_type;
        }

        public void setOverdue_type(String overdue_type) {
            this.overdue_type = overdue_type;
        }

        public String getEdit_status() {
            return edit_status;
        }

        public void setEdit_status(String edit_status) {
            this.edit_status = edit_status;
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

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getOld_price() {
            return old_price;
        }

        public void setOld_price(String old_price) {
            this.old_price = old_price;
        }

        public String getPay_day() {
            return pay_day;
        }

        public void setPay_day(String pay_day) {
            this.pay_day = pay_day;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public boolean isDesired() {
            return desired;
        }

        public void setDesired(boolean desired) {
            this.desired = desired;
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

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getSeller_pic() {
            return seller_pic;
        }

        public void setSeller_pic(String seller_pic) {
            this.seller_pic = seller_pic;
        }

        public List<String> getShow_pic() {
            return show_pic;
        }

        public void setShow_pic(List<String> show_pic) {
            this.show_pic = show_pic;
        }

        public List<MarkInfoBean> getMark_info() {
            return mark_info;
        }

        public void setMark_info(List<MarkInfoBean> mark_info) {
            this.mark_info = mark_info;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public static class MarkInfoBean {
            /**
             * mark_id : 279
             * model_code : goods
             * origin_id : 593
             * obj_id : 778
             * order_id :
             * obj_cash : 7920
             * mark_status : 2
             * mark_content : 我们
             * mark_contents :
             * mark_time : 1491905289
             * uid : 1
             * username : admin
             * mark_max_time : 1492164489
             * by_uid : 19
             * by_username : 深信服
             * aid : 1,2,3
             * aid_star : 5.0,3.0,4.0
             * mark_value : 3960.00
             * mark_type : 2
             * mark_count : 1
             * plxxzt : 0
             * pic : data/avatar/000/00/00/19_avatar_big.jpg
             * aidinfo : [{"aid_name":"速度","star":5,"count":1,"avg":"5.0"},{"aid_name":"质量","star":3,"count":1,"avg":"3.0"},{"aid_name":"态度","star":4,"count":1,"avg":"4.0"}]
             */

            private String mark_id;
            private String model_code;
            private String origin_id;
            private String obj_id;
            private String order_id;
            private String obj_cash;
            private String mark_status;
            private String mark_content;
            private String mark_contents;
            private String mark_time;
            private String uid;
            private String username;
            private String mark_max_time;
            private String by_uid;
            private String by_username;
            private String aid;
            private String aid_star;
            private String mark_value;
            private String mark_type;
            private String mark_count;
            private String plxxzt;
            private String pic;
            private List<AidinfoBean> aidinfo;

            public String getMark_id() {
                return mark_id;
            }

            public void setMark_id(String mark_id) {
                this.mark_id = mark_id;
            }

            public String getModel_code() {
                return model_code;
            }

            public void setModel_code(String model_code) {
                this.model_code = model_code;
            }

            public String getOrigin_id() {
                return origin_id;
            }

            public void setOrigin_id(String origin_id) {
                this.origin_id = origin_id;
            }

            public String getObj_id() {
                return obj_id;
            }

            public void setObj_id(String obj_id) {
                this.obj_id = obj_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getObj_cash() {
                return obj_cash;
            }

            public void setObj_cash(String obj_cash) {
                this.obj_cash = obj_cash;
            }

            public String getMark_status() {
                return mark_status;
            }

            public void setMark_status(String mark_status) {
                this.mark_status = mark_status;
            }

            public String getMark_content() {
                return mark_content;
            }

            public void setMark_content(String mark_content) {
                this.mark_content = mark_content;
            }

            public String getMark_contents() {
                return mark_contents;
            }

            public void setMark_contents(String mark_contents) {
                this.mark_contents = mark_contents;
            }

            public String getMark_time() {
                return mark_time;
            }

            public void setMark_time(String mark_time) {
                this.mark_time = mark_time;
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

            public String getMark_max_time() {
                return mark_max_time;
            }

            public void setMark_max_time(String mark_max_time) {
                this.mark_max_time = mark_max_time;
            }

            public String getBy_uid() {
                return by_uid;
            }

            public void setBy_uid(String by_uid) {
                this.by_uid = by_uid;
            }

            public String getBy_username() {
                return by_username;
            }

            public void setBy_username(String by_username) {
                this.by_username = by_username;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getAid_star() {
                return aid_star;
            }

            public void setAid_star(String aid_star) {
                this.aid_star = aid_star;
            }

            public String getMark_value() {
                return mark_value;
            }

            public void setMark_value(String mark_value) {
                this.mark_value = mark_value;
            }

            public String getMark_type() {
                return mark_type;
            }

            public void setMark_type(String mark_type) {
                this.mark_type = mark_type;
            }

            public String getMark_count() {
                return mark_count;
            }

            public void setMark_count(String mark_count) {
                this.mark_count = mark_count;
            }

            public String getPlxxzt() {
                return plxxzt;
            }

            public void setPlxxzt(String plxxzt) {
                this.plxxzt = plxxzt;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public List<AidinfoBean> getAidinfo() {
                return aidinfo;
            }

            public void setAidinfo(List<AidinfoBean> aidinfo) {
                this.aidinfo = aidinfo;
            }

            public static class AidinfoBean {
                /**
                 * aid_name : 速度
                 * star : 5
                 * count : 1
                 * avg : 5.0
                 */

                private String aid_name;
                private Object star;
                private int count;
                private double avg;

                public String getAid_name() {
                    return aid_name;
                }

                public void setAid_name(String aid_name) {
                    this.aid_name = aid_name;
                }

                public Object getStar() {
                    return star;
                }

                public void setStar(int star) {
                    this.star = star;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }
            }
        }
    }
}

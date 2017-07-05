package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/6/20 0020 13:51
 * 邮箱：995696826@qq.com
 */

public class JiShuBean implements Serializable {

    /**
     * code : 888
     * secess : true
     * data : {"service_id":"753","model_id":"14","service_type":"","shop_id":"53","uid":"105","username":"吕轻侯","fw_status":"0","phone":"","profit_rate":"1","indus_id":"31","indus_pid":"2","indus_path":"","cus_cate_id":"","num":"0","title":"可视是安全的基础","price":"66.66","unite_price":"","service_time":"","unit_time":"","obj_name":"","pic":"data/uploads/2017/05/08/969859101215b5a8e.png","ad_pic":"","area_range":"","key_word":"","submit_method":"inside","file_path":"data/uploads/2017/05/08/1305259101196e71af.mp4","content":"&lt;p&gt;网络安全建设就是识别并消除网络和业务的风险，保障网络的合法、合规，以及业务的可靠、可信。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;&lt;p&gt;过去进行网络安全建设，主要是通过防御设备划分安全边界，构建以隔离为基础、以防护为核心，通过IP、端口和特征进行风险判断的安全体系。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","on_time":"1494225434","is_stop":"0","sale_num":"0","focus_num":"0","mark_num":"2","leave_num":"0","views":"3","pay_item":"","att_cash":"0.00","refresh_time":"","unique_num":"","total_sale":"0.00","service_status":"2","is_top":"0","point":"","city":"0","payitem_time":"a:1:{s:3:\"top\";i:848894;}","exist_time":"","confirm_max":"1","seo_title":"","seo_keyword":"","seo_desc":"","province":"0","area":"0","goodstop":"0","overdue_type":"","edit_status":"2","lng":"","lat":"","brand_id":"0","sales":"8","old_price":"","pay_day":"","gid":"","invoice":"0","invoice_type":"","province_name":null,"city_name":null,"area_name":null,"favorite":false,"desired":false,"pay_flag":false,"indus_pname":"网络安全","indus_name":"上网行为管理","shop_name":"吕轻侯","show_pic":["data/uploads/2017/05/08/969859101215b5a8e.png"],"seller_pic":"data/avatar/000/00/01/05_avatar_big.jpg","mark_info":[],"user_pic":"data/avatar/000/00/01/05_avatar_big.jpg"}
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
         * service_id : 753
         * model_id : 14
         * service_type :
         * shop_id : 53
         * uid : 105
         * username : 吕轻侯
         * fw_status : 0
         * phone :
         * profit_rate : 1
         * indus_id : 31
         * indus_pid : 2
         * indus_path :
         * cus_cate_id :
         * num : 0
         * title : 可视是安全的基础
         * price : 66.66
         * unite_price :
         * service_time :
         * unit_time :
         * obj_name :
         * pic : data/uploads/2017/05/08/969859101215b5a8e.png
         * ad_pic :
         * area_range :
         * key_word :
         * submit_method : inside
         * file_path : data/uploads/2017/05/08/1305259101196e71af.mp4
         * content : &lt;p&gt;网络安全建设就是识别并消除网络和业务的风险，保障网络的合法、合规，以及业务的可靠、可信。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;&lt;p&gt;过去进行网络安全建设，主要是通过防御设备划分安全边界，构建以隔离为基础、以防护为核心，通过IP、端口和特征进行风险判断的安全体系。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;
         * on_time : 1494225434
         * is_stop : 0
         * sale_num : 0
         * focus_num : 0
         * mark_num : 2
         * leave_num : 0
         * views : 3
         * pay_item :
         * att_cash : 0.00
         * refresh_time :
         * unique_num :
         * total_sale : 0.00
         * service_status : 2
         * is_top : 0
         * point :
         * city : 0
         * payitem_time : a:1:{s:3:"top";i:848894;}
         * exist_time :
         * confirm_max : 1
         * seo_title :
         * seo_keyword :
         * seo_desc :
         * province : 0
         * area : 0
         * goodstop : 0
         * overdue_type :
         * edit_status : 2
         * lng :
         * lat :
         * brand_id : 0
         * sales : 8
         * old_price :
         * pay_day :
         * gid :
         * invoice : 0
         * invoice_type :
         * province_name : null
         * city_name : null
         * area_name : null
         * favorite : false
         * desired : false
         * pay_flag : false
         * indus_pname : 网络安全
         * indus_name : 上网行为管理
         * shop_name : 吕轻侯
         * show_pic : ["data/uploads/2017/05/08/969859101215b5a8e.png"]
         * seller_pic : data/avatar/000/00/01/05_avatar_big.jpg
         * mark_info : []
         * user_pic : data/avatar/000/00/01/05_avatar_big.jpg
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
        private String num;
        private String title;
        private String price;
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
        private String gid;
        private String invoice;
        private String invoice_type;
        private Object province_name;
        private Object city_name;
        private Object area_name;
        private boolean favorite;
        private boolean desired;
        private boolean pay_flag;
        private String indus_pname;
        private String indus_name;
        private String shop_name;
        private String seller_pic;
        private String user_pic;
        private List<String> show_pic;
        private List<?> mark_info;

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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
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

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public String getInvoice_type() {
            return invoice_type;
        }

        public void setInvoice_type(String invoice_type) {
            this.invoice_type = invoice_type;
        }

        public Object getProvince_name() {
            return province_name;
        }

        public void setProvince_name(Object province_name) {
            this.province_name = province_name;
        }

        public Object getCity_name() {
            return city_name;
        }

        public void setCity_name(Object city_name) {
            this.city_name = city_name;
        }

        public Object getArea_name() {
            return area_name;
        }

        public void setArea_name(Object area_name) {
            this.area_name = area_name;
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

        public boolean isPay_flag() {
            return pay_flag;
        }

        public void setPay_flag(boolean pay_flag) {
            this.pay_flag = pay_flag;
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

        public String getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(String user_pic) {
            this.user_pic = user_pic;
        }

        public List<String> getShow_pic() {
            return show_pic;
        }

        public void setShow_pic(List<String> show_pic) {
            this.show_pic = show_pic;
        }

        public List<?> getMark_info() {
            return mark_info;
        }

        public void setMark_info(List<?> mark_info) {
            this.mark_info = mark_info;
        }
    }
}

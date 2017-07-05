package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;

/**
 * 作者：王海宾 on 2017/6/9 0009 10:14
 * 邮箱：995696826@qq.com
 */

public class ServiceMinuteBean1 implements Serializable {
    /**
     * code : 888
     * secess : true
     * data : {"order_id":"1091","service_id":"","order_name":"我们的","order_time":"1496820327","order_amount":"100.00","num":"","message":"","liuyan_fj":"","order_status":"ok","order_body":"购买商品<a href=\"index.php?do=goods&id=817\">我们的<\/a>","order_uid":"84","order_username":"13668844170","seller_uid":"20","seller_username":"亿赛通","y_name":"","y_photo":"2147483647","y_address":"山东省青岛市崂山区中韩街道苗岭路裕龙大厦","model_id":"7","to_seller_message":"","ys_start_time":"0","ys_end_time":"","service_end_time":"1497008685","order_liuyan":"","invoice_type":"0","invoice_name":"","price":"100.00","obj_id":"817","detail_name":"我们的","obj_pic":null,"show_status":"已付款","serviceOrderInfo":{"id":"77","uid":"84","username":"13668844170","service_id":"817","order_id":"1091","title":"我们的","indus_pid":"2","indus_id":"30","content":"10000","file_ids":"1513","price":"100.00","workfile":""}}
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
         * order_id : 1091
         * service_id :
         * order_name : 我们的
         * order_time : 1496820327
         * order_amount : 100.00
         * num :
         * message :
         * liuyan_fj :
         * order_status : ok
         * order_body : 购买商品<a href="index.php?do=goods&id=817">我们的</a>
         * order_uid : 84
         * order_username : 13668844170
         * seller_uid : 20
         * seller_username : 亿赛通
         * y_name :
         * y_photo : 2147483647
         * y_address : 山东省青岛市崂山区中韩街道苗岭路裕龙大厦
         * model_id : 7
         * to_seller_message :
         * ys_start_time : 0
         * ys_end_time :
         * service_end_time : 1497008685
         * order_liuyan :
         * invoice_type : 0
         * invoice_name :
         * price : 100.00
         * obj_id : 817
         * detail_name : 我们的
         * obj_pic : null
         * show_status : 已付款
         * serviceOrderInfo : {"id":"77","uid":"84","username":"13668844170","service_id":"817","order_id":"1091","title":"我们的","indus_pid":"2","indus_id":"30","content":"10000","file_ids":"1513","price":"100.00","workfile":""}
         */

        private String order_id;
        private String service_id;
        private String order_name;
        private String order_time;
        private String order_amount;
        private String num;
        private String message;
        private String liuyan_fj;
        private String order_status;
        private String order_body;
        private String order_uid;
        private String order_username;
        private String seller_uid;
        private String seller_username;
        private String y_name;
        private String y_photo;
        private String y_address;
        private String model_id;
        private String to_seller_message;
        private String ys_start_time;
        private String ys_end_time;
        private String service_end_time;
        private String order_liuyan;
        private String invoice_type;
        private String invoice_name;
        private String price;
        private String obj_id;
        private String detail_name;
        private Object obj_pic;
        private String show_status;
        private ServiceOrderInfoBean serviceOrderInfo;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getOrder_name() {
            return order_name;
        }

        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getLiuyan_fj() {
            return liuyan_fj;
        }

        public void setLiuyan_fj(String liuyan_fj) {
            this.liuyan_fj = liuyan_fj;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getOrder_body() {
            return order_body;
        }

        public void setOrder_body(String order_body) {
            this.order_body = order_body;
        }

        public String getOrder_uid() {
            return order_uid;
        }

        public void setOrder_uid(String order_uid) {
            this.order_uid = order_uid;
        }

        public String getOrder_username() {
            return order_username;
        }

        public void setOrder_username(String order_username) {
            this.order_username = order_username;
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

        public String getY_name() {
            return y_name;
        }

        public void setY_name(String y_name) {
            this.y_name = y_name;
        }

        public String getY_photo() {
            return y_photo;
        }

        public void setY_photo(String y_photo) {
            this.y_photo = y_photo;
        }

        public String getY_address() {
            return y_address;
        }

        public void setY_address(String y_address) {
            this.y_address = y_address;
        }

        public String getModel_id() {
            return model_id;
        }

        public void setModel_id(String model_id) {
            this.model_id = model_id;
        }

        public String getTo_seller_message() {
            return to_seller_message;
        }

        public void setTo_seller_message(String to_seller_message) {
            this.to_seller_message = to_seller_message;
        }

        public String getYs_start_time() {
            return ys_start_time;
        }

        public void setYs_start_time(String ys_start_time) {
            this.ys_start_time = ys_start_time;
        }

        public String getYs_end_time() {
            return ys_end_time;
        }

        public void setYs_end_time(String ys_end_time) {
            this.ys_end_time = ys_end_time;
        }

        public String getService_end_time() {
            return service_end_time;
        }

        public void setService_end_time(String service_end_time) {
            this.service_end_time = service_end_time;
        }

        public String getOrder_liuyan() {
            return order_liuyan;
        }

        public void setOrder_liuyan(String order_liuyan) {
            this.order_liuyan = order_liuyan;
        }

        public String getInvoice_type() {
            return invoice_type;
        }

        public void setInvoice_type(String invoice_type) {
            this.invoice_type = invoice_type;
        }

        public String getInvoice_name() {
            return invoice_name;
        }

        public void setInvoice_name(String invoice_name) {
            this.invoice_name = invoice_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getObj_id() {
            return obj_id;
        }

        public void setObj_id(String obj_id) {
            this.obj_id = obj_id;
        }

        public String getDetail_name() {
            return detail_name;
        }

        public void setDetail_name(String detail_name) {
            this.detail_name = detail_name;
        }

        public Object getObj_pic() {
            return obj_pic;
        }

        public void setObj_pic(Object obj_pic) {
            this.obj_pic = obj_pic;
        }

        public String getShow_status() {
            return show_status;
        }

        public void setShow_status(String show_status) {
            this.show_status = show_status;
        }

        public ServiceOrderInfoBean getServiceOrderInfo() {
            return serviceOrderInfo;
        }

        public void setServiceOrderInfo(ServiceOrderInfoBean serviceOrderInfo) {
            this.serviceOrderInfo = serviceOrderInfo;
        }

        public static class ServiceOrderInfoBean {
            /**
             * id : 77
             * uid : 84
             * username : 13668844170
             * service_id : 817
             * order_id : 1091
             * title : 我们的
             * indus_pid : 2
             * indus_id : 30
             * content : 10000
             * file_ids : 1513
             * price : 100.00
             * workfile :
             */

            private String id;
            private String uid;
            private String username;
            private String service_id;
            private String order_id;
            private String title;
            private String indus_pid;
            private String indus_id;
            private String content;
            private String file_ids;
            private String price;
            private String workfile;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIndus_pid() {
                return indus_pid;
            }

            public void setIndus_pid(String indus_pid) {
                this.indus_pid = indus_pid;
            }

            public String getIndus_id() {
                return indus_id;
            }

            public void setIndus_id(String indus_id) {
                this.indus_id = indus_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getFile_ids() {
                return file_ids;
            }

            public void setFile_ids(String file_ids) {
                this.file_ids = file_ids;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getWorkfile() {
                return workfile;
            }

            public void setWorkfile(String workfile) {
                this.workfile = workfile;
            }
        }
    }
}

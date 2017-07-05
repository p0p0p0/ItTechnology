package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chinalink on 2017/3/24 0024.
 */

public class LikeBean implements Serializable {
    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"service_id":"58","username":"admin","title":"腾达（Tenda）交换机5口 千兆4口以太网线分线器8口孔分流集线器家用宿舍分流","pic":"data/uploads/2016/10/07/2074957f772130e2ce.jpg","price":"139.00"},{"service_id":"348","username":"亿赛通","title":"启明星辰 天珣内网安全风险管理与审计系统 CCMCPP-BM(251-500）L","pic":"data/uploads/2017/01/19/2277858807cc80337c.png","price":"11516.00"},{"service_id":"57","username":"admin","title":"华三（H3C）LSPM2SP2P 交换机扩展模块","pic":"data/uploads/2016/10/07/199557f7709982285.jpg","price":"4999.00"},{"service_id":"56","username":"admin","title":"华为（HUAWEI）S1700-16G 企业级16口千兆二层非网管交换机","pic":"data/uploads/2016/10/07/2380257f76d7ebef52.jpg","price":"699.00"},{"service_id":"46","username":"admin","title":"华为huawei USG5560 企业级高性能VPN光纤防火墙","pic":"data/uploads/2016/10/06/1526257f6159bd0c25.jpg","price":"300000.00"},{"service_id":"182","username":"admin","title":"华为最新款2017 交换机","pic":"data/uploads/2017/01/04/1600586c994e428aa.jpg","price":"3000.00"},{"service_id":"553","username":"fuligui","title":"绿盟 ICEYE-1200D-03","pic":"data/uploads/2017/03/02/1158358b7bd6da1921.png","price":"800000.00"},{"service_id":"190","username":"admin","title":"金山杀毒软件","pic":"data/uploads/2017/01/12/85395876fb0b5f0c2.png","price":"3000.00"},{"service_id":"506","username":"fuligui","title":"物联网大功率无线数传模块","pic":"data/uploads/2017/02/23/947558aea0cf26563.png","price":"600.00"},{"service_id":"501","username":"fuligui","title":"物联网GMY P7 舞台染色灯","pic":"data/uploads/2017/02/17/1603058a6aefd88f69.png","price":"1500.00"},{"service_id":"44","username":"admin","title":"思科（CISCO） ASA5525-K8 下一代 防火墙","pic":"data/uploads/2016/10/06/2548757f60f5fc1210.jpg","price":"42199.00"},{"service_id":"194","username":"亿赛通","title":"华为S1724G-AC 24口13英寸千兆无管理交换机自带挂耳","pic":"data/uploads/2017/01/16/20577587cd9452afc5.jpg","price":"1540.00"},{"service_id":"195","username":"亿赛通","title":"华为 S6700-48-EI 48口全万兆SFP+三层核心交换机","pic":"data/uploads/2017/01/16/18779587cda9fbf8c2.jpg","price":"49000.00"},{"service_id":"342","username":"亿赛通","title":"中国移动和目720P高清WIFI智能摄像头远程录像家用监控 云存储版","pic":"data/uploads/2017/01/19/15224588076c2542b8.png","price":"1499.00"},{"service_id":"344","username":"亿赛通","title":"九次方大数据基础版终端（CHINA IQ BIG DATA）","pic":"data/uploads/2017/01/19/245025880785e39c0e.png","price":"300000.00"},{"service_id":"346","username":"亿赛通","title":"启明星辰 天珣内网安全风险管理与审计系统V6.6标准版","pic":"data/uploads/2017/01/19/3218158807ac7da76a.png","price":"1331200.00"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * service_id : 58
         * username : admin
         * title : 腾达（Tenda）交换机5口 千兆4口以太网线分线器8口孔分流集线器家用宿舍分流
         * pic : data/uploads/2016/10/07/2074957f772130e2ce.jpg
         * price : 139.00
         */

        private String service_id;
        private String username;
        private String title;
        private String pic;
        private String price;

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}

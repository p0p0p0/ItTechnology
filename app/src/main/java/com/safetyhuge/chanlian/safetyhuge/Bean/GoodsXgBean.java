package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chinalink on 2017/3/28 0028.
 */

public class GoodsXgBean implements Serializable {
    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"service_id":"42","username":"admin","title":"华为USG6306 4GE+2combo千兆硬件防火墙","pic":"data/uploads/2016/10/06/2039457f60cf6d0244.jpg","price":"5899.00","sale_num":"0"},{"service_id":"43","username":"admin","title":"TP-LINK TL-ER5520G多WAN口双核全千兆企业级有线路由器","pic":"data/uploads/2016/10/06/1881957f60dea7583f.jpg","price":"3016.00","sale_num":"0"},{"service_id":"44","username":"admin","title":"思科（CISCO） ASA5525-K8 下一代 防火墙","pic":"data/uploads/2016/10/06/2548757f60f5fc1210.jpg","price":"42199.00","sale_num":"0"},{"service_id":"45","username":"admin","title":"网康 NF-S320-A","pic":"data/uploads/2016/10/06/2278757f613b6e8dab.jpg","price":"7100.00","sale_num":"0"},{"service_id":"46","username":"admin","title":"华为huawei USG5560 企业级高性能VPN光纤防火墙","pic":"data/uploads/2016/10/06/1526257f6159bd0c25.jpg","price":"300000.00","sale_num":"0"},{"service_id":"56","username":"admin","title":"华为（HUAWEI）S1700-16G 企业级16口千兆二层非网管交换机","pic":"data/uploads/2016/10/07/2380257f76d7ebef52.jpg","price":"699.00","sale_num":"1"},{"service_id":"57","username":"admin","title":"华三（H3C）LSPM2SP2P 交换机扩展模块","pic":"data/uploads/2016/10/07/199557f7709982285.jpg","price":"4999.00","sale_num":"2"},{"service_id":"58","username":"admin","title":"腾达（Tenda）交换机5口 千兆4口以太网线分线器8口孔分流集线器家用宿舍分流","pic":"data/uploads/2016/10/07/2074957f772130e2ce.jpg","price":"139.00","sale_num":"9"},{"service_id":"182","username":"admin","title":"华为最新款2017 交换机","pic":"data/uploads/2017/01/04/1600586c994e428aa.jpg","price":"3000.00","sale_num":"0"},{"service_id":"190","username":"admin","title":"金山杀毒软件","pic":"data/uploads/2017/01/12/85395876fb0b5f0c2.png","price":"3000.00","sale_num":"0"},{"service_id":"574","username":"admin","title":"七尾七尾","pic":null,"price":"123.00","sale_num":"0"}]
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
         * service_id : 42
         * username : admin
         * title : 华为USG6306 4GE+2combo千兆硬件防火墙
         * pic : data/uploads/2016/10/06/2039457f60cf6d0244.jpg
         * price : 5899.00
         * sale_num : 0
         */

        private String service_id;
        private String username;
        private String title;
        private String pic;
        private String price;
        private String sale_num;

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

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
        }
    }
}

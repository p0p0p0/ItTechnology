package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/4/21 0021 19:33
 * 邮箱：995696826@qq.com
 */

public class JYjiluBean implements Serializable {
    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"fina_id":"243","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=222\">路由器技术解决方法<\/a>","fina_cash":"1000.000","fina_time":"1479864703","fina_type":"out","fina_action":"pub_task"},{"fina_id":"234","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=58\">腾达（Tenda）交换机5口 千兆4口以太网线分线器8口孔分流集线器家用宿舍分流<\/a>","fina_cash":"139.000","fina_time":"1478852392","fina_type":"out","fina_action":"buy_service"},{"fina_id":"232","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=109\">test123<\/a>","fina_cash":"1.000","fina_time":"1478850038","fina_type":"out","fina_action":"pub_task"},{"fina_id":"228","fina_mem":"参与任务<a href=\"index.php?do=task&id=107\">考勤系统操作方法<\/a>，稿件被选为中标稿件","fina_cash":"485.000","fina_time":"1478136706","fina_type":"in","fina_action":"task_bid"},{"fina_id":"226","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=58\">腾达（Tenda）交换机5口 千兆4口以太网线分线器8口孔分流集线器家用宿舍分流<\/a>","fina_cash":"139.000","fina_time":"1477988551","fina_type":"out","fina_action":"buy_service"},{"fina_id":"224","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=106\">IDS 入侵检测系统解决方案<\/a>","fina_cash":"1000.000","fina_time":"1477296381","fina_type":"out","fina_action":"pub_task"},{"fina_id":"176","fina_mem":"网站手动充值","fina_cash":"10000.000","fina_time":"1475802874","fina_type":"in","fina_action":"admin_charge"},{"fina_id":"205","fina_mem":"参与任务<a href=\"index.php?do=task&id=86\">系统安全加固<\/a>，稿件被选为中标稿件","fina_cash":"970.000","fina_time":"1477038565","fina_type":"in","fina_action":"task_bid"},{"fina_id":"206","fina_mem":"参与任务<a href=\"index.php?do=task&id=78\">电力行业数据库安全解决方案<\/a>，稿件被选为中标稿件","fina_cash":"14550.000","fina_time":"1477038739","fina_type":"in","fina_action":"task_bid"},{"fina_id":"207","fina_mem":"参与任务<a href=\"index.php?do=task&id=81\">畜牧养殖物联网解决方案<\/a>，稿件被选为中标稿件","fina_cash":"3395.000","fina_time":"1477038888","fina_type":"in","fina_action":"task_bid"},{"fina_id":"208","fina_mem":"参与任务<a href=\"index.php?do=task&id=85\">桌面虚拟化解决方案<\/a>，稿件被选为中标稿件","fina_cash":"970.000","fina_time":"1477039031","fina_type":"in","fina_action":"task_bid"},{"fina_id":"209","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=92\">有关防火墙的安装步骤和技巧<\/a>","fina_cash":"1000.000","fina_time":"1477039221","fina_type":"out","fina_action":"pub_task"},{"fina_id":"210","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=93\">考勤系统解决方案<\/a>","fina_cash":"1000.000","fina_time":"1477039379","fina_type":"out","fina_action":"pub_task"},{"fina_id":"211","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=94\">停车系统的按章和实施技巧<\/a>","fina_cash":"500.000","fina_time":"1477039480","fina_type":"out","fina_action":"pub_task"},{"fina_id":"212","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=95\">电子监控的安装和实施技术<\/a>","fina_cash":"500.000","fina_time":"1477039566","fina_type":"out","fina_action":"pub_task"},{"fina_id":"213","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=96\">有关互联网信息探索数据分析方案<\/a>","fina_cash":"1000.000","fina_time":"1477039707","fina_type":"out","fina_action":"pub_task"},{"fina_id":"387","fina_mem":"参与任务<a href=\"index.php?do=task&id=326\">12test<\/a>，稿件被选为中标稿件","fina_cash":"0.970","fina_time":"1489570823","fina_type":"in","fina_action":"task_bid"},{"fina_id":"431","fina_mem":"参与任务<a href=\"index.php?do=task&id=360\">多人悬赏测试001<\/a>，稿件被选为中标稿件","fina_cash":"80.000","fina_time":"1489987861","fina_type":"in","fina_action":"task_bid"},{"fina_id":"433","fina_mem":"参与任务<a href=\"index.php?do=task&id=361\">多人悬赏测试002<\/a>，稿件被选为中标稿件","fina_cash":"80.000","fina_time":"1489988600","fina_type":"in","fina_action":"task_bid"},{"fina_id":"435","fina_mem":"参与任务<a href=\"index.php?do=task&id=361\">多人悬赏测试002<\/a>，稿件被选为中标稿件","fina_cash":"80.000","fina_time":"1489988690","fina_type":"in","fina_action":"task_bid"},{"fina_id":"493","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=428\">11111111<\/a>","fina_cash":"111.000","fina_time":"1490332090","fina_type":"out","fina_action":"pub_task"},{"fina_id":"495","fina_mem":"发布多人悬赏任务<a href=\"index.php?do=task&id=429\">多人多人多人<\/a>","fina_cash":"111.000","fina_time":"1490337295","fina_type":"out","fina_action":"pub_task"},{"fina_id":"497","fina_mem":"参与任务<a href=\"index.php?do=task&id=423\">点睛之笔2<\/a>，稿件被选为中标稿件","fina_cash":"90.000","fina_time":"1490338287","fina_type":"in","fina_action":"task_bid"},{"fina_id":"503","fina_mem":"发布普通招标任务<a href=\"index.php?do=task&id=432\">普通招标111111<\/a>","fina_cash":"111.000","fina_time":"1490342124","fina_type":"out","fina_action":"pub_task"},{"fina_id":"538","fina_mem":"发布多人悬赏任务<a href=\"index.php?do=task&id=469\">多人11111111<\/a>","fina_cash":"110.000","fina_time":"1490579299","fina_type":"out","fina_action":"pub_task"},{"fina_id":"550","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=474\">1111111<\/a>","fina_cash":"111.000","fina_time":"1490593480","fina_type":"out","fina_action":"pub_task"},{"fina_id":"551","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=475\">1111111<\/a>","fina_cash":"111.000","fina_time":"1490594474","fina_type":"out","fina_action":"pub_task"},{"fina_id":"552","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=476\">5555555<\/a>","fina_cash":"11111.000","fina_time":"1490595307","fina_type":"out","fina_action":"pub_task"},{"fina_id":"553","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=477\">123123<\/a>","fina_cash":"111.000","fina_time":"1490595341","fina_type":"out","fina_action":"pub_task"},{"fina_id":"554","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=478\">23231123123<\/a>","fina_cash":"111.000","fina_time":"1490595403","fina_type":"out","fina_action":"pub_task"},{"fina_id":"555","fina_mem":"发布多人悬赏任务<a href=\"index.php?do=task&id=479\">222222<\/a>","fina_cash":"111.000","fina_time":"1490595459","fina_type":"out","fina_action":"pub_task"},{"fina_id":"556","fina_mem":"发布普通招标任务<a href=\"index.php?do=task&id=480\">11212312<\/a>","fina_cash":"100.000","fina_time":"1490595493","fina_type":"out","fina_action":"pub_task"},{"fina_id":"559","fina_mem":"参与任务<a href=\"index.php?do=task&id=481\">订金招标11111111<\/a>，稿件被选为中标稿件","fina_cash":"27.000","fina_time":"1490599041","fina_type":"in","fina_action":"task_bid"},{"fina_id":"561","fina_mem":"发布单人悬赏任务<a href=\"index.php?do=task&id=484\">单人悬赏00001<\/a>","fina_cash":"111.000","fina_time":"1490606812","fina_type":"out","fina_action":"pub_task"},{"fina_id":"562","fina_mem":"发布普通招标任务<a href=\"index.php?do=task&id=485\">普通招标2222222<\/a>","fina_cash":"100.000","fina_time":"1490690570","fina_type":"out","fina_action":"pub_task"},{"fina_id":"564","fina_mem":"发布:model_name任务<a href=\"index.php?do=task&id=:task_id\">:task_title<\/a>","fina_cash":"0.300","fina_time":"1490749556","fina_type":"out","fina_action":"pub_task"},{"fina_id":"565","fina_mem":"托管订金招标任务<a href=\"index.php?do=task&id=450\">123<\/a>赏金","fina_cash":"119.700","fina_time":"1490754179","fina_type":"out","fina_action":"hosted_reward"},{"fina_id":"575","fina_mem":"托管订金招标任务<a href=\"index.php?do=task&id=483\">122121<\/a>赏金","fina_cash":"515.000","fina_time":"1490767090","fina_type":"out","fina_action":"hosted_reward"},{"fina_id":"580","fina_mem":"发布普通招标任务<a href=\"index.php?do=task&id=486\">普通招标测试0329<\/a>","fina_cash":"100.000","fina_time":"1490768400","fina_type":"out","fina_action":"pub_task"},{"fina_id":"583","fina_mem":"参与任务<a href=\"index.php?do=task&id=487\">订金招标0329<\/a>，稿件被选为中标稿件","fina_cash":"2700.000","fina_time":"1490770261","fina_type":"in","fina_action":"task_bid"},{"fina_id":"584","fina_mem":"参与任务<a href=\"index.php?do=task&id=487\">订金招标0329<\/a>，稿件被选为中标稿件","fina_cash":"2700.000","fina_time":"1490770306","fina_type":"in","fina_action":"task_bid"},{"fina_id":"585","fina_mem":"参与任务<a href=\"index.php?do=task&id=487\">订金招标0329<\/a>，稿件被选为中标稿件","fina_cash":"3600.000","fina_time":"1490770324","fina_type":"in","fina_action":"task_bid"},{"fina_id":"594","fina_mem":"发布:model_name任务<a href=\"index.php?do=task&id=:task_id\">:task_title<\/a>","fina_cash":"40.000","fina_time":"1490772037","fina_type":"out","fina_action":"pub_task"},{"fina_id":"595","fina_mem":"发布:model_name任务<a href=\"index.php?do=task&id=:task_id\">:task_title<\/a>","fina_cash":"40.000","fina_time":"1490772279","fina_type":"out","fina_action":"pub_task"},{"fina_id":"623","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=591\">大甩卖拉 苹果大甩卖<\/a>","fina_cash":"12.000","fina_time":"1491461614","fina_type":"out","fina_action":"buy_service"},{"fina_id":"625","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=591\">大甩卖拉 苹果大甩卖<\/a>","fina_cash":"18.000","fina_time":"1491463027","fina_type":"out","fina_action":"buy_service"},{"fina_id":"631","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=591\">大甩卖拉 苹果大甩卖<\/a>","fina_cash":"36.000","fina_time":"1491470591","fina_type":"out","fina_action":"buy_service"},{"fina_id":"700","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=591\">大甩卖拉 苹果大甩卖<\/a>","fina_cash":"6.000","fina_time":"1491888326","fina_type":"out","fina_action":"buy_service"},{"fina_id":"717","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=593\">出租法拉利劳斯莱斯兰博基尼豪华跑车自驾商务出行<\/a>","fina_cash":"8000.000","fina_time":"1491894319","fina_type":"out","fina_action":"buy_service"},{"fina_id":"720","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=591\">大甩卖拉 苹果大甩卖<\/a>","fina_cash":"6.000","fina_time":"1491904893","fina_type":"out","fina_action":"buy_service"},{"fina_id":"725","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=655\">浪潮财务云计算解决方案<\/a>","fina_cash":"453.000","fina_time":"1492082218","fina_type":"out","fina_action":"buy_service"},{"fina_id":"726","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=651\">电信运营商业务大数据解决方案<\/a>","fina_cash":"555.000","fina_time":"1492131307","fina_type":"out","fina_action":"buy_service"},{"fina_id":"727","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=649\">安全信息和事件管理解决方案<\/a>","fina_cash":"351.000","fina_time":"1492131374","fina_type":"out","fina_action":"buy_service"},{"fina_id":"736","fina_mem":"购买服务或商品<a href=\"index.php?do=goods&id=651\">电信运营商业务大数据解决方案<\/a>","fina_cash":"555.000","fina_time":"1492161738","fina_type":"out","fina_action":"buy_service"},{"fina_id":"740","fina_mem":"参与任务<a href=\"index.php?do=task&id=533\">kkkk<\/a>，稿件被选为中标稿件","fina_cash":"485.000","fina_time":"1492333830","fina_type":"in","fina_action":"task_bid"},{"fina_id":"744","fina_mem":"任务失败退款","fina_cash":"111.000","fina_time":"1492413036","fina_type":"in","fina_action":"task_fail"}]
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
         * fina_id : 243
         * fina_mem : 发布单人悬赏任务<a href="index.php?do=task&id=222">路由器技术解决方法</a>
         * fina_cash : 1000.000
         * fina_time : 1479864703
         * fina_type : out
         * fina_action : pub_task
         */

        private String fina_id;
        private String fina_mem;
        private String fina_cash;
        private String fina_time;
        private String fina_type;
        private String fina_action;

        public String getFina_id() {
            return fina_id;
        }

        public void setFina_id(String fina_id) {
            this.fina_id = fina_id;
        }

        public String getFina_mem() {
            return fina_mem;
        }

        public void setFina_mem(String fina_mem) {
            this.fina_mem = fina_mem;
        }

        public String getFina_cash() {
            return fina_cash;
        }

        public void setFina_cash(String fina_cash) {
            this.fina_cash = fina_cash;
        }

        public String getFina_time() {
            return fina_time;
        }

        public void setFina_time(String fina_time) {
            this.fina_time = fina_time;
        }

        public String getFina_type() {
            return fina_type;
        }

        public void setFina_type(String fina_type) {
            this.fina_type = fina_type;
        }

        public String getFina_action() {
            return fina_action;
        }

        public void setFina_action(String fina_action) {
            this.fina_action = fina_action;
        }
    }
}

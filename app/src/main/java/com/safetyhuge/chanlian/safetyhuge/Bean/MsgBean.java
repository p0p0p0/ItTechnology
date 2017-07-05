package com.safetyhuge.chanlian.safetyhuge.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/5/23 0023 19:19
 * 邮箱：995696826@qq.com
 */

public class MsgBean implements Serializable {

    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"title":"任务通过审核","content":"  尊敬的 13668844170：您的发布的任务已通过审核，感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。任务编号：#535 / ","on_time":"1492312613","view_status":"0"},{"title":"任务资料提交","content":"  尊敬的 13668844170： /   深信服向您的 a href=\"http://192.168.6.201/index.php?do=task&id=535\">aaaaa / 提交了稿件。 /   感谢您对IT科技谷的信任。如有特殊情况，请致电客服 / ","on_time":"1492313793","view_status":"0"},{"title":"后台手动充值通知","content":"  尊敬的 13668844170： /   &nbsp; 后台管理员充值您现金1000000，感谢您对IT科技谷网的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 /   -------------------------------------------------------------------------------------------------------------------- /   此邮件为系统自动发出的邮件，请勿直接回复。 / ","on_time":"1492324523","view_status":"0"},{"title":"用户组设定","content":"  尊敬的 13668844170：管理员admin设置了您的后台用户组 / ","on_time":"1492328659","view_status":"0"},{"title":"任务自动选稿中标","content":"  尊敬的 13668844170： /   您参与的任务533进行了自动选稿，任务信息： /   任务标题： a href =\"http://192.168.6.201/index.php?do=task&id=533\">kkkk /  /   感谢您对IT科技谷的信任。如有特殊情况，请致电客服 / ","on_time":"1492333830","view_status":"0"},{"title":"任务自动选稿中标","content":"  尊敬的 13668844170： /   您参与的任务533进行了自动选稿，任务信息： /   任务标题： a href =\"http://192.168.6.201/index.php?do=task&id=533\">kkkk /  /   感谢您对IT科技谷的信任。如有特殊情况，请致电客服 / ","on_time":"1492333831","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=539\" target=\"_blank\" >eeee /  未通过审核，未通过审核的原因：undefined,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412722","view_status":"0"},{"title":"任务结束通知","content":"  您发布的单人悬赏任务 a href=\"http://192.168.6.201/index.php?do=task&id=535\">aaaaa / 被管理员手动结束，原因：阿萨德  ","on_time":"1492412735","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=561\" target=\"_blank\" >555 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412743","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=560\" target=\"_blank\" >444 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412747","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=559\" target=\"_blank\" >999 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412751","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=558\" target=\"_blank\" >9999 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412754","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=557\" target=\"_blank\" >888 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412758","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=556\" target=\"_blank\" >888 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412761","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=555\" target=\"_blank\" >1000 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412765","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=554\" target=\"_blank\" >1000 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412768","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=553\" target=\"_blank\" >1111 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412772","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=552\" target=\"_blank\" >1000 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412777","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=551\" target=\"_blank\" >10001 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412808","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=550\" target=\"_blank\" >999 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412811","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=549\" target=\"_blank\" >777 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412815","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=548\" target=\"_blank\" >llll /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412819","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=547\" target=\"_blank\" >de /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412822","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=545\" target=\"_blank\" >pppp /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412825","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=544\" target=\"_blank\" >iiiiii /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412829","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=543\" target=\"_blank\" >wwww /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412833","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=542\" target=\"_blank\" >ooooo /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412836","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=540\" target=\"_blank\" >我们都 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412841","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=538\" target=\"_blank\" >1000 /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412873","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=537\" target=\"_blank\" >www /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412876","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=536\" target=\"_blank\" >ssss /  未通过审核，未通过审核的原因：啊,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492412880","view_status":"0"},{"title":"修改支付密码","content":"  尊敬的 13668844170： /   您的支付密码修改成功，您的新支付密码为：123456。感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492486264","view_status":"0"},{"title":"任务发布通知","content":"  尊敬的 13668844170： /   您的任务567100资料提交中，感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 /   任务编号：567 /   任务标题： a href=\"http://192.168.6.201/index.php?do=task&id=567\"  target=\"_blank\">100 /  /   任务状态：资料提交中 /   开始时间：2017-04-18 11:29:11 /   投稿结束时间：{投稿结束时间} /   选稿结束时间：{选稿结束时间} /   -------------------------------------------------------------------------------------------------------------------- /   此邮件为系统自动发出的邮件，请勿直接回复。 / ","on_time":"1492486277","view_status":"0"},{"title":"任务通过审核","content":"  尊敬的 13668844170：您的发布的任务已通过审核，感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。任务编号：#565 / ","on_time":"1492516465","view_status":"0"},{"title":"任务资料提交","content":"  尊敬的 13668844170： /   向您的 a href=\"http://192.168.6.201/index.php?do=task&id=565\">北京科创有限公司网络防火墙解决 / 提交了稿件。 /   感谢您对IT科技谷的信任。如有特殊情况，请致电客服 / ","on_time":"1492517436","view_status":"0"},{"title":"任务资料提交","content":"  尊敬的 13668844170： /   15224418155向您的 a href=\"http://192.168.6.201/index.php?do=task&id=567\">100 / 提交了稿件。 /   感谢您对IT科技谷的信任。如有特殊情况，请致电客服 / ","on_time":"1492518730","view_status":"0"},{"title":"任务发布通知","content":"  尊敬的 13668844170： /   您的任务579100资料提交中，感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 /   任务编号：579 /   任务标题： a href=\"http://192.168.6.201/index.php?do=task&id=579\"  target=\"_blank\">100 /  /   任务状态：资料提交中 /   开始时间：2017-04-18 20:40:15 /   投稿结束时间：2017-04-25 20:40:15 /   选稿结束时间：2017-04-26 20:40:15 /   -------------------------------------------------------------------------------------------------------------------- /   此邮件为系统自动发出的邮件，请勿直接回复。 / ","on_time":"1492519221","view_status":"0"},{"title":"任务资料提交","content":"  尊敬的 13668844170： /   向您的 a href=\"http://192.168.6.201/index.php?do=task&id=579\">100 / 提交了稿件。 /   感谢您对IT科技谷的信任。如有特殊情况，请致电客服 / ","on_time":"1492519257","view_status":"0"},{"title":"任务结算","content":"  您发布的多人悬赏任务 a href=\"index.php?do=task&id=579\">100 / 公示期已过，任务已圆满完成  ","on_time":"1492519519","view_status":"0"},{"title":"任务通过审核","content":"  尊敬的 13668844170：您的发布的任务已通过审核，感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。任务编号：#575 / ","on_time":"1492519932","view_status":"0"},{"title":"交稿通知","content":"  尊敬的 13668844170： /   15224418155向您的 a href =\"http://192.168.6.201/index.php?do=task&id=575\">1000 / 提交了稿件。 /   感谢您对IT科技谷的信任。如有特殊情况，请致电客服 / ","on_time":"1492522810","view_status":"0"},{"title":"任务结束通知","content":"  您发布的单人悬赏任务 a href=\"http://192.168.6.201/index.php?do=task&id=567\">100 / 被管理员手动结束，原因：asd  ","on_time":"1492578758","view_status":"0"},{"title":"任务结束通知","content":"  您发布的普通招标任务 a href=\"http://192.168.6.201/index.php?do=task&id=575\">1000 / 被管理员手动结束，原因：asda sd  ","on_time":"1492578862","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=573\" target=\"_blank\" >100 /  未通过审核，未通过审核的原因：asd,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492578873","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=572\" target=\"_blank\" >1000 /  未通过审核，未通过审核的原因：asd,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492578877","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=571\" target=\"_blank\" >1000 /  未通过审核，未通过审核的原因：asd,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492578881","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=570\" target=\"_blank\" >1000 /  未通过审核，未通过审核的原因：asd,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492578885","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=569\" target=\"_blank\" >100 /  未通过审核，未通过审核的原因：asd,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492578888","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=574\" target=\"_blank\" >1000 /  未通过审核，未通过审核的原因：undefined,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1492677759","view_status":"0"},{"title":"投稿期结束","content":"  您发布的多人悬赏任务 a href=\"index.php?do=task&id=565\">北京科创有限公司网络防火墙解决 / 投稿期已过，任务在自动进入下一阶段","on_time":"1492776997","view_status":"0"},{"title":"任务失败","content":"  您发布的多人悬赏任务 a href=\"index.php?do=task&id=565\">北京科创有限公司网络防火墙解决 / 因因你没有操作过任何稿件而失败结束已经失败。 / ","on_time":"1492994830","view_status":"0"},{"title":"产品发布发布提示","content":"  尊敬的 13668844170： /   您的产品发布已发布成功。产品发布信息： /   产品发布链接： a href=\"http://192.168.6.201/index.php?do=goods&id=720\">666 /  /    em> strong>发布时间：2017-04-27 18:51:49 /strong> /em> br /> /   产品发布状态：待审核 br /> /   感谢您对IT科技谷的信任。如有特殊情况，请致电客服 / ","on_time":"1493290309","view_status":"0"},{"title":"产品发布发布提示","content":"  尊敬的 13668844170： /   您的产品发布已发布成功。产品发布信息： /   产品发布链接： a href=\"http://192.168.6.201/index.php?do=goods&id=721\">100 /  /    em> strong>发布时间：2017-04-27 19:08:28 /strong> /em> br /> /   产品发布状态：待审核 br /> /   感谢您对IT科技谷的信任。如有特殊情况，请致电客服 / ","on_time":"1493291308","view_status":"0"},{"title":"任务通过审核","content":"  尊敬的 13668844170：您的发布的任务已通过审核，感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。任务编号：#568 / ","on_time":"1493689934","view_status":"0"},{"title":"作品审核失败","content":"  尊敬的 13668844170：您的发布的作品 100 未通过审核，未通过审核的原因：undefined,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1493689987","view_status":"0"},{"title":"作品审核失败","content":"  尊敬的 13668844170：您的发布的作品 666 未通过审核，未通过审核的原因：undefined,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1493689991","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=583\" target=\"_blank\" >111 /  未通过审核，未通过审核的原因：undefined,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1493890058","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=564\" target=\"_blank\" >交换机解决方案 /  未通过审核，未通过审核的原因：undefined,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1493890107","view_status":"0"},{"title":"任务结束通知","content":"  您发布的普通招标任务 a href=\"http://192.168.6.201/index.php?do=task&id=568\">青岛思科技有限公司网络链路优方案 / 被管理员手动结束，原因：阿斯打扫打扫打扫打扫的  ","on_time":"1493890151","view_status":"0"},{"title":"任务审核失败","content":"  尊敬的 13668844170：您的发布的任务  a href =\"http://192.168.6.201/index.php?do=task&id=566\" target=\"_blank\" >北京创客有限公司DDOS方案解决 /  未通过审核，未通过审核的原因：undefined,感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。 / ","on_time":"1493890155","view_status":"0"}]
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

    public static class DataBean implements Parcelable {
        /**
         * title : 任务通过审核
         * content :   尊敬的 13668844170：您的发布的任务已通过审核，感谢您对IT科技谷的信任。如有特殊情况，请致电客服，我们将协助您解决问题。任务编号：#535 /
         * on_time : 1492312613
         * view_status : 0
         */

        private String title;
        private String content;
        private String on_time;
        private String view_status;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getView_status() {
            return view_status;
        }

        public void setView_status(String view_status) {
            this.view_status = view_status;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.content);
            dest.writeString(this.on_time);
            dest.writeString(this.view_status);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.title = in.readString();
            this.content = in.readString();
            this.on_time = in.readString();
            this.view_status = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}

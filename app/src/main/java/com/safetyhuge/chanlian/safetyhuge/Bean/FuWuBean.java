package com.safetyhuge.chanlian.safetyhuge.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王海宾 on 2017/5/23 0023 13:21
 * 邮箱：995696826@qq.com
 */

public class FuWuBean implements Serializable{
    /**
     * code : 200
     * message : 数据返回成功
     * data : [{"indus_pid":"0","indus_id":"0","fw_status":"1","content":"1","phone":"0","service_id":"772","uid":"84","username":"13668844170","title":"防火墙","pic":"../data/uploads/cpimg/cropped_1495016625731.jpg","price":"1000.00","sale_num":"0","user_pic":null,"lng":"120.461817","lat":"36.099595"},{"indus_pid":"0","indus_id":"0","fw_status":"0","content":"2000","phone":"0","service_id":"771","uid":"84","username":"13668844170","title":"200","pic":"../data/uploads/cpimg/cropped_1495016048869.jpg","price":"200.00","sale_num":"0","user_pic":null,"lng":"120.46183","lat":"36.09959"},{"indus_pid":"29","indus_id":"2","fw_status":"0","content":"9999","phone":"0","service_id":"770","uid":"84","username":"13668844170","title":"防火墙","pic":"../data/uploads/cpimg/cropped_1495015991750.jpg","price":"333.00","sale_num":"0","user_pic":null,"lng":"120.46183","lat":"36.09959"},{"indus_pid":"0","indus_id":"0","fw_status":"0","content":"哦哦哦摸摸","phone":"0","service_id":"769","uid":"84","username":"13668844170","title":"防火墙","pic":"../data/uploads/cpimg/cropped_1495015830194.jpg","price":"200.00","sale_num":"0","user_pic":null,"lng":"120.461833","lat":"36.099591"},{"indus_pid":"0","indus_id":"0","fw_status":"0","content":"知我者谓我心忧","phone":"0","service_id":"768","uid":"84","username":"13668844170","title":"防火墙","pic":"../data/uploads/cpimg/cropped_1495015729309.jpg","price":"800.00","sale_num":"0","user_pic":null,"lng":"120.461833","lat":"36.099591"},{"indus_pid":"0","indus_id":"0","fw_status":"1","content":"1111","phone":"0","service_id":"767","uid":"84","username":"13668844170","title":"防火墙","pic":"../data/uploads/cpimg/cropped_1495015512845.jpg","price":"100.00","sale_num":"0","user_pic":null,"lng":"120.46181","lat":"36.099594"},{"indus_pid":"2","indus_id":"29","fw_status":"0","content":"&lt;p&gt;牙膏售后服务&lt;/p&gt;","phone":null,"service_id":"741","uid":"1","username":"admin","title":"牙膏售后服务","pic":"data/uploads/2017/05/04/10415590a83332f3f8.png","price":"0.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170414151834.jpg","lng":null,"lat":null},{"indus_pid":"2","indus_id":"29","fw_status":"0","content":"&lt;p&gt;有关于防火墙安装实施&lt;/p&gt;","phone":null,"service_id":"556","uid":"21","username":"爱数","title":"关于防火墙安装实施","pic":"data/uploads/2017/03/07/56858be09318f58d.jpg","price":"5000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"3","indus_id":"16","fw_status":"0","content":"&lt;p&gt;有关电子监控问题&lt;/p&gt;","phone":null,"service_id":"555","uid":"19","username":"深信服","title":"电子监控系统","pic":"data/uploads/2017/03/07/1678558be06e3e14db.png","price":"5000.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170309193948.jpg","lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;对于私人用户而言，亚马逊不再提供免费方案。你得成为高级会员，才有资格享用其免费照片存储服务。&lt;/p&gt;&lt;p&gt;然而，亚马逊的无限量内容存储(Unlimited Everything)服务既便宜，又很高效。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","phone":null,"service_id":"382","uid":"35","username":"fuligui","title":"亚马逊云端","pic":"data/uploads/2017/02/08/19359589a950b99ba7.png","price":"1000.00","sale_num":"1","user_pic":null,"lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;Box是为公司而生的。针对没钱购置大量云存储空间的小型工作组，这家公司的个人服务也许正是它们所需要的。免费帐户提供了10GB的存储空间。&lt;/p&gt;","phone":null,"service_id":"381","uid":"35","username":"fuligui","title":"Box云存储解决方案","pic":"data/uploads/2017/02/08/27175589a949a2f8ba.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;Syncplicity由ECM公司的企业文件同步和共享服务所提供，它旨在与Box直接竞争。它提供了许多一样的功能特性。与竞争对手一样，该服务也免费提供10GB的存储空间。&lt;/p&gt;","phone":null,"service_id":"380","uid":"35","username":"fuligui","title":"Syncplicity云存储解决方案","pic":"data/uploads/2017/02/08/32215589a9463b316c.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;Copy不是典型的云服务提供商。该服务是由梭子鱼网络公司(Barracuda Networks)开发并维护的，这家IT公司不仅专营存储业务，还主攻安全领域。&lt;/p&gt;","phone":null,"service_id":"379","uid":"35","username":"fuligui","title":"Copy云存储解决方案","pic":"data/uploads/2017/02/08/22110589a941f8ecf6.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;谷歌在互联网上早已大名鼎鼎，公司用户和个人用户都在使用谷歌的云存储服务。谷歌云盘(Google Drive)不仅提供相当大的存储空间，还随带使用方便的办公工具，这些工具可以创建文档、演示文稿和电子表格。&lt;/p&gt;","phone":null,"service_id":"378","uid":"35","username":"fuligui","title":"谷歌云盘","pic":"data/uploads/2017/02/08/22597589a93d7c3eaf.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;微软的OneDrive服务让用户可以轻松共享和保存其文件。还提供了移动和桌面应用程序，可以上传和下载文件。免费服务为用户提供了15GB的存储空间。&lt;/p&gt;","phone":null,"service_id":"377","uid":"35","username":"fuligui","title":"OneDrive云存储解决方案","pic":"data/uploads/2017/02/08/13232589a93894fb4e.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;pCloud总部位于瑞士，遵守欧洲的云存储法规。注册客户将获得10GB的免费存储空间。如果邀请朋友，用户可以获得最多10GB的额外存储空间，总共达到20GB。&lt;/p&gt;","phone":null,"service_id":"376","uid":"35","username":"fuligui","title":"pCloud云存储解决方案","pic":"data/uploads/2017/02/08/27019589a933cecab2.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;它由Kim Dotcom在第一个网站(Megaupload)被管理当局关闭后创办。该服务不仅仅因海量的免费服务出名，还因其安全性出名，其安全性无可匹敌。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","phone":null,"service_id":"375","uid":"35","username":"fuligui","title":"Mega云存储解决方案","pic":"data/uploads/2017/02/08/31733589a92f08489c.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;免费提供无限制云存储解决方案的公司。不过需要留意一些严重限制，你可能因而只想把它用作一项备份服务。首先，它是一个得到广告支持的网站，所以会有诸多广告，甚至还会有弹出窗口。&lt;/p&gt;","phone":null,"service_id":"374","uid":"35","username":"fuligui","title":"Hive云存储解决方案","pic":"data/uploads/2017/02/08/11404589a9292be8e8.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"192","indus_id":"193","fw_status":"0","content":"&lt;p&gt;用户可以用其服务来备份文件，并与其他人共享文件。虽然这家公司确实提供收费的企业级帐户(提供更多的功能特性)，但是免费帐户对小型工作组来说绰绰有余。&lt;/p&gt;","phone":null,"service_id":"373","uid":"35","username":"fuligui","title":"ADrive云存储解决方案","pic":"data/uploads/2017/02/08/16644589a92306db07.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"160","indus_id":"161","fw_status":"0","content":"&lt;p&gt;智慧农业农机装备的应用、管理和服务具有范围广、时间性强、区域分布明显的特点，同时农机装备本身也因其使用人群和使用环境决定了其在服务过程中需要面对和处理的用户需求时，要满足时效性、专业性的需求。这些需求在传统以电话联络、口头交流、全人工操作和管理的状态下，无法从根本上达到效率、结果的有效平衡，也造成了企业、用户共同的使用成本。&lt;/p&gt;","phone":null,"service_id":"372","uid":"35","username":"fuligui","title":"农机装备物联网方案","pic":"data/uploads/2017/02/08/6163589a902951065.png","price":"1800.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"160","indus_id":"161","fw_status":"0","content":"&lt;p&gt;机械重工物联网是借助全球定位系统（GPS）、手机通讯网、互联网，实现了工程机械智能化识别、定位、跟踪、监控和管理，使工程机械、操作手、技术服务工程师、代理店、制造厂之间异地、远程、动态、全天候\u201c物物相连、人人相连、物人相联\u201d。&lt;/p&gt;","phone":null,"service_id":"371","uid":"35","username":"fuligui","title":"机械重工物联网方案","pic":"data/uploads/2017/02/08/4385589a8fd9f2b15.png","price":"2000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"160","indus_id":"161","fw_status":"0","content":"&lt;p&gt;智慧电能通过研究现有纯电动车及油电混合电动车中，车载电池管理系统中缺乏远程智能管理与电池突发性事故智能决策的问题,出将车载电池管理系统、物联网技术和分布式部署技术相结合,设计能够实现远程管理并带有各级事故智能决策的新一代车载电池管理、电动汽车和充电桩管理的物联网系统。&lt;/p&gt;","phone":null,"service_id":"370","uid":"35","username":"fuligui","title":"动力电池/电动车物联网方案","pic":"data/uploads/2017/02/08/416589a8f897f851.png","price":"1800.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"160","indus_id":"161","fw_status":"0","content":"&lt;p&gt;消防远程管控系统，实现对联网单位消防控制中心及设备运行状态、防火值班工作情况、消防设施完好率、维护管理及维修作业等数据的通信和存储，实行实时监控联网单位火警、消防设施故障，随时随地了解联网单位消防设施运行状态、维护管理数据，火警发生时，通过系统第一时间自动向物联网监控中心报告火警信息；发生故障时，通过系统以最快时间调度维修人员到现场处理，维修维护人员地图动态管理调度，使得对联网单位的维修维护服务更及时和快捷。&lt;/p&gt;","phone":null,"service_id":"369","uid":"35","username":"fuligui","title":"建筑消防物联网远程管控方案","pic":"data/uploads/2017/02/08/11214589a8f31c5964.png","price":"1500.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"160","indus_id":"161","fw_status":"0","content":"&lt;p&gt;建筑机械行业发展到当前阶段，掌握了许多关键核心技术，具备了一定的创新能力，但整体上看，与国外同行数十年的积累比起来，我们的技术和产品创新还有一定的差距。比如，节能技术、智能制造技术、物联网技术等，仍需要加大力度，整合各种优良的科技资源，不断提高自主创新能力。&lt;/p&gt;","phone":null,"service_id":"368","uid":"35","username":"fuligui","title":"建筑机械物联网方案","pic":"data/uploads/2017/02/08/16376589a8ec242da0.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"160","indus_id":"161","fw_status":"0","content":"&lt;p&gt;智能快递柜是一套包裹投递收取自助服务系统，完成了包裹分散投递到集中投递的转变，逐步实行将人工投递收取转变成自动化的操作，能够大量减少快递公司的人员管理成本，提高包裹的投递时效，保证物品安全和收件人的隐私。&lt;/p&gt;","phone":null,"service_id":"367","uid":"35","username":"fuligui","title":":行业应用物联网方案","pic":"data/uploads/2017/02/08/31575589a8e6fcd46c.png","price":"2000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"160","indus_id":"161","fw_status":"0","content":"&lt;p&gt;当前国内市场，热泵行业正处于市场初级阶段。除了少数大型厂商，大多数热泵企业普遍是中小企业，品牌影响力还没有建立起来。故障保护不完善、无联网通信及无法实现远程控制等问题已经影响了热泵的推广使用。为了应对市场竞争，当前热泵企业普遍采用较长的维保期限（通常5年以上），这也使得企业售后管理面临了艰巨的挑战。&lt;/p&gt;","phone":null,"service_id":"366","uid":"35","username":"fuligui","title":"热泵物联网解决方案","pic":"data/uploads/2017/02/08/9992589a8e0ae9657.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"160","indus_id":"161","fw_status":"0","content":"&lt;p&gt;智慧营房设计思路是利用现代化物联网技术手段，建立一套针对于部队的人员、车辆、装备、物资的综合管理信息系统平台。充分利用智能卡技术、电子标签技术、二维码技术、智能传感技术，实现人、车、装备、物资的动态管理，实现信息整合，数据动态掌握。&lt;/p&gt;","phone":null,"service_id":"365","uid":"35","username":"fuligui","title":"物联网营房综合管理系统方案","pic":"data/uploads/2017/02/08/16635589a8dac2ada8.png","price":"700.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"121","indus_id":"36","fw_status":"0","content":"&lt;p&gt;安全研究员Samy Kamkar设计了一款名为\u201cKeySweeper\u201d的廉价USB充电器，该充电器可以秘密地记录附近微软无线键盘的敲击数据，然后将数据存储在本地设备或通过网络传输存储到后台。&lt;/p&gt;","phone":null,"service_id":"364","uid":"35","username":"fuligui","title":"伪装成USB充电器的无线键盘嗅探器\u2014\u2014KeySweeper","pic":"data/uploads/2017/02/08/3621589a89c45ab22.png","price":"200.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"2","indus_id":"28","fw_status":"0","content":"&lt;p&gt;代码覆盖测试主要用于漏洞研究领域。主要目的是使用不同输入覆盖程序代码的不同部分。如果某个输入导致程序崩溃，我们将检测崩溃是否能被利用。有很多代码覆盖测试的方法，比如随机测试等。但是本文重点关注使用动态符号执行进行代码覆盖测试。覆盖代码不意味着能找到所有的可能的缺陷。一些缺陷不会引起程序崩溃。然而2017年刚到，勒索软件以惊人的速度爆发。这周我们发现了大量的新变种，尤其是以很有名的名次命名的FSociety。我们也发现了一些解密工具，圣诞节有关的勒索软件，CryptoMix/CryptFile2的分析，大量的小的勒索软件。&lt;/p&gt;","phone":null,"service_id":"363","uid":"35","username":"fuligui","title":"利用动态符号执行进行代码覆盖测试","pic":"data/uploads/2017/02/08/8537589a894516e99.png","price":"2000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"121","indus_id":"36","fw_status":"0","content":"&lt;p&gt;在日常分析使用某个软件的过程中，如果我们想要去挖掘软件的漏洞、或者是通过打补丁的方式给软件增添一些新的功能，抑或是为了记录下软件运行过程中被调用的函数及其参数，有时候我们需要劫持对某些DLL库的调用过程。&lt;/p&gt;","phone":null,"service_id":"362","uid":"35","username":"fuligui","title":"实践API钩子拦截DLL库调用","pic":"data/uploads/2017/02/08/7723589a88cfa2711.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"121","indus_id":"36","fw_status":"0","content":"&lt;p&gt;黑客入门资料 黑客入门技术资料&nbsp;&lt;br/&gt;&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","phone":null,"service_id":"361","uid":"35","username":"fuligui","title":"黑客攻防从入门到精通","pic":"data/uploads/2017/02/06/214105897e9bfa83ba.png","price":"200.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"3","indus_id":"8","fw_status":"0","content":"&lt;p&gt;于面向未来的SOA架构设计，B/S浏览模式,系统中的各子系统均模块化设计，可灵活安装部署；提供标准的数据交换协议与业务集成接口，可方便实现第三方应用平台（如数字化园区、一卡通、BA系统等）或原有应用子系统（如：预付费水电系统等）接入&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","phone":null,"service_id":"360","uid":"35","username":"fuligui","title":"节能监管综合服务平台","pic":"data/uploads/2017/02/06/225835897e79ea2493.png","price":"1500.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"3","indus_id":"8","fw_status":"0","content":"&lt;p&gt;目前各级政府机关正在大力推进由管理监督职能向管理服务职能的转变，努力开展电子化政务的建设与实施。很多机关单位在计算机网络的架设及网站的建设上已经花费了大量的精力和资金，网站功能也日趋完善。但以中国国情来说，具备上网条件的群众和企业毕竟还相当少，且有着相当大的技术障碍，而最贴近群众，最便利、最普遍的服务手段----电话与传真尚未被有效的运用。「电子化政务与便民服务系统」就是将电话，传真，计算机，网络有效整合起来的政务电子化解决方案，可以为政府行政服务机关建立起电子化办公平台和便民服务的桥梁。&lt;/p&gt;","phone":null,"service_id":"359","uid":"35","username":"fuligui","title":"政府电子政务呼叫中心系统解决方案","pic":"data/uploads/2017/02/06/264765897e7228d4a4.png","price":"2000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"3","indus_id":"8","fw_status":"0","content":"&lt;p&gt;开锁行业呼叫中心系统，是专为开锁公司业务量身定制的一套结合了呼叫中心、移动APP的综合业务系统，最大限度的提高了工作效率，促成订单，历史数据可查。&nbsp;&lt;/p&gt;","phone":null,"service_id":"358","uid":"35","username":"fuligui","title":"开锁行业呼叫中心解决方案","pic":"data/uploads/2017/02/06/51595897e6a1c7b17.png","price":"500.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"121","indus_id":"36","fw_status":"0","content":"&lt;p&gt;政府网站已经成为各级人民政府及其部门发布政府信息、提供在线服务、与公众互动交流的重要平台和窗口，在提高行政效能、提升政府公信力等方面发挥了重要作用。政府网站突出完善信息公开、网上办事、政民互动三大功能，构建为企业和公众提供政府各个部门服务的\u201c一站式\u201d政府门户网站。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","phone":null,"service_id":"357","uid":"35","username":"fuligui","title":"政府行业网络安全解决方案","pic":"data/uploads/2017/02/06/37435897e61aaf424.png","price":"500.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"3","indus_id":"8","fw_status":"0","content":"&lt;p&gt;历经了十几年的发展，电子政务对存储资源、计算资源、网络资源的需求进一步加大，而云计算的出现无疑为不断增长的电子政务创造了技术条件。为此各级政府均开始了云数据中心的建设，将不同职能部门的资源整合起来，实现更广泛的资源共享。但同时云数据中心下的安全问题变得更加突出，虚拟化技术引入对安全建设带来了更高的挑战。&lt;/p&gt;","phone":null,"service_id":"356","uid":"35","username":"fuligui","title":"虚拟化技术打造安全政务云数据中心","pic":"data/uploads/2017/02/06/194475897e5615b2ca.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"3","indus_id":"8","fw_status":"0","content":"&lt;p&gt;相对于其他行业，高校对互联网访问内容的控制更加严格，特别是针对学生的上网访问行为，需要进行适度的管控，引导学生健康的上网。根据2005 年颁布的\u201c公安部82 令\u201d中对互联网访问日志审计提出的明确要求，要求互联网服务提供者、互联网使用单位必须采取必要的安全保护技术措施，对上网行为进行监控，并对非法行为进行有效记录。如何做到既开放又管控，既稳定运营又灵活扩展，既要符合绿色校园的要求又要提供差异化服务，既要实现透明化审计又要进行全面有效规划，这些都是高校安全建设中比较关心的问题。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","phone":null,"service_id":"355","uid":"35","username":"fuligui","title":"高校实名制安全管控与日志审计解决方案","pic":"data/uploads/2017/02/06/197215897e41d45fc8.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"2","indus_id":"28","fw_status":"0","content":"&lt;p&gt;如果服务器上连的交换机端口已经预先设置了TRUNK,并允许特定的VLAN可以通过，那么服务器的网卡在配置时就必须指定所属的VLAN，否则就不通了，这种情形在虚拟化部署时较常见。&lt;/p&gt;","phone":null,"service_id":"352","uid":"35","username":"fuligui","title":"虚拟化部署之linux网卡的VLAN配置","pic":"data/uploads/2017/02/06/238335897e0af9bef3.png","price":"500.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"2","indus_id":"29","fw_status":"0","content":"&lt;p&gt;完整说明centos做网关路由的文档。&lt;/p&gt;","phone":null,"service_id":"351","uid":"35","username":"fuligui","title":"centos6.5做网关路由器详细实现方法","pic":"data/uploads/2017/02/06/222035897df4f17ec0.png","price":"1000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"2","indus_id":"28","fw_status":"0","content":"&lt;p&gt;随着我国人民生活水平的不断提高，群众的人均住房面积越来越大，由此也引发了一些生活上的问题，例如无线路由器信号无法在所有房间都保持稳定。为了改善这种情况，小编将向各位网友介绍利用两个以上无线路由器进行桥接的方法，这将会解决WiFi信号死角或变弱的问题。&lt;/p&gt;","phone":null,"service_id":"350","uid":"35","username":"fuligui","title":"解决WiFi信号死角：无线路由器桥接大法","pic":"data/uploads/2017/02/06/240135897dc9ae6086.png","price":"2000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"3","indus_id":"27","fw_status":"0","content":"&lt;p&gt;随着城市建设迅速发展，对地下管线的依赖性也越来越强，在进行城市规划、设计、施工和管理工作中，如果没有完整准确的地下管线信息，高效办公就无从谈起。但是，由于历史和现实的各种原因，城市地下管线及其管理滞后于城市的发展，已成为阻碍城市建设和经济发展的瓶颈。面对日益繁杂的管线种类和日渐增多的管线量，必须采取更有效的机制和手段来进行管理，提高工作效率。&lt;/p&gt;","phone":null,"service_id":"277","uid":"35","username":"fuligui","title":"地下管线信息管理系统建设方案1","pic":"data/uploads/2017/01/19/31212588024d620fb2.png","price":"3000.00","sale_num":"0","user_pic":null,"lng":null,"lat":null},{"indus_pid":"29","indus_id":"2","fw_status":"0","content":"jlv","phone":null,"service_id":"176","uid":"20","username":"亿赛通","title":"服务01016055","pic":"../data/uploads/cpimg/20170103165010.jpg","price":"588.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170302175711.jpg","lng":"120.462000","lat":"36.099692"},{"indus_pid":"29","indus_id":"2","fw_status":"0","content":"路况","phone":null,"service_id":"175","uid":"20","username":"亿赛通","title":"发布服务568","pic":"../data/uploads/cpimg/20170103164536.jpg","price":"588.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170302175711.jpg","lng":"120.461852","lat":"36.099671"},{"indus_pid":"37","indus_id":"121","fw_status":"0","content":"信息化安全桌面管理","phone":null,"service_id":"174","uid":"20","username":"亿赛通","title":"信息化安全桌面管理","pic":"../data/uploads/cpimg/20170103164404.jpg","price":"88888888.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170302175711.jpg","lng":"0.000000","lat":"0.000000"},{"indus_pid":"29","indus_id":"2","fw_status":"0","content":"关于防火墙的解决方案","phone":null,"service_id":"185","uid":"19","username":"深信服","title":"防火墙解决问题","pic":"data/uploads/cpimg/20170110123543.jpg","price":"3000.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170309193948.jpg","lng":"120.459593","lat":"36.098874"},{"indus_pid":"37","indus_id":"121","fw_status":"0","content":"刘磊磊","phone":null,"service_id":"177","uid":"20","username":"亿赛通","title":"服务2887655","pic":"../data/uploads/cpimg/20170103170203.jpg","price":"8888.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170302175711.jpg","lng":"120.460698","lat":"36.102679"},{"indus_pid":"27","indus_id":"3","fw_status":"0","content":"有关于综合布线的解决方案","phone":null,"service_id":"186","uid":"19","username":"深信服","title":"综合布线解决方案","pic":"../data/uploads/cpimg/20170110123732.jpg","price":"5000.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170309193948.jpg","lng":"120.459807","lat":"36.101327"},{"indus_pid":"28","indus_id":"2","fw_status":"0","content":"啦啦啦","phone":null,"service_id":"349","uid":"20","username":"亿赛通","title":"家教服务","pic":"../data/uploads/cpimg/20170122163306.jpg","price":"123312.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170302175711.jpg","lng":"120.497892","lat":"36.161905"},{"indus_pid":"29","indus_id":"2","fw_status":"0","content":"哦啦啦啦啦啦啦","phone":null,"service_id":"561","uid":"33","username":"lvshitao","title":"服务0309有图","pic":"../data/uploads/cpimg/20170309142041.jpg","price":"123450.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170309143043.jpg","lng":"120.465966","lat":"36.100625"},{"indus_pid":"39","indus_id":"121","fw_status":"0","content":"健健康康","phone":null,"service_id":"562","uid":"33","username":"lvshitao","title":"服务0309001有图","pic":"../data/uploads/cpimg/20170309142559.jpg","price":"55555.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170309143043.jpg","lng":"120.468272","lat":"36.103286"},{"indus_pid":"476","indus_id":"218","fw_status":"0","content":"lll","phone":null,"service_id":"563","uid":"33","username":"lvshitao","title":"服务0309002有图","pic":"../data/uploads/cpimg/20170309142715.jpg","price":"555.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170309143043.jpg","lng":"120.461846","lat":"36.099445"},{"indus_pid":"476","indus_id":"218","fw_status":"0","content":"健健康康","phone":null,"service_id":"564","uid":"33","username":"lvshitao","title":"服务0309003有图","pic":"../data/uploads/cpimg/20170309143512.jpg","price":"6666.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170309143043.jpg","lng":"120.469109","lat":"36.103234"},{"indus_pid":"37","indus_id":"121","fw_status":"0","content":"得到","phone":null,"service_id":"565","uid":"33","username":"lvshitao","title":"服务0309004无图","pic":"","price":"88888.00","sale_num":"0","user_pic":"../data/uploads/imgtx/20170309143043.jpg","lng":"120.467328","lat":"36.097911"}]
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
         * indus_pid : 0
         * indus_id : 0
         * fw_status : 1
         * content : 1
         * phone : 0
         * service_id : 772
         * uid : 84
         * username : 13668844170
         * title : 防火墙
         * pic : ../data/uploads/cpimg/cropped_1495016625731.jpg
         * price : 1000.00
         * sale_num : 0
         * user_pic : null
         * lng : 120.461817
         * lat : 36.099595
         */

        private String indus_pid;
        private String indus_id;
        private String fw_status;
        private String content;
        private String phone;
        private String service_id;
        private String uid;
        private String username;
        private String title;
        private String pic;
        private String price;
        private String sale_num;
        private Object user_pic;
        private String lng;
        private String lat;

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

        public String getFw_status() {
            return fw_status;
        }

        public void setFw_status(String fw_status) {
            this.fw_status = fw_status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
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

        public Object getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(Object user_pic) {
            this.user_pic = user_pic;
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
    }
}

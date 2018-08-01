package com.yuri.xlog.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yuri.xlog.Settings;
import com.yuri.xlog.XLog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        XLog.initialize(Settings.getInstance().setAppTag("ahahah"));
        XLog.initialize().setAppTag("YYYY");

        XLog.i();
        String title = "TITLE";
        String name = "cats";
        XLog.d("title:%s,name:%s,items:%d", title, name, 22);
        XLog.d("url:%s", "http://www.baidu.com");
        XLog.v("my ssss");
        XLog.w("warning");
        XLog.e("error:%s", "errorcode msssage");

        String json = "{\"addressLatitude\":\"31.246017\",\"addressLongitude\":\"121.609757\",\"city\":\"上海市\",\"province\":\"上海市\",\"header\":{\"clientVersion\":\"1.0.01\",\"requestTime\":1464845832926,\"serviceVersion\":\"1.0\",\"sourceID\":\"1000\",\"userToken\":\"24a4012f-d0de-44f7-9a21-24b62de13f9d\"}}";
        XLog.json(json);
        UserInfo userInfo = new UserInfo();
        userInfo.userName = "lilei";
        userInfo.password = "123123";
        userInfo.age = 23;
        XLog.xobject(userInfo);


        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < 8; i++) {
            list.add("name" + i);
        }
        XLog.xobject(list);

        XLog.net("This is a net log");

        findViewById(R.id.btn_test)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        XLog.i("test btn has clicked");
                    }
                });

        //长文本输出
        String longstr = "意想不到 你的手腕会成为无人机停机坪\n" +
                "study875 发布于2016-07-27 12:25:16 | 30 次阅读\n" +
                "\n" +
                "当大家第一眼看到“nixie”的时候，你会觉得这个手表好像很有趣。错了，这个类似于手表一样的东西，其实是一部性能非常出色的无人机。没错，无人机！\n" +
                "cnBeta招夜间兼职编译一名 点此来函\n" +
                "详细内容已有0个意见  |0次推荐  |0次打分  |事件分:0分  |质量分:0分\n" +
                "电影中能自由组合形状的机器人 就快出现了\n" +
                "study875 发布于2016-07-27 12:24:20 | 12 次阅读\n" +
                "\n" +
                "还记得《超能陆战队》中的磁力机器人吗？能够按照人的指令自动组合成不同形状的材料如今在韩国出现了。相关研究论文已经发表在了科学月刊 Nature Materials 的网络版上。\n" +
                "逐浪CMS电商移动微信系统-z01.com\n" +
                "详细内容已有0个意见  |0次推荐  |0次打分  |事件分:0分  |质量分:0分\n" +
                "“国产良心”《仙剑奇侠传》为啥越做越穷了？\n" +
                "ugmbbc 发布于2016-07-27 12:15:49 | 735 次阅读\n" +
                "\n" +
                "哒哒君现在还记得，自己在大学宿舍和舍友通宵玩《仙剑奇侠传》（下文简称仙剑）的情景，但为了不暴露年龄，哒哒君不会告诉你当时自己玩的是第几代仙 剑...而如今，再回想起这款曾经是中国最风靡，最经典的单机游戏，你是会感慨当年为这款游戏留下的青春年少，还是会感叹——“啊？原来这游戏还“活 着”?”\n" +
                "有技术，爱写博客？来云栖社区 戳我首发\n" +
                "详细内容已有4个意见  |0次推荐  |2次打分  |事件分:-5分  |质量分:-5分\n" +
                "三星决定放弃透明OLED电视产品线\n" +
                "raymon725 发布于2016-07-27 11:48:41 | 1014 次阅读\n" +
                "\n" +
                "早在2008年的时候，三星就展示过自家的透明OLED显示产品。虽然受欢迎程度不如该公司的新旗舰SUHD电视，但用来炫耀技术实力还是相当有趣的。遗憾的是，三星已经决定砍掉半透明OLED显示面板，所有基于透明有机发光二极管（TOLED）的产品都会受到影响。外媒TechRadar援引内部消息人士的话称，“全球销量不足以合理收回其在TOLED技术上的投资”。\n" +
                "网易出品真正稳定的IM云服务\n" +
                "详细内容已有4个意见  |0次推荐  |0次打分  |事件分:0分  |质量分:0分\n" +
                "[图]《神奇女侠》曝最新剧照\n" +
                "teikaei 发布于2016-07-27 11:46:30 | 1502 次阅读\n" +
                "\n" +
                "据外媒报道，自在《蝙蝠侠大战超人：正义黎明》亮相之后，神奇女侠就已经为自己圈粉无数。虽然她的独立电影--《神奇女侠》还未上映，但光在前几日在圣迭戈漫展发布的首支预告片就足以燃爆影迷。现在，《Empire》又带来了一张新的剧照。看样子神奇女侠遇到了危机，只见剧照中的她神情有些慌张，也许对面正站着一位强大的敌人或某样强大的武器。\n" +
                "当贝市场 让智能电视更好玩\n" +
                "详细内容已有6个意见  |1次推荐  |2次打分  |事件分:-5分  |质量分:-5分\n" +
                "苹果Q3营收424亿美元略高于华尔街预期 表现不如去年同期\n" +
                "KungfuDogg 发布于2016-07-27 11:35:47 | 496 次阅读\n" +
                "\n" +
                "尽管苹果产品销量较去年有下滑，但苹果仍然交出了一份稍高于华尔街预期的Q3季度财报。苹果公布了Q3季度营收为424亿美元，季度净利润为78亿美元，共销售出4040万台iPhone。表现略高于华尔街预期的422亿美元和4000万台iPhone销量。尽管如此，苹果 Q3季度iPhone销量表现仍较去年同期的4750万台下滑15%，季度营收较去年同期的4960亿美元也出现显著下滑。\n" +
                "苹果 Apple Store\n" +
                "详细内容已有4个意见  |0次推荐  |1次打分  |事件分:0分  |质量分:-2分\n" +
                "疑似iPhone 7前玻璃面板谍照曝光\n" +
                "raymon725 发布于2016-07-27 11:32:10 | 2083 次阅读\n" +
                "\n" +
                "距离iPhone 7系的发布还有个把月的时间，但仍有不少谜团等到我们去揭开。根据数不清的传闻，“iPhone 7系”同iPhone 6s相比并没有太大的变化，只是机身略薄、对天线外露面进行了微调，同时拥有更大的电池、存储容量、以及升级后的摄像头。6月份泄露的消息和谍照称，iPhone 7会在人们没太注意的某个地方迎来大变动，即实体Home键会换成“平面3D Touch”+触觉反馈的样式。\n" +
                "全网覆盖 睿江BGP带你遨游\n" +
                "详细内容已有4个意见  |1次推荐  |4次打分  |事件分:-10分  |质量分:-10分\n" +
                "不堪重负 摩托不再同步推送谷歌月度安全更新\n" +
                "vivian 发布于2016-07-27 11:27:59 | 1553 次阅读\n" +
                "\n" +
                "北京时间7月27日消息，据科技网站SlashGear报道，眼下，谷歌会定期向安卓设备推送月度安全更新，但由于各家实力有差别，能跟上谷歌节奏的厂商并不多，三星、黑莓、摩托和HTC就是其中的佼佼者。但最新消息显示，与谷歌曾有着千丝万屡联系的摩托罗拉也不堪重负，要放弃月度安全更新了。据悉，未来摩托2016年新品Moto Z和Moto G4将不再同步推送常规性的月度安全更新了。\n" +
                "华夏名网最适合网站的云 5折抢购\n" +
                "详细内容已有9个意见  |0次推荐  |6次打分  |事件分:-15分  |质量分:-15分\n" +
                "库克：明年服务业务营收堪比财富100强公司\n" +
                "vivian 发布于2016-07-27 11:26:46 | 521 次阅读\n" +
                "\n" +
                "北京时间7月27日消息，据科技网站AppleInsider报道，当地时间周二，苹果首席执行官蒂姆·库克(Tim Cook)在财报分析师电话会议上表示，他预计明年公司服务业务营收将与财富100强公司相当。虽然iPhone销售在滑坡，苹果服务业务在不断增长。库克称，包括iTunes、iCloud、Apple Music、Apple Pay、Apple Care和多款App Store在内的服务业务第三财季营收，同比增长19%，达到创记录的60亿美元，其中App Store营收增长37%。\n" +
                "万达网络 - 服务器租用托管\n" +
                "详细内容已有6个意见  |0次推荐  |4次打分  |事件分:-10分  |质量分:-10分\n" +
                "教育部：鼓励中小学实现校园无线网络全覆盖\n" +
                "teikaei 发布于2016-07-27 11:26:34 | 1012 次阅读\n" +
                "\n" +
                "据教育部网站消息，近日，教育部印发《关于新形势下进一步做好普通中小学装备工作的意见》(简称《意见》)。《意见》针对当前存在的装备标准体系不健全，区域、城乡、校际之间发展不平衡，不适应教育教学的需要等问题，创新性的提出了一系列措施，着力构建配备、管理、应用、创新、保障为一体的教育装备工作体系和政府部门、社会、学校共同参与的教育装备工作新格局。\n" +
                "cnBeta.COM 移动版\n" +
                "详细内容已有9个意见  |0次推荐  |7次打分  |事件分:-5分  |质量分:0分\n" +
                "二战时的德国震惊世界：曾无限接近UFO飞碟技术\n" +
                "teikaei 发布于2016-07-27 11:26:02 | 3602 次阅读\n" +
                "\n" +
                "德国人的科学与制造能力应该说属于世界第一梯队，即便被釜底抽薪后的二战时期，德国损失了许多顶尖科学家，但剩下的那批人，仍然给出了许多技惊四座的科技成果。 这里说的飞碟并不是外星飞碟，而是碟形飞行器，近日在一档德国的电视纪录片中，披露了二战时期德国科学家已经开发出飞碟飞行器，并且赋予了其核打击的能力。德国东部图林根州的俄罗斯战俘目前透露了这个消息，他们描述了二战时德国科学家的惊人制造能力。\n" +
                "cnBeta官方iOS客户端\n" +
                "详细内容已有9个意见  |0次推荐  |6次打分  |事件分:-15分  |质量分:-15分\n" +
                "熊孩子购7000Q币买手游装备 腾讯：货到不退款\n" +
                "vivian 发布于2016-07-27 11:25:33 | 1857 次阅读\n" +
                "\n" +
                "放任孩子玩手机游戏的家长注意了！近日，花都和白云都有家长反映，孩子用父母的手机微信钱包购买游戏装备，有家长称发现时已花去数千元，拨打游戏运营商客服，对方称若游戏装备到货，不可退款。倪先生在花都新华镇经营一家便利店，7月24日，他查看自己微信钱包，发现有多笔购买Q币的零钱交易记录，Q币统统充入一QQ号，“是我儿子的QQ号”。\n" +
                "VeryCloud云主机/CDN\n" +
                "详细内容已有8个意见  |1次推荐  |6次打分  |事件分:-5分  |质量分:-5分\n" +
                "远离科技的“世外桃源”：美小镇禁用Wi-Fi和手机\n" +
                "teikaei 发布于2016-07-27 11:14:59 | 1321 次阅读\n" +
                "\n" +
                "据英国《每日邮报》7月26日报道，美国西弗吉尼亚州的绿岸（Green Bank）小镇有143名居民，被称为美国“最安静的小镇”。在这里，当地法律禁止使用手机、Wi-Fi、微波炉等等现代电子科技产品，俨然现代版的“世外桃源”。\n" +
                "详细内容已有8个意见  |1次推荐  |4次打分  |事件分:0分  |质量分:-1分\n" +
                "韩媒称韩国国税局正调查华为分公司\n" +
                "vivian 发布于2016-07-27 11:12:17 | 1217 次阅读\n" +
                "\n" +
                "北京时间7月27日消息，据韩国媒体报道，华为韩国分公司目前正在接受当地税务局的调查。报道称，韩国国税局首尔分局日前派遣国际企业部门官员对华为的纳税行为进行审计。虽然华为表示，这一审计只是每5年进行一次的常规审计，但媒体援引匿名人士的消息称，韩国国税局此次重点关注华为韩国分公司与中国总部之间的交易是否存在偷漏税行为。\n" +
                "详细内容已有11个意见  |0次推荐  |12次打分  |事件分:0分  |质量分:0分\n" +
                "排名前三：微软2015年全球云系统管理软件业务增势强劲\n" +
                "raymon725 发布于2016-07-27 11:12:10 | 582 次阅读\n" +
                "\n" +
                "IDC最近公布了一份2015年云市场调研报告，估计全球在商业云系统管理软件和软件即服务（SaaS）解决方案上的开支增长了24.6%（总额29亿美元）。IDC还预测，到2017年末的时候，会有超过80%的企业IT组织会采用横跨公共云、私有云、或非云服务的混合架构。而在“云优先、移动优先”的政策驱动下，微软公司也在榜单中名列前茅。";
//        XLog.d(longstr);
    }
}

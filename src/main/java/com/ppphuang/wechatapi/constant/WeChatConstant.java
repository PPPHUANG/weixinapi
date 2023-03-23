package com.ppphuang.wechatapi.constant;

import java.util.Arrays;
import java.util.List;

public class WeChatConstant {
    public static String TOKEN = "your token";

    public static String APPID_TEST = "";

    public static String SECRET_TEST = "";

    public static String TOKEN_TEST = "your test token";

    public static String DEFAULT_MSG = "大声一点，信号不好没听见。";

    public static String DEFAULT_EVENT_MSG = "嗯？发生什么了？";

    public static List<String> EAT_SOMETHING = Arrays.asList("吃什么", "吃点", "吃啥");

    public static List<String> DRINK_SOMETHING = Arrays.asList("喝什么", "喝点", "喝啥");

    public static List<String> EVENT_TODAY_HISTORY = Arrays.asList("大事记", "那年今日", "历史上的今天");

    public static List<String> TODAY_HISTORY = Arrays.asList("名人", "名人大事记", "名人今日", "今日名人", "历史上的名人");

    public static List<String> WEATHER = Arrays.asList("天气", "下雨", "热吗","冷吗", "热不热", "冷不冷","下雪", "多少度" , "温度");

    public static List<String> DAY_WEATHER = Arrays.asList("明天", "后天");

    public static List<String> EAT_LIST = Arrays.asList("糖醋排骨", "丽华快餐", "千里香馄饨", "胡子大厨", "天辣小馆", "温暖你的猪", "麺晟", "姚面记", "大米先生", "谷田稻香",
            "麦当劳", "肯德基", "必胜客", "华莱士", "水饺", "牛肉汤","牛肉面","锅贴","披萨","黄焖鸡米饭","烤鸭","热干面","炸排骨","手抓肉","彭浦一炸","过桥米线","绝味鸭脖","花甲",
            "龙虾","寿司","烤冷面","臭豆腐","烤冷面","易食汇","烤脑花","粥","猪脚饭","卤肉饭","WePic自选菜","酸菜鱼","鸡公煲","继光香香鸡","食其家");

    public static List<String> DRINK_LIST = Arrays.asList("星巴克", "乐乐茶", "茶百道", "一点点", "CoCo都可", "七分甜", "蜜雪冰城", "烧仙草","桂源铺", "泰式奶咖","喜茶", "奈雪的茶","皇茶", "沪上阿姨");

    /**
     * subscribe(订阅)、unsubscribe(取消订阅)
     */
    public final static String SUBSCRIBE_EVENT = "subscribe";

    public final static String UNSUBSCRIBE_EVENT = "unsubscribe";
}

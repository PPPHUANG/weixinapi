package com.ppphuang.wechatapi.constant;

public class ApiConstant {

    /**
     * 大事记
     */
    public final static String EVENT_TODAY_HISTORY = "https://zhufred.gitee.io/zreader/ht/event/%s.json";

    /**
     * 出身和逝世
     */
    public final static String TODAY_HISTORY = "https://zhufred.gitee.io/zreader/ht/ld/%s.json";

    /**
     * 获取城市
     */
    public final static String HEFENG_CITY = "https://geoapi.qweather.com/v2/city/lookup?location=%s&key=---";

    /**
     * 当前天气
     */
    public final static String HEFENG_WEATHER = "https://devapi.qweather.com/v7/weather/now?location=%s&key=---";

    /**
     * 三天天气预报
     */
    public final static String HEFENG_3_DAY_WEATHER = "https://devapi.qweather.com/v7/weather/3d?location=%s&key=---";
}

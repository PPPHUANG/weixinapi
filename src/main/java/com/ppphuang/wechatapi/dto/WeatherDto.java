package com.ppphuang.wechatapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class WeatherDto {

    @JsonProperty("code")
    private String code;
    @JsonProperty("updateTime")
    private String updateTime;
    @JsonProperty("fxLink")
    private String fxLink;
    @JsonProperty("now")
    private NowDTO now;
    @JsonProperty("refer")
    private ReferDTO refer;

    @NoArgsConstructor
    @Data
    public static class NowDTO {
        //数据观测时间
        @JsonProperty("obsTime")
        private String obsTime;
        //温度，默认单位：摄氏度
        @JsonProperty("temp")
        private String temp;
        //体感温度，默认单位：摄氏度
        @JsonProperty("feelsLike")
        private String feelsLike;
        @JsonProperty("icon")
        private String icon;
        //天气状况的文字描述，包括阴晴雨雪等天气状态的描述
        @JsonProperty("text")
        private String text;
        //风向360角度
        @JsonProperty("wind360")
        private String wind360;
        //风向
        @JsonProperty("windDir")
        private String windDir;
        //风力等级
        @JsonProperty("windScale")
        private String windScale;
        //风速，公里/小时
        @JsonProperty("windSpeed")
        private String windSpeed;
        //相对湿度，百分比数值
        @JsonProperty("humidity")
        private String humidity;
        //当前小时累计降水量，默认单位：毫米
        @JsonProperty("precip")
        private String precip;
        //大气压强，默认单位：百帕
        @JsonProperty("pressure")
        private String pressure;
        //能见度，默认单位：公里
        @JsonProperty("vis")
        private String vis;
        //云量，百分比数值。可能为空
        @JsonProperty("cloud")
        private String cloud;
        //露点温度。可能为空
        @JsonProperty("dew")
        private String dew;
    }

    @NoArgsConstructor
    @Data
    public static class ReferDTO {
        //原始数据来源，或数据源说明，可能为空
        @JsonProperty("sources")
        private List<String> sources;
        //数据许可或版权声明，可能为空
        @JsonProperty("license")
        private List<String> license;
    }
}

package com.ppphuang.wechatapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DayWeatherDto {

    @JsonProperty("code")
    private String code;
    @JsonProperty("updateTime")
    private String updateTime;
    @JsonProperty("fxLink")
    private String fxLink;
    @JsonProperty("daily")
    private List<DailyDTO> daily;
    @JsonProperty("refer")
    private ReferDTO refer;

    @NoArgsConstructor
    @Data
    public static class ReferDTO {
        @JsonProperty("sources")
        private List<String> sources;
        @JsonProperty("license")
        private List<String> license;
    }

    @NoArgsConstructor
    @Data
    public static class DailyDTO {
        //预报日期
        @JsonProperty("fxDate")
        private String fxDate;
        //日出时间
        @JsonProperty("sunrise")
        private String sunrise;
        //日落时间
        @JsonProperty("sunset")
        private String sunset;
        //月升时间
        @JsonProperty("moonrise")
        private String moonrise;
        //月落时间
        @JsonProperty("moonset")
        private String moonset;
        //月相名称
        @JsonProperty("moonPhase")
        private String moonPhase;
        //月相图标代码
        @JsonProperty("moonPhaseIcon")
        private String moonPhaseIcon;
        //预报当天最高温度
        @JsonProperty("tempMax")
        private String tempMax;
        //预报当天最低温度
        @JsonProperty("tempMin")
        private String tempMin;
        //预报白天天气状况的图标代码
        @JsonProperty("iconDay")
        private String iconDay;
        //预报白天天气状况文字描述
        @JsonProperty("textDay")
        private String textDay;
        //预报夜间天气状况的图标代码
        @JsonProperty("iconNight")
        private String iconNight;
        //预报晚间天气状况文字描述
        @JsonProperty("textNight")
        private String textNight;
        //预报白天风向360角度
        @JsonProperty("wind360Day")
        private String wind360Day;
        //预报白天风向
        @JsonProperty("windDirDay")
        private String windDirDay;
        //预报白天风力等级
        @JsonProperty("windScaleDay")
        private String windScaleDay;
        //预报白天风速，公里/小时
        @JsonProperty("windSpeedDay")
        private String windSpeedDay;
        //预报夜间风向360角度
        @JsonProperty("wind360Night")
        private String wind360Night;
        //预报夜间当天风向
        @JsonProperty("windDirNight")
        private String windDirNight;
        //预报夜间风力等级
        @JsonProperty("windScaleNight")
        private String windScaleNight;
        //预报夜间风速，公里/小时
        @JsonProperty("windSpeedNight")
        private String windSpeedNight;
        //相对湿度，百分比数值
        @JsonProperty("humidity")
        private String humidity;
        //预报当天总降水量，默认单位：毫米
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
        //紫外线强度指数
        @JsonProperty("uvIndex")
        private String uvIndex;
    }
}

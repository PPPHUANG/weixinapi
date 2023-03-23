package com.ppphuang.wechatapi.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppphuang.wechatapi.constant.ApiConstant;
import com.ppphuang.wechatapi.dto.CityCodeDto;
import com.ppphuang.wechatapi.dto.DayWeatherDto;
import com.ppphuang.wechatapi.dto.EventTodayHistoryDto;
import com.ppphuang.wechatapi.dto.WeatherDto;
import com.ppphuang.wechatapi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ThirdApiService {
    public static Gson gson = new Gson();

    public static List<EventTodayHistoryDto> getEventTodayHistoryDtos() {
        // 获取历史上的今天
        String dateByFormat = DateUtil.getDateByFormat(new Date(), DateUtil.FORMAT_PATTERN_MD);
        String uri = String.format(ApiConstant.EVENT_TODAY_HISTORY, dateByFormat);
        try (HttpResponse execute = HttpRequest.get(uri).timeout(1500).execute()) {
            String result = execute.body();
            return gson.fromJson(result, new TypeToken<List<EventTodayHistoryDto>>() {
            }.getType());
        } catch (Exception e) {
            log.error("uri:{}", uri, e);
        }
        return new ArrayList<>();
    }

    public static List<EventTodayHistoryDto> getTodayHistoryDtos() {
        // 获取历史上的今天名人事迹
        String dateByFormat = DateUtil.getDateByFormat(new Date(), DateUtil.FORMAT_PATTERN_MD);
        String uri = String.format(ApiConstant.TODAY_HISTORY, dateByFormat);
        try (HttpResponse execute = HttpRequest.get(uri).timeout(1500).execute()) {
            String result = execute.body();
            return gson.fromJson(result, new TypeToken<List<EventTodayHistoryDto>>() {
            }.getType());
        } catch (Exception e) {
            log.error("uri:{}", uri, e);
        }
        return new ArrayList<>();
    }

    public static String getWeatherString(String regin) {
        WeatherDto weatherDto = getWeather(regin);
        if (weatherDto != null && "200".equals(weatherDto.getCode())) {
            return "当前天气：\n" +
                "观测时间：" + weatherDto.getNow().getObsTime() + "\n" +
                "天气：" + weatherDto.getNow().getText() + "\n" +
                "温度：" + weatherDto.getNow().getTemp() + "摄氏度\n" +
                "体感温度：" + weatherDto.getNow().getFeelsLike() + "摄氏度\n" +
                "风力：" + weatherDto.getNow().getWindDir() + weatherDto.getNow().getWindScale() + "级" + weatherDto.getNow().getWindSpeed() + "公里/小时\n" +
                "相对湿度：" + weatherDto.getNow().getHumidity() + "%\n" +
                "小时降水量：" + weatherDto.getNow().getPrecip() + "毫米\n" +
                "气压：" + weatherDto.getNow().getPressure() + "百帕\n" +
                "云量：" + weatherDto.getNow().getCloud() + "%\n" +
                "能见度：" + weatherDto.getNow().getVis() + "公里\n";
        }
        return "请试着输入：上海天气怎么样 北京热吗 浦东下雨吗";
    }

    public static String getDayWeatherString(String regin) {
        DayWeatherDto weatherDto = getDayWeather(regin);
        if (weatherDto != null && "200".equals(weatherDto.getCode())) {
            weatherDto.getDaily().get(1).setFxDate("明天 " + weatherDto.getDaily().get(1).getFxDate());
            weatherDto.getDaily().get(2).setFxDate("后天 " + weatherDto.getDaily().get(2).getFxDate());
            return weatherDto.getDaily().stream().skip(1).map(w -> {
                return w.getFxDate() + "：\n" +
                    "天气：" + w.getTextDay() + "\n" +
                    "最高温度：" + w.getTempMax() + "摄氏度\n" +
                    "最低温度：" + w.getTempMin() + "摄氏度\n" +
                    "太阳：" + w.getSunrise() + " 升 " + w.getSunset() + " 落\n" +
                    w.getMoonPhase() + "：" + w.getMoonrise() + " 升 " + w.getMoonset() + " 落\n" +
                    "紫外线强度：" + w.getUvIndex() + "\n" +
                    "风力：" + w.getWindDirDay() + w.getWindScaleDay() + "级" + w.getWindSpeedDay() + "公里/小时\n" +
                    "相对湿度：" + w.getHumidity() + "%\n" +
                    "小时降水量：" + w.getPrecip() + "毫米\n" +
                    "气压：" + w.getPressure() + "百帕\n" +
                    "云量：" + w.getCloud() + "%\n" +
                    "能见度：" + w.getVis() + "公里";
            }).collect(Collectors.joining("\n\n"));
        }
        return "请试着输入：浦东明天天气怎么样 湖南后天热吗";
    }

    public static WeatherDto getWeather(String regin) {
        String reginName = regin.substring(0, 2);
        List<CityCodeDto.LocationDTO> cityCodes = getCityCode(reginName);
        if (cityCodes.isEmpty()) {
            return null;
        }
        String reginId = cityCodes.get(0).getId();
        String uri = String.format(ApiConstant.HEFENG_WEATHER, reginId);
        try (HttpResponse execute = HttpRequest.get(uri).timeout(2000).execute()) {
            String result = execute.body();
            return gson.fromJson(result, WeatherDto.class);
        } catch (Exception e) {
            log.error("uri:{}", uri, e);
        }
        return null;
    }

    public static DayWeatherDto getDayWeather(String regin) {
        String reginName = regin.substring(0, 2);
        List<CityCodeDto.LocationDTO> cityCodes = getCityCode(reginName);
        if (cityCodes.isEmpty()) {
            return null;
        }
        String reginId = cityCodes.get(0).getId();
        String uri = String.format(ApiConstant.HEFENG_3_DAY_WEATHER, reginId);
        try (HttpResponse execute = HttpRequest.get(uri).timeout(2000).execute()) {
            String result = execute.body();
            return gson.fromJson(result, DayWeatherDto.class);
        } catch (Exception e) {
            log.error("uri:{}", uri, e);
        }
        return null;
    }

    public static List<CityCodeDto.LocationDTO> getCityCode(String regin) {
        try (HttpResponse execute = HttpRequest.get(String.format(ApiConstant.HEFENG_CITY, regin)).timeout(1000).execute()) {
            String result = execute.body();
            CityCodeDto cityCodeDto = gson.fromJson(result, CityCodeDto.class);
            if (cityCodeDto != null && "200".equals(cityCodeDto.getCode()) && !cityCodeDto.getLocation().isEmpty()) {
                return cityCodeDto.getLocation();
            }
        } catch (Exception e) {
            log.error("uri:{}", String.format(ApiConstant.HEFENG_CITY, regin), e);
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(getTodayHistoryDtos());
    }
}

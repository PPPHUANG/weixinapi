package com.ppphuang.wechatapi.service;

import com.ppphuang.wechatapi.constant.WeChatConstant;
import com.ppphuang.wechatapi.dto.EventTodayHistoryDto;
import com.ppphuang.wechatapi.dto.TextMessage;
import com.ppphuang.wechatapi.util.ListUtil;
import com.ppphuang.wechatapi.util.WechatMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TextReplyService {
    private static final String FROM_USER_NAME = "FromUserName";
    private static final String TO_USER_NAME = "ToUserName";
    private static final String CONTENT = "Content";

    /**
     * 自动回复文本内容
     *
     * @param requestMap
     * @return
     */
    public String reply(Map<String, String> requestMap) {
        String wechatId = requestMap.get(FROM_USER_NAME);
        String gongzhonghaoId = requestMap.get(TO_USER_NAME);

        TextMessage textMessage = WechatMessageUtils.getDefaultTextMessage(wechatId, gongzhonghaoId);

        String content = requestMap.get(CONTENT);
        if (content == null) {
            return null;
//            textMessage.setContent(WeChatConstant.DEFAULT_MSG);
        } else {
            log.info("原始content：{}", content);
            String replyContent = getReplyContent(content);
            log.info("replyContent：{}", replyContent);
            if (StringUtils.isEmpty(replyContent)) {
                return null;
//                textMessage.setContent(WeChatConstant.DEFAULT_MSG);
            } else {
                textMessage.setContent(replyContent);
            }
        }
        return WechatMessageUtils.textMessageToXml(textMessage);
    }

    public static String getReplyContent(String content) {
        if (WeChatConstant.EAT_SOMETHING.stream().anyMatch(content::contains)) {
            // shuffle 打乱顺序
//             Collections.shuffle(WeChatConstant.EAT_LIST);
//             return "那就吃" + WeChatConstant.EAT_LIST.get(0) + "吧";
        } else if(WeChatConstant.DRINK_SOMETHING.stream().anyMatch(content::contains)) {
            // shuffle 打乱顺序
            Collections.shuffle(WeChatConstant.DRINK_LIST);
            return "喝个" + WeChatConstant.DRINK_LIST.get(0) + "吧";
        } else if (WeChatConstant.EVENT_TODAY_HISTORY.stream().anyMatch(content::contains)) {
            List<EventTodayHistoryDto> eventTodayHistoryDtos = ThirdApiService.getEventTodayHistoryDtos();
            eventTodayHistoryDtos = ListUtil.getRandomList(eventTodayHistoryDtos, Math.min(eventTodayHistoryDtos.size() / 2, 10));
            return eventTodayHistoryDtos.stream().map(EventTodayHistoryDto::getTitle).collect(Collectors.joining("\n\n"));
        } else if (WeChatConstant.TODAY_HISTORY.stream().anyMatch(content::contains)) {
            List<EventTodayHistoryDto> eventTodayHistoryDtos = ThirdApiService.getTodayHistoryDtos();
            return eventTodayHistoryDtos.stream().map(EventTodayHistoryDto::getTitle).collect(Collectors.joining("\n\n"));
        } else if (WeChatConstant.DAY_WEATHER.stream().anyMatch(content::contains) && WeChatConstant.WEATHER.stream().anyMatch(content::contains)) {
//            return ThirdApiService.getDayWeatherString(content);
        } else if (WeChatConstant.WEATHER.stream().anyMatch(content::contains)) {
//            return ThirdApiService.getWeatherString(content);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getReplyContent("那年今日"));
    }
}

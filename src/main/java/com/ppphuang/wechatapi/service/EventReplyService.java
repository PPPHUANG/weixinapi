package com.ppphuang.wechatapi.service;

import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppphuang.wechatapi.constant.ApiConstant;
import com.ppphuang.wechatapi.constant.WeChatConstant;
import com.ppphuang.wechatapi.dto.EventTodayHistoryDto;
import com.ppphuang.wechatapi.dto.TextMessage;
import com.ppphuang.wechatapi.util.DateUtil;
import com.ppphuang.wechatapi.util.WechatMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EventReplyService {
    private static final String FROM_USER_NAME = "FromUserName";
    private static final String TO_USER_NAME = "ToUserName";
    private static final String EVENT = "Event";

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

        //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
        String event = requestMap.get(EVENT);
        if (event == null) {
            textMessage.setContent(WeChatConstant.DEFAULT_EVENT_MSG);
        } else {
            log.info("原始event：{}", event);
            String replyeEvent = getReplyContent(event);
            log.info("replyeEvent：{}", replyeEvent);
            if (StringUtils.isEmpty(replyeEvent)) {
                textMessage.setContent(WeChatConstant.DEFAULT_EVENT_MSG);
            } else {
                textMessage.setContent(replyeEvent);
            }
        }
        return WechatMessageUtils.textMessageToXml(textMessage);
    }

    public static String getReplyContent(String event) {
        if (WeChatConstant.SUBSCRIBE_EVENT.equals(event)) {
            List<EventTodayHistoryDto> eventTodayHistoryList = ThirdApiService.getEventTodayHistoryDtos();
            List<EventTodayHistoryDto> todayHistoryList = ThirdApiService.getTodayHistoryDtos();
            return "咦！被你发现了！欢迎你今天的关注！\n看看历史上的今天发生了什么吧：\n\n"
                    + todayHistoryList.get(0).getTitle() + "\n\n"
                    + todayHistoryList.get(1).getTitle() + "\n\n"
                    + eventTodayHistoryList.get(0).getTitle() + "\n\n"
                    + eventTodayHistoryList.get(1).getTitle();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getReplyContent("subscribe"));
    }
}

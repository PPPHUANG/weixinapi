package com.ppphuang.wechatapi.controller;

import com.ppphuang.wechatapi.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("wechat")
public class WechatController {
    @Autowired
    WechatService wechatService;

    @RequestMapping("helloworld")
    public String helloWold() {
        return "HelloWorld";
    }
    /**
     * 微信消息回调
     *
     * @param request
     */
    @PostMapping("callback")
    public String callback(HttpServletRequest request) {
        try {
            String respMessage = wechatService.callback(request);
            if (StringUtils.isEmpty(respMessage)) {
                log.info("不回复消息");
                return null;
            }
            return respMessage;
        } catch (Throwable e) {
            log.error("微信发送消息失败", e);
        }
        return null;
    }

    /**
     * 微信消息回调
     *
     * @param request
     */
    @PostMapping("callback/test")
    public String callbackTest(HttpServletRequest request) {
        try {
            String respMessage = wechatService.callback(request);
            if (StringUtils.isEmpty(respMessage)) {
                log.info("不回复消息");
                return null;
            }
            return respMessage;
        } catch (Throwable e) {
            log.error("微信发送消息失败", e);
        }
        return null;
    }
}

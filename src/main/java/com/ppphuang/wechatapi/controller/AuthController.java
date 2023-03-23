package com.ppphuang.wechatapi.controller;

import com.ppphuang.wechatapi.constant.WeChatConstant;
import com.ppphuang.wechatapi.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@Slf4j
@RequestMapping("wechat")
public class AuthController {

    /**
     * 微信校验
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @GetMapping("callback")
    public String callback(String signature, String timestamp, String nonce, String echostr) {
        log.info("微信校验消息，signature:{}，timestamp:{}，nonce:{}，echostr:{}", signature, timestamp, nonce, echostr);
//        signature:c96233a4bc8cf82b763f2139adc9820d430ef5ad，timestamp:1657959333，nonce:140567105，echostr:7517511999014302693
        //判断是否来自自己的公众号
        String sha1 = WechatService.getSHA1(WeChatConstant.TOKEN, timestamp, nonce);
        if (Objects.equals(sha1, signature)) {
            return echostr;
        }
        return null;
//        return WechatService.checkSignature(signature, timestamp, nonce) ? echostr : null;
    }

    /**
     * 微信校验
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @GetMapping("callback/test")
    public String callbackTest(String signature, String timestamp, String nonce, String echostr) {
        log.info("微信校验消息，signature:{}，timestamp:{}，nonce:{}，echostr:{}", signature, timestamp, nonce, echostr);
//        signature:c96233a4bc8cf82b763f2139adc9820d430ef5ad，timestamp:1657959333，nonce:140567105，echos
        //判断是否来自自己的公众号
        String sha1 = WechatService.getSHA1(WeChatConstant.TOKEN_TEST, timestamp, nonce);
        if (Objects.equals(sha1, signature)) {
            return echostr;
        }
        return null;
//        return WechatService.checkSignature(signature, timestamp, nonce) ? echostr : null;
    }
}

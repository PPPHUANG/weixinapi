package com.ppphuang.wechatapi.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.Gson;
import com.ppphuang.wechatapi.constant.WeChatConstant;
import com.ppphuang.wechatapi.constant.WechatMsgTypeConstant;
import com.ppphuang.wechatapi.util.WechatMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WechatService {
    public static Gson gson = new Gson();

    @Autowired
    private TextReplyService textReplyService;

    @Autowired
    private EventReplyService eventReplyService;

    /**
     * 微信回复
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public String callback(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        try {
            Map<String, String> requestMap = WechatMessageUtils.parseXml(request);
            log.info("微信接收到消息:{}", gson.toJson(requestMap));
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 处理其他消息，暂时不做回复
            switch (msgType) {
                case WechatMsgTypeConstant.MESSAGE_TYPE_TEXT:
                    // 文本消息处理
                    return textReplyService.reply(requestMap);
                case WechatMsgTypeConstant.MESSAGE_TYPE_EVENT:
                    // 事件消息处理
                    return eventReplyService.reply(requestMap);
                default:
                    return textReplyService.reply(requestMap);
            }
        } catch (Throwable e) {
            log.error("回复消息错误", e);
        }
        // 不做回复
        return null;
    }

    public static String getSHA1(String token, String timestamp, String nonce) {
        try {
            String[] array = new String[]{token, timestamp, nonce};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 3; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        Cache<String, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .maximumSize(10_000)
                .build();

// 查找一个缓存元素， 没有查找到的时候返回null
        Integer graph = cache.getIfPresent("key");
        System.out.println(graph);
// 查找缓存，如果缓存不存在则生成缓存元素,  如果无法生成则返回null
        graph = cache.get("key", k -> 1);
        System.out.println(graph);
// 添加或者更新一个缓存元素
        cache.put("key", graph);
        graph = cache.get("key", k -> 2);
        System.out.println(graph);
// 移除一个缓存元素
        cache.invalidate("key");
        graph = cache.get("key", k -> 3);
        System.out.println(graph);
        Thread.sleep(2000);
        graph = cache.get("key", k -> 4);
        System.out.println(graph);
    }
}

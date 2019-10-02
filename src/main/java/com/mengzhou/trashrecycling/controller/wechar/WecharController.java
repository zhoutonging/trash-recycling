package com.mengzhou.trashrecycling.controller.wechar;

import com.alibaba.fastjson.JSONObject;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
import com.mengzhou.trashrecycling.common.wechar.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取用户信息
 *
 * @author ZHOUTONG
 * @date 2019年09月24日 16:04
 */
@RestController
@RequestMapping("/api/")
public class WecharController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(WecharController.class);

    @Autowired
    private RedisUtil redisUtil;

    // 微信小程序ID
    @Value("${wx.appId}")
    String appid;
    // 微信小程序秘钥
    @Value("${wx.secret}")
    String secret;

    /**
     * 登录
     *
     * @param
     */
    @GetMapping(value = "/login")
    public Map<String, Object> login(String code) {
        Map<String, Object> modelMap = new HashMap<>(16);

        // 根据小程序传过来的code发送url请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        // 发送请求，返回Json字符串
        String str = WeChatUtil.httpRequest(url, "GET", null);
        // 转成Json对象 获取openid
        JSONObject jsonObject = JSONObject.parseObject(str);

        // openId
        String openId = jsonObject.get("openid").toString();
        String sessionKey = jsonObject.get("session_key").toString();

        if (!redisUtil.exists(sessionKey)) {
            redisUtil.set(sessionKey, openId, 20L);
        }

        modelMap.put("sessionKey", sessionKey);
        modelMap.put("success", true);

        // TODO 微信登录后续操作

        return modelMap;
    }
}

package com.mengzhou.trashrecycling.controller.wechar;

import com.alibaba.fastjson.JSONObject;
import com.mengzhou.trashrecycling.common.Dto.WecharDto;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
import com.mengzhou.trashrecycling.common.wechar.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
     * 微信登录（获取sessionKey和openId）
     *
     * @param
     */
    @GetMapping(value = "/login")
    public Map<String, Object> login(String code) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            // 根据小程序传过来的code发送url请求
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
            // 发送请求，返回Json字符串
            String str = WeChatUtil.httpRequest(url, "GET", null);
            // 转成Json对象 获取openid
            JSONObject jsonObject = JSONObject.parseObject(str);

            // openId
            String openId = jsonObject.get("openid").toString();
            String sessionKey = jsonObject.get("session_key").toString();
            //放入缓存
            redisUtil.set(sessionKey, openId, 172800L);

            modelMap.put("success", true);
            modelMap.put("sessionKey", sessionKey);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("data", "获取sessionKey时出现异常");
            log.error("(微信登录)获取sessionKey和openId时出现异常:" + e.getMessage());
            return modelMap;
        }

    }

    /**
     * 查询sessionKey是否失效
     *
     * @param sessionKey
     * @return
     */
    @GetMapping("findBySessionKey")
    public Map<String, Object> findBySessionKey(String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);
        //判断redis的key是否失效
        if (!redisUtil.exists(sessionKey)) {
            modelMap.put("success", false);
            modelMap.put("msg", "redis的Key已经失效");
            return modelMap;
        }

        modelMap.put("success", true);
        modelMap.put("msg", "查询成功");

        return modelMap;
    }

    @GetMapping("findTest")
    public Map<String, Object> findTest(String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);
        if (redisUtil.exists(sessionKey)) {
            String serrsion = redisUtil.get(sessionKey).toString();

            modelMap.put("success", false);
            modelMap.put("data", serrsion);
            return modelMap;
        }
        return modelMap;
    }
}

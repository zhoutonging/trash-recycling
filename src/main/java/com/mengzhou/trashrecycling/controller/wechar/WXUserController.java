package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.model.User;
import com.mengzhou.trashrecycling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户管理前端控制器(微信)
 *
 * @author CC
 * @since 2019-10-17
 */
@RestController
@RequestMapping("/api/user")
public class WXUserController {

    @Autowired
    private UserService userService;

    /**
     * 微信用户添加
     *
     * @param user
     * @param sessionKey
     * @return
     */
    @PostMapping("save")
    public Map<String, Object> save(User user, String sessionKey) {
        return userService.saveWechar(user, sessionKey);
    }
}


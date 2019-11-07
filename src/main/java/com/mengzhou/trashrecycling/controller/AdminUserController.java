package com.mengzhou.trashrecycling.controller;


import com.mengzhou.trashrecycling.common.websocket.WebSocketServlet;
import com.mengzhou.trashrecycling.model.Adminuser;
import com.mengzhou.trashrecycling.service.AdminuserService;
import com.mengzhou.trashrecycling.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录前端控制器
 *
 * @author CC
 * @since 2019-11-05
 */
@RestController
@RequestMapping("/api/adminuser/")
public class AdminUserController {

    @Autowired
    private AdminuserService adminuserService;

    @Autowired
    WebSocketServlet wbs;


    /**
     * 用户登录
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("userLogin")
    public Map<String, Object> userLogin(Adminuser user, HttpSession session) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            //1.获取用户信息
            Subject subject = SecurityUtils.getSubject();

            Adminuser user1 = adminuserService.findByUserName(user.getUserName());

            if (user1 == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "帐号或密码错误");
                return modelMap;
            }

            //运算次数
            int count = 2;
            //加密方式
            String algorithmName = "md5";

            //最终加密结果
            String password = new SimpleHash(algorithmName, user.getPassword(), user1.getSalt(), count).toString();

            //封装用户数据
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), password);

            // 3.执行登录方法

            // 只要没有异常就代表登录成功
            subject.login(token);

            session.setAttribute("userName", user1.getUserName());

            modelMap.put("success", true);
            modelMap.put("msg", "登录成功");
            return modelMap;
        } catch (UnknownAccountException e) {
            modelMap.put("success", false);
            modelMap.put("msg", "帐号或密码错误");
            return modelMap;
        } catch (IncorrectCredentialsException e) {
            modelMap.put("success", false);
            modelMap.put("msg", "帐号或密码错误");
            return modelMap;
        }

    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("register")
    public Map<String, Object> register(Adminuser user, String roles) {

        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            Adminuser user1 = adminuserService.findByUserName(user.getUserName());

            if (user1 != null) {
                modelMap.put("success", false);
                modelMap.put("msg", "用户名已存在");
                return modelMap;
            }

            //加密盐值
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            //运算次数
            int count = 2;
            //加密方式
            String algorithmName = "md5";
            //最终加密结果
            String password = new SimpleHash(algorithmName, user.getPassword(), salt, count).toString();

            user.setPassword(password);
            user.setSalt(salt);
            adminuserService.save(user);

            modelMap.put("success", true);
            modelMap.put("msg", "注册成功");
            wbs.onMessage(2);

            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "注册失败");
            return modelMap;
        }
    }
}


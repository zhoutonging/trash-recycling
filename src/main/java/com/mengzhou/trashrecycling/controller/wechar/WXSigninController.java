package com.mengzhou.trashrecycling.controller.wechar;


import com.mengzhou.trashrecycling.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 签到信息控制器
 *
 * @author CC
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/api/signin/")
public class WXSigninController {

    @Autowired
    private SigninService signinService;

    /**
     * 查询签到积分
     *
     * @return
     */
    @GetMapping("findById")
    public Map<String, Object> findByIdWechat() {
        return signinService.findByIdWechat(1);
    }

}


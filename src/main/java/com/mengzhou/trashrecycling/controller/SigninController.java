package com.mengzhou.trashrecycling.controller;


import com.mengzhou.trashrecycling.model.Signin;
import com.mengzhou.trashrecycling.service.SigninService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * 签到信息控制器
 *
 * @author CC
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/signin/")
public class SigninController {

    @Autowired
    private SigninService signinService;

    /**
     * 修改签到积分
     *
     * @param signin
     * @return
     */
    @GetMapping("modifyById")
    public LayuiResult modifyById(Signin signin) {
        return signinService.modifyById(signin);
    }

    /**
     * 查询签到积分
     *
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll() {
        return signinService.findAll();
    }


}


package com.mengzhou.trashrecycling.controller;

import com.mengzhou.trashrecycling.service.AdminuserService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户前端控制器
 *
 * @author ZHOUTONG
 * @date 2019年11月06日 19:10
 */
@RestController
@RequestMapping("/sysUser/")
public class SysUserController {

    @Autowired
    private AdminuserService adminuserService;

    /**
     * 查询所有系统用户
     *
     * @param page
     * @param limit
     * @param userName
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page, Integer limit, String userName) {
        return adminuserService.findAll(page, limit, userName);
    }
}

package com.mengzhou.trashrecycling.controller;

import com.mengzhou.trashrecycling.model.Adminuser;
import com.mengzhou.trashrecycling.service.AdminuserService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
     * 添加用户
     *
     * @param adminuser
     * @return
     */
    @PostMapping("save")
    public LayuiResult save(Adminuser adminuser) {
        return adminuserService.save(adminuser);
    }

    /**
     * 根据id删除系统用户
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer id) {
        return adminuserService.deleteById(id);
    }

    /**
     * 修改用户
     *
     * @param adminuser
     * @param beforePsd 原密码
     * @return
     */
    @PostMapping("modifyById")
    public LayuiResult modifyById(Adminuser adminuser, String beforePsd) {
        return adminuserService.modifyById(adminuser, beforePsd);
    }

    /**
     * 根据id查询帐号信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public LayuiResult findById(Integer id) {
        return adminuserService.findById(id);
    }


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

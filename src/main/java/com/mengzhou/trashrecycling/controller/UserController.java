package com.mengzhou.trashrecycling.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.User;
import com.mengzhou.trashrecycling.service.UserService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理前端控制器
 *
 * @author CC
 * @since 2019-10-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer id) {
        return userService.deleteById(id);
    }

    /**
     * 查询所有用户
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page, Integer limit) {

        PageHelper.startPage(page, limit);

        List<User> userList = userService.findAll();

        PageInfo pageInfo = new PageInfo(userList);
        return LayuiResult.success(pageInfo.getTotal(), pageInfo.getList());
    }
}


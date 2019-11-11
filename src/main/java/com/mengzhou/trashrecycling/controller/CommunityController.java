package com.mengzhou.trashrecycling.controller;


import com.mengzhou.trashrecycling.service.CommunityService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社区动态前端控制器
 *
 * @author CC
 * @since 2019-11-09
 */
@RestController
@RequestMapping("/community/")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    /**
     * 根据id删除社区动态
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer id) {
        return communityService.deleteById(id);
    }

    /**
     * 修改审核状态
     *
     * @param id
     * @return
     */
    @GetMapping("modifyByStatus")
    public LayuiResult modifyByStatus(Integer id) {
        return communityService.modifyByStatus(id);
    }

    /**
     * 查询所有社区动态
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page, Integer limit) {
        return communityService.findAll(page, limit);
    }
}


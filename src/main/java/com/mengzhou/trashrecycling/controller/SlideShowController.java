package com.mengzhou.trashrecycling.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.Product;
import com.mengzhou.trashrecycling.model.SlideShow;
import com.mengzhou.trashrecycling.service.SlideShowService;
import com.mengzhou.trashrecycling.utils.JsonData;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页广告位前端控制器
 *
 * @author CC
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/slideShow/")
@Slf4j
public class SlideShowController {

    @Autowired
    private SlideShowService slideShowService;

    /**
     * 添加轮播图
     *
     * @param slideShow
     * @return
     */
    @PostMapping("save")
    public LayuiResult save(SlideShow slideShow) {

        if (slideShow.getImage() == null || slideShow.getDescribe() == null) {
            return LayuiResult.fail("添加失败,图片或者图书描述不能为空");
        }

        slideShowService.save(slideShow);
        return LayuiResult.success("添加成功");
    }

    /**
     * 删除广告
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer id) {
        if (id == null) {
            log.error("id为空");
            return LayuiResult.fail("id不能为空为空");
        }

        slideShowService.removeById(id);
        return LayuiResult.success("删除成功");
    }

    /**
     * 根据Id修改广告
     *
     * @param slideShow
     * @return
     */
    @PostMapping("modifyById")
    public LayuiResult modifyById(SlideShow slideShow) {
        if (slideShow.getId() == null) {
            log.error("广告id为空");
            return LayuiResult.fail("广告id为空");
        }

        slideShowService.modifyById(slideShow);
        return LayuiResult.success("更新成功");
    }

    /**
     * 修改广告状态
     *
     * @param id
     * @param status
     * @return
     */
    @GetMapping("modifyByStatus")
    public LayuiResult modifyByStatus(Integer id, Integer status) {
        if (id == null || status == null) {
            log.debug("id或状态为空");
            return LayuiResult.fail("id或状态为空");
        }

        slideShowService.modifyByStatus(id, status);
        return LayuiResult.success("更改状态成功");
    }

    /**
     * 根据ID查询广告信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public LayuiResult findById(Integer id) {

        if (id == null) {
            log.error("广告Id为空");
            return LayuiResult.fail("广告Id不能为空");
        }

        SlideShow slideShow = slideShowService.findById(id);
        return LayuiResult.success(slideShow);
    }

    /**
     * 查询广告列表
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page, Integer limit) {

        //分页
        Page pageObject = PageHelper.startPage(page, limit);

        List<SlideShow> productList = slideShowService.findAll();

        PageInfo pageInfo = new PageInfo(productList);
        return LayuiResult.success(pageInfo.getTotal(), productList);

    }
}


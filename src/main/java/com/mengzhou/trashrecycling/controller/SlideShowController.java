package com.mengzhou.trashrecycling.controller;


import com.mengzhou.trashrecycling.model.SlideShow;
import com.mengzhou.trashrecycling.service.SlideShowService;
import com.mengzhou.trashrecycling.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页轮播图前端控制器
 *
 * @author CC
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/slideShow/")
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
    public Map<String, Object> save(SlideShow slideShow) {
        Map<String, Object> modelMap = new HashMap<>();

        slideShowService.save(slideShow);
        modelMap.put("success", true);
        modelMap.put("msg", "添加成功");
        return modelMap;
    }
}


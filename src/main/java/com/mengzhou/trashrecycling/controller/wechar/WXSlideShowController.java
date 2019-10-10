package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.service.SlideShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 首页广告位(微信)
 *
 * @author ZHOUTONG
 * @date 2019年10月08日 18:54
 */
@RestController
@RequestMapping("/api/slideShow/")
public class WXSlideShowController {

    @Autowired
    private SlideShowService slideShowService;

    /**
     * 查询广告位轮播列表
     *
     * @return
     */
    @GetMapping("findAll")
    public Map<String, Object> findAll() {
        return slideShowService.findAllWechar();
    }

    /**
     * 根据id查询广告
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Map<String, Object> findById(Integer id) {
        return slideShowService.findByIdWechar(id);
    }
}

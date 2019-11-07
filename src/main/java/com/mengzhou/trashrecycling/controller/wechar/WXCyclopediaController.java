package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.service.CyclopediaService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 环保百科前端控制器(微信)
 *
 * @author ZHOUTONG
 * @date 2019年11月04日 20:03
 */
@RequestMapping("/api/cyclopedia/")
@RestController
public class WXCyclopediaController {

    @Autowired
    private CyclopediaService cyclopediaService;

    /**
     * 查询所有环保百科信息
     *
     * @return
     */
    @GetMapping("findAll")
    public Map<String, Object> findAll() {
        return cyclopediaService.findAllWechat();
    }

    /**
     * 根据id查询环保百科信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Map<String, Object> findById(Integer id) {
        return cyclopediaService.findByIdWechat(id);
    }
}

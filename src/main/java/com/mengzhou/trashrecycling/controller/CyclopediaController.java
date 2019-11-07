package com.mengzhou.trashrecycling.controller;


import com.mengzhou.trashrecycling.model.Cyclopedia;
import com.mengzhou.trashrecycling.service.CyclopediaService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 环保百科控制器
 *
 * @author CC
 * @since 2019-11-03
 */
@RestController
@RequestMapping("/cyclopedia")
public class CyclopediaController {

    @Autowired
    private CyclopediaService cyclopediaService;

    /**
     * 添加环保百科
     *
     * @param cyclopedia
     * @return
     */
    @PostMapping("save")
    public LayuiResult save(Cyclopedia cyclopedia) {
        return cyclopediaService.save(cyclopedia);
    }

    /**
     * 删除环保百科
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer id) {
        return cyclopediaService.deleteById(id);
    }

    /**
     * 修改环保百科
     *
     * @param cyclopedia
     * @return
     */
    @PostMapping("modifyById")
    public LayuiResult modifyById(Cyclopedia cyclopedia) {
        return cyclopediaService.modifyById(cyclopedia);
    }

    /**
     * 根据id查询环保百科
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public LayuiResult findById(Integer id) {
        return cyclopediaService.findById(id);
    }

    /**
     * 查询所有环保百科
     *
     * @param page
     * @param limit
     * @param cyclopediaName
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "limit", defaultValue = "10") Integer limit, String cyclopediaName) {
        return cyclopediaService.findAll(page, limit, cyclopediaName);
    }
}


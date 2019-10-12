package com.mengzhou.trashrecycling.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.Recyclecategory;
import com.mengzhou.trashrecycling.service.RecyclecategoryService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 垃圾类别控制器
 *
 * @author CC
 * @since 2019-10-05
 */
@RestController
@RequestMapping("/recyclecategory/")
public class RecyclecategoryController {

    @Autowired
    private RecyclecategoryService recyclecategoryService;

    /**
     * 添加垃圾类别
     *
     * @param recyclecategory
     * @return
     */
    @GetMapping("save")
    public LayuiResult save(Recyclecategory recyclecategory) {
        return recyclecategoryService.save(recyclecategory);
    }

    /**
     * 根据id删除垃圾类别
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer id) {
        return recyclecategoryService.deleteById(id);
    }

    /**
     * 根据id修改垃圾类别
     *
     * @param recyclecategory
     * @return
     */
    @GetMapping("modifyById")
    public LayuiResult modifyById(Recyclecategory recyclecategory) {
        return recyclecategoryService.modifyById(recyclecategory);
    }


    /**
     * 查询所有垃圾类别
     *
     * @param limit
     * @param page
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);

        List<Recyclecategory> recyclecategoryList = recyclecategoryService.findAll();
        PageInfo pageInfo = new PageInfo(recyclecategoryList);
        return LayuiResult.success(pageInfo.getTotal(), pageInfo.getList());
    }
}


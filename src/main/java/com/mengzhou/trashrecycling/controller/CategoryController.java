package com.mengzhou.trashrecycling.controller;


import com.mengzhou.trashrecycling.model.Category;
import com.mengzhou.trashrecycling.service.CategoryService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CC
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/findAll")
    public LayuiResult findAll() {
        List<Category> categoryList = categoryService.findAll();
        return LayuiResult.success(categoryList);
    }
}


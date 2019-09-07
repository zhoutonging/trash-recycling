package com.mengzhou.trashrecycling.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.Product;
import com.mengzhou.trashrecycling.service.ProductService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 商品信息控制器
 *
 * @author CC
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 添加商品信息
     *
     * @param product
     * @return
     */
    @PostMapping("save")
    public LayuiResult save(Product product) {
        productService.save(product);
        return LayuiResult.success("添加成功");
    }

    /**
     * 根据Id删除信息
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult removeById(Integer id) {

        if (id == null) {
            return LayuiResult.fail("商品Id不能为空");
        }

        productService.remove(id);

        return LayuiResult.success("删除成功");
    }

    /**
     * 更改商品上下架状态
     *
     * @param id
     * @param status
     * @return
     */
    @GetMapping("modifyByStatus")
    public LayuiResult modifyByStatus(Integer id, Integer status) {

        if (id == null || status == null) {
            return LayuiResult.fail("id或status为空");
        }
        productService.modifyByStatus(id, status);
        return LayuiResult.success("更新成功");
    }

    /**
     * 根据Id修改商品信息
     *
     * @param product
     * @return
     */
    @PostMapping("modifyById")
    public LayuiResult modifyById(Product product) {

        if (product.getId() == null) {
            return LayuiResult.fail("id为空");
        }

        productService.modifyById(product);
        return LayuiResult.success("更新成功");

    }

    /**
     * 根据商品状态查询商品列表
     *
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page,Integer limit, Product product) {
        //分页
        Page pageObject = PageHelper.startPage(page, limit);

        List<Product> productList = productService.findAll(product);

        PageInfo pageInfo = new PageInfo(productList);
        return LayuiResult.success(pageInfo.getTotal(), productList);
    }

    /**
     * 根据Id查询商品信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public LayuiResult findById(Integer id) {

        if (id == null) {
            return LayuiResult.fail("商品Id不能为空");
        }

        Product product = productService.findById(id);

        return LayuiResult.success(product);
    }
}


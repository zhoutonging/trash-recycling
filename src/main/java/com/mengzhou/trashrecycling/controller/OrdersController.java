package com.mengzhou.trashrecycling.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.Orders;
import com.mengzhou.trashrecycling.service.OrdersService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单详情控制器
 *
 * @author CC
 * @since 2019-09-07
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 删除订单信息
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(String id) {
        return ordersService.deleteById(id);
    }

    /**
     * 查询所有订单
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page, Integer limit) {

        //分页
        Page pageObject = PageHelper.startPage(page, limit);

        List<Orders> ordersList = ordersService.findAll();
        PageInfo pageInfo = new PageInfo(ordersList);

        return LayuiResult.success(pageInfo.getTotal(), pageInfo.getList());
    }
}


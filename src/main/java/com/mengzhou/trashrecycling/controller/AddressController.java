package com.mengzhou.trashrecycling.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.Address;
import com.mengzhou.trashrecycling.service.AddressService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 收货地址控制器
 *
 * @author CC
 * @since 2019-09-19
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 根据Id删除收货地址
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer id) {
        return addressService.removeById(id);
    }

    /**
     * 查询所有收货地址OR根据手机号查询
     *
     * @param page
     * @param limit
     * @param mobile
     * @return
     */
    @GetMapping("findAll")
    public LayuiResult findAll(Integer page, Integer limit,String mobile) {
        //分页
        PageHelper.startPage(page, limit);

        List<Address> addressList = addressService.findAll(mobile);

        PageInfo pageInfo = new PageInfo(addressList);
        return LayuiResult.success(pageInfo.getTotal(), addressList);
    }
}


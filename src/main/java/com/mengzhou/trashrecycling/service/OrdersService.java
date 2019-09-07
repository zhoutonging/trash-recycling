package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Orders;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 订单业务逻辑层
 *
 * @author CC
 * @since 2019-09-07
 */
public interface OrdersService extends IService<Orders> {



    /**
     * 查询所有订单
     *
     * @return
     */
    List<Orders> findAll();
}

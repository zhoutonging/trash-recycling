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
     * 添加订单
     *
     * @param productId    商品ID
     * @param ProductCount 商品数量
     */
    void save(Integer productId, Integer ProductCount);

    /**
     * 查询所有订单
     *
     * @return
     */
    List<Orders> findAll();
}

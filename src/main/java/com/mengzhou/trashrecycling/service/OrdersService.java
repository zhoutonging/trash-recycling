package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Orders;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

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
     */
    Map<String,Object> save(Integer productId,Orders orders);

    /**
     * 查询所有订单
     *
     * @return
     */
    List<Orders> findAll();
}

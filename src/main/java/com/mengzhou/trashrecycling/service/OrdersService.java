package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Orders;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

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
     * @param productId 商品ID
     */
    Map<String, Object> save(Integer productId, Orders orders, String sessionKey);

    /**
     * 根据Id删除订单
     *
     * @param id
     * @return
     */
    LayuiResult deleteById(String id);

    /**
     * 根据openId查询订单
     *
     * @param sessionKey
     * @return
     */
    Map<String, Object> findByOpenId(String sessionKey);

    /**
     * 根据openId查询兑换订单次数
     *
     * @param sessionKey
     * @return
     */
    Map<String, Object> findCountByOpenId(String sessionKey);

    /**
     * 查询所有订单or条件查询
     *
     * @return
     */
    List<Orders> findAll(String id, Integer status);

    /**
     * 查询七天内每天成交的订单量
     *
     * @return
     */
    Map<String, Object> find7Count();

    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     */
    Map<String, Object> findOrderById(String id);

}

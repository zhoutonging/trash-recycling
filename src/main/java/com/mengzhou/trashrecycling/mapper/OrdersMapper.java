package com.mengzhou.trashrecycling.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mengzhou.trashrecycling.model.Orders;

import java.util.List;
import java.util.Map;

/**
 * 订单数据访问层
 *
 * @author CC
 * @since 2019-09-07
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 查询七天内每天成交的订单量
     *
     * @return
     */
    List<Map<String, Object>> find7Count();
}

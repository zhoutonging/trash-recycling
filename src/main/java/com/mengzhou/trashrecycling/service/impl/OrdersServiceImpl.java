package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Orders;
import com.mengzhou.trashrecycling.mapper.OrdersMapper;
import com.mengzhou.trashrecycling.service.OrdersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-09-07
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}

package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.model.Orders;
import com.mengzhou.trashrecycling.mapper.OrdersMapper;
import com.mengzhou.trashrecycling.model.Product;
import com.mengzhou.trashrecycling.service.OrdersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-09-07
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ProductService productService;

    @Override
    public void save(Integer productId, Integer ProductCount) {

        // 查询商品信息
        Product product = productService.selectById(productId);

        //计算总金额
        int totalMoney = product.getProductPrice() * ProductCount;

        //TODO 计算总金额
    }

    @Override
    public List<Orders> findAll() {

        List<Orders> ordersList = ordersMapper.selectList(new EntityWrapper<Orders>()
                .orderBy("createTime", false));

        return ordersList;
    }
}

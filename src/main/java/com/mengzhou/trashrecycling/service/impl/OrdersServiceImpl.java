package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.enums.OrderStatusEnum;
import com.mengzhou.trashrecycling.model.Orders;
import com.mengzhou.trashrecycling.mapper.OrdersMapper;
import com.mengzhou.trashrecycling.model.Product;
import com.mengzhou.trashrecycling.service.OrdersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.service.ProductService;
import com.mengzhou.trashrecycling.utils.GenerateNum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-09-07
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Autowired
    private ProductService productService;

    @Override
    public Map<String, Object> save(Integer productId, Orders orders) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            //判断商品id和商品数量是否为空
            if (productId == null || orders.getProductCount() == null) {
                modelMap.put("msg", "商品id或者商品数量为空");
                modelMap.put("success", false);
                log.error("添加订单时出现错误:商品id或者商品数量为空");
                return modelMap;
            }

            // 查询商品信息
            Product product = productService.selectById(productId);
            if (product == null) {
                modelMap.put("msg", "无法查询到商品");
                modelMap.put("success", false);
                log.error("添加订单时出现错误:无法查询到商品");
                return modelMap;
            }

            //计算总金额
            int totalMoney = product.getProductPrice() * orders.getProductCount();
            //TODO 判断用户是否有足够的积分

            orders.setId(GenerateNum.getInstance().GenerateOrder());
            orders.setCreateTime(new Date());
            orders.setProductName(product.getProductName());
            orders.setProductPrice(product.getProductPrice());
            orders.setIntegral(totalMoney);
            orders.setStatus(OrderStatusEnum.SUCCESS.getCode());

            //TODO 获取微信用户sessionKey查询用户收货地址
            ordersMapper.insert(orders);
            modelMap.put("msg", "新增订单成功");
            modelMap.put("success", true);
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("msg", "新增订单时失败");
            modelMap.put("success", false);
            log.error("新增订单时失败");
            return modelMap;
        }

    }

    @Override
    public List<Orders> findAll() {

        List<Orders> ordersList = ordersMapper.selectList(new EntityWrapper<Orders>()
                .orderBy("createTime", false));

        return ordersList;
    }
}

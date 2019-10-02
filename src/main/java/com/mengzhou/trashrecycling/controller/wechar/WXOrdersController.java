package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.model.Orders;
import com.mengzhou.trashrecycling.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * WECHAR 订单管理
 *
 * @author ZHOUTONG
 * @date 2019年09月10日 7:31
 */
@RestController
@RequestMapping("/api/orders/")
public class WXOrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 新增订单
     *
     * @param productId
     * @param orders
     * @return
     */
    @GetMapping("save")
    public Map<String, Object> save(Integer productId, Orders orders) {
        return ordersService.save(productId, orders);
    }
}

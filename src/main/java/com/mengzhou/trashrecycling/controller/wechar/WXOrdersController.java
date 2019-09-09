package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WECHAR 订单管理
 *
 * @author ZHOUTONG
 * @date 2019年09月10日 7:31
 */
@RestController
@RequestMapping("/wx/orders/")
public class WXOrdersController {

    @Autowired
    private OrdersService ordersService;


}

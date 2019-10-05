package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 地址控制器(微信)
 *
 * @author ZHOUTONG
 * @date 2019年10月02日 16:33
 */
@RestController
@RequestMapping("/api/address/")
public class WecharAddressController {

    @Autowired
    private AddressService addressService;
}

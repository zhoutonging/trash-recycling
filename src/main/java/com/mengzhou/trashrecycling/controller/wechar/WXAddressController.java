package com.mengzhou.trashrecycling.controller.wechar;

import com.mengzhou.trashrecycling.model.Address;
import com.mengzhou.trashrecycling.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 地址控制器(微信)
 *
 * @author ZHOUTONG
 * @date 2019年10月02日 16:33
 */
@RestController
@RequestMapping("/api/address/")
public class WXAddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("save")
    public Map<String, Object> save(Address address) {
        return addressService.save(address);
    }
}

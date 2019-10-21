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

    /**
     * 添加收获地址
     *
     * @param address
     * @param sessionKey
     * @return
     */
    @PostMapping("save")
    public Map<String, Object> save(Address address, String sessionKey) {
        return addressService.save(address, sessionKey);
    }

    /**
     * 根据id删除收货地址
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public Map<String, Object> deleteById(Integer id) {
        return addressService.removeByIdWechar(id);
    }

    /**
     * 根据id修改收货地址
     *
     * @param address
     * @return
     */
    @PostMapping("modifyById")
    public Map<String, Object> modifyById(Address address) {
        return addressService.modifyById(address);
    }

    /**
     * 查询用户所有的收货地址
     *
     * @param sessionKey
     * @return
     */
    @GetMapping("findByOpenId")
    public Map<String, Object> findByOpenId(String sessionKey) {
        return addressService.findByOpenIdWechar(sessionKey);
    }


}

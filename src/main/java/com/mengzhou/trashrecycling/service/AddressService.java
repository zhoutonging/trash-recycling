package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Address;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.List;
import java.util.Map;

/**
 * 用户地址业务逻辑层
 *
 * @author CC
 * @since 2019-09-19
 */
public interface AddressService extends IService<Address> {

    /**
     * 添加收货地址(微信)
     *
     * @param address
     */
    Map<String, Object> save(Address address, String sessionKey);

    /**
     * 根据Id删除信息
     *
     * @param id
     */
    LayuiResult removeById(Integer id);

    /**
     * 根据Id删除信息(微信)
     *
     * @param id
     */
    Map<String, Object> removeByIdWechar(Integer id);

    /**
     * 根据id修改收货地址
     *
     * @param address
     * @return
     */
    Map<String, Object> modifyById(Address address);

    /**
     * 根据Id查询收货地址
     *
     * @return
     */
    Address findById(Integer id);

    /**
     * 根据用户openId查询用户下的所有信息
     *
     * @param openId
     * @return
     */
    List<Address> findByOpenId(String openId);

    /**
     * 根据用户openId查询用户的地址(微信)
     *
     * @param sessionKey
     * @return
     */
    Map<String, Object> findByOpenIdWechar(String sessionKey);

    /**
     * 查询所有用户的收货地址
     *
     * @return
     */
    List<Address> findAll(String mobile);

}

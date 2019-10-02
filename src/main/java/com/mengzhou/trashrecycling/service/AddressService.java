package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Address;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.List;

/**
 * 用户地址业务逻辑层
 *
 * @author CC
 * @since 2019-09-19
 */
public interface AddressService extends IService<Address> {

    /**
     * 添加收货地址
     *
     * @param address
     */
    LayuiResult save(Address address);

    /**
     * 根据Id删除信息
     *
     * @param id
     */
    LayuiResult removeById(Integer id);

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
     * 查询所有用户的收货地址
     *
     * @return
     */
    List<Address> findAll(String mobile);

}

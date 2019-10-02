package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.enums.AddressEnum;
import com.mengzhou.trashrecycling.model.Address;
import com.mengzhou.trashrecycling.mapper.AddressMapper;
import com.mengzhou.trashrecycling.service.AddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-09-19
 */
@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public LayuiResult save(Address address) {
        if (address.getAddress() == null || address.getMobile() == null
                || address.getUserName() == null || address.getOpenId() == null) {
            return LayuiResult.fail("必填项不能为空");
        }
        address.setCreateTime(new Date());
        address.setDefaultAddress(AddressEnum.NO.getCode());
        addressMapper.insert(address);
        return LayuiResult.success("添加成功");
    }


    @Override
    public LayuiResult removeById(Integer id) {
        if (id == null) {
            LayuiResult.fail("id不能为空");
        }

        addressMapper.deleteById(id);
        return LayuiResult.success("删除成功");
    }

    @Override
    public Address findById(Integer id) {
        if (id == null) {
            log.error("地址Id为空");
        }

        return addressMapper.selectById(id);
    }

    @Override
    public List<Address> findByOpenId(String openId) {
        if (openId == null) {
            log.error("查询用户收货地址时出现错误,openId为空");
        }

        List<Address> addressList = addressMapper.selectList(new EntityWrapper<Address>().eq("openId", openId));
        return addressList;
    }

    @Override
    public List<Address> findAll(String mobile) {

        if (mobile == null || mobile.equals("")) {
            List<Address> addressList = addressMapper.selectList(new EntityWrapper<Address>());
            return addressList;
        }

        List<Address> addressList = addressMapper.selectList(new EntityWrapper<Address>().like("mobile", mobile));

        return addressList;
    }
}

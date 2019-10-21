package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.enums.AddressEnum;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, Object> save(Address address, String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (address.getAddress() == null || address.getMobile() == null
                    || address.getUserName() == null || sessionKey == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "必填项不能为空");
                log.error("(微信)添加收货地址出现错误,必填项不能为空~");

                return modelMap;
            }

            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey已过期");
                log.error("(微信)添加收货地址出现错误,sessionKey已过期~");

                return modelMap;
            }

            String openId = redisUtil.get(sessionKey).toString();

            address.setOpenId(openId);
            address.setCreateTime(new Date());
            address.setDefaultAddress(AddressEnum.NO.getCode());
            addressMapper.insert(address);

            modelMap.put("success", true);
            modelMap.put("msg", "添加成功");

            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "添加收货地址出现异常");
            log.error("(微信)添加收货地址出现异常：" + e.getMessage());
            return modelMap;

        }
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
    public Map<String, Object> removeByIdWechar(Integer id) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (id == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "(微信)根据Id删除收货地址信息出现错误,id为空");
                log.error("(微信)根据Id删除收货地址信息出现错误,id为空");
                return modelMap;
            }

            addressMapper.deleteById(id);
            modelMap.put("success", true);
            modelMap.put("msg", "删除成功");

            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "(微信)根据Id删除收货地址信息出现异常");
            log.error("(微信)根据Id删除收货地址信息出现异常" + e.getMessage());
            return modelMap;
        }
    }

    @Override
    public Map<String, Object> modifyById(Address address) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (address.getId() == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "(微信)根据Id修改收货地址信息出现错误,id为空");
                log.error("(微信)根据Id修改收货地址信息出现错误,id为空");
                return modelMap;
            }

            addressMapper.updateById(address);
            modelMap.put("success", true);
            modelMap.put("msg", "更新成功");

            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "(微信)根据Id修改收货地址信息出现异常");
            log.error("(微信)根据Id修改收货地址信息出现异常" + e.getMessage());
            return modelMap;
        }

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
            return null;
        }

        List<Address> addressList = addressMapper.selectList(new EntityWrapper<Address>().eq("openId", openId));
        return addressList;
    }

    @Override
    public Map<String, Object> findByOpenIdWechar(String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (sessionKey == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey为空");
                log.error("(微信)查询用户收货地址时出现错误,sessionKey为空");
                return modelMap;
            }

            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey已过期");
                log.error("(微信)查询用户收货地址时出现错误,sessionKey已过期");
                return modelMap;
            }

            String openId = redisUtil.get(sessionKey).toString();

            List<Address> addressList = addressMapper.selectList(new EntityWrapper<Address>()
                    .eq("openId", openId));

            modelMap.put("success", true);
            modelMap.put("data", addressList);
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "(微信)根据用户openId查询用户的地址出现异常");
            log.error("(微信)根据用户openId查询用户的地址出现异常:" + e.getMessage());
            return modelMap;
        }

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

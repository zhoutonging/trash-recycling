package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
import com.mengzhou.trashrecycling.model.User;
import com.mengzhou.trashrecycling.mapper.UserMapper;
import com.mengzhou.trashrecycling.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 *
 * @author CC
 * @since 2019-10-17
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, Object> saveWechar(User user, String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (sessionKey == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "用户sessionKey为空");
                log.error("(微信)添加用户信息时出现错误:用户sessionKey为空");
                return modelMap;
            }

            if (user.getNickName() == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "用户基本信息为空");
                log.error("(微信)添加用户信息时出现错误:用户sessionKey为空");
                return modelMap;
            }

            //判断sessionKey是否过期
            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "缓存中没有查到对应的sessionKey");
                log.error("(微信)添加用户信息时出现错误:缓存中没有查到对应的sessionKey");
                return modelMap;
            }

            //从缓存中拿到openId
            String openId = (String) redisUtil.get(sessionKey);

            //查询用户是否存在
            User user1 = userMapper.findByOpenId(openId);
            if (user1 != null) {
                modelMap.put("success", false);
                modelMap.put("msg", "用户已经存在");
                return modelMap;
            }

            user.setOpenId(openId);//openId
            user.setIntegral(0);//初始化积分
            user.setCreateTime(new Date());

            userMapper.insert(user);

            modelMap.put("success", true);
            modelMap.put("msg", "添加用户成功");
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "(微信)添加用户时出现异常");
            log.error("(微信)添加用户时出现异常" + e.getMessage());
            return modelMap;
        }
    }

    @Override
    public LayuiResult deleteById(Integer id) {
        if (id == null) {
            return LayuiResult.fail("删除失败,id为空");
        }
        userMapper.deleteById(id);

        return LayuiResult.success("删除成功");
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public User findByOpenId(String openId) {
        if (openId == null) {
            return null;
        }

        return userMapper.findByOpenId(openId);
    }

    @Override
    public Map<String, Object> findByIntegral(String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (sessionKey == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "查询积分失败");
                log.error("(微信)查询用户积分失败,sessionKey为空");
                return modelMap;
            }

            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "查询积分失败");
                log.error("(微信)查询用户积分失败,sessionKey已过期");
                return modelMap;
            }

            String openId = redisUtil.get(sessionKey).toString();
            User user = userMapper.findByIntegral(openId);

            modelMap.put("success", true);
            modelMap.put("data", user.getIntegral());
            return modelMap;
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "查询积分异常");
            log.error("(微信)查询用户积分出现异常:" + e.getMessage());
            return modelMap;
        }
    }
}

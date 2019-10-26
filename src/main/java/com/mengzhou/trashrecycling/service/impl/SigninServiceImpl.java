package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.common.redis.RedisUtil;
import com.mengzhou.trashrecycling.mapper.UserMapper;
import com.mengzhou.trashrecycling.model.Signin;
import com.mengzhou.trashrecycling.mapper.SigninMapper;
import com.mengzhou.trashrecycling.model.Signindetails;
import com.mengzhou.trashrecycling.model.User;
import com.mengzhou.trashrecycling.service.SigninService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.service.SignindetailsService;
import com.mengzhou.trashrecycling.service.UserService;
import com.mengzhou.trashrecycling.utils.LayuiResult;
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
 * @since 2019-09-25
 */
@Service
@Slf4j
public class SigninServiceImpl extends ServiceImpl<SigninMapper, Signin> implements SigninService {

    @Autowired
    private SigninMapper signinMapper;

    @Autowired
    private SignindetailsService signindetailsService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    @Override
    public LayuiResult modifyById(Signin signin) {

        try {
            if (signin.getId() == null) {
                log.error("修改签到信息时出错,id不能为空");
                return LayuiResult.fail("更新失败,id为空");
            }
            signin.setUpdateTime(new Date());
            signinMapper.updateById(signin);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改签到信息时出错:" + e.getMessage());
            return LayuiResult.fail("更新失败");
        }
        return LayuiResult.success("更新成功");
    }

    @Override
    public Map<String, Object> findByIdWechat(String sessionKey) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            if (sessionKey == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey为空");
                log.error("(微信)用户签到时出现错误,sessionKey为空");
                return modelMap;
            }

            if (!redisUtil.exists(sessionKey)) {
                modelMap.put("success", false);
                modelMap.put("msg", "sessionKey已过期");
                log.error("(微信)用户签到时出现错误,sessionKey已过期");
                return modelMap;
            }

            String openId = redisUtil.get(sessionKey).toString();

            //查询用户信息
            User user = userMapper.findByOpenId(openId);
            if (user == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "用户不存在~");
                return modelMap;
            }

            //查询用户是否今天已签到
            Signindetails signindetails = signindetailsService.findByOpenId(openId);
            if (signindetails != null) {
                modelMap.put("success", true);
                modelMap.put("msg", "一天只能签一次哦~");
                return modelMap;
            }

            //签到设置只有一条信息SO默认查询第一条
            Signin signin = signinMapper.selectById(1);

            if (signin == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "查询签到信息失败");
                log.error("(微信)用户签到时出现错误,查询签到信息失败");
                return modelMap;
            }

            //添加签到积分
            user.setIntegral(user.getIntegral() + signin.getSigninCount());
            userMapper.updateById(user);

            //记录签到积分
            Signindetails signindetails1 = new Signindetails();
            signindetails1.setOpenId(openId);
            signindetails1.setCreateTime(new Date());
            signindetailsService.insert(signindetails1);

            modelMap.put("success", true);
            modelMap.put("msg", "签到成功");
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "用户签到时出现异常");
            log.error("(微信)用户签到时出现异常" + e.getMessage());
            return modelMap;
        }
        return modelMap;
    }

    @Override
    public LayuiResult findById(Integer id) {
        Signin signin = signinMapper.selectById(id);
        return LayuiResult.success(signin);
    }

    @Override
    public LayuiResult findAll() {
        List<Signin> signinList = signinMapper.selectList(new EntityWrapper<Signin>());
        return LayuiResult.success(signinList);
    }
}

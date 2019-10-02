package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mengzhou.trashrecycling.model.Signin;
import com.mengzhou.trashrecycling.mapper.SigninMapper;
import com.mengzhou.trashrecycling.service.SigninService;
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
    public Map<String, Object> findByIdWechat(Integer id) {
        Map<String, Object> modelMap = new HashMap<>(16);

        if (id == null) {
            log.error("查询签到信息时出错,id不能为空");
            modelMap.put("success", false);
            modelMap.put("data", "查询失败,签到id为空");
            return modelMap;
        }

        Signin signin = signinMapper.selectById(id);
        modelMap.put("success", true);
        modelMap.put("data", signin);
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

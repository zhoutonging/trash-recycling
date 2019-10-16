package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Health;
import com.mengzhou.trashrecycling.mapper.HealthMapper;
import com.mengzhou.trashrecycling.service.HealthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-10-15
 */
@Service
@Slf4j
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements HealthService {

    @Autowired
    private HealthService healthService;

    @Override
    public Map<String, Object> save(Health health) {

        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            health.setCreateTime(new Date());
            healthService.insert(health);

            modelMap.put("success", true);
            modelMap.put("msg", "添加心率血压血氧信息成功");
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "添加心率血压血氧时出现异常");
            log.error("添加心率血压血氧时出现异常:"+e.getMessage());
            return modelMap;
        }
    }
}

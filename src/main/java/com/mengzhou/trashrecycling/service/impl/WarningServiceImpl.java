package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Warning;
import com.mengzhou.trashrecycling.mapper.WarningMapper;
import com.mengzhou.trashrecycling.service.WarningService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-10-15
 */
@Service
@Slf4j
public class WarningServiceImpl extends ServiceImpl<WarningMapper, Warning> implements WarningService {

    @Autowired
    private WarningService warningService;

    @Override
    public Map<String, Object> save(Warning warning) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            warning.setCreateTime(new Date());
            warningService.insert(warning);

            modelMap.put("success", true);
            modelMap.put("msg", "添加预警信息成功");
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "添加预警信息时出现异常");
            log.error("添加预警信息时出现异常:"+e.getMessage());
            return modelMap;
        }

    }
}

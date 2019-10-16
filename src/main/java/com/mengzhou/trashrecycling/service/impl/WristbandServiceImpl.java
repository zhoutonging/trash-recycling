package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Wristband;
import com.mengzhou.trashrecycling.mapper.WristbandMapper;
import com.mengzhou.trashrecycling.service.WristbandService;
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
public class WristbandServiceImpl extends ServiceImpl<WristbandMapper, Wristband> implements WristbandService {

    @Autowired
    private WristbandMapper wristbandMapper;

    @Override
    public Map<String, Object> save(Wristband wristband) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            wristband.setCreateTime(new Date());
            wristbandMapper.insert(wristband);

            modelMap.put("success", true);
            modelMap.put("msg", "添加定位数据成功");
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "添加定位信息时出现异常");
            log.error("添加定位信息时出现异常：" + e.getMessage());
            return modelMap;
        }
    }
}

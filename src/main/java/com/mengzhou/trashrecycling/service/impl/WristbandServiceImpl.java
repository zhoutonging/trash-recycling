package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Wristband;
import com.mengzhou.trashrecycling.mapper.WristbandMapper;
import com.mengzhou.trashrecycling.service.WristbandService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class WristbandServiceImpl extends ServiceImpl<WristbandMapper, Wristband> implements WristbandService {

    @Autowired
    private WristbandMapper wristbandMapper;

    @Override
    public Map<String, Object> save(Wristband wristband) {
        Map<String, Object> modelMap = new HashMap<>();

        wristband.setCreateTime(new Date());
        wristbandMapper.insert(wristband);
        modelMap.put("success", true);
        modelMap.put("msg", "添加数据成功");

        return modelMap;
    }
}

package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Beatheart;
import com.mengzhou.trashrecycling.mapper.BeatheartMapper;
import com.mengzhou.trashrecycling.service.BeatheartService;
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
public class BeatheartServiceImpl extends ServiceImpl<BeatheartMapper, Beatheart> implements BeatheartService {

    @Autowired
    private BeatheartMapper beatheartMapper;

    @Override
    public Map<String, Object> save(Beatheart beatheart) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            beatheart.setCreateTime(new Date());
            beatheartMapper.insert(beatheart);

            modelMap.put("success", true);
            modelMap.put("msg", "添加添加手环电量/计步信息成功");
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "添加手环电量/计步信息时出现异常");
            log.error("添加手环电量/计步信息时出现异常:" + e.getMessage());
            return modelMap;
        }
    }
}

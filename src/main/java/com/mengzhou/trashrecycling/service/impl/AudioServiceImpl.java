package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Audio;
import com.mengzhou.trashrecycling.mapper.AudioMapper;
import com.mengzhou.trashrecycling.service.AudioService;
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
public class AudioServiceImpl extends ServiceImpl<AudioMapper, Audio> implements AudioService {

    @Autowired
    private AudioService audioService;

    @Override
    public Map<String, Object> save(Audio audio) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            audio.setCreateTime(new Date());
            audioService.insert(audio);

            modelMap.put("success", true);
            modelMap.put("msg", "添加语音信息成功");
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "添加语音信息时出现异常");
            log.error("添加语音信息时出现异常:"+e.getMessage());
            return modelMap;
        }
    }
}

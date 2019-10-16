package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Audio;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * 手环语音服务业务逻辑层
 *
 * @author CC
 * @since 2019-10-15
 */
public interface AudioService extends IService<Audio> {

    /**
     * 添加语音
     *
     * @param audio
     * @return
     */
    Map<String, Object> save(Audio audio);
}

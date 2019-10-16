package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Beatheart;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * 手环电量/计步数据业务逻辑层
 *
 * @author CC
 * @since 2019-10-15
 */
public interface BeatheartService extends IService<Beatheart> {

    /**
     * 添加手环电量/计步
     *
     * @param beatheart
     * @return
     */
    Map<String, Object> save(Beatheart beatheart);
}

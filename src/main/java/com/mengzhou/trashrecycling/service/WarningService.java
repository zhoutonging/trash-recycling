package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Warning;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * 手环预警数据业务逻辑层
 *
 * @author CC
 * @since 2019-10-15
 */
public interface WarningService extends IService<Warning> {

    /**
     * 添加预警数据
     *
     * @param warning
     * @return
     */
    Map<String, Object> save(Warning warning);
}

package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Wristband;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * 定位服务业务逻辑层
 *
 * @author CC
 * @since 2019-10-15
 */
public interface WristbandService extends IService<Wristband> {

    /**
     * 添加定位服务
     *
     * @param wristband
     * @return
     */
    Map<String, Object> save(Wristband wristband);
}

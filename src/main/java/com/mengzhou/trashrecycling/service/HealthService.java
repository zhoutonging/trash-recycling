package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Health;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * 手环心率血压血氧业务逻辑层
 *
 * @author CC
 * @since 2019-10-15
 */
public interface HealthService extends IService<Health> {

    /**
     * 添加心率血压血氧
     *
     * @param health
     * @return
     */
    Map<String, Object> save(Health health);
}

package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Wristband;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CC
 * @since 2019-10-15
 */
public interface WristbandService extends IService<Wristband> {

    Map<String,Object> save(Wristband wristband);
}

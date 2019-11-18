package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.History;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * 微信用户访问记录业务逻辑层
 *
 * @author CC
 * @since 2019-11-17
 */
public interface HistoryService extends IService<History> {

    /**
     * 添加记录
     *
     * @param history
     */
    void save(History history);

    /**
     * 查询近七天的访问小程序的用户量
     *
     * @return
     */
    Map<String, Object> find7Count();

    /**
     * 查询今天是否已经登录过小程序
     *
     * @param openId
     * @return
     */
    History findByOpenId(String openId);
}

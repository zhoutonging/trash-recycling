package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Signin;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.List;
import java.util.Map;

/**
 * 签到业务逻辑层
 *
 * @author CC
 * @since 2019-09-25
 */
public interface SigninService extends IService<Signin> {

    /**
     * 修改签到信息
     *
     * @param signin
     * @return
     */
    LayuiResult modifyById(Signin signin);

    /**
     * 用户签到(微信)
     *
     * @param sessionKey
     * @return
     */
    Map<String, Object> findByIdWechat(String sessionKey);

    /**
     * 查询签到信息
     *
     * @param id
     * @return
     */
    LayuiResult findById(Integer id);

    /**
     * 查询签到信息
     *
     * @return
     */
    LayuiResult findAll();
}

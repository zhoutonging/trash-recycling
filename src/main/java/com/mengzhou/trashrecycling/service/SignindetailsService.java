package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.Signindetails;
import com.baomidou.mybatisplus.service.IService;

/**
 * 用户签到记录表业务逻辑层
 *
 * @author CC
 * @since 2019-10-26
 */
public interface SignindetailsService extends IService<Signindetails> {

    /**
     * 查询用户当天是否已经签到
     *
     * @param openId
     * @return
     */
    Signindetails findByOpenId(String openId);
}

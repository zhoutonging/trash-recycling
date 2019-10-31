package com.mengzhou.trashrecycling.mapper;

import com.mengzhou.trashrecycling.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author CC
 * @since 2019-10-17
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户是否存在
     *
     * @param openId
     * @return
     */
    User findByOpenId(String openId);

    /**
     * 查询用户积分
     *
     * @param openId
     * @return
     */
    User findByIntegral(String openId);
}

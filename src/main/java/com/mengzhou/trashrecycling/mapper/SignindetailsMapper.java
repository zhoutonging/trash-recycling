package com.mengzhou.trashrecycling.mapper;

import com.mengzhou.trashrecycling.model.Signindetails;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author CC
 * @since 2019-10-26
 */
public interface SignindetailsMapper extends BaseMapper<Signindetails> {

    /**
     * 查询用户当天是否已经签到
     *
     * @param openId
     * @return
     */
    Signindetails findByOpenId(@Param("openId") String openId);
}

package com.mengzhou.trashrecycling.mapper;

import com.mengzhou.trashrecycling.model.Adminuser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户数据访问层
 *
 * @author CC
 * @since 2019-11-05
 */
public interface AdminUserMapper extends BaseMapper<Adminuser> {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    Adminuser findByUserName(@Param("userName") String userName);
}

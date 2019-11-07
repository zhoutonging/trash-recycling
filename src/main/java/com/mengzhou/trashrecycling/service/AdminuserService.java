package com.mengzhou.trashrecycling.service;

import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.model.Adminuser;

/**
 * 系统用户业务逻辑层
 *
 * @author CC
 * @since 2019-11-05
 */
public interface AdminuserService extends IService<Adminuser> {

    /**
     * 添加用户
     *
     * @param adminuser
     */
    void save(Adminuser adminuser);

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    Adminuser findByUserName(String userName);
}

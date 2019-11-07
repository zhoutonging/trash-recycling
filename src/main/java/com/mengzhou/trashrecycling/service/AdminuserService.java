package com.mengzhou.trashrecycling.service;

import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.model.Adminuser;
import com.mengzhou.trashrecycling.utils.LayuiResult;

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

    /**
     * 查询所有系统用户OR模糊查询
     *
     * @param page
     * @param limit
     * @param userName
     * @return
     */
    LayuiResult findAll(Integer page, Integer limit, String userName);

    /**
     * 根据ID查询系统用户
     *
     * @param id
     * @return
     */
    LayuiResult findById(Integer id);

    /**
     * 根据id修改用户信息
     *
     * @param adminuser
     * @return
     */
    LayuiResult modifyById(Adminuser adminuser);
}

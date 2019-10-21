package com.mengzhou.trashrecycling.service;

import com.mengzhou.trashrecycling.model.User;
import com.baomidou.mybatisplus.service.IService;
import com.mengzhou.trashrecycling.utils.LayuiResult;

import java.util.List;
import java.util.Map;

/**
 * 用户管理业务逻辑层
 *
 * @author CC
 * @since 2019-10-17
 */
public interface UserService extends IService<User> {

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Map<String, Object> saveWechar(User user, String session);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    LayuiResult deleteById(Integer id);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAll();
}

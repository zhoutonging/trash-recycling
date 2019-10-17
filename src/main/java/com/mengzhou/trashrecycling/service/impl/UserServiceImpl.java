package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.User;
import com.mengzhou.trashrecycling.mapper.UserMapper;
import com.mengzhou.trashrecycling.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-10-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

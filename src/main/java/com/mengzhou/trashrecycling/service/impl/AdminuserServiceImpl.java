package com.mengzhou.trashrecycling.service.impl;

import com.mengzhou.trashrecycling.model.Adminuser;
import com.mengzhou.trashrecycling.mapper.AdminUserMapper;
import com.mengzhou.trashrecycling.service.AdminuserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CC
 * @since 2019-11-05
 */
@Service
public class AdminuserServiceImpl extends ServiceImpl<AdminUserMapper, Adminuser> implements AdminuserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public void save(Adminuser adminuser) {
        adminUserMapper.insert(adminuser);
    }

    @Override
    public Adminuser findByUserName(String userName) {
        return adminUserMapper.findByUserName(userName);
    }
}

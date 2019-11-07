package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.model.Adminuser;
import com.mengzhou.trashrecycling.mapper.AdminUserMapper;
import com.mengzhou.trashrecycling.service.AdminuserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public LayuiResult findAll(Integer page, Integer limit, String userName) {
        PageHelper.startPage(page, limit);

        List<Adminuser> adminuserList;

        if (userName == null) {
            adminuserList = adminUserMapper.selectList(new EntityWrapper<Adminuser>());
        } else {
            adminuserList = adminUserMapper.selectList(new EntityWrapper<Adminuser>().like("userName", userName));
        }

        if (adminuserList == null) {
            return LayuiResult.success(null);
        }

        PageInfo pageInfo = new PageInfo(adminuserList);
        return LayuiResult.success(pageInfo.getTotal(), adminuserList);
    }

    @Override
    public LayuiResult findById(Integer id) {
        try {
            if (id == null) {
                return LayuiResult.fail("id为空");
            }

            Adminuser adminuser = adminUserMapper.selectById(id);

            return LayuiResult.success(adminuser);
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("查询失败");
        }
    }

    @Override
    public LayuiResult modifyById(Adminuser adminuser) {
        try {
            if (adminuser.getId() == null) {
                return LayuiResult.fail("id为空");
            }

            adminUserMapper.updateById(adminuser);

            return LayuiResult.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("更新失败");
        }
    }
}

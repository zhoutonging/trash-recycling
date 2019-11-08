package com.mengzhou.trashrecycling.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mengzhou.trashrecycling.common.websocket.WebSocketServlet;
import com.mengzhou.trashrecycling.model.Adminuser;
import com.mengzhou.trashrecycling.mapper.AdminUserMapper;
import com.mengzhou.trashrecycling.service.AdminuserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mengzhou.trashrecycling.utils.LayuiResult;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private WebSocketServlet webSocketServlet;

    @Override
    public LayuiResult save(Adminuser adminuser) {

        try {
            Adminuser user1 = adminUserMapper.findByUserName(adminuser.getUserName());

            if (user1 != null) {
                return LayuiResult.fail("用户已存在");
            }

            //加密盐值
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            //运算次数
            int count = 2;
            //加密方式
            String algorithmName = "md5";
            //最终加密结果
            String password = new SimpleHash(algorithmName, adminuser.getPassword(), salt, count).toString();

            adminuser.setPassword(password);
            adminuser.setSalt(salt);
            adminuser.setCreateTime(new Date());
            adminUserMapper.insert(adminuser);
            webSocketServlet.onMessage(1);//TODO 测试webSocket

            return LayuiResult.success("添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("添加失败");
        }

    }

    @Override
    public LayuiResult deleteById(Integer id) {
        try {
            if (id == null) {
                return LayuiResult.fail("id为空");
            }

            adminUserMapper.deleteById(id);

            return LayuiResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("删除失败");
        }
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
    public LayuiResult modifyById(Adminuser adminuser, String beforePsd) {
        try {
            if (adminuser.getId() == null) {
                return LayuiResult.fail("id为空");
            }

            //运算次数
            int count = 2;
            //加密方式
            String algorithmName = "md5";

            Adminuser adminuser1 = adminUserMapper.selectById(adminuser.getId());
            String password1 = new SimpleHash(algorithmName, beforePsd, adminuser1.getSalt(), count).toString();
            if (!password1.equals(adminuser1.getPassword())) {
                return LayuiResult.fail("原密码不正确");
            }


            //最终加密结果
            String password = new SimpleHash(algorithmName, adminuser.getPassword(), adminuser1.getSalt(), count).toString();
            adminuser.setPassword(password);
            adminUserMapper.updateById(adminuser);

            return LayuiResult.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("更新失败");
        }
    }
}

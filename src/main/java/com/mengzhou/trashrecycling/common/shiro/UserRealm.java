package com.mengzhou.trashrecycling.common.shiro;

import com.mengzhou.trashrecycling.model.Adminuser;
import com.mengzhou.trashrecycling.service.AdminuserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 自定义Realm
 *
 * @author Lmr
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminuserService adminuserService;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        // 编写Shiro判断逻辑,判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;

        // 数据库的用户名和密码
        Adminuser adminuser = adminuserService.findByUserName(token.getUsername());

        if (adminuser == null) {
            // 用户名不存在
            return null;// Shiro底层会抛出UnknownAccountException
        }

//		2.判断密码
        return new SimpleAuthenticationInfo(adminuser, adminuser.getPassword(), this.getClass().getName());
    }

}

package com.mengzhou.trashrecycling.common.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加Shiro内置过滤器

        /**
         * Shiro内置过滤器,可以实现权限的拦截 常用的过滤器:
         * 		anon:无需认证(登录)可以访问
         *      anthc:必须认证才可以访问
         * 		user:如果使用remember的功能可以访问
         *		perms:该资源必须得到资源权限才可以访问
         *		role:该资源必须得到角色权限才可以访问
         */

        Map<String, String> filterMap = new LinkedHashMap<>();

        // 放行以下页面
        filterMap.put("/login", "anon");
        filterMap.put("/api/**", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/index", "authc");

        //退出登录
        filterMap.put("/logout", "logout");

        //修改未登录就访问的默认登录页
        shiroFilterFactoryBean.setLoginUrl("/login");

        filterMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {

        return new UserRealm();
    }

}

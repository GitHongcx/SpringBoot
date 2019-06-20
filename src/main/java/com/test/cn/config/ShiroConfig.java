package com.test.cn.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.test.cn.shiro.MyShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private static final Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        log.info("ShiroConfiguration.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器
        Map<String,String> filterChainDefnitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的连接 顺序判断
        filterChainDefnitionMap.put("/static/**","anon");
        filterChainDefnitionMap.put("/userInfo/loginUser", "anon");
        // 配置退出过滤器，其中的具体的退出代码Shiro已经替我们现实了
        filterChainDefnitionMap.put("/userInfo/logout","anon");
        // 过滤链定义，从上往下顺序执行，一般将/**放在最后 ->这是一个坑，一不小心代码就不好使了
        // authc：所有url都必须认证通过才可以访问；anon：所有url都可以匿名访问
        filterChainDefnitionMap.put("/**","authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.html"页面
        shiroFilterFactoryBean.setLoginUrl("/userInfo/login");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/userInfo/index");

        // 未授权页面:
        shiroFilterFactoryBean.setUnauthorizedUrl("/userInfo/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefnitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 配置shiro标签
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}

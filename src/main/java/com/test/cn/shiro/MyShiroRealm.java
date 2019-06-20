package com.test.cn.shiro;

import com.test.cn.pojo.jpa.SpringBootPermission;
import com.test.cn.pojo.jpa.SpringBootRole;
import com.test.cn.pojo.jpa.SpringBootUserInfo;
import com.test.cn.service.jpa.SpringBootUserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private SpringBootUserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("权限配置--》MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SpringBootUserInfo userInfo = (SpringBootUserInfo)principals.getPrimaryPrincipal();
        for(SpringBootRole role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SpringBootPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("MyShiroRealm.doGetAuthenticationInfo()");
        // 获取用户输入的账号
        String username = (String)token.getPrincipal();
        log.info("token.getCredentials() = "+token.getCredentials());
        // 通过username从数据库中查找User对象
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内部会重复执行该方法
        SpringBootUserInfo userInfo = userInfoService.findByUsername(username);
        log.info("——》userInfo="+userInfo);
        if(userInfo == null){
            throw new UnknownAccountException("用户名不存在");
        }
        return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(),this.getClass().getName());
    }
}

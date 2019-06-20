package com.test.cn.service.jpa;

import com.test.cn.pojo.jpa.SpringBootUserInfo;
import org.springframework.data.domain.Page;

public interface SpringBootUserInfoService {

    public Page<SpringBootUserInfo> findSpringBootUserInfo(Integer pageIndex, Integer pageSize);

    public SpringBootUserInfo selectByPrimaryKey(Long id);

    public SpringBootUserInfo saveOrUpdate(SpringBootUserInfo userInfo);

    public void deleteByPrimaryKey(Long id);

    public SpringBootUserInfo findByUsername(String username);

    public SpringBootUserInfo findUserInfo(SpringBootUserInfo userInfo);

    /**
     * 动态sql实现-hql
     * @param userInfo
     * @return
     */
    public SpringBootUserInfo selectUserInfo(SpringBootUserInfo userInfo);

    /**
     * 动态sql实现-sql
     * @param userInfo
     * @return
     */
    public SpringBootUserInfo selectUserInfoSql(SpringBootUserInfo userInfo);
}
package com.test.cn.dao.mybatis;

import com.github.pagehelper.Page;
import com.test.cn.pojo.mybatis.SpringBootUserInfo;

public interface SpringBootUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpringBootUserInfo record);

    int insertSelective(SpringBootUserInfo record);

    SpringBootUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpringBootUserInfo record);

    int updateByPrimaryKey(SpringBootUserInfo record);

    Page<com.test.cn.pojo.jpa.SpringBootUserInfo> findSpringBootUserInfo();
}
package com.test.cn.service.mybatis.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.test.cn.dao.mybatis.SpringBootUserInfoMapper;
import com.test.cn.pojo.jpa.SpringBootUserInfo;
import com.test.cn.service.mybatis.SpringBootUserInfoMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringBootUserInfoMybatisServiceImpl implements SpringBootUserInfoMybatisService {

    @Autowired
    SpringBootUserInfoMapper userInfoMapper;

    @Override
    public Page<SpringBootUserInfo> findSpringBootUserInfo(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page<SpringBootUserInfo> userList = userInfoMapper.findSpringBootUserInfo();
        return userList;
    }
}

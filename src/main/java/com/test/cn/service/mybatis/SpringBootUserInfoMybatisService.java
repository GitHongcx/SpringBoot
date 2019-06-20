package com.test.cn.service.mybatis;

import com.github.pagehelper.Page;
import com.test.cn.pojo.jpa.SpringBootUserInfo;

public interface SpringBootUserInfoMybatisService {

    public Page<SpringBootUserInfo> findSpringBootUserInfo(Integer pageIndex, Integer pageSize);
}
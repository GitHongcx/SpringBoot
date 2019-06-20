package com.test.cn.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.test.cn.dubbo.api.SpringbootDubboService;

@Service
public class SpringbootDubboServiceImpl implements SpringbootDubboService {
    @Override
    public String springbootDubboTest() {
        return "springboot-dubbo-test";
    }
}

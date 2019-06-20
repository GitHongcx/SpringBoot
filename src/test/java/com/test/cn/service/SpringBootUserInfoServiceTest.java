package com.test.cn.service;

import com.test.cn.pojo.jpa.SpringBootUserInfo;
import com.test.cn.service.jpa.SpringBootUserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootUserInfoServiceTest {

    @Autowired
    private SpringBootUserInfoService infoService;
    @Test
    public void findUserInfo() throws Exception{
        SpringBootUserInfo userInfo = new SpringBootUserInfo();
        userInfo.setUsername("star1");
        SpringBootUserInfo info = infoService.findUserInfo(userInfo);
        System.out.println("--------------------->:"+info.getUsername()+";"+info.getEmail());
    }

    @Test
    public void selectUserInfo() throws Exception{
        SpringBootUserInfo userInfo = new SpringBootUserInfo();
        userInfo.setUsername("star1");
        SpringBootUserInfo info = infoService.selectUserInfo(userInfo);
        System.out.println("--------------------->:"+info.getUsername()+";"+info.getEmail());

        SpringBootUserInfo userInfo2 = new SpringBootUserInfo();
        userInfo2.setMobile("18734332323");
        SpringBootUserInfo info2 = infoService.selectUserInfo(userInfo2);
        System.out.println("--------------------->:"+info2.getUsername()+";"+info2.getEmail());
    }
}
package com.test.cn.dao.jpa;

import com.test.cn.pojo.jpa.SpringBootUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoDao extends JpaRepository<SpringBootUserInfo,Long> {

    public SpringBootUserInfo findByUsername(String userName);

    /**
     * nativeQuery = true 表示使用原生sql执行
     * 使用spel表达式匹配参数
     * @param userInfo
     * @return
     */
    @Query(value = "select * from springboot_user_info where 1=1  user_name = :#{#userInfo.username}",nativeQuery = true)
    public SpringBootUserInfo selectUserInfo(@Param("userInfo") SpringBootUserInfo userInfo);


}

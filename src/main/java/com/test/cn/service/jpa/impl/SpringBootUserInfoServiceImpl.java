package com.test.cn.service.jpa.impl;

import com.test.cn.dao.jpa.UserInfoDao;
import com.test.cn.pojo.jpa.SpringBootUserInfo;
import com.test.cn.service.jpa.SpringBootUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.applet.Main;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpringBootUserInfoServiceImpl implements SpringBootUserInfoService {

    @Autowired
    private UserInfoDao userDao;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 完全查询创建工厂
     */
    private CriteriaBuilder cb = null;

    /**
     * 安全查询主语句
     */
    private CriteriaQuery<SpringBootUserInfo> criteriaQuery = null;

    private Root<SpringBootUserInfo> register = null;

    public SpringBootUserInfoServiceImpl(EntityManager entityManager){
        this.entityManager = entityManager;
        this.cb = this.entityManager.getCriteriaBuilder();
        this.criteriaQuery = this.cb.createQuery(SpringBootUserInfo.class);
        this.register = this.criteriaQuery.from(SpringBootUserInfo.class);
    }

    @Override
    public Page<SpringBootUserInfo> findSpringBootUserInfo(Integer pageIndex, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC,"id"); // 设置根据ID倒序排序
        Pageable pageable = PageRequest.of(pageIndex,pageSize,sort);
        return userDao.findAll(pageable);
    }

    @Override
    public SpringBootUserInfo selectByPrimaryKey(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public SpringBootUserInfo saveOrUpdate(SpringBootUserInfo userInfo) {
        return userDao.save(userInfo);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public SpringBootUserInfo findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public SpringBootUserInfo findUserInfo(SpringBootUserInfo userInfo) {
        return userDao.selectUserInfo(userInfo);
    }

    @Override
    public SpringBootUserInfo selectUserInfo(SpringBootUserInfo userInfo) {
        this.criteriaQuery.where(this.getPredicates(userInfo));
        TypedQuery<SpringBootUserInfo> typedQuery = this.entityManager.createQuery(this.criteriaQuery);
        SpringBootUserInfo info = typedQuery.getSingleResult();
        return info;
    }

    /**
     * 设置过滤条件
     * @param userInfo
     * @return
     */
    private Predicate[] getPredicates(SpringBootUserInfo userInfo){
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(!StringUtils.isEmpty(userInfo.getUsername())){
            predicates.add(this.cb.equal(this.register.<String>get("username"), userInfo.getUsername()));
        }
        if(!StringUtils.isEmpty(userInfo.getMobile())){
            predicates.add(this.cb.equal(this.register.<String>get("mobile"), userInfo.getMobile()));
        }
        return predicates.toArray(new Predicate[0]);
    }

    @Override
    public SpringBootUserInfo selectUserInfoSql(SpringBootUserInfo userInfo) {
        StringBuilder dataSql = new StringBuilder("select * from springboot_user_info ");
        StringBuilder whereSql = new StringBuilder("where 1=1");
        if(!StringUtils.isEmpty(userInfo.getUsername())){
            whereSql.append(" and user_name= :username");
        }
        if(!StringUtils.isEmpty(userInfo.getMobile())){
            whereSql.append(" and mobile= :mobile");

        }
        // 组装sql语句
        dataSql.append(whereSql);
        // 创建本地sql查询实例
        Query dataQuery = entityManager.createNativeQuery(dataSql.toString(), SpringBootUserInfo.class);
        // 设置参数
        if(!StringUtils.isEmpty(userInfo.getUsername())){
            dataQuery.setParameter("username",userInfo.getUsername());
        }
        if(!StringUtils.isEmpty(userInfo.getMobile())){
            dataQuery.setParameter("mobile",userInfo.getMobile());
        }
        return (SpringBootUserInfo)dataQuery.getSingleResult();
    }
}


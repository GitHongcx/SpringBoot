package com.test.cn.pojo.mybatis;

public class SpringBootUserInfo {
    private Long id;

    private String userName;

    private String sex;

    private Integer age;

    private String email;

    private String mobile;

    private String password;

    private String salt;

    private String state;

    public SpringBootUserInfo(Long id, String userName, String sex, Integer age, String email, String mobile, String password, String salt, String state) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.salt = salt;
        this.state = state;
    }

    public SpringBootUserInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
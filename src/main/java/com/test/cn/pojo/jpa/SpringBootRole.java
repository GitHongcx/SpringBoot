package com.test.cn.pojo.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "springboot_role")
public class SpringBootRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    private String role;
    @Column(name = "description")
    private String description;
    @Column(name = "available")
    private Boolean available;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SpringbootRolePermission",joinColumns = {@JoinColumn(name="roleId")},inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SpringBootPermission> permissions;

    @ManyToMany
    @JoinTable(name = "SpringbootUserRole",joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<SpringBootUserInfo> userInfos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SpringBootPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SpringBootPermission> permissions) {
        this.permissions = permissions;
    }

    public List<SpringBootUserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<SpringBootUserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}

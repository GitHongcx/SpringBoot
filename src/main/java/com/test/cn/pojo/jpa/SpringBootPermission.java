package com.test.cn.pojo.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "springboot_permission")
public class SpringBootPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "resourceType",columnDefinition = "menu('menu','button')")
    private String resourceType;  //资源类型，[menu|button]
    @Column(name = "url")
    private String url;
    @Column(name = "permission")
    private String permission;
    @Column(name = "parentId")
    private Long parentId;
    @Column(name = "parentIds")
    private String parentIds;
    @Column(name = "available")
    private Boolean available;
    @ManyToMany
    @JoinTable(name = "SpringbootRolePermission", joinColumns = {@JoinColumn(name = "permissionId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SpringBootRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SpringBootRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SpringBootRole> roles) {
        this.roles = roles;
    }
}

package com.wtu.forum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName RoleAuthority
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/6 21:46
 * @Version 1.0
 **/
@Entity
@Table(name = "role_authority")
public class RoleAuthority implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer roleId;
    private Integer authorityId;
    private Date createTime;//创建时间
    private Short status;//状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}

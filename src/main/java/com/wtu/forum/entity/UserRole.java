package com.wtu.forum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName UserRole
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/6 21:43
 * @Version 1.0
 **/
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer roleId;

    private Date createTime;//创建时间
    private Short status;//状态

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}

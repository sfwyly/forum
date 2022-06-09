package com.wtu.forum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Role
 * @Description 权限表
 * @Author 逝风无言
 * @Data 2019/10/6 21:08
 * @Version 1.0
 **/
@Entity
@Table(name = "role")
public class Role implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;//主键

    private Short status;//状态

    private Date createTime;//创建时间

    private String description;//描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

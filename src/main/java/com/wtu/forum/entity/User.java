package com.wtu.forum.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;//用户id 暂时只用int
    @NotBlank(message = "用户名不能为空")
    private String username;//用户名
    @NotBlank(message = "密码不能为空")
    private String password;//密码

    private Date createTime;//创建时间
    private Short status;//状态

    User(){

    }
    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


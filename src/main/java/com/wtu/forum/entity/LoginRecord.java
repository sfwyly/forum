package com.wtu.forum.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName LoginRecord
 * @Description 登录历史
 * @Author 逝风无言
 * @Data 2019/10/17 21:26
 * @Version 1.0
 **/
@Table(name = "loginHistory")
public class LoginRecord {

    @Id
    @GeneratedValue
    private Long id;//主键
    private String username;//用户名
//    private String password;//密码
    private Short status;//状态
    private Date createTime;//记录创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

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
}

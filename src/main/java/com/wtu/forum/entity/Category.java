package com.wtu.forum.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName Category
 * @Description 文章分类表(TODO 是否需要可用户自定义，是否需要加userId)
 * @Author 逝风无言
 * @Data 2019/10/17 21:18
 * @Version 1.0
 **/
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private Integer id;//主键

//    private Integer userId;//用户id

    private String name;//名称

    private Date createTime;//创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

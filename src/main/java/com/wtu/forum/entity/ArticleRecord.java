package com.wtu.forum.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName ArticleRecord
 * @Description 文章操作记录表
 * @Author 逝风无言
 * @Data 2019/10/17 21:34
 * @Version 1.0
 **/
@Table(name = "articleRecord")
public class ArticleRecord {
    @Id
    private Integer articleId;//文章id
    @Id
    private Integer userId;//用户id
    @Id
    private Short state;//0浏览 1点赞 2收藏

    private Date createTime;//创建时间

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

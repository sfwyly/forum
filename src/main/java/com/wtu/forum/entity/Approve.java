package com.wtu.forum.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 点赞实体类
 */
@Table(name = "approve")
public class Approve {
    @Id
    private Integer userId;//用户id
    @Id
    private Integer articleId;//文章id
    @Id
    private Integer messageId;//留言id

    private Date createTime;//创建时间

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

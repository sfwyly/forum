package com.wtu.forum.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 留言
 */
@Table(name = "message")
public class Message implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;//用户id 与用户表(多对一)
    private Integer articleId;//文章id 多对一
    private String messageContent;//留言内容
    private Date messageTime;//留言时间
    private Integer approveNumber;//点赞数
    private Integer replyNumber;//回复数

    private Short privacy;//是否为隐私

    private Integer replyFlag;
    private Integer approveFlag;//当前登录用户是否在该留言下点赞

    private User user;//用户

    public Integer getApproveNumber() {
        return approveNumber;
    }

    public void setApproveNumber(Integer approveNumber) {
        this.approveNumber = approveNumber;
    }

    public Integer getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
    }

    public Integer getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(Integer replyFlag) {
        this.replyFlag = replyFlag;
    }

    public Integer getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(Integer approveFlag) {
        this.approveFlag = approveFlag;
    }

    public Short getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Short privacy) {
        this.privacy = privacy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }


}

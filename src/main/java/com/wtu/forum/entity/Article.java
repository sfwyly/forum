package com.wtu.forum.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Article
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/17 21:03
 * @Version 1.0
 **/
@Table(name = "article")
@NamedStoredProcedureQuery(name = "execute_record",procedureName = "execute_record",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN,name="articleId",type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN,name="userId",type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN,name="operateFlag",type=Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN,name = "state",type=Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT,name = "result",type=Integer.class),
        @StoredProcedureParameter(mode=ParameterMode.OUT,name = "flag",type =Integer.class)
})
public class Article  implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;//主键
    private Integer userId;//用户id
    private Integer categoryId;//分类id
    private String title;//文章标题（直接从内容进行抽取）
    private String content;//文章内容
    private Date createTime;//创建时间
    private Short status;//文章可看状态

    private Integer viewNumber;//浏览次数
    private Integer approveNumber;//点赞次数
    private Integer collectionNumber;//收藏次数

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getApproveNumber() {
        return approveNumber;
    }

    public void setApproveNumber(Integer approveNumber) {
        this.approveNumber = approveNumber;
    }

    public Integer getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

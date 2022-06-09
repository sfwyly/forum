package com.wtu.forum.dto;

import com.wtu.forum.enums.CommonError;
import com.wtu.forum.enums.EmUserError;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName RequestResult
 * @Description 公共返回类，用于向前台显示结果
 * @Author 逝风无言
 * @Data 2019/10/6 20:59
 * @Version 1.0
 **/
public class RequestResult<T> implements Serializable{
    private T object;//返回对象

    private boolean success;//是否成功

    private int statusCode;//返回状态码

    private String comment;//说明
    //private CommonError commonError;//统一存储状态与错误码,直接显示有个问题，前台没有提示信息

    public RequestResult(T object, boolean success, CommonError commonError) {
        this.object = object;
        this.success = success;
        this.statusCode = commonError.getErrCode();
        this.comment = commonError.getErrMsg();
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

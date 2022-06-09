package com.wtu.forum.dto;

import org.springframework.stereotype.Component;

/**
 * @ClassName CommonReturnType
 * @Description 用户内部进行公共包装返回
 * @Author 逝风无言
 * @Data 2019/10/6 21:02
 * @Version 1.0
 **/
public class CommonReturnType {

    private boolean success;//是否成功

    private String comment;//说明


    public CommonReturnType(boolean success,String comment){
        this.success = success;
        this.comment = comment;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

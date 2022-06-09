package com.wtu.forum.enums;

/**
 * @ClassName EmRequestError
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/12 21:14
 * @Version 1.0
 **/
public enum EmRequestError implements CommonError {
    USER_REQUEST_ERROR(30000,"用户请求错误"),
    NOT_CURRENT_USER_ERROR(30020,"非当前用户或当前文章id不存在"),
    ARTICLE_SAVE_SUCCESS(30021,"文章保存成功"),
    ARTICLE_SAVE_FAILURE(30022,"文章保存失败"),
    ARTICLE_CONTENT_INVAILDATION(30023,"文章内容无效"),
    ARTICLE_REQUEST_SUCCESS(30024,"文章内容请求成功"),
    MESSAGE_INFORMATION_INVALIDATION(40001,"留言内容不合法"),
    MESSAGE_NOT_ARTICLE(40002,"没有合适的留言文章"),
    MESSAGE_INFORMATION_STORAGE_FAILTURE(40003,"信息存储失败"),
    MESSAGE_STORAGE_SUCCESS(40004,"留言存储成功"),
    MESSAGE_NOT_EXISTS(40006,"留言不存在"),
    MESSAGE_DELETE_SUCCESS(40009,"留言删除成功");

    EmRequestError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;

    @Override
    public int getErrCode() {
        return 0;
    }

    @Override
    public String getErrMsg() {
        return null;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return null;
    }
}

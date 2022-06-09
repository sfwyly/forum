package com.wtu.forum.enums;

/**
 * @ClassName EmUserError
 * @Description 构建用户输入错误类型枚举
 * @Author 逝风无言
 * @Data 2019/10/8 20:58
 * @Version 1.0
 **/
public enum EmUserError implements CommonError {
    USER_INPUT_ERROR(10000,"用户输入错误"),
    USER_INPUT_USERNAME_ERROR(10001,"用户输入用户名错误"),
    USER_INPUT_PASSWORD_ERROR(10002,"用户输入密码错误"),
    USER_INPUT_ID_ERROR(10003,"用户输入ID错误"),
    USER_FREEZE_ERROR(10004,"该账户已冻结"),
    USER_LOGIN_REPEAT(10005,"用户已登录"),
    USER_LOGIN_SUCCESS(10008,"用户登录成功"),
    USER_REGISTER_ERROR(10010,"用户输入错误"),
    USER_REGISTER_USERNAME_ERROR(10011,"用户输入用户名格式错误"),
    USER_REGISTER_PASSWORD_ERROR(10012,"用户输入面格式错误"),
    USER_REGISTER_USERNAME_USED_ERROR(10013,"用户名邮箱已被占用"),
    USER_REGISTER_SUCCESS(10015,"用户注册成功"),
    USER_LOGOUT_SUCCESS(10020,"用户退出登录成功"),
    USER_LOGOUT_REPEAT_SUCCESS(10021,"用户已退出登录");

    private int errCode;
    private String errMsg;

    private EmUserError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}

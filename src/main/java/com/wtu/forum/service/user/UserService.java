package com.wtu.forum.service.user;

import com.wtu.forum.dto.RequestResult;
import com.wtu.forum.entity.Role;
import com.wtu.forum.entity.UserRole;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/6 21:42
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public RequestResult login(String username,String password);

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    public RequestResult register(String username,String password);

    /**
     * 退出登录
     * @return
     */
    public RequestResult logOut();
}

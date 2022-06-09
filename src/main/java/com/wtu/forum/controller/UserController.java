package com.wtu.forum.controller;

import com.wtu.forum.dto.RequestResult;
import com.wtu.forum.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description 每个方法进入之前会对参数进行非法校验，为了确保参数类型错误，统一用String进行接收
 * @Author 逝风无言
 * @Data 2019/10/3 11:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public RequestResult login(String username,String password){
        return userService.login(username,password);
    }

    @PostMapping("/register")
    public RequestResult register(String username,String password){
        return userService.register(username,password);
    }

    @PostMapping("/logout")
    public RequestResult logOut(){
        return userService.logOut();
    }
}

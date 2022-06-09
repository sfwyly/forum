package com.wtu.forum.service.user.impl;

import com.wtu.forum.dao.user.UserRepository;
import com.wtu.forum.dto.RequestResult;
import com.wtu.forum.entity.User;
import com.wtu.forum.enums.EmUserError;
import com.wtu.forum.service.common.safe.SafeCheckpoint;
import com.wtu.forum.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO （必须在controller层进入方法前对参数进行统一校验）
 * @Author 逝风无言
 * @Data 2019/10/6 21:50
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SafeCheckpoint safeCheckpoint;

    @Autowired
    private HttpSession httpSession;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public RequestResult login(String username, String password) {
        //验证用户登陆状况
        //进行缓存表校验
        User user = (User)httpSession.getAttribute("user");
        if(user!=null){
            return new RequestResult(null,false, EmUserError.USER_INPUT_USERNAME_ERROR);
        }

        if(username==null){
            return new RequestResult(null,false, EmUserError.USER_INPUT_USERNAME_ERROR);
        }else if(password==null){
            return new RequestResult(null,false, EmUserError.USER_INPUT_PASSWORD_ERROR);
        }

        //这里进行登录记录表插入，在每个返回值前先插入记录登录成功，失败。
        List<User> userList = userRepository.getByUsername(username);
        if(userList==null||userList.size()<=0){//用户名不存在
            return new RequestResult(null,false, EmUserError.USER_INPUT_USERNAME_ERROR);
        }else if(!userList.get(0).getPassword().equals(DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(password.getBytes()).getBytes()))){//密码错误
            return new RequestResult(null,false, EmUserError.USER_INPUT_PASSWORD_ERROR);
        }else if(userList.get(0).getStatus().intValue()==0){//用户冻结
            return new RequestResult(null,false,EmUserError.USER_FREEZE_ERROR);
        }


        //登录记录
        httpSession.setAttribute("user",user);

        return new RequestResult(null,true, EmUserError.USER_LOGIN_SUCCESS);
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public RequestResult register(String username, String password) {

        //用户名密码格式校验
        if(!safeCheckpoint.usernameCheck(username)){
            return new RequestResult(null,false, EmUserError.USER_REGISTER_USERNAME_ERROR);
        }else if(!safeCheckpoint.passwordCheck(password)){
            return new RequestResult(null,false, EmUserError.USER_REGISTER_PASSWORD_ERROR);
        }
        //查询库中用户名是否注册
        List<User> userList = userRepository.getByUsername(username);
        if(userList!=null &&userList.size()>0){
            return new RequestResult(null,false,EmUserError.USER_REGISTER_USERNAME_USED_ERROR);
        }

        httpSession.setAttribute("registered",new Boolean(true));//设置该session已经注册

        //数据库存储
        User user= userRepository.save(new User(username,password));//这里可能存在的问题，没有解决
        if(user==null){
            return new RequestResult(null,false,EmUserError.USER_REGISTER_SUCCESS);
        }
        return new RequestResult(null,false,EmUserError.USER_REGISTER_SUCCESS);
    }

    @Override
    public RequestResult logOut() {
        User user = (User)httpSession.getAttribute("user");
        if(user==null){
            return new RequestResult(null,true,EmUserError.USER_LOGOUT_REPEAT_SUCCESS);
        }
        httpSession.removeAttribute("user");
        httpSession.invalidate();
        return new RequestResult(null,true,EmUserError.USER_LOGOUT_SUCCESS);
    }
}

package com.wtu.forum.service.common;

import com.wtu.forum.dao.user.UserRepository;
import com.wtu.forum.dto.CommonReturnType;
import com.wtu.forum.entity.Authority;
import com.wtu.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName UserRequestURIAuthority
 * @Description 用户请求url与对应用户对应
 * @Author 逝风无言
 * @Data 2019/10/6 20:56
 * @Version 1.0
 **/
@Service
public class UserRequestURIAuthority {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 匹配用户与uri
     * @param user
     * @param uri
     * @return
     */
    public CommonReturnType userAndURIAdapter(User user , String uri){

        if(user == null){//非登录用户，某些请求权限，怎么处理
            //1.在用户表中设置一个记录专门用于未登录用户进行查询连接进行使用 TODO
            String defaultUsername = "123456789@qq.com";//设置默认用户
            user = userRepository.getByUsername(defaultUsername).get(0);//肯定存在这个用户

//            return new CommonReturnType(false,"用户id为空！");
        }
        List<String> linkList = userRepository.queryUserAccessAuthority(user.getId());//查询所有连接
        String pre = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName();
        uri =uri.replace(pre,"").substring(uri.indexOf("/"));
        System.out.println(uri);
        //String[] servers = {"localhost","127.0.0.1","47.100.24.126"};
        for (int i =0;i<linkList.size();i++){

                if(uri.indexOf(linkList.get(i))==0){//请求验证
                    return new CommonReturnType(true,null);
                }
        }

        return new CommonReturnType(false,null);

    }


}

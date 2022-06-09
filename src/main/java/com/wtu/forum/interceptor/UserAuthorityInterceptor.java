package com.wtu.forum.interceptor;

import com.google.gson.Gson;
import com.wtu.forum.dto.CommonReturnType;
import com.wtu.forum.dto.RequestResult;
import com.wtu.forum.entity.User;
import com.wtu.forum.enums.EmRequestError;
import com.wtu.forum.service.common.UserRequestURIAuthority;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @ClassName UserAuthorityInterceptor
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/6 20:51
 * @Version 1.0
 **/
@Component
public class UserAuthorityInterceptor implements HandlerInterceptor {


    private UserRequestURIAuthority userRequestURIAuthority;

    public UserAuthorityInterceptor(UserRequestURIAuthority userRequestURIAuthority){//通过配置类注入
        this.userRequestURIAuthority = userRequestURIAuthority;
    }
    UserAuthorityInterceptor(){}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user =  (User)request.getSession().getAttribute("user");
        String link = request.getRequestURI().toString();
        //统一交由该方法进行管理，必须进行非登录用户的校验
        CommonReturnType commonReturnType = userRequestURIAuthority.userAndURIAdapter(user,link);
        if(commonReturnType.isSuccess()){//通过
            return true;
        }
        PrintWriter pw = response.getWriter();
        Gson g = new Gson();
        pw.print(g.toJson(new RequestResult(null,false, EmRequestError.USER_REQUEST_ERROR)));//返回错误信息到前端
        pw.flush();
        pw.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}

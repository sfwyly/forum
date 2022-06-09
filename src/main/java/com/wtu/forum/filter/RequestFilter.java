package com.wtu.forum.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName RequestFilter
 * @Description 对非法请求进行拦截
 * @Author 逝风无言
 * @Data 2019/10/8 21:07
 * @Version 1.0
 **/
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

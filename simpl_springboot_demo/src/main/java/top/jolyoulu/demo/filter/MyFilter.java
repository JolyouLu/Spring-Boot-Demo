package top.jolyoulu.demo.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: JolyouLu
 * @Date: 2021/11/1 16:00
 * @Version 1.0
 * 简单的过滤器
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter的doFilter方法");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

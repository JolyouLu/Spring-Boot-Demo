package top.jolyoulu.security.filter;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.jolyoulu.security.entity.LoginUser;
import top.jolyoulu.security.entity.SysUser;
import top.jolyoulu.security.utils.JwtUtils;
import top.jolyoulu.security.utils.RedisUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/17 1:13
 * @Version 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            //放行，后面会有其它过滤器做认证
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        Claims claims = JwtUtils.parseToken(token);
        String userId = claims.get("userId").toString();
        //从redis获取用户信息
        SysUser user = redisUtils.get(userId);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user,null,null
        );//使用3个参数的构造函数，会触发super.setAuthenticated(true);表示已经认证过
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}

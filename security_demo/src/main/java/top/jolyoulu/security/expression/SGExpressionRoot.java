package top.jolyoulu.security.expression;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import top.jolyoulu.security.entity.LoginUser;

import java.util.Set;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/17 3:35
 * @Version 1.0
 */
@Component("ex")
public class SGExpressionRoot {

    public boolean hasAuthority(String authority){
        //获取用户的权限表
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Set<String> permissions = loginUser.getPermissions();
        //判断用户是否有权限
        return permissions.contains(authority);
    }
}

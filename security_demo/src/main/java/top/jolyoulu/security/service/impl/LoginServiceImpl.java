package top.jolyoulu.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jolyoulu.security.entity.LoginUser;
import top.jolyoulu.security.entity.SysUser;
import top.jolyoulu.security.result.ResultInfo;
import top.jolyoulu.security.utils.JwtUtils;
import top.jolyoulu.security.utils.RedisUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/17 0:44
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional
public class LoginServiceImpl {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtils redisUtils;

    public ResultInfo login(SysUser user) {
        //获取authenticationManager进行用户认证
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);//最终会调用UserDetailsServiceImpl
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //认证通过，使用userid生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal(); //实际是UserDetailsServiceImpl返回的对象
        String userId = loginUser.getUser().getId();
        //生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        String jwt = JwtUtils.createToken(claims);
        //userid做key存入redis中,30分钟过期
        redisUtils.set(userId,loginUser,30, TimeUnit.MINUTES);
        return ResultInfo.valueOf(jwt);
    }

    public ResultInfo logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser principal = (SysUser) authentication.getPrincipal();
        redisUtils.del(principal.getId());
        return ResultInfo.valueOf(null);
    }
}

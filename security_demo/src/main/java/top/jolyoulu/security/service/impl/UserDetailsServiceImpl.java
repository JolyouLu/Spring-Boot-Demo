package top.jolyoulu.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.jolyoulu.security.entity.LoginUser;
import top.jolyoulu.security.entity.SysUser;
import top.jolyoulu.security.service.ISysUserService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/16 23:01
 * @Version 1.0
 * 一登录后就会触发该loadUserByUsername方法
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService iSysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        SysUser user = iSysUserService.getByUsername(username);
        if(Objects.isNull(user)){
            log.error("找不到用户{}",user);
            throw new RuntimeException("用户名或密码错误");
        }
        //查询用户权限，假数据正常情况应该去数据库获取
        Set<String> permissions = new HashSet<>();
        permissions.add("update");
        return new LoginUser(user,permissions);
    }
}

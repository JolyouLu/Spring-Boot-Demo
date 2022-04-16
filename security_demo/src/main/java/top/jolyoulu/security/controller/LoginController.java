package top.jolyoulu.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.jolyoulu.security.entity.SysUser;
import top.jolyoulu.security.result.ResultInfo;
import top.jolyoulu.security.service.ISysUserService;
import top.jolyoulu.security.service.impl.LoginServiceImpl;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/16 22:35
 * @Version 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/user/login")
    public ResultInfo login(@RequestBody SysUser user){
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResultInfo logout(){
        return loginService.logout();
    }
}

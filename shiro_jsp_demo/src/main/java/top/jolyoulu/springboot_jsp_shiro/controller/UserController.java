package top.jolyoulu.springboot_jsp_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jolyoulu.springboot_jsp_shiro.entity.User;
import top.jolyoulu.springboot_jsp_shiro.service.UserService;
import top.jolyoulu.springboot_jsp_shiro.utils.VerifyCodeUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/14 22:46
 * @Version 1.0
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取验证码
     */
    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //验证码保存带session中
        session.setAttribute("code", code);
        //保存验证码图片
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(220, 60, response.getOutputStream(), code);
    }

    /**
     * 用户注册
     */
    @RequestMapping("register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            return "redirect:/register.jsp";
        }
    }

    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout(); //退出登录
        return "redirect:/login.jsp";
    }

    /**
     * 身份认证接口
     */
    @RequestMapping("login")
    public String login(String username, String password, String code, HttpSession session) {
        //比较验证码
        String codes = (String) session.getAttribute("code");
        try {
            if (!codes.equalsIgnoreCase(code)) {
                throw new RuntimeException("验证码错误!");
            }
            //获取主体对象
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:/index.jsp";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误！");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误！");
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("验证码错误！");
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping("add")
    @ResponseBody
    public Map<Object, Object> add() {
        Map<Object, Object> map = new HashMap<>();
        //通过编码方式获取主体对象判断，主体对象是否拥有权限
        Subject subject = SecurityUtils.getSubject();
        //同理判断角色用 subject.hasRole("admin")
        if (subject.isPermitted("user:add:*")) {
            map.put("code", 200);
            map.put("msg", "用户信息新增成功");
        } else {
            map.put("code", 403);
            map.put("msg", "用户权限不足");
        }
        return map;
    }

    @RequestMapping("update")
    @ResponseBody
    //通过注解方式判断权限
    //同理判断角色用 @RequiresRoles("admin")
    @RequiresPermissions("user:update:*")
    public Map<Object, Object> update() {
        Map<Object, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "用户信息修改成功");
        return map;
    }

}

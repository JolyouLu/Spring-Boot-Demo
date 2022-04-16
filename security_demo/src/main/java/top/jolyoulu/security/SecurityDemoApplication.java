package top.jolyoulu.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/16 22:33
 * @Version 1.0
 * SecurityConfig 设置拦截规则，加密规则
 * LoginServiceImpl 真正的登录实现
 * UserDetailsServiceImpl 调用authenticate方法时会触发该类
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启权限控制注解
public class SecurityDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SecurityDemoApplication.class, args);
        System.out.println();
    }
}

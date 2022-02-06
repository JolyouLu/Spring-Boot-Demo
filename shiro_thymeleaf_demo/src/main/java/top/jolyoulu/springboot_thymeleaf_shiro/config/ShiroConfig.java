package top.jolyoulu.springboot_thymeleaf_shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.jolyoulu.springboot_thymeleaf_shiro.shiro.realms.CustomerRealm;
import top.jolyoulu.springboot_thymeleaf_shiro.shiro.cache.RedisCacheManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/14 22:03
 * @Version 1.0
 * Shiro框架配置类
 */
@Configuration
public class ShiroConfig {

    @Bean("shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    //创建ShiroFilter
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给Filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(this.getDefaultSecurityManager());
        //配置系统受限资源 与 公共资源
        Map<String, String> map = new HashMap<>();
        map.put("/login.html", "anon"); //anon 公共资源
        map.put("/register.html", "anon"); //anon 公共资源
        map.put("/user/registerview", "anon"); //anon 公共资源
        map.put("/user/getImage", "anon"); //anon 公共资源
        map.put("/user/login", "anon"); //anon 公共资源
        map.put("/user/register", "anon"); //anon 公共资源
        map.put("/**", "authc"); //authc 请求这个资源需要认证和授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //配置认证界面的路径
        shiroFilterFactoryBean.setLoginUrl("/user/loginview");
        return shiroFilterFactoryBean;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(this.getRealm());
        return defaultSecurityManager;
    }

    //创建自定义realm
    @Bean
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        //修改凭证匹配器对加密后代码密码匹配 MD5+盐+hash
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        //开启缓存管理器
        customerRealm.setCacheManager(new RedisCacheManager());//使用自定义的缓存管理器
//        customerRealm.setCacheManager(new EhCacheManager());//使用Shiro提供的缓存管理器
        customerRealm.setCachingEnabled(true); //开启全局缓存
        customerRealm.setAuthorizationCachingEnabled(true); //认证缓存开启
        customerRealm.setAuthorizationCacheName("authorizationCache");
        customerRealm.setAuthenticationCachingEnabled(true); //授权缓存开启
        customerRealm.setAuthenticationCacheName("authenticationCache");
        return customerRealm;
    }
}

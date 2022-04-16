package top.jolyoulu.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/17 3:22
 * @Version 1.0
 * 允许跨域
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域路径
        registry.addMapping("/**")
                //设置允许跨域请求域名
                .allowedOriginPatterns("*")
                //是否允许cookie
                .allowCredentials(true)
                //设置允许的请求方式
                .allowedOriginPatterns("GET","POST","DELETE","PUT")
                //设置header属性
                .allowedHeaders("*")
                //跨域允许的时间
                .maxAge(3600);
    }
}

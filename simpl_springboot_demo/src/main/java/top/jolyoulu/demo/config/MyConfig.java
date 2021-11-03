package top.jolyoulu.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.jolyoulu.demo.filter.MyFilter;
import top.jolyoulu.demo.interceptors.MyInterceptors;

import javax.servlet.Filter;

/**
 * @Author: JolyouLu
 * @Date: 2021/11/1 15:58
 * @Version 1.0
 */
@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MyInterceptors myInterceptors;

    /**
     * 注册一个拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptors).addPathPatterns("/**").excludePathPatterns("/index.html","/");
    }

    /**
     * 注册一个过滤
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        //添加过滤器
        filterFilterRegistrationBean.setFilter(new MyFilter());
        //过滤所有路径
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }

}

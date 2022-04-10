package top.jolyoulu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import top.jolyoulu.apiVersion.ApiHandlerMapping;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/10 20:06
 * @Version 1.0
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    //自定义映射
 @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiHandlerMapping();
    }
}

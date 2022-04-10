package top.jolyoulu.apiVersion;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 从类和方法级别创建映射实例
 * 自定义请求映射规则，继承springMvc的 RequestMappingHandlerMapping 类重写
 * DispatcherServlet的初始化过程中自动加载的，默认会自动加载所有实现HandlerMapping接口的bean
 */
public class ApiHandlerMapping extends RequestMappingHandlerMapping {

    // 自定义类映射条件
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        return buildFrom(AnnotationUtils.findAnnotation(handlerType, ApiVersion.class));
    }

    // 自定义方法映射条件
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return buildFrom(AnnotationUtils.findAnnotation(method, ApiVersion.class));
    }

    private ApiCondition buildFrom(ApiVersion platform) {
        // 如果没有 建立 @APIVersion 注解，则给予默认值1.0.0的注解
        return platform == null ? new ApiCondition(new ApiItem()) :
                new ApiCondition(ApiConverter.convert(platform.value()));
    }
}
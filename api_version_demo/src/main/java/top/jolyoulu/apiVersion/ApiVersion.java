package top.jolyoulu.apiVersion;

import java.lang.annotation.*;

//定义版本的注解，用于标记 web 服务接口的版本，默认版本号为 1.0.0
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {
    // 版本，默认 1.0.0
    String value() default "1.0.0";
}

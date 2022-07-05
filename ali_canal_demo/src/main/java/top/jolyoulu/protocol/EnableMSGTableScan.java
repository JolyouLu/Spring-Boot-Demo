package top.jolyoulu.protocol;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/5 21:39
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MSGTableRegistrar.class)
public @interface EnableMSGTableScan {

    /** 需要扫描的包 bean存放目录*/
    String[] basePackages() default {};
}

package top.jolyoulu.springboot_jsp_shiro.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }

    /**
     * 获取applicationContext对象
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 根据bean的name来查找对象
     * @param name
     * @return
     */
    public static Object getBeanByName(String name){
        return applicationContext.getBean(name);
    }

    /**
     * 根据bean的class来查找对象
     * @param c
     * @return
     */
    public static Object getBeanByClass(Class c){
        return applicationContext.getBean(c);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param c
     * @return
     */
    public static Map getBeansByClass(Class c){
        return applicationContext.getBeansOfType(c);
    }
}
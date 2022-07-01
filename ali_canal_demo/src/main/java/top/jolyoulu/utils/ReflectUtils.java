package top.jolyoulu.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Function;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/1 23:03
 * @Version 1.0
 * 反射工具类型
 */
public class ReflectUtils {
    /**
     * 调用一个class的无参构建函数
     * @param Clazz
     * @return
     */
    public static <T> T NoArgNewInstance(Class<T> Clazz){
        T res = null;
        try {
            res = Clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 给一个对象属性赋值
     * @param Clazz 目标对象
     * @param targetObj 目标对象
     * @param targetField 目标对象属性
     * @param arg 参数
     * @param format 格式化规则
     */
    public static <T,R> void setMethod(Class<?> Clazz,
                                     Object targetObj,
                                     Field targetField,
                                     T arg,
                                     Function<T,R> format){
        try {
            targetField.setAccessible(true);
            PropertyDescriptor pd = new PropertyDescriptor(targetField.getName(), Clazz);
            Method method = pd.getWriteMethod();
            //如果有转换格式化，就格式化
            if (!Objects.isNull(format)){
                R formatArg = format.apply(arg);
                method.invoke(targetObj,formatArg);
            }else {
                method.invoke(targetObj,arg);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static class Format{

        /**
         * 简单的String转LocalDateTime
         */
        public static final Function<String, LocalDateTime> SIMP_STR_2_LOCALDATETIME = s -> {
            if (s.equals("0000-00-00 00:00:00")){
                return null;
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(s,dtf);
        };

        /**
         * 简单的String转Integer
         */
        public static final Function<String, Integer> STR_2_INT = s -> {
            return Integer.valueOf(s);
        };
    }
}

package top.jolyoulu.utils;

import top.jolyoulu.pipline.entity.ParamType;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
     * @param clazz 目标对象
     * @param targetObj 目标对象
     * @param targetField 目标对象属性
     * @param arg 参数
     * @param format 格式化规则
     */
    public static <T,R> void setMethod(Class<?> clazz,
                                     Object targetObj,
                                     Field targetField,
                                     T arg,
                                     Function<T,R> format){
        try {
            targetField.setAccessible(true);
            PropertyDescriptor pd = new PropertyDescriptor(targetField.getName(), clazz);
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

    /**
     * 将当前类的泛型与父类类泛型匹配，匹配相同的返回
     * @param thisObj 当前对象
     * @param superClazz 父类clazz
     * @return
     */
    public static Map<Class<?>, Map<String, Class<?>>> getParamType(Object thisObj,Class<?> superClazz){
        HashMap<Class<?>, Map<String, Class<?>>> res = new HashMap<>();
        Class<?> thisClazz = thisObj.getClass();
        HashMap<String, Class<?>> map = new HashMap<>();
        if (!Objects.isNull(thisClazz)){
            res.put(thisClazz,map);
        }
        Type genericSuperType = thisClazz.getGenericSuperclass();
        if (!(genericSuperType instanceof ParameterizedType)){ //如果里面没有泛型参数
            return res;
        }
        //获取父类原始泛型
        TypeVariable<? extends Class<?>>[] superType = superClazz.getTypeParameters();
        //获取实现类泛型类型
        Type[] actualTypeParams = ((ParameterizedType) genericSuperType).getActualTypeArguments();
        for (int i = 0; i < superType.length; i++) { //将父类的
            map.put(superType[i].getName(), (Class<?>) actualTypeParams[i]);
        }
        return res;
    }


    public static class Format{

        /**
         * String转LocalDateTime
         */
        public static final Function<String, LocalDateTime> STR_2_LOCALDATETIME = s -> {
            if (s.equals("0000-00-00 00:00:00")){
                return null;
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(s,dtf);
        };

        /**
         * String转Integer
         */
        public static final Function<String, Integer> STR_2_INT = s -> {
            return Integer.valueOf(s);
        };

        /**
         * String转Long
         */
        public static final Function<String, Long> STR_2_LONG = s -> {
            return Long.valueOf(s);
        };

        /**
         * String转Short
         */
        public static final Function<String, Short> STR_2_SHORT = s -> {
            return Short.valueOf(s);
        };

        /**
         * String转Double
         */
        public static final Function<String, Double> STR_2_DOUBLE = s -> {
            return Double.valueOf(s);
        };

        /**
         * String转Float
         */
        public static final Function<String, Float> STR_2_FLOAT = s -> {
            return Float.valueOf(s);
        };

        /**
         * String转Float
         */
        public static final Function<String, Date> STR_2_DATE = s -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}

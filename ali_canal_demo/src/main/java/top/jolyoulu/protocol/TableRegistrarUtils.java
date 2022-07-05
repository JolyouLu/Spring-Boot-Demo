package top.jolyoulu.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/5 21:44
 * @Version 1.0
 */
public class TableRegistrarUtils {

    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    public static Class<?> putIfAbsent(String key, Class<?> value){
        return map.putIfAbsent(key,value);
    }

    /**
     * 根据 Table注解 name获取Class对象
     *
     * @param tableName
     * @return
     */
    public static Class<?> getClass(String tableName) {
        return map.getOrDefault(tableName, null);
    }
}

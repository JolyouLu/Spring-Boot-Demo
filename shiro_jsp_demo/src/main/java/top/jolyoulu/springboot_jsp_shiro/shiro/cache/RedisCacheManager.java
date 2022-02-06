package top.jolyoulu.springboot_jsp_shiro.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/17 22:35
 * @Version 1.0
 * 自定义的Shiro缓存管理器
 */
public class RedisCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println("getCache收到参数" + cacheName);
        return new RedisCache<K, V>(cacheName);
    }
}

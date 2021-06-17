package top.jolyoulu.springboot_jsp_shiro.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.jolyoulu.springboot_jsp_shiro.utils.ApplicationContextUtils;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/17 22:39
 * @Version 1.0
 * //实现自定义缓存实现
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    //由于RedisCache不是由Spring托管的所有需要使用工具类获取bean工厂中对象
    private RedisTemplate getRedisTemplate(){
        return (RedisTemplate) ApplicationContextUtils.getBeanByName("redisTemplate");
    }

    @Override
    public V get(K k) throws CacheException {
        return (V) getRedisTemplate().opsForHash().get(this.cacheName,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        getRedisTemplate().opsForHash().put(this.cacheName,k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}

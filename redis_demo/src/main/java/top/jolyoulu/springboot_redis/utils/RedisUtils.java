package top.jolyoulu.springboot_redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author: JolyouLu
 * @Date: 2021/9/26 9:43
 * @Version 1.0
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象
     *
     * @param key   缓存的键
     * @param value 缓存的值
     */
    public <T> void set(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存一个基本对象并且设置过期时间
     *
     * @param key     缓存的键
     * @param value   缓存的值
     * @param timeout 超时时间（默认秒）
     * @return true/false
     */
    public <T> void set(final String key, final T value, final long timeout) {
        this.set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 缓存一个基本对象并且设置过期时间
     *
     * @param key     缓存的键
     * @param value   缓存的值
     * @param timeout 超时时间
     * @param unit    时间类型(时分秒)
     * @return true/false
     */
    public <T> void set(final String key, final T value, final long timeout, final TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 缓存基本的对象，若key不存在插入，若key存在不插入
     *
     * @param key   缓存的键
     * @param value 缓存的值
     * @return true key不存在插入成功 false 已经存在插入失败
     */
    public <T> boolean setIfAbsent(final String key, final T value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 缓存基本的对象，若key不存在插入，若key存在不插入
     *
     * @param key      缓存的键
     * @param value    缓存的值
     * @param timeOut  超时时长
     * @param timeUnit 时间单位
     * @return true key不存在插入成功 false 已经存在插入失败
     */
    public <T> boolean setIfAbsent(final String key, final T value, final long timeOut, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeOut, timeUnit);
    }

    /**
     * 获取缓存基本的对象
     *
     * @param key 缓存的键
     * @return 缓存的值
     */
    public <T> T get(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 监测缓存是否存在指定key
     *
     * @param key 缓存的键
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * <key,value>方式
     * 删除指定缓存对象
     *
     * @param key 缓存的键
     * @return true/false
     */
    public <T> boolean del(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * <key,Map<key,value>>方式
     * 往缓存map中保存对象
     *
     * @param mapName map名称
     * @param key     缓存的键
     * @param value   缓存的值
     * @param <T>
     */
    public <T> void hset(final String mapName, final String key, final T value) {
        redisTemplate.opsForHash().put(mapName, key, value);
    }

    /**
     * <key,Map<key,value>>方式
     * 获取缓存Map中的对象
     *
     * @param mapName map名称
     * @param key     缓存的键
     * @param <T>
     * @return
     */
    public <T> T hget(final String mapName, final String key) {
        return (T) redisTemplate.opsForHash().get(mapName, key);
    }

    /**
     * <key,Map<key,value>>方式
     * 删除缓存Map中的对象
     *
     * @param mapName map名称
     * @param key     缓存的键
     * @param <T>
     */
    public <T> void hdel(final String mapName, final String... key) {
        redisTemplate.opsForHash().delete(mapName, key);
    }

    /**
     * 原子性递减1
     *
     * @param key key
     * @return
     */
    public long decr(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 原子性递增1
     *
     * @param key key
     * @return
     */
    public long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 原子性递减
     *
     * @param key key
     * @param num 步长
     * @return
     */
    public long decr(String key, long num) {
        return redisTemplate.opsForValue().decrement(key, num);
    }

    /**
     * 原子性递增
     *
     * @param key key
     * @param num 步长
     * @return
     */
    public long incr(String key, long num) {
        return redisTemplate.opsForValue().increment(key, num);
    }

    /**
     * 分布式锁，加锁
     *
     * @param lock     锁ID
     * @param tag      锁标记，释放前与释放后需传入一致(防止被其它线程释放锁)
     * @param timeOut  锁有效时间
     * @param timeUnit 时间类型
     * @return true获取锁成功，false获取锁失败
     */
    public boolean tryLock(String lock, String tag, int timeOut, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(lock, tag, timeOut, timeUnit);
    }

    /**
     * 分布式锁，加锁(spring-boot-starter-data-redis 2.x之前的早期版本不推荐使用)
     *
     * @param lock    锁ID
     * @param tag     锁标记，释放前与释放后需传入一致(防止被其它线程释放锁)
     * @param timeOut 锁有效时间(秒)
     * @return true获取锁成功，false获取锁失败
     */
    @Deprecated
    public boolean tryLock(String lock, String tag, int timeOut) {
        String script = "if (redis.call('GET',KEYS[1])) then\n" +
                "    return 1\n" +
                "else\n" +
                "    redis.call('SET',KEYS[1],ARGV[1])\n" +
                "    redis.call('EXPIRE',KEYS[1],ARGV[2])\n" +
                "    return 0\n" +
                "end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);
        Long res = (Long) redisTemplate.execute(redisScript, Arrays.asList(lock), tag, timeOut);
        return res == 0;
    }

    /**
     * 分布式锁，释放锁
     *
     * @param lock 锁ID
     * @param tag  锁标记，释放前与释放后需传入一致(防止被其它线程释放锁)
     */
    public void unLock(String lock, String tag) {
        //释放锁，使用lua脚本，原子性操作
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                "return redis.call('del', KEYS[1]) " +
                "else " +
                "return 0 " +
                "end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);
        redisTemplate.execute(redisScript, Arrays.asList(lock), tag);
    }
}

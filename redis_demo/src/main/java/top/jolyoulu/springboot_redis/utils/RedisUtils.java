package top.jolyoulu.springboot_redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
     * 原子性递减1
     * @param key key
     * @return
     */
    public long decr(String key){
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 原子性递增1
     * @param key key
     * @return
     */
    public long incr(String key){
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 原子性递减
     * @param key key
     * @param num 步长
     * @return
     */
    public long decr(String key,long num){
        return redisTemplate.opsForValue().decrement(key,num);
    }

    /**
     * 原子性递增
     * @param key key
     * @param num 步长
     * @return
     */
    public long incr(String key,long num){
        return redisTemplate.opsForValue().increment(key,num);
    }

    /**
     * 分布式锁，加锁
     * @param lock 锁ID
     * @param tag 锁标记，释放前与释放后需传入一致(防止被其它线程释放锁)
     * @param timeOut 锁有效时间
     * @param timeUnit 时间类型
     * @return true获取锁成功，false获取锁失败
     */
    public boolean tryLock(String lock, String tag, int timeOut, TimeUnit timeUnit){
        return redisTemplate.opsForValue().setIfAbsent(lock, tag, timeOut, timeUnit);
    }

    /**
     * 分布式锁，释放锁
     * @param lock 锁ID
     * @param tag 锁标记，释放前与释放后需传入一致(防止被其它线程释放锁)
     */
    public void unLock(String lock, String tag){
        //释放锁，使用lua脚本，原子性操作
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                "return redis.call('del', KEYS[1]) " +
                "else " +
                "return 0 " +
                "end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);
        redisTemplate.execute(redisScript, Arrays.asList(lock),tag);
    }
}

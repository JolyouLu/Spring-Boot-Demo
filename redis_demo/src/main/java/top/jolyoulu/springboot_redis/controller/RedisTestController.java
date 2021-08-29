package top.jolyoulu.springboot_redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: JolyouLu
 * @Date: 2021/8/21 15:01
 * @Version 1.0
 */
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;

    //分布式锁的实现方式1：set nx ex + uuid
    @GetMapping("/testLock1")
    public void testLock1(){
        //生成uuid
        String uuid = UUID.randomUUID().toString();
        //获取锁
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 3, TimeUnit.SECONDS);
        if (lock){
            Object value = redisTemplate.opsForValue().get("num");
            //判断num为空return
            if (value == null){
                return;
            }
            //有值就转成int
            int num = Integer.parseInt(value + "");
            //把redis的num加1
            redisTemplate.opsForValue().set("num",++num);
            //释放锁，del
            //比较判断uuid是否一致
            String lockUUID = (String) redisTemplate.opsForValue().get("lock");
            if (lockUUID.equals(uuid)){
                redisTemplate.delete("lock");
            }
        }else {
            //获取锁失败后，每隔0.1秒再次获取
            try {
                Thread.sleep(100);
                testLock1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //分布式锁的实现方式2：lua脚本
    @GetMapping("/testLock2")
    public void testLock2(){
        //生成uuid
        String uuid = UUID.randomUUID().toString();
        //获取锁
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 3, TimeUnit.SECONDS);
        if (lock){
            Object value = redisTemplate.opsForValue().get("num");
            //判断num为空return
            if (value == null){
                return;
            }
            //有值就转成int
            int num = Integer.parseInt(value + "");
            //把redis的num加1
            redisTemplate.opsForValue().set("num",++num);
            //释放锁，使用lua脚本，原子性操作
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                              "return redis.call('del', KEYS[1]) " +
                            "else " +
                              "return 0 " +
                            "end";
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(script);
            redisScript.setResultType(Long.class);
            redisTemplate.execute(redisScript, Arrays.asList("lock"),uuid);
        }else {
            //获取锁失败后，每隔0.1秒再次获取
            try {
                Thread.sleep(100);
                testLock2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

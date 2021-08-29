package top.jolyoulu.sharding_jdbc_demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jolyoulu.sharding_jdbc_demo.entity.Order;
import top.jolyoulu.sharding_jdbc_demo.entity.User;
import top.jolyoulu.sharding_jdbc_demo.mapper.UserMapper;

/**
 * @Author: JolyouLu
 * @Date: 2021/8/29 21:43
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcDemoApplication.class)
public class RWProxyTests {

    @Autowired
    private UserMapper userMapper;

    //测试插入
    @Test
    public void addCourse() {
        User user = new User();
        user.setUsername("test");
        user.setUstatus("1");
        userMapper.insert(user);
    }

    //测试修改
    @Test
    public void find(){
        System.out.println(userMapper.selectList(new QueryWrapper<>()));
    }

}

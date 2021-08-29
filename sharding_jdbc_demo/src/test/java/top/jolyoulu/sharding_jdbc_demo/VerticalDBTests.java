package top.jolyoulu.sharding_jdbc_demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jolyoulu.sharding_jdbc_demo.entity.Course;
import top.jolyoulu.sharding_jdbc_demo.entity.Order;
import top.jolyoulu.sharding_jdbc_demo.entity.User;
import top.jolyoulu.sharding_jdbc_demo.mapper.CourseMapper;
import top.jolyoulu.sharding_jdbc_demo.mapper.OrderMapper;
import top.jolyoulu.sharding_jdbc_demo.mapper.UserMapper;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcDemoApplication.class)
class VerticalDBTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    //测试插入
    @Test
    void addCourse() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("test");
            user.setUstatus("正常");
            userMapper.insert(user);
            Order order = new Order();
            order.setUserId(123L);
            order.setOrderInfo("test");
            order.setOstatus("正常");
            orderMapper.insert(order);
        }
    }

    //测试查询
    @Test
    public void find(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id",638774471406125056L);
        System.out.println(userMapper.selectOne(userQueryWrapper));

        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_id",638774471200604161L);
        System.out.println(orderMapper.selectOne(orderQueryWrapper));
    }

}

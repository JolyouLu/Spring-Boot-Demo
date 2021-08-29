package top.jolyoulu.sharding_jdbc_demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jolyoulu.sharding_jdbc_demo.entity.Order;
import top.jolyoulu.sharding_jdbc_demo.entity.Udict;
import top.jolyoulu.sharding_jdbc_demo.entity.User;
import top.jolyoulu.sharding_jdbc_demo.mapper.OrderMapper;
import top.jolyoulu.sharding_jdbc_demo.mapper.UdictMapper;
import top.jolyoulu.sharding_jdbc_demo.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcDemoApplication.class)
class PublicTableTests {

    @Autowired
    private UdictMapper udictMapper;

    //测试插入
    @Test
    void addCourse() {
        Udict udict = new Udict();
        udict.setUvalue("1");
        udict.setUstatus("正常");
        udictMapper.insert(udict);
    }

    //测试删除
    @Test
    public void delete(){
        QueryWrapper<Udict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_id",638783223932911617L);
        udictMapper.delete(wrapper);
    }

}

package top.jolyoulu.sharding_jdbc_demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jolyoulu.sharding_jdbc_demo.entity.Course;
import top.jolyoulu.sharding_jdbc_demo.mapper.CourseMapper;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcDemoApplication.class)
class HorizontalDBTests {

    @Autowired
    private CourseMapper courseMapper;

    //测试插入语句
    @Test
    void addCourse() {
        for (int i = 0; i < 100; i++) {
            Course course = new Course();
            course.setCname("java"+i);
            //随机生成userId
            int userId = Math.abs(new Random().nextInt(100));
            course.setUserId(Long.valueOf(userId));
            course.setCstatus("Normal");
            courseMapper.insert(course);
        }
    }

    //测试查询
    @Test
    public void find(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",638753295996813313L);
        System.out.println(courseMapper.selectOne(wrapper));
    }

}

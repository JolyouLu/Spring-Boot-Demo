package top.jolyoulu.sharding_jdbc_demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jolyoulu.sharding_jdbc_demo.entity.Course;
import top.jolyoulu.sharding_jdbc_demo.mapper.CourseMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcDemoApplication.class)
class HorizontalTableTests {

    @Autowired
    private CourseMapper courseMapper;

    //测试插入
    @Test
    void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("java"+i);
            course.setUserId(123L);
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

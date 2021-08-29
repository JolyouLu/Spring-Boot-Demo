package top.jolyoulu.sharding_jdbc_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.jolyoulu.sharding_jdbc_demo.entity.Course;

/**
 * @Author: JolyouLu
 * @Date: 2021/8/29 13:50
 * @Version 1.0
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {
}

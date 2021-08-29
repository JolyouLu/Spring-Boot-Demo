package top.jolyoulu.sharding_jdbc_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.jolyoulu.sharding_jdbc_demo.entity.User;

/**
 * @Author: JolyouLu
 * @Date: 2021/8/29 16:06
 * @Version 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}

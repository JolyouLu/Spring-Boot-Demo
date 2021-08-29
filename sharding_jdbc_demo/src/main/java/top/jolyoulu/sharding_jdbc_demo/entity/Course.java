package top.jolyoulu.sharding_jdbc_demo.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: JolyouLu
 * @Date: 2021/8/29 13:48
 * @Version 1.0
 */
@Data
@ToString
public class Course {
    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;
}

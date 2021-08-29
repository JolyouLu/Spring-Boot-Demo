package top.jolyoulu.sharding_jdbc_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: JolyouLu
 * @Date: 2021/8/29 16:02
 * @Version 1.0
 */
@Data
@ToString
public class User {
    private Long userId;
    private String username;
    private String ustatus;
}

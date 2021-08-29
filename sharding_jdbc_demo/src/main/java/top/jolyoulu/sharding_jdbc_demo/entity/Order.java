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
public class Order {
    private Long orderId;
    private Long userId;
    private String orderInfo;
    private String ostatus;
}

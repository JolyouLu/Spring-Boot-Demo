package top.jolyoulu.entity;

import lombok.Data;
import top.jolyoulu.protocol.Table;
import top.jolyoulu.protocol.TableField;

import java.time.LocalDateTime;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/1 21:59
 * @Version 1.0
 */
@Data
@Table(name = "fzd_a")
public class TestEntity2 {
    @TableField(name = "id")
    private String id;
    @TableField(name = "user_id")
    private String userId;
    @TableField(name = "create_by")
    private String create_by;
    @TableField(name = "create_time")
    private LocalDateTime create_time;
    @TableField(name = "update_by")
    private String update_by;
    @TableField(name = "update_time")
    private LocalDateTime update_time;
    @TableField(name = "del_flag")
    private Integer del_flag;
}

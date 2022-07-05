package top.jolyoulu.entity;

import lombok.Data;
import top.jolyoulu.protocol.MSGTable;
import top.jolyoulu.protocol.MSGTableField;

import java.time.LocalDateTime;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/1 21:59
 * @Version 1.0
 */
@Data
@MSGTable("fzd_a2")
public class TestEntity2 {
    @MSGTableField("id")
    private String id;
    @MSGTableField("user_id")
    private String userId;
    @MSGTableField("create_by")
    private String create_by;
    @MSGTableField("create_time")
    private LocalDateTime create_time;
    @MSGTableField("update_by")
    private String update_by;
    @MSGTableField("update_time")
    private LocalDateTime update_time;
    @MSGTableField("del_flag")
    private Integer del_flag;
}

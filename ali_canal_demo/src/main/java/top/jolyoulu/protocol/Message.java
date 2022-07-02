package top.jolyoulu.protocol;

import lombok.Data;
import lombok.ToString;
import top.jolyoulu.entity.TestEntity;

import java.util.*;

/**
 * @Author LuZhouJin
 * @Date 2022/7/1
 */
@Data
@ToString
public class Message<T> {
    private Class<T> Clazz;
    private List<T> data;
    private String database;
    private Long es;
    private Integer id;
    private Boolean isDdl;
    private Map<String,String> mysqlType;
    private List<T> old;
    private List<String> pkNames;
    private String sql;
    private Map<String,String> sqlType;
    private String table;
    private Long ts;
    private String type;

    public Message(Class<T> Clazz) {
        this.Clazz = Clazz;
    }
}

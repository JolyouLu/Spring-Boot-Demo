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
    private Class<T> tClass;
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

    public Message(Class<T> tClass) {
        this.tClass = tClass;
    }




    public static void main(String[] args){
        Message<TestEntity> message = new Message<>(TestEntity.class);
        MessageDecodeUtils.decode(msg,message);
        System.out.println(message);
    }

    private static String msg = "{\"data\":[{\t\t\"id\":\"2\",\t\t\"user_id\":\"666\",\t\t\"create_by\":\"bb\",\t\t\"create_time\":\"2022-05-25 13:14:55\",\t\t\"update_by\":\"\",\t\t\"update_time\":\"0000-00-00 00:00:00\",\t\t\"del_flag\":\"0\"\t},{\t\t\"id\":\"3\",\t\t\"user_id\":\"666\",\t\t\"create_by\":\"132\",\t\t\"create_time\":\"0000-00-00 00:00:00\",\t\t\"update_by\":\"\",\t\t\"update_time\":\"0000-00-00 00:00:00\",\t\t\"del_flag\":\"0\"\t}],\t\"database\":\"fenzhidao_product\",\t\"es\":1655191608000,\t\"id\":249544,\t\"isDdl\":false,\t\"mysqlType\":{\t\t\"id\":\"varchar(32)\",\t\t\"user_id\":\"varchar(32)\",\t\t\"create_by\":\"varchar(32)\",\t\t\"create_time\":\"datetime\",\t\t\"update_by\":\"varchar(32)\",\t\t\"update_time\":\"datetime\",\t\t\"del_flag\":\"tinyint(1) unsigned\"\t},\t\"old\":[{\t\t\"user_id\":\"cc\"\t},{\t\t\"user_id\":\"55\"\t}],\t\"pkNames\":[\"id\"],\t\"sql\":\"\",\t\"sqlType\":{\t\t\"id\":12,\t\t\"user_id\":12,\t\t\"create_by\":12,\t\t\"create_time\":93,\t\t\"update_by\":12,\t\t\"update_time\":93,\t\t\"del_flag\":-6\t},\t\"table\":\"fzd_a\",\t\"ts\":1655191608243,\t\"type\":\"UPDATE\"}";
}

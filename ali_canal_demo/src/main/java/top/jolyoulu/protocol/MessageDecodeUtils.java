package top.jolyoulu.protocol;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import top.jolyoulu.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 0:54
 * @Version 1.0
 */
public class MessageDecodeUtils {

    /**
     * 解码消息
     * @param msg 消息内容
     * @param message 返回掉线
     */
    public static <T> void decode(String msg, Message<T> message){
        Class<T> Clazz = message.getTClass();
        //转json对象
        JSONObject jsonObject = JSONObject.parseObject(msg);
        //获取datajson数组
        List<Map<String,Object>> dataList= jsonObject.getObject("data", new TypeReference<List<Map<String,Object>>>(){});
        ArrayList<T> datas = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            Map<String,Object> map = dataList.get(i);
            T targetObj = setAllField(map, Clazz);
            datas.add(targetObj);
        }
        message.setData(datas);
        message.setDatabase(jsonObject.getString("database"));
        message.setEs(jsonObject.getLong("es"));
        message.setIsDdl(jsonObject.getBoolean("isDdl"));
        message.setMysqlType(jsonObject.getObject("mysqlType", new TypeReference<Map<String,String>>(){}));
        //获取datajson数组
        List<Map<String,Object>> oldList= jsonObject.getObject("old", new TypeReference<List<Map<String,Object>>>(){});
        ArrayList<T> olds = new ArrayList<>();
        for (int i = 0; i < oldList.size(); i++) {
            Map<String,Object> map = oldList.get(i);
            T targetObj = setAllField(map, Clazz);
            olds.add(targetObj);
        }
        message.setOld(olds);
        message.setPkNames(jsonObject.getObject("pkNames", new TypeReference<List<String>>(){}));
        message.setSql(jsonObject.getString("sql"));
        message.setSqlType(jsonObject.getObject("sqlType", new TypeReference<Map<String,String>>(){}));
        message.setTable(jsonObject.getString("table"));
        message.setTs(jsonObject.getLong("ts"));
        message.setType(jsonObject.getString("type"));
    }

    //将对象中的所以参数都赋值
    private static  <T> T setAllField(Map<String,Object> source,Class<T> Clazz){
        T targetObj = ReflectUtils.NoArgNewInstance(Clazz);
        Field[] fields = Clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(TableField.class)){
                TableField annotation = field.getAnnotation(TableField.class);
                String name = annotation.name();
                Object arg = source.get(name);
                if (!Objects.isNull(arg)){
                    Function format= null;
                    if (field.getType().isAssignableFrom(LocalDateTime.class)){
                        format = ReflectUtils.Format.SIMP_STR_2_LOCALDATETIME;
                    }
                    if (field.getType().isAssignableFrom(Integer.class)){
                        format = ReflectUtils.Format.STR_2_INT;
                    }
                    ReflectUtils.setMethod(Clazz,targetObj,field,arg,format);
                }
            }
        }
        return targetObj;
    }
}

package top.jolyoulu.handle;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import top.jolyoulu.pipline.defhandler.AbstractMessageHandlerContextAdapter;
import top.jolyoulu.protocol.Messages;
import top.jolyoulu.protocol.TableField;
import top.jolyoulu.protocol.TableRegistrarUtils;
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
 * @Date: 2022/7/2 12:22
 * @Version 1.0
 */
@Slf4j
public class ProtocolDecodeHandler extends AbstractMessageHandlerContextAdapter {


    public ProtocolDecodeHandler(String name) {
        super(name);
    }

    @Override
    public void accept(AbstractMessageHandlerContextAdapter ctx, Object msg) {
        String str = (String) msg;
        String table = JSONObject.parseObject(str).getString("table");
        Class<?> clazz = TableRegistrarUtils.getClass(table);
        if (!Objects.isNull(clazz)){
            log.warn("解析消息 => {}",msg);
            Messages message = new Messages(clazz);
            decode(str,message);
            log.warn("消息解析成功 => {}",message);
            ctx.next(message);
        }else {
            log.warn("{} 消息未找到对应的类解析",table);
            ctx.next(msg);
        }
    }

    /**
     * 解码消息
     * @param msg 消息内容
     * @param messages 返回掉线
     */
    private static <T> void decode(String msg, Messages<T> messages){
        Class<T> clazz = messages.getClazz();
        if (Objects.isNull(clazz)){
            return;
        }
        //转json对象
        JSONObject jsonObject = JSONObject.parseObject(msg);
        //获取datajson数组
        List<Map<String,Object>> dataList= jsonObject.getObject("data", new TypeReference<List<Map<String,Object>>>(){});
        ArrayList<T> datas = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            Map<String,Object> map = dataList.get(i);
            T targetObj = setAllField(map, clazz);
            datas.add(targetObj);
        }
        messages.setData(datas);
        messages.setDatabase(jsonObject.getString("database"));
        messages.setEs(jsonObject.getLong("es"));
        messages.setIsDdl(jsonObject.getBoolean("isDdl"));
        messages.setMysqlType(jsonObject.getObject("mysqlType", new TypeReference<Map<String,String>>(){}));
        //获取datajson数组
        List<Map<String,Object>> oldList= jsonObject.getObject("old", new TypeReference<List<Map<String,Object>>>(){});
        ArrayList<T> olds = new ArrayList<>();
        for (int i = 0; i < oldList.size(); i++) {
            Map<String,Object> map = oldList.get(i);
            T targetObj = setAllField(map, clazz);
            olds.add(targetObj);
        }
        messages.setOld(olds);
        messages.setPkNames(jsonObject.getObject("pkNames", new TypeReference<List<String>>(){}));
        messages.setSql(jsonObject.getString("sql"));
        messages.setSqlType(jsonObject.getObject("sqlType", new TypeReference<Map<String,String>>(){}));
        messages.setTable(jsonObject.getString("table"));
        messages.setTs(jsonObject.getLong("ts"));
        messages.setType(jsonObject.getString("type"));
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
                    switch (field.getType().getName()){
                        case "java.time.LocalDateTime":
                            format = ReflectUtils.Format.STR_2_LOCALDATETIME;
                            break;
                        case "java.util.Date":
                            format = ReflectUtils.Format.STR_2_DATE;
                            break;
                        case "java.lang.Integer":
                            format = ReflectUtils.Format.STR_2_INT;
                            break;
                        case "java.lang.Long":
                            format = ReflectUtils.Format.STR_2_LONG;
                            break;
                        case "java.lang.Short":
                            format = ReflectUtils.Format.STR_2_SHORT;
                            break;
                        case "java.lang.Double":
                            format = ReflectUtils.Format.STR_2_DOUBLE;
                            break;
                        case "java.lang.Float":
                            format = ReflectUtils.Format.STR_2_FLOAT;
                            break;
                    }
                    ReflectUtils.setMethod(Clazz,targetObj,field,arg,format);
                }
            }
        }
        return targetObj;
    }
}

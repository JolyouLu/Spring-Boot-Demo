package top.jolyoulu.protocol;

import com.alibaba.fastjson.JSONObject;
import top.jolyoulu.AliCanalApplication;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 2:40
 * @Version 1.0
 */
public class ProtocolDecode{

    public static Message decode(String msg){
        Message message = null;
        String table = JSONObject.parseObject(msg).getString("table");
        Class<?> clazz = AliCanalApplication.map.get(table);
        message = new Message(clazz);
        MessageDecodeUtils.decode(msg,message);
        return message;
    }


}

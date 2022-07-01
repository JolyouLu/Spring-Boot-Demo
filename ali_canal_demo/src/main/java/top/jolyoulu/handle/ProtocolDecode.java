package top.jolyoulu.handle;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import top.jolyoulu.entity.TestEntity;
import top.jolyoulu.pipline.AbstractMessageHandlerContextAdapter;
import top.jolyoulu.protocol.Message;
import top.jolyoulu.protocol.MessageDecodeUtils;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 2:40
 * @Version 1.0
 */
public class ProtocolDecode{

    public static Message decode(String msg){
        Message message = null;
        String table = JSONObject.parseObject(msg).getString("table");
        switch (table){
            case "fzd_a":
                message = new Message(TestEntity.class);
                break;
        }
        MessageDecodeUtils.decode(msg,message);
        return message;
    }


}

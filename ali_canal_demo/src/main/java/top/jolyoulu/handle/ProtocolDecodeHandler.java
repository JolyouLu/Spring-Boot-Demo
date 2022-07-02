package top.jolyoulu.handle;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import top.jolyoulu.AliCanalApplication;
import top.jolyoulu.pipline.defhandler.AbstractMessageHandlerContextAdapter;
import top.jolyoulu.protocol.Message;
import top.jolyoulu.protocol.MessageDecodeUtils;
import top.jolyoulu.protocol.TableBeanFactory;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 12:22
 * @Version 1.0
 */
@Slf4j
public class ProtocolDecodeHandler extends AbstractMessageHandlerContextAdapter {

    @Autowired
    private TableBeanFactory tableBeanFactory;

    public ProtocolDecodeHandler(String name) {
        super(name);
    }

    @Override
    public void accept(AbstractMessageHandlerContextAdapter ctx, Object msg) {
        log.warn("收到消息 => {}",msg);
        String str = (String) msg;
        Message message = null;
        String table = JSONObject.parseObject(str).getString("table");
        Class<?> clazz = tableBeanFactory.getClass(table);
        message = new Message(clazz);
        MessageDecodeUtils.decode(str,message);
        log.warn("消息解析 => {}",message);
        ctx.next(message);
    }
}

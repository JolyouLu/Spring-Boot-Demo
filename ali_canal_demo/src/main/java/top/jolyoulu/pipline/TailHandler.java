package top.jolyoulu.pipline;

import lombok.extern.slf4j.Slf4j;
import top.jolyoulu.protocol.Message;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:11
 * @Version 1.0
 */

@Slf4j
public class TailHandler extends AbstractMessageHandlerContextAdapter{

    public TailHandler(String name) {
        super(name);
    }

    @Override
    public void accept(AbstractMessageHandlerContextAdapter ctx,Message message) {
        log.warn("未处理消息：{}",message.toString());
    }
}

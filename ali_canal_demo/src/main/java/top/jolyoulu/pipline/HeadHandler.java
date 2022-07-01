package top.jolyoulu.pipline;

import lombok.extern.slf4j.Slf4j;
import top.jolyoulu.protocol.Message;

@Slf4j
public class HeadHandler extends AbstractMessageHandlerContextAdapter{

    public HeadHandler(String name) {
        super(name);
    }

    @Override
    public void accept(AbstractMessageHandlerContextAdapter ctx,Message message) {
        log.info("用户消息:{}",message.toString());
        ctx.next(message);
    }
}
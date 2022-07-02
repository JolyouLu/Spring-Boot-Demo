package top.jolyoulu.pipline.defhandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeadHandler extends AbstractMessageHandlerContextAdapter {

    public HeadHandler(String name) {
        super(name);
    }

    @Override
    public void accept(AbstractMessageHandlerContextAdapter ctx,Object message) {
        log.info("用户消息:{}",message.toString());
        ctx.next(message);
    }
}
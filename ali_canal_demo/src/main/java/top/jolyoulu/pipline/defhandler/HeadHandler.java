package top.jolyoulu.pipline.defhandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeadHandler extends AbstractMessageHandlerContextAdapter {

    public HeadHandler(String name) {
        super(name);
    }

    @Override
    public void accept(AbstractMessageHandlerContextAdapter ctx,Object msg) {
        log.info("用户消息:{}", msg.toString());
        ctx.next(msg);
    }
}
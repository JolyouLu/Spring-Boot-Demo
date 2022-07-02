package top.jolyoulu.pipline.defhandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:11
 * @Version 1.0
 */

@Slf4j
public class TailHandler extends AbstractMessageHandlerContextAdapter {

    public TailHandler(String name) {
        super(name);
    }

    @Override
    public void accept(AbstractMessageHandlerContextAdapter ctx,Object msg) {
        log.warn("未处理消息：{}", msg.toString());
    }
}

package top.jolyoulu.pipline;

import top.jolyoulu.pipline.defhandler.AbstractMessageHandlerContextAdapter;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:14
 * @Version 1.0
 */
public interface MessagePipeline {

    void addHandler(AbstractMessageHandlerContextAdapter ctx);

}

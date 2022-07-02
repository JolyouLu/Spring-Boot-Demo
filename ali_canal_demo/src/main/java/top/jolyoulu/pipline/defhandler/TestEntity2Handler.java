package top.jolyoulu.pipline.defhandler;

import top.jolyoulu.entity.TestEntity2;
import top.jolyoulu.protocol.Message;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 3:37
 * @Version 1.0
 */
public class TestEntity2Handler extends SimpleMessageHandlerContext<TestEntity2> {

    public TestEntity2Handler(String name) {
        super(name);
    }

    @Override
    public void accept0(AbstractMessageHandlerContextAdapter ctx, Message<TestEntity2> message) {
        System.out.println("测水水水水水水水水水水水水水水水水水水"+message);
    }
}
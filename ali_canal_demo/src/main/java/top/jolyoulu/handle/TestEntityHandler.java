package top.jolyoulu.handle;

import top.jolyoulu.entity.TestEntity;
import top.jolyoulu.pipline.defhandler.AbstractMessageHandlerContextAdapter;
import top.jolyoulu.pipline.defhandler.SimpleMessageHandlerContext;
import top.jolyoulu.protocol.MSGBody;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/1 21:59
 * @Version 1.0
 */
public class TestEntityHandler extends SimpleMessageHandlerContext<TestEntity> {

    public TestEntityHandler(String name) {
        super(name);
    }

    @Override
    public void accept0(AbstractMessageHandlerContextAdapter ctx, MSGBody<TestEntity> msg) {
        System.out.println("TestEntity2Handler"+msg);
        ctx.next(msg);
    }
}

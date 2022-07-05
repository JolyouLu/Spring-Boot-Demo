package top.jolyoulu.handle;

import top.jolyoulu.entity.TestEntity2;
import top.jolyoulu.pipline.defhandler.AbstractMessageHandlerContextAdapter;
import top.jolyoulu.pipline.defhandler.SimpleMessageHandlerContext;
import top.jolyoulu.protocol.MSGBody;

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
    public void accept0(AbstractMessageHandlerContextAdapter ctx, MSGBody<TestEntity2> MSGBody) {
        System.out.println("TestEntity2Handler"+ MSGBody);
        ctx.next(MSGBody);
    }
}

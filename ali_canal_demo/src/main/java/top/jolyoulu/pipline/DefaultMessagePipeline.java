package top.jolyoulu.pipline;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:15
 * @Version 1.0
 */
public class DefaultMessagePipeline implements MessagePipeline {

    private AbstractMessageHandlerContextAdapter<?> ctx;

    public DefaultMessagePipeline(AbstractMessageHandlerContextAdapter<?> ctx) {
        this.ctx = ctx;
    }

    /**
     * 迭代添加Handler
     * @param next
     */
    @Override
    public void addHandler(AbstractMessageHandlerContextAdapter<?> next) {
        AbstractMessageHandlerContextAdapter<?> context = this.ctx;
        AbstractMessageHandlerContextAdapter<?> tail = null;

        while (context.getNextHandlerContext() != null){
            //如果下一个TailHandle那么就不获取了
            if (context.getNextHandlerContext() instanceof TailHandler){
                tail = context.getNextHandlerContext();
                break;
            }else {
                context = context.getNextHandlerContext();
            }
        }
        context.setNextHandlerContext(next);
        context.getNextHandlerContext().setNextHandlerContext(tail);
    }

    public AbstractMessageHandlerContextAdapter<?> getCtx() {
        return ctx;
    }
}

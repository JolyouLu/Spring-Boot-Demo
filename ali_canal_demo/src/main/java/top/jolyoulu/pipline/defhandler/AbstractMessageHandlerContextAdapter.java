package top.jolyoulu.pipline.defhandler;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:10
 * @Version 1.0
 */
public abstract class AbstractMessageHandlerContextAdapter {

    //当前处理对象的下一个处理者
    protected AbstractMessageHandlerContextAdapter nextHandlerContext;
    //Handler名字
    protected String name;

    public AbstractMessageHandlerContextAdapter(String name){
        this.name = name;
    }

    public AbstractMessageHandlerContextAdapter getNextHandlerContext() {
        return nextHandlerContext;
    }

    public void setNextHandlerContext(AbstractMessageHandlerContextAdapter nextHandlerContext) {
        this.nextHandlerContext = nextHandlerContext;
    }

    public void next(Object message){
        nextHandlerContext.accept(nextHandlerContext,message);
    }


    //处理Handler重新方法
    public abstract void accept(AbstractMessageHandlerContextAdapter ctx,Object message);

}

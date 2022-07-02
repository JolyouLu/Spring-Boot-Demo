package top.jolyoulu.pipline.defhandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:10
 * @Version 1.0
 */
@Slf4j
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

    public void next(Object msg){
        try {
            nextHandlerContext.accept(nextHandlerContext, msg);
        }catch (Exception e){
            exception(nextHandlerContext, msg,e);
        }
    }


    //处理Handler重新方法
    public abstract void accept(AbstractMessageHandlerContextAdapter ctx,Object msg);

    public void exception(AbstractMessageHandlerContextAdapter ctx,Object msg,Exception e){
        log.error("一个未被处理的异常",e);
    };

}

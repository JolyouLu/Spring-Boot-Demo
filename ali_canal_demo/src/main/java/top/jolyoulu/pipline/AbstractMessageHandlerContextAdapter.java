package top.jolyoulu.pipline;

import top.jolyoulu.protocol.Message;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:10
 * @Version 1.0
 */
public abstract class AbstractMessageHandlerContextAdapter<T> {

    //当前处理对象的下一个处理者
    protected AbstractMessageHandlerContextAdapter<?> nextHandlerContext;
    //Handler名字
    protected String name;

    public AbstractMessageHandlerContextAdapter(String name){
        this.name = name;
    }

    protected AbstractMessageHandlerContextAdapter<?> getNextHandlerContext() {
        return nextHandlerContext;
    }

    protected void setNextHandlerContext(AbstractMessageHandlerContextAdapter<?> nextHandlerContext) {
        this.nextHandlerContext = nextHandlerContext;
    }

    public void next(Message message){
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
            Type[] types = type.getActualTypeArguments();
            if (((Class) types[0]).isInstance(message)){
                accept(nextHandlerContext,message);
                return;
            }
        }else {
            accept(nextHandlerContext,message);
            return;
        }
        if (!Objects.isNull(nextHandlerContext)){
            nextHandlerContext.next(message);
        }
    }


    //处理Handler重新方法
    public abstract void accept(AbstractMessageHandlerContextAdapter ctx,Message<T> message);

}

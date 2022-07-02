package top.jolyoulu.pipline.defhandler;

import lombok.Data;
import top.jolyoulu.pipline.defhandler.AbstractMessageHandlerContextAdapter;
import top.jolyoulu.pipline.entity.ParamType;
import top.jolyoulu.protocol.Message;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 10:58
 * @Version 1.0
 */
public abstract class SimpleMessageHandlerContext<T> extends AbstractMessageHandlerContextAdapter {

    private ParamType paramType;

    public SimpleMessageHandlerContext(String name) {
        super(name);
    }

    @Override
    public void accept(AbstractMessageHandlerContextAdapter ctx, Object msg) {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
            Type[] types = type.getActualTypeArguments();
            if (types[0] == paramType.getType()){
                @SuppressWarnings("unchecked")
                Message<T> message = (Message<T>) msg;
                accept0(nextHandlerContext,message);
                return;
            }
        }
        if (!Objects.isNull(nextHandlerContext)){
            nextHandlerContext.next(msg);
        }
    }

    public abstract void accept0(AbstractMessageHandlerContextAdapter ctx,Message<T> msg);

    public ParamType getParamType() {
        return paramType;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }
}

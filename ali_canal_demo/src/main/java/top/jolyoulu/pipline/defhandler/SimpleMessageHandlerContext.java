package top.jolyoulu.pipline.defhandler;

import top.jolyoulu.pipline.entity.ParamType;
import top.jolyoulu.protocol.Messages;

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
        if (msg instanceof Messages){
            if (paramType.getType() == ((Messages<?>) msg).getClazz()){
                @SuppressWarnings("unchecked")
                Messages<T> messages = (Messages<T>) msg;
                accept0(ctx, messages);
                return;
            }
        }
        if (!Objects.isNull(ctx.nextHandlerContext)){
            ctx.next(msg);
        }
    }

    public abstract void accept0(AbstractMessageHandlerContextAdapter ctx, Messages<T> msg);

    public ParamType getParamType() {
        return paramType;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }
}

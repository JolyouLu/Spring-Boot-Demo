package top.jolyoulu.pipline;

import top.jolyoulu.pipline.defhandler.AbstractMessageHandlerContextAdapter;
import top.jolyoulu.pipline.defhandler.HeadHandler;
import top.jolyoulu.pipline.defhandler.SimpleMessageHandlerContext;
import top.jolyoulu.pipline.defhandler.TailHandler;
import top.jolyoulu.pipline.entity.ParamType;
import top.jolyoulu.utils.ReflectUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:15
 * @Version 1.0
 */
public class DefaultMessagePipeline implements MessagePipeline {

    private final AbstractMessageHandlerContextAdapter ctx = new HeadHandler("headHandle");

    /**
     * 迭代添加Handler
     * @param next
     */
    @Override
    public void addHandler(AbstractMessageHandlerContextAdapter next) {
        AbstractMessageHandlerContextAdapter context = this.ctx;
        AbstractMessageHandlerContextAdapter tail = null;
        if (next instanceof SimpleMessageHandlerContext){
            SimpleMessageHandlerContext simple = (SimpleMessageHandlerContext) next;
            simple.setParamType(getParamType(next));
        }
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

    public AbstractMessageHandlerContextAdapter getCtx() {
        return ctx;
    }

    private ParamType getParamType(AbstractMessageHandlerContextAdapter ctx){
        Class<?> superclass = ctx.getClass().getSuperclass();
        Map<Class<?>, Map<String, Class<?>>> map = ReflectUtils.getParamType(ctx, superclass);
        if (map.isEmpty()){
            return null;
        }
        Map<String, Class<?>> type = map.get(ctx.getClass());
        if (type.isEmpty()){
            return null;
        }
        Class<?> aClass = type.get("T");
        if (Objects.isNull(aClass)){
            return null;
        }
        return new ParamType(aClass);
    }
}

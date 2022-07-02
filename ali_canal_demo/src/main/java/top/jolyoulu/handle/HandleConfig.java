package top.jolyoulu.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.jolyoulu.pipline.DefaultMessagePipeline;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 11:37
 * @Version 1.0
 */
@Configuration
public class HandleConfig {

    @Autowired
    private DefaultMessagePipeline pipeline;

    @Bean
    public void initPipeline(){
        pipeline.addHandler(this.protocolDecodeHandler());
        pipeline.addHandler(this.testEntityHandler());
        pipeline.addHandler(this.testEntity2Handler());
    }

    @Bean
    public TestEntity2Handler testEntity2Handler(){
        return new TestEntity2Handler("testEntity2Handler");
    }

    @Bean
    public TestEntityHandler testEntityHandler(){
        return new TestEntityHandler("testEntityHandler");
    }

    @Bean
    public ProtocolDecodeHandler protocolDecodeHandler(){
        return new ProtocolDecodeHandler("protocolDecodeHandler");
    }

}

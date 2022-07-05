package top.jolyoulu.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import top.jolyoulu.pipline.DefaultMessagePipeline;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 11:37
 * @Version 1.0
 */
@Slf4j
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

package top.jolyoulu.pipline.defhandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.jolyoulu.pipline.DefaultMessagePipeline;
import top.jolyoulu.pipline.defhandler.HeadHandler;
import top.jolyoulu.pipline.defhandler.TailHandler;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:15
 * @Version 1.0
 */
@Configuration
public class PipelineConfig {

    @Bean
    public DefaultMessagePipeline defaultRequestPipeline(){
        DefaultMessagePipeline pipeline = new DefaultMessagePipeline();
        pipeline.addHandler(new TailHandler("tailHandle"));
        return pipeline;
    }
}

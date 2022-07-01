package top.jolyoulu.pipline;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:15
 * @Version 1.0
 */
@Configuration
public class PipelineConfig {

    @Bean
    public DefaultMessagePipeline defaultRequestPipeline(){
        DefaultMessagePipeline pipeline = new DefaultMessagePipeline(new HeadHandler("headHandle"));
        pipeline.addHandler(new TailHandler("tailHandle"));
        return pipeline;
    }
}

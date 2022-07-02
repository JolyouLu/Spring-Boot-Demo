package top.jolyoulu.protocol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 12:54
 * @Version 1.0
 */
@Configuration
public class TableBeanFactoryConfig {
    @Bean
    public TableBeanFactory tableBeanFactory(){
        TableBeanFactory factory = new TableBeanFactory("top.jolyoulu.entity");
        factory.init();
        return factory;
    }
}

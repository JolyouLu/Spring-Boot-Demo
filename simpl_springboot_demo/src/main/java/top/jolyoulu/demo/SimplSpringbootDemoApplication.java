package top.jolyoulu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import top.jolyoulu.demo.controller.Person;

@SpringBootApplication
@EnableConfigurationProperties({Person.class})
public class SimplSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplSpringbootDemoApplication.class, args);
    }

}

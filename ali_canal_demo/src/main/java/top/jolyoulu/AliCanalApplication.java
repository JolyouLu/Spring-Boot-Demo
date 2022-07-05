package top.jolyoulu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.jolyoulu.protocol.EnableTableScan;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:40
 * @Version 1.0
 */
@SpringBootApplication
@EnableTableScan(basePackages = {"top.jolyoulu.entity"})
public class AliCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliCanalApplication.class, args);
    }
}

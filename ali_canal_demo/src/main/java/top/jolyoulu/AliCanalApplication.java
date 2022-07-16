package top.jolyoulu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.jolyoulu.protocol.EnableMSGTableScan;
import top.jolyoulu.protocol.TestConfig;

import java.io.IOException;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:40
 * @Version 1.0
 */
@SpringBootApplication
@EnableMSGTableScan
public class AliCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliCanalApplication.class, args);
    }
}

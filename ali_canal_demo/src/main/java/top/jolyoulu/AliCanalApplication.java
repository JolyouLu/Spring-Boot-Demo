package top.jolyoulu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import top.jolyoulu.protocol.Table;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:40
 * @Version 1.0
 */
@SpringBootApplication
public class AliCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliCanalApplication.class, args);
    }
}

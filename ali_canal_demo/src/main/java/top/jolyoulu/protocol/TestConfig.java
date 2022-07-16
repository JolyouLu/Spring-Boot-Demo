package top.jolyoulu.protocol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.jolyoulu.entity.TestEntity;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/6 1:33
 * @Version 1.0
 */
@Slf4j
@Component
public class TestConfig {

    @PostConstruct
    public void init() throws IOException {
        ClassLoader loader = getClass().getClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        log.info(loader.toString());
        log.info(systemClassLoader.toString());
        Enumeration<URL> resources =
                systemClassLoader.getResources("top/jolyoulu/entity");
        log.info("getResources"+Collections.list(resources).toString());
        Enumeration<URL> systemResources = ClassLoader.getSystemResources("top/jolyoulu/entity");
        log.info("getSystemResources"+Collections.list(systemResources).toString());
        InputStream inputStream = TestEntity.class.getResourceAsStream("/top/jolyoulu/entity");
        if (!Objects.isNull(inputStream)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            if (!reader.ready()){
                log.info("有数据没准备好"+reader.ready());
            }
            String str = null;
            while ((str = reader.readLine()) != null){
                log.info("getSystemResourceAsStream => "+str);
            }
        }else {
            log.info("inputStream为空");
        }
    }
}

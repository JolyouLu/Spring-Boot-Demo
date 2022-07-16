package top.jolyoulu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jolyoulu.entity.TestEntity;
import top.jolyoulu.pipline.defhandler.AbstractMessageHandlerContextAdapter;
import top.jolyoulu.pipline.DefaultMessagePipeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 1:39
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestHandlerController {

    @Autowired
    private DefaultMessagePipeline defaultMessagePipeline;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static String msg = "{\"data\":[{\t\t\"id\":\"2\",\t\t\"user_id\":\"666\",\t\t\"create_by\":\"bb\",\t\t\"create_time\":\"2022-05-25 13:14:55\",\t\t\"update_by\":\"\",\t\t\"update_time\":\"0000-00-00 00:00:00\",\t\t\"del_flag\":\"0\"\t},{\t\t\"id\":\"3\",\t\t\"user_id\":\"666\",\t\t\"create_by\":\"132\",\t\t\"create_time\":\"0000-00-00 00:00:00\",\t\t\"update_by\":\"\",\t\t\"update_time\":\"0000-00-00 00:00:00\",\t\t\"del_flag\":\"0\"\t}],\t\"database\":\"fenzhidao_product\",\t\"es\":1655191608000,\t\"id\":249544,\t\"isDdl\":false,\t\"mysqlType\":{\t\t\"id\":\"varchar(32)\",\t\t\"user_id\":\"varchar(32)\",\t\t\"create_by\":\"varchar(32)\",\t\t\"create_time\":\"datetime\",\t\t\"update_by\":\"varchar(32)\",\t\t\"update_time\":\"datetime\",\t\t\"del_flag\":\"tinyint(1) unsigned\"\t},\t\"old\":[{\t\t\"user_id\":\"cc\"\t},{\t\t\"user_id\":\"55\"\t}],\t\"pkNames\":[\"id\"],\t\"sql\":\"\",\t\"sqlType\":{\t\t\"id\":12,\t\t\"user_id\":12,\t\t\"create_by\":12,\t\t\"create_time\":93,\t\t\"update_by\":12,\t\t\"update_time\":93,\t\t\"del_flag\":-6\t},\t\"table\":\"fzd_a\",\t\"ts\":1655191608243,\t\"type\":\"UPDATE\"}";

    @GetMapping
    public void test() throws IOException {
        executorService.submit(() ->{
            AbstractMessageHandlerContextAdapter ctx = defaultMessagePipeline.getCtx();
            ctx.accept(ctx,msg);
        });
        ClassLoader loader = getClass().getClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        log.info(loader.toString());
        log.info(systemClassLoader.toString());
        Enumeration<URL> resources =
                systemClassLoader.getResources("top/jolyoulu/entity");
        log.info("getResources"+ Collections.list(resources).toString());
        Enumeration<URL> systemResources = ClassLoader.getSystemResources("top/jolyoulu/entity");
        log.info("getSystemResources"+Collections.list(systemResources).toString());
        InputStream inputStream = TestHandlerController.class.getResourceAsStream("/top/jolyoulu/entity");
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

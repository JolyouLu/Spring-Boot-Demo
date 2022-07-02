package top.jolyoulu.protocol;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 12:31
 * @Version 1.0
 */
@Slf4j
public class TableBeanFactory {

    private final Map<String,Class<?>> map = new ConcurrentHashMap<>();
    private String[] scanPackage;

    public TableBeanFactory(String... pkg) {
        this.scanPackage = pkg;
    }

    public void init(){
        try {
            for (String p : scanPackage) {
                if (StringUtils.isBlank(p)){
                    continue;
                }
                log.warn("扫描包 => {}",p);
                String replace = p.replace(".", "/");
                URL url = getClass().getClassLoader().getResource(replace);
                if (Objects.isNull(url)){
                    log.error("",new IllegalArgumentException("不存在的路径"));
                    continue;
                }
                File file = new File(url.toURI());
                if (!file.exists()){
                    continue;
                }
                File[] files = file.listFiles();
                if (Objects.isNull(files)){
                    continue;
                }
                for (File f : files) {
                    String name = f.getName();
                    String className = name.substring(0, name.indexOf("."));
                    Class<?> aClass = Class.forName(p + "." + className);
                    Table table = aClass.getAnnotation(Table.class);
                    String tableName = table.name();
                    Class<?> old = map.putIfAbsent(tableName, aClass);
                    if (old != null){
                        log.error("{} 类被 {} 类覆盖",old,aClass);
                    }
                }
            }
            log.warn("扫描完毕共装配{}个类",map.size());
        } catch (URISyntaxException | ClassNotFoundException e) {
            log.error("TableBeanFactory加载类失败",e);
        }
    }

    /**
     * 根据 Table注解 name获取Class对象
     * @param tableName
     * @return
     */
    public Class<?> getClass (String tableName){
        return map.getOrDefault(tableName,null);
    }
}

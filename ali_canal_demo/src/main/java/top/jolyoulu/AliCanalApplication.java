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
        //扫描
        scan("top.jolyoulu.entity");
    }

    public static Map<String,Class<?>> map = new Hashtable<>();

    private static void scan(String... pkg){
        try {
            for (String p : pkg) {
                String replace = p.replace(".", "/");
                URL url = ClassLoader.getSystemResource(replace);
                File file = new File(url.toURI());
                File[] files = file.listFiles();
                for (File f : files) {
                    String name = f.getName();
                    String className = name.substring(0, name.indexOf("."));
                    Class<?> aClass = Class.forName(p + "." + className);
                    Table table = aClass.getAnnotation(Table.class);
                    String tableName = table.name();
                    map.put(tableName,aClass);
                }
            }
            System.out.println(map);
        } catch (URISyntaxException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

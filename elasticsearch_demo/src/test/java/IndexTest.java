import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.jolyoulu.ElasticsearchDemoApplication;
import top.jolyoulu.entity.Product;

/**
 * @Author: JolyouLu
 * @Date: 2021/9/8 15:13
 * @Version 1.0
 */
@SpringBootTest(classes = ElasticsearchDemoApplication.class)
@RunWith(SpringRunner.class)
public class IndexTest {

    @Autowired
    private ElasticsearchRestTemplate esTemplate;

    //创建索引并增加映射配置
    @Test
    public void createIndex() {
        //创建索引，系统初始化会自动创建索引
        System.out.println("创建索引");
    }

    @Test
    public void deleteIndex() {
        System.out.println("删除索引" + esTemplate.deleteIndex(Product.class));
    }
}

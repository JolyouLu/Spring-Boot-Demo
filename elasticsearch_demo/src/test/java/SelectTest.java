import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jolyoulu.ElasticsearchDemoApplication;
import top.jolyoulu.dao.ProductDao;
import top.jolyoulu.entity.Product;

/**
 * @Author: JolyouLu
 * @Date: 2021/9/8 15:38
 * @Version 1.0
 */
@SpringBootTest(classes = ElasticsearchDemoApplication.class)
@RunWith(SpringRunner.class)
public class SelectTest {

    @Autowired
    private ProductDao productDao;

    //条件查询
    @Test
    public void temQuery(){
        TermQueryBuilder termQuery = QueryBuilders.termQuery("category", "手机");
        Iterable<Product> products = productDao.search(termQuery);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    //条件分页查询
    @Test
    public void temQueryPage(){
        int current = 0;
        int size = 5;
        //设置分页查询
        PageRequest pageRequest = PageRequest.of(current, size);

        TermQueryBuilder termQuery = QueryBuilders.termQuery("category", "手机");
        Iterable<Product> products = productDao.search(termQuery,pageRequest);
        for (Product product : products) {
            System.out.println(product);
        }
    }
}

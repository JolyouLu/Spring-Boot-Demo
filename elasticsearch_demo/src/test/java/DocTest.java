import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.jolyoulu.ElasticsearchDemoApplication;
import top.jolyoulu.dao.ProductDao;
import top.jolyoulu.entity.Product;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: JolyouLu
 * @Date: 2021/9/8 15:19
 * @Version 1.0
 */
@SpringBootTest(classes = ElasticsearchDemoApplication.class)
@RunWith(SpringRunner.class)
public class DocTest {

    @Autowired
    private ProductDao productDao;

    //新增文档
    @Test
    public void installDoc() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("华为手机");
        product.setCategory("手机");
        product.setPrice(2999.0);
        product.setImages("http://www.baidu.com");
        productDao.save(product);
    }

    //修改文档
    @Test
    public void update() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("小米手机");
        product.setCategory("手机");
        product.setPrice(2999.0);
        product.setImages("http://www.baidu.com");
        productDao.save(product);
    }

    //根据ID查询
    @Test
    public void findById() {
        Product product = productDao.findById(1L).get();
        System.out.println(product);
    }

    //查询所有数据
    @Test
    public void findAll() {
        Iterable<Product> products = productDao.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    //删除
    @Test
    public void delete() {
        Product product = new Product();
        product.setId(1L);
        productDao.delete(product);
    }

    //批量查询
    @Test
    public void installList() {
        Random random = new Random();
        ArrayList<Product> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(i + 1L);
            product.setTitle("华为手机");
            product.setCategory("手机");
            product.setPrice((double) random.nextInt(5000));
            product.setImages("http://www.baidu.com");
            list.add(product);
        }
        productDao.saveAll(list);
    }

    //分页查询
    @Test
    public void findPage() {
        //设置排序方式，与排序字段
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        int current = 0;
        int size = 5;
        //设置分页查询
        PageRequest pageRequest = PageRequest.of(current, size, sort);
        //分页查询
        Page<Product> productPage = productDao.findAll(pageRequest);
        for (Product product : productPage) {
            System.out.println(product);
        }
    }
}

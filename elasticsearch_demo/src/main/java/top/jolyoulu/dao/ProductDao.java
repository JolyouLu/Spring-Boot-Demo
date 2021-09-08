package top.jolyoulu.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import top.jolyoulu.entity.Product;

/**
 * @Author: JolyouLu
 * @Date: 2021/9/8 15:09
 * @Version 1.0
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}

package top.jolyoulu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: JolyouLu
 * @Date: 2021/9/8 15:02
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "product",shards = 3,replicas = 1)
public class Product {
    @Id
    private Long id;//商品唯一标识
    @Field(type = FieldType.Text)
    private String title;//商品名称
    @Field(type = FieldType.Keyword)
    private String category;//分类名
    @Field(type = FieldType.Double)
    private Double price;//商品价格
    @Field(type = FieldType.Keyword,index = false)
    private String images;//图片地址
}

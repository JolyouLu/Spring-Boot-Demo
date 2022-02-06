package top.jolyoulu.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: JolyouLu
 * @Date: 2021/11/3 20:10
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(value = DruidDataSourceProperties.class)
public class DruidDataSourceConfig {

    @Autowired
    private DruidDataSourceProperties druidDataSourceProperties;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidDataSourceProperties.getDriverClassName());
        dataSource.setUrl(druidDataSourceProperties.getJdbcUrl());
        dataSource.setUsername(druidDataSourceProperties.getUsername());
        dataSource.setPassword(druidDataSourceProperties.getPassword());
        dataSource.setFilters(druidDataSourceProperties.getFilters());
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        HashMap<String, String> params = new HashMap<>();
        params.put("loginUsername", "admin");
        params.put("loginPassword", "123456");
        bean.setInitParameters(params);
        return bean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        HashMap<String, String> params = new HashMap<>();
        params.put("exclusions", "*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(params);
        return filterRegistrationBean;

    }

}

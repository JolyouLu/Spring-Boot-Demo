package top.jolyoulu.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: JolyouLu
 * @Date: 2021/11/3 20:09
 * @Version 1.0
 */
@ConfigurationProperties(value = "spring.datasource.druid")
public class DruidDataSourceProperties {
    private String username;
    private String password;
    private String jdbcUrl;
    private String driverClassName;
    private String filters;

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}

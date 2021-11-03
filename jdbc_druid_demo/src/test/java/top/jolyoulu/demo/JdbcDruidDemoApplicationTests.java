package top.jolyoulu.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@SpringBootTest
class JdbcDruidDemoApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM course_1");
        System.out.println(list);
    }

}

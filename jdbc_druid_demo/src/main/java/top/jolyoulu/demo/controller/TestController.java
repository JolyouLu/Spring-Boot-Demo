package top.jolyoulu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JolyouLu
 * @Date: 2021/11/3 20:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    @ResponseBody
    public Object test(){
        return jdbcTemplate.queryForList("SELECT * FROM course_1");
    }
}

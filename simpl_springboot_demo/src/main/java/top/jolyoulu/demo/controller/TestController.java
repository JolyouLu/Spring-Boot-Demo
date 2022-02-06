package top.jolyoulu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jolyoulu.demo.Exceptions.MyException;

/**
 * @Author: JolyouLu
 * @Date: 2021/11/1 14:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private Person person;

    @GetMapping("/person")
    public Person getPerson() {
        return person;
    }

    @GetMapping("/errHandler")
    public void errHandler() {
        throw new MyException("程序执行出错");
    }
}

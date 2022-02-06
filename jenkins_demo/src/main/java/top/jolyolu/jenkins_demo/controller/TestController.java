package top.jolyolu.jenkins_demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JolyouLu
 * @Date: 2022/1/27 17:06
 * @Version 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        int a = 0/10;
        return "测试 测试 ~~~";
    }

    @GetMapping("/test2")
    public String test2(){
        return "测试 测试 Maven ~~~";
    }

    @GetMapping("/test3")
    public String test3(){
        return "测试 测试 pipelien dev ~~~";
    }

    @GetMapping("/test4")
    public String test4(){
        return "测试 测试 修改 修改 66666 ~~~";
    }

}

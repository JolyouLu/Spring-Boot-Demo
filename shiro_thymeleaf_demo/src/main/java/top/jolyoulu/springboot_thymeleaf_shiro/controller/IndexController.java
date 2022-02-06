package top.jolyoulu.springboot_thymeleaf_shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/19 18:10
 * @Version 1.0
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }
}

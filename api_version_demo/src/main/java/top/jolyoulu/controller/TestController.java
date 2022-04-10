package top.jolyoulu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jolyoulu.apiVersion.ApiVersion;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/10 20:00
 * @Version 1.0
 */
@RestController("/")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "v1.0.0";
    }

    @ApiVersion("1.1.0")
    @GetMapping("/test")
    public String test1_1_0(){
        return "v1.1.0";
    }

    @ApiVersion("1.2.0")
    @GetMapping("/test")
    public String test1_2_0(){
        return "v1.2.0";
    }

}

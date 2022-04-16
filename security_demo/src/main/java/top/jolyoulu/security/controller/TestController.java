package top.jolyoulu.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/17 1:33
 * @Version 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @PreAuthorize("hasAnyAuthority('update')")
    @GetMapping("/update")
    public String update(){
        return "update";
    }

    @PreAuthorize("hasAnyAuthority('delete')")
    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }
}

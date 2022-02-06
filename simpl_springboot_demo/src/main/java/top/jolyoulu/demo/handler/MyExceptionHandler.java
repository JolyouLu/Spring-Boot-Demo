package top.jolyoulu.demo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jolyoulu.demo.Exceptions.MyException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2021/11/2 14:28
 * @Version 1.0
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Map<String, Object> dealException(MyException e, HttpServletRequest request) {
        Map<String, Object> retInfo = new HashMap<>();
        retInfo.put("code", e.getCode());
        retInfo.put("msg", e.getMsg());
        return retInfo;
    }

}

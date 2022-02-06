package top.jolyoulu.demo.Exceptions;

/**
 * @Author: JolyouLu
 * @Date: 2021/11/2 14:35
 * @Version 1.0
 */
public class MyException extends RuntimeException {
    private Integer code;
    private String msg;

    public MyException(String message) {
        super(message);
        this.code = 500;
        this.msg = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

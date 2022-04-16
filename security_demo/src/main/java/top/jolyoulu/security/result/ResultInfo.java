package top.jolyoulu.security.result;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/16 18:08
 * @Version 1.0
 */
@Data
public class ResultInfo {

    private int code;
    private String msg;
    private Object data;
    private JSONObject error;

    public ResultInfo() {
    }

    public ResultInfo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(Object data) {
        this.code = 0;
        this.msg = "成功";
        this.data = data;
    }

    public static ResultInfo valueOf(Object data) {
        return new ResultInfo(0, "成功", data);
    }

    public static ResultInfo errorOf(int code, String msg) {
        return new ResultInfo(code, msg);
    }
}

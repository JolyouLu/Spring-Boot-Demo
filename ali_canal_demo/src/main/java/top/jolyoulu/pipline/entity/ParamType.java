package top.jolyoulu.pipline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: JolyouLu
 * @Date: 2022/7/2 11:22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class ParamType {
    private Class<?> type;

    public ParamType(Class<?> type) {
        this.type = type;
    }
}

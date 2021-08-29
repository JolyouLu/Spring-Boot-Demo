package top.jolyoulu.springboot_jsp_shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/15 23:09
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String id;
    private String name;
}

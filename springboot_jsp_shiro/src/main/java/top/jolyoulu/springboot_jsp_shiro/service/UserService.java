package top.jolyoulu.springboot_jsp_shiro.service;

import org.apache.ibatis.annotations.Param;
import top.jolyoulu.springboot_jsp_shiro.entity.Pers;
import top.jolyoulu.springboot_jsp_shiro.entity.User;

import java.util.List;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/14 23:42
 * @Version 1.0
 */
public interface UserService {

    void register(User user);

    User findByUserName(String username);

    User findRolesByUserName(String username);

    List<Pers> findPermsByRolesId(String roleId);

}

package top.jolyoulu.springboot_thymeleaf_shiro.dao;

import org.apache.ibatis.annotations.Mapper;
import top.jolyoulu.springboot_thymeleaf_shiro.entity.Pers;
import top.jolyoulu.springboot_thymeleaf_shiro.entity.User;

import java.util.List;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/14 23:32
 * @Version 1.0
 */
@Mapper
public interface UserDao {
    /**
     * 保存用户信息
     * @param user
     */
    void save(User user);

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 查询用户角色列表
     * @param username
     * @return
     */
    User findRolesByUserName(String username);

    /**
     * 查询权限列表
     * @param roleId
     * @return
     */
    List<Pers> findPermsByRolesId(String roleId);
}

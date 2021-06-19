package top.jolyoulu.springboot_thymeleaf_shiro.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jolyoulu.springboot_thymeleaf_shiro.dao.UserDao;
import top.jolyoulu.springboot_thymeleaf_shiro.entity.Pers;
import top.jolyoulu.springboot_thymeleaf_shiro.entity.User;
import top.jolyoulu.springboot_thymeleaf_shiro.service.UserService;
import top.jolyoulu.springboot_thymeleaf_shiro.utils.SaltUtils;

import java.util.List;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/14 23:43
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        //注册用户
        //生成一个8位的随机盐
        String salt = SaltUtils.getSalt(8);
        //将来随机盐保存到user中后期存入数据库
        user.setSalt(salt);
        //对用户明文的密码解析md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findRolesByUserName(String username) {
        return userDao.findRolesByUserName(username);
    }

    @Override
    public List<Pers> findPermsByRolesId(String roleId) {
        return userDao.findPermsByRolesId(roleId);
    }
}

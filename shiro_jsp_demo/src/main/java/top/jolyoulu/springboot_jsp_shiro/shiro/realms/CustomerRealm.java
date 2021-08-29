package top.jolyoulu.springboot_jsp_shiro.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import top.jolyoulu.springboot_jsp_shiro.entity.Pers;
import top.jolyoulu.springboot_jsp_shiro.entity.User;
import top.jolyoulu.springboot_jsp_shiro.service.UserService;

import java.util.List;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/14 22:10
 * @Version 1.0
 * 自定义Realm
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("获取角色与权限信息");
        //获取主身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        //获取Roles信息
        User rolesByUserName = userService.findRolesByUserName(primaryPrincipal);
        //根据主身份信息获取角色与权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (!rolesByUserName.getRoleList().isEmpty()){
            rolesByUserName.getRoleList().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                //通过角色id获取权限信息
                List<Pers> permsByRolesId = userService.findPermsByRolesId(role.getId());
                if (!permsByRolesId.isEmpty()){
                    permsByRolesId.forEach(pers -> {
                        simpleAuthorizationInfo.addStringPermission(pers.getName());
                    });
                }
            });
        }
        return simpleAuthorizationInfo;
    }

    //用户认证时会调用该方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取到用户的身份信息(用户名)
        String principal = (String) token.getPrincipal();
        //根据用户身份信息去数据库查询用户密码与盐
        User user = userService.findByUserName(principal);
        if (user != null){
            //封装到SimpleAuthenticationInfo返回
            return new SimpleAuthenticationInfo(
                    principal,
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),
                    this.getName());
        }
        return null;
    }
}

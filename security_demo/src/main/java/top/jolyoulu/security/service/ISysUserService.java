package top.jolyoulu.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.jolyoulu.security.entity.SysUser;
import top.jolyoulu.security.result.ResultInfo;


/**
 * @Author: JolyouLu
 * @Date: 2022/4/16 20:59
 * @Version 1.0
 */
public interface ISysUserService extends IService<SysUser> {
    SysUser getByUsername(String username);
}

package top.jolyoulu.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jolyoulu.security.entity.SysUser;
import top.jolyoulu.security.mapper.SysUserMapper;
import top.jolyoulu.security.result.ResultInfo;
import top.jolyoulu.security.service.ISysUserService;


/**
 * @Author: JolyouLu
 * @Date: 2022/4/16 20:59
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUsername(String username) {
        LambdaQueryWrapper<SysUser> ew = new LambdaQueryWrapper<>();
        ew.eq(SysUser::getUsername,username);
        return sysUserMapper.selectOne(ew);
    }
}

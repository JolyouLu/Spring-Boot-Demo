package top.jolyoulu.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/16 20:57
 * @Version 1.0
 */
@Data
@ToString
public class SysUser {

    /**
     * 主键 primary key
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 名称
     */
    private String nickName;

    /**
     * 名称
     */
    private String username;

    /**
     * 用户密码 md5加密随机盐
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 备注
     */
    private String remark;

}

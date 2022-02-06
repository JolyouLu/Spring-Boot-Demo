create table pers
(
    id   int auto_increment comment '主键'
        primary key,
    name varchar(64) null comment '权限标识',
    url  varchar(256) null comment '资源路径'
) comment '权限表';

create table roles
(
    id   int auto_increment comment '主键'
        primary key,
    name varchar(64) null comment '角色名称'
) comment '角色表';

create table roles_pers
(
    id       int auto_increment
        primary key,
    roles_id int null comment '角色id',
    pers_id  int null comment '权限id'
) comment '角色与权限关系表';

create table users
(
    id       int auto_increment comment '主键'
        primary key,
    password varchar(64) null comment '用户名',
    username varchar(64) null comment '密码',
    salt     varchar(64) null comment '盐'
) comment '用户表';

create table users_roles
(
    id       int auto_increment comment '主键'
        primary key,
    users_id int null,
    roles_id int null
) comment '用户与角色关联表';

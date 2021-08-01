
create table grape
(
    uid          varchar(32)                   not null comment '用户唯一标识'
        primary key,
    name         varchar(19)  default 'orange' null comment '名称',
    role_id      tinyint      default 1        null comment '角色id',
    password     varchar(100) default '0'      null comment '密码',
    email        varchar(50)                   null comment '邮箱',
    phone        varchar(11)                   null comment '手机',
    nick_name    varchar(50)                   null comment '昵称',
    sign_up_time varchar(50)                   null comment '注册时间'
)
    comment '用户信息表';

create index index_name
    on grape (phone, name);
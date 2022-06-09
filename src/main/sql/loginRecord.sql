create TABLE `loginHistory`(
   `id` int(11)  PRIMARY key  not null auto_increment comment "主键",
   `username` int(11) not null comment "请求登录用户名",
    `status` tinyint default 0 comment "请求状态",
   `create_time` TIMESTAMP not null DEFAULT  CURRENT_TIMESTAMP comment "创建时间",
   key username_index(username)
)ENGINE=Innodb CHARSET=utf8 comment "登录历史表";
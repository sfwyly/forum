create TABLE `user`(
  `id` int(11)  PRIMARY key not null auto_increment comment "主键",
  `username` varchar(50) not null comment "用户名邮箱",
  `password` varchar(50) not null comment "密码",
  `create_time` TIMESTAMP not null DEFAULT  CURRENT_TIMESTAMP comment "创建时间",
  `status` tinyint not null DEFAULT 1 comment "状态",
  key username_index(username)
 )ENGINE=Innodb CHARSET=utf8 comment "用户表";
create TABLE `user_role`(
   `id` int(11) PRIMARY key  not null auto_increment comment "主键",
   `user_id` int(11) not null  comment "用户主键",
    `role_id` int(11) not null  comment "角色主键",
    `status` int(2) not null DEFAULT 1 comment "角色标志 1正常 0禁止使用",
   `create_time` TIMESTAMP not null DEFAULT  CURRENT_TIMESTAMP comment "创建时间",
   key user_id_index(user_id),
   key role_id_index(role_id)
)ENGINE=Innodb CHARSET=utf8 comment "用户角色表";
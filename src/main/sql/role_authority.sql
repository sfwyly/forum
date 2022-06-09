create TABLE `role_authority`(
   `id` int(11) PRIMARY key  not null auto_increment comment "主键",
   `role_id` int(11) not null  comment "用户主键",
    `authority_id` int(11) not null  comment "角色主键",
    `status` int(2) not null DEFAULT 1 comment "角色标志 1正常 0禁止使用",
   `create_time` TIMESTAMP not null DEFAULT  CURRENT_TIMESTAMP comment "创建时间",
   key authority_id_index(authority_id),
   key role_id_index(role_id)
)ENGINE=Innodb charset=utf8 comment="用户角色表";
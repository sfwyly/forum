create TABLE `role`(
   `id` int(11)  PRIMARY key  not null auto_increment comment "主键",
   `status` int(2) not null DEFAULT 1 comment "角色标志 1正常 0禁止使用",
   `create_time` TIMESTAMP not null DEFAULT  CURRENT_TIMESTAMP comment "创建时间",
   `description` LONGTEXT not null comment "描述"
)ENGINE=Innodb CHARSET=utf8 comment "角色表";
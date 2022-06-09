create table articleRecord(
-- `id` int not null PRIMARY KEY auto_increment comment '主键id',
`user_id` int not null comment '用户id',
`article_id` int not null comment '文章id',
`state` tinyint not null comment '0浏览或者1点赞 2收藏',
`create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间',
primary key(user_id,article_id,state),
key idx_user_id(user_id),
key idx_article_id(article_id),
key idx_state(state)
)ENGINE=InnoDB CHARSET=utf8 COMMENT='文章记录表';
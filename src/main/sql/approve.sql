--点赞表
create table approve(
`user_id` int not null comment '用户id',
`article_id` int not null comment '文章id',
`message_id` int not null comment '留言id',
`create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间',
primary key(user_id,article_id,message_id),
key idx_user_id(user_id),
key idx_article_id(article_id),
key idx_message_id(message_id)
)ENGINE=InnoDB CHARSET=utf8 COMMENT='点赞记录表';
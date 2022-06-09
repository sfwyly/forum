CREATE  TABLE  message(
`id` int not NULL PRIMARY key auto_increment comment '主键',
`user_id` int  not null comment '用户id',
`article_id` int  not null comment '文章id',
`message_content` longtext not null comment '留言内容',
`message_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP comment '留言时间',
`approve_number` int  not null DEFAULT 0 comment '点赞数',
`reply_number` int  not null DEFAULT 0 comment '回复数',
`privacy` tinyint not null default 0 comment '隐私标志',
key idx_user_id(user_id),
key idx_article_id(article_id)
)ENGINE=InnoDB CHARSET=utf8 COMMENT='留言表';

--留言点赞,或者取消点赞
delimiter $$
--p_operate_num >0点赞 ，否则取消点赞 p_result 1成功 也是用articleRecord表 state=3为留言点赞
create procedure `blogs`.`approve_message`(in p_article_id int , in p_user_id int ,in p_message_id int , in p_operate_num int , out p_result int)
BEGIN
  declare operate_count int DEFAULT  0;
  start TRANSACTION ;
  set p_result = 0;
  if(p_operate_num > 0) THEN
    insert ignore into approve(user_id , article_id , message_id) values(p_user_id , p_article_id ,p_message_id);
  else
    delete from approve where user_id = p_user_id and article_id = p_article_id and message_id = p_message_id;
  end if;
  select row_count() into operate_count;
  if(operate_count <= 0) THEN
    ROLLBACK ;
    set p_result = 0;
  else
    if(p_operate_num > 0) THEN
      update message set approve_number = approve_number + 1 where id = p_message_id;
    else
      update message set approve_number = approve_number - 1 where id = p_message_id;
    end if;
    select row_count() into operate_count;
    if(operate_count <= 0) THEN
      ROLLBACK ;
      set p_result = -1;
    ELSE
      COMMIT ;
      set p_result = 1;
    end if;
  end if;
END ;
$$
delimiter ;

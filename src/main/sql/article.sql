create TABLE `article`(
   `id` int(11)  PRIMARY key  not null auto_increment comment "主键",
   `user_id` int(11) not null comment "用户id",
    `category_id` int(11) not null comment "文章分类id",
    `title` varchar(200) not null  comment "标题",
    `content` LONGTEXT not null comment "文章内容",
   `status` int(2) not null DEFAULT 1 comment "文章标志 1正常 0禁止使用",
   `create_time` TIMESTAMP not null DEFAULT  CURRENT_TIMESTAMP comment "创建时间",
   `view_number` int not null DEFAULT 0 comment '浏览次数',
    `approve_number` int not null DEFAULT  0 comment '点赞数',
    `collection_number` int not null DEFAULT 0 comment '收藏数',
    key category_id_index(category_id),
    key user_id_index(user_id)
)ENGINE=Innodb CHARSET=utf8 comment "文章表";

delimiter $$
--定义存储过程 传递一个正数就是1点赞/2收藏 负数取消1点赞/2收藏 flag 1插入 2 删除
create procedure `forum`.`execute_record`(in p_article_id int,in p_user_id int,in p_operate_num int,in p_state int,out p_result int,out p_flag int)
BEGIN
  declare update_count int DEFAULT 0;
  start TRANSACTION ;
  set p_flag=0;
  case p_state
    when 1 THEN
      if(p_operate_num>0) then
        update article set approve_number = approve_number + 1 where id = p_article_id;
      ELSE
        update article set approve_number = approve_number - 1 where id = p_article_id and  approve_number > 0;
      end if;
    when 2 THEN
      if(p_operate_num>0) then
        update article set collection_number = collection_number + 1 where id = p_article_id;
      ELSE
        update article set collection_number = collection_number - 1 where id = p_article_id and  collection_number > 0;
      end if;
  end case ;
  select row_count() into update_count;
  if(update_count = 0) THEN
    ROLLBACK ;
    set p_result=-1;
  elseif(update_count < 0) THEN
    ROLLBACK ;
    set p_result=-2;
  ELSE
    if(p_operate_num > 0) then
      insert ignore into articleRecord(user_id,article_id,state) values(p_user_id,p_article_id,p_state);
      set p_flag=1;
    ELSE
      delete from articleRecord where user_id = p_user_id and article_id = p_article_id and state=p_state;
      set p_flag=2;
    end if ;
  end IF;
  select row_count() into update_count;
  IF(update_count = 0) THEN
    ROLLBACK ;
    set p_result=0;
  ELSEIF (update_count < 0) THEN
    ROLLBACK ;
    set p_result=-2;
  ELSE
    COMMIT ;
    set p_result=1;
  end IF ;
END ;
$$

delimiter ;

delimiter $$
--定义存储过程，删除文章，同时删除该文章的记录
create procedure `blogs`.`delete_article` (in p_article_id int,out p_result int)
BEGIN
  declare delete_count int DEFAULT 0;
  start TRANSACTION ;
  delete from article where id=p_article_id;
  select row_count() into delete_count;
  if(delete_count <= 0) THEN
    ROLLBACK ;
    set p_result=0;
  else
    delete from articleRecord where article_id=p_article_id;
    commit ;
    set p_result =1;
  end if;

END
$$
delimiter ;
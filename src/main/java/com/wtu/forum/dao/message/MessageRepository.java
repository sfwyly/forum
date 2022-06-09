package com.wtu.forum.dao.message;

import com.wtu.forum.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 留言消息持久化类
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Integer>{
    /**
     * 查询留言列表,如果不想查询某参数必须将参数置位null，因为数据库确保了关键字不为空
     * @param id
     * @param userId
     * @param articleId
     * @return
     */
    List<Message> findAllByIdOrUserIdOrArticleIdOrPrivacy(Integer id,Integer userId,Integer articleId,Short privacy);



}

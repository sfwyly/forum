package com.wtu.forum.service.messageservice;


import com.wtu.forum.dto.RequestResult;
import com.wtu.forum.entity.Approve;
import com.wtu.forum.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {
    /**
     * 留言
     * @param articleId 文章id
     * @param messageContent 留言内容
     * @return
     */
    RequestResult leaveMessage(Integer articleId, String messageContent, String privacy);

    /**
     * 查询留言信息 参数都可为null
     * @param id 留言记录id
     * @param userId 用户id
     * @param articleId 文章id
     * @return
     */
    List<Message> queryMessage(Integer id, Integer userId, Integer articleId, Short privacy);

    /**
     * 删除留言id
     * @param messageId
     * @return
     */
    RequestResult deleteMessage(Integer messageId);

    /**
     * 文章留言点赞
     * @param articleId 文章id
     * @param messageId 留言id
     * @param operateFlag 操作数 >0留言点赞 <=0 取消留言点赞
     * @return
     */
    RequestResult approveMessage(Integer articleId, Integer messageId, Integer operateFlag);

    /**
     * 查询点赞
     * @param map
     * @return
     */
    List<Approve> queryApprove(Integer userId, Integer articleId, Integer messageId);
}

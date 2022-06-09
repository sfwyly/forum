package com.wtu.forum.service.messageservice.impl;

import com.wtu.forum.dao.message.MessageRepository;
import com.wtu.forum.dto.RequestResult;
import com.wtu.forum.entity.Article;
import com.wtu.forum.entity.Message;
import com.wtu.forum.entity.User;
import com.wtu.forum.enums.EmRequestError;
import com.wtu.forum.service.article.ArticleService;
import com.wtu.forum.service.common.safe.SafeCheckpoint;
import com.wtu.forum.service.messageservice.MessageService;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SafeCheckpoint safeCheckpoint;

    @Autowired
    private HttpSession httpSession;

    @Override
    public List<Message> queryMessage(Integer id, Integer userId, Integer articleId , Short privacy) {
        return messageRepository.findAllByIdOrUserIdOrArticleIdOrPrivacy(id, userId, articleId, privacy);
    }

    @Override
    public RequestResult leaveMessage(Integer articleId, String messageContent, String privacy) {

        if(!safeCheckpoint.contentCheck(messageContent)){//留言内容不合法
            return new RequestResult(null,false, EmRequestError.MESSAGE_INFORMATION_INVALIDATION);
        }else if(articleId==null){//没有留言文章
            return new RequestResult(null,false,EmRequestError.MESSAGE_NOT_ARTICLE);
        }

        //防止xss攻击
        messageContent=Jsoup.clean(messageContent, Whitelist.basic());
        if(!safeCheckpoint.contentCheck(messageContent)){//留言内容不合法
            return new RequestResult(null,false,EmRequestError.MESSAGE_INFORMATION_INVALIDATION);
        }

        //检查留言文章是否存在
        List<Article> articleList=articleService.queryArticle(articleId,null,null);
        if(articleList==null||articleList.size()<=0){//不存在留言的文章
            return new RequestResult(null,false,EmRequestError.MESSAGE_NOT_ARTICLE);
        }

        User user=(User)httpSession.getAttribute("user");
        Message message=new Message();
        message.setUserId(user.getId());
        message.setArticleId(articleId);
        message.setMessageContent(messageContent);
        if("privacy".equals(privacy)){//私密留言
            message.setPrivacy(Short.decode("1"));
        }else{
            message.setPrivacy(Short.decode("0"));
        }
        Message result = messageRepository.save(message);
        if(message ==null){//信息存储失败
            return new RequestResult(null,false,EmRequestError.MESSAGE_INFORMATION_STORAGE_FAILTURE);
        }
        return new RequestResult(null,true,EmRequestError.MESSAGE_STORAGE_SUCCESS);//留言存储成功
    }

    public RequestResult deleteMessage(Integer messageId){
        if(messageId ==null){
            return new RequestResult(null,false,EmRequestError.MESSAGE_NOT_EXISTS);//留言不存在
        }
        messageRepository.deleteById(messageId);
//        if(result <= 0){
//            return new ResponseString(false,"内部错误");
//        }
        //这里要是删除不成功怎么办
        return new RequestResult(null,true,EmRequestError.MESSAGE_DELETE_SUCCESS);
    }

    /**
     * 文章留言点赞，存储approve表
     * @param articeId
     * @param messageId 留言id
     * @param operateFlag 操作数 >0留言点赞 <=0 取消留言点赞
     * @return
     */
    public RequestResult approveMessage(Integer articeId , Integer messageId , Integer operateFlag){

        User user =(User) httpSession.getAttribute("user");
        //注意！！！这里由operateFlag控制点赞或者取消点赞，完全有前端控制，后端只验证
        if(operateFlag>0){//点赞

        }
        Map<String , Object> map =new HashMap<>();
        map.put("articleId",articeId);
        map.put("messageId",messageId);
        map.put("userId",user.getId());
        String str = "";
        if(operateFlag >0){
            map.put("operateFlag",1);
        }else{
            map.put("operateFlag",-1);
            str +="取消";
        }
        map.put("result",null);
        messageDao.approve(map);//执行
        int result = (int)map.get("result");

        if(result > 0){
            return new ResponseString(true ,str+"点赞成功");
        }
        return new ResponseString(true ,str+"点赞失败");
    }

    public List<Approve> queryApprove(Integer userId , Integer articleId , Integer messageId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("articleId",articleId);
        map.put("messageId",messageId);
        return messageDao.queryApprove(map);
    }
}

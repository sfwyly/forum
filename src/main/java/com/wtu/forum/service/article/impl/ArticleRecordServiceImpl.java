package com.wtu.forum.service.article.impl;

import com.wtu.dao.articlerecord.ArticleRecordDao;
import com.wtu.entity.ArticleRecord;
import com.wtu.forum.dao.articlerecord.ArticleRecordRepository;
import com.wtu.forum.entity.ArticleRecord;
import com.wtu.forum.service.article.ArticleRecordService;
import com.wtu.service.articleservice.ArticleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleRecordServiceImpl implements ArticleRecordService{

    @Autowired
    private ArticleRecordRepository articleRecordRepository;

    @Autowired
    private HttpSession httpSession;

    /**
     * 查询文章记录
     * @param userId
     * @param articleId 文章id
     * @param state 0 浏览记录 1 点赞 2 收藏
     * @return
     */
    @Override
    public List<ArticleRecord> queryArticleRecord(Integer userId, Integer articleId, Short state) {
       return articleRecordRepository.queryAllByUserIdOrArticleIdOrState(userId,articleId,state);
    }
}

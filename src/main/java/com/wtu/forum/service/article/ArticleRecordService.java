package com.wtu.forum.service.article;

import com.wtu.forum.entity.ArticleRecord;

import java.util.List;

public interface ArticleRecordService {
    /**
     *
     * @param articleId 文章id
     * @param state 0 浏览记录 1 点赞 2 收藏
     * @return
     */
    List<ArticleRecord> queryArticleRecord(Integer articleId, Integer userId, Short state);

}

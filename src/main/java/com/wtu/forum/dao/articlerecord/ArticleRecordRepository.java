package com.wtu.forum.dao.articlerecord;

import com.wtu.forum.entity.ArticleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName ArticleRecordRepository
 * @Description 文章记录操作
 * @Author 逝风无言
 * @Data 2019/10/22 20:25
 * @Version 1.0
 **/
public interface ArticleRecordRepository extends JpaRepository<ArticleRecord,Integer>{

    /**
     * 查询
     * @param userId
     * @param articleId
     * @return
     */
    public List<ArticleRecord> queryAllByUserIdOrArticleIdOrState(Integer userId,Integer articleId,Short state);

    public int


}

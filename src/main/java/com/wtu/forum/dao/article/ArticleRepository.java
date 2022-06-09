package com.wtu.forum.dao.article;

import com.wtu.forum.entity.Article;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName ArticleRepository
 * @Description 文章操作
 * @Author 逝风无言
 * @Data 2019/10/20 19:58
 * @Version 1.0
 **/
@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    /**
     * 查询所有文章
     * @return
     */
    List<Article> findAll();

    /**
     * 更具需求查询文章列表
     * @param id
     * @param userId
     * @param categoryId
     * @return
     */
   List<Article> findAllByIdOrUserIdOrCategoryId(Integer id,Integer userId,Integer categoryId);

    /**
     * 更新文章浏览数
     * @param articleId
     * @return
     */
    //@Modifying
    //@Query('update article set) IF(:articleId not null,"view_number = view_number +1" ,"1=1")'
    int updateArticleViewedNumber(@Param("articleId") Integer articleId);


}

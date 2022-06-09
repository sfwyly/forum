package com.wtu.forum.service.article;

import com.wtu.forum.dto.RequestResult;
import com.wtu.forum.entity.Article;

import java.util.List;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/20 20:07
 * @Version 1.0
 **/
public interface ArticleService {


    public RequestResult saveArticle(String content, Integer articleId,Integer categoryId);

    public RequestResult<List<Article>> queryAllArticleList();//查询所有的文章列表

    public List<Article> queryArticle(Integer articleId,Integer userId,Integer categoryId);//查询文章

    public RequestResult<Article> readArticleContent(Integer articeId);//查询文章内容（作者）

    public RequestResult<Article> showArticle(Integer articleId);//显示文章（访问者）

    public RequestResult operateArticle(Integer articleId,Integer state ,Integer operateFlag);//点赞文章


}

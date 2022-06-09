package com.wtu.forum.service.article.impl;

import com.wtu.forum.dao.article.ArticleRepository;
import com.wtu.forum.dao.articlerecord.ArticleRecordRepository;
import com.wtu.forum.dao.category.CategoryRepository;
import com.wtu.forum.dto.RequestResult;
import com.wtu.forum.entity.Article;
import com.wtu.forum.entity.ArticleRecord;
import com.wtu.forum.entity.User;
import com.wtu.forum.enums.CommonError;
import com.wtu.forum.enums.EmRequestError;
import com.wtu.forum.service.article.ArticleRecordService;
import com.wtu.forum.service.common.safe.SafeCheckpoint;
import com.wtu.forum.service.article.ArticleService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/20 20:08
 * @Version 1.0
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRecordRepository articleRecordRepository;


    /**
     * 保存文章 整型在进入controller之前已经全部进行了参数校验
     * @param content
     * @param articleId 也作为一种标志，如果更新文章就会传递该参数,
     * @param categoryId 分类标志
     * @return
     */
    @Override
    public RequestResult saveArticle(String content, Integer articleId,Integer categoryId) {
        User user = (User)httpSession.getAttribute("user");//拦截器已拦截，这里比存在用户

        if(!SafeCheckpoint.contentCheck(content)){
            return new RequestResult(null,false,EmRequestError.ARTICLE_CONTENT_INVAILDATION);
        }
        content = Jsoup.clean(content, Whitelist.basic());//清除白名单
        if(!SafeCheckpoint.contentCheck(content)){
            return new RequestResult(null,false,EmRequestError.ARTICLE_CONTENT_INVAILDATION);
        }

        if(articleId !=null){//更新
            Article article  = articleRepository.findById(articleId).get();
            if(article ==null || article.getUserId().intValue()!=user.getId().intValue()){
                return new RequestResult(null,false, EmRequestError.NOT_CURRENT_USER_ERROR);
            }
            if(categoryId!=null){
                if(categoryRepository.findById(categoryId).get()!=null){//查询分类存在
                    article.setCategoryId(categoryId);
                }
            }
            articleRepository.save(article);
        }else{//插入
            Article article = new Article();
            article.setContent(content);
            if(categoryId!=null){
                if(categoryRepository.findById(categoryId).get()!=null){
                    article.setCategoryId(categoryId);
                }
            }else{
                article.setCategoryId(1);//默认为第一个
            }
            article.setUserId(user.getId());
            articleRepository.save(article);
        }
        return new RequestResult(null,false,EmRequestError.ARTICLE_SAVE_SUCCESS);
    }

    /**
     * 查询所有文章信息
     * @return
     */
    @Override
    public RequestResult<List<Article>> queryAllArticleList() {
        List<Article> articleList = articleRepository.findAll();
        return new RequestResult<>(articleList,true,EmRequestError.ARTICLE_REQUEST_SUCCESS);
    }

    /**
     * 查询某一篇文章
     * @param articleId
     * @param userId
     * @param categoryId
     * @return
     */
    @Override
    public List<Article> queryArticle(Integer articleId, Integer userId, Integer categoryId) {
        return articleRepository.findAllByIdOrUserIdOrCategoryId(articleId,userId,categoryId);
    }

    /**
     * 文章信息查询 (作者编辑时读取自己的文章进行修改) 先进行用户登录校验
     * @param articleId
     * @return (文章id 分类id ) 因为前端需要获取所有的种类信息，所以通过后台直接返回种类id，前端就能得到信息
     */
    @Override
    public RequestResult<Article> readArticleContent(Integer articleId){
        User user = (User)httpSession.getAttribute("user");

        Article article = articleRepository.findById(articleId).get();//查询文章信息
        if(article == null ||user.getId().intValue()!=article.getUserId().intValue()) {//文章不存在或者该文章非当前用户所写
            return new RequestResult(null, false, EmRequestError.NOT_CURRENT_USER_ERROR);
        }
        //对非前端信息进行null处理（按道理是应该专门做个model层对前端显示信息进行存储的）
        article.setUserId(null);
        return new RequestResult<Article>(article,true, EmRequestError.ARTICLE_REQUEST_SUCCESS);//返回的时带格式字符
    }

    /**
     * 文章显示 （和上面那个先这么弄着）（坑定是有点冗余）
     * @param articleId
     * @return
     */
    @Override
    public RequestResult<Article> showArticle(Integer articleId) {

        Article article = articleRepository.findById(articleId).get();
        if(article==null){
            return new RequestResult(null, false, EmRequestError.NOT_CURRENT_USER_ERROR);
        }
        User user = (User) httpSession.getAttribute("user");
        if(user !=null){//当前用户存在就进行记录
            ArticleRecord articleRecord = new ArticleRecord();
            articleRecord.setArticleId(articleId);
            articleRecord.setUserId(user.getId());
            articleRecord.setState(Short.decode("0"));//0:浏览 1：点赞 2：收藏
            articleRecordRepository.save(articleRecord);
        }
        //对非前端信息进行null处理（按道理是应该专门做个model层对前端显示信息进行存储的）
        article.setUserId(null);
        return new RequestResult<Article>(article,true, EmRequestError.ARTICLE_REQUEST_SUCCESS);//返回的时带格式字符
    }

    /**
     * 点赞文章，存储文章操作记录表，这里用存储过程
     * @param articleId
     * @return
     */
    @Override
    public RequestResult operateArticle(Integer articleId, Integer state, Integer operateFlag) {
        User user =(User) httpSession.getAttribute("user");


        return null;
    }
}

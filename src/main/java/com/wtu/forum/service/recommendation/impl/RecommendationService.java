package com.wtu.forum.service.recommendation.impl;

import com.wtu.entity.Article;
import com.wtu.entity.ArticleRecord;
import com.wtu.entity.ArticleRecordCollection;
import com.wtu.entity.User;
import com.wtu.service.articleservice.ArticleRecordService;
import com.wtu.service.articleservice.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
/**
 * 基于协同过滤算法的文章推荐服务 ，必须登录并浏览文章后才能进行服务
 */
public class RecommendationService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ArticleRecordService articleRecordService;

    @Autowired
    private ArticleService articleService;


    public void setValue(ApplicationContext context , HttpSession httpSession){
        this.httpSession=httpSession;
        this.articleRecordService = (ArticleRecordService) context.getBean("articleRecordServiceImpl");
        this.articleService = (ArticleService) context.getBean("articleServiceImpl");
    }
    /**
     * 进行用户个性化推荐文章 ,协同过滤算法
     * @return
     */
    public List<Article> recommend(){

        //只会对登录用户进行个性化推荐
        User current_user =(User) httpSession.getAttribute("user");//获取当前用户，必须存在，会用拦截器进行拦截
        if(current_user ==null){//未登录用户不进行推荐
            return null;
        }
        Integer current_userId = current_user.getId();
        //获取当前用户的浏览列表
        List<ArticleRecord> current_user_articleRecordList = articleRecordService.queryArticleRecord(null , current_userId , 0);
        List<ArticleRecord> current_list =  new ArrayList<>(current_user_articleRecordList);
        if(current_user_articleRecordList ==null ||current_user_articleRecordList.size() <=0){//未浏览用户不进行推荐
            return null;
        }
        Map<Integer , Double> user_weight = new HashMap<>();//key userId ; value 相似率
        List<ArticleRecordCollection> articleRecordCollectionList = new ArrayList<>();//封装list差集和权重

        boolean commonFlag = false;

        for(ArticleRecord current_ar : current_user_articleRecordList){//对当前用户所有浏览文章进行遍历
            //获取有所有相同文章id的用户,包含当前用户
            List<ArticleRecord> all_user_articleRecordList = articleRecordService.queryArticleRecord(current_ar.getArticleId() ,null ,0);
            for (ArticleRecord all_user_ar : all_user_articleRecordList){
                if(all_user_ar.getUserId().intValue() == current_userId.intValue() || user_weight.get(all_user_ar.getUserId())!=null){//若为当前用户或者已经查询过该用户的权重，直接进入下次循环
                    continue ;
                }
                commonFlag =true;
                //非当前用户
                //查询拥有相同文章的用户文章集合
                List<ArticleRecord> common_article_user_articleRecordList = articleRecordService.queryArticleRecord(null ,all_user_ar.getUserId() ,0);
                //获取当前用户与该用户的相关率,这里的Map里面一定不存在key为all_user_ar.getUserId()的键值对
                double userWeight =getCommonArticleId(common_article_user_articleRecordList,current_list);
                user_weight.put(all_user_ar.getUserId(),userWeight);

                ArticleRecordCollection articleRecordCollection = new ArticleRecordCollection();
                articleRecordCollection.setUserId(all_user_ar.getUserId());
                articleRecordCollection.setUserWeight(userWeight);
                articleRecordCollection.setArticleRecordList(getDiffArticleRecord(common_article_user_articleRecordList,current_list));
                articleRecordCollectionList.add(articleRecordCollection);
            }

        }

        return getArticleList(getArticleIdList(articleRecordCollectionList)) ;
    }

    /**
     * 获取两个list相同articleId的个数
     * @param articleRecordList1
     * @param articleRecordList2
     * @return 两个列表的相关度
     */
    public double getCommonArticleId(List<ArticleRecord> articleRecordList1 , List<ArticleRecord> articleRecordList2){

        if(articleRecordList1 ==null ||articleRecordList2 ==null){//有一个为空则相关度为0
            return 0;
        }
        int count = 0;
        //对两个list按照articleId排序
        Collections.sort(articleRecordList1);
        Collections.sort(articleRecordList2);
        int listSize1 = articleRecordList1.size(),listSize2 = articleRecordList2.size();

        for(int i=0,j=0 ;i <listSize1 && j<listSize2 ; ){//求取相同数目
            if(articleRecordList1.get(i).getArticleId() < articleRecordList2.get(j).getArticleId()){
                ++i;
                continue;
            }else if(articleRecordList1.get(i).getArticleId() > articleRecordList2.get(j).getArticleId()){
                ++j;
                continue;
            }else{
                ++i;
                ++j;
                ++count;
                continue;
            }
        }

        if(listSize1 <= 0 || listSize2 <=0){//两个列表有一个为空
            return 0;
        }
        return (double)count/Math.sqrt((double)(listSize1*listSize2));//返回相关度
    }

    /**
     * 获取差集
     * @param articleRecordList1
     * @param articleRecordList2
     * @return
     */
    public List<ArticleRecord> getDiffArticleRecord(List<ArticleRecord> articleRecordList1 , List<ArticleRecord> articleRecordList2){

        if(articleRecordList1 == null ||articleRecordList1.size()<=0){//list1没有实体元素
            return null;
        }else if(articleRecordList2 ==null || articleRecordList2.size() <=0){
            return articleRecordList1;
        }
        List<ArticleRecord> articleRecordList = new ArrayList<>();

        //先排序
        Collections.sort(articleRecordList1);
        Collections.sort(articleRecordList2);

        int listSize1 = articleRecordList1.size(),listSize2 = articleRecordList2.size();
        int i=0,j=0;
        for(;i <listSize1 && j<listSize2 ; ){
            ArticleRecord articleRecordi = articleRecordList1.get(i) ,articleRecordj = articleRecordList2.get(j);
            if(articleRecordi.getArticleId() < articleRecordj.getArticleId() ){//因为是按顺序排列的
                articleRecordList.add(articleRecordi);
                ++i;
                continue;
            }else if(articleRecordi.getArticleId() > articleRecordj.getArticleId()){
                ++j;
                continue;
            }else{
                ++i;
                ++j;
                continue;
            }
        }
        while(i < listSize1){//list2遍历玩了，list1还没有遍历完
            articleRecordList.add(articleRecordList1.get(i));
            ++i;
        }

        if(articleRecordList.size() <= 0 ){//差集没有元素
            return null;
        }
        return articleRecordList;

    }

    /**
     * 通过封装类获取所有推荐的articleId 列表
     * @param articleRecordCollectionList
     * @return
     */
    public List<Integer> getArticleIdList(List<ArticleRecordCollection> articleRecordCollectionList){

        Map<Integer,Double> article_weight = new HashMap<>();//存储文章权重 key articleId ; value 文章权重

        //对列表内的所有文章权重进行求和累加
        for(ArticleRecordCollection articleRecordCollection : articleRecordCollectionList){//遍历封装列表

            for(ArticleRecord articleRecord :articleRecordCollection.getArticleRecordList()){
                if(article_weight.get(articleRecord.getArticleId()) == null){//该文章未加入map
                    article_weight.put(articleRecord.getArticleId(),articleRecordCollection.getUserWeight());
                }else{//在原来基础上加上权重
                    article_weight.put(articleRecord.getArticleId(),article_weight.get(articleRecord.getArticleId())+articleRecordCollection.getUserWeight());
                }
            }
        }
        List<Integer> articleList = new ArrayList<>();
        //对Map进行遍历
        for(Map.Entry<Integer,Double> entry : article_weight.entrySet()){
            if(articleList.size() <=0){
                articleList.add(entry.getKey());
            }else{
                boolean flag=false;
                for(int i =0 ;i<articleList.size() ;++i){
                    if(article_weight.get(articleList.get(i))>entry.getValue()){//按照从大到小排序
                        continue;
                    }else{
                        articleList.add(i , entry.getKey());
                        flag=true;
                        break;
                    }
                }
                if(flag == false){//未加入列表
                    articleList.add(entry.getKey());
                }
            }
        }


        return articleList;//返回文章id列表
    }

    /**
     * 根据文章id列表获取文章列表
     * @param articleIdList
     * @return
     */
    public List<Article> getArticleList(List<Integer> articleIdList){
        List<Article> articleList = new ArrayList<>();
        int max_count =20;//文章推荐最大数量

        for(int i=0 ;i<articleIdList.size() && i<max_count; ++i){
            Integer articleId = articleIdList.get(i);//获取articleId
            List<Article> al = articleService.queryArticle(articleId,null,null,null,null);//根据文章id查询
            if(al!=null && al.size()>0){
                articleList.add(al.get(0));
            }
        }
        if(articleIdList.size() <=0){
            return null;//方便前端判断
        }
        return articleList;

    }

}

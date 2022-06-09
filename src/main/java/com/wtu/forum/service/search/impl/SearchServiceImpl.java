package com.wtu.forum.service.search.impl;

import com.wtu.entity.Article;
import com.wtu.entity.Index;
import com.wtu.service.articleservice.ArticleService;
import com.wtu.service.common.safe.Encrypt;
import com.wtu.service.common.safe.SafeCheckpoint;
import com.wtu.service.mq.ConsumerServiceImpl;
import com.wtu.service.mq.ProducerServiceImpl;
import com.wtu.service.search.SearchService;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private SafeCheckpoint safeCheckpoint;

    @Autowired
    private ArticleService articleService;

//    @Resource(name="producerService")
//    private ProducerServiceImpl producerService;
    @Override
    public List<Index> search(String keyword) {
//        producerService.sendMessage(keyword);
        //不关注这些词性,d副词 p介词 c连词 u助词 e叹词 y语气词 o拟声词 h前缀 k后缀 w标点符号
        Set<String> expectedNature = new HashSet<String>() {{
           add("d");add("p");add("c");add("u");
           add("e");add("y");add("o");add("h");
           add("k");add("w");
        }};
        if(!safeCheckpoint.contentCheck(keyword)){
            return null;
        }
        Result result = ToAnalysis.parse(keyword); //分词结果的一个封装，主要是一个List<Term>的terms
        List<Term> terms = result.getTerms();
        List<String> keyList=new ArrayList<>();

        //匹配中文字符，ansj分词
        for(int i=0; i<terms.size(); i++) {
            String word = terms.get(i).getName(); //拿到词

            String natureStr = terms.get(i).getNatureStr(); //拿到词性
            if(!expectedNature.contains(natureStr.charAt(0)+"")) {//去除过滤词
                keyList.add(word);//添加到关键词列表
            }
        }
        //匹配英文字符,没有分词
        Pattern pattern=Pattern.compile("([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(keyword);
        while(matcher.find()){
            keyList.add(matcher.group());
        }

        //查询所有文章信息
        List<Article> articleList=articleService.queryArticle(null,null,null,null,null);
        List<Index> indexList=new ArrayList<>();

        //整合分类搜索
        String greg="(java|php|html|js|python|other|)";
        List<String> rangeList=new ArrayList<>();
        Pattern pattern1=Pattern.compile(greg);
        Matcher matcher1=pattern1.matcher(keyword.toLowerCase());
        while(matcher1.find()){
            rangeList.add(matcher1.group());
        }



        for(Article article:articleList){//对所有文章进行遍历
            boolean flag=false;//标记该文章是否有关键词
            StringBuffer description = new StringBuffer(Jsoup.parse(article.getDescription()).body().text());//原描述带格式，先抽取字符
            for (String str:keyList){//遍历所有关键字
                int start_index=description.indexOf(str);
                if(start_index>=0){
                    flag=true;//该文章有关键词
                    description.insert(start_index+str.length(),"</font>").insert(start_index,"<font color='red'>");
                }
            }
            if(flag==true){//已经查找到该文章有该关键字
                Index index=new Index();
                index.setFlag(article.getId());
                index.setDescription(description.toString());
                try {
                    index.setLink(URLEncoder.encode(new Encrypt(article.getLink()).encrypt(),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                index.setTitle(article.getTitle());
                indexList.add(index);
            }else{//该文章没有该关键字
                for (String range : rangeList){
                    if(range.equals(article.getRange())){//为该分类查询
                        Index index=new Index();
                        index.setFlag(article.getId());
                        index.setDescription(description.toString());
                        try {
                            index.setLink(URLEncoder.encode(new Encrypt(article.getLink()).encrypt(),"utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        index.setTitle(article.getTitle());
                        indexList.add(index);
                    }
                }
            }
        }

        return indexList;
    }
}

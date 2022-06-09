package com.wtu.forum.service.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ArticleCacheService
 * @Description 文章缓存服务
 * @Author 逝风无言
 * @Data 2019/10/8 21:30
 * @Version 1.0
 **/
@Service
public class ArticleCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setArticleList(List<Object> articleList,String classes){

        redisTemplate.opsForValue().set("classes-"+classes,articleList);
        redisTemplate.expire("classes"+classes,10, TimeUnit.MINUTES);//10分钟过期
        return ;
    }

    public List<Object> getArticleList(String classes){
        return (List<Object>)redisTemplate.opsForValue().get("classes-"+classes);
    }

}

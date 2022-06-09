package com.wtu.forum.service.search;

import com.wtu.entity.Index;

import java.util.List;

/**
 * 搜索服务
 */
public interface SearchService {
    /**
     * 关键词搜索 基于ansj分词器
     * @param keyword
     * @return
     */
    List<Index> search(String keyword);
}

package com.wtu.forum.dao.category;

import com.wtu.forum.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CategoryRepository
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/20 20:46
 * @Version 1.0
 **/
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    List<Category> queryAll();
}

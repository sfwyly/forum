package com.wtu.forum.dao.authority;

import com.wtu.forum.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName AuthorityRepository
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/7 10:19
 * @Version 1.0
 **/
@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

//    Authority findOne(Integer authorityId);//springboot取消了findOne()函数

    /**
     * 根据权限id获取
     * @param authorityIdList
     * @return
     */
    List<Authority> queryAllByIdIn(List<Integer> authorityIdList);

}

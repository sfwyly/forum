package com.wtu.forum.dao.approve;

import com.wtu.forum.entity.Approve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 文章点赞
 */
@Repository
public interface ApproveRepository extends JpaRepository<Approve,Integer> {

}

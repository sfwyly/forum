package com.wtu.forum.dao.user;

import com.wtu.forum.entity.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName LoginRecordRepository
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/20 21:27
 * @Version 1.0
 **/
@Repository
public interface LoginRecordRepository extends JpaRepository<LoginRecord,Integer>{
}

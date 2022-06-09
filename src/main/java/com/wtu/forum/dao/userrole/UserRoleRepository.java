package com.wtu.forum.dao.userrole;

import com.wtu.forum.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName UserRoleRepository
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/6 21:51
 * @Version 1.0
 **/
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {


    List<UserRole> queryAllByUserId(Integer userId);

}

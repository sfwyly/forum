package com.wtu.forum.dao.user;

import com.wtu.forum.entity.Authority;
import com.wtu.forum.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName UserRepository
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/3 11:35
 * @Version 1.0
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAll();

    List<User> getByUsername(String username);

    User save(User user);

    @Query(value = "select a.link from authority a where a.id in (select ra.authority_id from role_authority ra where ra.role_id in (select ur.role_id from user_role ur where ur.user_id=?1))",nativeQuery = true)
    List<String> queryUserAccessAuthority(Integer userId);
}

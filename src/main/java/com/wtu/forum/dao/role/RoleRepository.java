package com.wtu.forum.dao.role;

import com.wtu.forum.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName RoleRepository
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/7 10:16
 * @Version 1.0
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {



}

package com.wtu.forum.dao.roleauthority;

import com.wtu.forum.entity.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName RoleAuthorityRepository
 * @Description TODO
 * @Author 逝风无言
 * @Data 2019/10/7 10:23
 * @Version 1.0
 **/
@Repository
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority,Integer> {


    /**
     * 根据传入list进行查询
     * @param roleIdList
     * @return
     */
    List<RoleAuthority> queryAllByRoleIdIn(List<Integer> roleIdList);

}

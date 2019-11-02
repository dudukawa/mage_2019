package com.mage.crm.dao;

import com.mage.crm.query.RoleQuery;
import com.mage.crm.vo.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    List<Role> queryRolesByParams(RoleQuery roleQuery);

    Role queryRoleByRoleName(String roleName);

    int insert(Role role);

    int update(Role role);

    @Delete("delete from t_role where id=#{id}")
    int delete(Integer id);

    @Select("select id,role_name as 'roleName' from t_role where id=#{id} ")
    Role queryRoleById(String id);
}

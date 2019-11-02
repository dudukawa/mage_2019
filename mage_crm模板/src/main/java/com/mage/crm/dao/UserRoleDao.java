package com.mage.crm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

public interface UserRoleDao {
    /**
     * 通过角色id查询用户个数
     */
    @Select("select count(1) from t_user_role where role_id=#{id}")
    int queryUserRoleCountsByRoleId(Integer id);

    /**
     * 通过角色id批量删除用户角色表中的用户
     * @param id
     * @return
     */
    @Delete("delete t_user_role where role_id=#{id}")
    int deleteUserRolesByRoleId(Integer id);

}

package com.mage.crm.dao;

import com.mage.crm.dto.ModuleDto;
import com.mage.crm.vo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select module_id from t_permission where role_id=#{rid}")
    List<Integer> queryModuleIdByRid(Integer rid);

    @Select("delete from t_permission where role_id=#{rid}")
    int deletePermissionByRid(Integer rid);

    @Select("select count(1) from t_permission where role_id=#{rid}")
    int queryModuleCountsByRid(Integer rid);

    int insertBatchPermission(List<Permission> permissions);
}

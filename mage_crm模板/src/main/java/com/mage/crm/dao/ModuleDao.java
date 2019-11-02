package com.mage.crm.dao;

import com.mage.crm.dto.ModuleDto;
import com.mage.crm.vo.Module;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModuleDao {

    List<ModuleDto> queryAllsModuleDtos(Integer rid);

    @Select("select id,module_name as 'moduleName',parent_id as 'pId',opt_value as 'optVal'" +
            "from t_module where is_valid=1 andid=#{moduleId}")
    Module queryModuleById(Integer moduleId);
}

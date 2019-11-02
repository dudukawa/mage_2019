package com.mage.crm.service;

import com.mage.crm.dao.ModuleDao;
import com.mage.crm.dao.PermissionDao;
import com.mage.crm.dto.ModuleDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService {
    @Resource
    private ModuleDao moduleDao;
    @Resource
    private PermissionDao permissionDao;

    public List<ModuleDto> queryAllsModuleDtos(Integer rid) {
        //查询所有的模块
        List<ModuleDto> moduleDtos = moduleDao.queryAllsModuleDtos(rid);
        //通过permission表查询模块
        List<Integer> moduleId  = permissionDao.queryModuleIdByRid(rid);
        if(moduleDtos!=null && moduleDtos.size()>0){
            for(ModuleDto moduleDto:moduleDtos){
                if(moduleDtos.contains(moduleId)){
                    moduleDto.setChecked(true);
                }
            }
        }
        return moduleDtos;
    }
}

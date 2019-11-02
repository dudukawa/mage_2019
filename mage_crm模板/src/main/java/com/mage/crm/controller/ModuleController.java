package com.mage.crm.controller;

import com.mage.crm.dto.ModuleDto;
import com.mage.crm.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @Resource
    private ModuleService moduleService;

    @ResponseBody
    @RequestMapping("/queryAllsModuleDtos")
    public List<ModuleDto> queryAllsModuleDtos(Integer rid){
        return moduleService.queryAllsModuleDtos(rid);
    }

}

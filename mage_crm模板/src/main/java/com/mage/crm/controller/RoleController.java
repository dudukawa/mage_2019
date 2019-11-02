package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.model.MessageModel;
import com.mage.crm.query.RoleQuery;
import com.mage.crm.service.RoleService;
import com.mage.crm.vo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Resource
    private RoleService roleService;

    @RequestMapping("/index")
    public String index(Model model){
        return "role";
    }

    @RequestMapping("/queryRolesByParams")
    @ResponseBody
    public Map<String,Object> queryCustomersByParams(RoleQuery roleQuery){
        return roleService.queryRolesByParams(roleQuery);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public MessageModel insert(Role role){
        roleService.insert(role);
        return createMessageModel("角色记录添加成功");
    }

    @RequestMapping("/update")
    @ResponseBody
    public MessageModel update(Role role){
        roleService.update(role);
        return createMessageModel("角色记录修改成功");
    }

    /**
     * 删除角色的同时应该删除t_permission权限表的角色记录
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public MessageModel delete(Integer id){
        roleService.delete(id);
        return createMessageModel("角色记录删除成功");
    }

    @RequestMapping("/addPermission")
    @ResponseBody
    public MessageModel addPermission(Integer rid,Integer[] moduleIds){
        roleService.addPermission(rid,moduleIds);
        return createMessageModel("添加权限成功");
    }
}

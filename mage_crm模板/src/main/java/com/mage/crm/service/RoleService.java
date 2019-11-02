package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.mage.crm.base.CrmConstant;
import com.mage.crm.dao.ModuleDao;
import com.mage.crm.dao.PermissionDao;
import com.mage.crm.dao.RoleDao;
import com.mage.crm.dao.UserRoleDao;
import com.mage.crm.query.RoleQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.Module;
import com.mage.crm.vo.Permission;
import com.mage.crm.vo.Role;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private PermissionDao permissionDao;
    @Resource
    private ModuleDao moduleDao;


    public Map<String, Object> queryRolesByParams(RoleQuery roleQuery) {
        PageHelper.startPage(roleQuery.getPage(),roleQuery.getRows());
        List<Role> roleList = roleDao.queryRolesByParams(roleQuery);
        PageInfo<Role> rolePageInfo = new PageInfo<Role>(roleList);
        Map<String,Object> map  = new HashMap<>();
        map.put("total",rolePageInfo.getTotal());
        map.put("rows",rolePageInfo.getList());
        return map;
    }

    /**
     * 角色名非空验证
     * 角色名重复验证
     * 额外字段添加
     * 执行添加
     * @param role
     */
    public void insert(Role role) {
        AssertUtil.isTrue(StringUtil.isEmpty(role.getRoleName()),"角色名不能为空！  ");
        AssertUtil.isTrue(roleDao.queryRoleByRoleName(role.getRoleName())!=null,"角色名不能相同！");
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        role.setIsValid(1);
        AssertUtil.isTrue(roleDao.insert(role)<1,"插入角色记录失败");
    }

    public void update(Role role) {
        //判断角色不能为空
        AssertUtil.isTrue(StringUtil.isEmpty(role.getRoleName()),"角色名不能为空！  ");
        //判断角色记录是否存在
        AssertUtil.isTrue(role.getId()==null||roleDao.queryRoleById(role.getId())==null,"角色记录不存在！  ");
        //判断角色名是否重复
        Role temp = roleDao.queryRoleByRoleName(role.getRoleName());
        AssertUtil.isTrue(temp.getId()!=null&&role.getId().equals(temp.getId()),"角色名已存在！");
        //修改更新时间
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleDao.update(role)<1,CrmConstant.OPS_FAILED_MSG);
    }

    /**
     * 删除角色记录：
     * 查询此id，用户角色表中记录数，判断是否需要删除
     * 级联删除用户角色表中的用户
     * 删除角色记录
     * @param id
     */
    public void delete(Integer id) {
        AssertUtil.isTrue(id==null||roleDao.queryRoleById(id+"")==null,"待删除的角色记录不存在");
        int count = userRoleDao.queryUserRoleCountsByRoleId(id);
        if(count>0){
            AssertUtil.isTrue(userRoleDao.deleteUserRolesByRoleId(id)<count, CrmConstant.OPS_FAILED_MSG);
        }
        AssertUtil.isTrue(roleDao.delete(id)<1,CrmConstant.OPS_FAILED_MSG);
    }

    /**
     * 判断角色id是否为空，模块id可以为空
     * 查询是否有权限，有权限继续
     * 删除权限
     * 批量添加权限
     * @param rid
     * @param moduleIds
     * @return
     */
    public void addPermission(Integer rid, Integer[] moduleIds) {
        AssertUtil.isTrue(rid==null||roleDao.queryRoleById(rid+"")==null,"角色异常");
        int count = permissionDao.queryModuleCountsByRid(rid);
        if(count>0){
             AssertUtil.isTrue(permissionDao.deletePermissionByRid(rid)<count,CrmConstant.OPS_FAILED_MSG);
        }
        //批量添加(关联module表查出opt_value)
        List<Permission> permissions=new ArrayList<>();
        Permission permission = new Permission();;
        permission.setRoleId(rid);
        for (Integer moduleId:moduleIds) {
            Module module = moduleDao.queryModuleById(moduleId);
            if(module!=null){
                permission.setAclValue(module.getOptValue());
            }
            permission.setCreateDate(new Date());
            permission.setUpdateDate(new Date());
            permissions.add(permission);
        }
        AssertUtil.isTrue(permissionDao.insertBatchPermission(permissions)<moduleIds.length,"添加权限失败");
    }
}

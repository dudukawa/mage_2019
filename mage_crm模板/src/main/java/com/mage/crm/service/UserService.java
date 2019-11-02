package com.mage.crm.service;

import com.mage.crm.dao.UserDao;
import com.mage.crm.model.UserModel;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.util.Base64Util;
import com.mage.crm.util.Md5Util;
import com.mage.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public UserModel login(String userName, String userPwd){
        //是否要非空
        User user = userDao.queryUserByName(userName);
        userPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(user==null,"用户不存在");
        AssertUtil.isTrue(!userPwd.equals(user.getUserPwd()),"用户名或密码不正确");
        AssertUtil.isTrue("0".equals(user.getIsValid()),"用户已经失效");
        return createUserModel(user);
    }
    private UserModel createUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(Base64Util.encode(user.getId()));
        userModel.setTrueName(user.getTrueName());
        userModel.setUserName(user.getUserName());
        return userModel;
    }

    public void updatePwd(String id,String oldPassword,String newPassword,String confirmPassword){
        AssertUtil.isTrue(StringUtils.isBlank(id),"id不存在");
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"确认密码不能为空");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"新密码和确认密码不一致");
        AssertUtil.isTrue(oldPassword.equals(newPassword),"新密码和旧密码不能相同");
        User user = userDao.queryUserById(Base64Util.decode(id));
        AssertUtil.isTrue(user==null,"用户不存在");
        AssertUtil.isTrue("0".equals(user.getIsValid()),"用户已注销");
        oldPassword = Md5Util.encode(oldPassword);
        AssertUtil.isTrue(!oldPassword.equals(user.getUserPwd()),"原密码不正确");
        newPassword = Md5Util.encode(newPassword);
        int i = userDao.updatePwd(newPassword, Base64Util.decode(id));
        AssertUtil.isTrue(i<1,"修改失败");
    }

    public User queryUserById(String id){
        return userDao.queryUserById(id);
    }

    public List<User> queryAllCustomerManager() {
        return userDao.queryAllCustomerManager();
    }
}

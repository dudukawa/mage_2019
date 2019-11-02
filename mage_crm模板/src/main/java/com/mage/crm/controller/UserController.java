package com.mage.crm.controller;

import com.mage.crm.base.CrmConstant;
import com.mage.crm.base.exceptions.ParamException;
import com.mage.crm.model.MessageModel;
import com.mage.crm.model.UserModel;
import com.mage.crm.service.UserService;
import com.mage.crm.util.CookieUtil;
import com.mage.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.annotation.ExceptionProxy;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/login")
    public MessageModel login(String userName, String userPwd){
        MessageModel messageModel = new MessageModel();
        messageModel.setMsg(CrmConstant.OPS_SUCCESS_MSG);
        messageModel.setCode(CrmConstant.OPS_SUCCESS_CODE);
        //如果没有写全局异常就需要把以下两条语句捕捉异常，
        //并且处理自定义异常ParamException和未定义异常Exception两种异常
        //在不同的异常里面设置状态码和具体的异常消息msg到MessageModel中
            UserModel userModel = userService.login(userName, userPwd);
            messageModel.setResult(userModel);
        return messageModel;
    }

    @ResponseBody
    @RequestMapping("/updatePwd")
    public MessageModel updatePwd(HttpServletRequest request,String oldPassword,String newPassword,String confirmPassword){
        MessageModel messageModel = new MessageModel();
        String id = CookieUtil.getCookieValue(request, "id");
        userService.updatePwd(id,oldPassword,newPassword,confirmPassword);
        messageModel.setMsg("修改成功");
        return messageModel;
    }

    //展示所有是"客户经理"的用户
    @ResponseBody
    @RequestMapping("/queryAllCustomerManager")
    public List<User> queryAllCustomerManager(){
        return userService.queryAllCustomerManager();
    }
}
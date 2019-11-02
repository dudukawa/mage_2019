package com.mage.crm.interceptors;

import com.mage.crm.base.CrmConstant;
import com.mage.crm.service.UserService;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.util.Base64Util;
import com.mage.crm.util.CookieUtil;
import com.mage.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String id = CookieUtil.getCookieValue(httpServletRequest,"id");
        System.out.println(id);
        AssertUtil.isTrue(StringUtils.isBlank(id), CrmConstant.NOT_LOGINED_CODE,CrmConstant.NOT_LOGIN_MSG);
        //id是id的用户不存在时，提示登录
        User user = userService.queryUserById(Base64Util.decode(id));
        System.out.println(user);
        AssertUtil.isTrue(user == null,CrmConstant.NOT_LOGINED_CODE,CrmConstant.NOT_LOGIN_MSG);
        AssertUtil.isTrue("0".equals(user.getIsValid()),CrmConstant.NOT_LOGINED_CODE,CrmConstant.NOT_LOGIN_MSG);
        return true;
    }
}

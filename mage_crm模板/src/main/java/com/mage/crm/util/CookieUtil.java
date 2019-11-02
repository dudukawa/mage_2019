package com.mage.crm.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 获取cookie key对应的值
 */
public class CookieUtil {
    public static String getCookieValue(HttpServletRequest request,String key){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie:cookies){
                if(key.equals(cookie.getName())){
                    try{
                        //解决乱码
                        return URLDecoder.decode(cookie.getValue(),"utf-8");
                    }catch (UnsupportedEncodingException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}

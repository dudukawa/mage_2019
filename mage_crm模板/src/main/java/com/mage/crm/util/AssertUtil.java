package com.mage.crm.util;

import com.mage.crm.base.exceptions.ParamException;

public class AssertUtil {
    public static void isTrue(boolean flag,String msg){
        if(flag){
            throw new ParamException(300,msg);
        }
    }
    public static void isTrue(boolean flag,Integer code,String msg){
        if(flag){
            throw new ParamException(code,msg);
        }
    }
}

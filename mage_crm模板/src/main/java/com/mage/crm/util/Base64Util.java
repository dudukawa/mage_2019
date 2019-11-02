package com.mage.crm.util;


import org.apache.commons.codec.binary.Base64;

public class Base64Util {

    public static String encode(String str){
        return Base64.encodeBase64String(str.getBytes());
    }

    public static String decode(String str){
        byte[] bytes = Base64.decodeBase64(str);
        return new String(bytes);
    }
}

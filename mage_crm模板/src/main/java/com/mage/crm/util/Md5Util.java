package com.mage.crm.util;

import org.apache.commons.codec.binary.Base64;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public static String encode(String str){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] digest = messageDigest.digest(str.getBytes());
            str = Base64.encodeBase64String(digest);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return str;
    }
}

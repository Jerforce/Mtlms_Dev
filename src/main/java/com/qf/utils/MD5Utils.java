package com.qf.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public  static String md5Encode(String pwd){
        String str="";
        //加密
        try {
            MessageDigest digest=MessageDigest.getInstance("MD5");
            //将pwd转成byte数组，然后转成MessageDigest对象
            digest.update(pwd.getBytes());
            //获得加密之后的数组
            byte[] bytes = digest.digest();
            //将byte转成想要的格式然后返回回去
            str=new BigInteger(1,bytes).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    };
}

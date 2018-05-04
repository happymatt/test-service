package com.jhqc.pxsj.shopsign.encrypt;


public class Encrypt {
    
    public static String encrypt(String msg){
        return MD5Encrypt.encrypt(MD5Encrypt.encrypt(msg));
    }

}

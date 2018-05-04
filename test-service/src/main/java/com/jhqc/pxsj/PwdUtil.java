package com.jhqc.pxsj;


public class PwdUtil {

    public static void main(String[] args) {
        String keyPre  = "453480e9ebba08ec";
        String pwd = "aa123456";
        String keyPost = "e97968a0ef523d36";
        String s = MD5Encrypt.encrypt(keyPre + pwd + keyPost);
        //EncryptionPwdUtil.getEncryptionPwd(s, "20158");
        System.out.println(s);
        String ss = "4279abefa7ab0bcb292eebfcdab549ee";
    }
}

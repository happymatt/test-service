package com.jhqc.pxsj;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 */
public class MD5Encrypt {
	
	/*public static void main(String[] args) {
		System.out.println(encrypt(encrypt("123456")));
	}*/

	public static String encrypt(String data) {
		String encode = data;
		StringBuilder stringbuilder = new StringBuilder();
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
		md5.update(encode.getBytes());
		byte[] str_encoded = md5.digest();
		for (int i = 0; i < str_encoded.length; i++) {
			if ((str_encoded[i] & 0xff) < 0x10) {
				stringbuilder.append("0");
			}
			stringbuilder.append(Long.toString(str_encoded[i] & 0xff, 16));
		}
		return stringbuilder.toString();
	}
}

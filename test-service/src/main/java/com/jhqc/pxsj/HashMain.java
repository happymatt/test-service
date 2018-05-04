package com.jhqc.pxsj;


import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import javax.crypto.KeyGenerator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import java.lang.String;


public class HashMain {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		long  m29 = 1024 * 1024 * 512;//2 ^ 29
		long  m30 = m29 * 2;//2^30
		long  m31 = m30 * 2;//2^31
		long  m32 = m31 * 2;//2^32
		long m  = Integer.MAX_VALUE;
		long n = m + m;
		String str = new String();
		System.out.println(n);
		String temp1 = "abc123";
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(512);
		KeyPair kp = kpg.generateKeyPair();
		Signature sig = Signature.getInstance("SHA1withRSA");
		PublicKey pk = kp.getPublic();
		PrivateKey privateKey = kp.getPrivate();
		String publicStr = new String(Base64Utils.encode(pk.getEncoded()));
		String privateStr = new String(Base64Utils.encode(privateKey.getEncoded()));
		System.out.println(publicStr);
		System.out.println(privateStr.length());
		byte[] bytes2 = Base64.getEncoder().encode(temp1.getBytes("UTF-8"));
		
		KeyGenerator.getInstance("des");
		MessageDigest md = MessageDigest.getInstance("MD5");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		String temp = "how are you man?";
		byte[] bts = md.digest(temp.getBytes("utf-8"));
		for (byte b : bts) {
			int ti = (int) b;
			System.out.println(Integer.toHexString(ti));
		}
		System.out.println(bts);
	}

}

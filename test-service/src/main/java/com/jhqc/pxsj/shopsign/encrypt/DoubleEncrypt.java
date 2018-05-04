package com.jhqc.pxsj.shopsign.encrypt;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZRK 二次加密，将ShortEncrypt的加密结果二次加密
 */
public class DoubleEncrypt extends ShortEncrypt
{
	private static Logger logger = LoggerFactory.getLogger(DoubleEncrypt.class);

	final static String LOCAL_DB_URL = "jdbc:mysql://192.168.31.147:3306/pxsj?characterEncoding=utf8";
	final static String TEST_DB_URL_QY = "jdbc:mysql://119.254.108.218:3306/pxsj?characterEncoding=utf8";
	final static String PROD_DB_URL = "jdbc:mysql://124.42.117.161:3306/pxsj?characterEncoding=utf8";
	final static String PROD_DB_URL2 = "jdbc:mysql://119.254.98.47:3306/pxsj?characterEncoding=utf8";
	final static String TEST_DB_URL_LOCAL = "jdbc:mysql://192.168.31.86:3306/pxsj?characterEncoding=utf8";
	
	final static String PROD_MYSQL = "jdbc:mysql://192.168.0.8:3306/pxsj?characterEncoding=utf8";
	final static String PROD_USER = "root";
	final static String PROD_PASSWORD = "jHqC#$)187";

	final static String STAGING_MYSQL1 = "jdbc:mysql://192.168.0.12:3306/pxsj?characterEncoding=utf8";
	final static String STAGING_MYSQL2 = "jdbc:mysql://192.168.0.17:3306/pxsj?characterEncoding=utf8";
	final static String STAGING_MYSQL3 = "jdbc:mysql://192.168.0.18:3306/pxsj?characterEncoding=utf8";
	final static String STAGING_USER = "jhqcmdb";
	final static String STAGING_PASSWORD = "jHqC#$)187";
	
	final static String TEST_PASSWORD="Test_123";
	final static String DEMO_PASSWORD="q8ivP&mKFkLS9fs4";
	final static String DEMOMOBILE_PASSWORD="oS4_oSYy";
	
	final static String DEMOMOBILE_USER="pxsjuser";
	
	final static String test = "1QbB9noiR4b5Qv7hwEi6YpdlYwpzUx";
	
	public final static String keyString = "pxsjhhgxz2016";
	
	
	public static void main(String[] args) {
		DoubleEncrypt de = DoubleEncrypt.getEncrypt(DoubleEncrypt.keyString.getBytes());
		String temp1 = de.getEncodeString(DEMOMOBILE_PASSWORD);
		System.out.println("加密后："+temp1);
		String t1 = de.getDecodeString("oS4_oSYy");
		System.out.println("解密后:"+t1);
		String temp2 = de.getEncodeString(STAGING_PASSWORD);
		System.out.println(temp1.equals(temp2));
		System.out.println("加密后："+temp2);
		String t2 = de.getDecodeString(temp2);
		System.out.println("解密后:"+t2);
	}
	/**
	 * 随机码生成密钥，生成加密
	 * @return
	 */
	public static DoubleEncrypt getEncryptRandom()
	{
		Random random = new Random(new Date().getTime());
		
		byte[] sk = new byte[10+random.nextInt(5)];
		for(int n=0; n<sk.length; n++)
		{
			sk[n] = (byte) (random.nextInt(Byte.MAX_VALUE) + 1);
		}
		return new DoubleEncrypt(sk);
	}
	
	/**
	 * 自定义密钥，生成加密
	 * @param secretKey
	 * @return
	 */
	public static DoubleEncrypt getEncrypt(byte[] secretKey)
	{
		return new DoubleEncrypt(secretKey);
	}
	
	private DoubleEncrypt(byte[] secretKey)
	{
		super(secretKey);
	}
	
	/**
	 * 加密
	 */
	public String getEncodeString(String iptString)
	{
		try
		{
			String es = super.getEncodeString(iptString);
			es = super.getEncodeString(es);
			
			return es;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		}		
	}

	/**
	 * 解密
	 */
	public String getDecodeString(String utString)
	{
		try
		{
			String ds = super.getDecodeString(utString);
			ds = super.getDecodeString(ds);	
	
			return ds;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		}
	}
}
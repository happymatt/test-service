package com.jhqc.pxsj.shopsign.encrypt;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ZRK 长型加密算法，加密后的长度约是原串的10倍
 */
public class LongEncrypt extends ShortEncrypt
{
	
	private static Logger logger = LoggerFactory.getLogger(LongEncrypt.class);
	
	/**
	 * 随机码生成密钥，生成加密
	 * @return
	 */
	public static LongEncrypt getEncryptRandom()
	{
		Random random = new Random(new Date().getTime());
		
		byte[] sk = new byte[10+random.nextInt(5)];
		for(int n=0; n<sk.length; n++)
		{
			sk[n] = (byte) (random.nextInt(Byte.MAX_VALUE) + 1);
		}
		return new LongEncrypt(sk);
	}
	
	/**
	 * 自定义密钥，生成加密
	 * @param secretKey
	 * @return
	 */
	public static LongEncrypt getEncrypt(byte[] secretKey)
	{
		return new LongEncrypt(secretKey);
	}
	
	private LongEncrypt(byte[] secretKey)
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
			byte[] bytes = iptString.getBytes(CHAR_ENCODING);
			
			StringBuffer buffer = new StringBuffer();
			int p = 0;
			for(byte b : bytes)
			{
				int bc = getBitCode()[p++];
				if(p >= getBitCode().length) p = 0;
				int i = (int)b + 128 + bc;
				buffer.append(String.format("%03d", i));
			}
			
			String es = super.getEncodeString(buffer.toString());
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
			
			if(ds.length()%3 !=0) return "";
			
			byte[] bytes = new byte[ds.length()/3];
			int p = 0;
			for(int n=0; n<bytes.length; n++)
			{
				String str = ds.substring(n*3, (n+1)*3);
				int bc = getBitCode()[p++];
				if(p >= getBitCode().length) p = 0;
				bytes[n] = (byte)(Integer.parseInt(str) - 128 - bc);
			}
	
			return new String(bytes, CHAR_ENCODING);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		}
	}
}

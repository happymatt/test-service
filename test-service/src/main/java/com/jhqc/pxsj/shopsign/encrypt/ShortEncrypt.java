package com.jhqc.pxsj.shopsign.encrypt;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * ZRK 短型加密算法
 */
public class ShortEncrypt
{
	private static Logger logger = LoggerFactory.getLogger(ShortEncrypt.class);
	
	/**
	 * 随机码生成密钥，生成加密
	 * @return
	 */
	public static ShortEncrypt getEncryptRandom()
	{
		Random random = new Random(new Date().getTime());
		
		byte[] sk = new byte[10+random.nextInt(5)];
		for(int n=0; n<sk.length; n++)
		{
			sk[n] = (byte) (random.nextInt(Byte.MAX_VALUE) + 1);
		}
		return new ShortEncrypt(sk);
	}
	
	/**
	 * 自定义密钥，生成加密
	 * @param secretKey
	 * @return
	 */
	public static ShortEncrypt getEncrypt(byte[] secretKey)
	{
		return new ShortEncrypt(secretKey);
	}

	protected ShortEncrypt(byte[] secretKey)
	{
		BIT_CODE = secretKey;
		
		Char_UT = new int[128];
		for (int n = 0; n < Char_UT.length; n++)
		{
			Char_UT[n] = -1;
		}
		for (int n = 0; n < UT_Char.length; n++)
		{
			Char_UT[UT_Char[n]] = n;
		}
	}

	protected static String CHAR_ENCODING = "UTF-8";
	private static char[] UT_Char = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', '_', '*' };
	
	private byte[] BIT_CODE;
	private int[] Char_UT;

	/**
	 * 获取密钥
	 * @return
	 */
	public byte[] getBitCode()
	{
		return BIT_CODE;
	}
	
	/**
	 * 加密
	 * @param iptString
	 * @return
	 */
	public String getEncodeString(String iptString)
	{
		if(StringUtils.isBlank(iptString)) return "";
		
		StringBuffer hxStrBuffer = new StringBuffer(stringEncrypt(iptString));
		int lessThree = 3 - hxStrBuffer.length() % 3;
		if (lessThree != 3)
		{
			StringBuffer addZero = new StringBuffer();
			for (int n = 0; n < lessThree; n++)
			{
				addZero.append('0');
			}
			hxStrBuffer.insert(0, addZero);
		}
		String hxString = hxStrBuffer.toString();
		int count = hxString.length() / 3;
		StringBuffer utString = new StringBuffer();
		for (int n = 0; n < count; n++)
		{
			String hxBit = hxString.substring(n * 3, n * 3 + 3);
			int num = Integer.parseInt(hxBit, 16);
			char[] utChar = getNumberStyle(num);
			utString.append(utChar);
		}
		return (utString.toString());
	}

	/**
	 * 解密
	 * @param utString
	 * @return
	 */
	public String getDecodeString(String utString)
	{
		if(StringUtils.isBlank(utString)) return "";
		
		try
		{
			if (utString.length() % 2 != 0) return "";
			int count = utString.length() / 2;
			StringBuffer hxString = new StringBuffer();
			for (int n = 0; n < count; n++)
			{
				String utBit = utString.substring(n * 2, n * 2 + 2);
				int num = getStyleNumber(utBit);
				if (num < 0) return "";
				String hxBit = Integer.toHexString(num).toUpperCase();
				if (hxBit.length() == 1)
				{
					hxString.append("00");
				}
				else if (hxBit.length() == 2)
				{
					hxString.append("0");
				}
				hxString.append(hxBit);
			}
			String rxstr = null;
			if (hxString.length() >= 3 && hxString.charAt(0) == '0')
			{
				if (hxString.length() % 2 != 0)
				{
					rxstr = hxString.substring(1);
				}
				else if (hxString.charAt(1) == '0')
				{
					rxstr = hxString.substring(2);
				}
				else
				{
					rxstr = hxString.toString();
				}
			}
			else rxstr = hxString.toString();
			return stringDecrypt(rxstr);
		}
		catch (Exception e)
		{
			return "";
		}
	}

	// encrypt
	private String stringEncrypt(String str)
	{
		byte[] bytes = getStringBytes(str);
		int[] codeByte = new int[bytes.length];
		int codeIndex = 0;
		int btbuff;
		for (int n = 0; n < bytes.length; n++)
		{
			byte b = bytes[n];
			btbuff = b + 128;
			if (codeIndex == BIT_CODE.length) codeIndex = 0;
			int thisByte = btbuff + BIT_CODE[codeIndex];
			if (thisByte > 255) thisByte -= 255;
			codeByte[codeByte.length - n - 1] = thisByte;
			codeIndex++;
		}
		StringBuffer codeStr = new StringBuffer();
		for (int n = 0; n < codeByte.length; n++)
		{
			String oxstr = Integer.toHexString(codeByte[n]);
			oxstr = oxstr.length() == 1 ? ("0" + oxstr) : oxstr;
			codeStr.append(oxstr);
		}
		return codeStr.toString();
	}

	// decrypt
	private String stringDecrypt(String str)
	{
		if (str == null) return "";
		int[] decodeInt = new int[str.length() / 2];
		for (int n = 0; n < decodeInt.length; n++)
		{
			StringBuffer oxstr = new StringBuffer();
			oxstr.append(str.charAt(2 * n));
			oxstr.append(str.charAt(2 * n + 1));
			decodeInt[decodeInt.length - n - 1] = Integer.parseInt(oxstr.toString(), 16);
		}
		int codeIndex = 0;
		byte[] decodeByte = new byte[decodeInt.length];
		for (int n = 0; n < decodeInt.length; n++)
		{
			int b = decodeInt[n];
			if (codeIndex == BIT_CODE.length) codeIndex = 0;
			int bInt = b - BIT_CODE[codeIndex];
			if (bInt < 0) bInt = 255 + bInt;
			bInt -= 128;
			decodeByte[n] = (byte) bInt;
			codeIndex++;
		}
		String decodeString = getBytesString(decodeByte);
		return decodeString;
	}

	private String getBytesString(byte[] decodeByte)
	{
		try
		{
			return new String(decodeByte, CHAR_ENCODING);
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error(e.getMessage());
			return "";
		}
	}

	private byte[] getStringBytes(String str)
	{
		if (null == str || str.length() == 0) return new byte[0];
		try
		{
			return str.getBytes(CHAR_ENCODING);
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error(e.getMessage());
			return new byte[0];
		}
	}

	private char[] getNumberStyle(int number)
	{
		int scale = 64;
		int lessNumber = number;
		char[] numChar = new char[2];
		int bitNumber = lessNumber % scale;
		lessNumber = lessNumber / scale;
		numChar[1] = UT_Char[bitNumber];
		numChar[0] = UT_Char[lessNumber];
		return numChar;
	}

	private int getStyleNumber(String utBit)
	{
		int scale = 64;
		int num = 0;
		for (int n = 0; n < utBit.length(); n++)
		{
			int bitCt = utBit.length() - n - 1;
			char c = utBit.charAt(n);
			if (c >= 128) return -1;
			int utNum = Char_UT[c];
			if (utNum < 0) return -1;
			num += utNum * (mathPow(scale, bitCt));
		}
		return num;
	}

	private int mathPow(int num, int pow)
	{
		if (pow == 0) return 1;
		if (pow < 0) return 0;
		if (pow == 1) return num;
		int pnum = num;
		for (int n = 2; n < pow; n++)
		{
			pnum *= num;
		}
		return pnum;
	}
}

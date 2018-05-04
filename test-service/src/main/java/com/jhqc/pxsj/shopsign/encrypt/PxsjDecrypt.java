package com.jhqc.pxsj.shopsign.encrypt;

public class PxsjDecrypt {

	private static DoubleEncrypt de = DoubleEncrypt.getEncrypt(DoubleEncrypt.keyString.getBytes());
	private static String seed = "{ab12}";

	public static String decrypt(String str) {
		if (str == null) return null;
		if (str.contains(seed)) {
			return de.getDecodeString(str.substring(str.indexOf(seed) + 6));
		}
		return str;
	}
}

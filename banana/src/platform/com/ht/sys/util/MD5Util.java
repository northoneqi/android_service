package com.ht.sys.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	public static String getMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes("utf-8"));
		byte[] b = md.digest();
		StringBuffer sb = new StringBuffer("");
		int temp = 0;

		for (int offset = 0; offset < b.length; offset++) {
			temp = b[offset];
			if (temp < 0) {
				temp += 256;
			}
			if (temp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(temp));
		}
		return sb.toString();
	}

}

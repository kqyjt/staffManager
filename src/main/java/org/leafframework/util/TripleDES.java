package org.leafframework.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public final class TripleDES {
	//private static String DesKey = "1234567890";

	public static String encrypt(String plaintext) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String md5Password = md5.encodePassword(plaintext, null);
		return md5Password;
	}

	public static String decrypt(String cryptogram) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String md5Password = md5.encodePassword(cryptogram, null);
		return md5Password;
	}
	
	public static void main(String[] args) {
		TripleDES des = new TripleDES();
		System.out.println(des.encrypt("wlyx@qaz#"));
	}
}

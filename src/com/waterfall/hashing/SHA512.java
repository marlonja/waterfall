package com.waterfall.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.sun.mail.auth.MD4;

public class SHA512 {

	public static String get_SHA512(String password, byte[] salt) {
		String sha512_password = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			sha512_password = hashPassword(md, password, salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		return sha512_password;
	}
	
	public static String hashPassword(MessageDigest md, String password, byte[] salt) {
		md.update(salt);
		byte[] bytes = md.digest(password.getBytes());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		return sb.toString();
	}
	
	public static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
}

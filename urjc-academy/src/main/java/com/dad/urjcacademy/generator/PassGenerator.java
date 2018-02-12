package com.dad.urjcacademy.generator;

public class PassGenerator {

	private static final String KEYS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int KEYSIZE = 5;
	
	private String password = "";
	
	public PassGenerator() {
		for(int i = 0; i < KEYSIZE;i++) {
			this.password += (KEYS.charAt((int)(Math.random() * KEYS.length())));
		}
	}
	
	public String getPassword() {
		return password;
	}
	
}

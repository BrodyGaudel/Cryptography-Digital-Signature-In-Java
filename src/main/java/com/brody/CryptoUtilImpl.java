package com.brody;

import java.util.Base64;
import java.util.Formatter;

import org.apache.commons.codec.binary.Hex;

public class CryptoUtilImpl {
	
	public String encodeToBase64(byte[] data) {
		
		return Base64.getEncoder().encodeToString(data);
	}
	
	public byte[] decodeFromBase64(String dataBase64) {
		
		return Base64.getDecoder().decode(dataBase64.getBytes());
	}
	
	public String encodeToBase64Url(byte[] data) {
		
		return Base64.getUrlEncoder().encodeToString(data);
	}
	
	public byte[] decodeFromBase64Url(String dataBase64) {
		
		return Base64.getUrlDecoder().decode(dataBase64.getBytes());
	}
	
	/**
	 * with library
	 * @param data
	 * @return
	 */
	public String encodeToHex(byte[] data) {
		return Hex.encodeHexString(data);
	}
	
	/**
	 * without library
	 * @param data
	 * @return
	 */
	public String encodeToHexNative(byte[] data) {
		Formatter formatter = new Formatter();
		for(byte b: data) {
			formatter.format("%02x",b);
		}
		return formatter.toString();
	}
	
	

}

package com.brody.encryption;

import java.util.Base64;
import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
		@SuppressWarnings("resource")
		Formatter formatter = new Formatter();
		for(byte b: data) {
			formatter.format("%02x",b);
		}
		//formatter.close();
		return formatter.toString();
	}
	
	/**
	 * Generate AES Secret Key initialized to 256 bits
	 * @return SecretKey
	 * @throws Exception
	 */
	public SecretKey generateSecretKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		return keyGenerator.generateKey();
		
	}
	

	/**
	 * Generate AES Secret Key
	 * @param secret
	 * @return SecretKey
	 */
	public SecretKey generateSecretKey(String secret){
		return new SecretKeySpec(secret.getBytes(), 0, secret.length(), "AES");	
	}
	
	
	
	/**
	 * encrypt AES
	 * @param data
	 * @param secretKey
	 * @return encrypted data
	 * @throws Exception
	 */
	public String encryptAES(byte[] data, SecretKey secretKey) throws Exception {
		
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encrypedData = cipher.doFinal(data);  
		return Base64.getEncoder().encodeToString(encrypedData);
		
	}
	
	/**
	 * Decrypted AES
	 * @param encodedEncryptedData
	 * @param secretKey
	 * @return decrypted data
	 * @throws Exception
	 */
	public byte[] decryptAES(String encodedEncryptedData, SecretKey secretKey) throws Exception {
		
		byte[] decodedEncryptedData = Base64.getDecoder().decode(encodedEncryptedData);
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(decodedEncryptedData);
	}
	
	

}

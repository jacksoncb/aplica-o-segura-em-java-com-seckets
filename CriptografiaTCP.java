package tcp;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class CriptografiaTCP {

	static String IV = "AAAAAAAAAAAAAAAA"; // precisa ter 16 bytes
	static String encryptionKey = "0123456789abcdef"; // precisa ter 16 bytes

	//Método para criptografar
	public String encrypt(String plainText) throws Exception {

		Cipher cipher = Cipher.getInstance("AES");

		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");

		cipher.init(Cipher.ENCRYPT_MODE, key);

		String encriptado = new BASE64Encoder().encode(cipher.doFinal(plainText
				.getBytes("UTF-8")));

		return encriptado;
	}

	//Método para descriptografar
	public String decrypt(String cipherText) throws Exception {

		Cipher cipher = Cipher.getInstance("AES");

		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");

		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] decordedValue = new BASE64Decoder().decodeBuffer(cipherText);

		byte[] decValue = cipher.doFinal(decordedValue);

		String decriptado = new String(decValue);

		return decriptado;
	}
}

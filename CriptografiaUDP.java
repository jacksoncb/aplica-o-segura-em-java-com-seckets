/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CriptografiaUDP {

    static String IV = "AAAAAAAAAAAAAAAA"; //precisa ter 16 bytes
    static String encryptionKey = "0123456789abcdef"; //precisa ter 16 bytes

    public static byte[] encrypt(String plainText) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] cipherText) throws Exception {
        cipherText = cutZeros(cipherText);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText), "UTF-8");
    }



    public static int getNumBytes(byte[] list) {
        int numBytes = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] != 0) {
                numBytes = numBytes + 1;
            }
        }
        return numBytes;
    }
    
    public static byte[] cutZeros(byte[] oldCipher) {
        int cipherNumBytes = getNumBytes(oldCipher);
        byte[] cipher = new byte[cipherNumBytes];
        for (int i = 0; i < cipherNumBytes; i++) {
            cipher[i] = oldCipher[i];
        }
        return cipher;
    }

}

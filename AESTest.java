import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.*;

class AESDecryptTest {
    public static void main(String[] args) {
        byte[][] keys = {
            hexToBytes("000102030405060708090a0b0c0d0e0f"),
            hexToBytes("00000000000000000000000000000000"),
            hexToBytes("00000000000000000000000000000000"),
            hexToBytes("00000000000000000000000000000000")
        };
        
        byte[][] ciphertexts = {
            hexToBytes("69c4e0d86a7b0430d8cdb78070b4c55a"),
            hexToBytes("0336763e966d92595a567cc9ce537f5e"),
            hexToBytes("ff4f8391a6a40ca5b25d23bedd44a597"),
            hexToBytes("dc43be40be0e53712f7e2bf5ca707209")
        };
        
        byte[][] expectedPlaintexts = {
            hexToBytes("00112233445566778899aabbccddeeff"),
            hexToBytes("f34481ec3cc627bacd5dc3fb08f273e6"),
            hexToBytes("96ab5c2ff612d9dfaae8c31f30c42168"),
            hexToBytes("6a118a874519e64e9963798a503f1d35")
        };
        
        try {
            for (int i = 0; i < keys.length; i++) {
                SecretKey ky = new SecretKeySpec(keys[i], "AES");
                Cipher cf = Cipher.getInstance("AES/ECB/NoPadding");
                cf.init(Cipher.DECRYPT_MODE, ky);
                byte[] decryptedText = cf.doFinal(ciphertexts[i]);
                
                System.out.println("Test Vector " + (i + 1));
                System.out.println("Key         : " + bytesToHex(keys[i]));
                System.out.println("Ciphertext  : " + bytesToHex(ciphertexts[i]));
                System.out.println("Decrypted   : " + bytesToHex(decryptedText));
                System.out.println("Expected    : " + bytesToHex(expectedPlaintexts[i]));
                System.out.println("Decryption Successful: " + Arrays.equals(decryptedText, expectedPlaintexts[i]));
                System.out.println("------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static byte[] hexToBytes(String str) {
        int len = str.length() / 2;
        byte[] buffer = new byte[len];
        for (int i = 0; i < len; i++) {
            buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
        }
        return buffer;
    }
    
    public static String bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}

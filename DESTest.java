import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

class DESTest {
   public static void main(String[] args) {
      String test = "1";
      try {
         byte[] theKey = null;
         byte[] theMsg = null; 
         byte[] theExp = null; 
         if (test.equals("1")) { 
            theKey = hexToBytes("0101010101010101");
            theMsg = hexToBytes("8000000000000000");
            theExp = hexToBytes("95F8A5E5DD31D900");
         } else if (test.equals("2")) { 
            theKey = hexToBytes("38627974656B6579"); 
            theMsg = hexToBytes("6D6573736167652E"); 
            theExp = hexToBytes("7CF45E129445D451");
         } else if (test.equals("3")) { 
            theKey = hexToBytes("0101010101010101"); 
            theMsg = hexToBytes("4000000000000000"); 
            theExp = hexToBytes("DD7F121CA5015619");
         } else if (test.equals("4")) { 
            theKey = hexToBytes("0101010101010101 "); 
            theMsg = hexToBytes("2000000000000000"); 
            theExp = hexToBytes("2E8653104F3834EA ");
         } else if (test.equals("5")) { 
            theKey = hexToBytes("0101010101010101"); // "8bytekey"
            theMsg = hexToBytes("1000000000000000"); // "message."
            theExp = hexToBytes("4BD388FF6CD81D4F");
         } else if (test.equals("6")) { 
            theKey = hexToBytes("0101010101010101 "); // "8bytekey"
            theMsg = hexToBytes("0800000000000000"); // "message."
            theExp = hexToBytes("20B9E767B2FB1456");
         } else {
            System.out.println("Usage:");
            System.out.println("java JceSunDesTest 1/2");
            return;
         }	

         KeySpec ks = new DESKeySpec(theKey);
         SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
         SecretKey ky = kf.generateSecret(ks);
         Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
         cf.init(Cipher.ENCRYPT_MODE,ky);
         byte[] theCph = cf.doFinal(theMsg);
         cf.init(Cipher.DECRYPT_MODE,ky);
         byte[] theDec = cf.doFinal(theCph);
         System.out.println("Key     : "+bytesToHex(theKey));
         System.out.println("Message : "+bytesToHex(theMsg));
         System.out.println("Cipher  : "+bytesToHex(theCph));
         System.out.println("Expected Cipher: "+bytesToHex(theExp));
         System.out.println("Decrypted: "+bytesToHex(theDec));
         System.out.println("Expected Decrypted: "+bytesToHex(theMsg));
        
      } catch (Exception e) {
         e.printStackTrace();
         return;
      }
   }




   
   public static byte[] hexToBytes(String str) {
      if (str==null) {
         return null;
      } else if (str.length() < 2) {
         return null;
      } else {
         int len = str.length() / 2;
         byte[] buffer = new byte[len];
         for (int i=0; i<len; i++) {
             buffer[i] = (byte) Integer.parseInt(
                str.substring(i*2,i*2+2),16);
         }
         return buffer;
      }

   }
   public static String bytesToHex(byte[] data) {
      if (data==null) {
         return null;
      } else {
         int len = data.length;
         String str = "";
         for (int i=0; i<len; i++) {
            if ((data[i]&0xFF)<16) str = str + "0" 
               + java.lang.Integer.toHexString(data[i]&0xFF);
            else str = str
               + java.lang.Integer.toHexString(data[i]&0xFF);
         }
         return str.toUpperCase();
      }
   }            
}
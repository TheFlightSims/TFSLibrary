/*    */ package org.postgresql.util;
/*    */ 
/*    */ import java.security.MessageDigest;
/*    */ 
/*    */ public class MD5Digest {
/*    */   public static byte[] encode(byte[] user, byte[] password, byte[] salt) {
/* 42 */     byte[] hex_digest = new byte[35];
/*    */     try {
/* 46 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 48 */       md.update(password);
/* 49 */       md.update(user);
/* 50 */       byte[] temp_digest = md.digest();
/* 52 */       bytesToHex(temp_digest, hex_digest, 0);
/* 53 */       md.update(hex_digest, 0, 32);
/* 54 */       md.update(salt);
/* 55 */       byte[] pass_digest = md.digest();
/* 57 */       bytesToHex(pass_digest, hex_digest, 3);
/* 58 */       hex_digest[0] = 109;
/* 59 */       hex_digest[1] = 100;
/* 60 */       hex_digest[2] = 53;
/* 62 */     } catch (Exception e) {}
/* 67 */     return hex_digest;
/*    */   }
/*    */   
/*    */   private static void bytesToHex(byte[] bytes, byte[] hex, int offset) {
/* 76 */     char[] lookup = { 
/* 76 */         '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/* 76 */         'a', 'b', 'c', 'd', 'e', 'f' };
/* 79 */     int pos = offset;
/* 81 */     for (int i = 0; i < 16; i++) {
/* 83 */       int c = bytes[i] & 0xFF;
/* 84 */       int j = c >> 4;
/* 85 */       hex[pos++] = (byte)lookup[j];
/* 86 */       j = c & 0xF;
/* 87 */       hex[pos++] = (byte)lookup[j];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\MD5Digest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
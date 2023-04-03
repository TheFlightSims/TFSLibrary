/*     */ package org.apache.commons.compress.utils;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ 
/*     */ public class ArchiveUtils {
/*     */   public static String toString(ArchiveEntry entry) {
/*  44 */     StringBuffer sb = new StringBuffer();
/*  45 */     sb.append(entry.isDirectory() ? 100 : 45);
/*  46 */     String size = Long.toString(entry.getSize());
/*  47 */     sb.append(' ');
/*  49 */     for (int i = 7; i > size.length(); i--)
/*  50 */       sb.append(' '); 
/*  52 */     sb.append(size);
/*  53 */     sb.append(' ').append(entry.getName());
/*  54 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static boolean matchAsciiBuffer(String expected, byte[] buffer, int offset, int length) {
/*     */     byte[] buffer1;
/*     */     try {
/*  70 */       buffer1 = expected.getBytes("ASCII");
/*  71 */     } catch (UnsupportedEncodingException e) {
/*  72 */       throw new RuntimeException(e);
/*     */     } 
/*  74 */     return isEqual(buffer1, 0, buffer1.length, buffer, offset, length, false);
/*     */   }
/*     */   
/*     */   public static boolean matchAsciiBuffer(String expected, byte[] buffer) {
/*  85 */     return matchAsciiBuffer(expected, buffer, 0, buffer.length);
/*     */   }
/*     */   
/*     */   public static byte[] toAsciiBytes(String inputString) {
/*     */     try {
/*  97 */       return inputString.getBytes("ASCII");
/*  98 */     } catch (UnsupportedEncodingException e) {
/*  99 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String toAsciiString(byte[] inputBytes) {
/*     */     try {
/* 111 */       return new String(inputBytes, "ASCII");
/* 112 */     } catch (UnsupportedEncodingException e) {
/* 113 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String toAsciiString(byte[] inputBytes, int offset, int length) {
/*     */     try {
/* 127 */       return new String(inputBytes, offset, length, "ASCII");
/* 128 */     } catch (UnsupportedEncodingException e) {
/* 129 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isEqual(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2, boolean ignoreTrailingNulls) {
/* 149 */     int minLen = (length1 < length2) ? length1 : length2;
/*     */     int i;
/* 150 */     for (i = 0; i < minLen; i++) {
/* 151 */       if (buffer1[offset1 + i] != buffer2[offset2 + i])
/* 152 */         return false; 
/*     */     } 
/* 155 */     if (length1 == length2)
/* 156 */       return true; 
/* 158 */     if (ignoreTrailingNulls) {
/* 159 */       if (length1 > length2) {
/* 160 */         for (i = length2; i < length1; i++) {
/* 161 */           if (buffer1[offset1 + i] != 0)
/* 162 */             return false; 
/*     */         } 
/*     */       } else {
/* 166 */         for (i = length1; i < length2; i++) {
/* 167 */           if (buffer2[offset2 + i] != 0)
/* 168 */             return false; 
/*     */         } 
/*     */       } 
/* 172 */       return true;
/*     */     } 
/* 174 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isEqual(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2) {
/* 191 */     return isEqual(buffer1, offset1, length1, buffer2, offset2, length2, false);
/*     */   }
/*     */   
/*     */   public static boolean isEqual(byte[] buffer1, byte[] buffer2) {
/* 202 */     return isEqual(buffer1, 0, buffer1.length, buffer2, 0, buffer2.length, false);
/*     */   }
/*     */   
/*     */   public static boolean isEqual(byte[] buffer1, byte[] buffer2, boolean ignoreTrailingNulls) {
/* 214 */     return isEqual(buffer1, 0, buffer1.length, buffer2, 0, buffer2.length, ignoreTrailingNulls);
/*     */   }
/*     */   
/*     */   public static boolean isEqualWithNull(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2) {
/* 231 */     return isEqual(buffer1, offset1, length1, buffer2, offset2, length2, true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compres\\utils\ArchiveUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
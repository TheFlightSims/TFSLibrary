/*     */ package org.postgresql.util;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ public class Base64 {
/*     */   public static final int NO_OPTIONS = 0;
/*     */   
/*     */   public static final int ENCODE = 1;
/*     */   
/*     */   public static final int DECODE = 0;
/*     */   
/*     */   public static final int DONT_BREAK_LINES = 8;
/*     */   
/*     */   private static final int MAX_LINE_LENGTH = 76;
/*     */   
/*     */   private static final byte EQUALS_SIGN = 61;
/*     */   
/*     */   private static final byte NEW_LINE = 10;
/*     */   
/*     */   private static final String PREFERRED_ENCODING = "UTF-8";
/*     */   
/*     */   private static final byte[] ALPHABET;
/*     */   
/*  98 */   private static final byte[] _NATIVE_ALPHABET = new byte[] { 
/*  98 */       65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
/*  98 */       75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
/*  98 */       85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
/*  98 */       101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
/*  98 */       111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
/*  98 */       121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
/*  98 */       56, 57, 43, 47 };
/*     */   
/*     */   private static final byte[] DECODABET;
/*     */   
/*     */   private static final byte WHITE_SPACE_ENC = -5;
/*     */   
/*     */   private static final byte EQUALS_SIGN_ENC = -1;
/*     */   
/*     */   static {
/*     */     byte[] arrayOfByte;
/*     */   }
/*     */   
/*     */   static {
/*     */     try {
/* 118 */       arrayOfByte = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes("UTF-8");
/* 120 */     } catch (UnsupportedEncodingException use) {
/* 122 */       arrayOfByte = _NATIVE_ALPHABET;
/*     */     } 
/* 124 */     ALPHABET = arrayOfByte;
/* 132 */     DECODABET = new byte[] { 
/* 132 */         -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, 
/* 132 */         -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, 
/* 132 */         -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
/* 132 */         -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, 
/* 132 */         -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 
/* 132 */         54, 55, 56, 57, 58, 59, 60, 61, -9, -9, 
/* 132 */         -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 
/* 132 */         5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
/* 132 */         15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
/* 132 */         25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 
/* 132 */         29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
/* 132 */         39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
/* 132 */         49, 50, 51, -9, -9, -9, -9 };
/*     */   }
/*     */   
/*     */   private static byte[] encode3to4(byte[] b4, byte[] threeBytes, int numSigBytes) {
/* 198 */     encode3to4(threeBytes, 0, numSigBytes, b4, 0);
/* 199 */     return b4;
/*     */   }
/*     */   
/*     */   private static byte[] encode3to4(byte[] source, int srcOffset, int numSigBytes, byte[] destination, int destOffset) {
/* 239 */     int inBuff = ((numSigBytes > 0) ? (source[srcOffset] << 24 >>> 8) : 0) | ((numSigBytes > 1) ? (source[srcOffset + 1] << 24 >>> 16) : 0) | ((numSigBytes > 2) ? (source[srcOffset + 2] << 24 >>> 24) : 0);
/* 243 */     switch (numSigBytes) {
/*     */       case 3:
/* 246 */         destination[destOffset] = ALPHABET[inBuff >>> 18];
/* 247 */         destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3F];
/* 248 */         destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 0x3F];
/* 249 */         destination[destOffset + 3] = ALPHABET[inBuff & 0x3F];
/* 250 */         return destination;
/*     */       case 2:
/* 253 */         destination[destOffset] = ALPHABET[inBuff >>> 18];
/* 254 */         destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3F];
/* 255 */         destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 0x3F];
/* 256 */         destination[destOffset + 3] = 61;
/* 257 */         return destination;
/*     */       case 1:
/* 260 */         destination[destOffset] = ALPHABET[inBuff >>> 18];
/* 261 */         destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3F];
/* 262 */         destination[destOffset + 2] = 61;
/* 263 */         destination[destOffset + 3] = 61;
/* 264 */         return destination;
/*     */     } 
/* 267 */     return destination;
/*     */   }
/*     */   
/*     */   public static String encodeBytes(byte[] source) {
/* 280 */     return encodeBytes(source, 0, source.length, 0);
/*     */   }
/*     */   
/*     */   public static String encodeBytes(byte[] source, int options) {
/* 306 */     return encodeBytes(source, 0, source.length, options);
/*     */   }
/*     */   
/*     */   public static String encodeBytes(byte[] source, int off, int len) {
/* 321 */     return encodeBytes(source, off, len, 0);
/*     */   }
/*     */   
/*     */   public static String encodeBytes(byte[] source, int off, int len, int options) {
/* 350 */     int dontBreakLines = options & 0x8;
/* 355 */     boolean breakLines = (dontBreakLines == 0);
/* 357 */     int len43 = len * 4 / 3;
/* 358 */     byte[] outBuff = new byte[len43 + ((len % 3 > 0) ? 4 : 0) + (breakLines ? (len43 / 76) : 0)];
/* 361 */     int d = 0;
/* 362 */     int e = 0;
/* 363 */     int len2 = len - 2;
/* 364 */     int lineLength = 0;
/* 365 */     for (; d < len2; d += 3, e += 4) {
/* 367 */       encode3to4(source, d + off, 3, outBuff, e);
/* 369 */       lineLength += 4;
/* 370 */       if (breakLines && lineLength == 76) {
/* 372 */         outBuff[e + 4] = 10;
/* 373 */         e++;
/* 374 */         lineLength = 0;
/*     */       } 
/*     */     } 
/* 378 */     if (d < len) {
/* 380 */       encode3to4(source, d + off, len - d, outBuff, e);
/* 381 */       e += 4;
/*     */     } 
/*     */     try {
/* 388 */       return new String(outBuff, 0, e, "UTF-8");
/* 390 */     } catch (UnsupportedEncodingException uue) {
/* 392 */       return new String(outBuff, 0, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int decode4to3(byte[] source, int srcOffset, byte[] destination, int destOffset) {
/* 431 */     if (source[srcOffset + 2] == 61) {
/* 436 */       int outBuff = (DECODABET[source[srcOffset]] & 0xFF) << 18 | (DECODABET[source[srcOffset + 1]] & 0xFF) << 12;
/* 439 */       destination[destOffset] = (byte)(outBuff >>> 16);
/* 440 */       return 1;
/*     */     } 
/* 444 */     if (source[srcOffset + 3] == 61) {
/* 450 */       int outBuff = (DECODABET[source[srcOffset]] & 0xFF) << 18 | (DECODABET[source[srcOffset + 1]] & 0xFF) << 12 | (DECODABET[source[srcOffset + 2]] & 0xFF) << 6;
/* 454 */       destination[destOffset] = (byte)(outBuff >>> 16);
/* 455 */       destination[destOffset + 1] = (byte)(outBuff >>> 8);
/* 456 */       return 2;
/*     */     } 
/*     */     try {
/* 468 */       int outBuff = (DECODABET[source[srcOffset]] & 0xFF) << 18 | (DECODABET[source[srcOffset + 1]] & 0xFF) << 12 | (DECODABET[source[srcOffset + 2]] & 0xFF) << 6 | DECODABET[source[srcOffset + 3]] & 0xFF;
/* 474 */       destination[destOffset] = (byte)(outBuff >> 16);
/* 475 */       destination[destOffset + 1] = (byte)(outBuff >> 8);
/* 476 */       destination[destOffset + 2] = (byte)outBuff;
/* 478 */       return 3;
/* 479 */     } catch (Exception e) {
/* 480 */       System.out.println("" + source[srcOffset] + ": " + DECODABET[source[srcOffset]]);
/* 481 */       System.out.println("" + source[srcOffset + 1] + ": " + DECODABET[source[srcOffset + 1]]);
/* 482 */       System.out.println("" + source[srcOffset + 2] + ": " + DECODABET[source[srcOffset + 2]]);
/* 483 */       System.out.println("" + source[srcOffset + 3] + ": " + DECODABET[source[srcOffset + 3]]);
/* 484 */       return -1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static byte[] decode(byte[] source, int off, int len) {
/* 505 */     int len34 = len * 3 / 4;
/* 506 */     byte[] outBuff = new byte[len34];
/* 507 */     int outBuffPosn = 0;
/* 509 */     byte[] b4 = new byte[4];
/* 510 */     int b4Posn = 0;
/* 511 */     int i = 0;
/* 512 */     byte sbiCrop = 0;
/* 513 */     byte sbiDecode = 0;
/* 514 */     for (i = off; i < off + len; i++) {
/* 516 */       sbiCrop = (byte)(source[i] & Byte.MAX_VALUE);
/* 517 */       sbiDecode = DECODABET[sbiCrop];
/* 519 */       if (sbiDecode >= -5) {
/* 521 */         if (sbiDecode >= -1) {
/* 523 */           b4[b4Posn++] = sbiCrop;
/* 524 */           if (b4Posn > 3) {
/* 526 */             outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn);
/* 527 */             b4Posn = 0;
/* 530 */             if (sbiCrop == 61)
/*     */               break; 
/*     */           } 
/*     */         } 
/*     */       } else {
/* 539 */         System.err.println("Bad Base64 input character at " + i + ": " + source[i] + "(decimal)");
/* 540 */         return null;
/*     */       } 
/*     */     } 
/* 544 */     byte[] out = new byte[outBuffPosn];
/* 545 */     System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
/* 546 */     return out;
/*     */   }
/*     */   
/*     */   public static byte[] decode(String s) {
/*     */     try {
/* 563 */       arrayOfByte = s.getBytes("UTF-8");
/* 565 */     } catch (UnsupportedEncodingException uee) {
/* 567 */       arrayOfByte = s.getBytes();
/*     */     } 
/* 572 */     byte[] arrayOfByte = decode(arrayOfByte, 0, arrayOfByte.length);
/* 574 */     return arrayOfByte;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\Base64.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
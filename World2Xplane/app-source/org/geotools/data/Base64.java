/*      */ package org.geotools.data;
/*      */ 
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FilterInputStream;
/*      */ import java.io.FilterOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.util.zip.GZIPInputStream;
/*      */ import java.util.zip.GZIPOutputStream;
/*      */ 
/*      */ public class Base64 {
/*      */   public static final int NO_OPTIONS = 0;
/*      */   
/*      */   public static final int ENCODE = 1;
/*      */   
/*      */   public static final int DECODE = 0;
/*      */   
/*      */   public static final int GZIP = 2;
/*      */   
/*      */   public static final int DONT_BREAK_LINES = 8;
/*      */   
/*      */   private static final int MAX_LINE_LENGTH = 76;
/*      */   
/*      */   private static final byte EQUALS_SIGN = 61;
/*      */   
/*      */   private static final byte NEW_LINE = 10;
/*      */   
/*      */   private static final String PREFERRED_ENCODING = "UTF-8";
/*      */   
/*      */   private static final byte[] ALPHABET;
/*      */   
/*      */   static {
/*      */     byte[] arrayOfByte;
/*      */   }
/*      */   
/*  127 */   private static final byte[] _NATIVE_ALPHABET = new byte[] { 
/*  127 */       65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
/*  127 */       75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
/*  127 */       85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
/*  127 */       101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
/*  127 */       111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
/*  127 */       121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
/*  127 */       56, 57, 43, 47 };
/*      */   
/*      */   private static final byte[] DECODABET;
/*      */   
/*      */   private static final byte WHITE_SPACE_ENC = -5;
/*      */   
/*      */   private static final byte EQUALS_SIGN_ENC = -1;
/*      */   
/*      */   static {
/*      */     try {
/*  147 */       arrayOfByte = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes("UTF-8");
/*  149 */     } catch (UnsupportedEncodingException use) {
/*  151 */       arrayOfByte = _NATIVE_ALPHABET;
/*      */     } 
/*  153 */     ALPHABET = arrayOfByte;
/*  161 */     DECODABET = new byte[] { 
/*  161 */         -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, 
/*  161 */         -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, 
/*  161 */         -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
/*  161 */         -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, 
/*  161 */         -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 
/*  161 */         54, 55, 56, 57, 58, 59, 60, 61, -9, -9, 
/*  161 */         -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 
/*  161 */         5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
/*  161 */         15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
/*  161 */         25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 
/*  161 */         29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
/*  161 */         39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
/*  161 */         49, 50, 51, -9, -9, -9, -9 };
/*      */   }
/*      */   
/*      */   private static byte[] encode3to4(byte[] b4, byte[] threeBytes, int numSigBytes) {
/*  227 */     encode3to4(threeBytes, 0, numSigBytes, b4, 0);
/*  228 */     return b4;
/*      */   }
/*      */   
/*      */   private static byte[] encode3to4(byte[] source, int srcOffset, int numSigBytes, byte[] destination, int destOffset) {
/*  268 */     int inBuff = ((numSigBytes > 0) ? (source[srcOffset] << 24 >>> 8) : 0) | ((numSigBytes > 1) ? (source[srcOffset + 1] << 24 >>> 16) : 0) | ((numSigBytes > 2) ? (source[srcOffset + 2] << 24 >>> 24) : 0);
/*  272 */     switch (numSigBytes) {
/*      */       case 3:
/*  275 */         destination[destOffset] = ALPHABET[inBuff >>> 18];
/*  276 */         destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3F];
/*  277 */         destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 0x3F];
/*  278 */         destination[destOffset + 3] = ALPHABET[inBuff & 0x3F];
/*  279 */         return destination;
/*      */       case 2:
/*  282 */         destination[destOffset] = ALPHABET[inBuff >>> 18];
/*  283 */         destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3F];
/*  284 */         destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 0x3F];
/*  285 */         destination[destOffset + 3] = 61;
/*  286 */         return destination;
/*      */       case 1:
/*  289 */         destination[destOffset] = ALPHABET[inBuff >>> 18];
/*  290 */         destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3F];
/*  291 */         destination[destOffset + 2] = 61;
/*  292 */         destination[destOffset + 3] = 61;
/*  293 */         return destination;
/*      */     } 
/*  296 */     return destination;
/*      */   }
/*      */   
/*      */   public static String encodeObject(Serializable serializableObject) {
/*  315 */     return encodeObject(serializableObject, 0);
/*      */   }
/*      */   
/*      */   public static String encodeObject(Serializable serializableObject, int options) {
/*  346 */     ByteArrayOutputStream baos = null;
/*  347 */     java.io.OutputStream b64os = null;
/*  348 */     ObjectOutputStream oos = null;
/*  349 */     GZIPOutputStream gzos = null;
/*  352 */     int gzip = options & 0x2;
/*  353 */     int dontBreakLines = options & 0x8;
/*      */     try {
/*  358 */       baos = new ByteArrayOutputStream();
/*  359 */       b64os = new OutputStream(baos, 0x1 | dontBreakLines);
/*  362 */       if (gzip == 2) {
/*  364 */         gzos = new GZIPOutputStream(b64os);
/*  365 */         oos = new ObjectOutputStream(gzos);
/*      */       } else {
/*  368 */         oos = new ObjectOutputStream(b64os);
/*      */       } 
/*  370 */       oos.writeObject(serializableObject);
/*  372 */     } catch (IOException e) {
/*  374 */       e.printStackTrace();
/*  375 */       return null;
/*      */     } finally {
/*      */       try {
/*  379 */         oos.close();
/*  379 */       } catch (Exception e) {}
/*      */       try {
/*  380 */         gzos.close();
/*  380 */       } catch (Exception e) {}
/*      */       try {
/*  381 */         b64os.close();
/*  381 */       } catch (Exception e) {}
/*      */       try {
/*  382 */         baos.close();
/*  382 */       } catch (Exception e) {}
/*      */     } 
/*      */     try {
/*  388 */       return new String(baos.toByteArray(), "UTF-8");
/*  390 */     } catch (UnsupportedEncodingException uue) {
/*  392 */       return new String(baos.toByteArray());
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String encodeBytes(byte[] source) {
/*  408 */     return encodeBytes(source, 0, source.length, 0);
/*      */   }
/*      */   
/*      */   public static String encodeBytes(byte[] source, int options) {
/*  435 */     return encodeBytes(source, 0, source.length, options);
/*      */   }
/*      */   
/*      */   public static String encodeBytes(byte[] source, int off, int len) {
/*  450 */     return encodeBytes(source, off, len, 0);
/*      */   }
/*      */   
/*      */   public static String encodeBytes(byte[] source, int off, int len, int options) {
/*  480 */     int dontBreakLines = options & 0x8;
/*  481 */     int gzip = options & 0x2;
/*  484 */     if (gzip == 2) {
/*  486 */       ByteArrayOutputStream baos = null;
/*  487 */       GZIPOutputStream gzos = null;
/*  488 */       OutputStream b64os = null;
/*      */       try {
/*  494 */         baos = new ByteArrayOutputStream();
/*  495 */         b64os = new OutputStream(baos, 0x1 | dontBreakLines);
/*  496 */         gzos = new GZIPOutputStream(b64os);
/*  498 */         gzos.write(source, off, len);
/*  499 */         gzos.close();
/*  501 */       } catch (IOException iOException) {
/*  503 */         iOException.printStackTrace();
/*  504 */         return null;
/*      */       } finally {
/*      */         try {
/*  508 */           gzos.close();
/*  508 */         } catch (Exception exception) {}
/*      */         try {
/*  509 */           b64os.close();
/*  509 */         } catch (Exception exception) {}
/*      */         try {
/*  510 */           baos.close();
/*  510 */         } catch (Exception exception) {}
/*      */       } 
/*      */       try {
/*  516 */         return new String(baos.toByteArray(), "UTF-8");
/*  518 */       } catch (UnsupportedEncodingException uue) {
/*  520 */         return new String(baos.toByteArray());
/*      */       } 
/*      */     } 
/*  528 */     boolean breakLines = (dontBreakLines == 0);
/*  530 */     int len43 = len * 4 / 3;
/*  531 */     byte[] outBuff = new byte[len43 + ((len % 3 > 0) ? 4 : 0) + (breakLines ? (len43 / 76) : 0)];
/*  534 */     int d = 0;
/*  535 */     int e = 0;
/*  536 */     int len2 = len - 2;
/*  537 */     int lineLength = 0;
/*  538 */     for (; d < len2; d += 3, e += 4) {
/*  540 */       encode3to4(source, d + off, 3, outBuff, e);
/*  542 */       lineLength += 4;
/*  543 */       if (breakLines && lineLength == 76) {
/*  545 */         outBuff[e + 4] = 10;
/*  546 */         e++;
/*  547 */         lineLength = 0;
/*      */       } 
/*      */     } 
/*  551 */     if (d < len) {
/*  553 */       encode3to4(source, d + off, len - d, outBuff, e);
/*  554 */       e += 4;
/*      */     } 
/*      */     try {
/*  561 */       return new String(outBuff, 0, e, "UTF-8");
/*  563 */     } catch (UnsupportedEncodingException uue) {
/*  565 */       return new String(outBuff, 0, e);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int decode4to3(byte[] source, int srcOffset, byte[] destination, int destOffset) {
/*  603 */     if (source[srcOffset + 2] == 61) {
/*  608 */       int outBuff = (DECODABET[source[srcOffset]] & 0xFF) << 18 | (DECODABET[source[srcOffset + 1]] & 0xFF) << 12;
/*  611 */       destination[destOffset] = (byte)(outBuff >>> 16);
/*  612 */       return 1;
/*      */     } 
/*  616 */     if (source[srcOffset + 3] == 61) {
/*  622 */       int outBuff = (DECODABET[source[srcOffset]] & 0xFF) << 18 | (DECODABET[source[srcOffset + 1]] & 0xFF) << 12 | (DECODABET[source[srcOffset + 2]] & 0xFF) << 6;
/*  626 */       destination[destOffset] = (byte)(outBuff >>> 16);
/*  627 */       destination[destOffset + 1] = (byte)(outBuff >>> 8);
/*  628 */       return 2;
/*      */     } 
/*      */     try {
/*  640 */       int outBuff = (DECODABET[source[srcOffset]] & 0xFF) << 18 | (DECODABET[source[srcOffset + 1]] & 0xFF) << 12 | (DECODABET[source[srcOffset + 2]] & 0xFF) << 6 | DECODABET[source[srcOffset + 3]] & 0xFF;
/*  646 */       destination[destOffset] = (byte)(outBuff >> 16);
/*  647 */       destination[destOffset + 1] = (byte)(outBuff >> 8);
/*  648 */       destination[destOffset + 2] = (byte)outBuff;
/*  650 */       return 3;
/*  651 */     } catch (Exception e) {
/*  652 */       System.out.println("" + source[srcOffset] + ": " + DECODABET[source[srcOffset]]);
/*  653 */       System.out.println("" + source[srcOffset + 1] + ": " + DECODABET[source[srcOffset + 1]]);
/*  654 */       System.out.println("" + source[srcOffset + 2] + ": " + DECODABET[source[srcOffset + 2]]);
/*  655 */       System.out.println("" + source[srcOffset + 3] + ": " + DECODABET[source[srcOffset + 3]]);
/*  656 */       return -1;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static byte[] decode(byte[] source) {
/*  720 */     int len = source.length;
/*  721 */     int len34 = len * 3 / 4;
/*  722 */     byte[] outBuff = new byte[len34];
/*  723 */     int outBuffPosn = 0;
/*  725 */     byte[] b4 = new byte[4];
/*  726 */     int b4Posn = 0;
/*  727 */     int i = 0;
/*  729 */     byte sbiDecode = 0;
/*  730 */     for (i = 0; i < len; i++) {
/*  733 */       sbiDecode = DECODABET[source[i]];
/*  735 */       if (sbiDecode > -1) {
/*  737 */         b4[b4Posn++] = sbiDecode;
/*  738 */         if (b4Posn > 3) {
/*  740 */           int temp = (b4[0] & 0xFF) << 18 | (b4[1] & 0xFF) << 12 | (b4[2] & 0xFF) << 6 | b4[3] & 0xFF;
/*  746 */           outBuff[outBuffPosn++] = (byte)(temp >> 16);
/*  747 */           outBuff[outBuffPosn++] = (byte)(temp >> 8);
/*  748 */           outBuff[outBuffPosn++] = (byte)temp;
/*  750 */           b4Posn = 0;
/*      */         } 
/*  753 */       } else if (sbiDecode == -1) {
/*  755 */         b4[b4Posn++] = 0;
/*  756 */         if (b4Posn > 3) {
/*  758 */           int temp = (b4[0] & 0xFF) << 18 | (b4[1] & 0xFF) << 12 | (b4[2] & 0xFF) << 6 | b4[3] & 0xFF;
/*  764 */           outBuff[outBuffPosn++] = (byte)(temp >> 16);
/*  765 */           outBuff[outBuffPosn++] = (byte)(temp >> 8);
/*  766 */           outBuff[outBuffPosn++] = (byte)temp;
/*  768 */           b4Posn = 0;
/*      */         } 
/*      */       } 
/*      */     } 
/*  772 */     return outBuff;
/*      */   }
/*      */   
/*      */   public static byte[] decode(byte[] source, int off, int len) {
/*  791 */     int len34 = len * 3 / 4;
/*  792 */     byte[] outBuff = new byte[len34];
/*  793 */     int outBuffPosn = 0;
/*  795 */     byte[] b4 = new byte[4];
/*  796 */     int b4Posn = 0;
/*  797 */     int i = 0;
/*  798 */     byte sbiCrop = 0;
/*  799 */     byte sbiDecode = 0;
/*  800 */     for (i = off; i < off + len; i++) {
/*  802 */       sbiCrop = (byte)(source[i] & Byte.MAX_VALUE);
/*  803 */       sbiDecode = DECODABET[sbiCrop];
/*  805 */       if (sbiDecode >= -5) {
/*  807 */         if (sbiDecode >= -1) {
/*  809 */           b4[b4Posn++] = sbiCrop;
/*  810 */           if (b4Posn > 3) {
/*  812 */             outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn);
/*  813 */             b4Posn = 0;
/*  816 */             if (sbiCrop == 61)
/*      */               break; 
/*      */           } 
/*      */         } 
/*      */       } else {
/*  825 */         System.err.println("Bad Base64 input character at " + i + ": " + source[i] + "(decimal)");
/*  826 */         return null;
/*      */       } 
/*      */     } 
/*  830 */     byte[] out = new byte[outBuffPosn];
/*  831 */     System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
/*  832 */     return out;
/*      */   }
/*      */   
/*      */   public static byte[] decode(String s) {
/*      */     try {
/*  852 */       arrayOfByte = s.getBytes("UTF-8");
/*  854 */     } catch (UnsupportedEncodingException uee) {
/*  856 */       arrayOfByte = s.getBytes();
/*      */     } 
/*  861 */     byte[] arrayOfByte = decode(arrayOfByte, 0, arrayOfByte.length);
/*  866 */     if (arrayOfByte != null && arrayOfByte.length >= 4) {
/*  869 */       int head = arrayOfByte[0] & 0xFF | arrayOfByte[1] << 8 & 0xFF00;
/*  870 */       if (35615 == head) {
/*  872 */         ByteArrayInputStream bais = null;
/*  873 */         GZIPInputStream gzis = null;
/*  874 */         ByteArrayOutputStream baos = null;
/*  875 */         byte[] buffer = new byte[2048];
/*  876 */         int length = 0;
/*      */         try {
/*  880 */           baos = new ByteArrayOutputStream();
/*  881 */           bais = new ByteArrayInputStream(arrayOfByte);
/*  882 */           gzis = new GZIPInputStream(bais);
/*  884 */           while ((length = gzis.read(buffer)) >= 0)
/*  886 */             baos.write(buffer, 0, length); 
/*  890 */           arrayOfByte = baos.toByteArray();
/*  893 */         } catch (IOException e) {
/*      */         
/*      */         } finally {
/*      */           try {
/*  899 */             baos.close();
/*  899 */           } catch (Exception e) {}
/*      */           try {
/*  900 */             gzis.close();
/*  900 */           } catch (Exception e) {}
/*      */           try {
/*  901 */             bais.close();
/*  901 */           } catch (Exception e) {}
/*      */         } 
/*      */       } 
/*      */     } 
/*  907 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */   public static Object decodeToObject(String encodedObject) {
/*  924 */     byte[] objBytes = decode(encodedObject);
/*  926 */     ByteArrayInputStream bais = null;
/*  927 */     ObjectInputStream ois = null;
/*  928 */     Object obj = null;
/*      */     try {
/*  932 */       bais = new ByteArrayInputStream(objBytes);
/*  933 */       ois = new ObjectInputStream(bais);
/*  935 */       obj = ois.readObject();
/*  937 */     } catch (IOException e) {
/*  939 */       e.printStackTrace();
/*  940 */       obj = null;
/*  942 */     } catch (ClassNotFoundException e) {
/*  944 */       e.printStackTrace();
/*  945 */       obj = null;
/*      */     } finally {
/*      */       try {
/*  949 */         bais.close();
/*  949 */       } catch (Exception e) {}
/*      */       try {
/*  950 */         ois.close();
/*  950 */       } catch (Exception e) {}
/*      */     } 
/*  953 */     return obj;
/*      */   }
/*      */   
/*      */   public static boolean encodeToFile(byte[] dataToEncode, String filename) {
/*  969 */     boolean success = false;
/*  970 */     OutputStream bos = null;
/*      */     try {
/*  973 */       bos = new OutputStream(new FileOutputStream(filename), 1);
/*  975 */       bos.write(dataToEncode);
/*  976 */       success = true;
/*  978 */     } catch (IOException e) {
/*  981 */       success = false;
/*      */     } finally {
/*      */       try {
/*  985 */         bos.close();
/*  985 */       } catch (Exception e) {}
/*      */     } 
/*  988 */     return success;
/*      */   }
/*      */   
/*      */   public static boolean decodeToFile(String dataToDecode, String filename) {
/* 1003 */     boolean success = false;
/* 1004 */     OutputStream bos = null;
/*      */     try {
/* 1007 */       bos = new OutputStream(new FileOutputStream(filename), 0);
/* 1009 */       bos.write(dataToDecode.getBytes("UTF-8"));
/* 1010 */       success = true;
/* 1012 */     } catch (IOException e) {
/* 1014 */       success = false;
/*      */     } finally {
/*      */       try {
/* 1018 */         bos.close();
/* 1018 */       } catch (Exception e) {}
/*      */     } 
/* 1021 */     return success;
/*      */   }
/*      */   
/*      */   public static byte[] decodeFromFile(String filename) {
/* 1038 */     byte[] decodedData = null;
/* 1039 */     InputStream bis = null;
/*      */     try {
/* 1043 */       File file = new File(filename);
/* 1044 */       byte[] buffer = null;
/* 1045 */       int length = 0;
/* 1046 */       int numBytes = 0;
/* 1049 */       if (file.length() > 2147483647L) {
/* 1051 */         System.err.println("File is too big for this convenience method (" + file.length() + " bytes).");
/* 1052 */         return null;
/*      */       } 
/* 1054 */       buffer = new byte[(int)file.length()];
/* 1057 */       bis = new InputStream(new BufferedInputStream(new FileInputStream(file)), 0);
/* 1062 */       while ((numBytes = bis.read(buffer, length, 4096)) >= 0)
/* 1063 */         length += numBytes; 
/* 1066 */       decodedData = new byte[length];
/* 1067 */       System.arraycopy(buffer, 0, decodedData, 0, length);
/* 1070 */     } catch (IOException e) {
/* 1072 */       System.err.println("Error decoding from file " + filename);
/*      */     } finally {
/*      */       try {
/* 1076 */         bis.close();
/* 1076 */       } catch (Exception e) {}
/*      */     } 
/* 1079 */     return decodedData;
/*      */   }
/*      */   
/*      */   public static String encodeFromFile(String filename) {
/* 1095 */     String encodedData = null;
/* 1096 */     InputStream bis = null;
/*      */     try {
/* 1100 */       File file = new File(filename);
/* 1101 */       byte[] buffer = new byte[(int)(file.length() * 1.4D)];
/* 1102 */       int length = 0;
/* 1103 */       int numBytes = 0;
/* 1106 */       bis = new InputStream(new BufferedInputStream(new FileInputStream(file)), 1);
/* 1111 */       while ((numBytes = bis.read(buffer, length, 4096)) >= 0)
/* 1112 */         length += numBytes; 
/* 1115 */       encodedData = new String(buffer, 0, length, "UTF-8");
/* 1118 */     } catch (IOException e) {
/* 1120 */       System.err.println("Error encoding from file " + filename);
/*      */     } finally {
/*      */       try {
/* 1124 */         bis.close();
/* 1124 */       } catch (Exception e) {}
/*      */     } 
/* 1127 */     return encodedData;
/*      */   }
/*      */   
/*      */   public static class InputStream extends FilterInputStream {
/*      */     private boolean encode;
/*      */     
/*      */     private int position;
/*      */     
/*      */     private byte[] buffer;
/*      */     
/*      */     private int bufferLength;
/*      */     
/*      */     private int numSigBytes;
/*      */     
/*      */     private int lineLength;
/*      */     
/*      */     private boolean breakLines;
/*      */     
/*      */     public InputStream(java.io.InputStream in) {
/* 1164 */       this(in, 0);
/*      */     }
/*      */     
/*      */     public InputStream(java.io.InputStream in, int options) {
/* 1191 */       super(in);
/* 1192 */       this.breakLines = ((options & 0x8) != 8);
/* 1193 */       this.encode = ((options & 0x1) == 1);
/* 1194 */       this.bufferLength = this.encode ? 4 : 3;
/* 1195 */       this.buffer = new byte[this.bufferLength];
/* 1196 */       this.position = -1;
/* 1197 */       this.lineLength = 0;
/*      */     }
/*      */     
/*      */     public int read() throws IOException {
/* 1210 */       if (this.position < 0)
/* 1212 */         if (this.encode) {
/* 1214 */           byte[] b3 = new byte[3];
/* 1215 */           int numBinaryBytes = 0;
/* 1216 */           for (int i = 0; i < 3; i++) {
/*      */             try {
/* 1220 */               int b = this.in.read();
/* 1223 */               if (b >= 0) {
/* 1225 */                 b3[i] = (byte)b;
/* 1226 */                 numBinaryBytes++;
/*      */               } 
/* 1230 */             } catch (IOException e) {
/* 1233 */               if (i == 0)
/* 1234 */                 throw e; 
/*      */             } 
/*      */           } 
/* 1239 */           if (numBinaryBytes > 0) {
/* 1241 */             Base64.encode3to4(b3, 0, numBinaryBytes, this.buffer, 0);
/* 1242 */             this.position = 0;
/* 1243 */             this.numSigBytes = 4;
/*      */           } else {
/* 1247 */             return -1;
/*      */           } 
/*      */         } else {
/* 1254 */           byte[] b4 = new byte[4];
/* 1255 */           int i = 0;
/* 1256 */           for (i = 0; i < 4; i++) {
/* 1259 */             int b = 0;
/*      */             do {
/* 1260 */               b = this.in.read();
/* 1261 */             } while (b >= 0 && Base64.DECODABET[b & 0x7F] <= -5);
/* 1263 */             if (b < 0)
/*      */               break; 
/* 1266 */             b4[i] = (byte)b;
/*      */           } 
/* 1269 */           if (i == 4) {
/* 1271 */             this.numSigBytes = Base64.decode4to3(b4, 0, this.buffer, 0);
/* 1272 */             this.position = 0;
/*      */           } else {
/* 1274 */             if (i == 0)
/* 1275 */               return -1; 
/* 1280 */             throw new IOException("Improperly padded Base64 input.");
/*      */           } 
/*      */         }  
/* 1287 */       if (this.position >= 0) {
/* 1290 */         if (this.position >= this.numSigBytes)
/* 1291 */           return -1; 
/* 1293 */         if (this.encode && this.breakLines && this.lineLength >= 76) {
/* 1295 */           this.lineLength = 0;
/* 1296 */           return 10;
/*      */         } 
/* 1300 */         this.lineLength++;
/* 1304 */         int b = this.buffer[this.position++];
/* 1306 */         if (this.position >= this.bufferLength)
/* 1307 */           this.position = -1; 
/* 1309 */         return b & 0xFF;
/*      */       } 
/* 1318 */       throw new IOException("Error in Base64 code reading stream.");
/*      */     }
/*      */     
/*      */     public int read(byte[] dest, int off, int len) throws IOException {
/*      */       int i;
/* 1339 */       for (i = 0; i < len; i++) {
/* 1341 */         int b = read();
/* 1346 */         if (b >= 0) {
/* 1347 */           dest[off + i] = (byte)b;
/*      */         } else {
/* 1348 */           if (i == 0)
/* 1349 */             return -1; 
/*      */           break;
/*      */         } 
/*      */       } 
/* 1353 */       return i;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class OutputStream extends FilterOutputStream {
/*      */     private boolean encode;
/*      */     
/*      */     private int position;
/*      */     
/*      */     private byte[] buffer;
/*      */     
/*      */     private int bufferLength;
/*      */     
/*      */     private int lineLength;
/*      */     
/*      */     private boolean breakLines;
/*      */     
/*      */     private byte[] b4;
/*      */     
/*      */     private boolean suspendEncoding;
/*      */     
/*      */     public OutputStream(java.io.OutputStream out) {
/* 1394 */       this(out, 1);
/*      */     }
/*      */     
/*      */     public OutputStream(java.io.OutputStream out, int options) {
/* 1420 */       super(out);
/* 1421 */       this.breakLines = ((options & 0x8) != 8);
/* 1422 */       this.encode = ((options & 0x1) == 1);
/* 1423 */       this.bufferLength = this.encode ? 3 : 4;
/* 1424 */       this.buffer = new byte[this.bufferLength];
/* 1425 */       this.position = 0;
/* 1426 */       this.lineLength = 0;
/* 1427 */       this.suspendEncoding = false;
/* 1428 */       this.b4 = new byte[4];
/*      */     }
/*      */     
/*      */     public void write(int theByte) throws IOException {
/* 1447 */       if (this.suspendEncoding) {
/* 1449 */         this.out.write(theByte);
/*      */         return;
/*      */       } 
/* 1454 */       if (this.encode) {
/* 1456 */         this.buffer[this.position++] = (byte)theByte;
/* 1457 */         if (this.position >= this.bufferLength) {
/* 1459 */           this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength));
/* 1461 */           this.lineLength += 4;
/* 1462 */           if (this.breakLines && this.lineLength >= 76) {
/* 1464 */             this.out.write(10);
/* 1465 */             this.lineLength = 0;
/*      */           } 
/* 1468 */           this.position = 0;
/*      */         } 
/* 1476 */       } else if (Base64.DECODABET[theByte & 0x7F] > -5) {
/* 1478 */         this.buffer[this.position++] = (byte)theByte;
/* 1479 */         if (this.position >= this.bufferLength) {
/* 1481 */           int len = Base64.decode4to3(this.buffer, 0, this.b4, 0);
/* 1482 */           this.out.write(this.b4, 0, len);
/* 1484 */           this.position = 0;
/*      */         } 
/* 1487 */       } else if (Base64.DECODABET[theByte & 0x7F] != -5) {
/* 1489 */         throw new IOException("Invalid character in Base64 data.");
/*      */       } 
/*      */     }
/*      */     
/*      */     public void write(byte[] theBytes, int off, int len) throws IOException {
/* 1508 */       if (this.suspendEncoding) {
/* 1510 */         this.out.write(theBytes, off, len);
/*      */         return;
/*      */       } 
/* 1514 */       for (int i = 0; i < len; i++)
/* 1516 */         write(theBytes[off + i]); 
/*      */     }
/*      */     
/*      */     public void flushBase64() throws IOException {
/* 1529 */       if (this.position > 0)
/* 1531 */         if (this.encode) {
/* 1533 */           this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position));
/* 1534 */           this.position = 0;
/*      */         } else {
/* 1538 */           throw new IOException("Base64 input not properly padded.");
/*      */         }  
/*      */     }
/*      */     
/*      */     public void close() throws IOException {
/* 1553 */       flushBase64();
/* 1557 */       super.close();
/* 1559 */       this.buffer = null;
/* 1560 */       this.out = null;
/*      */     }
/*      */     
/*      */     public void suspendEncoding() throws IOException {
/* 1574 */       flushBase64();
/* 1575 */       this.suspendEncoding = true;
/*      */     }
/*      */     
/*      */     public void resumeEncoding() {
/* 1588 */       this.suspendEncoding = false;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\Base64.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.compress.archivers.tar;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.math.BigInteger;
/*     */ import java.nio.ByteBuffer;
/*     */ import org.apache.commons.compress.archivers.zip.ZipEncoding;
/*     */ import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
/*     */ 
/*     */ public class TarUtils {
/*     */   private static final int BYTE_MASK = 255;
/*     */   
/*  37 */   static final ZipEncoding DEFAULT_ENCODING = ZipEncodingHelper.getZipEncoding(null);
/*     */   
/*  44 */   static final ZipEncoding FALLBACK_ENCODING = new ZipEncoding() {
/*     */       public boolean canEncode(String name) {
/*  45 */         return true;
/*     */       }
/*     */       
/*     */       public ByteBuffer encode(String name) {
/*  48 */         int length = name.length();
/*  49 */         byte[] buf = new byte[length];
/*  52 */         for (int i = 0; i < length; i++)
/*  53 */           buf[i] = (byte)name.charAt(i); 
/*  55 */         return ByteBuffer.wrap(buf);
/*     */       }
/*     */       
/*     */       public String decode(byte[] buffer) {
/*  59 */         int length = buffer.length;
/*  60 */         StringBuffer result = new StringBuffer(length);
/*  62 */         for (int i = 0; i < length; i++) {
/*  63 */           byte b = buffer[i];
/*  64 */           if (b == 0)
/*     */             break; 
/*  67 */           result.append((char)(b & 0xFF));
/*     */         } 
/*  70 */         return result.toString();
/*     */       }
/*     */     };
/*     */   
/*     */   public static long parseOctal(byte[] buffer, int offset, int length) {
/* 100 */     long result = 0L;
/* 101 */     int end = offset + length;
/* 102 */     int start = offset;
/* 104 */     if (length < 2)
/* 105 */       throw new IllegalArgumentException("Length " + length + " must be at least 2"); 
/* 108 */     if (buffer[start] == 0)
/* 109 */       return 0L; 
/* 113 */     while (start < end && 
/* 114 */       buffer[start] == 32)
/* 115 */       start++; 
/* 123 */     byte trailer = buffer[end - 1];
/* 124 */     if (trailer == 0 || trailer == 32) {
/* 125 */       end--;
/*     */     } else {
/* 127 */       throw new IllegalArgumentException(exceptionMessage(buffer, offset, length, end - 1, trailer));
/*     */     } 
/* 131 */     trailer = buffer[end - 1];
/* 132 */     if (trailer == 0 || trailer == 32)
/* 133 */       end--; 
/* 136 */     for (; start < end; start++) {
/* 137 */       byte currentByte = buffer[start];
/* 139 */       if (currentByte < 48 || currentByte > 55)
/* 140 */         throw new IllegalArgumentException(exceptionMessage(buffer, offset, length, start, currentByte)); 
/* 143 */       result = (result << 3L) + (currentByte - 48);
/*     */     } 
/* 147 */     return result;
/*     */   }
/*     */   
/*     */   public static long parseOctalOrBinary(byte[] buffer, int offset, int length) {
/* 170 */     if ((buffer[offset] & 0x80) == 0)
/* 171 */       return parseOctal(buffer, offset, length); 
/* 173 */     boolean negative = (buffer[offset] == -1);
/* 174 */     if (length < 9)
/* 175 */       return parseBinaryLong(buffer, offset, length, negative); 
/* 177 */     return parseBinaryBigInteger(buffer, offset, length, negative);
/*     */   }
/*     */   
/*     */   private static long parseBinaryLong(byte[] buffer, int offset, int length, boolean negative) {
/* 183 */     if (length >= 9)
/* 184 */       throw new IllegalArgumentException("At offset " + offset + ", " + length + " byte binary number" + " exceeds maximum signed long" + " value"); 
/* 189 */     long val = 0L;
/* 190 */     for (int i = 1; i < length; i++)
/* 191 */       val = (val << 8L) + (buffer[offset + i] & 0xFF); 
/* 193 */     if (negative) {
/* 195 */       val--;
/* 196 */       val ^= (long)Math.pow(2.0D, ((length - 1) * 8)) - 1L;
/*     */     } 
/* 198 */     return negative ? -val : val;
/*     */   }
/*     */   
/*     */   private static long parseBinaryBigInteger(byte[] buffer, int offset, int length, boolean negative) {
/* 205 */     byte[] remainder = new byte[length - 1];
/* 206 */     System.arraycopy(buffer, offset + 1, remainder, 0, length - 1);
/* 207 */     BigInteger val = new BigInteger(remainder);
/* 208 */     if (negative)
/* 210 */       val = val.add(BigInteger.valueOf(-1L)).not(); 
/* 212 */     if (val.bitLength() > 63)
/* 213 */       throw new IllegalArgumentException("At offset " + offset + ", " + length + " byte binary number" + " exceeds maximum signed long" + " value"); 
/* 218 */     return negative ? -val.longValue() : val.longValue();
/*     */   }
/*     */   
/*     */   public static boolean parseBoolean(byte[] buffer, int offset) {
/* 232 */     return (buffer[offset] == 1);
/*     */   }
/*     */   
/*     */   private static String exceptionMessage(byte[] buffer, int offset, int length, int current, byte currentByte) {
/* 238 */     String string = new String(buffer, offset, length);
/* 239 */     string = string.replaceAll("\000", "{NUL}");
/* 240 */     String s = "Invalid byte " + currentByte + " at offset " + (current - offset) + " in '" + string + "' len=" + length;
/* 241 */     return s;
/*     */   }
/*     */   
/*     */   public static String parseName(byte[] buffer, int offset, int length) {
/*     */     try {
/* 256 */       return parseName(buffer, offset, length, DEFAULT_ENCODING);
/* 257 */     } catch (IOException ex) {
/*     */       try {
/* 259 */         return parseName(buffer, offset, length, FALLBACK_ENCODING);
/* 260 */       } catch (IOException ex2) {
/* 262 */         throw new RuntimeException(ex2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String parseName(byte[] buffer, int offset, int length, ZipEncoding encoding) throws IOException {
/* 284 */     int len = length;
/* 285 */     for (; len > 0 && 
/* 286 */       buffer[offset + len - 1] == 0; len--);
/* 290 */     if (len > 0) {
/* 291 */       byte[] b = new byte[len];
/* 292 */       System.arraycopy(buffer, offset, b, 0, len);
/* 293 */       return encoding.decode(b);
/*     */     } 
/* 295 */     return "";
/*     */   }
/*     */   
/*     */   public static int formatNameBytes(String name, byte[] buf, int offset, int length) {
/*     */     try {
/* 315 */       return formatNameBytes(name, buf, offset, length, DEFAULT_ENCODING);
/* 316 */     } catch (IOException ex) {
/*     */       try {
/* 318 */         return formatNameBytes(name, buf, offset, length, FALLBACK_ENCODING);
/* 320 */       } catch (IOException ex2) {
/* 322 */         throw new RuntimeException(ex2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int formatNameBytes(String name, byte[] buf, int offset, int length, ZipEncoding encoding) throws IOException {
/* 348 */     int len = name.length();
/* 349 */     ByteBuffer b = encoding.encode(name);
/* 350 */     while (b.limit() > length && len > 0)
/* 351 */       b = encoding.encode(name.substring(0, --len)); 
/* 353 */     int limit = b.limit();
/* 354 */     System.arraycopy(b.array(), b.arrayOffset(), buf, offset, limit);
/* 357 */     for (int i = limit; i < length; i++)
/* 358 */       buf[offset + i] = 0; 
/* 361 */     return offset + length;
/*     */   }
/*     */   
/*     */   public static void formatUnsignedOctalString(long value, byte[] buffer, int offset, int length) {
/* 375 */     int remaining = length;
/* 376 */     remaining--;
/* 377 */     if (value == 0L) {
/* 378 */       buffer[offset + remaining--] = 48;
/*     */     } else {
/* 380 */       long val = value;
/* 381 */       for (; remaining >= 0 && val != 0L; remaining--) {
/* 383 */         buffer[offset + remaining] = (byte)(48 + (byte)(int)(val & 0x7L));
/* 384 */         val >>>= 3L;
/*     */       } 
/* 387 */       if (val != 0L)
/* 388 */         throw new IllegalArgumentException(value + "=" + Long.toOctalString(value) + " will not fit in octal number buffer of length " + length); 
/*     */     } 
/* 393 */     for (; remaining >= 0; remaining--)
/* 394 */       buffer[offset + remaining] = 48; 
/*     */   }
/*     */   
/*     */   public static int formatOctalBytes(long value, byte[] buf, int offset, int length) {
/* 414 */     int idx = length - 2;
/* 415 */     formatUnsignedOctalString(value, buf, offset, idx);
/* 417 */     buf[offset + idx++] = 32;
/* 418 */     buf[offset + idx] = 0;
/* 420 */     return offset + length;
/*     */   }
/*     */   
/*     */   public static int formatLongOctalBytes(long value, byte[] buf, int offset, int length) {
/* 439 */     int idx = length - 1;
/* 441 */     formatUnsignedOctalString(value, buf, offset, idx);
/* 442 */     buf[offset + idx] = 32;
/* 444 */     return offset + length;
/*     */   }
/*     */   
/*     */   public static int formatLongOctalOrBinaryBytes(long value, byte[] buf, int offset, int length) {
/* 468 */     long maxAsOctalChar = (length == 8) ? 2097151L : 8589934591L;
/* 470 */     boolean negative = (value < 0L);
/* 471 */     if (!negative && value <= maxAsOctalChar)
/* 472 */       return formatLongOctalBytes(value, buf, offset, length); 
/* 475 */     if (length < 9)
/* 476 */       formatLongBinary(value, buf, offset, length, negative); 
/* 478 */     formatBigIntegerBinary(value, buf, offset, length, negative);
/* 480 */     buf[offset] = (byte)(negative ? 255 : 128);
/* 481 */     return offset + length;
/*     */   }
/*     */   
/*     */   private static void formatLongBinary(long value, byte[] buf, int offset, int length, boolean negative) {
/* 487 */     int bits = (length - 1) * 8;
/* 488 */     long max = 1L << bits;
/* 489 */     long val = Math.abs(value);
/* 490 */     if (val >= max)
/* 491 */       throw new IllegalArgumentException("Value " + value + " is too large for " + length + " byte field."); 
/* 494 */     if (negative) {
/* 495 */       val ^= max - 1L;
/* 496 */       val |= (255 << bits);
/* 497 */       val++;
/*     */     } 
/* 499 */     for (int i = offset + length - 1; i >= offset; i--) {
/* 500 */       buf[i] = (byte)(int)val;
/* 501 */       val >>= 8L;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void formatBigIntegerBinary(long value, byte[] buf, int offset, int length, boolean negative) {
/* 509 */     BigInteger val = BigInteger.valueOf(value);
/* 510 */     byte[] b = val.toByteArray();
/* 511 */     int len = b.length;
/* 512 */     int off = offset + length - len;
/* 513 */     System.arraycopy(b, 0, buf, off, len);
/* 514 */     byte fill = (byte)(negative ? 255 : 0);
/* 515 */     for (int i = offset + 1; i < off; i++)
/* 516 */       buf[i] = fill; 
/*     */   }
/*     */   
/*     */   public static int formatCheckSumOctalBytes(long value, byte[] buf, int offset, int length) {
/* 536 */     int idx = length - 2;
/* 537 */     formatUnsignedOctalString(value, buf, offset, idx);
/* 539 */     buf[offset + idx++] = 0;
/* 540 */     buf[offset + idx] = 32;
/* 542 */     return offset + length;
/*     */   }
/*     */   
/*     */   public static long computeCheckSum(byte[] buf) {
/* 552 */     long sum = 0L;
/* 554 */     for (int i = 0; i < buf.length; i++)
/* 555 */       sum += (0xFF & buf[i]); 
/* 558 */     return sum;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\tar\TarUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
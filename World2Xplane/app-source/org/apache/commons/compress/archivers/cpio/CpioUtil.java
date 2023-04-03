/*     */ package org.apache.commons.compress.archivers.cpio;
/*     */ 
/*     */ class CpioUtil {
/*     */   static long byteArray2long(byte[] number, boolean swapHalfWord) {
/*  39 */     if (number.length % 2 != 0)
/*  40 */       throw new UnsupportedOperationException(); 
/*  43 */     long ret = 0L;
/*  44 */     int pos = 0;
/*  45 */     byte[] tmp_number = new byte[number.length];
/*  46 */     System.arraycopy(number, 0, tmp_number, 0, number.length);
/*  48 */     if (!swapHalfWord) {
/*  49 */       byte tmp = 0;
/*  50 */       for (pos = 0; pos < tmp_number.length; pos++) {
/*  51 */         tmp = tmp_number[pos];
/*  52 */         tmp_number[pos++] = tmp_number[pos];
/*  53 */         tmp_number[pos] = tmp;
/*     */       } 
/*     */     } 
/*  57 */     ret = (tmp_number[0] & 0xFF);
/*  58 */     for (pos = 1; pos < tmp_number.length; pos++) {
/*  59 */       ret <<= 8L;
/*  60 */       ret |= (tmp_number[pos] & 0xFF);
/*     */     } 
/*  62 */     return ret;
/*     */   }
/*     */   
/*     */   static byte[] long2byteArray(long number, int length, boolean swapHalfWord) {
/*  81 */     byte[] ret = new byte[length];
/*  82 */     int pos = 0;
/*  83 */     long tmp_number = 0L;
/*  85 */     if (length % 2 != 0 || length < 2)
/*  86 */       throw new UnsupportedOperationException(); 
/*  89 */     tmp_number = number;
/*  90 */     for (pos = length - 1; pos >= 0; pos--) {
/*  91 */       ret[pos] = (byte)(int)(tmp_number & 0xFFL);
/*  92 */       tmp_number >>= 8L;
/*     */     } 
/*  95 */     if (!swapHalfWord) {
/*  96 */       byte tmp = 0;
/*  97 */       for (pos = 0; pos < length; pos++) {
/*  98 */         tmp = ret[pos];
/*  99 */         ret[pos++] = ret[pos];
/* 100 */         ret[pos] = tmp;
/*     */       } 
/*     */     } 
/* 104 */     return ret;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\cpio\CpioUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
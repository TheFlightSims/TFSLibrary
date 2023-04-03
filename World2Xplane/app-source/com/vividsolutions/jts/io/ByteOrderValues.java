/*     */ package com.vividsolutions.jts.io;
/*     */ 
/*     */ public class ByteOrderValues {
/*     */   public static final int BIG_ENDIAN = 1;
/*     */   
/*     */   public static final int LITTLE_ENDIAN = 2;
/*     */   
/*     */   public static int getInt(byte[] buf, int byteOrder) {
/*  48 */     if (byteOrder == 1)
/*  49 */       return (buf[0] & 0xFF) << 24 | (buf[1] & 0xFF) << 16 | (buf[2] & 0xFF) << 8 | buf[3] & 0xFF; 
/*  55 */     return (buf[3] & 0xFF) << 24 | (buf[2] & 0xFF) << 16 | (buf[1] & 0xFF) << 8 | buf[0] & 0xFF;
/*     */   }
/*     */   
/*     */   public static void putInt(int intValue, byte[] buf, int byteOrder) {
/*  64 */     if (byteOrder == 1) {
/*  65 */       buf[0] = (byte)(intValue >> 24);
/*  66 */       buf[1] = (byte)(intValue >> 16);
/*  67 */       buf[2] = (byte)(intValue >> 8);
/*  68 */       buf[3] = (byte)intValue;
/*     */     } else {
/*  71 */       buf[0] = (byte)intValue;
/*  72 */       buf[1] = (byte)(intValue >> 8);
/*  73 */       buf[2] = (byte)(intValue >> 16);
/*  74 */       buf[3] = (byte)(intValue >> 24);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static long getLong(byte[] buf, int byteOrder) {
/*  79 */     if (byteOrder == 1)
/*  80 */       return (buf[0] & 0xFF) << 56L | (buf[1] & 0xFF) << 48L | (buf[2] & 0xFF) << 40L | (buf[3] & 0xFF) << 32L | (buf[4] & 0xFF) << 24L | (buf[5] & 0xFF) << 16L | (buf[6] & 0xFF) << 8L | (buf[7] & 0xFF); 
/*  91 */     return (buf[7] & 0xFF) << 56L | (buf[6] & 0xFF) << 48L | (buf[5] & 0xFF) << 40L | (buf[4] & 0xFF) << 32L | (buf[3] & 0xFF) << 24L | (buf[2] & 0xFF) << 16L | (buf[1] & 0xFF) << 8L | (buf[0] & 0xFF);
/*     */   }
/*     */   
/*     */   public static void putLong(long longValue, byte[] buf, int byteOrder) {
/* 105 */     if (byteOrder == 1) {
/* 106 */       buf[0] = (byte)(int)(longValue >> 56L);
/* 107 */       buf[1] = (byte)(int)(longValue >> 48L);
/* 108 */       buf[2] = (byte)(int)(longValue >> 40L);
/* 109 */       buf[3] = (byte)(int)(longValue >> 32L);
/* 110 */       buf[4] = (byte)(int)(longValue >> 24L);
/* 111 */       buf[5] = (byte)(int)(longValue >> 16L);
/* 112 */       buf[6] = (byte)(int)(longValue >> 8L);
/* 113 */       buf[7] = (byte)(int)longValue;
/*     */     } else {
/* 116 */       buf[0] = (byte)(int)longValue;
/* 117 */       buf[1] = (byte)(int)(longValue >> 8L);
/* 118 */       buf[2] = (byte)(int)(longValue >> 16L);
/* 119 */       buf[3] = (byte)(int)(longValue >> 24L);
/* 120 */       buf[4] = (byte)(int)(longValue >> 32L);
/* 121 */       buf[5] = (byte)(int)(longValue >> 40L);
/* 122 */       buf[6] = (byte)(int)(longValue >> 48L);
/* 123 */       buf[7] = (byte)(int)(longValue >> 56L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static double getDouble(byte[] buf, int byteOrder) {
/* 129 */     long longVal = getLong(buf, byteOrder);
/* 130 */     return Double.longBitsToDouble(longVal);
/*     */   }
/*     */   
/*     */   public static void putDouble(double doubleValue, byte[] buf, int byteOrder) {
/* 135 */     long longVal = Double.doubleToLongBits(doubleValue);
/* 136 */     putLong(longVal, buf, byteOrder);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\ByteOrderValues.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
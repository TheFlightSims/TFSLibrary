/*     */ package au.com.objectix.jgridshift;
/*     */ 
/*     */ public class Util {
/*     */   public static final int getIntLE(byte[] b, int i) {
/*  39 */     return b[i++] & 0xFF | b[i++] << 8 & 0xFF00 | b[i++] << 16 & 0xFF0000 | b[i] << 24;
/*     */   }
/*     */   
/*     */   public static final int getIntBE(byte[] b, int i) {
/*  49 */     return b[i++] << 24 | b[i++] << 16 & 0xFF0000 | b[i++] << 8 & 0xFF00 | b[i] & 0xFF;
/*     */   }
/*     */   
/*     */   public static final int getInt(byte[] b, boolean bigEndian) {
/*  60 */     if (bigEndian)
/*  61 */       return getIntBE(b, 0); 
/*  63 */     return getIntLE(b, 0);
/*     */   }
/*     */   
/*     */   public static final float getFloat(byte[] b, boolean bigEndian) {
/*  75 */     int i = 0;
/*  76 */     if (bigEndian) {
/*  77 */       i = getIntBE(b, 0);
/*     */     } else {
/*  79 */       i = getIntLE(b, 0);
/*     */     } 
/*  81 */     return Float.intBitsToFloat(i);
/*     */   }
/*     */   
/*     */   public static final double getDouble(byte[] b, boolean bigEndian) {
/*  93 */     int i = 0;
/*  94 */     int j = 0;
/*  95 */     if (bigEndian) {
/*  96 */       i = getIntBE(b, 0);
/*  97 */       j = getIntBE(b, 4);
/*     */     } else {
/*  99 */       i = getIntLE(b, 4);
/* 100 */       j = getIntLE(b, 0);
/*     */     } 
/* 102 */     long l = i << 32L | j & 0xFFFFFFFFL;
/* 104 */     return Double.longBitsToDouble(l);
/*     */   }
/*     */   
/*     */   public static boolean isNioAvailable() {
/* 112 */     boolean nioAvailable = false;
/*     */     try {
/* 114 */       Class.forName("java.nio.channels.FileChannel");
/* 115 */       nioAvailable = true;
/* 116 */     } catch (ClassNotFoundException cnfe) {}
/* 117 */     return nioAvailable;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\au\com\objectix\jgridshift\Util.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
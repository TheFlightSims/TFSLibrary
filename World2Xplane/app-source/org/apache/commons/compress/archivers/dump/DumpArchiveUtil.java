/*     */ package org.apache.commons.compress.archivers.dump;
/*     */ 
/*     */ class DumpArchiveUtil {
/*     */   public static int calculateChecksum(byte[] buffer) {
/*  39 */     int calc = 0;
/*  41 */     for (int i = 0; i < 256; i++)
/*  42 */       calc += convert32(buffer, 4 * i); 
/*  45 */     return 84446 - calc - convert32(buffer, 28);
/*     */   }
/*     */   
/*     */   public static final boolean verify(byte[] buffer) {
/*  56 */     int magic = convert32(buffer, 24);
/*  58 */     if (magic != 60012)
/*  59 */       return false; 
/*  63 */     int checksum = convert32(buffer, 28);
/*  65 */     if (checksum != calculateChecksum(buffer))
/*  66 */       return false; 
/*  69 */     return true;
/*     */   }
/*     */   
/*     */   public static final int getIno(byte[] buffer) {
/*  78 */     return convert32(buffer, 20);
/*     */   }
/*     */   
/*     */   public static final long convert64(byte[] buffer, int offset) {
/*  89 */     long i = 0L;
/*  90 */     i += buffer[offset + 7] << 56L;
/*  91 */     i += buffer[offset + 6] << 48L & 0xFF000000000000L;
/*  92 */     i += buffer[offset + 5] << 40L & 0xFF0000000000L;
/*  93 */     i += buffer[offset + 4] << 32L & 0xFF00000000L;
/*  94 */     i += buffer[offset + 3] << 24L & 0xFF000000L;
/*  95 */     i += buffer[offset + 2] << 16L & 0xFF0000L;
/*  96 */     i += buffer[offset + 1] << 8L & 0xFF00L;
/*  97 */     i += buffer[offset] & 0xFFL;
/*  99 */     return i;
/*     */   }
/*     */   
/*     */   public static final int convert32(byte[] buffer, int offset) {
/* 110 */     int i = 0;
/* 111 */     i = buffer[offset + 3] << 24;
/* 112 */     i += buffer[offset + 2] << 16 & 0xFF0000;
/* 113 */     i += buffer[offset + 1] << 8 & 0xFF00;
/* 114 */     i += buffer[offset] & 0xFF;
/* 116 */     return i;
/*     */   }
/*     */   
/*     */   public static final int convert16(byte[] buffer, int offset) {
/* 127 */     int i = 0;
/* 128 */     i += buffer[offset + 1] << 8 & 0xFF00;
/* 129 */     i += buffer[offset] & 0xFF;
/* 131 */     return i;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\dump\DumpArchiveUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
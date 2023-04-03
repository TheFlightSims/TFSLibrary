/*     */ package org.apache.commons.compress.archivers;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public abstract class ArchiveInputStream extends InputStream {
/*  42 */   private byte[] SINGLE = new byte[1];
/*     */   
/*     */   private static final int BYTE_MASK = 255;
/*     */   
/*  46 */   private long bytesRead = 0L;
/*     */   
/*     */   public abstract ArchiveEntry getNextEntry() throws IOException;
/*     */   
/*     */   public int read() throws IOException {
/*  81 */     int num = read(this.SINGLE, 0, 1);
/*  82 */     return (num == -1) ? -1 : (this.SINGLE[0] & 0xFF);
/*     */   }
/*     */   
/*     */   protected void count(int read) {
/*  92 */     count(read);
/*     */   }
/*     */   
/*     */   protected void count(long read) {
/* 103 */     if (read != -1L)
/* 104 */       this.bytesRead += read; 
/*     */   }
/*     */   
/*     */   protected void pushedBackBytes(long pushedBack) {
/* 115 */     this.bytesRead -= pushedBack;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getCount() {
/* 126 */     return (int)this.bytesRead;
/*     */   }
/*     */   
/*     */   public long getBytesRead() {
/* 135 */     return this.bytesRead;
/*     */   }
/*     */   
/*     */   public boolean canReadEntryData(ArchiveEntry ae) {
/* 149 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\ArchiveInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
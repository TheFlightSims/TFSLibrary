/*    */ package org.apache.commons.compress.utils;
/*    */ 
/*    */ import java.io.FilterInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class CountingInputStream extends FilterInputStream {
/*    */   private long bytesRead;
/*    */   
/*    */   public CountingInputStream(InputStream in) {
/* 34 */     super(in);
/*    */   }
/*    */   
/*    */   public int read() throws IOException {
/* 39 */     int r = this.in.read();
/* 40 */     if (r >= 0)
/* 41 */       count(1L); 
/* 43 */     return r;
/*    */   }
/*    */   
/*    */   public int read(byte[] b) throws IOException {
/* 47 */     return read(b, 0, b.length);
/*    */   }
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 51 */     int r = this.in.read(b, off, len);
/* 52 */     if (r >= 0)
/* 53 */       count(r); 
/* 55 */     return r;
/*    */   }
/*    */   
/*    */   protected final void count(long read) {
/* 64 */     if (read != -1L)
/* 65 */       this.bytesRead += read; 
/*    */   }
/*    */   
/*    */   public long getBytesRead() {
/* 74 */     return this.bytesRead;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compres\\utils\CountingInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.compress.compressors;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public abstract class CompressorInputStream extends InputStream {
/* 24 */   private long bytesRead = 0L;
/*    */   
/*    */   protected void count(int read) {
/* 35 */     count(read);
/*    */   }
/*    */   
/*    */   protected void count(long read) {
/* 45 */     if (read != -1L)
/* 46 */       this.bytesRead += read; 
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public int getCount() {
/* 58 */     return (int)this.bytesRead;
/*    */   }
/*    */   
/*    */   public long getBytesRead() {
/* 68 */     return this.bytesRead;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\CompressorInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class OffsetTrackingOutputStream extends OutputStream {
/*    */   private OutputStream out;
/*    */   
/*    */   private long byteCount;
/*    */   
/*    */   public OffsetTrackingOutputStream(OutputStream out) {
/* 26 */     this.out = out;
/* 28 */     this.byteCount = 0L;
/*    */   }
/*    */   
/*    */   public long getByteCount() {
/* 38 */     return this.byteCount;
/*    */   }
/*    */   
/*    */   public void write(int b) throws IOException {
/* 47 */     this.byteCount++;
/* 49 */     this.out.write(b);
/*    */   }
/*    */   
/*    */   public void write(byte[] b, int off, int len) throws IOException {
/* 58 */     this.byteCount += len;
/* 60 */     this.out.write(b, off, len);
/*    */   }
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 69 */     this.byteCount += b.length;
/* 71 */     this.out.write(b);
/*    */   }
/*    */   
/*    */   public void flush() throws IOException {
/* 80 */     this.out.flush();
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 89 */     this.out.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\OffsetTrackingOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
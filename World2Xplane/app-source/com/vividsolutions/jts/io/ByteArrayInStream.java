/*    */ package com.vividsolutions.jts.io;
/*    */ 
/*    */ public class ByteArrayInStream implements InStream {
/*    */   private byte[] buffer;
/*    */   
/*    */   private int position;
/*    */   
/*    */   public ByteArrayInStream(byte[] buffer) {
/* 56 */     setBytes(buffer);
/*    */   }
/*    */   
/*    */   public void setBytes(byte[] buffer) {
/* 65 */     this.buffer = buffer;
/* 66 */     this.position = 0;
/*    */   }
/*    */   
/*    */   public void read(byte[] buf) {
/* 76 */     int numToRead = buf.length;
/* 78 */     if (this.position + numToRead > this.buffer.length) {
/* 79 */       numToRead = this.buffer.length - this.position;
/* 80 */       System.arraycopy(this.buffer, this.position, buf, 0, numToRead);
/* 82 */       for (int i = numToRead; i < buf.length; i++)
/* 83 */         buf[i] = 0; 
/*    */     } else {
/* 87 */       System.arraycopy(this.buffer, this.position, buf, 0, numToRead);
/*    */     } 
/* 89 */     this.position += numToRead;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\ByteArrayInStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.postgresql.util;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class StreamWrapper {
/*    */   private final InputStream stream;
/*    */   
/*    */   private final byte[] rawData;
/*    */   
/*    */   private final int offset;
/*    */   
/*    */   private final int length;
/*    */   
/*    */   public StreamWrapper(byte[] data, int offset, int length) {
/* 22 */     this.stream = null;
/* 23 */     this.rawData = data;
/* 24 */     this.offset = offset;
/* 25 */     this.length = length;
/*    */   }
/*    */   
/*    */   public StreamWrapper(InputStream stream, int length) {
/* 29 */     this.stream = stream;
/* 30 */     this.rawData = null;
/* 31 */     this.offset = 0;
/* 32 */     this.length = length;
/*    */   }
/*    */   
/*    */   public InputStream getStream() {
/* 36 */     if (this.stream != null)
/* 37 */       return this.stream; 
/* 39 */     return new ByteArrayInputStream(this.rawData, this.offset, this.length);
/*    */   }
/*    */   
/*    */   public int getLength() {
/* 43 */     return this.length;
/*    */   }
/*    */   
/*    */   public int getOffset() {
/* 47 */     return this.offset;
/*    */   }
/*    */   
/*    */   public byte[] getBytes() {
/* 51 */     return this.rawData;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 55 */     return "<stream of " + this.length + " bytes>";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresq\\util\StreamWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
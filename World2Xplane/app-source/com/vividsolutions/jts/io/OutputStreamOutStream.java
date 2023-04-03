/*    */ package com.vividsolutions.jts.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class OutputStreamOutStream implements OutStream {
/*    */   private OutputStream os;
/*    */   
/*    */   public OutputStreamOutStream(OutputStream os) {
/* 48 */     this.os = os;
/*    */   }
/*    */   
/*    */   public void write(byte[] buf, int len) throws IOException {
/* 52 */     this.os.write(buf, 0, len);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\OutputStreamOutStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
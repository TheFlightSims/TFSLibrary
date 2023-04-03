/*    */ package com.vividsolutions.jts.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class InputStreamInStream implements InStream {
/*    */   private InputStream is;
/*    */   
/*    */   public InputStreamInStream(InputStream is) {
/* 47 */     this.is = is;
/*    */   }
/*    */   
/*    */   public void read(byte[] buf) throws IOException {
/* 52 */     this.is.read(buf);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\InputStreamInStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
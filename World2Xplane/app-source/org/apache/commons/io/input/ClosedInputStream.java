/*    */ package org.apache.commons.io.input;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class ClosedInputStream extends InputStream {
/* 37 */   public static final ClosedInputStream CLOSED_INPUT_STREAM = new ClosedInputStream();
/*    */   
/*    */   public int read() {
/* 46 */     return -1;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\io\input\ClosedInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
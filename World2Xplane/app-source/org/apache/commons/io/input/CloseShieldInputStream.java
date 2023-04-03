/*    */ package org.apache.commons.io.input;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class CloseShieldInputStream extends ProxyInputStream {
/*    */   public CloseShieldInputStream(InputStream in) {
/* 40 */     super(in);
/*    */   }
/*    */   
/*    */   public void close() {
/* 50 */     this.in = new ClosedInputStream();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\io\input\CloseShieldInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.io.output;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class CloseShieldOutputStream extends ProxyOutputStream {
/*    */   public CloseShieldOutputStream(OutputStream out) {
/* 40 */     super(out);
/*    */   }
/*    */   
/*    */   public void close() {
/* 50 */     this.out = new ClosedOutputStream();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\io\output\CloseShieldOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
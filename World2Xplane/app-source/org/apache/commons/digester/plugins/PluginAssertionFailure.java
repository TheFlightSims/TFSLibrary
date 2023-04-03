/*    */ package org.apache.commons.digester.plugins;
/*    */ 
/*    */ public class PluginAssertionFailure extends RuntimeException {
/* 50 */   private Throwable cause = null;
/*    */   
/*    */   public PluginAssertionFailure(Throwable cause) {
/* 56 */     this(cause.getMessage());
/* 57 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public PluginAssertionFailure(String msg) {
/* 64 */     super(msg);
/*    */   }
/*    */   
/*    */   public PluginAssertionFailure(String msg, Throwable cause) {
/* 72 */     this(msg);
/* 73 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 83 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\PluginAssertionFailure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
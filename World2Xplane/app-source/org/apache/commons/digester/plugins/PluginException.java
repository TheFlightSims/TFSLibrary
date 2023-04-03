/*    */ package org.apache.commons.digester.plugins;
/*    */ 
/*    */ public class PluginException extends Exception {
/* 30 */   private Throwable cause = null;
/*    */   
/*    */   public PluginException(Throwable cause) {
/* 36 */     this(cause.getMessage());
/* 37 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public PluginException(String msg) {
/* 44 */     super(msg);
/*    */   }
/*    */   
/*    */   public PluginException(String msg, Throwable cause) {
/* 52 */     this(msg);
/* 53 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 60 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\PluginException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
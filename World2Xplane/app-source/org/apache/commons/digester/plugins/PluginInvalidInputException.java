/*    */ package org.apache.commons.digester.plugins;
/*    */ 
/*    */ public class PluginInvalidInputException extends PluginException {
/* 28 */   private Throwable cause = null;
/*    */   
/*    */   public PluginInvalidInputException(Throwable cause) {
/* 34 */     this(cause.getMessage());
/* 35 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public PluginInvalidInputException(String msg) {
/* 42 */     super(msg);
/*    */   }
/*    */   
/*    */   public PluginInvalidInputException(String msg, Throwable cause) {
/* 50 */     this(msg);
/* 51 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 61 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\PluginInvalidInputException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
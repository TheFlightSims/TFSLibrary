/*    */ package org.apache.commons.digester.plugins;
/*    */ 
/*    */ public class PluginConfigurationException extends RuntimeException {
/* 32 */   private Throwable cause = null;
/*    */   
/*    */   public PluginConfigurationException(Throwable cause) {
/* 38 */     this(cause.getMessage());
/* 39 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public PluginConfigurationException(String msg) {
/* 46 */     super(msg);
/*    */   }
/*    */   
/*    */   public PluginConfigurationException(String msg, Throwable cause) {
/* 54 */     this(msg);
/* 55 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 65 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\PluginConfigurationException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
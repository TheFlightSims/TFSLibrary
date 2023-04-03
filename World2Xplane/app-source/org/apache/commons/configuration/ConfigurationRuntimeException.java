/*    */ package org.apache.commons.configuration;
/*    */ 
/*    */ import org.apache.commons.lang.exception.NestableRuntimeException;
/*    */ 
/*    */ public class ConfigurationRuntimeException extends NestableRuntimeException {
/*    */   private static final long serialVersionUID = -7838702245512140996L;
/*    */   
/*    */   public ConfigurationRuntimeException() {}
/*    */   
/*    */   public ConfigurationRuntimeException(String message) {
/* 54 */     super(message);
/*    */   }
/*    */   
/*    */   public ConfigurationRuntimeException(Throwable cause) {
/* 65 */     super(cause);
/*    */   }
/*    */   
/*    */   public ConfigurationRuntimeException(String message, Throwable cause) {
/* 77 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\ConfigurationRuntimeException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */
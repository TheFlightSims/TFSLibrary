/*    */ package org.apache.commons.configuration;
/*    */ 
/*    */ import org.apache.commons.lang.exception.NestableException;
/*    */ 
/*    */ public class ConfigurationException extends NestableException {
/*    */   private static final long serialVersionUID = -1316746661346991484L;
/*    */   
/*    */   public ConfigurationException() {}
/*    */   
/*    */   public ConfigurationException(String message) {
/* 53 */     super(message);
/*    */   }
/*    */   
/*    */   public ConfigurationException(Throwable cause) {
/* 64 */     super(cause);
/*    */   }
/*    */   
/*    */   public ConfigurationException(String message, Throwable cause) {
/* 76 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\ConfigurationException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.beanutils;
/*    */ 
/*    */ public class ConversionException extends RuntimeException {
/*    */   protected Throwable cause;
/*    */   
/*    */   public ConversionException(String message) {
/* 44 */     super(message);
/* 83 */     this.cause = null;
/*    */   }
/*    */   
/*    */   public ConversionException(String message, Throwable cause) {
/*    */     super(message);
/* 83 */     this.cause = null;
/*    */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public ConversionException(Throwable cause) {
/*    */     super(cause.getMessage());
/* 83 */     this.cause = null;
/*    */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 90 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\ConversionException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
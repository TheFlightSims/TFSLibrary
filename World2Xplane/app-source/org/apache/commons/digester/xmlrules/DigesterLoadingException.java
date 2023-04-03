/*    */ package org.apache.commons.digester.xmlrules;
/*    */ 
/*    */ public class DigesterLoadingException extends Exception {
/* 31 */   private Throwable cause = null;
/*    */   
/*    */   public DigesterLoadingException(String msg) {
/* 37 */     super(msg);
/*    */   }
/*    */   
/*    */   public DigesterLoadingException(Throwable cause) {
/* 44 */     this(cause.getMessage());
/* 45 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public DigesterLoadingException(String msg, Throwable cause) {
/* 53 */     this(msg);
/* 54 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 64 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\xmlrules\DigesterLoadingException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
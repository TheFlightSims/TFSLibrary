/*    */ package org.apache.commons.digester.xmlrules;
/*    */ 
/*    */ public class XmlLoadException extends RuntimeException {
/* 30 */   private Throwable cause = null;
/*    */   
/*    */   public XmlLoadException(Throwable cause) {
/* 36 */     this(cause.getMessage());
/* 37 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public XmlLoadException(String msg) {
/* 41 */     super(msg);
/*    */   }
/*    */   
/*    */   public XmlLoadException(String msg, Throwable cause) {
/* 45 */     this(msg);
/* 46 */     this.cause = cause;
/*    */   }
/*    */   
/*    */   public Throwable getCause() {
/* 54 */     return this.cause;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\xmlrules\XmlLoadException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.codehaus.stax2.validation;
/*    */ 
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ 
/*    */ public class XMLValidationException extends XMLStreamException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected XMLValidationProblem mCause;
/*    */   
/*    */   protected XMLValidationException(XMLValidationProblem cause) {
/* 31 */     if (cause == null)
/* 32 */       throwMissing(); 
/* 34 */     this.mCause = cause;
/*    */   }
/*    */   
/*    */   protected XMLValidationException(XMLValidationProblem cause, String msg) {
/* 39 */     super(msg);
/* 40 */     if (cause == null)
/* 41 */       throwMissing(); 
/* 43 */     this.mCause = cause;
/*    */   }
/*    */   
/*    */   protected XMLValidationException(XMLValidationProblem cause, String msg, Location loc) {
/* 49 */     super(msg, loc);
/* 50 */     if (cause == null)
/* 51 */       throwMissing(); 
/* 53 */     this.mCause = cause;
/*    */   }
/*    */   
/*    */   public static XMLValidationException createException(XMLValidationProblem cause) {
/* 60 */     String msg = cause.getMessage();
/* 61 */     if (msg == null)
/* 62 */       return new XMLValidationException(cause); 
/* 64 */     Location loc = cause.getLocation();
/* 65 */     if (loc == null)
/* 66 */       return new XMLValidationException(cause, msg); 
/* 68 */     return new XMLValidationException(cause, msg, loc);
/*    */   }
/*    */   
/*    */   public XMLValidationProblem getValidationProblem() {
/* 78 */     return this.mCause;
/*    */   }
/*    */   
/*    */   protected static void throwMissing() throws RuntimeException {
/* 86 */     throw new IllegalArgumentException("Validation problem argument can not be null");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\validation\XMLValidationException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */
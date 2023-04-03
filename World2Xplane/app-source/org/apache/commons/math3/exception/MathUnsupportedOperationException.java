/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.ExceptionContext;
/*    */ import org.apache.commons.math3.exception.util.ExceptionContextProvider;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class MathUnsupportedOperationException extends UnsupportedOperationException implements ExceptionContextProvider {
/*    */   private static final long serialVersionUID = -6024911025449780478L;
/*    */   
/*    */   private final ExceptionContext context;
/*    */   
/*    */   public MathUnsupportedOperationException() {
/* 44 */     this((Localizable)LocalizedFormats.UNSUPPORTED_OPERATION, new Object[0]);
/*    */   }
/*    */   
/*    */   public MathUnsupportedOperationException(Localizable pattern, Object... args) {
/* 53 */     this.context = new ExceptionContext(this);
/* 54 */     this.context.addMessage(pattern, args);
/*    */   }
/*    */   
/*    */   public ExceptionContext getContext() {
/* 59 */     return this.context;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 65 */     return this.context.getMessage();
/*    */   }
/*    */   
/*    */   public String getLocalizedMessage() {
/* 71 */     return this.context.getLocalizedMessage();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\MathUnsupportedOperationException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
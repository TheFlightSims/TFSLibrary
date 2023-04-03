/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.ExceptionContext;
/*    */ import org.apache.commons.math3.exception.util.ExceptionContextProvider;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ 
/*    */ public class MathIllegalArgumentException extends IllegalArgumentException implements ExceptionContextProvider {
/*    */   private static final long serialVersionUID = -6024911025449780478L;
/*    */   
/*    */   private final ExceptionContext context;
/*    */   
/*    */   public MathIllegalArgumentException(Localizable pattern, Object... args) {
/* 45 */     this.context = new ExceptionContext(this);
/* 46 */     this.context.addMessage(pattern, args);
/*    */   }
/*    */   
/*    */   public ExceptionContext getContext() {
/* 51 */     return this.context;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 57 */     return this.context.getMessage();
/*    */   }
/*    */   
/*    */   public String getLocalizedMessage() {
/* 63 */     return this.context.getLocalizedMessage();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\MathIllegalArgumentException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
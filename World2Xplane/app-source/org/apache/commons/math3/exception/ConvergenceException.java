/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class ConvergenceException extends MathIllegalStateException {
/*    */   private static final long serialVersionUID = 4330003017885151975L;
/*    */   
/*    */   public ConvergenceException() {
/* 37 */     this((Localizable)LocalizedFormats.CONVERGENCE_FAILED, new Object[0]);
/*    */   }
/*    */   
/*    */   public ConvergenceException(Localizable pattern, Object... args) {
/* 49 */     getContext().addMessage(pattern, args);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\ConvergenceException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
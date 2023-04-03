/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class TooManyEvaluationsException extends MaxCountExceededException {
/*    */   private static final long serialVersionUID = 4330003017885151975L;
/*    */   
/*    */   public TooManyEvaluationsException(Number max) {
/* 37 */     super(max);
/* 38 */     getContext().addMessage((Localizable)LocalizedFormats.EVALUATIONS, new Object[0]);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\TooManyEvaluationsException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
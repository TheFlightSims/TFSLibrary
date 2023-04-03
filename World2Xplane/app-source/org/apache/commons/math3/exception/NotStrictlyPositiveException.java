/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ 
/*    */ public class NotStrictlyPositiveException extends NumberIsTooSmallException {
/*    */   private static final long serialVersionUID = -7824848630829852237L;
/*    */   
/*    */   public NotStrictlyPositiveException(Number value) {
/* 38 */     super(value, Integer.valueOf(0), false);
/*    */   }
/*    */   
/*    */   public NotStrictlyPositiveException(Localizable specific, Number value) {
/* 48 */     super(specific, value, Integer.valueOf(0), false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\NotStrictlyPositiveException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
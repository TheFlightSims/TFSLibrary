/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ 
/*    */ public class NotPositiveException extends NumberIsTooSmallException {
/*    */   private static final long serialVersionUID = -2250556892093726375L;
/*    */   
/*    */   public NotPositiveException(Number value) {
/* 37 */     super(value, Integer.valueOf(0), true);
/*    */   }
/*    */   
/*    */   public NotPositiveException(Localizable specific, Number value) {
/* 47 */     super(specific, value, Integer.valueOf(0), true);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\NotPositiveException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
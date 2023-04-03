/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class NumberIsTooLargeException extends MathIllegalNumberException {
/*    */   private static final long serialVersionUID = 4330003017885151975L;
/*    */   
/*    */   private final Number max;
/*    */   
/*    */   private final boolean boundIsAllowed;
/*    */   
/*    */   public NumberIsTooLargeException(Number wrong, Number max, boolean boundIsAllowed) {
/* 50 */     this(boundIsAllowed ? (Localizable)LocalizedFormats.NUMBER_TOO_LARGE : (Localizable)LocalizedFormats.NUMBER_TOO_LARGE_BOUND_EXCLUDED, wrong, max, boundIsAllowed);
/*    */   }
/*    */   
/*    */   public NumberIsTooLargeException(Localizable specific, Number wrong, Number max, boolean boundIsAllowed) {
/* 67 */     super(specific, wrong, new Object[] { max });
/* 69 */     this.max = max;
/* 70 */     this.boundIsAllowed = boundIsAllowed;
/*    */   }
/*    */   
/*    */   public boolean getBoundIsAllowed() {
/* 77 */     return this.boundIsAllowed;
/*    */   }
/*    */   
/*    */   public Number getMax() {
/* 84 */     return this.max;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\NumberIsTooLargeException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
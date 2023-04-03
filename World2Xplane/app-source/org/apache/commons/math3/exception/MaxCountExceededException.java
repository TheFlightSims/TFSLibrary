/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class MaxCountExceededException extends MathIllegalStateException {
/*    */   private static final long serialVersionUID = 4330003017885151975L;
/*    */   
/*    */   private final Number max;
/*    */   
/*    */   public MaxCountExceededException(Number max) {
/* 42 */     this((Localizable)LocalizedFormats.MAX_COUNT_EXCEEDED, max, new Object[0]);
/*    */   }
/*    */   
/*    */   public MaxCountExceededException(Localizable specific, Number max, Object... args) {
/* 54 */     getContext().addMessage(specific, new Object[] { max, args });
/* 55 */     this.max = max;
/*    */   }
/*    */   
/*    */   public Number getMax() {
/* 62 */     return this.max;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\MaxCountExceededException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
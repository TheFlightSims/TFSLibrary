/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class OutOfRangeException extends MathIllegalNumberException {
/*    */   private static final long serialVersionUID = 111601815794403609L;
/*    */   
/*    */   private final Number lo;
/*    */   
/*    */   private final Number hi;
/*    */   
/*    */   public OutOfRangeException(Number wrong, Number lo, Number hi) {
/* 46 */     this((Localizable)LocalizedFormats.OUT_OF_RANGE_SIMPLE, wrong, lo, hi);
/*    */   }
/*    */   
/*    */   public OutOfRangeException(Localizable specific, Number wrong, Number lo, Number hi) {
/* 62 */     super(specific, wrong, new Object[] { lo, hi });
/* 63 */     this.lo = lo;
/* 64 */     this.hi = hi;
/*    */   }
/*    */   
/*    */   public Number getLo() {
/* 71 */     return this.lo;
/*    */   }
/*    */   
/*    */   public Number getHi() {
/* 77 */     return this.hi;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\OutOfRangeException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
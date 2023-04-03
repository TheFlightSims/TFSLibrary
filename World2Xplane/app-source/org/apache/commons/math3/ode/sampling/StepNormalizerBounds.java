/*    */ package org.apache.commons.math3.ode.sampling;
/*    */ 
/*    */ public enum StepNormalizerBounds {
/* 32 */   NEITHER(false, false),
/* 35 */   FIRST(true, false),
/* 38 */   LAST(false, true),
/* 41 */   BOTH(true, true);
/*    */   
/*    */   private final boolean first;
/*    */   
/*    */   private final boolean last;
/*    */   
/*    */   StepNormalizerBounds(boolean first, boolean last) {
/* 61 */     this.first = first;
/* 62 */     this.last = last;
/*    */   }
/*    */   
/*    */   public boolean firstIncluded() {
/* 72 */     return this.first;
/*    */   }
/*    */   
/*    */   public boolean lastIncluded() {
/* 82 */     return this.last;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\sampling\StepNormalizerBounds.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
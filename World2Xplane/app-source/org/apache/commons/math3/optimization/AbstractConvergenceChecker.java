/*    */ package org.apache.commons.math3.optimization;
/*    */ 
/*    */ public abstract class AbstractConvergenceChecker<PAIR> implements ConvergenceChecker<PAIR> {
/*    */   private static final double DEFAULT_RELATIVE_THRESHOLD = 1.1102230246251565E-14D;
/*    */   
/*    */   private static final double DEFAULT_ABSOLUTE_THRESHOLD = 2.2250738585072014E-306D;
/*    */   
/*    */   private final double relativeThreshold;
/*    */   
/*    */   private final double absoluteThreshold;
/*    */   
/*    */   public AbstractConvergenceChecker() {
/* 53 */     this.relativeThreshold = 1.1102230246251565E-14D;
/* 54 */     this.absoluteThreshold = 2.2250738585072014E-306D;
/*    */   }
/*    */   
/*    */   public AbstractConvergenceChecker(double relativeThreshold, double absoluteThreshold) {
/* 65 */     this.relativeThreshold = relativeThreshold;
/* 66 */     this.absoluteThreshold = absoluteThreshold;
/*    */   }
/*    */   
/*    */   public double getRelativeThreshold() {
/* 73 */     return this.relativeThreshold;
/*    */   }
/*    */   
/*    */   public double getAbsoluteThreshold() {
/* 80 */     return this.absoluteThreshold;
/*    */   }
/*    */   
/*    */   public abstract boolean converged(int paramInt, PAIR paramPAIR1, PAIR paramPAIR2);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\AbstractConvergenceChecker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
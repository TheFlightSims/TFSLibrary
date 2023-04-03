/*    */ package org.apache.commons.math3.optimization;
/*    */ 
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class SimpleValueChecker extends AbstractConvergenceChecker<PointValuePair> {
/*    */   public SimpleValueChecker() {}
/*    */   
/*    */   public SimpleValueChecker(double relativeThreshold, double absoluteThreshold) {
/* 52 */     super(relativeThreshold, absoluteThreshold);
/*    */   }
/*    */   
/*    */   public boolean converged(int iteration, PointValuePair previous, PointValuePair current) {
/* 75 */     double p = ((Double)previous.getValue()).doubleValue();
/* 76 */     double c = ((Double)current.getValue()).doubleValue();
/* 77 */     double difference = FastMath.abs(p - c);
/* 78 */     double size = FastMath.max(FastMath.abs(p), FastMath.abs(c));
/* 79 */     return (difference <= size * getRelativeThreshold() || difference <= getAbsoluteThreshold());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\SimpleValueChecker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
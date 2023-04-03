/*    */ package org.apache.commons.math3.optimization;
/*    */ 
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ import org.apache.commons.math3.util.Pair;
/*    */ 
/*    */ public class SimplePointChecker<PAIR extends Pair<double[], ? extends Object>> extends AbstractConvergenceChecker<PAIR> {
/*    */   public SimplePointChecker() {}
/*    */   
/*    */   public SimplePointChecker(double relativeThreshold, double absoluteThreshold) {
/* 56 */     super(relativeThreshold, absoluteThreshold);
/*    */   }
/*    */   
/*    */   public boolean converged(int iteration, PAIR previous, PAIR current) {
/* 79 */     double[] p = (double[])previous.getKey();
/* 80 */     double[] c = (double[])current.getKey();
/* 81 */     for (int i = 0; i < p.length; i++) {
/* 82 */       double pi = p[i];
/* 83 */       double ci = c[i];
/* 84 */       double difference = FastMath.abs(pi - ci);
/* 85 */       double size = FastMath.max(FastMath.abs(pi), FastMath.abs(ci));
/* 86 */       if (difference > size * getRelativeThreshold() && difference > getAbsoluteThreshold())
/* 88 */         return false; 
/*    */     } 
/* 91 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\SimplePointChecker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
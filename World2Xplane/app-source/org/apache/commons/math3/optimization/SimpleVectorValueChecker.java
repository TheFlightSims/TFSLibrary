/*    */ package org.apache.commons.math3.optimization;
/*    */ 
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class SimpleVectorValueChecker extends AbstractConvergenceChecker<PointVectorValuePair> {
/*    */   public SimpleVectorValueChecker() {}
/*    */   
/*    */   public SimpleVectorValueChecker(double relativeThreshold, double absoluteThreshold) {
/* 53 */     super(relativeThreshold, absoluteThreshold);
/*    */   }
/*    */   
/*    */   public boolean converged(int iteration, PointVectorValuePair previous, PointVectorValuePair current) {
/* 76 */     double[] p = previous.getValueRef();
/* 77 */     double[] c = current.getValueRef();
/* 78 */     for (int i = 0; i < p.length; i++) {
/* 79 */       double pi = p[i];
/* 80 */       double ci = c[i];
/* 81 */       double difference = FastMath.abs(pi - ci);
/* 82 */       double size = FastMath.max(FastMath.abs(pi), FastMath.abs(ci));
/* 83 */       if (difference > size * getRelativeThreshold() && difference > getAbsoluteThreshold())
/* 85 */         return false; 
/*    */     } 
/* 88 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\SimpleVectorValueChecker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
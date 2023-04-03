/*    */ package org.apache.commons.math3.analysis.function;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*    */ import org.apache.commons.math3.exception.NoDataException;
/*    */ import org.apache.commons.math3.exception.NullArgumentException;
/*    */ import org.apache.commons.math3.util.MathArrays;
/*    */ 
/*    */ public class StepFunction implements UnivariateFunction {
/*    */   private final double[] abscissa;
/*    */   
/*    */   private final double[] ordinate;
/*    */   
/*    */   public StepFunction(double[] x, double[] y) {
/* 62 */     if (x == null || y == null)
/* 64 */       throw new NullArgumentException(); 
/* 66 */     if (x.length == 0 || y.length == 0)
/* 68 */       throw new NoDataException(); 
/* 70 */     if (y.length != x.length)
/* 71 */       throw new DimensionMismatchException(y.length, x.length); 
/* 73 */     MathArrays.checkOrder(x);
/* 75 */     this.abscissa = MathArrays.copyOf(x);
/* 76 */     this.ordinate = MathArrays.copyOf(y);
/*    */   }
/*    */   
/*    */   public double value(double x) {
/* 81 */     int index = Arrays.binarySearch(this.abscissa, x);
/* 82 */     double fx = 0.0D;
/* 84 */     if (index < -1) {
/* 86 */       fx = this.ordinate[-index - 2];
/* 87 */     } else if (index >= 0) {
/* 89 */       fx = this.ordinate[index];
/*    */     } else {
/* 93 */       fx = this.ordinate[0];
/*    */     } 
/* 96 */     return fx;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\StepFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.math3.optimization;
/*    */ 
/*    */ import org.apache.commons.math3.util.Pair;
/*    */ 
/*    */ public class PointValuePair extends Pair<double[], Double> {
/*    */   public PointValuePair(double[] point, double value) {
/* 41 */     this(point, value, true);
/*    */   }
/*    */   
/*    */   public PointValuePair(double[] point, double value, boolean copyArray) {
/* 55 */     super(copyArray ? ((point == null) ? null : point.clone()) : point, Double.valueOf(value));
/*    */   }
/*    */   
/*    */   public double[] getPoint() {
/* 67 */     double[] p = (double[])getKey();
/* 68 */     return (p == null) ? null : (double[])p.clone();
/*    */   }
/*    */   
/*    */   public double[] getPointRef() {
/* 77 */     return (double[])getKey();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\PointValuePair.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
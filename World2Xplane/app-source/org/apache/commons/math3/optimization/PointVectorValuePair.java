/*     */ package org.apache.commons.math3.optimization;
/*     */ 
/*     */ import org.apache.commons.math3.util.Pair;
/*     */ 
/*     */ public class PointVectorValuePair extends Pair<double[], double[]> {
/*     */   public PointVectorValuePair(double[] point, double[] value) {
/*  41 */     this(point, value, true);
/*     */   }
/*     */   
/*     */   public PointVectorValuePair(double[] point, double[] value, boolean copyArray) {
/*  55 */     super(copyArray ? ((point == null) ? null : point.clone()) : point, copyArray ? ((value == null) ? null : value.clone()) : value);
/*     */   }
/*     */   
/*     */   public double[] getPoint() {
/*  71 */     double[] p = (double[])getKey();
/*  72 */     return (p == null) ? null : (double[])p.clone();
/*     */   }
/*     */   
/*     */   public double[] getPointRef() {
/*  81 */     return (double[])getKey();
/*     */   }
/*     */   
/*     */   public double[] getValue() {
/*  91 */     double[] v = (double[])super.getValue();
/*  92 */     return (v == null) ? null : (double[])v.clone();
/*     */   }
/*     */   
/*     */   public double[] getValueRef() {
/* 102 */     return (double[])super.getValue();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\PointVectorValuePair.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
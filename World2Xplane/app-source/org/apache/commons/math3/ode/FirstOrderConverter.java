/*     */ package org.apache.commons.math3.ode;
/*     */ 
/*     */ public class FirstOrderConverter implements FirstOrderDifferentialEquations {
/*     */   private final SecondOrderDifferentialEquations equations;
/*     */   
/*     */   private final int dimension;
/*     */   
/*     */   private final double[] z;
/*     */   
/*     */   private final double[] zDot;
/*     */   
/*     */   private final double[] zDDot;
/*     */   
/*     */   public FirstOrderConverter(SecondOrderDifferentialEquations equations) {
/*  79 */     this.equations = equations;
/*  80 */     this.dimension = equations.getDimension();
/*  81 */     this.z = new double[this.dimension];
/*  82 */     this.zDot = new double[this.dimension];
/*  83 */     this.zDDot = new double[this.dimension];
/*     */   }
/*     */   
/*     */   public int getDimension() {
/*  92 */     return 2 * this.dimension;
/*     */   }
/*     */   
/*     */   public void computeDerivatives(double t, double[] y, double[] yDot) {
/* 103 */     System.arraycopy(y, 0, this.z, 0, this.dimension);
/* 104 */     System.arraycopy(y, this.dimension, this.zDot, 0, this.dimension);
/* 107 */     this.equations.computeSecondDerivatives(t, this.z, this.zDot, this.zDDot);
/* 110 */     System.arraycopy(this.zDot, 0, yDot, 0, this.dimension);
/* 111 */     System.arraycopy(this.zDDot, 0, yDot, this.dimension, this.dimension);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\FirstOrderConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
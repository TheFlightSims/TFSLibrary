/*     */ package org.apache.commons.math3.optimization;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.analysis.MultivariateVectorFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ 
/*     */ public class LeastSquaresConverter implements MultivariateFunction {
/*     */   private final MultivariateVectorFunction function;
/*     */   
/*     */   private final double[] observations;
/*     */   
/*     */   private final double[] weights;
/*     */   
/*     */   private final RealMatrix scale;
/*     */   
/*     */   public LeastSquaresConverter(MultivariateVectorFunction function, double[] observations) {
/*  76 */     this.function = function;
/*  77 */     this.observations = (double[])observations.clone();
/*  78 */     this.weights = null;
/*  79 */     this.scale = null;
/*     */   }
/*     */   
/*     */   public LeastSquaresConverter(MultivariateVectorFunction function, double[] observations, double[] weights) {
/* 112 */     if (observations.length != weights.length)
/* 113 */       throw new DimensionMismatchException(observations.length, weights.length); 
/* 115 */     this.function = function;
/* 116 */     this.observations = (double[])observations.clone();
/* 117 */     this.weights = (double[])weights.clone();
/* 118 */     this.scale = null;
/*     */   }
/*     */   
/*     */   public LeastSquaresConverter(MultivariateVectorFunction function, double[] observations, RealMatrix scale) {
/* 142 */     if (observations.length != scale.getColumnDimension())
/* 143 */       throw new DimensionMismatchException(observations.length, scale.getColumnDimension()); 
/* 145 */     this.function = function;
/* 146 */     this.observations = (double[])observations.clone();
/* 147 */     this.weights = null;
/* 148 */     this.scale = scale.copy();
/*     */   }
/*     */   
/*     */   public double value(double[] point) {
/* 154 */     double[] residuals = this.function.value(point);
/* 155 */     if (residuals.length != this.observations.length)
/* 156 */       throw new DimensionMismatchException(residuals.length, this.observations.length); 
/* 158 */     for (int i = 0; i < residuals.length; i++)
/* 159 */       residuals[i] = residuals[i] - this.observations[i]; 
/* 163 */     double sumSquares = 0.0D;
/* 164 */     if (this.weights != null) {
/* 165 */       for (int j = 0; j < residuals.length; j++) {
/* 166 */         double ri = residuals[j];
/* 167 */         sumSquares += this.weights[j] * ri * ri;
/*     */       } 
/* 169 */     } else if (this.scale != null) {
/* 170 */       for (double yi : this.scale.operate(residuals))
/* 171 */         sumSquares += yi * yi; 
/*     */     } else {
/* 174 */       for (double ri : residuals)
/* 175 */         sumSquares += ri * ri; 
/*     */     } 
/* 179 */     return sumSquares;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\LeastSquaresConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
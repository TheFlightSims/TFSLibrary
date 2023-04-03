/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class MultivariateFunctionPenaltyAdapter implements MultivariateFunction {
/*     */   private final MultivariateFunction bounded;
/*     */   
/*     */   private final double[] lower;
/*     */   
/*     */   private final double[] upper;
/*     */   
/*     */   private final double offset;
/*     */   
/*     */   private final double[] scale;
/*     */   
/*     */   public MultivariateFunctionPenaltyAdapter(MultivariateFunction bounded, double[] lower, double[] upper, double offset, double[] scale) {
/* 128 */     MathUtils.checkNotNull(lower);
/* 129 */     MathUtils.checkNotNull(upper);
/* 130 */     MathUtils.checkNotNull(scale);
/* 131 */     if (lower.length != upper.length)
/* 132 */       throw new DimensionMismatchException(lower.length, upper.length); 
/* 134 */     if (lower.length != scale.length)
/* 135 */       throw new DimensionMismatchException(lower.length, scale.length); 
/* 137 */     for (int i = 0; i < lower.length; i++) {
/* 139 */       if (upper[i] < lower[i])
/* 140 */         throw new NumberIsTooSmallException(Double.valueOf(upper[i]), Double.valueOf(lower[i]), true); 
/*     */     } 
/* 144 */     this.bounded = bounded;
/* 145 */     this.lower = (double[])lower.clone();
/* 146 */     this.upper = (double[])upper.clone();
/* 147 */     this.offset = offset;
/* 148 */     this.scale = (double[])scale.clone();
/*     */   }
/*     */   
/*     */   public double value(double[] point) {
/* 164 */     for (int i = 0; i < this.scale.length; i++) {
/* 165 */       if (point[i] < this.lower[i] || point[i] > this.upper[i]) {
/* 167 */         double sum = 0.0D;
/* 168 */         for (int j = i; j < this.scale.length; j++) {
/*     */           double overshoot;
/* 170 */           if (point[j] < this.lower[j]) {
/* 171 */             overshoot = this.scale[j] * (this.lower[j] - point[j]);
/* 172 */           } else if (point[j] > this.upper[j]) {
/* 173 */             overshoot = this.scale[j] * (point[j] - this.upper[j]);
/*     */           } else {
/* 175 */             overshoot = 0.0D;
/*     */           } 
/* 177 */           sum += FastMath.sqrt(overshoot);
/*     */         } 
/* 179 */         return this.offset + sum;
/*     */       } 
/*     */     } 
/* 185 */     return this.bounded.value(point);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\MultivariateFunctionPenaltyAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
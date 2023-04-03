/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.function.Logit;
/*     */ import org.apache.commons.math3.analysis.function.Sigmoid;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class MultivariateFunctionMappingAdapter implements MultivariateFunction {
/*     */   private final MultivariateFunction bounded;
/*     */   
/*     */   private final Mapper[] mappers;
/*     */   
/*     */   public MultivariateFunctionMappingAdapter(MultivariateFunction bounded, double[] lower, double[] upper) {
/* 101 */     MathUtils.checkNotNull(lower);
/* 102 */     MathUtils.checkNotNull(upper);
/* 103 */     if (lower.length != upper.length)
/* 104 */       throw new DimensionMismatchException(lower.length, upper.length); 
/*     */     int i;
/* 106 */     for (i = 0; i < lower.length; i++) {
/* 108 */       if (upper[i] < lower[i])
/* 109 */         throw new NumberIsTooSmallException(Double.valueOf(upper[i]), Double.valueOf(lower[i]), true); 
/*     */     } 
/* 113 */     this.bounded = bounded;
/* 114 */     this.mappers = new Mapper[lower.length];
/* 115 */     for (i = 0; i < this.mappers.length; i++) {
/* 116 */       if (Double.isInfinite(lower[i])) {
/* 117 */         if (Double.isInfinite(upper[i])) {
/* 119 */           this.mappers[i] = new NoBoundsMapper();
/*     */         } else {
/* 122 */           this.mappers[i] = new UpperBoundMapper(upper[i]);
/*     */         } 
/* 125 */       } else if (Double.isInfinite(upper[i])) {
/* 127 */         this.mappers[i] = new LowerBoundMapper(lower[i]);
/*     */       } else {
/* 130 */         this.mappers[i] = new LowerUpperBoundMapper(lower[i], upper[i]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] unboundedToBounded(double[] point) {
/* 144 */     double[] mapped = new double[this.mappers.length];
/* 145 */     for (int i = 0; i < this.mappers.length; i++)
/* 146 */       mapped[i] = this.mappers[i].unboundedToBounded(point[i]); 
/* 149 */     return mapped;
/*     */   }
/*     */   
/*     */   public double[] boundedToUnbounded(double[] point) {
/* 160 */     double[] mapped = new double[this.mappers.length];
/* 161 */     for (int i = 0; i < this.mappers.length; i++)
/* 162 */       mapped[i] = this.mappers[i].boundedToUnbounded(point[i]); 
/* 165 */     return mapped;
/*     */   }
/*     */   
/*     */   public double value(double[] point) {
/* 180 */     return this.bounded.value(unboundedToBounded(point));
/*     */   }
/*     */   
/*     */   private static interface Mapper {
/*     */     double unboundedToBounded(double param1Double);
/*     */     
/*     */     double boundedToUnbounded(double param1Double);
/*     */   }
/*     */   
/*     */   private static class NoBoundsMapper implements Mapper {
/*     */     public double unboundedToBounded(double y) {
/* 210 */       return y;
/*     */     }
/*     */     
/*     */     public double boundedToUnbounded(double x) {
/* 215 */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class LowerBoundMapper implements Mapper {
/*     */     private final double lower;
/*     */     
/*     */     public LowerBoundMapper(double lower) {
/* 230 */       this.lower = lower;
/*     */     }
/*     */     
/*     */     public double unboundedToBounded(double y) {
/* 235 */       return this.lower + FastMath.exp(y);
/*     */     }
/*     */     
/*     */     public double boundedToUnbounded(double x) {
/* 240 */       return FastMath.log(x - this.lower);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class UpperBoundMapper implements Mapper {
/*     */     private final double upper;
/*     */     
/*     */     public UpperBoundMapper(double upper) {
/* 255 */       this.upper = upper;
/*     */     }
/*     */     
/*     */     public double unboundedToBounded(double y) {
/* 260 */       return this.upper - FastMath.exp(-y);
/*     */     }
/*     */     
/*     */     public double boundedToUnbounded(double x) {
/* 265 */       return -FastMath.log(this.upper - x);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class LowerUpperBoundMapper implements Mapper {
/*     */     private final UnivariateFunction boundingFunction;
/*     */     
/*     */     private final UnivariateFunction unboundingFunction;
/*     */     
/*     */     public LowerUpperBoundMapper(double lower, double upper) {
/* 284 */       this.boundingFunction = (UnivariateFunction)new Sigmoid(lower, upper);
/* 285 */       this.unboundingFunction = (UnivariateFunction)new Logit(lower, upper);
/*     */     }
/*     */     
/*     */     public double unboundedToBounded(double y) {
/* 290 */       return this.boundingFunction.value(y);
/*     */     }
/*     */     
/*     */     public double boundedToUnbounded(double x) {
/* 295 */       return this.unboundingFunction.value(x);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\MultivariateFunctionMappingAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
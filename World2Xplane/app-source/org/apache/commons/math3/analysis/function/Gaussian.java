/*     */ package org.apache.commons.math3.analysis.function;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Gaussian implements DifferentiableUnivariateFunction {
/*     */   private final double mean;
/*     */   
/*     */   private final double i2s2;
/*     */   
/*     */   private final double norm;
/*     */   
/*     */   public Gaussian(double norm, double mean, double sigma) {
/*  54 */     if (sigma <= 0.0D)
/*  55 */       throw new NotStrictlyPositiveException(Double.valueOf(sigma)); 
/*  58 */     this.norm = norm;
/*  59 */     this.mean = mean;
/*  60 */     this.i2s2 = 1.0D / 2.0D * sigma * sigma;
/*     */   }
/*     */   
/*     */   public Gaussian(double mean, double sigma) {
/*  72 */     this(1.0D / sigma * FastMath.sqrt(6.283185307179586D), mean, sigma);
/*     */   }
/*     */   
/*     */   public Gaussian() {
/*  79 */     this(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public double value(double x) {
/*  84 */     return value(x - this.mean, this.norm, this.i2s2);
/*     */   }
/*     */   
/*     */   public UnivariateFunction derivative() {
/*  89 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/*  92 */           double diff = x - Gaussian.this.mean;
/*  93 */           double g = Gaussian.value(diff, Gaussian.this.norm, Gaussian.this.i2s2);
/*  95 */           if (g == 0.0D)
/*  97 */             return 0.0D; 
/*  99 */           return -2.0D * diff * Gaussian.this.i2s2 * g;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static class Parametric implements ParametricUnivariateFunction {
/*     */     public double value(double x, double... param) {
/* 127 */       validateParameters(param);
/* 129 */       double diff = x - param[1];
/* 130 */       double i2s2 = 1.0D / 2.0D * param[2] * param[2];
/* 131 */       return Gaussian.value(diff, param[0], i2s2);
/*     */     }
/*     */     
/*     */     public double[] gradient(double x, double... param) {
/* 149 */       validateParameters(param);
/* 151 */       double norm = param[0];
/* 152 */       double diff = x - param[1];
/* 153 */       double sigma = param[2];
/* 154 */       double i2s2 = 1.0D / 2.0D * sigma * sigma;
/* 156 */       double n = Gaussian.value(diff, 1.0D, i2s2);
/* 157 */       double m = norm * n * 2.0D * i2s2 * diff;
/* 158 */       double s = m * diff / sigma;
/* 160 */       return new double[] { n, m, s };
/*     */     }
/*     */     
/*     */     private void validateParameters(double[] param) {
/* 175 */       if (param == null)
/* 176 */         throw new NullArgumentException(); 
/* 178 */       if (param.length != 3)
/* 179 */         throw new DimensionMismatchException(param.length, 3); 
/* 181 */       if (param[2] <= 0.0D)
/* 182 */         throw new NotStrictlyPositiveException(Double.valueOf(param[2])); 
/*     */     }
/*     */   }
/*     */   
/*     */   private static double value(double xMinusMean, double norm, double i2s2) {
/* 196 */     return norm * FastMath.exp(-xMinusMean * xMinusMean * i2s2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Gaussian.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
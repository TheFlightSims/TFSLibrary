/*     */ package org.apache.commons.math3.analysis.function;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Logit implements DifferentiableUnivariateFunction {
/*     */   private final double lo;
/*     */   
/*     */   private final double hi;
/*     */   
/*     */   public Logit() {
/*  47 */     this(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public Logit(double lo, double hi) {
/*  58 */     this.lo = lo;
/*  59 */     this.hi = hi;
/*     */   }
/*     */   
/*     */   public double value(double x) {
/*  64 */     return value(x, this.lo, this.hi);
/*     */   }
/*     */   
/*     */   public UnivariateFunction derivative() {
/*  69 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/*  72 */           return (Logit.this.hi - Logit.this.lo) / (x - Logit.this.lo) * (Logit.this.hi - x);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static class Parametric implements ParametricUnivariateFunction {
/*     */     public double value(double x, double... param) {
/*  97 */       validateParameters(param);
/*  98 */       return Logit.value(x, param[0], param[1]);
/*     */     }
/*     */     
/*     */     public double[] gradient(double x, double... param) {
/* 115 */       validateParameters(param);
/* 117 */       double lo = param[0];
/* 118 */       double hi = param[1];
/* 120 */       return new double[] { 1.0D / (lo - x), 1.0D / (hi - x) };
/*     */     }
/*     */     
/*     */     private void validateParameters(double[] param) {
/* 134 */       if (param == null)
/* 135 */         throw new NullArgumentException(); 
/* 137 */       if (param.length != 2)
/* 138 */         throw new DimensionMismatchException(param.length, 2); 
/*     */     }
/*     */   }
/*     */   
/*     */   private static double value(double x, double lo, double hi) {
/* 152 */     if (x < lo || x > hi)
/* 153 */       throw new OutOfRangeException(Double.valueOf(x), Double.valueOf(lo), Double.valueOf(hi)); 
/* 155 */     return FastMath.log((x - lo) / (hi - x));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Logit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
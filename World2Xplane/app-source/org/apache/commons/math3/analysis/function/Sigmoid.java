/*     */ package org.apache.commons.math3.analysis.function;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Sigmoid implements DifferentiableUnivariateFunction {
/*     */   private final double lo;
/*     */   
/*     */   private final double hi;
/*     */   
/*     */   public Sigmoid() {
/*  48 */     this(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public Sigmoid(double lo, double hi) {
/*  59 */     this.lo = lo;
/*  60 */     this.hi = hi;
/*     */   }
/*     */   
/*     */   public UnivariateFunction derivative() {
/*  65 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/*  68 */           double exp = FastMath.exp(-x);
/*  69 */           if (Double.isInfinite(exp))
/*  71 */             return 0.0D; 
/*  73 */           double exp1 = 1.0D + exp;
/*  74 */           return (Sigmoid.this.hi - Sigmoid.this.lo) * exp / exp1 * exp1;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public double value(double x) {
/*  81 */     return value(x, this.lo, this.hi);
/*     */   }
/*     */   
/*     */   public static class Parametric implements ParametricUnivariateFunction {
/*     */     public double value(double x, double... param) {
/* 104 */       validateParameters(param);
/* 105 */       return Sigmoid.value(x, param[0], param[1]);
/*     */     }
/*     */     
/*     */     public double[] gradient(double x, double... param) {
/* 122 */       validateParameters(param);
/* 124 */       double invExp1 = 1.0D / (1.0D + FastMath.exp(-x));
/* 126 */       return new double[] { 1.0D - invExp1, invExp1 };
/*     */     }
/*     */     
/*     */     private void validateParameters(double[] param) {
/* 140 */       if (param == null)
/* 141 */         throw new NullArgumentException(); 
/* 143 */       if (param.length != 2)
/* 144 */         throw new DimensionMismatchException(param.length, 2); 
/*     */     }
/*     */   }
/*     */   
/*     */   private static double value(double x, double lo, double hi) {
/* 158 */     return lo + (hi - lo) / (1.0D + FastMath.exp(-x));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Sigmoid.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
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
/*     */ public class Logistic implements DifferentiableUnivariateFunction {
/*     */   private final double a;
/*     */   
/*     */   private final double k;
/*     */   
/*     */   private final double b;
/*     */   
/*     */   private final double oneOverN;
/*     */   
/*     */   private final double q;
/*     */   
/*     */   private final double m;
/*     */   
/*     */   public Logistic(double k, double m, double b, double q, double a, double n) {
/*  68 */     if (n <= 0.0D)
/*  69 */       throw new NotStrictlyPositiveException(Double.valueOf(n)); 
/*  72 */     this.k = k;
/*  73 */     this.m = m;
/*  74 */     this.b = b;
/*  75 */     this.q = q;
/*  76 */     this.a = a;
/*  77 */     this.oneOverN = 1.0D / n;
/*     */   }
/*     */   
/*     */   public double value(double x) {
/*  82 */     return value(this.m - x, this.k, this.b, this.q, this.a, this.oneOverN);
/*     */   }
/*     */   
/*     */   public UnivariateFunction derivative() {
/*  87 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/*  90 */           double exp = Logistic.this.q * FastMath.exp(Logistic.this.b * (Logistic.this.m - x));
/*  91 */           if (Double.isInfinite(exp))
/*  93 */             return 0.0D; 
/*  95 */           double exp1 = exp + 1.0D;
/*  96 */           return Logistic.this.b * Logistic.this.oneOverN * exp / FastMath.pow(exp1, Logistic.this.oneOverN + 1.0D);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static class Parametric implements ParametricUnivariateFunction {
/*     */     public double value(double x, double... param) {
/* 122 */       validateParameters(param);
/* 123 */       return Logistic.value(param[1] - x, param[0], param[2], param[3], param[4], 1.0D / param[5]);
/*     */     }
/*     */     
/*     */     public double[] gradient(double x, double... param) {
/* 143 */       validateParameters(param);
/* 145 */       double b = param[2];
/* 146 */       double q = param[3];
/* 148 */       double mMinusX = param[1] - x;
/* 149 */       double oneOverN = 1.0D / param[5];
/* 150 */       double exp = FastMath.exp(b * mMinusX);
/* 151 */       double qExp = q * exp;
/* 152 */       double qExp1 = qExp + 1.0D;
/* 153 */       double factor1 = (param[0] - param[4]) * oneOverN / FastMath.pow(qExp1, oneOverN);
/* 154 */       double factor2 = -factor1 / qExp1;
/* 157 */       double gk = Logistic.value(mMinusX, 1.0D, b, q, 0.0D, oneOverN);
/* 158 */       double gm = factor2 * b * qExp;
/* 159 */       double gb = factor2 * mMinusX * qExp;
/* 160 */       double gq = factor2 * exp;
/* 161 */       double ga = Logistic.value(mMinusX, 0.0D, b, q, 1.0D, oneOverN);
/* 162 */       double gn = factor1 * Math.log(qExp1) * oneOverN;
/* 164 */       return new double[] { gk, gm, gb, gq, ga, gn };
/*     */     }
/*     */     
/*     */     private void validateParameters(double[] param) {
/* 179 */       if (param == null)
/* 180 */         throw new NullArgumentException(); 
/* 182 */       if (param.length != 6)
/* 183 */         throw new DimensionMismatchException(param.length, 6); 
/* 185 */       if (param[5] <= 0.0D)
/* 186 */         throw new NotStrictlyPositiveException(Double.valueOf(param[5])); 
/*     */     }
/*     */   }
/*     */   
/*     */   private static double value(double mMinusX, double k, double b, double q, double a, double oneOverN) {
/* 206 */     return a + (k - a) / FastMath.pow(1.0D + q * FastMath.exp(b * mMinusX), oneOverN);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Logistic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
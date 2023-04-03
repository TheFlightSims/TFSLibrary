/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public abstract class ContinuedFraction {
/*     */   private static final double DEFAULT_EPSILON = 1.0E-8D;
/*     */   
/*     */   protected abstract double getA(int paramInt, double paramDouble);
/*     */   
/*     */   protected abstract double getB(int paramInt, double paramDouble);
/*     */   
/*     */   public double evaluate(double x) {
/*  73 */     return evaluate(x, 1.0E-8D, 2147483647);
/*     */   }
/*     */   
/*     */   public double evaluate(double x, double epsilon) {
/*  84 */     return evaluate(x, epsilon, 2147483647);
/*     */   }
/*     */   
/*     */   public double evaluate(double x, int maxIterations) {
/*  95 */     return evaluate(x, 1.0E-8D, maxIterations);
/*     */   }
/*     */   
/*     */   public double evaluate(double x, double epsilon, int maxIterations) {
/* 125 */     double p0 = 1.0D;
/* 126 */     double p1 = getA(0, x);
/* 127 */     double q0 = 0.0D;
/* 128 */     double q1 = 1.0D;
/* 129 */     double c = p1 / q1;
/* 130 */     int n = 0;
/* 131 */     double relativeError = Double.MAX_VALUE;
/* 132 */     while (n < maxIterations && relativeError > epsilon) {
/* 133 */       n++;
/* 134 */       double a = getA(n, x);
/* 135 */       double b = getB(n, x);
/* 136 */       double p2 = a * p1 + b * p0;
/* 137 */       double q2 = a * q1 + b * q0;
/* 138 */       boolean infinite = false;
/* 139 */       if (Double.isInfinite(p2) || Double.isInfinite(q2)) {
/* 145 */         double scaleFactor = 1.0D;
/* 146 */         double lastScaleFactor = 1.0D;
/* 147 */         int maxPower = 5;
/* 148 */         double scale = FastMath.max(a, b);
/* 149 */         if (scale <= 0.0D)
/* 150 */           throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_INFINITY_DIVERGENCE, new Object[] { Double.valueOf(x) }); 
/* 153 */         infinite = true;
/* 154 */         for (int i = 0; i < 5; i++) {
/* 155 */           lastScaleFactor = scaleFactor;
/* 156 */           scaleFactor *= scale;
/* 157 */           if (a != 0.0D && a > b) {
/* 158 */             p2 = p1 / lastScaleFactor + b / scaleFactor * p0;
/* 159 */             q2 = q1 / lastScaleFactor + b / scaleFactor * q0;
/* 160 */           } else if (b != 0.0D) {
/* 161 */             p2 = a / scaleFactor * p1 + p0 / lastScaleFactor;
/* 162 */             q2 = a / scaleFactor * q1 + q0 / lastScaleFactor;
/*     */           } 
/* 164 */           infinite = (Double.isInfinite(p2) || Double.isInfinite(q2));
/* 165 */           if (!infinite)
/*     */             break; 
/*     */         } 
/*     */       } 
/* 171 */       if (infinite)
/* 173 */         throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_INFINITY_DIVERGENCE, new Object[] { Double.valueOf(x) }); 
/* 177 */       double r = p2 / q2;
/* 179 */       if (Double.isNaN(r))
/* 180 */         throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_NAN_DIVERGENCE, new Object[] { Double.valueOf(x) }); 
/* 183 */       relativeError = FastMath.abs(r / c - 1.0D);
/* 186 */       c = p2 / q2;
/* 187 */       p0 = p1;
/* 188 */       p1 = p2;
/* 189 */       q0 = q1;
/* 190 */       q1 = q2;
/*     */     } 
/* 193 */     if (n >= maxIterations)
/* 194 */       throw new MaxCountExceededException(LocalizedFormats.NON_CONVERGENT_CONTINUED_FRACTION, Integer.valueOf(maxIterations), new Object[] { Double.valueOf(x) }); 
/* 198 */     return c;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\ContinuedFraction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
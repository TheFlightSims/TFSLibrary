/*     */ package org.apache.commons.math3.optimization.univariate;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public class BrentOptimizer extends BaseAbstractUnivariateOptimizer {
/*  42 */   private static final double GOLDEN_SECTION = 0.5D * (3.0D - FastMath.sqrt(5.0D));
/*     */   
/*  46 */   private static final double MIN_RELATIVE_TOLERANCE = 2.0D * FastMath.ulp(1.0D);
/*     */   
/*     */   private final double relativeThreshold;
/*     */   
/*     */   private final double absoluteThreshold;
/*     */   
/*     */   public BrentOptimizer(double rel, double abs, ConvergenceChecker<UnivariatePointValuePair> checker) {
/*  75 */     super(checker);
/*  77 */     if (rel < MIN_RELATIVE_TOLERANCE)
/*  78 */       throw new NumberIsTooSmallException(Double.valueOf(rel), Double.valueOf(MIN_RELATIVE_TOLERANCE), true); 
/*  80 */     if (abs <= 0.0D)
/*  81 */       throw new NotStrictlyPositiveException(Double.valueOf(abs)); 
/*  83 */     this.relativeThreshold = rel;
/*  84 */     this.absoluteThreshold = abs;
/*     */   }
/*     */   
/*     */   public BrentOptimizer(double rel, double abs) {
/* 103 */     this(rel, abs, (ConvergenceChecker<UnivariatePointValuePair>)null);
/*     */   }
/*     */   
/*     */   protected UnivariatePointValuePair doOptimize() {
/*     */     double a, b;
/* 109 */     boolean isMinim = (getGoalType() == GoalType.MINIMIZE);
/* 110 */     double lo = getMin();
/* 111 */     double mid = getStartValue();
/* 112 */     double hi = getMax();
/* 115 */     ConvergenceChecker<UnivariatePointValuePair> checker = getConvergenceChecker();
/* 120 */     if (lo < hi) {
/* 121 */       a = lo;
/* 122 */       b = hi;
/*     */     } else {
/* 124 */       a = hi;
/* 125 */       b = lo;
/*     */     } 
/* 128 */     double x = mid;
/* 129 */     double v = x;
/* 130 */     double w = x;
/* 131 */     double d = 0.0D;
/* 132 */     double e = 0.0D;
/* 133 */     double fx = computeObjectiveValue(x);
/* 134 */     if (!isMinim)
/* 135 */       fx = -fx; 
/* 137 */     double fv = fx;
/* 138 */     double fw = fx;
/* 140 */     UnivariatePointValuePair previous = null;
/* 141 */     UnivariatePointValuePair current = new UnivariatePointValuePair(x, isMinim ? fx : -fx);
/* 144 */     int iter = 0;
/*     */     while (true) {
/* 146 */       double m = 0.5D * (a + b);
/* 147 */       double tol1 = this.relativeThreshold * FastMath.abs(x) + this.absoluteThreshold;
/* 148 */       double tol2 = 2.0D * tol1;
/* 151 */       boolean stop = (FastMath.abs(x - m) <= tol2 - 0.5D * (b - a));
/* 152 */       if (!stop) {
/* 153 */         double p = 0.0D;
/* 154 */         double q = 0.0D;
/* 155 */         double r = 0.0D;
/* 156 */         double u = 0.0D;
/* 158 */         if (FastMath.abs(e) > tol1) {
/* 159 */           r = (x - w) * (fx - fv);
/* 160 */           q = (x - v) * (fx - fw);
/* 161 */           p = (x - v) * q - (x - w) * r;
/* 162 */           q = 2.0D * (q - r);
/* 164 */           if (q > 0.0D) {
/* 165 */             p = -p;
/*     */           } else {
/* 167 */             q = -q;
/*     */           } 
/* 170 */           r = e;
/* 171 */           e = d;
/* 173 */           if (p > q * (a - x) && p < q * (b - x) && FastMath.abs(p) < FastMath.abs(0.5D * q * r)) {
/* 177 */             d = p / q;
/* 178 */             u = x + d;
/* 181 */             if (u - a < tol2 || b - u < tol2)
/* 182 */               if (x <= m) {
/* 183 */                 d = tol1;
/*     */               } else {
/* 185 */                 d = -tol1;
/*     */               }  
/*     */           } else {
/* 190 */             if (x < m) {
/* 191 */               e = b - x;
/*     */             } else {
/* 193 */               e = a - x;
/*     */             } 
/* 195 */             d = GOLDEN_SECTION * e;
/*     */           } 
/*     */         } else {
/* 199 */           if (x < m) {
/* 200 */             e = b - x;
/*     */           } else {
/* 202 */             e = a - x;
/*     */           } 
/* 204 */           d = GOLDEN_SECTION * e;
/*     */         } 
/* 208 */         if (FastMath.abs(d) < tol1) {
/* 209 */           if (d >= 0.0D) {
/* 210 */             u = x + tol1;
/*     */           } else {
/* 212 */             u = x - tol1;
/*     */           } 
/*     */         } else {
/* 215 */           u = x + d;
/*     */         } 
/* 218 */         double fu = computeObjectiveValue(u);
/* 219 */         if (!isMinim)
/* 220 */           fu = -fu; 
/* 224 */         if (fu <= fx) {
/* 225 */           if (u < x) {
/* 226 */             b = x;
/*     */           } else {
/* 228 */             a = x;
/*     */           } 
/* 230 */           v = w;
/* 231 */           fv = fw;
/* 232 */           w = x;
/* 233 */           fw = fx;
/* 234 */           x = u;
/* 235 */           fx = fu;
/*     */         } else {
/* 237 */           if (u < x) {
/* 238 */             a = u;
/*     */           } else {
/* 240 */             b = u;
/*     */           } 
/* 242 */           if (fu <= fw || Precision.equals(w, x)) {
/* 244 */             v = w;
/* 245 */             fv = fw;
/* 246 */             w = u;
/* 247 */             fw = fu;
/* 248 */           } else if (fu <= fv || Precision.equals(v, x) || Precision.equals(v, w)) {
/* 251 */             v = u;
/* 252 */             fv = fu;
/*     */           } 
/*     */         } 
/* 256 */         previous = current;
/* 257 */         current = new UnivariatePointValuePair(x, isMinim ? fx : -fx);
/* 260 */         if (checker != null && 
/* 261 */           checker.converged(iter, previous, current))
/* 262 */           return current; 
/*     */       } else {
/* 266 */         return current;
/*     */       } 
/* 268 */       iter++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimizatio\\univariate\BrentOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NoBracketingException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public class BrentSolver extends AbstractUnivariateSolver {
/*     */   private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*     */   
/*     */   public BrentSolver() {
/*  45 */     this(1.0E-6D);
/*     */   }
/*     */   
/*     */   public BrentSolver(double absoluteAccuracy) {
/*  53 */     super(absoluteAccuracy);
/*     */   }
/*     */   
/*     */   public BrentSolver(double relativeAccuracy, double absoluteAccuracy) {
/*  63 */     super(relativeAccuracy, absoluteAccuracy);
/*     */   }
/*     */   
/*     */   public BrentSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/*  75 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
/*     */   }
/*     */   
/*     */   protected double doSolve() {
/*  83 */     double min = getMin();
/*  84 */     double max = getMax();
/*  85 */     double initial = getStartValue();
/*  86 */     double functionValueAccuracy = getFunctionValueAccuracy();
/*  88 */     verifySequence(min, initial, max);
/*  91 */     double yInitial = computeObjectiveValue(initial);
/*  92 */     if (FastMath.abs(yInitial) <= functionValueAccuracy)
/*  93 */       return initial; 
/*  97 */     double yMin = computeObjectiveValue(min);
/*  98 */     if (FastMath.abs(yMin) <= functionValueAccuracy)
/*  99 */       return min; 
/* 103 */     if (yInitial * yMin < 0.0D)
/* 104 */       return brent(min, initial, yMin, yInitial); 
/* 108 */     double yMax = computeObjectiveValue(max);
/* 109 */     if (FastMath.abs(yMax) <= functionValueAccuracy)
/* 110 */       return max; 
/* 114 */     if (yInitial * yMax < 0.0D)
/* 115 */       return brent(initial, max, yInitial, yMax); 
/* 118 */     throw new NoBracketingException(min, max, yMin, yMax);
/*     */   }
/*     */   
/*     */   private double brent(double lo, double hi, double fLo, double fHi) {
/* 139 */     double a = lo;
/* 140 */     double fa = fLo;
/* 141 */     double b = hi;
/* 142 */     double fb = fHi;
/* 143 */     double c = a;
/* 144 */     double fc = fa;
/* 145 */     double d = b - a;
/* 146 */     double e = d;
/* 148 */     double t = getAbsoluteAccuracy();
/* 149 */     double eps = getRelativeAccuracy();
/*     */     while (true) {
/* 152 */       if (FastMath.abs(fc) < FastMath.abs(fb)) {
/* 153 */         a = b;
/* 154 */         b = c;
/* 155 */         c = a;
/* 156 */         fa = fb;
/* 157 */         fb = fc;
/* 158 */         fc = fa;
/*     */       } 
/* 161 */       double tol = 2.0D * eps * FastMath.abs(b) + t;
/* 162 */       double m = 0.5D * (c - b);
/* 164 */       if (FastMath.abs(m) <= tol || Precision.equals(fb, 0.0D))
/* 166 */         return b; 
/* 168 */       if (FastMath.abs(e) < tol || FastMath.abs(fa) <= FastMath.abs(fb)) {
/* 171 */         d = m;
/* 172 */         e = d;
/*     */       } else {
/* 174 */         double p, q, s = fb / fa;
/* 180 */         if (a == c) {
/* 182 */           p = 2.0D * m * s;
/* 183 */           q = 1.0D - s;
/*     */         } else {
/* 186 */           q = fa / fc;
/* 187 */           double r = fb / fc;
/* 188 */           p = s * (2.0D * m * q * (q - r) - (b - a) * (r - 1.0D));
/* 189 */           q = (q - 1.0D) * (r - 1.0D) * (s - 1.0D);
/*     */         } 
/* 191 */         if (p > 0.0D) {
/* 192 */           q = -q;
/*     */         } else {
/* 194 */           p = -p;
/*     */         } 
/* 196 */         s = e;
/* 197 */         e = d;
/* 198 */         if (p >= 1.5D * m * q - FastMath.abs(tol * q) || p >= FastMath.abs(0.5D * s * q)) {
/* 203 */           d = m;
/* 204 */           e = d;
/*     */         } else {
/* 206 */           d = p / q;
/*     */         } 
/*     */       } 
/* 209 */       a = b;
/* 210 */       fa = fb;
/* 212 */       if (FastMath.abs(d) > tol) {
/* 213 */         b += d;
/* 214 */       } else if (m > 0.0D) {
/* 215 */         b += tol;
/*     */       } else {
/* 217 */         b -= tol;
/*     */       } 
/* 219 */       fb = computeObjectiveValue(b);
/* 220 */       if ((fb > 0.0D && fc > 0.0D) || (fb <= 0.0D && fc <= 0.0D)) {
/* 222 */         c = a;
/* 223 */         fc = fa;
/* 224 */         d = b - a;
/* 225 */         e = d;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\BrentSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
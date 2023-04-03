/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.complex.Complex;
/*     */ import org.apache.commons.math3.exception.NoBracketingException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class LaguerreSolver extends AbstractPolynomialSolver {
/*     */   private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*     */   
/*  45 */   private final ComplexSolver complexSolver = new ComplexSolver();
/*     */   
/*     */   public LaguerreSolver() {
/*  51 */     this(1.0E-6D);
/*     */   }
/*     */   
/*     */   public LaguerreSolver(double absoluteAccuracy) {
/*  59 */     super(absoluteAccuracy);
/*     */   }
/*     */   
/*     */   public LaguerreSolver(double relativeAccuracy, double absoluteAccuracy) {
/*  69 */     super(relativeAccuracy, absoluteAccuracy);
/*     */   }
/*     */   
/*     */   public LaguerreSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/*  81 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
/*     */   }
/*     */   
/*     */   public double doSolve() {
/*  89 */     double min = getMin();
/*  90 */     double max = getMax();
/*  91 */     double initial = getStartValue();
/*  92 */     double functionValueAccuracy = getFunctionValueAccuracy();
/*  94 */     verifySequence(min, initial, max);
/*  97 */     double yInitial = computeObjectiveValue(initial);
/*  98 */     if (FastMath.abs(yInitial) <= functionValueAccuracy)
/*  99 */       return initial; 
/* 103 */     double yMin = computeObjectiveValue(min);
/* 104 */     if (FastMath.abs(yMin) <= functionValueAccuracy)
/* 105 */       return min; 
/* 109 */     if (yInitial * yMin < 0.0D)
/* 110 */       return laguerre(min, initial, yMin, yInitial); 
/* 114 */     double yMax = computeObjectiveValue(max);
/* 115 */     if (FastMath.abs(yMax) <= functionValueAccuracy)
/* 116 */       return max; 
/* 120 */     if (yInitial * yMax < 0.0D)
/* 121 */       return laguerre(initial, max, yInitial, yMax); 
/* 124 */     throw new NoBracketingException(min, max, yMin, yMax);
/*     */   }
/*     */   
/*     */   public double laguerre(double lo, double hi, double fLo, double fHi) {
/* 147 */     double[] coefficients = getCoefficients();
/* 148 */     Complex[] c = new Complex[coefficients.length];
/* 149 */     for (int i = 0; i < coefficients.length; i++)
/* 150 */       c[i] = new Complex(coefficients[i], 0.0D); 
/* 152 */     Complex initial = new Complex(0.5D * (lo + hi), 0.0D);
/* 153 */     Complex z = this.complexSolver.solve(c, initial);
/* 154 */     if (this.complexSolver.isRoot(lo, hi, z))
/* 155 */       return z.getReal(); 
/* 157 */     double r = Double.NaN;
/* 159 */     Complex[] root = this.complexSolver.solveAll(c, initial);
/* 160 */     for (int j = 0; j < root.length; j++) {
/* 161 */       if (this.complexSolver.isRoot(lo, hi, root[j])) {
/* 162 */         r = root[j].getReal();
/*     */         break;
/*     */       } 
/*     */     } 
/* 166 */     return r;
/*     */   }
/*     */   
/*     */   private class ComplexSolver {
/*     */     private ComplexSolver() {}
/*     */     
/*     */     public boolean isRoot(double min, double max, Complex z) {
/* 184 */       if (LaguerreSolver.this.isSequence(min, z.getReal(), max)) {
/* 185 */         double tolerance = FastMath.max(LaguerreSolver.this.getRelativeAccuracy() * z.abs(), LaguerreSolver.this.getAbsoluteAccuracy());
/* 186 */         return (FastMath.abs(z.getImaginary()) <= tolerance || z.abs() <= LaguerreSolver.this.getFunctionValueAccuracy());
/*     */       } 
/* 189 */       return false;
/*     */     }
/*     */     
/*     */     public Complex[] solveAll(Complex[] coefficients, Complex initial) {
/* 206 */       if (coefficients == null)
/* 207 */         throw new NullArgumentException(); 
/* 209 */       int n = coefficients.length - 1;
/* 210 */       if (n == 0)
/* 211 */         throw new NoDataException(LocalizedFormats.POLYNOMIAL); 
/* 214 */       Complex[] c = new Complex[n + 1];
/* 215 */       for (int i = 0; i <= n; i++)
/* 216 */         c[i] = coefficients[i]; 
/* 220 */       Complex[] root = new Complex[n];
/* 221 */       for (int j = 0; j < n; j++) {
/* 222 */         Complex[] subarray = new Complex[n - j + 1];
/* 223 */         System.arraycopy(c, 0, subarray, 0, subarray.length);
/* 224 */         root[j] = solve(subarray, initial);
/* 226 */         Complex newc = c[n - j];
/* 227 */         Complex oldc = null;
/* 228 */         for (int k = n - j - 1; k >= 0; k--) {
/* 229 */           oldc = c[k];
/* 230 */           c[k] = newc;
/* 231 */           newc = oldc.add(newc.multiply(root[j]));
/*     */         } 
/*     */       } 
/* 235 */       return root;
/*     */     }
/*     */     
/*     */     public Complex solve(Complex[] coefficients, Complex initial) {
/* 252 */       if (coefficients == null)
/* 253 */         throw new NullArgumentException(); 
/* 256 */       int n = coefficients.length - 1;
/* 257 */       if (n == 0)
/* 258 */         throw new NoDataException(LocalizedFormats.POLYNOMIAL); 
/* 261 */       double absoluteAccuracy = LaguerreSolver.this.getAbsoluteAccuracy();
/* 262 */       double relativeAccuracy = LaguerreSolver.this.getRelativeAccuracy();
/* 263 */       double functionValueAccuracy = LaguerreSolver.this.getFunctionValueAccuracy();
/* 265 */       Complex N = new Complex(n, 0.0D);
/* 266 */       Complex N1 = new Complex((n - 1), 0.0D);
/* 268 */       Complex pv = null;
/* 269 */       Complex dv = null;
/* 270 */       Complex d2v = null;
/* 271 */       Complex G = null;
/* 272 */       Complex G2 = null;
/* 273 */       Complex H = null;
/* 274 */       Complex delta = null;
/* 275 */       Complex denominator = null;
/* 276 */       Complex z = initial;
/* 277 */       Complex oldz = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */       while (true) {
/* 282 */         pv = coefficients[n];
/* 283 */         dv = Complex.ZERO;
/* 284 */         d2v = Complex.ZERO;
/* 285 */         for (int j = n - 1; j >= 0; j--) {
/* 286 */           d2v = dv.add(z.multiply(d2v));
/* 287 */           dv = pv.add(z.multiply(dv));
/* 288 */           pv = coefficients[j].add(z.multiply(pv));
/*     */         } 
/* 290 */         d2v = d2v.multiply(new Complex(2.0D, 0.0D));
/* 293 */         double tolerance = FastMath.max(relativeAccuracy * z.abs(), absoluteAccuracy);
/* 295 */         if (z.subtract(oldz).abs() <= tolerance)
/* 296 */           return z; 
/* 298 */         if (pv.abs() <= functionValueAccuracy)
/* 299 */           return z; 
/* 303 */         G = dv.divide(pv);
/* 304 */         G2 = G.multiply(G);
/* 305 */         H = G2.subtract(d2v.divide(pv));
/* 306 */         delta = N1.multiply(N.multiply(H).subtract(G2));
/* 308 */         Complex deltaSqrt = delta.sqrt();
/* 309 */         Complex dplus = G.add(deltaSqrt);
/* 310 */         Complex dminus = G.subtract(deltaSqrt);
/* 311 */         denominator = (dplus.abs() > dminus.abs()) ? dplus : dminus;
/* 314 */         if (denominator.equals(new Complex(0.0D, 0.0D))) {
/* 315 */           z = z.add(new Complex(absoluteAccuracy, absoluteAccuracy));
/* 316 */           oldz = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */         } else {
/* 319 */           oldz = z;
/* 320 */           z = z.subtract(N.divide(denominator));
/*     */         } 
/* 322 */         LaguerreSolver.this.incrementEvaluationCount();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\LaguerreSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
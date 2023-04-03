/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.NoBracketingException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class UnivariateSolverUtils {
/*     */   public static double solve(UnivariateFunction function, double x0, double x1) {
/*  50 */     if (function == null)
/*  51 */       throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]); 
/*  53 */     UnivariateSolver solver = new BrentSolver();
/*  54 */     return solver.solve(2147483647, function, x0, x1);
/*     */   }
/*     */   
/*     */   public static double solve(UnivariateFunction function, double x0, double x1, double absoluteAccuracy) {
/*  73 */     if (function == null)
/*  74 */       throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]); 
/*  76 */     UnivariateSolver solver = new BrentSolver(absoluteAccuracy);
/*  77 */     return solver.solve(2147483647, function, x0, x1);
/*     */   }
/*     */   
/*     */   public static double forceSide(int maxEval, UnivariateFunction f, BracketedUnivariateSolver<UnivariateFunction> bracketing, double baseRoot, double min, double max, AllowedSolution allowedSolution) {
/*  99 */     if (allowedSolution == AllowedSolution.ANY_SIDE)
/* 101 */       return baseRoot; 
/* 105 */     double step = FastMath.max(bracketing.getAbsoluteAccuracy(), FastMath.abs(baseRoot * bracketing.getRelativeAccuracy()));
/* 107 */     double xLo = FastMath.max(min, baseRoot - step);
/* 108 */     double fLo = f.value(xLo);
/* 109 */     double xHi = FastMath.min(max, baseRoot + step);
/* 110 */     double fHi = f.value(xHi);
/* 111 */     int remainingEval = maxEval - 2;
/* 112 */     while (remainingEval > 0) {
/* 114 */       if ((fLo >= 0.0D && fHi <= 0.0D) || (fLo <= 0.0D && fHi >= 0.0D))
/* 116 */         return bracketing.solve(remainingEval, f, xLo, xHi, baseRoot, allowedSolution); 
/* 120 */       boolean changeLo = false;
/* 121 */       boolean changeHi = false;
/* 122 */       if (fLo < fHi) {
/* 124 */         if (fLo >= 0.0D) {
/* 125 */           changeLo = true;
/*     */         } else {
/* 127 */           changeHi = true;
/*     */         } 
/* 129 */       } else if (fLo > fHi) {
/* 131 */         if (fLo <= 0.0D) {
/* 132 */           changeLo = true;
/*     */         } else {
/* 134 */           changeHi = true;
/*     */         } 
/*     */       } else {
/* 138 */         changeLo = true;
/* 139 */         changeHi = true;
/*     */       } 
/* 143 */       if (changeLo) {
/* 144 */         xLo = FastMath.max(min, xLo - step);
/* 145 */         fLo = f.value(xLo);
/* 146 */         remainingEval--;
/*     */       } 
/* 150 */       if (changeHi) {
/* 151 */         xHi = FastMath.min(max, xHi + step);
/* 152 */         fHi = f.value(xHi);
/* 153 */         remainingEval--;
/*     */       } 
/*     */     } 
/* 158 */     throw new NoBracketingException(LocalizedFormats.FAILED_BRACKETING, xLo, xHi, fLo, fHi, new Object[] { Integer.valueOf(maxEval - remainingEval), Integer.valueOf(maxEval), Double.valueOf(baseRoot), Double.valueOf(min), Double.valueOf(max) });
/*     */   }
/*     */   
/*     */   public static double[] bracket(UnivariateFunction function, double initial, double lowerBound, double upperBound) {
/* 207 */     return bracket(function, initial, lowerBound, upperBound, 2147483647);
/*     */   }
/*     */   
/*     */   public static double[] bracket(UnivariateFunction function, double initial, double lowerBound, double upperBound, int maximumIterations) {
/*     */     double fa, fb;
/* 246 */     if (function == null)
/* 247 */       throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]); 
/* 249 */     if (maximumIterations <= 0)
/* 250 */       throw new NotStrictlyPositiveException(LocalizedFormats.INVALID_MAX_ITERATIONS, Integer.valueOf(maximumIterations)); 
/* 252 */     verifySequence(lowerBound, initial, upperBound);
/* 254 */     double a = initial;
/* 255 */     double b = initial;
/* 258 */     int numIterations = 0;
/*     */     do {
/* 261 */       a = FastMath.max(a - 1.0D, lowerBound);
/* 262 */       b = FastMath.min(b + 1.0D, upperBound);
/* 263 */       fa = function.value(a);
/* 265 */       fb = function.value(b);
/* 266 */       numIterations++;
/* 267 */     } while (fa * fb > 0.0D && numIterations < maximumIterations && (a > lowerBound || b < upperBound));
/* 270 */     if (fa * fb > 0.0D)
/* 271 */       throw new NoBracketingException(LocalizedFormats.FAILED_BRACKETING, a, b, fa, fb, new Object[] { Integer.valueOf(numIterations), Integer.valueOf(maximumIterations), Double.valueOf(initial), Double.valueOf(lowerBound), Double.valueOf(upperBound) }); 
/* 277 */     return new double[] { a, b };
/*     */   }
/*     */   
/*     */   public static double midpoint(double a, double b) {
/* 288 */     return (a + b) * 0.5D;
/*     */   }
/*     */   
/*     */   public static boolean isBracketing(UnivariateFunction function, double lower, double upper) {
/* 305 */     if (function == null)
/* 306 */       throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]); 
/* 308 */     double fLo = function.value(lower);
/* 309 */     double fHi = function.value(upper);
/* 310 */     return ((fLo >= 0.0D && fHi <= 0.0D) || (fLo <= 0.0D && fHi >= 0.0D));
/*     */   }
/*     */   
/*     */   public static boolean isSequence(double start, double mid, double end) {
/* 324 */     return (start < mid && mid < end);
/*     */   }
/*     */   
/*     */   public static void verifyInterval(double lower, double upper) {
/* 336 */     if (lower >= upper)
/* 337 */       throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(lower), Double.valueOf(upper), false); 
/*     */   }
/*     */   
/*     */   public static void verifySequence(double lower, double initial, double upper) {
/* 354 */     verifyInterval(lower, initial);
/* 355 */     verifyInterval(initial, upper);
/*     */   }
/*     */   
/*     */   public static void verifyBracketing(UnivariateFunction function, double lower, double upper) {
/* 371 */     if (function == null)
/* 372 */       throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]); 
/* 374 */     verifyInterval(lower, upper);
/* 375 */     if (!isBracketing(function, lower, upper))
/* 376 */       throw new NoBracketingException(lower, upper, function.value(lower), function.value(upper)); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\UnivariateSolverUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
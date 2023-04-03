/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.MathInternalError;
/*     */ import org.apache.commons.math3.exception.NoBracketingException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public class BracketingNthOrderBrentSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
/*     */   private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*     */   
/*     */   private static final int DEFAULT_MAXIMAL_ORDER = 5;
/*     */   
/*     */   private static final int MAXIMAL_AGING = 2;
/*     */   
/*     */   private static final double REDUCTION_FACTOR = 0.0625D;
/*     */   
/*     */   private final int maximalOrder;
/*     */   
/*     */   private AllowedSolution allowed;
/*     */   
/*     */   public BracketingNthOrderBrentSolver() {
/*  69 */     this(1.0E-6D, 5);
/*     */   }
/*     */   
/*     */   public BracketingNthOrderBrentSolver(double absoluteAccuracy, int maximalOrder) throws NumberIsTooSmallException {
/*  82 */     super(absoluteAccuracy);
/*  83 */     if (maximalOrder < 2)
/*  84 */       throw new NumberIsTooSmallException(Integer.valueOf(maximalOrder), Integer.valueOf(2), true); 
/*  86 */     this.maximalOrder = maximalOrder;
/*  87 */     this.allowed = AllowedSolution.ANY_SIDE;
/*     */   }
/*     */   
/*     */   public BracketingNthOrderBrentSolver(double relativeAccuracy, double absoluteAccuracy, int maximalOrder) throws NumberIsTooSmallException {
/* 102 */     super(relativeAccuracy, absoluteAccuracy);
/* 103 */     if (maximalOrder < 2)
/* 104 */       throw new NumberIsTooSmallException(Integer.valueOf(maximalOrder), Integer.valueOf(2), true); 
/* 106 */     this.maximalOrder = maximalOrder;
/* 107 */     this.allowed = AllowedSolution.ANY_SIDE;
/*     */   }
/*     */   
/*     */   public BracketingNthOrderBrentSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy, int maximalOrder) throws NumberIsTooSmallException {
/* 124 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
/* 125 */     if (maximalOrder < 2)
/* 126 */       throw new NumberIsTooSmallException(Integer.valueOf(maximalOrder), Integer.valueOf(2), true); 
/* 128 */     this.maximalOrder = maximalOrder;
/* 129 */     this.allowed = AllowedSolution.ANY_SIDE;
/*     */   }
/*     */   
/*     */   public int getMaximalOrder() {
/* 136 */     return this.maximalOrder;
/*     */   }
/*     */   
/*     */   protected double doSolve() {
/*     */     int nbPoints, signChangeIndex;
/* 146 */     double[] x = new double[this.maximalOrder + 1];
/* 147 */     double[] y = new double[this.maximalOrder + 1];
/* 148 */     x[0] = getMin();
/* 149 */     x[1] = getStartValue();
/* 150 */     x[2] = getMax();
/* 151 */     verifySequence(x[0], x[1], x[2]);
/* 154 */     y[1] = computeObjectiveValue(x[1]);
/* 155 */     if (Precision.equals(y[1], 0.0D, 1))
/* 157 */       return x[1]; 
/* 161 */     y[0] = computeObjectiveValue(x[0]);
/* 162 */     if (Precision.equals(y[0], 0.0D, 1))
/* 164 */       return x[0]; 
/* 169 */     if (y[0] * y[1] < 0.0D) {
/* 172 */       nbPoints = 2;
/* 173 */       signChangeIndex = 1;
/*     */     } else {
/* 178 */       y[2] = computeObjectiveValue(x[2]);
/* 179 */       if (Precision.equals(y[2], 0.0D, 1))
/* 181 */         return x[2]; 
/* 184 */       if (y[1] * y[2] < 0.0D) {
/* 186 */         nbPoints = 3;
/* 187 */         signChangeIndex = 2;
/*     */       } else {
/* 189 */         throw new NoBracketingException(x[0], x[2], y[0], y[2]);
/*     */       } 
/*     */     } 
/* 195 */     double[] tmpX = new double[x.length];
/* 198 */     double xA = x[signChangeIndex - 1];
/* 199 */     double yA = y[signChangeIndex - 1];
/* 200 */     double absYA = FastMath.abs(yA);
/* 201 */     int agingA = 0;
/* 202 */     double xB = x[signChangeIndex];
/* 203 */     double yB = y[signChangeIndex];
/* 204 */     double absYB = FastMath.abs(yB);
/* 205 */     int agingB = 0;
/*     */     while (true) {
/* 211 */       double targetY, nextX, xTol = getAbsoluteAccuracy() + getRelativeAccuracy() * FastMath.max(FastMath.abs(xA), FastMath.abs(xB));
/* 213 */       if (xB - xA <= xTol || FastMath.max(absYA, absYB) < getFunctionValueAccuracy()) {
/* 214 */         switch (this.allowed) {
/*     */           case ANY_SIDE:
/* 216 */             return (absYA < absYB) ? xA : xB;
/*     */           case LEFT_SIDE:
/* 218 */             return xA;
/*     */           case RIGHT_SIDE:
/* 220 */             return xB;
/*     */           case BELOW_SIDE:
/* 222 */             return (yA <= 0.0D) ? xA : xB;
/*     */           case ABOVE_SIDE:
/* 224 */             return (yA < 0.0D) ? xB : xA;
/*     */         } 
/* 227 */         throw new MathInternalError(null);
/*     */       } 
/* 233 */       if (agingA >= 2) {
/* 235 */         int p = agingA - 2;
/* 236 */         double weightA = ((1 << p) - 1);
/* 237 */         double weightB = (p + 1);
/* 238 */         targetY = (weightA * yA - weightB * 0.0625D * yB) / (weightA + weightB);
/* 239 */       } else if (agingB >= 2) {
/* 241 */         int p = agingB - 2;
/* 242 */         double weightA = (p + 1);
/* 243 */         double weightB = ((1 << p) - 1);
/* 244 */         targetY = (weightB * yB - weightA * 0.0625D * yA) / (weightA + weightB);
/*     */       } else {
/* 247 */         targetY = 0.0D;
/*     */       } 
/* 252 */       int start = 0;
/* 253 */       int end = nbPoints;
/*     */       do {
/* 257 */         System.arraycopy(x, start, tmpX, start, end - start);
/* 258 */         nextX = guessX(targetY, tmpX, y, start, end);
/* 260 */         if (nextX > xA && nextX < xB)
/*     */           continue; 
/* 266 */         if (signChangeIndex - start >= end - signChangeIndex) {
/* 268 */           start++;
/*     */         } else {
/* 271 */           end--;
/*     */         } 
/* 275 */         nextX = Double.NaN;
/* 279 */       } while (Double.isNaN(nextX) && end - start > 1);
/* 281 */       if (Double.isNaN(nextX)) {
/* 283 */         nextX = xA + 0.5D * (xB - xA);
/* 284 */         start = signChangeIndex - 1;
/* 285 */         end = signChangeIndex;
/*     */       } 
/* 289 */       double nextY = computeObjectiveValue(nextX);
/* 290 */       if (Precision.equals(nextY, 0.0D, 1))
/* 293 */         return nextX; 
/* 296 */       if (nbPoints > 2 && end - start != nbPoints) {
/* 300 */         nbPoints = end - start;
/* 301 */         System.arraycopy(x, start, x, 0, nbPoints);
/* 302 */         System.arraycopy(y, start, y, 0, nbPoints);
/* 303 */         signChangeIndex -= start;
/* 305 */       } else if (nbPoints == x.length) {
/* 308 */         nbPoints--;
/* 311 */         if (signChangeIndex >= (x.length + 1) / 2) {
/* 313 */           System.arraycopy(x, 1, x, 0, nbPoints);
/* 314 */           System.arraycopy(y, 1, y, 0, nbPoints);
/* 315 */           signChangeIndex--;
/*     */         } 
/*     */       } 
/* 322 */       System.arraycopy(x, signChangeIndex, x, signChangeIndex + 1, nbPoints - signChangeIndex);
/* 323 */       x[signChangeIndex] = nextX;
/* 324 */       System.arraycopy(y, signChangeIndex, y, signChangeIndex + 1, nbPoints - signChangeIndex);
/* 325 */       y[signChangeIndex] = nextY;
/* 326 */       nbPoints++;
/* 329 */       if (nextY * yA <= 0.0D) {
/* 331 */         xB = nextX;
/* 332 */         yB = nextY;
/* 333 */         absYB = FastMath.abs(yB);
/* 334 */         agingA++;
/* 335 */         agingB = 0;
/*     */         continue;
/*     */       } 
/* 338 */       xA = nextX;
/* 339 */       yA = nextY;
/* 340 */       absYA = FastMath.abs(yA);
/* 341 */       agingA = 0;
/* 342 */       agingB++;
/* 345 */       signChangeIndex++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private double guessX(double targetY, double[] x, double[] y, int start, int end) {
/* 371 */     for (int i = start; i < end - 1; i++) {
/* 372 */       int delta = i + 1 - start;
/* 373 */       for (int k = end - 1; k > i; k--)
/* 374 */         x[k] = (x[k] - x[k - 1]) / (y[k] - y[k - delta]); 
/*     */     } 
/* 379 */     double x0 = 0.0D;
/* 380 */     for (int j = end - 1; j >= start; j--)
/* 381 */       x0 = x[j] + x0 * (targetY - y[j]); 
/* 384 */     return x0;
/*     */   }
/*     */   
/*     */   public double solve(int maxEval, UnivariateFunction f, double min, double max, AllowedSolution allowedSolution) {
/* 391 */     this.allowed = allowedSolution;
/* 392 */     return solve(maxEval, f, min, max);
/*     */   }
/*     */   
/*     */   public double solve(int maxEval, UnivariateFunction f, double min, double max, double startValue, AllowedSolution allowedSolution) {
/* 399 */     this.allowed = allowedSolution;
/* 400 */     return solve(maxEval, f, min, max, startValue);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\BracketingNthOrderBrentSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
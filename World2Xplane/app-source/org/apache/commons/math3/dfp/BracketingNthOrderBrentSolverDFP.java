/*     */ package org.apache.commons.math3.dfp;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.solvers.AllowedSolution;
/*     */ import org.apache.commons.math3.exception.MathInternalError;
/*     */ import org.apache.commons.math3.exception.NoBracketingException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.util.Incrementor;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class BracketingNthOrderBrentSolverDFP {
/*     */   private static final int MAXIMAL_AGING = 2;
/*     */   
/*     */   private final int maximalOrder;
/*     */   
/*     */   private final Dfp functionValueAccuracy;
/*     */   
/*     */   private final Dfp absoluteAccuracy;
/*     */   
/*     */   private final Dfp relativeAccuracy;
/*     */   
/*  61 */   private final Incrementor evaluations = new Incrementor();
/*     */   
/*     */   public BracketingNthOrderBrentSolverDFP(Dfp relativeAccuracy, Dfp absoluteAccuracy, Dfp functionValueAccuracy, int maximalOrder) throws NumberIsTooSmallException {
/*  77 */     if (maximalOrder < 2)
/*  78 */       throw new NumberIsTooSmallException(Integer.valueOf(maximalOrder), Integer.valueOf(2), true); 
/*  80 */     this.maximalOrder = maximalOrder;
/*  81 */     this.absoluteAccuracy = absoluteAccuracy;
/*  82 */     this.relativeAccuracy = relativeAccuracy;
/*  83 */     this.functionValueAccuracy = functionValueAccuracy;
/*     */   }
/*     */   
/*     */   public int getMaximalOrder() {
/*  90 */     return this.maximalOrder;
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/*  99 */     return this.evaluations.getMaximalCount();
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/* 111 */     return this.evaluations.getCount();
/*     */   }
/*     */   
/*     */   public Dfp getAbsoluteAccuracy() {
/* 119 */     return this.absoluteAccuracy;
/*     */   }
/*     */   
/*     */   public Dfp getRelativeAccuracy() {
/* 127 */     return this.relativeAccuracy;
/*     */   }
/*     */   
/*     */   public Dfp getFunctionValueAccuracy() {
/* 135 */     return this.functionValueAccuracy;
/*     */   }
/*     */   
/*     */   public Dfp solve(int maxEval, UnivariateDfpFunction f, Dfp min, Dfp max, AllowedSolution allowedSolution) {
/* 158 */     return solve(maxEval, f, min, max, min.add(max).divide(2), allowedSolution);
/*     */   }
/*     */   
/*     */   public Dfp solve(int maxEval, UnivariateDfpFunction f, Dfp min, Dfp max, Dfp startValue, AllowedSolution allowedSolution) {
/*     */     int nbPoints, signChangeIndex;
/* 185 */     MathUtils.checkNotNull(f);
/* 188 */     this.evaluations.setMaximalCount(maxEval);
/* 189 */     this.evaluations.resetCount();
/* 190 */     Dfp zero = startValue.getZero();
/* 191 */     Dfp nan = zero.newInstance((byte)1, (byte)3);
/* 194 */     Dfp[] x = new Dfp[this.maximalOrder + 1];
/* 195 */     Dfp[] y = new Dfp[this.maximalOrder + 1];
/* 196 */     x[0] = min;
/* 197 */     x[1] = startValue;
/* 198 */     x[2] = max;
/* 201 */     this.evaluations.incrementCount();
/* 202 */     y[1] = f.value(x[1]);
/* 203 */     if (y[1].isZero())
/* 205 */       return x[1]; 
/* 209 */     this.evaluations.incrementCount();
/* 210 */     y[0] = f.value(x[0]);
/* 211 */     if (y[0].isZero())
/* 213 */       return x[0]; 
/* 218 */     if (y[0].multiply(y[1]).negativeOrNull()) {
/* 221 */       nbPoints = 2;
/* 222 */       signChangeIndex = 1;
/*     */     } else {
/* 227 */       this.evaluations.incrementCount();
/* 228 */       y[2] = f.value(x[2]);
/* 229 */       if (y[2].isZero())
/* 231 */         return x[2]; 
/* 234 */       if (y[1].multiply(y[2]).negativeOrNull()) {
/* 236 */         nbPoints = 3;
/* 237 */         signChangeIndex = 2;
/*     */       } else {
/* 239 */         throw new NoBracketingException(x[0].toDouble(), x[2].toDouble(), y[0].toDouble(), y[2].toDouble());
/*     */       } 
/*     */     } 
/* 246 */     Dfp[] tmpX = new Dfp[x.length];
/* 249 */     Dfp xA = x[signChangeIndex - 1];
/* 250 */     Dfp yA = y[signChangeIndex - 1];
/* 251 */     Dfp absXA = xA.abs();
/* 252 */     Dfp absYA = yA.abs();
/* 253 */     int agingA = 0;
/* 254 */     Dfp xB = x[signChangeIndex];
/* 255 */     Dfp yB = y[signChangeIndex];
/* 256 */     Dfp absXB = xB.abs();
/* 257 */     Dfp absYB = yB.abs();
/* 258 */     int agingB = 0;
/*     */     while (true) {
/* 264 */       Dfp targetY, nextX, maxX = absXA.lessThan(absXB) ? absXB : absXA;
/* 265 */       Dfp maxY = absYA.lessThan(absYB) ? absYB : absYA;
/* 266 */       Dfp xTol = this.absoluteAccuracy.add(this.relativeAccuracy.multiply(maxX));
/* 267 */       if (xB.subtract(xA).subtract(xTol).negativeOrNull() || maxY.lessThan(this.functionValueAccuracy)) {
/* 269 */         switch (allowedSolution) {
/*     */           case ANY_SIDE:
/* 271 */             return absYA.lessThan(absYB) ? xA : xB;
/*     */           case LEFT_SIDE:
/* 273 */             return xA;
/*     */           case RIGHT_SIDE:
/* 275 */             return xB;
/*     */           case BELOW_SIDE:
/* 277 */             return yA.lessThan(zero) ? xA : xB;
/*     */           case ABOVE_SIDE:
/* 279 */             return yA.lessThan(zero) ? xB : xA;
/*     */         } 
/* 282 */         throw new MathInternalError(null);
/*     */       } 
/* 288 */       if (agingA >= 2) {
/* 290 */         targetY = yB.divide(16).negate();
/* 291 */       } else if (agingB >= 2) {
/* 293 */         targetY = yA.divide(16).negate();
/*     */       } else {
/* 296 */         targetY = zero;
/*     */       } 
/* 301 */       int start = 0;
/* 302 */       int end = nbPoints;
/*     */       do {
/* 306 */         System.arraycopy(x, start, tmpX, start, end - start);
/* 307 */         nextX = guessX(targetY, tmpX, y, start, end);
/* 309 */         if (nextX.greaterThan(xA) && nextX.lessThan(xB))
/*     */           continue; 
/* 315 */         if (signChangeIndex - start >= end - signChangeIndex) {
/* 317 */           start++;
/*     */         } else {
/* 320 */           end--;
/*     */         } 
/* 324 */         nextX = nan;
/* 328 */       } while (nextX.isNaN() && end - start > 1);
/* 330 */       if (nextX.isNaN()) {
/* 332 */         nextX = xA.add(xB.subtract(xA).divide(2));
/* 333 */         start = signChangeIndex - 1;
/* 334 */         end = signChangeIndex;
/*     */       } 
/* 338 */       this.evaluations.incrementCount();
/* 339 */       Dfp nextY = f.value(nextX);
/* 340 */       if (nextY.isZero())
/* 343 */         return nextX; 
/* 346 */       if (nbPoints > 2 && end - start != nbPoints) {
/* 350 */         nbPoints = end - start;
/* 351 */         System.arraycopy(x, start, x, 0, nbPoints);
/* 352 */         System.arraycopy(y, start, y, 0, nbPoints);
/* 353 */         signChangeIndex -= start;
/* 355 */       } else if (nbPoints == x.length) {
/* 358 */         nbPoints--;
/* 361 */         if (signChangeIndex >= (x.length + 1) / 2) {
/* 363 */           System.arraycopy(x, 1, x, 0, nbPoints);
/* 364 */           System.arraycopy(y, 1, y, 0, nbPoints);
/* 365 */           signChangeIndex--;
/*     */         } 
/*     */       } 
/* 372 */       System.arraycopy(x, signChangeIndex, x, signChangeIndex + 1, nbPoints - signChangeIndex);
/* 373 */       x[signChangeIndex] = nextX;
/* 374 */       System.arraycopy(y, signChangeIndex, y, signChangeIndex + 1, nbPoints - signChangeIndex);
/* 375 */       y[signChangeIndex] = nextY;
/* 376 */       nbPoints++;
/* 379 */       if (nextY.multiply(yA).negativeOrNull()) {
/* 381 */         xB = nextX;
/* 382 */         yB = nextY;
/* 383 */         absYB = yB.abs();
/* 384 */         agingA++;
/* 385 */         agingB = 0;
/*     */         continue;
/*     */       } 
/* 388 */       xA = nextX;
/* 389 */       yA = nextY;
/* 390 */       absYA = yA.abs();
/* 391 */       agingA = 0;
/* 392 */       agingB++;
/* 395 */       signChangeIndex++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Dfp guessX(Dfp targetY, Dfp[] x, Dfp[] y, int start, int end) {
/* 421 */     for (int i = start; i < end - 1; i++) {
/* 422 */       int delta = i + 1 - start;
/* 423 */       for (int k = end - 1; k > i; k--)
/* 424 */         x[k] = x[k].subtract(x[k - 1]).divide(y[k].subtract(y[k - delta])); 
/*     */     } 
/* 429 */     Dfp x0 = targetY.getZero();
/* 430 */     for (int j = end - 1; j >= start; j--)
/* 431 */       x0 = x[j].add(x0.multiply(targetY.subtract(y[j]))); 
/* 434 */     return x0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\dfp\BracketingNthOrderBrentSolverDFP.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
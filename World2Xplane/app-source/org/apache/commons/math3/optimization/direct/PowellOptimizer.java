/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.optimization.MultivariateOptimizer;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ import org.apache.commons.math3.optimization.univariate.BracketFinder;
/*     */ import org.apache.commons.math3.optimization.univariate.BrentOptimizer;
/*     */ import org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class PowellOptimizer extends BaseAbstractMultivariateOptimizer<MultivariateFunction> implements MultivariateOptimizer {
/*  54 */   private static final double MIN_RELATIVE_TOLERANCE = 2.0D * FastMath.ulp(1.0D);
/*     */   
/*     */   private final double relativeThreshold;
/*     */   
/*     */   private final double absoluteThreshold;
/*     */   
/*     */   private final LineSearch line;
/*     */   
/*     */   public PowellOptimizer(double rel, double abs, ConvergenceChecker<PointValuePair> checker) {
/*  82 */     super(checker);
/*  84 */     if (rel < MIN_RELATIVE_TOLERANCE)
/*  85 */       throw new NumberIsTooSmallException(Double.valueOf(rel), Double.valueOf(MIN_RELATIVE_TOLERANCE), true); 
/*  87 */     if (abs <= 0.0D)
/*  88 */       throw new NotStrictlyPositiveException(Double.valueOf(abs)); 
/*  90 */     this.relativeThreshold = rel;
/*  91 */     this.absoluteThreshold = abs;
/*  95 */     double minTol = 1.0E-4D;
/*  96 */     double lsRel = Math.min(FastMath.sqrt(this.relativeThreshold), 1.0E-4D);
/*  97 */     double lsAbs = Math.min(FastMath.sqrt(this.absoluteThreshold), 1.0E-4D);
/*  98 */     this.line = new LineSearch(lsRel, lsAbs);
/*     */   }
/*     */   
/*     */   public PowellOptimizer(double rel, double abs) {
/* 112 */     this(rel, abs, (ConvergenceChecker<PointValuePair>)null);
/*     */   }
/*     */   
/*     */   protected PointValuePair doOptimize() {
/* 118 */     GoalType goal = getGoalType();
/* 119 */     double[] guess = getStartPoint();
/* 120 */     int n = guess.length;
/* 122 */     double[][] direc = new double[n][n];
/* 123 */     for (int i = 0; i < n; i++)
/* 124 */       direc[i][i] = 1.0D; 
/* 127 */     ConvergenceChecker<PointValuePair> checker = getConvergenceChecker();
/* 130 */     double[] x = guess;
/* 131 */     double fVal = computeObjectiveValue(x);
/* 132 */     double[] x1 = (double[])x.clone();
/* 133 */     int iter = 0;
/*     */     while (true) {
/* 135 */       iter++;
/* 137 */       double fX = fVal;
/* 138 */       double fX2 = 0.0D;
/* 139 */       double delta = 0.0D;
/* 140 */       int bigInd = 0;
/* 141 */       double alphaMin = 0.0D;
/* 143 */       for (int j = 0; j < n; j++) {
/* 144 */         double[] arrayOfDouble = MathArrays.copyOf(direc[j]);
/* 146 */         fX2 = fVal;
/* 148 */         UnivariatePointValuePair optimum = this.line.search(x, arrayOfDouble);
/* 149 */         fVal = optimum.getValue();
/* 150 */         alphaMin = optimum.getPoint();
/* 151 */         double[][] result = newPointAndDirection(x, arrayOfDouble, alphaMin);
/* 152 */         x = result[0];
/* 154 */         if (fX2 - fVal > delta) {
/* 155 */           delta = fX2 - fVal;
/* 156 */           bigInd = j;
/*     */         } 
/*     */       } 
/* 161 */       boolean stop = (2.0D * (fX - fVal) <= this.relativeThreshold * (FastMath.abs(fX) + FastMath.abs(fVal)) + this.absoluteThreshold);
/* 165 */       PointValuePair previous = new PointValuePair(x1, fX);
/* 166 */       PointValuePair current = new PointValuePair(x, fVal);
/* 167 */       if (!stop && 
/* 168 */         checker != null)
/* 169 */         stop = checker.converged(iter, previous, current); 
/* 172 */       if (stop) {
/* 173 */         if (goal == GoalType.MINIMIZE)
/* 174 */           return (fVal < fX) ? current : previous; 
/* 176 */         return (fVal > fX) ? current : previous;
/*     */       } 
/* 180 */       double[] d = new double[n];
/* 181 */       double[] x2 = new double[n];
/* 182 */       for (int k = 0; k < n; k++) {
/* 183 */         d[k] = x[k] - x1[k];
/* 184 */         x2[k] = 2.0D * x[k] - x1[k];
/*     */       } 
/* 187 */       x1 = (double[])x.clone();
/* 188 */       fX2 = computeObjectiveValue(x2);
/* 190 */       if (fX > fX2) {
/* 191 */         double t = 2.0D * (fX + fX2 - 2.0D * fVal);
/* 192 */         double temp = fX - fVal - delta;
/* 193 */         t *= temp * temp;
/* 194 */         temp = fX - fX2;
/* 195 */         t -= delta * temp * temp;
/* 197 */         if (t < 0.0D) {
/* 198 */           UnivariatePointValuePair optimum = this.line.search(x, d);
/* 199 */           fVal = optimum.getValue();
/* 200 */           alphaMin = optimum.getPoint();
/* 201 */           double[][] result = newPointAndDirection(x, d, alphaMin);
/* 202 */           x = result[0];
/* 204 */           int lastInd = n - 1;
/* 205 */           direc[bigInd] = direc[lastInd];
/* 206 */           direc[lastInd] = result[1];
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private double[][] newPointAndDirection(double[] p, double[] d, double optimum) {
/* 226 */     int n = p.length;
/* 227 */     double[][] result = new double[2][n];
/* 228 */     double[] nP = result[0];
/* 229 */     double[] nD = result[1];
/* 230 */     for (int i = 0; i < n; i++) {
/* 231 */       nD[i] = d[i] * optimum;
/* 232 */       nP[i] = p[i] + nD[i];
/*     */     } 
/* 234 */     return result;
/*     */   }
/*     */   
/*     */   private class LineSearch extends BrentOptimizer {
/* 245 */     private final BracketFinder bracket = new BracketFinder();
/*     */     
/*     */     LineSearch(double rel, double abs) {
/* 253 */       super(rel, abs);
/*     */     }
/*     */     
/*     */     public UnivariatePointValuePair search(final double[] p, final double[] d) {
/* 266 */       final int n = p.length;
/* 267 */       UnivariateFunction f = new UnivariateFunction() {
/*     */           public double value(double alpha) {
/* 269 */             double[] x = new double[n];
/* 270 */             for (int i = 0; i < n; i++)
/* 271 */               x[i] = p[i] + alpha * d[i]; 
/* 273 */             double obj = PowellOptimizer.this.computeObjectiveValue(x);
/* 274 */             return obj;
/*     */           }
/*     */         };
/* 278 */       GoalType goal = PowellOptimizer.this.getGoalType();
/* 279 */       this.bracket.search(f, goal, 0.0D, 1.0D);
/* 283 */       return optimize(2147483647, f, goal, this.bracket.getLo(), this.bracket.getHi(), this.bracket.getMid());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\PowellOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
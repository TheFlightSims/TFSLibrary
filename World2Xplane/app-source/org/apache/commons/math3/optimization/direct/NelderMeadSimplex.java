/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ 
/*     */ public class NelderMeadSimplex extends AbstractSimplex {
/*     */   private static final double DEFAULT_RHO = 1.0D;
/*     */   
/*     */   private static final double DEFAULT_KHI = 2.0D;
/*     */   
/*     */   private static final double DEFAULT_GAMMA = 0.5D;
/*     */   
/*     */   private static final double DEFAULT_SIGMA = 0.5D;
/*     */   
/*     */   private final double rho;
/*     */   
/*     */   private final double khi;
/*     */   
/*     */   private final double gamma;
/*     */   
/*     */   private final double sigma;
/*     */   
/*     */   public NelderMeadSimplex(int n) {
/*  57 */     this(n, 1.0D);
/*     */   }
/*     */   
/*     */   public NelderMeadSimplex(int n, double sideLength) {
/*  70 */     this(n, sideLength, 1.0D, 2.0D, 0.5D, 0.5D);
/*     */   }
/*     */   
/*     */   public NelderMeadSimplex(int n, double sideLength, double rho, double khi, double gamma, double sigma) {
/*  89 */     super(n, sideLength);
/*  91 */     this.rho = rho;
/*  92 */     this.khi = khi;
/*  93 */     this.gamma = gamma;
/*  94 */     this.sigma = sigma;
/*     */   }
/*     */   
/*     */   public NelderMeadSimplex(int n, double rho, double khi, double gamma, double sigma) {
/* 110 */     this(n, 1.0D, rho, khi, gamma, sigma);
/*     */   }
/*     */   
/*     */   public NelderMeadSimplex(double[] steps) {
/* 122 */     this(steps, 1.0D, 2.0D, 0.5D, 0.5D);
/*     */   }
/*     */   
/*     */   public NelderMeadSimplex(double[] steps, double rho, double khi, double gamma, double sigma) {
/* 140 */     super(steps);
/* 142 */     this.rho = rho;
/* 143 */     this.khi = khi;
/* 144 */     this.gamma = gamma;
/* 145 */     this.sigma = sigma;
/*     */   }
/*     */   
/*     */   public NelderMeadSimplex(double[][] referenceSimplex) {
/* 157 */     this(referenceSimplex, 1.0D, 2.0D, 0.5D, 0.5D);
/*     */   }
/*     */   
/*     */   public NelderMeadSimplex(double[][] referenceSimplex, double rho, double khi, double gamma, double sigma) {
/* 177 */     super(referenceSimplex);
/* 179 */     this.rho = rho;
/* 180 */     this.khi = khi;
/* 181 */     this.gamma = gamma;
/* 182 */     this.sigma = sigma;
/*     */   }
/*     */   
/*     */   public void iterate(MultivariateFunction evaluationFunction, Comparator<PointValuePair> comparator) {
/* 190 */     int n = getDimension();
/* 193 */     PointValuePair best = getPoint(0);
/* 194 */     PointValuePair secondBest = getPoint(n - 1);
/* 195 */     PointValuePair worst = getPoint(n);
/* 196 */     double[] xWorst = worst.getPointRef();
/* 200 */     double[] centroid = new double[n];
/* 201 */     for (int i = 0; i < n; i++) {
/* 202 */       double[] x = getPoint(i).getPointRef();
/* 203 */       for (int m = 0; m < n; m++)
/* 204 */         centroid[m] = centroid[m] + x[m]; 
/*     */     } 
/* 207 */     double scaling = 1.0D / n;
/* 208 */     for (int j = 0; j < n; j++)
/* 209 */       centroid[j] = centroid[j] * scaling; 
/* 213 */     double[] xR = new double[n];
/* 214 */     for (int k = 0; k < n; k++)
/* 215 */       xR[k] = centroid[k] + this.rho * (centroid[k] - xWorst[k]); 
/* 217 */     PointValuePair reflected = new PointValuePair(xR, evaluationFunction.value(xR), false);
/* 220 */     if (comparator.compare(best, reflected) <= 0 && comparator.compare(reflected, secondBest) < 0) {
/* 223 */       replaceWorstPoint(reflected, comparator);
/* 224 */     } else if (comparator.compare(reflected, best) < 0) {
/* 226 */       double[] xE = new double[n];
/* 227 */       for (int m = 0; m < n; m++)
/* 228 */         xE[m] = centroid[m] + this.khi * (xR[m] - centroid[m]); 
/* 230 */       PointValuePair expanded = new PointValuePair(xE, evaluationFunction.value(xE), false);
/* 233 */       if (comparator.compare(expanded, reflected) < 0) {
/* 235 */         replaceWorstPoint(expanded, comparator);
/*     */       } else {
/* 238 */         replaceWorstPoint(reflected, comparator);
/*     */       } 
/*     */     } else {
/* 241 */       if (comparator.compare(reflected, worst) < 0) {
/* 243 */         double[] xC = new double[n];
/* 244 */         for (int i1 = 0; i1 < n; i1++)
/* 245 */           xC[i1] = centroid[i1] + this.gamma * (xR[i1] - centroid[i1]); 
/* 247 */         PointValuePair outContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
/* 249 */         if (comparator.compare(outContracted, reflected) <= 0) {
/* 251 */           replaceWorstPoint(outContracted, comparator);
/*     */           return;
/*     */         } 
/*     */       } else {
/* 256 */         double[] xC = new double[n];
/* 257 */         for (int i1 = 0; i1 < n; i1++)
/* 258 */           xC[i1] = centroid[i1] - this.gamma * (centroid[i1] - xWorst[i1]); 
/* 260 */         PointValuePair inContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
/* 263 */         if (comparator.compare(inContracted, worst) < 0) {
/* 265 */           replaceWorstPoint(inContracted, comparator);
/*     */           return;
/*     */         } 
/*     */       } 
/* 271 */       double[] xSmallest = getPoint(0).getPointRef();
/* 272 */       for (int m = 1; m <= n; m++) {
/* 273 */         double[] x = getPoint(m).getPoint();
/* 274 */         for (int i1 = 0; i1 < n; i1++)
/* 275 */           x[i1] = xSmallest[i1] + this.sigma * (x[i1] - xSmallest[i1]); 
/* 277 */         setPoint(m, new PointValuePair(x, Double.NaN, false));
/*     */       } 
/* 279 */       evaluate(evaluationFunction, comparator);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\NelderMeadSimplex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
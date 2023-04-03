/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ 
/*     */ public class MultiDirectionalSimplex extends AbstractSimplex {
/*     */   private static final double DEFAULT_KHI = 2.0D;
/*     */   
/*     */   private static final double DEFAULT_GAMMA = 0.5D;
/*     */   
/*     */   private final double khi;
/*     */   
/*     */   private final double gamma;
/*     */   
/*     */   public MultiDirectionalSimplex(int n) {
/*  48 */     this(n, 1.0D);
/*     */   }
/*     */   
/*     */   public MultiDirectionalSimplex(int n, double sideLength) {
/*  60 */     this(n, sideLength, 2.0D, 0.5D);
/*     */   }
/*     */   
/*     */   public MultiDirectionalSimplex(int n, double khi, double gamma) {
/*  73 */     this(n, 1.0D, khi, gamma);
/*     */   }
/*     */   
/*     */   public MultiDirectionalSimplex(int n, double sideLength, double khi, double gamma) {
/*  88 */     super(n, sideLength);
/*  90 */     this.khi = khi;
/*  91 */     this.gamma = gamma;
/*     */   }
/*     */   
/*     */   public MultiDirectionalSimplex(double[] steps) {
/* 102 */     this(steps, 2.0D, 0.5D);
/*     */   }
/*     */   
/*     */   public MultiDirectionalSimplex(double[] steps, double khi, double gamma) {
/* 116 */     super(steps);
/* 118 */     this.khi = khi;
/* 119 */     this.gamma = gamma;
/*     */   }
/*     */   
/*     */   public MultiDirectionalSimplex(double[][] referenceSimplex) {
/* 130 */     this(referenceSimplex, 2.0D, 0.5D);
/*     */   }
/*     */   
/*     */   public MultiDirectionalSimplex(double[][] referenceSimplex, double khi, double gamma) {
/* 147 */     super(referenceSimplex);
/* 149 */     this.khi = khi;
/* 150 */     this.gamma = gamma;
/*     */   }
/*     */   
/*     */   public void iterate(MultivariateFunction evaluationFunction, Comparator<PointValuePair> comparator) {
/* 158 */     PointValuePair[] original = getPoints();
/* 159 */     PointValuePair best = original[0];
/* 162 */     PointValuePair reflected = evaluateNewSimplex(evaluationFunction, original, 1.0D, comparator);
/* 164 */     if (comparator.compare(reflected, best) < 0) {
/* 166 */       PointValuePair[] reflectedSimplex = getPoints();
/* 167 */       PointValuePair expanded = evaluateNewSimplex(evaluationFunction, original, this.khi, comparator);
/* 169 */       if (comparator.compare(reflected, expanded) <= 0)
/* 171 */         setPoints(reflectedSimplex); 
/*     */       return;
/*     */     } 
/* 178 */     evaluateNewSimplex(evaluationFunction, original, this.gamma, comparator);
/*     */   }
/*     */   
/*     */   private PointValuePair evaluateNewSimplex(MultivariateFunction evaluationFunction, PointValuePair[] original, double coeff, Comparator<PointValuePair> comparator) {
/* 198 */     double[] xSmallest = original[0].getPointRef();
/* 201 */     setPoint(0, original[0]);
/* 202 */     int dim = getDimension();
/* 203 */     for (int i = 1; i < getSize(); i++) {
/* 204 */       double[] xOriginal = original[i].getPointRef();
/* 205 */       double[] xTransformed = new double[dim];
/* 206 */       for (int j = 0; j < dim; j++)
/* 207 */         xTransformed[j] = xSmallest[j] + coeff * (xSmallest[j] - xOriginal[j]); 
/* 209 */       setPoint(i, new PointValuePair(xTransformed, Double.NaN, false));
/*     */     } 
/* 213 */     evaluate(evaluationFunction, comparator);
/* 215 */     return getPoint(0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\MultiDirectionalSimplex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
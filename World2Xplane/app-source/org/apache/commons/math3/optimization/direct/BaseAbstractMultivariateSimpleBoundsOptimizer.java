/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.optimization.BaseMultivariateOptimizer;
/*     */ import org.apache.commons.math3.optimization.BaseMultivariateSimpleBoundsOptimizer;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ 
/*     */ public abstract class BaseAbstractMultivariateSimpleBoundsOptimizer<FUNC extends MultivariateFunction> extends BaseAbstractMultivariateOptimizer<FUNC> implements BaseMultivariateOptimizer<FUNC>, BaseMultivariateSimpleBoundsOptimizer<FUNC> {
/*     */   private double[] lowerBound;
/*     */   
/*     */   private double[] upperBound;
/*     */   
/*     */   protected BaseAbstractMultivariateSimpleBoundsOptimizer() {}
/*     */   
/*     */   protected BaseAbstractMultivariateSimpleBoundsOptimizer(ConvergenceChecker<PointValuePair> checker) {
/*  65 */     super(checker);
/*     */   }
/*     */   
/*     */   public double[] getLowerBound() {
/*  72 */     return (double[])this.lowerBound.clone();
/*     */   }
/*     */   
/*     */   public double[] getUpperBound() {
/*  79 */     return (double[])this.upperBound.clone();
/*     */   }
/*     */   
/*     */   public PointValuePair optimize(int maxEval, FUNC f, GoalType goalType, double[] startPoint) {
/*  85 */     return optimize(maxEval, f, goalType, startPoint, null, null);
/*     */   }
/*     */   
/*     */   public PointValuePair optimize(int maxEval, FUNC f, GoalType goalType, double[] startPoint, double[] lower, double[] upper) {
/*  93 */     int dim = startPoint.length;
/*  94 */     if (lower != null) {
/*  95 */       if (lower.length != dim)
/*  96 */         throw new DimensionMismatchException(lower.length, dim); 
/*  98 */       for (int i = 0; i < dim; i++) {
/*  99 */         double v = startPoint[i];
/* 100 */         double lo = lower[i];
/* 101 */         if (v < lo)
/* 102 */           throw new NumberIsTooSmallException(Double.valueOf(v), Double.valueOf(lo), true); 
/*     */       } 
/*     */     } 
/* 106 */     if (upper != null) {
/* 107 */       if (upper.length != dim)
/* 108 */         throw new DimensionMismatchException(upper.length, dim); 
/* 110 */       for (int i = 0; i < dim; i++) {
/* 111 */         double v = startPoint[i];
/* 112 */         double hi = upper[i];
/* 113 */         if (v > hi)
/* 114 */           throw new NumberIsTooLargeException(Double.valueOf(v), Double.valueOf(hi), true); 
/*     */       } 
/*     */     } 
/* 120 */     if (lower == null) {
/* 121 */       this.lowerBound = new double[dim];
/* 122 */       for (int i = 0; i < dim; i++)
/* 123 */         this.lowerBound[i] = Double.NEGATIVE_INFINITY; 
/*     */     } else {
/* 126 */       this.lowerBound = (double[])lower.clone();
/*     */     } 
/* 128 */     if (upper == null) {
/* 129 */       this.upperBound = new double[dim];
/* 130 */       for (int i = 0; i < dim; i++)
/* 131 */         this.upperBound[i] = Double.POSITIVE_INFINITY; 
/*     */     } else {
/* 134 */       this.upperBound = (double[])upper.clone();
/*     */     } 
/* 138 */     return super.optimize(maxEval, f, goalType, startPoint);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\BaseAbstractMultivariateSimpleBoundsOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
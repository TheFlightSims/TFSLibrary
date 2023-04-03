/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.MultivariateVectorFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.optimization.BaseMultivariateVectorOptimizer;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.PointVectorValuePair;
/*     */ import org.apache.commons.math3.optimization.SimpleVectorValueChecker;
/*     */ import org.apache.commons.math3.util.Incrementor;
/*     */ 
/*     */ public abstract class BaseAbstractMultivariateVectorOptimizer<FUNC extends MultivariateVectorFunction> implements BaseMultivariateVectorOptimizer<FUNC> {
/*  44 */   protected final Incrementor evaluations = new Incrementor();
/*     */   
/*     */   private ConvergenceChecker<PointVectorValuePair> checker;
/*     */   
/*     */   private double[] target;
/*     */   
/*     */   private double[] weight;
/*     */   
/*     */   private double[] start;
/*     */   
/*     */   private MultivariateVectorFunction function;
/*     */   
/*     */   protected BaseAbstractMultivariateVectorOptimizer() {
/*  62 */     this((ConvergenceChecker<PointVectorValuePair>)new SimpleVectorValueChecker());
/*     */   }
/*     */   
/*     */   protected BaseAbstractMultivariateVectorOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
/*  68 */     this.checker = checker;
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/*  73 */     return this.evaluations.getMaximalCount();
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/*  78 */     return this.evaluations.getCount();
/*     */   }
/*     */   
/*     */   public ConvergenceChecker<PointVectorValuePair> getConvergenceChecker() {
/*  83 */     return this.checker;
/*     */   }
/*     */   
/*     */   protected double[] computeObjectiveValue(double[] point) {
/*     */     try {
/*  96 */       this.evaluations.incrementCount();
/*  97 */     } catch (MaxCountExceededException e) {
/*  98 */       throw new TooManyEvaluationsException(e.getMax());
/*     */     } 
/* 100 */     return this.function.value(point);
/*     */   }
/*     */   
/*     */   public PointVectorValuePair optimize(int maxEval, FUNC f, double[] t, double[] w, double[] startPoint) {
/* 107 */     if (f == null)
/* 108 */       throw new NullArgumentException(); 
/* 110 */     if (t == null)
/* 111 */       throw new NullArgumentException(); 
/* 113 */     if (w == null)
/* 114 */       throw new NullArgumentException(); 
/* 116 */     if (startPoint == null)
/* 117 */       throw new NullArgumentException(); 
/* 119 */     if (t.length != w.length)
/* 120 */       throw new DimensionMismatchException(t.length, w.length); 
/* 124 */     this.evaluations.setMaximalCount(maxEval);
/* 125 */     this.evaluations.resetCount();
/* 128 */     this.function = (MultivariateVectorFunction)f;
/* 129 */     this.target = (double[])t.clone();
/* 130 */     this.weight = (double[])w.clone();
/* 131 */     this.start = (double[])startPoint.clone();
/* 134 */     return doOptimize();
/*     */   }
/*     */   
/*     */   public double[] getStartPoint() {
/* 141 */     return (double[])this.start.clone();
/*     */   }
/*     */   
/*     */   protected abstract PointVectorValuePair doOptimize();
/*     */   
/*     */   protected double[] getTargetRef() {
/* 156 */     return this.target;
/*     */   }
/*     */   
/*     */   protected double[] getWeightRef() {
/* 162 */     return this.weight;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\BaseAbstractMultivariateVectorOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
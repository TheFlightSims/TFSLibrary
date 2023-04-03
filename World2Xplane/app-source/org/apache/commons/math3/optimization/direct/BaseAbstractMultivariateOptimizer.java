/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.optimization.BaseMultivariateOptimizer;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ import org.apache.commons.math3.optimization.SimpleValueChecker;
/*     */ import org.apache.commons.math3.util.Incrementor;
/*     */ 
/*     */ public abstract class BaseAbstractMultivariateOptimizer<FUNC extends MultivariateFunction> implements BaseMultivariateOptimizer<FUNC> {
/*  44 */   protected final Incrementor evaluations = new Incrementor();
/*     */   
/*     */   private ConvergenceChecker<PointValuePair> checker;
/*     */   
/*     */   private GoalType goal;
/*     */   
/*     */   private double[] start;
/*     */   
/*     */   private MultivariateFunction function;
/*     */   
/*     */   protected BaseAbstractMultivariateOptimizer() {
/*  60 */     this((ConvergenceChecker<PointValuePair>)new SimpleValueChecker());
/*     */   }
/*     */   
/*     */   protected BaseAbstractMultivariateOptimizer(ConvergenceChecker<PointValuePair> checker) {
/*  66 */     this.checker = checker;
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/*  71 */     return this.evaluations.getMaximalCount();
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/*  76 */     return this.evaluations.getCount();
/*     */   }
/*     */   
/*     */   public ConvergenceChecker<PointValuePair> getConvergenceChecker() {
/*  81 */     return this.checker;
/*     */   }
/*     */   
/*     */   protected double computeObjectiveValue(double[] point) {
/*     */     try {
/*  94 */       this.evaluations.incrementCount();
/*  95 */     } catch (MaxCountExceededException e) {
/*  96 */       throw new TooManyEvaluationsException(e.getMax());
/*     */     } 
/*  98 */     return this.function.value(point);
/*     */   }
/*     */   
/*     */   public PointValuePair optimize(int maxEval, FUNC f, GoalType goalType, double[] startPoint) {
/* 105 */     if (f == null)
/* 106 */       throw new NullArgumentException(); 
/* 108 */     if (goalType == null)
/* 109 */       throw new NullArgumentException(); 
/* 111 */     if (startPoint == null)
/* 112 */       throw new NullArgumentException(); 
/* 116 */     this.evaluations.setMaximalCount(maxEval);
/* 117 */     this.evaluations.resetCount();
/* 120 */     this.function = (MultivariateFunction)f;
/* 121 */     this.goal = goalType;
/* 122 */     this.start = (double[])startPoint.clone();
/* 125 */     return doOptimize();
/*     */   }
/*     */   
/*     */   public GoalType getGoalType() {
/* 132 */     return this.goal;
/*     */   }
/*     */   
/*     */   public double[] getStartPoint() {
/* 139 */     return (double[])this.start.clone();
/*     */   }
/*     */   
/*     */   protected abstract PointValuePair doOptimize();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\BaseAbstractMultivariateOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
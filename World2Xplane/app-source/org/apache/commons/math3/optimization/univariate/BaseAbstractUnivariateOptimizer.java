/*     */ package org.apache.commons.math3.optimization.univariate;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.util.Incrementor;
/*     */ 
/*     */ public abstract class BaseAbstractUnivariateOptimizer implements UnivariateOptimizer {
/*     */   private final ConvergenceChecker<UnivariatePointValuePair> checker;
/*     */   
/*  40 */   private final Incrementor evaluations = new Incrementor();
/*     */   
/*     */   private GoalType goal;
/*     */   
/*     */   private double searchMin;
/*     */   
/*     */   private double searchMax;
/*     */   
/*     */   private double searchStart;
/*     */   
/*     */   private UnivariateFunction function;
/*     */   
/*     */   protected BaseAbstractUnivariateOptimizer(ConvergenceChecker<UnivariatePointValuePair> checker) {
/*  56 */     this.checker = checker;
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/*  61 */     return this.evaluations.getMaximalCount();
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/*  66 */     return this.evaluations.getCount();
/*     */   }
/*     */   
/*     */   public GoalType getGoalType() {
/*  73 */     return this.goal;
/*     */   }
/*     */   
/*     */   public double getMin() {
/*  79 */     return this.searchMin;
/*     */   }
/*     */   
/*     */   public double getMax() {
/*  85 */     return this.searchMax;
/*     */   }
/*     */   
/*     */   public double getStartValue() {
/*  91 */     return this.searchStart;
/*     */   }
/*     */   
/*     */   protected double computeObjectiveValue(double point) {
/*     */     try {
/* 104 */       this.evaluations.incrementCount();
/* 105 */     } catch (MaxCountExceededException e) {
/* 106 */       throw new TooManyEvaluationsException(e.getMax());
/*     */     } 
/* 108 */     return this.function.value(point);
/*     */   }
/*     */   
/*     */   public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goalType, double min, double max, double startValue) {
/* 117 */     if (f == null)
/* 118 */       throw new NullArgumentException(); 
/* 120 */     if (goalType == null)
/* 121 */       throw new NullArgumentException(); 
/* 125 */     this.searchMin = min;
/* 126 */     this.searchMax = max;
/* 127 */     this.searchStart = startValue;
/* 128 */     this.goal = goalType;
/* 129 */     this.function = f;
/* 130 */     this.evaluations.setMaximalCount(maxEval);
/* 131 */     this.evaluations.resetCount();
/* 134 */     return doOptimize();
/*     */   }
/*     */   
/*     */   public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goalType, double min, double max) {
/* 142 */     return optimize(maxEval, f, goalType, min, max, min + 0.5D * (max - min));
/*     */   }
/*     */   
/*     */   public ConvergenceChecker<UnivariatePointValuePair> getConvergenceChecker() {
/* 149 */     return this.checker;
/*     */   }
/*     */   
/*     */   protected abstract UnivariatePointValuePair doOptimize();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimizatio\\univariate\BaseAbstractUnivariateOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
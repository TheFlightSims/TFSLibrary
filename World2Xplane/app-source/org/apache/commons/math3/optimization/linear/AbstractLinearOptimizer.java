/*     */ package org.apache.commons.math3.optimization.linear;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ 
/*     */ public abstract class AbstractLinearOptimizer implements LinearOptimizer {
/*     */   public static final int DEFAULT_MAX_ITERATIONS = 100;
/*     */   
/*     */   private LinearObjectiveFunction function;
/*     */   
/*     */   private Collection<LinearConstraint> linearConstraints;
/*     */   
/*     */   private GoalType goal;
/*     */   
/*     */   private boolean nonNegative;
/*     */   
/*     */   private int maxIterations;
/*     */   
/*     */   private int iterations;
/*     */   
/*     */   protected AbstractLinearOptimizer() {
/*  75 */     setMaxIterations(100);
/*     */   }
/*     */   
/*     */   protected boolean restrictToNonNegative() {
/*  82 */     return this.nonNegative;
/*     */   }
/*     */   
/*     */   protected GoalType getGoalType() {
/*  89 */     return this.goal;
/*     */   }
/*     */   
/*     */   protected LinearObjectiveFunction getFunction() {
/*  96 */     return this.function;
/*     */   }
/*     */   
/*     */   protected Collection<LinearConstraint> getConstraints() {
/* 103 */     return Collections.unmodifiableCollection(this.linearConstraints);
/*     */   }
/*     */   
/*     */   public void setMaxIterations(int maxIterations) {
/* 108 */     this.maxIterations = maxIterations;
/*     */   }
/*     */   
/*     */   public int getMaxIterations() {
/* 113 */     return this.maxIterations;
/*     */   }
/*     */   
/*     */   public int getIterations() {
/* 118 */     return this.iterations;
/*     */   }
/*     */   
/*     */   protected void incrementIterationsCounter() throws MaxCountExceededException {
/* 127 */     if (++this.iterations > this.maxIterations)
/* 128 */       throw new MaxCountExceededException(Integer.valueOf(this.maxIterations)); 
/*     */   }
/*     */   
/*     */   public PointValuePair optimize(LinearObjectiveFunction f, Collection<LinearConstraint> constraints, GoalType goalType, boolean restrictToNonNegative) throws MathIllegalStateException {
/* 139 */     this.function = f;
/* 140 */     this.linearConstraints = constraints;
/* 141 */     this.goal = goalType;
/* 142 */     this.nonNegative = restrictToNonNegative;
/* 144 */     this.iterations = 0;
/* 147 */     return doOptimize();
/*     */   }
/*     */   
/*     */   protected abstract PointValuePair doOptimize() throws MathIllegalStateException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\linear\AbstractLinearOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
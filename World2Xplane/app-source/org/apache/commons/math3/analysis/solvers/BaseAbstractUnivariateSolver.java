/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NoBracketingException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.util.Incrementor;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public abstract class BaseAbstractUnivariateSolver<FUNC extends UnivariateFunction> implements BaseUnivariateSolver<FUNC> {
/*     */   private static final double DEFAULT_RELATIVE_ACCURACY = 1.0E-14D;
/*     */   
/*     */   private static final double DEFAULT_FUNCTION_VALUE_ACCURACY = 1.0E-15D;
/*     */   
/*     */   private final double functionValueAccuracy;
/*     */   
/*     */   private final double absoluteAccuracy;
/*     */   
/*     */   private final double relativeAccuracy;
/*     */   
/*  49 */   private final Incrementor evaluations = new Incrementor();
/*     */   
/*     */   private double searchMin;
/*     */   
/*     */   private double searchMax;
/*     */   
/*     */   private double searchStart;
/*     */   
/*     */   private FUNC function;
/*     */   
/*     */   protected BaseAbstractUnivariateSolver(double absoluteAccuracy) {
/*  65 */     this(1.0E-14D, absoluteAccuracy, 1.0E-15D);
/*     */   }
/*     */   
/*     */   protected BaseAbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy) {
/*  78 */     this(relativeAccuracy, absoluteAccuracy, 1.0E-15D);
/*     */   }
/*     */   
/*     */   protected BaseAbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/*  93 */     this.absoluteAccuracy = absoluteAccuracy;
/*  94 */     this.relativeAccuracy = relativeAccuracy;
/*  95 */     this.functionValueAccuracy = functionValueAccuracy;
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/* 100 */     return this.evaluations.getMaximalCount();
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/* 104 */     return this.evaluations.getCount();
/*     */   }
/*     */   
/*     */   public double getMin() {
/* 110 */     return this.searchMin;
/*     */   }
/*     */   
/*     */   public double getMax() {
/* 116 */     return this.searchMax;
/*     */   }
/*     */   
/*     */   public double getStartValue() {
/* 122 */     return this.searchStart;
/*     */   }
/*     */   
/*     */   public double getAbsoluteAccuracy() {
/* 128 */     return this.absoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getRelativeAccuracy() {
/* 134 */     return this.relativeAccuracy;
/*     */   }
/*     */   
/*     */   public double getFunctionValueAccuracy() {
/* 140 */     return this.functionValueAccuracy;
/*     */   }
/*     */   
/*     */   protected double computeObjectiveValue(double point) throws TooManyEvaluationsException {
/* 153 */     incrementEvaluationCount();
/* 154 */     return this.function.value(point);
/*     */   }
/*     */   
/*     */   protected void setup(int maxEval, FUNC f, double min, double max, double startValue) {
/* 173 */     MathUtils.checkNotNull(f);
/* 176 */     this.searchMin = min;
/* 177 */     this.searchMax = max;
/* 178 */     this.searchStart = startValue;
/* 179 */     this.function = f;
/* 180 */     this.evaluations.setMaximalCount(maxEval);
/* 181 */     this.evaluations.resetCount();
/*     */   }
/*     */   
/*     */   public double solve(int maxEval, FUNC f, double min, double max, double startValue) {
/* 187 */     setup(maxEval, f, min, max, startValue);
/* 190 */     return doSolve();
/*     */   }
/*     */   
/*     */   public double solve(int maxEval, FUNC f, double min, double max) {
/* 195 */     return solve(maxEval, f, min, max, min + 0.5D * (max - min));
/*     */   }
/*     */   
/*     */   public double solve(int maxEval, FUNC f, double startValue) {
/* 200 */     return solve(maxEval, f, Double.NaN, Double.NaN, startValue);
/*     */   }
/*     */   
/*     */   protected abstract double doSolve() throws TooManyEvaluationsException, NoBracketingException;
/*     */   
/*     */   protected boolean isBracketing(double lower, double upper) {
/* 226 */     return UnivariateSolverUtils.isBracketing((UnivariateFunction)this.function, lower, upper);
/*     */   }
/*     */   
/*     */   protected boolean isSequence(double start, double mid, double end) {
/* 240 */     return UnivariateSolverUtils.isSequence(start, mid, end);
/*     */   }
/*     */   
/*     */   protected void verifyInterval(double lower, double upper) {
/* 253 */     UnivariateSolverUtils.verifyInterval(lower, upper);
/*     */   }
/*     */   
/*     */   protected void verifySequence(double lower, double initial, double upper) {
/* 268 */     UnivariateSolverUtils.verifySequence(lower, initial, upper);
/*     */   }
/*     */   
/*     */   protected void verifyBracketing(double lower, double upper) {
/* 282 */     UnivariateSolverUtils.verifyBracketing((UnivariateFunction)this.function, lower, upper);
/*     */   }
/*     */   
/*     */   protected void incrementEvaluationCount() {
/*     */     try {
/* 294 */       this.evaluations.incrementCount();
/* 295 */     } catch (MaxCountExceededException e) {
/* 296 */       throw new TooManyEvaluationsException(e.getMax());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\BaseAbstractUnivariateSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
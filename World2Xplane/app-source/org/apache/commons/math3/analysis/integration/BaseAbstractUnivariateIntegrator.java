/*     */ package org.apache.commons.math3.analysis.integration;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*     */ import org.apache.commons.math3.util.Incrementor;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public abstract class BaseAbstractUnivariateIntegrator implements UnivariateIntegrator {
/*     */   public static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-15D;
/*     */   
/*     */   public static final double DEFAULT_RELATIVE_ACCURACY = 1.0E-6D;
/*     */   
/*     */   public static final int DEFAULT_MIN_ITERATIONS_COUNT = 3;
/*     */   
/*     */   public static final int DEFAULT_MAX_ITERATIONS_COUNT = 2147483647;
/*     */   
/*     */   protected final Incrementor iterations;
/*     */   
/*     */   private final double absoluteAccuracy;
/*     */   
/*     */   private final double relativeAccuracy;
/*     */   
/*     */   private final int minimalIterationCount;
/*     */   
/*     */   private final Incrementor evaluations;
/*     */   
/*     */   private UnivariateFunction function;
/*     */   
/*     */   private double min;
/*     */   
/*     */   private double max;
/*     */   
/*     */   protected BaseAbstractUnivariateIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException {
/* 116 */     this.relativeAccuracy = relativeAccuracy;
/* 117 */     this.absoluteAccuracy = absoluteAccuracy;
/* 120 */     if (minimalIterationCount <= 0)
/* 121 */       throw new NotStrictlyPositiveException(Integer.valueOf(minimalIterationCount)); 
/* 123 */     if (maximalIterationCount <= minimalIterationCount)
/* 124 */       throw new NumberIsTooSmallException(Integer.valueOf(maximalIterationCount), Integer.valueOf(minimalIterationCount), false); 
/* 126 */     this.minimalIterationCount = minimalIterationCount;
/* 127 */     this.iterations = new Incrementor();
/* 128 */     this.iterations.setMaximalCount(maximalIterationCount);
/* 131 */     this.evaluations = new Incrementor();
/*     */   }
/*     */   
/*     */   protected BaseAbstractUnivariateIntegrator(double relativeAccuracy, double absoluteAccuracy) {
/* 142 */     this(relativeAccuracy, absoluteAccuracy, 3, 2147483647);
/*     */   }
/*     */   
/*     */   protected BaseAbstractUnivariateIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException {
/* 158 */     this(1.0E-6D, 1.0E-15D, minimalIterationCount, maximalIterationCount);
/*     */   }
/*     */   
/*     */   public double getRelativeAccuracy() {
/* 164 */     return this.relativeAccuracy;
/*     */   }
/*     */   
/*     */   public double getAbsoluteAccuracy() {
/* 169 */     return this.absoluteAccuracy;
/*     */   }
/*     */   
/*     */   public int getMinimalIterationCount() {
/* 174 */     return this.minimalIterationCount;
/*     */   }
/*     */   
/*     */   public int getMaximalIterationCount() {
/* 179 */     return this.iterations.getMaximalCount();
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/* 184 */     return this.evaluations.getCount();
/*     */   }
/*     */   
/*     */   public int getIterations() {
/* 189 */     return this.iterations.getCount();
/*     */   }
/*     */   
/*     */   protected double getMin() {
/* 196 */     return this.min;
/*     */   }
/*     */   
/*     */   protected double getMax() {
/* 202 */     return this.max;
/*     */   }
/*     */   
/*     */   protected double computeObjectiveValue(double point) throws TooManyEvaluationsException {
/*     */     try {
/* 216 */       this.evaluations.incrementCount();
/* 217 */     } catch (MaxCountExceededException e) {
/* 218 */       throw new TooManyEvaluationsException(e.getMax());
/*     */     } 
/* 220 */     return this.function.value(point);
/*     */   }
/*     */   
/*     */   protected void setup(int maxEval, UnivariateFunction f, double lower, double upper) throws NullArgumentException, MathIllegalArgumentException {
/* 241 */     MathUtils.checkNotNull(f);
/* 242 */     UnivariateSolverUtils.verifyInterval(lower, upper);
/* 245 */     this.min = lower;
/* 246 */     this.max = upper;
/* 247 */     this.function = f;
/* 248 */     this.evaluations.setMaximalCount(maxEval);
/* 249 */     this.evaluations.resetCount();
/* 250 */     this.iterations.resetCount();
/*     */   }
/*     */   
/*     */   public double integrate(int maxEval, UnivariateFunction f, double lower, double upper) throws TooManyEvaluationsException, MaxCountExceededException, MathIllegalArgumentException, NullArgumentException {
/* 261 */     setup(maxEval, f, lower, upper);
/* 264 */     return doIntegrate();
/*     */   }
/*     */   
/*     */   protected abstract double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\integration\BaseAbstractUnivariateIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
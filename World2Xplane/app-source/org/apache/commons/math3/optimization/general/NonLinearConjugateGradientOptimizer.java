/*     */ package org.apache.commons.math3.optimization.general;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.solvers.BrentSolver;
/*     */ import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ import org.apache.commons.math3.optimization.SimpleValueChecker;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class NonLinearConjugateGradientOptimizer extends AbstractScalarDifferentiableOptimizer {
/*     */   private final ConjugateGradientFormula updateFormula;
/*     */   
/*     */   private final Preconditioner preconditioner;
/*     */   
/*     */   private final UnivariateSolver solver;
/*     */   
/*     */   private double initialStep;
/*     */   
/*     */   private double[] point;
/*     */   
/*     */   public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula) {
/*  66 */     this(updateFormula, (ConvergenceChecker<PointValuePair>)new SimpleValueChecker());
/*     */   }
/*     */   
/*     */   public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker) {
/*  81 */     this(updateFormula, checker, (UnivariateSolver)new BrentSolver(), new IdentityPreconditioner());
/*     */   }
/*     */   
/*     */   public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver) {
/* 100 */     this(updateFormula, checker, lineSearchSolver, new IdentityPreconditioner());
/*     */   }
/*     */   
/*     */   public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver, Preconditioner preconditioner) {
/* 118 */     super(checker);
/* 120 */     this.updateFormula = updateFormula;
/* 121 */     this.solver = lineSearchSolver;
/* 122 */     this.preconditioner = preconditioner;
/* 123 */     this.initialStep = 1.0D;
/*     */   }
/*     */   
/*     */   public void setInitialStep(double initialStep) {
/* 137 */     if (initialStep <= 0.0D) {
/* 138 */       this.initialStep = 1.0D;
/*     */     } else {
/* 140 */       this.initialStep = initialStep;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected PointValuePair doOptimize() {
/* 147 */     ConvergenceChecker<PointValuePair> checker = getConvergenceChecker();
/* 148 */     this.point = getStartPoint();
/* 149 */     GoalType goal = getGoalType();
/* 150 */     int n = this.point.length;
/* 151 */     double[] r = computeObjectiveGradient(this.point);
/* 152 */     if (goal == GoalType.MINIMIZE)
/* 153 */       for (int j = 0; j < n; j++)
/* 154 */         r[j] = -r[j];  
/* 159 */     double[] steepestDescent = this.preconditioner.precondition(this.point, r);
/* 160 */     double[] searchDirection = (double[])steepestDescent.clone();
/* 162 */     double delta = 0.0D;
/* 163 */     for (int i = 0; i < n; i++)
/* 164 */       delta += r[i] * searchDirection[i]; 
/* 167 */     PointValuePair current = null;
/* 168 */     int iter = 0;
/* 169 */     int maxEval = getMaxEvaluations();
/*     */     while (true) {
/*     */       double beta;
/* 171 */       iter++;
/* 173 */       double objective = computeObjectiveValue(this.point);
/* 174 */       PointValuePair previous = current;
/* 175 */       current = new PointValuePair(this.point, objective);
/* 176 */       if (previous != null && 
/* 177 */         checker.converged(iter, previous, current))
/* 179 */         return current; 
/* 184 */       UnivariateFunction lsf = new LineSearchFunction(searchDirection);
/* 185 */       double uB = findUpperBound(lsf, 0.0D, this.initialStep);
/* 189 */       double step = this.solver.solve(maxEval, lsf, 0.0D, uB, 1.0E-15D);
/* 190 */       maxEval -= this.solver.getEvaluations();
/*     */       int j;
/* 193 */       for (j = 0; j < this.point.length; j++)
/* 194 */         this.point[j] = this.point[j] + step * searchDirection[j]; 
/* 197 */       r = computeObjectiveGradient(this.point);
/* 198 */       if (goal == GoalType.MINIMIZE)
/* 199 */         for (j = 0; j < n; j++)
/* 200 */           r[j] = -r[j];  
/* 205 */       double deltaOld = delta;
/* 206 */       double[] newSteepestDescent = this.preconditioner.precondition(this.point, r);
/* 207 */       delta = 0.0D;
/* 208 */       for (int k = 0; k < n; k++)
/* 209 */         delta += r[k] * newSteepestDescent[k]; 
/* 213 */       if (this.updateFormula == ConjugateGradientFormula.FLETCHER_REEVES) {
/* 214 */         beta = delta / deltaOld;
/*     */       } else {
/* 216 */         double deltaMid = 0.0D;
/* 217 */         for (int i1 = 0; i1 < r.length; i1++)
/* 218 */           deltaMid += r[i1] * steepestDescent[i1]; 
/* 220 */         beta = (delta - deltaMid) / deltaOld;
/*     */       } 
/* 222 */       steepestDescent = newSteepestDescent;
/* 225 */       if (iter % n == 0 || beta < 0.0D) {
/* 228 */         searchDirection = (double[])steepestDescent.clone();
/*     */         continue;
/*     */       } 
/* 231 */       for (int m = 0; m < n; m++)
/* 232 */         searchDirection[m] = steepestDescent[m] + beta * searchDirection[m]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private double findUpperBound(UnivariateFunction f, double a, double h) {
/* 249 */     double yA = f.value(a);
/* 250 */     double yB = yA;
/*     */     double step;
/* 251 */     for (step = h; step < Double.MAX_VALUE; step *= FastMath.max(2.0D, yA / yB)) {
/* 252 */       double b = a + step;
/* 253 */       yB = f.value(b);
/* 254 */       if (yA * yB <= 0.0D)
/* 255 */         return b; 
/*     */     } 
/* 258 */     throw new MathIllegalStateException(LocalizedFormats.UNABLE_TO_BRACKET_OPTIMUM_IN_LINE_SEARCH, new Object[0]);
/*     */   }
/*     */   
/*     */   public static class IdentityPreconditioner implements Preconditioner {
/*     */     public double[] precondition(double[] variables, double[] r) {
/* 266 */       return (double[])r.clone();
/*     */     }
/*     */   }
/*     */   
/*     */   private class LineSearchFunction implements UnivariateFunction {
/*     */     private final double[] searchDirection;
/*     */     
/*     */     public LineSearchFunction(double[] searchDirection) {
/* 287 */       this.searchDirection = searchDirection;
/*     */     }
/*     */     
/*     */     public double value(double x) {
/* 293 */       double[] shiftedPoint = (double[])NonLinearConjugateGradientOptimizer.this.point.clone();
/* 294 */       for (int i = 0; i < shiftedPoint.length; i++)
/* 295 */         shiftedPoint[i] = shiftedPoint[i] + x * this.searchDirection[i]; 
/* 299 */       double[] gradient = NonLinearConjugateGradientOptimizer.this.computeObjectiveGradient(shiftedPoint);
/* 302 */       double dotProduct = 0.0D;
/* 303 */       for (int j = 0; j < gradient.length; j++)
/* 304 */         dotProduct += gradient[j] * this.searchDirection[j]; 
/* 307 */       return dotProduct;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\general\NonLinearConjugateGradientOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
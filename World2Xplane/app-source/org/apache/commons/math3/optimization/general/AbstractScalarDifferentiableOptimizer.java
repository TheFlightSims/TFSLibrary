/*    */ package org.apache.commons.math3.optimization.general;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableMultivariateFunction;
/*    */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*    */ import org.apache.commons.math3.analysis.MultivariateVectorFunction;
/*    */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*    */ import org.apache.commons.math3.optimization.DifferentiableMultivariateOptimizer;
/*    */ import org.apache.commons.math3.optimization.GoalType;
/*    */ import org.apache.commons.math3.optimization.PointValuePair;
/*    */ import org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer;
/*    */ 
/*    */ public abstract class AbstractScalarDifferentiableOptimizer extends BaseAbstractMultivariateOptimizer<DifferentiableMultivariateFunction> implements DifferentiableMultivariateOptimizer {
/*    */   private MultivariateVectorFunction gradient;
/*    */   
/*    */   protected AbstractScalarDifferentiableOptimizer() {}
/*    */   
/*    */   protected AbstractScalarDifferentiableOptimizer(ConvergenceChecker<PointValuePair> checker) {
/* 55 */     super(checker);
/*    */   }
/*    */   
/*    */   protected double[] computeObjectiveGradient(double[] evaluationPoint) {
/* 67 */     return this.gradient.value(evaluationPoint);
/*    */   }
/*    */   
/*    */   public PointValuePair optimize(int maxEval, DifferentiableMultivariateFunction f, GoalType goalType, double[] startPoint) {
/* 77 */     this.gradient = f.gradient();
/* 79 */     return super.optimize(maxEval, (MultivariateFunction)f, goalType, startPoint);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\general\AbstractScalarDifferentiableOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
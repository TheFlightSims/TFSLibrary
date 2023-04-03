/*    */ package org.apache.commons.math3.optimization;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.DifferentiableMultivariateFunction;
/*    */ import org.apache.commons.math3.random.RandomVectorGenerator;
/*    */ 
/*    */ public class DifferentiableMultivariateMultiStartOptimizer extends BaseMultivariateMultiStartOptimizer<DifferentiableMultivariateFunction> implements DifferentiableMultivariateOptimizer {
/*    */   public DifferentiableMultivariateMultiStartOptimizer(DifferentiableMultivariateOptimizer optimizer, int starts, RandomVectorGenerator generator) {
/* 49 */     super(optimizer, starts, generator);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\DifferentiableMultivariateMultiStartOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
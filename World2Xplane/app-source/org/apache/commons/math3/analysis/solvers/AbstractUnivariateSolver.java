/*    */ package org.apache.commons.math3.analysis.solvers;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ 
/*    */ public abstract class AbstractUnivariateSolver extends BaseAbstractUnivariateSolver<UnivariateFunction> implements UnivariateSolver {
/*    */   protected AbstractUnivariateSolver(double absoluteAccuracy) {
/* 37 */     super(absoluteAccuracy);
/*    */   }
/*    */   
/*    */   protected AbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy) {
/* 47 */     super(relativeAccuracy, absoluteAccuracy);
/*    */   }
/*    */   
/*    */   protected AbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/* 59 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\AbstractUnivariateSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
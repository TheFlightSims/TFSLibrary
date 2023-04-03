/*    */ package org.apache.commons.math3.analysis.solvers;
/*    */ 
/*    */ public class IllinoisSolver extends BaseSecantSolver {
/*    */   public IllinoisSolver() {
/* 48 */     super(1.0E-6D, BaseSecantSolver.Method.ILLINOIS);
/*    */   }
/*    */   
/*    */   public IllinoisSolver(double absoluteAccuracy) {
/* 57 */     super(absoluteAccuracy, BaseSecantSolver.Method.ILLINOIS);
/*    */   }
/*    */   
/*    */   public IllinoisSolver(double relativeAccuracy, double absoluteAccuracy) {
/* 68 */     super(relativeAccuracy, absoluteAccuracy, BaseSecantSolver.Method.ILLINOIS);
/*    */   }
/*    */   
/*    */   public IllinoisSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/* 81 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy, BaseSecantSolver.Method.PEGASUS);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\IllinoisSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
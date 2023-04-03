/*    */ package org.apache.commons.math3.analysis.solvers;
/*    */ 
/*    */ public class PegasusSolver extends BaseSecantSolver {
/*    */   public PegasusSolver() {
/* 50 */     super(1.0E-6D, BaseSecantSolver.Method.PEGASUS);
/*    */   }
/*    */   
/*    */   public PegasusSolver(double absoluteAccuracy) {
/* 59 */     super(absoluteAccuracy, BaseSecantSolver.Method.PEGASUS);
/*    */   }
/*    */   
/*    */   public PegasusSolver(double relativeAccuracy, double absoluteAccuracy) {
/* 70 */     super(relativeAccuracy, absoluteAccuracy, BaseSecantSolver.Method.PEGASUS);
/*    */   }
/*    */   
/*    */   public PegasusSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/* 83 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy, BaseSecantSolver.Method.PEGASUS);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\PegasusSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
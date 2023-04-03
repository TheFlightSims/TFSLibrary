/*    */ package org.apache.commons.math3.analysis.solvers;
/*    */ 
/*    */ public class RegulaFalsiSolver extends BaseSecantSolver {
/*    */   public RegulaFalsiSolver() {
/* 60 */     super(1.0E-6D, BaseSecantSolver.Method.REGULA_FALSI);
/*    */   }
/*    */   
/*    */   public RegulaFalsiSolver(double absoluteAccuracy) {
/* 69 */     super(absoluteAccuracy, BaseSecantSolver.Method.REGULA_FALSI);
/*    */   }
/*    */   
/*    */   public RegulaFalsiSolver(double relativeAccuracy, double absoluteAccuracy) {
/* 80 */     super(relativeAccuracy, absoluteAccuracy, BaseSecantSolver.Method.REGULA_FALSI);
/*    */   }
/*    */   
/*    */   public RegulaFalsiSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
/* 93 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy, BaseSecantSolver.Method.REGULA_FALSI);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\RegulaFalsiSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
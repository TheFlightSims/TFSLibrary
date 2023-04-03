/*    */ package org.apache.commons.math3.ode.nonstiff;
/*    */ 
/*    */ public class EulerIntegrator extends RungeKuttaIntegrator {
/* 52 */   private static final double[] STATIC_C = new double[0];
/*    */   
/* 56 */   private static final double[][] STATIC_A = new double[0][];
/*    */   
/* 60 */   private static final double[] STATIC_B = new double[] { 1.0D };
/*    */   
/*    */   public EulerIntegrator(double step) {
/* 69 */     super("Euler", STATIC_C, STATIC_A, STATIC_B, new EulerStepInterpolator(), step);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\EulerIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.math3.ode.nonstiff;
/*    */ 
/*    */ public class ClassicalRungeKuttaIntegrator extends RungeKuttaIntegrator {
/* 49 */   private static final double[] STATIC_C = new double[] { 0.5D, 0.5D, 1.0D };
/*    */   
/* 54 */   private static final double[][] STATIC_A = new double[][] { { 0.5D }, { 0.0D, 0.5D }, { 0.0D, 0.0D, 1.0D } };
/*    */   
/* 61 */   private static final double[] STATIC_B = new double[] { 0.16666666666666666D, 0.3333333333333333D, 0.3333333333333333D, 0.16666666666666666D };
/*    */   
/*    */   public ClassicalRungeKuttaIntegrator(double step) {
/* 71 */     super("classical Runge-Kutta", STATIC_C, STATIC_A, STATIC_B, new ClassicalRungeKuttaStepInterpolator(), step);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\ClassicalRungeKuttaIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
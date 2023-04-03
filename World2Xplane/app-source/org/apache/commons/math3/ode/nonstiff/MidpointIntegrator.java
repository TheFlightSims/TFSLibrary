/*    */ package org.apache.commons.math3.ode.nonstiff;
/*    */ 
/*    */ public class MidpointIntegrator extends RungeKuttaIntegrator {
/* 46 */   private static final double[] STATIC_C = new double[] { 0.5D };
/*    */   
/* 51 */   private static final double[][] STATIC_A = new double[][] { { 0.5D } };
/*    */   
/* 56 */   private static final double[] STATIC_B = new double[] { 0.0D, 1.0D };
/*    */   
/*    */   public MidpointIntegrator(double step) {
/* 65 */     super("midpoint", STATIC_C, STATIC_A, STATIC_B, new MidpointStepInterpolator(), step);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\MidpointIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.math3.ode.nonstiff;
/*    */ 
/*    */ public class ThreeEighthesIntegrator extends RungeKuttaIntegrator {
/* 48 */   private static final double[] STATIC_C = new double[] { 0.3333333333333333D, 0.6666666666666666D, 1.0D };
/*    */   
/* 53 */   private static final double[][] STATIC_A = new double[][] { { 0.3333333333333333D }, { -0.3333333333333333D, 1.0D }, { 1.0D, -1.0D, 1.0D } };
/*    */   
/* 60 */   private static final double[] STATIC_B = new double[] { 0.125D, 0.375D, 0.375D, 0.125D };
/*    */   
/*    */   public ThreeEighthesIntegrator(double step) {
/* 69 */     super("3/8", STATIC_C, STATIC_A, STATIC_B, new ThreeEighthesStepInterpolator(), step);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\ThreeEighthesIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
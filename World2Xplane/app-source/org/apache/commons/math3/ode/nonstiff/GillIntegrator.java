/*    */ package org.apache.commons.math3.ode.nonstiff;
/*    */ 
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class GillIntegrator extends RungeKuttaIntegrator {
/* 50 */   private static final double[] STATIC_C = new double[] { 0.5D, 0.5D, 1.0D };
/*    */   
/* 55 */   private static final double[][] STATIC_A = new double[][] { { 0.5D }, { (FastMath.sqrt(2.0D) - 1.0D) / 2.0D, (2.0D - FastMath.sqrt(2.0D)) / 2.0D }, { 0.0D, -FastMath.sqrt(2.0D) / 2.0D, (2.0D + FastMath.sqrt(2.0D)) / 2.0D } };
/*    */   
/* 62 */   private static final double[] STATIC_B = new double[] { 0.16666666666666666D, (2.0D - FastMath.sqrt(2.0D)) / 6.0D, (2.0D + FastMath.sqrt(2.0D)) / 6.0D, 0.16666666666666666D };
/*    */   
/*    */   public GillIntegrator(double step) {
/* 71 */     super("Gill", STATIC_C, STATIC_A, STATIC_B, new GillStepInterpolator(), step);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\GillIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
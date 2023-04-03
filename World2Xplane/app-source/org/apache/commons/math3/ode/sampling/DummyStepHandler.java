/*    */ package org.apache.commons.math3.ode.sampling;
/*    */ 
/*    */ public class DummyStepHandler implements StepHandler {
/*    */   private DummyStepHandler() {}
/*    */   
/*    */   public static DummyStepHandler getInstance() {
/* 50 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */   
/*    */   public void init(double t0, double[] y0, double t) {}
/*    */   
/*    */   public void handleStep(StepInterpolator interpolator, boolean isLast) {}
/*    */   
/*    */   private static class LazyHolder {
/* 78 */     private static final DummyStepHandler INSTANCE = new DummyStepHandler();
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 87 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\sampling\DummyStepHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
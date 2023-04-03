/*    */ package org.apache.commons.math3.ode.events;
/*    */ 
/*    */ public interface EventHandler {
/*    */   void init(double paramDouble1, double[] paramArrayOfdouble, double paramDouble2);
/*    */   
/*    */   double g(double paramDouble, double[] paramArrayOfdouble);
/*    */   
/*    */   Action eventOccurred(double paramDouble, double[] paramArrayOfdouble, boolean paramBoolean);
/*    */   
/*    */   void resetState(double paramDouble, double[] paramArrayOfdouble);
/*    */   
/*    */   public enum Action {
/* 60 */     STOP, RESET_STATE, RESET_DERIVATIVES, CONTINUE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\events\EventHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
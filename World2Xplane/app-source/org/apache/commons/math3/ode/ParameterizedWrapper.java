/*    */ package org.apache.commons.math3.ode;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ class ParameterizedWrapper implements ParameterizedODE {
/*    */   private final FirstOrderDifferentialEquations fode;
/*    */   
/*    */   public ParameterizedWrapper(FirstOrderDifferentialEquations ode) {
/* 40 */     this.fode = ode;
/*    */   }
/*    */   
/*    */   public int getDimension() {
/* 47 */     return this.fode.getDimension();
/*    */   }
/*    */   
/*    */   public void computeDerivatives(double t, double[] y, double[] yDot) {
/* 56 */     this.fode.computeDerivatives(t, y, yDot);
/*    */   }
/*    */   
/*    */   public Collection<String> getParametersNames() {
/* 61 */     return new ArrayList<String>();
/*    */   }
/*    */   
/*    */   public boolean isSupported(String name) {
/* 66 */     return false;
/*    */   }
/*    */   
/*    */   public double getParameter(String name) throws MathIllegalArgumentException {
/* 72 */     if (!isSupported(name))
/* 73 */       throw new MathIllegalArgumentException(LocalizedFormats.UNKNOWN_PARAMETER, new Object[] { name }); 
/* 75 */     return Double.NaN;
/*    */   }
/*    */   
/*    */   public void setParameter(String name, double value) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\ParameterizedWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
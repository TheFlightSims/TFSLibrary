/*    */ package org.apache.commons.math3.ode;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ class ParameterJacobianWrapper implements ParameterJacobianProvider {
/*    */   private final FirstOrderDifferentialEquations fode;
/*    */   
/*    */   private final ParameterizedODE pode;
/*    */   
/*    */   private final Map<String, Double> hParam;
/*    */   
/*    */   public ParameterJacobianWrapper(FirstOrderDifferentialEquations fode, ParameterizedODE pode, ParameterConfiguration[] paramsAndSteps) {
/* 49 */     this.fode = fode;
/* 50 */     this.pode = pode;
/* 51 */     this.hParam = new HashMap<String, Double>();
/* 54 */     for (ParameterConfiguration param : paramsAndSteps) {
/* 55 */       String name = param.getParameterName();
/* 56 */       if (pode.isSupported(name))
/* 57 */         this.hParam.put(name, Double.valueOf(param.getHP())); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Collection<String> getParametersNames() {
/* 64 */     return this.pode.getParametersNames();
/*    */   }
/*    */   
/*    */   public boolean isSupported(String name) {
/* 69 */     return this.pode.isSupported(name);
/*    */   }
/*    */   
/*    */   public void computeParameterJacobian(double t, double[] y, double[] yDot, String paramName, double[] dFdP) {
/* 76 */     int n = this.fode.getDimension();
/* 77 */     double[] tmpDot = new double[n];
/* 80 */     double p = this.pode.getParameter(paramName);
/* 81 */     double hP = ((Double)this.hParam.get(paramName)).doubleValue();
/* 82 */     this.pode.setParameter(paramName, p + hP);
/* 83 */     this.fode.computeDerivatives(t, y, tmpDot);
/* 84 */     for (int i = 0; i < n; i++)
/* 85 */       dFdP[i] = (tmpDot[i] - yDot[i]) / hP; 
/* 87 */     this.pode.setParameter(paramName, p);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\ParameterJacobianWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.math3.analysis.interpolation;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;
/*    */ 
/*    */ public class NevilleInterpolator implements UnivariateInterpolator, Serializable {
/*    */   static final long serialVersionUID = 3003707660147873733L;
/*    */   
/*    */   public PolynomialFunctionLagrangeForm interpolate(double[] x, double[] y) {
/* 55 */     return new PolynomialFunctionLagrangeForm(x, y);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\NevilleInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
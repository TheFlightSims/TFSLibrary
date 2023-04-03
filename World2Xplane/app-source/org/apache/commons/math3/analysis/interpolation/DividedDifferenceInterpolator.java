/*     */ package org.apache.commons.math3.analysis.interpolation;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;
/*     */ import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;
/*     */ 
/*     */ public class DividedDifferenceInterpolator implements UnivariateInterpolator, Serializable {
/*     */   private static final long serialVersionUID = 107049519551235069L;
/*     */   
/*     */   public PolynomialFunctionNewtonForm interpolate(double[] x, double[] y) {
/*  60 */     PolynomialFunctionLagrangeForm.verifyInterpolationArray(x, y, true);
/*  70 */     double[] c = new double[x.length - 1];
/*  71 */     System.arraycopy(x, 0, c, 0, c.length);
/*  73 */     double[] a = computeDividedDifference(x, y);
/*  74 */     return new PolynomialFunctionNewtonForm(a, c);
/*     */   }
/*     */   
/*     */   protected static double[] computeDividedDifference(double[] x, double[] y) {
/*  98 */     PolynomialFunctionLagrangeForm.verifyInterpolationArray(x, y, true);
/* 100 */     double[] divdiff = (double[])y.clone();
/* 102 */     int n = x.length;
/* 103 */     double[] a = new double[n];
/* 104 */     a[0] = divdiff[0];
/* 105 */     for (int i = 1; i < n; i++) {
/* 106 */       for (int j = 0; j < n - i; j++) {
/* 107 */         double denominator = x[j + i] - x[j];
/* 108 */         divdiff[j] = (divdiff[j + 1] - divdiff[j]) / denominator;
/*     */       } 
/* 110 */       a[i] = divdiff[0];
/*     */     } 
/* 113 */     return a;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\DividedDifferenceInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
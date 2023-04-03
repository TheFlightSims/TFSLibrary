/*    */ package org.apache.commons.math3.analysis.interpolation;
/*    */ 
/*    */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*    */ import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
/*    */ import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
/*    */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*    */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ import org.apache.commons.math3.util.MathArrays;
/*    */ 
/*    */ public class LinearInterpolator implements UnivariateInterpolator {
/*    */   public PolynomialSplineFunction interpolate(double[] x, double[] y) {
/* 44 */     if (x.length != y.length)
/* 45 */       throw new DimensionMismatchException(x.length, y.length); 
/* 48 */     if (x.length < 2)
/* 49 */       throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(x.length), Integer.valueOf(2), true); 
/* 54 */     int n = x.length - 1;
/* 56 */     MathArrays.checkOrder(x);
/* 59 */     double[] m = new double[n];
/* 60 */     for (int i = 0; i < n; i++)
/* 61 */       m[i] = (y[i + 1] - y[i]) / (x[i + 1] - x[i]); 
/* 64 */     PolynomialFunction[] polynomials = new PolynomialFunction[n];
/* 65 */     double[] coefficients = new double[2];
/* 66 */     for (int j = 0; j < n; j++) {
/* 67 */       coefficients[0] = y[j];
/* 68 */       coefficients[1] = m[j];
/* 69 */       polynomials[j] = new PolynomialFunction(coefficients);
/*    */     } 
/* 72 */     return new PolynomialSplineFunction(x, polynomials);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\interpolation\LinearInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
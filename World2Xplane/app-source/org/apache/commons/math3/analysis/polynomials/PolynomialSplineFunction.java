/*     */ package org.apache.commons.math3.analysis.polynomials;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class PolynomialSplineFunction implements DifferentiableUnivariateFunction {
/*     */   private final double[] knots;
/*     */   
/*     */   private final PolynomialFunction[] polynomials;
/*     */   
/*     */   private final int n;
/*     */   
/*     */   public PolynomialSplineFunction(double[] knots, PolynomialFunction[] polynomials) {
/* 101 */     if (knots == null || polynomials == null)
/* 103 */       throw new NullArgumentException(); 
/* 105 */     if (knots.length < 2)
/* 106 */       throw new NumberIsTooSmallException(LocalizedFormats.NOT_ENOUGH_POINTS_IN_SPLINE_PARTITION, Integer.valueOf(2), Integer.valueOf(knots.length), false); 
/* 109 */     if (knots.length - 1 != polynomials.length)
/* 110 */       throw new DimensionMismatchException(polynomials.length, knots.length); 
/* 112 */     MathArrays.checkOrder(knots);
/* 114 */     this.n = knots.length - 1;
/* 115 */     this.knots = new double[this.n + 1];
/* 116 */     System.arraycopy(knots, 0, this.knots, 0, this.n + 1);
/* 117 */     this.polynomials = new PolynomialFunction[this.n];
/* 118 */     System.arraycopy(polynomials, 0, this.polynomials, 0, this.n);
/*     */   }
/*     */   
/*     */   public double value(double v) {
/* 133 */     if (v < this.knots[0] || v > this.knots[this.n])
/* 134 */       throw new OutOfRangeException(Double.valueOf(v), Double.valueOf(this.knots[0]), Double.valueOf(this.knots[this.n])); 
/* 136 */     int i = Arrays.binarySearch(this.knots, v);
/* 137 */     if (i < 0)
/* 138 */       i = -i - 2; 
/* 143 */     if (i >= this.polynomials.length)
/* 144 */       i--; 
/* 146 */     return this.polynomials[i].value(v - this.knots[i]);
/*     */   }
/*     */   
/*     */   public UnivariateFunction derivative() {
/* 155 */     return (UnivariateFunction)polynomialSplineDerivative();
/*     */   }
/*     */   
/*     */   public PolynomialSplineFunction polynomialSplineDerivative() {
/* 164 */     PolynomialFunction[] derivativePolynomials = new PolynomialFunction[this.n];
/* 165 */     for (int i = 0; i < this.n; i++)
/* 166 */       derivativePolynomials[i] = this.polynomials[i].polynomialDerivative(); 
/* 168 */     return new PolynomialSplineFunction(this.knots, derivativePolynomials);
/*     */   }
/*     */   
/*     */   public int getN() {
/* 178 */     return this.n;
/*     */   }
/*     */   
/*     */   public PolynomialFunction[] getPolynomials() {
/* 189 */     PolynomialFunction[] p = new PolynomialFunction[this.n];
/* 190 */     System.arraycopy(this.polynomials, 0, p, 0, this.n);
/* 191 */     return p;
/*     */   }
/*     */   
/*     */   public double[] getKnots() {
/* 202 */     double[] out = new double[this.n + 1];
/* 203 */     System.arraycopy(this.knots, 0, out, 0, this.n + 1);
/* 204 */     return out;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\polynomials\PolynomialSplineFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
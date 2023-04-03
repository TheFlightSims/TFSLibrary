/*     */ package org.apache.commons.math3.analysis.polynomials;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class PolynomialFunctionNewtonForm implements UnivariateFunction {
/*     */   private double[] coefficients;
/*     */   
/*     */   private final double[] c;
/*     */   
/*     */   private final double[] a;
/*     */   
/*     */   private boolean coefficientsComputed;
/*     */   
/*     */   public PolynomialFunctionNewtonForm(double[] a, double[] c) {
/*  79 */     verifyInputArray(a, c);
/*  80 */     this.a = new double[a.length];
/*  81 */     this.c = new double[c.length];
/*  82 */     System.arraycopy(a, 0, this.a, 0, a.length);
/*  83 */     System.arraycopy(c, 0, this.c, 0, c.length);
/*  84 */     this.coefficientsComputed = false;
/*     */   }
/*     */   
/*     */   public double value(double z) {
/*  94 */     return evaluate(this.a, this.c, z);
/*     */   }
/*     */   
/*     */   public int degree() {
/* 103 */     return this.c.length;
/*     */   }
/*     */   
/*     */   public double[] getNewtonCoefficients() {
/* 114 */     double[] out = new double[this.a.length];
/* 115 */     System.arraycopy(this.a, 0, out, 0, this.a.length);
/* 116 */     return out;
/*     */   }
/*     */   
/*     */   public double[] getCenters() {
/* 127 */     double[] out = new double[this.c.length];
/* 128 */     System.arraycopy(this.c, 0, out, 0, this.c.length);
/* 129 */     return out;
/*     */   }
/*     */   
/*     */   public double[] getCoefficients() {
/* 140 */     if (!this.coefficientsComputed)
/* 141 */       computeCoefficients(); 
/* 143 */     double[] out = new double[this.coefficients.length];
/* 144 */     System.arraycopy(this.coefficients, 0, out, 0, this.coefficients.length);
/* 145 */     return out;
/*     */   }
/*     */   
/*     */   public static double evaluate(double[] a, double[] c, double z) {
/* 164 */     verifyInputArray(a, c);
/* 166 */     int n = c.length;
/* 167 */     double value = a[n];
/* 168 */     for (int i = n - 1; i >= 0; i--)
/* 169 */       value = a[i] + (z - c[i]) * value; 
/* 172 */     return value;
/*     */   }
/*     */   
/*     */   protected void computeCoefficients() {
/* 180 */     int n = degree();
/* 182 */     this.coefficients = new double[n + 1];
/*     */     int i;
/* 183 */     for (i = 0; i <= n; i++)
/* 184 */       this.coefficients[i] = 0.0D; 
/* 187 */     this.coefficients[0] = this.a[n];
/* 188 */     for (i = n - 1; i >= 0; i--) {
/* 189 */       for (int j = n - i; j > 0; j--)
/* 190 */         this.coefficients[j] = this.coefficients[j - 1] - this.c[i] * this.coefficients[j]; 
/* 192 */       this.coefficients[0] = this.a[i] - this.c[i] * this.coefficients[0];
/*     */     } 
/* 195 */     this.coefficientsComputed = true;
/*     */   }
/*     */   
/*     */   protected static void verifyInputArray(double[] a, double[] c) {
/* 215 */     if (a.length == 0 || c.length == 0)
/* 217 */       throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY); 
/* 219 */     if (a.length != c.length + 1)
/* 220 */       throw new DimensionMismatchException(LocalizedFormats.ARRAY_SIZES_SHOULD_HAVE_DIFFERENCE_1, a.length, c.length); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\polynomials\PolynomialFunctionNewtonForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.math3.analysis.polynomials;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class PolynomialFunction implements DifferentiableUnivariateFunction, Serializable {
/*     */   private static final long serialVersionUID = -7726511984200295583L;
/*     */   
/*     */   private final double[] coefficients;
/*     */   
/*     */   public PolynomialFunction(double[] c) throws NullArgumentException, NoDataException {
/*  68 */     MathUtils.checkNotNull(c);
/*  69 */     int n = c.length;
/*  70 */     if (n == 0)
/*  71 */       throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY); 
/*  73 */     while (n > 1 && c[n - 1] == 0.0D)
/*  74 */       n--; 
/*  76 */     this.coefficients = new double[n];
/*  77 */     System.arraycopy(c, 0, this.coefficients, 0, n);
/*     */   }
/*     */   
/*     */   public double value(double x) {
/*  92 */     return evaluate(this.coefficients, x);
/*     */   }
/*     */   
/*     */   public int degree() {
/* 101 */     return this.coefficients.length - 1;
/*     */   }
/*     */   
/*     */   public double[] getCoefficients() {
/* 113 */     return (double[])this.coefficients.clone();
/*     */   }
/*     */   
/*     */   protected static double evaluate(double[] coefficients, double argument) throws NullArgumentException, NoDataException {
/* 128 */     MathUtils.checkNotNull(coefficients);
/* 129 */     int n = coefficients.length;
/* 130 */     if (n == 0)
/* 131 */       throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY); 
/* 133 */     double result = coefficients[n - 1];
/* 134 */     for (int j = n - 2; j >= 0; j--)
/* 135 */       result = argument * result + coefficients[j]; 
/* 137 */     return result;
/*     */   }
/*     */   
/*     */   public PolynomialFunction add(PolynomialFunction p) {
/* 148 */     int lowLength = FastMath.min(this.coefficients.length, p.coefficients.length);
/* 149 */     int highLength = FastMath.max(this.coefficients.length, p.coefficients.length);
/* 152 */     double[] newCoefficients = new double[highLength];
/* 153 */     for (int i = 0; i < lowLength; i++)
/* 154 */       newCoefficients[i] = this.coefficients[i] + p.coefficients[i]; 
/* 156 */     System.arraycopy((this.coefficients.length < p.coefficients.length) ? p.coefficients : this.coefficients, lowLength, newCoefficients, lowLength, highLength - lowLength);
/* 162 */     return new PolynomialFunction(newCoefficients);
/*     */   }
/*     */   
/*     */   public PolynomialFunction subtract(PolynomialFunction p) {
/* 173 */     int lowLength = FastMath.min(this.coefficients.length, p.coefficients.length);
/* 174 */     int highLength = FastMath.max(this.coefficients.length, p.coefficients.length);
/* 177 */     double[] newCoefficients = new double[highLength];
/*     */     int i;
/* 178 */     for (i = 0; i < lowLength; i++)
/* 179 */       newCoefficients[i] = this.coefficients[i] - p.coefficients[i]; 
/* 181 */     if (this.coefficients.length < p.coefficients.length) {
/* 182 */       for (i = lowLength; i < highLength; i++)
/* 183 */         newCoefficients[i] = -p.coefficients[i]; 
/*     */     } else {
/* 186 */       System.arraycopy(this.coefficients, lowLength, newCoefficients, lowLength, highLength - lowLength);
/*     */     } 
/* 190 */     return new PolynomialFunction(newCoefficients);
/*     */   }
/*     */   
/*     */   public PolynomialFunction negate() {
/* 199 */     double[] newCoefficients = new double[this.coefficients.length];
/* 200 */     for (int i = 0; i < this.coefficients.length; i++)
/* 201 */       newCoefficients[i] = -this.coefficients[i]; 
/* 203 */     return new PolynomialFunction(newCoefficients);
/*     */   }
/*     */   
/*     */   public PolynomialFunction multiply(PolynomialFunction p) {
/* 213 */     double[] newCoefficients = new double[this.coefficients.length + p.coefficients.length - 1];
/* 215 */     for (int i = 0; i < newCoefficients.length; i++) {
/* 216 */       newCoefficients[i] = 0.0D;
/* 217 */       int j = FastMath.max(0, i + 1 - p.coefficients.length);
/* 218 */       for (; j < FastMath.min(this.coefficients.length, i + 1); 
/* 219 */         j++)
/* 220 */         newCoefficients[i] = newCoefficients[i] + this.coefficients[j] * p.coefficients[i - j]; 
/*     */     } 
/* 224 */     return new PolynomialFunction(newCoefficients);
/*     */   }
/*     */   
/*     */   protected static double[] differentiate(double[] coefficients) throws NullArgumentException, NoDataException {
/* 237 */     MathUtils.checkNotNull(coefficients);
/* 238 */     int n = coefficients.length;
/* 239 */     if (n == 0)
/* 240 */       throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY); 
/* 242 */     if (n == 1)
/* 243 */       return new double[] { 0.0D }; 
/* 245 */     double[] result = new double[n - 1];
/* 246 */     for (int i = n - 1; i > 0; i--)
/* 247 */       result[i - 1] = i * coefficients[i]; 
/* 249 */     return result;
/*     */   }
/*     */   
/*     */   public PolynomialFunction polynomialDerivative() {
/* 258 */     return new PolynomialFunction(differentiate(this.coefficients));
/*     */   }
/*     */   
/*     */   public UnivariateFunction derivative() {
/* 267 */     return (UnivariateFunction)polynomialDerivative();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 287 */     StringBuilder s = new StringBuilder();
/* 288 */     if (this.coefficients[0] == 0.0D) {
/* 289 */       if (this.coefficients.length == 1)
/* 290 */         return "0"; 
/*     */     } else {
/* 293 */       s.append(toString(this.coefficients[0]));
/*     */     } 
/* 296 */     for (int i = 1; i < this.coefficients.length; i++) {
/* 297 */       if (this.coefficients[i] != 0.0D) {
/* 298 */         if (s.length() > 0) {
/* 299 */           if (this.coefficients[i] < 0.0D) {
/* 300 */             s.append(" - ");
/*     */           } else {
/* 302 */             s.append(" + ");
/*     */           } 
/* 305 */         } else if (this.coefficients[i] < 0.0D) {
/* 306 */           s.append("-");
/*     */         } 
/* 310 */         double absAi = FastMath.abs(this.coefficients[i]);
/* 311 */         if (absAi - 1.0D != 0.0D) {
/* 312 */           s.append(toString(absAi));
/* 313 */           s.append(' ');
/*     */         } 
/* 316 */         s.append("x");
/* 317 */         if (i > 1) {
/* 318 */           s.append('^');
/* 319 */           s.append(Integer.toString(i));
/*     */         } 
/*     */       } 
/*     */     } 
/* 324 */     return s.toString();
/*     */   }
/*     */   
/*     */   private static String toString(double coeff) {
/* 334 */     String c = Double.toString(coeff);
/* 335 */     if (c.endsWith(".0"))
/* 336 */       return c.substring(0, c.length() - 2); 
/* 338 */     return c;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 345 */     int prime = 31;
/* 346 */     int result = 1;
/* 347 */     result = 31 * result + Arrays.hashCode(this.coefficients);
/* 348 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 354 */     if (this == obj)
/* 355 */       return true; 
/* 357 */     if (!(obj instanceof PolynomialFunction))
/* 358 */       return false; 
/* 360 */     PolynomialFunction other = (PolynomialFunction)obj;
/* 361 */     if (!Arrays.equals(this.coefficients, other.coefficients))
/* 362 */       return false; 
/* 364 */     return true;
/*     */   }
/*     */   
/*     */   public static class Parametric implements ParametricUnivariateFunction {
/*     */     public double[] gradient(double x, double... parameters) {
/* 375 */       double[] gradient = new double[parameters.length];
/* 376 */       double xn = 1.0D;
/* 377 */       for (int i = 0; i < parameters.length; i++) {
/* 378 */         gradient[i] = xn;
/* 379 */         xn *= x;
/*     */       } 
/* 381 */       return gradient;
/*     */     }
/*     */     
/*     */     public double value(double x, double... parameters) {
/* 386 */       return PolynomialFunction.evaluate(parameters, x);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\polynomials\PolynomialFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
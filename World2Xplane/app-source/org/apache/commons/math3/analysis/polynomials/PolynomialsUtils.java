/*     */ package org.apache.commons.math3.analysis.polynomials;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.math3.fraction.BigFraction;
/*     */ import org.apache.commons.math3.util.ArithmeticUtils;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class PolynomialsUtils {
/*  55 */   private static final List<BigFraction> CHEBYSHEV_COEFFICIENTS = new ArrayList<BigFraction>();
/*     */   
/*     */   static {
/*  56 */     CHEBYSHEV_COEFFICIENTS.add(BigFraction.ONE);
/*  57 */     CHEBYSHEV_COEFFICIENTS.add(BigFraction.ZERO);
/*  58 */     CHEBYSHEV_COEFFICIENTS.add(BigFraction.ONE);
/*     */   }
/*     */   
/*  62 */   private static final List<BigFraction> HERMITE_COEFFICIENTS = new ArrayList<BigFraction>();
/*     */   
/*     */   static {
/*  63 */     HERMITE_COEFFICIENTS.add(BigFraction.ONE);
/*  64 */     HERMITE_COEFFICIENTS.add(BigFraction.ZERO);
/*  65 */     HERMITE_COEFFICIENTS.add(BigFraction.TWO);
/*     */   }
/*     */   
/*  69 */   private static final List<BigFraction> LAGUERRE_COEFFICIENTS = new ArrayList<BigFraction>();
/*     */   
/*     */   static {
/*  70 */     LAGUERRE_COEFFICIENTS.add(BigFraction.ONE);
/*  71 */     LAGUERRE_COEFFICIENTS.add(BigFraction.ONE);
/*  72 */     LAGUERRE_COEFFICIENTS.add(BigFraction.MINUS_ONE);
/*     */   }
/*     */   
/*  76 */   private static final List<BigFraction> LEGENDRE_COEFFICIENTS = new ArrayList<BigFraction>();
/*     */   
/*     */   static {
/*  77 */     LEGENDRE_COEFFICIENTS.add(BigFraction.ONE);
/*  78 */     LEGENDRE_COEFFICIENTS.add(BigFraction.ZERO);
/*  79 */     LEGENDRE_COEFFICIENTS.add(BigFraction.ONE);
/*     */   }
/*     */   
/*  82 */   private static final Map<JacobiKey, List<BigFraction>> JACOBI_COEFFICIENTS = new HashMap<JacobiKey, List<BigFraction>>();
/*     */   
/*     */   public static PolynomialFunction createChebyshevPolynomial(int degree) {
/* 106 */     return buildPolynomial(degree, CHEBYSHEV_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {
/* 108 */           private final BigFraction[] coeffs = new BigFraction[] { BigFraction.ZERO, BigFraction.TWO, BigFraction.ONE };
/*     */           
/*     */           public BigFraction[] generate(int k) {
/* 111 */             return this.coeffs;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static PolynomialFunction createHermitePolynomial(int degree) {
/* 131 */     return buildPolynomial(degree, HERMITE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {
/*     */           public BigFraction[] generate(int k) {
/* 135 */             return new BigFraction[] { BigFraction.ZERO, BigFraction.TWO, new BigFraction(2 * k) };
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static PolynomialFunction createLaguerrePolynomial(int degree) {
/* 157 */     return buildPolynomial(degree, LAGUERRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {
/*     */           public BigFraction[] generate(int k) {
/* 161 */             int kP1 = k + 1;
/* 162 */             return new BigFraction[] { new BigFraction(2 * k + 1, kP1), new BigFraction(-1, kP1), new BigFraction(k, kP1) };
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static PolynomialFunction createLegendrePolynomial(int degree) {
/* 184 */     return buildPolynomial(degree, LEGENDRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator() {
/*     */           public BigFraction[] generate(int k) {
/* 188 */             int kP1 = k + 1;
/* 189 */             return new BigFraction[] { BigFraction.ZERO, new BigFraction(k + kP1, kP1), new BigFraction(k, kP1) };
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static PolynomialFunction createJacobiPolynomial(int degree, final int v, final int w) {
/* 217 */     JacobiKey key = new JacobiKey(v, w);
/* 219 */     if (!JACOBI_COEFFICIENTS.containsKey(key)) {
/* 222 */       List<BigFraction> list = new ArrayList<BigFraction>();
/* 223 */       JACOBI_COEFFICIENTS.put(key, list);
/* 226 */       list.add(BigFraction.ONE);
/* 229 */       list.add(new BigFraction(v - w, 2));
/* 230 */       list.add(new BigFraction(2 + v + w, 2));
/*     */     } 
/* 234 */     return buildPolynomial(degree, JACOBI_COEFFICIENTS.get(key), new RecurrenceCoefficientsGenerator() {
/*     */           public BigFraction[] generate(int k) {
/* 238 */             k++;
/* 239 */             int kvw = k + v + w;
/* 240 */             int twoKvw = kvw + k;
/* 241 */             int twoKvwM1 = twoKvw - 1;
/* 242 */             int twoKvwM2 = twoKvw - 2;
/* 243 */             int den = 2 * k * kvw * twoKvwM2;
/* 245 */             return new BigFraction[] { new BigFraction(twoKvwM1 * (this.val$v * this.val$v - this.val$w * this.val$w), den), new BigFraction(twoKvwM1 * twoKvw * twoKvwM2, den), new BigFraction(2 * (k + this.val$v - 1) * (k + this.val$w - 1) * twoKvw, den) };
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private static class JacobiKey {
/*     */     private final int v;
/*     */     
/*     */     private final int w;
/*     */     
/*     */     public JacobiKey(int v, int w) {
/* 269 */       this.v = v;
/* 270 */       this.w = w;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 278 */       return this.v << 16 ^ this.w;
/*     */     }
/*     */     
/*     */     public boolean equals(Object key) {
/* 288 */       if (key == null || !(key instanceof JacobiKey))
/* 289 */         return false; 
/* 292 */       JacobiKey otherK = (JacobiKey)key;
/* 293 */       return (this.v == otherK.v && this.w == otherK.w);
/*     */     }
/*     */   }
/*     */   
/*     */   public static double[] shift(double[] coefficients, double shift) {
/* 324 */     int dp1 = coefficients.length;
/* 325 */     double[] newCoefficients = new double[dp1];
/* 328 */     int[][] coeff = new int[dp1][dp1];
/*     */     int i;
/* 329 */     for (i = 0; i < dp1; i++) {
/* 330 */       for (int k = 0; k <= i; k++)
/* 331 */         coeff[i][k] = (int)ArithmeticUtils.binomialCoefficient(i, k); 
/*     */     } 
/* 336 */     for (i = 0; i < dp1; i++)
/* 337 */       newCoefficients[0] = newCoefficients[0] + coefficients[i] * FastMath.pow(shift, i); 
/* 341 */     int d = dp1 - 1;
/* 342 */     for (int j = 0; j < d; j++) {
/* 343 */       for (int k = j; k < d; k++)
/* 344 */         newCoefficients[j + 1] = newCoefficients[j + 1] + coeff[k + 1][k - j] * coefficients[k + 1] * FastMath.pow(shift, (k - j)); 
/*     */     } 
/* 349 */     return newCoefficients;
/*     */   }
/*     */   
/*     */   private static PolynomialFunction buildPolynomial(int degree, List<BigFraction> coefficients, RecurrenceCoefficientsGenerator generator) {
/* 363 */     int maxDegree = (int)FastMath.floor(FastMath.sqrt((2 * coefficients.size()))) - 1;
/* 364 */     synchronized (PolynomialsUtils.class) {
/* 365 */       if (degree > maxDegree)
/* 366 */         computeUpToDegree(degree, maxDegree, generator, coefficients); 
/*     */     } 
/* 378 */     int start = degree * (degree + 1) / 2;
/* 380 */     double[] a = new double[degree + 1];
/* 381 */     for (int i = 0; i <= degree; i++)
/* 382 */       a[i] = ((BigFraction)coefficients.get(start + i)).doubleValue(); 
/* 386 */     return new PolynomialFunction(a);
/*     */   }
/*     */   
/*     */   private static void computeUpToDegree(int degree, int maxDegree, RecurrenceCoefficientsGenerator generator, List<BigFraction> coefficients) {
/* 400 */     int startK = (maxDegree - 1) * maxDegree / 2;
/* 401 */     for (int k = maxDegree; k < degree; k++) {
/* 404 */       int startKm1 = startK;
/* 405 */       startK += k;
/* 408 */       BigFraction[] ai = generator.generate(k);
/* 410 */       BigFraction ck = coefficients.get(startK);
/* 411 */       BigFraction ckm1 = coefficients.get(startKm1);
/* 414 */       coefficients.add(ck.multiply(ai[0]).subtract(ckm1.multiply(ai[2])));
/* 417 */       for (int i = 1; i < k; i++) {
/* 418 */         BigFraction bigFraction = ck;
/* 419 */         ck = coefficients.get(startK + i);
/* 420 */         ckm1 = coefficients.get(startKm1 + i);
/* 421 */         coefficients.add(ck.multiply(ai[0]).add(bigFraction.multiply(ai[1])).subtract(ckm1.multiply(ai[2])));
/*     */       } 
/* 425 */       BigFraction ckPrev = ck;
/* 426 */       ck = coefficients.get(startK + k);
/* 427 */       coefficients.add(ck.multiply(ai[0]).add(ckPrev.multiply(ai[1])));
/* 430 */       coefficients.add(ck.multiply(ai[1]));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static interface RecurrenceCoefficientsGenerator {
/*     */     BigFraction[] generate(int param1Int);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\polynomials\PolynomialsUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.math3.analysis;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.function.Identity;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class FunctionUtils {
/*     */   public static UnivariateFunction compose(UnivariateFunction... f) {
/*  46 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/*  49 */           double r = x;
/*  50 */           for (int i = f.length - 1; i >= 0; i--)
/*  51 */             r = f[i].value(r); 
/*  53 */           return r;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static DifferentiableUnivariateFunction compose(DifferentiableUnivariateFunction... f) {
/*  67 */     return new DifferentiableUnivariateFunction() {
/*     */         public double value(double x) {
/*  70 */           double r = x;
/*  71 */           for (int i = f.length - 1; i >= 0; i--)
/*  72 */             r = f[i].value(r); 
/*  74 */           return r;
/*     */         }
/*     */         
/*     */         public UnivariateFunction derivative() {
/*  79 */           return new UnivariateFunction() {
/*     */               public double value(double x) {
/*  82 */                 double p = 1.0D;
/*  83 */                 double r = x;
/*  84 */                 for (int i = f.length - 1; i >= 0; i--) {
/*  85 */                   p *= f[i].derivative().value(r);
/*  86 */                   r = f[i].value(r);
/*     */                 } 
/*  88 */                 return p;
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static UnivariateFunction add(UnivariateFunction... f) {
/* 102 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/* 105 */           double r = f[0].value(x);
/* 106 */           for (int i = 1; i < f.length; i++)
/* 107 */             r += f[i].value(x); 
/* 109 */           return r;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static DifferentiableUnivariateFunction add(DifferentiableUnivariateFunction... f) {
/* 121 */     return new DifferentiableUnivariateFunction() {
/*     */         public double value(double x) {
/* 124 */           double r = f[0].value(x);
/* 125 */           for (int i = 1; i < f.length; i++)
/* 126 */             r += f[i].value(x); 
/* 128 */           return r;
/*     */         }
/*     */         
/*     */         public UnivariateFunction derivative() {
/* 133 */           return new UnivariateFunction() {
/*     */               public double value(double x) {
/* 136 */                 double r = f[0].derivative().value(x);
/* 137 */                 for (int i = 1; i < f.length; i++)
/* 138 */                   r += f[i].derivative().value(x); 
/* 140 */                 return r;
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static UnivariateFunction multiply(UnivariateFunction... f) {
/* 154 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/* 157 */           double r = f[0].value(x);
/* 158 */           for (int i = 1; i < f.length; i++)
/* 159 */             r *= f[i].value(x); 
/* 161 */           return r;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static DifferentiableUnivariateFunction multiply(DifferentiableUnivariateFunction... f) {
/* 173 */     return new DifferentiableUnivariateFunction() {
/*     */         public double value(double x) {
/* 176 */           double r = f[0].value(x);
/* 177 */           for (int i = 1; i < f.length; i++)
/* 178 */             r *= f[i].value(x); 
/* 180 */           return r;
/*     */         }
/*     */         
/*     */         public UnivariateFunction derivative() {
/* 185 */           return new UnivariateFunction() {
/*     */               public double value(double x) {
/* 188 */                 double sum = 0.0D;
/* 189 */                 for (int i = 0; i < f.length; i++) {
/* 190 */                   double prod = f[i].derivative().value(x);
/* 191 */                   for (int j = 0; j < f.length; j++) {
/* 192 */                     if (i != j)
/* 193 */                       prod *= f[j].value(x); 
/*     */                   } 
/* 196 */                   sum += prod;
/*     */                 } 
/* 198 */                 return sum;
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static UnivariateFunction combine(final BivariateFunction combiner, final UnivariateFunction f, final UnivariateFunction g) {
/* 217 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/* 220 */           return combiner.value(f.value(x), g.value(x));
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static MultivariateFunction collector(final BivariateFunction combiner, final UnivariateFunction f, final double initialValue) {
/* 238 */     return new MultivariateFunction() {
/*     */         public double value(double[] point) {
/* 241 */           double result = combiner.value(initialValue, f.value(point[0]));
/* 242 */           for (int i = 1; i < point.length; i++)
/* 243 */             result = combiner.value(result, f.value(point[i])); 
/* 245 */           return result;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static MultivariateFunction collector(BivariateFunction combiner, double initialValue) {
/* 261 */     return collector(combiner, (UnivariateFunction)new Identity(), initialValue);
/*     */   }
/*     */   
/*     */   public static UnivariateFunction fix1stArgument(final BivariateFunction f, final double fixed) {
/* 273 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/* 276 */           return f.value(fixed, x);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static UnivariateFunction fix2ndArgument(final BivariateFunction f, final double fixed) {
/* 289 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/* 292 */           return f.value(x, fixed);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static double[] sample(UnivariateFunction f, double min, double max, int n) {
/* 320 */     if (n <= 0)
/* 321 */       throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, Integer.valueOf(n)); 
/* 325 */     if (min >= max)
/* 326 */       throw new NumberIsTooLargeException(Double.valueOf(min), Double.valueOf(max), false); 
/* 329 */     double[] s = new double[n];
/* 330 */     double h = (max - min) / n;
/* 331 */     for (int i = 0; i < n; i++)
/* 332 */       s[i] = f.value(min + i * h); 
/* 334 */     return s;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\FunctionUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
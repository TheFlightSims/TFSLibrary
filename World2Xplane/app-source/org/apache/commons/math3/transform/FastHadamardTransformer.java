/*     */ package org.apache.commons.math3.transform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.analysis.FunctionUtils;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.ArithmeticUtils;
/*     */ 
/*     */ public class FastHadamardTransformer implements RealTransformer, Serializable {
/*     */   static final long serialVersionUID = 20120211L;
/*     */   
/*     */   public double[] transform(double[] f, TransformType type) {
/*  50 */     if (type == TransformType.FORWARD)
/*  51 */       return fht(f); 
/*  53 */     return TransformUtils.scaleArray(fht(f), 1.0D / f.length);
/*     */   }
/*     */   
/*     */   public double[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) {
/*  70 */     return transform(FunctionUtils.sample(f, min, max, n), type);
/*     */   }
/*     */   
/*     */   public int[] transform(int[] f) {
/*  84 */     return fht(f);
/*     */   }
/*     */   
/*     */   protected double[] fht(double[] x) throws MathIllegalArgumentException {
/* 232 */     int n = x.length;
/* 233 */     int halfN = n / 2;
/* 235 */     if (!ArithmeticUtils.isPowerOfTwo(n))
/* 236 */       throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, new Object[] { Integer.valueOf(n) }); 
/* 245 */     double[] yPrevious = new double[n];
/* 246 */     double[] yCurrent = (double[])x.clone();
/*     */     int j;
/* 249 */     for (j = 1; j < n; j <<= 1) {
/* 252 */       double[] yTmp = yCurrent;
/* 253 */       yCurrent = yPrevious;
/* 254 */       yPrevious = yTmp;
/*     */       int i;
/* 257 */       for (i = 0; i < halfN; i++) {
/* 259 */         int twoI = 2 * i;
/* 260 */         yCurrent[i] = yPrevious[twoI] + yPrevious[twoI + 1];
/*     */       } 
/* 262 */       for (i = halfN; i < n; i++) {
/* 264 */         int twoI = 2 * i;
/* 265 */         yCurrent[i] = yPrevious[twoI - n] - yPrevious[twoI - n + 1];
/*     */       } 
/*     */     } 
/* 269 */     return yCurrent;
/*     */   }
/*     */   
/*     */   protected int[] fht(int[] x) throws MathIllegalArgumentException {
/* 284 */     int n = x.length;
/* 285 */     int halfN = n / 2;
/* 287 */     if (!ArithmeticUtils.isPowerOfTwo(n))
/* 288 */       throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, new Object[] { Integer.valueOf(n) }); 
/* 297 */     int[] yPrevious = new int[n];
/* 298 */     int[] yCurrent = (int[])x.clone();
/*     */     int j;
/* 301 */     for (j = 1; j < n; j <<= 1) {
/* 304 */       int[] yTmp = yCurrent;
/* 305 */       yCurrent = yPrevious;
/* 306 */       yPrevious = yTmp;
/*     */       int i;
/* 309 */       for (i = 0; i < halfN; i++) {
/* 311 */         int twoI = 2 * i;
/* 312 */         yCurrent[i] = yPrevious[twoI] + yPrevious[twoI + 1];
/*     */       } 
/* 314 */       for (i = halfN; i < n; i++) {
/* 316 */         int twoI = 2 * i;
/* 317 */         yCurrent[i] = yPrevious[twoI - n] - yPrevious[twoI - n + 1];
/*     */       } 
/*     */     } 
/* 322 */     return yCurrent;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\transform\FastHadamardTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
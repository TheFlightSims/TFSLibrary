/*     */ package org.apache.commons.math3.transform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.analysis.FunctionUtils;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.complex.Complex;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.ArithmeticUtils;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class FastCosineTransformer implements RealTransformer, Serializable {
/*     */   static final long serialVersionUID = 20120212L;
/*     */   
/*     */   private final DctNormalization normalization;
/*     */   
/*     */   public FastCosineTransformer(DctNormalization normalization) {
/*  88 */     this.normalization = normalization;
/*     */   }
/*     */   
/*     */   public double[] transform(double[] f, TransformType type) {
/*     */     double s1;
/*  98 */     if (type == TransformType.FORWARD) {
/*  99 */       if (this.normalization == DctNormalization.ORTHOGONAL_DCT_I) {
/* 100 */         double s = FastMath.sqrt(2.0D / (f.length - 1));
/* 101 */         return TransformUtils.scaleArray(fct(f), s);
/*     */       } 
/* 103 */       return fct(f);
/*     */     } 
/* 105 */     double s2 = 2.0D / (f.length - 1);
/* 107 */     if (this.normalization == DctNormalization.ORTHOGONAL_DCT_I) {
/* 108 */       s1 = FastMath.sqrt(s2);
/*     */     } else {
/* 110 */       s1 = s2;
/*     */     } 
/* 112 */     return TransformUtils.scaleArray(fct(f), s1);
/*     */   }
/*     */   
/*     */   public double[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) {
/* 129 */     double[] data = FunctionUtils.sample(f, min, max, n);
/* 130 */     return transform(data, type);
/*     */   }
/*     */   
/*     */   protected double[] fct(double[] f) throws MathIllegalArgumentException {
/* 144 */     double[] transformed = new double[f.length];
/* 146 */     int n = f.length - 1;
/* 147 */     if (!ArithmeticUtils.isPowerOfTwo(n))
/* 148 */       throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_PLUS_ONE, new Object[] { Integer.valueOf(f.length) }); 
/* 152 */     if (n == 1) {
/* 153 */       transformed[0] = 0.5D * (f[0] + f[1]);
/* 154 */       transformed[1] = 0.5D * (f[0] - f[1]);
/* 155 */       return transformed;
/*     */     } 
/* 159 */     double[] x = new double[n];
/* 160 */     x[0] = 0.5D * (f[0] + f[n]);
/* 161 */     x[n >> 1] = f[n >> 1];
/* 163 */     double t1 = 0.5D * (f[0] - f[n]);
/* 164 */     for (int i = 1; i < n >> 1; i++) {
/* 165 */       double a = 0.5D * (f[i] + f[n - i]);
/* 166 */       double b = FastMath.sin(i * Math.PI / n) * (f[i] - f[n - i]);
/* 167 */       double c = FastMath.cos(i * Math.PI / n) * (f[i] - f[n - i]);
/* 168 */       x[i] = a - b;
/* 169 */       x[n - i] = a + b;
/* 170 */       t1 += c;
/*     */     } 
/* 173 */     FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
/* 174 */     Complex[] y = transformer.transform(x, TransformType.FORWARD);
/* 177 */     transformed[0] = y[0].getReal();
/* 178 */     transformed[1] = t1;
/* 179 */     for (int j = 1; j < n >> 1; j++) {
/* 180 */       transformed[2 * j] = y[j].getReal();
/* 181 */       transformed[2 * j + 1] = transformed[2 * j - 1] - y[j].getImaginary();
/*     */     } 
/* 183 */     transformed[n] = y[n >> 1].getReal();
/* 185 */     return transformed;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\transform\FastCosineTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
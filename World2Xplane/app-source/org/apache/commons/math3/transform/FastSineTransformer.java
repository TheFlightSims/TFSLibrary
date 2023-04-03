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
/*     */ public class FastSineTransformer implements RealTransformer, Serializable {
/*     */   static final long serialVersionUID = 20120211L;
/*     */   
/*     */   private final DstNormalization normalization;
/*     */   
/*     */   public FastSineTransformer(DstNormalization normalization) {
/*  93 */     this.normalization = normalization;
/*     */   }
/*     */   
/*     */   public double[] transform(double[] f, TransformType type) {
/* 105 */     if (this.normalization == DstNormalization.ORTHOGONAL_DST_I) {
/* 106 */       double d = FastMath.sqrt(2.0D / f.length);
/* 107 */       return TransformUtils.scaleArray(fst(f), d);
/*     */     } 
/* 109 */     if (type == TransformType.FORWARD)
/* 110 */       return fst(f); 
/* 112 */     double s = 2.0D / f.length;
/* 113 */     return TransformUtils.scaleArray(fst(f), s);
/*     */   }
/*     */   
/*     */   public double[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) {
/* 132 */     double[] data = FunctionUtils.sample(f, min, max, n);
/* 133 */     data[0] = 0.0D;
/* 134 */     return transform(data, type);
/*     */   }
/*     */   
/*     */   protected double[] fst(double[] f) throws MathIllegalArgumentException {
/* 148 */     double[] transformed = new double[f.length];
/* 150 */     if (!ArithmeticUtils.isPowerOfTwo(f.length))
/* 151 */       throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, new Object[] { Integer.valueOf(f.length) }); 
/* 155 */     if (f[0] != 0.0D)
/* 156 */       throw new MathIllegalArgumentException(LocalizedFormats.FIRST_ELEMENT_NOT_ZERO, new Object[] { Double.valueOf(f[0]) }); 
/* 160 */     int n = f.length;
/* 161 */     if (n == 1) {
/* 162 */       transformed[0] = 0.0D;
/* 163 */       return transformed;
/*     */     } 
/* 167 */     double[] x = new double[n];
/* 168 */     x[0] = 0.0D;
/* 169 */     x[n >> 1] = 2.0D * f[n >> 1];
/* 170 */     for (int i = 1; i < n >> 1; i++) {
/* 171 */       double a = FastMath.sin(i * Math.PI / n) * (f[i] + f[n - i]);
/* 172 */       double b = 0.5D * (f[i] - f[n - i]);
/* 173 */       x[i] = a + b;
/* 174 */       x[n - i] = a - b;
/*     */     } 
/* 177 */     FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
/* 178 */     Complex[] y = transformer.transform(x, TransformType.FORWARD);
/* 181 */     transformed[0] = 0.0D;
/* 182 */     transformed[1] = 0.5D * y[0].getReal();
/* 183 */     for (int j = 1; j < n >> 1; j++) {
/* 184 */       transformed[2 * j] = -y[j].getImaginary();
/* 185 */       transformed[2 * j + 1] = y[j].getReal() + transformed[2 * j - 1];
/*     */     } 
/* 188 */     return transformed;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\transform\FastSineTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
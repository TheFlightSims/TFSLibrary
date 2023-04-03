/*     */ package org.apache.commons.math3.transform;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.complex.Complex;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class TransformUtils {
/*  38 */   private static final int[] POWERS_OF_TWO = new int[] { 
/*  38 */       1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 
/*  38 */       1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 
/*  38 */       1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 
/*  38 */       1073741824 };
/*     */   
/*     */   public static double[] scaleArray(double[] f, double d) {
/*  62 */     for (int i = 0; i < f.length; i++)
/*  63 */       f[i] = f[i] * d; 
/*  65 */     return f;
/*     */   }
/*     */   
/*     */   public static Complex[] scaleArray(Complex[] f, double d) {
/*  78 */     for (int i = 0; i < f.length; i++)
/*  79 */       f[i] = new Complex(d * f[i].getReal(), d * f[i].getImaginary()); 
/*  81 */     return f;
/*     */   }
/*     */   
/*     */   public static double[][] createRealImaginaryArray(Complex[] dataC) {
/*  99 */     double[][] dataRI = new double[2][dataC.length];
/* 100 */     double[] dataR = dataRI[0];
/* 101 */     double[] dataI = dataRI[1];
/* 102 */     for (int i = 0; i < dataC.length; i++) {
/* 103 */       Complex c = dataC[i];
/* 104 */       dataR[i] = c.getReal();
/* 105 */       dataI[i] = c.getImaginary();
/*     */     } 
/* 107 */     return dataRI;
/*     */   }
/*     */   
/*     */   public static Complex[] createComplexArray(double[][] dataRI) throws DimensionMismatchException {
/* 128 */     if (dataRI.length != 2)
/* 129 */       throw new DimensionMismatchException(dataRI.length, 2); 
/* 131 */     double[] dataR = dataRI[0];
/* 132 */     double[] dataI = dataRI[1];
/* 133 */     if (dataR.length != dataI.length)
/* 134 */       throw new DimensionMismatchException(dataI.length, dataR.length); 
/* 137 */     int n = dataR.length;
/* 138 */     Complex[] c = new Complex[n];
/* 139 */     for (int i = 0; i < n; i++)
/* 140 */       c[i] = new Complex(dataR[i], dataI[i]); 
/* 142 */     return c;
/*     */   }
/*     */   
/*     */   public static int exactLog2(int n) throws MathIllegalArgumentException {
/* 157 */     int index = Arrays.binarySearch(POWERS_OF_TWO, n);
/* 158 */     if (index < 0)
/* 159 */       throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, new Object[] { Integer.valueOf(n) }); 
/* 163 */     return index;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\transform\TransformUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
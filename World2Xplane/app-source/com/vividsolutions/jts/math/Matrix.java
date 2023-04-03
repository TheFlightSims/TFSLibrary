/*     */ package com.vividsolutions.jts.math;
/*     */ 
/*     */ public class Matrix {
/*     */   private static void swapRows(double[][] m, int i, int j) {
/*  46 */     if (i == j)
/*     */       return; 
/*  47 */     for (int col = 0; col < (m[0]).length; col++) {
/*  48 */       double temp = m[i][col];
/*  49 */       m[i][col] = m[j][col];
/*  50 */       m[j][col] = temp;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void swapRows(double[] m, int i, int j) {
/*  56 */     if (i == j)
/*     */       return; 
/*  57 */     double temp = m[i];
/*  58 */     m[i] = m[j];
/*  59 */     m[j] = temp;
/*     */   }
/*     */   
/*     */   public static double[] solve(double[][] a, double[] b) {
/*  77 */     int n = b.length;
/*  78 */     if (a.length != n || (a[0]).length != n)
/*  79 */       throw new IllegalArgumentException("Matrix A is incorrectly sized"); 
/*  83 */     for (int i = 0; i < n; i++) {
/*  85 */       int maxElementRow = i;
/*     */       int k;
/*  86 */       for (k = i + 1; k < n; k++) {
/*  87 */         if (Math.abs(a[k][i]) > Math.abs(a[maxElementRow][i]))
/*  88 */           maxElementRow = k; 
/*     */       } 
/*  90 */       if (a[maxElementRow][i] == 0.0D)
/*  91 */         return null; 
/*  94 */       swapRows(a, i, maxElementRow);
/*  95 */       swapRows(b, i, maxElementRow);
/*  98 */       for (k = i + 1; k < n; k++) {
/*  99 */         double rowFactor = a[k][i] / a[i][i];
/* 100 */         for (int m = n - 1; m >= i; m--)
/* 101 */           a[k][m] = a[k][m] - a[i][m] * rowFactor; 
/* 102 */         b[k] = b[k] - b[i] * rowFactor;
/*     */       } 
/*     */     } 
/* 110 */     double[] solution = new double[n];
/* 111 */     for (int j = n - 1; j >= 0; j--) {
/* 112 */       double t = 0.0D;
/* 113 */       for (int k = j + 1; k < n; k++)
/* 114 */         t += a[j][k] * solution[k]; 
/* 115 */       solution[j] = (b[j] - t) / a[j][j];
/*     */     } 
/* 117 */     return solution;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\math\Matrix.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
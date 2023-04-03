/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ class TriDiagonalTransformer {
/*     */   private final double[][] householderVectors;
/*     */   
/*     */   private final double[] main;
/*     */   
/*     */   private final double[] secondary;
/*     */   
/*     */   private RealMatrix cachedQ;
/*     */   
/*     */   private RealMatrix cachedQt;
/*     */   
/*     */   private RealMatrix cachedT;
/*     */   
/*     */   public TriDiagonalTransformer(RealMatrix matrix) {
/*  63 */     if (!matrix.isSquare())
/*  64 */       throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension()); 
/*  68 */     int m = matrix.getRowDimension();
/*  69 */     this.householderVectors = matrix.getData();
/*  70 */     this.main = new double[m];
/*  71 */     this.secondary = new double[m - 1];
/*  72 */     this.cachedQ = null;
/*  73 */     this.cachedQt = null;
/*  74 */     this.cachedT = null;
/*  77 */     transform();
/*     */   }
/*     */   
/*     */   public RealMatrix getQ() {
/*  86 */     if (this.cachedQ == null)
/*  87 */       this.cachedQ = getQT().transpose(); 
/*  89 */     return this.cachedQ;
/*     */   }
/*     */   
/*     */   public RealMatrix getQT() {
/*  98 */     if (this.cachedQt == null) {
/*  99 */       int m = this.householderVectors.length;
/* 100 */       double[][] qta = new double[m][m];
/* 103 */       for (int k = m - 1; k >= 1; k--) {
/* 104 */         double[] hK = this.householderVectors[k - 1];
/* 105 */         qta[k][k] = 1.0D;
/* 106 */         if (hK[k] != 0.0D) {
/* 107 */           double inv = 1.0D / this.secondary[k - 1] * hK[k];
/* 108 */           double beta = 1.0D / this.secondary[k - 1];
/* 109 */           qta[k][k] = 1.0D + beta * hK[k];
/* 110 */           for (int i = k + 1; i < m; i++)
/* 111 */             qta[k][i] = beta * hK[i]; 
/* 113 */           for (int j = k + 1; j < m; j++) {
/* 114 */             beta = 0.0D;
/*     */             int n;
/* 115 */             for (n = k + 1; n < m; n++)
/* 116 */               beta += qta[j][n] * hK[n]; 
/* 118 */             beta *= inv;
/* 119 */             qta[j][k] = beta * hK[k];
/* 120 */             for (n = k + 1; n < m; n++)
/* 121 */               qta[j][n] = qta[j][n] + beta * hK[n]; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 126 */       qta[0][0] = 1.0D;
/* 127 */       this.cachedQt = MatrixUtils.createRealMatrix(qta);
/*     */     } 
/* 131 */     return this.cachedQt;
/*     */   }
/*     */   
/*     */   public RealMatrix getT() {
/* 139 */     if (this.cachedT == null) {
/* 140 */       int m = this.main.length;
/* 141 */       double[][] ta = new double[m][m];
/* 142 */       for (int i = 0; i < m; i++) {
/* 143 */         ta[i][i] = this.main[i];
/* 144 */         if (i > 0)
/* 145 */           ta[i][i - 1] = this.secondary[i - 1]; 
/* 147 */         if (i < this.main.length - 1)
/* 148 */           ta[i][i + 1] = this.secondary[i]; 
/*     */       } 
/* 151 */       this.cachedT = MatrixUtils.createRealMatrix(ta);
/*     */     } 
/* 155 */     return this.cachedT;
/*     */   }
/*     */   
/*     */   double[][] getHouseholderVectorsRef() {
/* 165 */     return this.householderVectors;
/*     */   }
/*     */   
/*     */   double[] getMainDiagonalRef() {
/* 175 */     return this.main;
/*     */   }
/*     */   
/*     */   double[] getSecondaryDiagonalRef() {
/* 185 */     return this.secondary;
/*     */   }
/*     */   
/*     */   private void transform() {
/* 193 */     int m = this.householderVectors.length;
/* 194 */     double[] z = new double[m];
/* 195 */     for (int k = 0; k < m - 1; k++) {
/* 198 */       double[] hK = this.householderVectors[k];
/* 199 */       this.main[k] = hK[k];
/* 200 */       double xNormSqr = 0.0D;
/* 201 */       for (int j = k + 1; j < m; j++) {
/* 202 */         double c = hK[j];
/* 203 */         xNormSqr += c * c;
/*     */       } 
/* 205 */       double a = (hK[k + 1] > 0.0D) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
/* 206 */       this.secondary[k] = a;
/* 207 */       if (a != 0.0D) {
/* 210 */         hK[k + 1] = hK[k + 1] - a;
/* 211 */         double beta = -1.0D / a * hK[k + 1];
/* 217 */         Arrays.fill(z, k + 1, m, 0.0D);
/* 218 */         for (int i = k + 1; i < m; i++) {
/* 219 */           double[] hI = this.householderVectors[i];
/* 220 */           double hKI = hK[i];
/* 221 */           double zI = hI[i] * hKI;
/* 222 */           for (int i1 = i + 1; i1 < m; i1++) {
/* 223 */             double hIJ = hI[i1];
/* 224 */             zI += hIJ * hK[i1];
/* 225 */             z[i1] = z[i1] + hIJ * hKI;
/*     */           } 
/* 227 */           z[i] = beta * (z[i] + zI);
/*     */         } 
/* 231 */         double gamma = 0.0D;
/*     */         int n;
/* 232 */         for (n = k + 1; n < m; n++)
/* 233 */           gamma += z[n] * hK[n]; 
/* 235 */         gamma *= beta / 2.0D;
/* 238 */         for (n = k + 1; n < m; n++)
/* 239 */           z[n] = z[n] - gamma * hK[n]; 
/* 244 */         for (n = k + 1; n < m; n++) {
/* 245 */           double[] hI = this.householderVectors[n];
/* 246 */           for (int i1 = n; i1 < m; i1++)
/* 247 */             hI[i1] = hI[i1] - hK[n] * z[i1] + z[n] * hK[i1]; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 252 */     this.main[m - 1] = this.householderVectors[m - 1][m - 1];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\TriDiagonalTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
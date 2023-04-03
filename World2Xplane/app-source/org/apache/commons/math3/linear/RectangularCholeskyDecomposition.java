/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class RectangularCholeskyDecomposition {
/*     */   private final RealMatrix root;
/*     */   
/*     */   private int rank;
/*     */   
/*     */   public RectangularCholeskyDecomposition(RealMatrix matrix, double small) throws NonPositiveDefiniteMatrixException {
/*  65 */     int order = matrix.getRowDimension();
/*  66 */     double[][] c = matrix.getData();
/*  67 */     double[][] b = new double[order][order];
/*  69 */     int[] swap = new int[order];
/*  70 */     int[] index = new int[order];
/*  71 */     for (int i = 0; i < order; i++)
/*  72 */       index[i] = i; 
/*  75 */     int r = 0;
/*     */     boolean loop;
/*  76 */     for (loop = true; loop; ) {
/*  79 */       swap[r] = r;
/*  80 */       for (int k = r + 1; k < order; k++) {
/*  81 */         int ii = index[k];
/*  82 */         int isi = index[swap[k]];
/*  83 */         if (c[ii][ii] > c[isi][isi])
/*  84 */           swap[r] = k; 
/*     */       } 
/*  90 */       if (swap[r] != r) {
/*  91 */         int tmp = index[r];
/*  92 */         index[r] = index[swap[r]];
/*  93 */         index[swap[r]] = tmp;
/*     */       } 
/*  97 */       int ir = index[r];
/*  98 */       if (c[ir][ir] < small) {
/* 100 */         if (r == 0)
/* 101 */           throw new NonPositiveDefiniteMatrixException(c[ir][ir], ir, small); 
/* 105 */         for (int n = r; n < order; n++) {
/* 106 */           if (c[index[n]][index[n]] < -small)
/* 109 */             throw new NonPositiveDefiniteMatrixException(c[index[n]][index[n]], n, small); 
/*     */         } 
/* 115 */         r++;
/* 116 */         loop = false;
/*     */         continue;
/*     */       } 
/* 121 */       double sqrt = FastMath.sqrt(c[ir][ir]);
/* 122 */       b[r][r] = sqrt;
/* 123 */       double inverse = 1.0D / sqrt;
/* 124 */       for (int m = r + 1; m < order; m++) {
/* 125 */         int ii = index[m];
/* 126 */         double e = inverse * c[ii][ir];
/* 127 */         b[m][r] = e;
/* 128 */         c[ii][ii] = c[ii][ii] - e * e;
/* 129 */         for (int n = r + 1; n < m; n++) {
/* 130 */           int ij = index[n];
/* 131 */           double f = c[ii][ij] - e * b[n][r];
/* 132 */           c[ii][ij] = f;
/* 133 */           c[ij][ii] = f;
/*     */         } 
/*     */       } 
/* 138 */       loop = (++r < order);
/*     */     } 
/* 143 */     this.rank = r;
/* 144 */     this.root = MatrixUtils.createRealMatrix(order, r);
/* 145 */     for (int j = 0; j < order; j++) {
/* 146 */       for (int k = 0; k < r; k++)
/* 147 */         this.root.setEntry(index[j], k, b[j][k]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RealMatrix getRootMatrix() {
/* 160 */     return this.root;
/*     */   }
/*     */   
/*     */   public int getRank() {
/* 171 */     return this.rank;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\RectangularCholeskyDecomposition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
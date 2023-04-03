/*     */ package javax.media.jai;
/*     */ 
/*     */ public final class WarpGeneralPolynomial extends WarpPolynomial {
/*     */   public WarpGeneralPolynomial(float[] xCoeffs, float[] yCoeffs, float preScaleX, float preScaleY, float postScaleX, float postScaleY) {
/*  70 */     super(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY);
/*     */   }
/*     */   
/*     */   public WarpGeneralPolynomial(float[] xCoeffs, float[] yCoeffs) {
/*  85 */     this(xCoeffs, yCoeffs, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public float[] warpSparseRect(int x, int y, int width, int height, int periodX, int periodY, float[] destRect) {
/* 116 */     if (destRect == null)
/* 117 */       destRect = new float[2 * (width + periodX - 1) / periodX * (height + periodY - 1) / periodY]; 
/* 123 */     float[] xPows = new float[this.degree + 1];
/* 124 */     float[] yPows = new float[this.degree + 1];
/* 125 */     xPows[0] = 1.0F;
/* 126 */     yPows[0] = 1.0F;
/* 128 */     width += x;
/* 129 */     height += y;
/* 130 */     int index = 0;
/*     */     int j;
/* 132 */     for (j = y; j < height; j += periodY) {
/* 134 */       float y1 = (j + 0.5F) * this.preScaleY;
/* 135 */       for (int n = 1; n <= this.degree; n++)
/* 136 */         yPows[n] = yPows[n - 1] * y1; 
/*     */       int i;
/* 139 */       for (i = x; i < width; i += periodX) {
/* 141 */         float x1 = (i + 0.5F) * this.preScaleX;
/* 142 */         for (int k = 1; k <= this.degree; k++)
/* 143 */           xPows[k] = xPows[k - 1] * x1; 
/* 146 */         float wx = 0.0F;
/* 147 */         float wy = 0.0F;
/* 148 */         int c = 0;
/* 150 */         for (int nx = 0; nx <= this.degree; nx++) {
/* 151 */           for (int ny = 0; ny <= nx; ny++) {
/* 152 */             float t = xPows[nx - ny] * yPows[ny];
/* 153 */             wx += this.xCoeffs[c] * t;
/* 154 */             wy += this.yCoeffs[c] * t;
/* 155 */             c++;
/*     */           } 
/*     */         } 
/* 159 */         destRect[index++] = wx * this.postScaleX - 0.5F;
/* 160 */         destRect[index++] = wy * this.postScaleY - 0.5F;
/*     */       } 
/*     */     } 
/* 164 */     return destRect;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WarpGeneralPolynomial.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
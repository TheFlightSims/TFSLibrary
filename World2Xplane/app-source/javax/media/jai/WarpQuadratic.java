/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public final class WarpQuadratic extends WarpPolynomial {
/*     */   private float c1;
/*     */   
/*     */   private float c2;
/*     */   
/*     */   private float c3;
/*     */   
/*     */   private float c4;
/*     */   
/*     */   private float c5;
/*     */   
/*     */   private float c6;
/*     */   
/*     */   private float c7;
/*     */   
/*     */   private float c8;
/*     */   
/*     */   private float c9;
/*     */   
/*     */   private float c10;
/*     */   
/*     */   private float c11;
/*     */   
/*     */   private float c12;
/*     */   
/*     */   public WarpQuadratic(float[] xCoeffs, float[] yCoeffs, float preScaleX, float preScaleY, float postScaleX, float postScaleY) {
/*  63 */     super(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY);
/*  65 */     if (xCoeffs.length != 6 || yCoeffs.length != 6)
/*  66 */       throw new IllegalArgumentException(JaiI18N.getString("WarpQuadratic0")); 
/*  69 */     this.c1 = xCoeffs[0];
/*  70 */     this.c2 = xCoeffs[1];
/*  71 */     this.c3 = xCoeffs[2];
/*  72 */     this.c4 = xCoeffs[3];
/*  73 */     this.c5 = xCoeffs[4];
/*  74 */     this.c6 = xCoeffs[5];
/*  76 */     this.c7 = yCoeffs[0];
/*  77 */     this.c8 = yCoeffs[1];
/*  78 */     this.c9 = yCoeffs[2];
/*  79 */     this.c10 = yCoeffs[3];
/*  80 */     this.c11 = yCoeffs[4];
/*  81 */     this.c12 = yCoeffs[5];
/*     */   }
/*     */   
/*     */   public WarpQuadratic(float[] xCoeffs, float[] yCoeffs) {
/*  96 */     this(xCoeffs, yCoeffs, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public float[] warpSparseRect(int x, int y, int width, int height, int periodX, int periodY, float[] destRect) {
/* 131 */     if (destRect == null)
/* 132 */       destRect = new float[(width + periodX - 1) / periodX * (height + periodY - 1) / periodY * 2]; 
/* 142 */     float px1 = periodX * this.preScaleX;
/* 143 */     float px2 = px1 * px1;
/* 146 */     float ddx = this.c4 * 2.0F * px2;
/* 147 */     float ddy = this.c10 * 2.0F * px2;
/* 149 */     float x1 = (x + 0.5F) * this.preScaleX;
/* 150 */     float x2 = x1 * x1;
/* 152 */     width += x;
/* 153 */     height += y;
/* 154 */     int index = 0;
/*     */     int j;
/* 156 */     for (j = y; j < height; j += periodY) {
/* 158 */       float y1 = (j + 0.5F) * this.preScaleY;
/* 159 */       float y2 = y1 * y1;
/* 162 */       float wx = this.c1 + this.c2 * x1 + this.c3 * y1 + this.c4 * x2 + this.c5 * x1 * y1 + this.c6 * y2;
/* 164 */       float wy = this.c7 + this.c8 * x1 + this.c9 * y1 + this.c10 * x2 + this.c11 * x1 * y1 + this.c12 * y2;
/* 168 */       float dx = this.c2 * px1 + this.c4 * (2.0F * x1 * px1 + px2) + this.c5 * px1 * y1;
/* 169 */       float dy = this.c8 * px1 + this.c10 * (2.0F * x1 * px1 + px2) + this.c11 * px1 * y1;
/*     */       int i;
/* 171 */       for (i = x; i < width; i += periodX) {
/* 172 */         destRect[index++] = wx * this.postScaleX - 0.5F;
/* 173 */         destRect[index++] = wy * this.postScaleY - 0.5F;
/* 175 */         wx += dx;
/* 176 */         wy += dy;
/* 177 */         dx += ddx;
/* 178 */         dy += ddy;
/*     */       } 
/*     */     } 
/* 182 */     return destRect;
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 218 */     if (destPt == null)
/* 219 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 222 */     double x1 = (destPt.getX() + 0.5D) * this.preScaleX;
/* 223 */     double x2 = x1 * x1;
/* 225 */     double y1 = (destPt.getY() + 0.5D) * this.preScaleY;
/* 226 */     double y2 = y1 * y1;
/* 228 */     double x = this.c1 + this.c2 * x1 + this.c3 * y1 + this.c4 * x2 + this.c5 * x1 * y1 + this.c6 * y2;
/* 229 */     double y = this.c7 + this.c8 * x1 + this.c9 * y1 + this.c10 * x2 + this.c11 * x1 * y1 + this.c12 * y2;
/* 231 */     Point2D pt = (Point2D)destPt.clone();
/* 232 */     pt.setLocation(x * this.postScaleX - 0.5D, y * this.postScaleY - 0.5D);
/* 234 */     return pt;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WarpQuadratic.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
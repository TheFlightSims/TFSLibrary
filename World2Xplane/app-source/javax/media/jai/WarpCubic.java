/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public final class WarpCubic extends WarpPolynomial {
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
/*     */   private float c13;
/*     */   
/*     */   private float c14;
/*     */   
/*     */   private float c15;
/*     */   
/*     */   private float c16;
/*     */   
/*     */   private float c17;
/*     */   
/*     */   private float c18;
/*     */   
/*     */   private float c19;
/*     */   
/*     */   private float c20;
/*     */   
/*     */   public WarpCubic(float[] xCoeffs, float[] yCoeffs, float preScaleX, float preScaleY, float postScaleX, float postScaleY) {
/*  62 */     super(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY);
/*  64 */     if (xCoeffs.length != 10 || yCoeffs.length != 10)
/*  65 */       throw new IllegalArgumentException(JaiI18N.getString("WarpCubic0")); 
/*  68 */     this.c1 = xCoeffs[0];
/*  69 */     this.c2 = xCoeffs[1];
/*  70 */     this.c3 = xCoeffs[2];
/*  71 */     this.c4 = xCoeffs[3];
/*  72 */     this.c5 = xCoeffs[4];
/*  73 */     this.c6 = xCoeffs[5];
/*  74 */     this.c7 = xCoeffs[6];
/*  75 */     this.c8 = xCoeffs[7];
/*  76 */     this.c9 = xCoeffs[8];
/*  77 */     this.c10 = xCoeffs[9];
/*  79 */     this.c11 = yCoeffs[0];
/*  80 */     this.c12 = yCoeffs[1];
/*  81 */     this.c13 = yCoeffs[2];
/*  82 */     this.c14 = yCoeffs[3];
/*  83 */     this.c15 = yCoeffs[4];
/*  84 */     this.c16 = yCoeffs[5];
/*  85 */     this.c17 = yCoeffs[6];
/*  86 */     this.c18 = yCoeffs[7];
/*  87 */     this.c19 = yCoeffs[8];
/*  88 */     this.c20 = yCoeffs[9];
/*     */   }
/*     */   
/*     */   public WarpCubic(float[] xCoeffs, float[] yCoeffs) {
/* 102 */     this(xCoeffs, yCoeffs, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public float[] warpSparseRect(int x, int y, int width, int height, int periodX, int periodY, float[] destRect) {
/* 137 */     if (destRect == null)
/* 138 */       destRect = new float[(width + periodX - 1) / periodX * (height + periodY - 1) / periodY * 2]; 
/* 149 */     float px1 = periodX * this.preScaleX;
/* 150 */     float px2 = px1 * px1;
/* 151 */     float px3 = px2 * px1;
/* 154 */     float dddx = this.c7 * 6.0F * px3;
/* 155 */     float dddy = this.c17 * 6.0F * px3;
/* 157 */     float x1 = (x + 0.5F) * this.preScaleX;
/* 158 */     float x2 = x1 * x1;
/* 159 */     float x3 = x2 * x1;
/* 161 */     width += x;
/* 162 */     height += y;
/* 163 */     int index = 0;
/*     */     int j;
/* 165 */     for (j = y; j < height; j += periodY) {
/* 166 */       float y1 = (j + 0.5F) * this.preScaleY;
/* 167 */       float y2 = y1 * y1;
/* 168 */       float y3 = y2 * y1;
/* 171 */       float wx = this.c1 + this.c2 * x1 + this.c3 * y1 + this.c4 * x2 + this.c5 * x1 * y1 + this.c6 * y2 + this.c7 * x3 + this.c8 * x2 * y1 + this.c9 * x1 * y2 + this.c10 * y3;
/* 174 */       float wy = this.c11 + this.c12 * x1 + this.c13 * y1 + this.c14 * x2 + this.c15 * x1 * y1 + this.c16 * y2 + this.c17 * x3 + this.c18 * x2 * y1 + this.c19 * x1 * y2 + this.c20 * y3;
/* 179 */       float dx = this.c2 * px1 + this.c4 * (2.0F * x1 * px1 + px2) + this.c5 * px1 * y1 + this.c7 * (3.0F * x2 * px1 + 3.0F * x1 * px2 + px3) + this.c8 * (2.0F * x1 * px1 + px2) * y1 + this.c9 * px1 * y2;
/* 185 */       float dy = this.c12 * px1 + this.c14 * (2.0F * x1 * px1 + px2) + this.c15 * px1 * y1 + this.c17 * (3.0F * x2 * px1 + 3.0F * x1 * px2 + px3) + this.c18 * (2.0F * x1 * px1 + px2) * y1 + this.c19 * px1 * y2;
/* 193 */       float ddx = this.c4 * 2.0F * px2 + this.c7 * (6.0F * x1 * px2 + 6.0F * px3) + this.c8 * 2.0F * px2 * y1;
/* 196 */       float ddy = this.c14 * 2.0F * px2 + this.c17 * (6.0F * x1 * px2 + 6.0F * px3) + this.c18 * 2.0F * px2 * y1;
/*     */       int i;
/* 200 */       for (i = x; i < width; i += periodX) {
/* 201 */         destRect[index++] = wx * this.postScaleX - 0.5F;
/* 202 */         destRect[index++] = wy * this.postScaleY - 0.5F;
/* 204 */         wx += dx;
/* 205 */         wy += dy;
/* 206 */         dx += ddx;
/* 207 */         dy += ddy;
/* 208 */         ddx += dddx;
/* 209 */         ddy += dddy;
/*     */       } 
/*     */     } 
/* 213 */     return destRect;
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 255 */     if (destPt == null)
/* 256 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 259 */     double x1 = (destPt.getX() + 0.5D) * this.preScaleX;
/* 260 */     double x2 = x1 * x1;
/* 261 */     double x3 = x2 * x1;
/* 263 */     double y1 = (destPt.getY() + 0.5D) * this.preScaleY;
/* 264 */     double y2 = y1 * y1;
/* 265 */     double y3 = y2 * y1;
/* 267 */     double sx = this.c1 + this.c2 * x1 + this.c3 * y1 + this.c4 * x2 + this.c5 * x1 * y1 + this.c6 * y2 + this.c7 * x3 + this.c8 * x2 * y1 + this.c9 * x1 * y2 + this.c10 * y3;
/* 270 */     double sy = this.c11 + this.c12 * x1 + this.c13 * y1 + this.c14 * x2 + this.c15 * x1 * y1 + this.c16 * y2 + this.c17 * x3 + this.c18 * x2 * y1 + this.c19 * x1 * y2 + this.c20 * y3;
/* 274 */     Point2D pt = (Point2D)destPt.clone();
/* 275 */     pt.setLocation(sx * this.postScaleX - 0.5D, sy * this.postScaleY - 0.5D);
/* 277 */     return pt;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WarpCubic.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
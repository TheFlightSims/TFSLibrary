/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.PolyWarpSolver;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public abstract class WarpPolynomial extends Warp {
/*     */   protected float[] xCoeffs;
/*     */   
/*     */   protected float[] yCoeffs;
/*     */   
/*     */   protected float preScaleX;
/*     */   
/*     */   protected float preScaleY;
/*     */   
/*     */   protected float postScaleX;
/*     */   
/*     */   protected float postScaleY;
/*     */   
/*     */   protected int degree;
/*     */   
/*     */   public WarpPolynomial(float[] xCoeffs, float[] yCoeffs, float preScaleX, float preScaleY, float postScaleX, float postScaleY) {
/* 126 */     if (xCoeffs == null || yCoeffs == null || xCoeffs.length < 1 || yCoeffs.length < 1 || xCoeffs.length != yCoeffs.length)
/* 129 */       throw new IllegalArgumentException(JaiI18N.getString("WarpPolynomial0")); 
/* 133 */     int numCoeffs = xCoeffs.length;
/* 134 */     this.degree = -1;
/* 135 */     while (numCoeffs > 0) {
/* 136 */       this.degree++;
/* 137 */       numCoeffs -= this.degree + 1;
/*     */     } 
/* 139 */     if (numCoeffs != 0)
/* 140 */       throw new IllegalArgumentException(JaiI18N.getString("WarpPolynomial0")); 
/* 144 */     this.xCoeffs = (float[])xCoeffs.clone();
/* 145 */     this.yCoeffs = (float[])yCoeffs.clone();
/* 146 */     this.preScaleX = preScaleX;
/* 147 */     this.preScaleY = preScaleY;
/* 148 */     this.postScaleX = postScaleX;
/* 149 */     this.postScaleY = postScaleY;
/*     */   }
/*     */   
/*     */   public WarpPolynomial(float[] xCoeffs, float[] yCoeffs) {
/* 161 */     this(xCoeffs, yCoeffs, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public float[] getXCoeffs() {
/* 171 */     return (float[])this.xCoeffs.clone();
/*     */   }
/*     */   
/*     */   public float[] getYCoeffs() {
/* 181 */     return (float[])this.yCoeffs.clone();
/*     */   }
/*     */   
/*     */   public float[][] getCoeffs() {
/* 191 */     float[][] coeffs = new float[2][];
/* 192 */     coeffs[0] = (float[])this.xCoeffs.clone();
/* 193 */     coeffs[1] = (float[])this.yCoeffs.clone();
/* 195 */     return coeffs;
/*     */   }
/*     */   
/*     */   public float getPreScaleX() {
/* 200 */     return this.preScaleX;
/*     */   }
/*     */   
/*     */   public float getPreScaleY() {
/* 205 */     return this.preScaleY;
/*     */   }
/*     */   
/*     */   public float getPostScaleX() {
/* 210 */     return this.postScaleX;
/*     */   }
/*     */   
/*     */   public float getPostScaleY() {
/* 215 */     return this.postScaleY;
/*     */   }
/*     */   
/*     */   public int getDegree() {
/* 224 */     return this.degree;
/*     */   }
/*     */   
/*     */   public static WarpPolynomial createWarp(float[] sourceCoords, int sourceOffset, float[] destCoords, int destOffset, int numCoords, float preScaleX, float preScaleY, float postScaleX, float postScaleY, int degree) {
/* 277 */     int minNumPoints = (degree + 1) * (degree + 2);
/* 278 */     if (sourceOffset + minNumPoints > sourceCoords.length || destOffset + minNumPoints > destCoords.length)
/* 281 */       throw new IllegalArgumentException(JaiI18N.getString("WarpPolynomial1")); 
/* 284 */     float[] coeffs = PolyWarpSolver.getCoeffs(sourceCoords, sourceOffset, destCoords, destOffset, numCoords, preScaleX, preScaleY, postScaleX, postScaleY, degree);
/* 291 */     int numCoeffs = coeffs.length / 2;
/* 292 */     float[] xCoeffs = new float[numCoeffs];
/* 293 */     float[] yCoeffs = new float[numCoeffs];
/* 295 */     for (int i = 0; i < numCoeffs; i++) {
/* 296 */       xCoeffs[i] = coeffs[i];
/* 297 */       yCoeffs[i] = coeffs[i + numCoeffs];
/*     */     } 
/* 300 */     if (degree == 1)
/* 301 */       return new WarpAffine(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY); 
/* 304 */     if (degree == 2)
/* 305 */       return new WarpQuadratic(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY); 
/* 308 */     if (degree == 3)
/* 309 */       return new WarpCubic(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY); 
/* 313 */     return new WarpGeneralPolynomial(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY);
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 359 */     if (destPt == null)
/* 360 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 363 */     double dx = (destPt.getX() + 0.5D) * this.preScaleX;
/* 364 */     double dy = (destPt.getY() + 0.5D) * this.preScaleY;
/* 366 */     double sx = 0.0D;
/* 367 */     double sy = 0.0D;
/* 368 */     int c = 0;
/* 370 */     for (int nx = 0; nx <= this.degree; nx++) {
/* 371 */       for (int ny = 0; ny <= nx; ny++) {
/* 372 */         double t = Math.pow(dx, (nx - ny)) * Math.pow(dy, ny);
/* 373 */         sx += this.xCoeffs[c] * t;
/* 374 */         sy += this.yCoeffs[c] * t;
/* 375 */         c++;
/*     */       } 
/*     */     } 
/* 379 */     Point2D pt = (Point2D)destPt.clone();
/* 380 */     pt.setLocation(sx * this.postScaleX - 0.5D, sy * this.postScaleY - 0.5D);
/* 382 */     return pt;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WarpPolynomial.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
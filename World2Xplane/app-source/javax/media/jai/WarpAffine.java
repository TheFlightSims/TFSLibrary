/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public final class WarpAffine extends WarpPolynomial {
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
/*     */   private float invc1;
/*     */   
/*     */   private float invc2;
/*     */   
/*     */   private float invc3;
/*     */   
/*     */   private float invc4;
/*     */   
/*     */   private float invc5;
/*     */   
/*     */   private float invc6;
/*     */   
/*     */   private AffineTransform transform;
/*     */   
/*     */   private AffineTransform invTransform;
/*     */   
/*     */   private static final float[] xCoeffsHelper(AffineTransform transform) {
/*  52 */     float[] coeffs = new float[3];
/*  53 */     coeffs[0] = (float)transform.getTranslateX();
/*  54 */     coeffs[1] = (float)transform.getScaleX();
/*  55 */     coeffs[2] = (float)transform.getShearX();
/*  56 */     return coeffs;
/*     */   }
/*     */   
/*     */   private static final float[] yCoeffsHelper(AffineTransform transform) {
/*  60 */     float[] coeffs = new float[3];
/*  61 */     coeffs[0] = (float)transform.getTranslateY();
/*  62 */     coeffs[1] = (float)transform.getShearY();
/*  63 */     coeffs[2] = (float)transform.getScaleY();
/*  64 */     return coeffs;
/*     */   }
/*     */   
/*     */   public WarpAffine(float[] xCoeffs, float[] yCoeffs, float preScaleX, float preScaleY, float postScaleX, float postScaleY) {
/*  95 */     super(xCoeffs, yCoeffs, preScaleX, preScaleY, postScaleX, postScaleY);
/*  97 */     if (xCoeffs.length != 3 || yCoeffs.length != 3)
/*  98 */       throw new IllegalArgumentException(JaiI18N.getString("WarpAffine0")); 
/* 102 */     this.c1 = xCoeffs[0];
/* 103 */     this.c2 = xCoeffs[1];
/* 104 */     this.c3 = xCoeffs[2];
/* 106 */     this.c4 = yCoeffs[0];
/* 107 */     this.c5 = yCoeffs[1];
/* 108 */     this.c6 = yCoeffs[2];
/* 110 */     this.transform = getTransform();
/*     */     try {
/* 114 */       this.invTransform = this.transform.createInverse();
/* 116 */       this.invc1 = (float)this.invTransform.getTranslateX();
/* 117 */       this.invc2 = (float)this.invTransform.getScaleX();
/* 118 */       this.invc3 = (float)this.invTransform.getShearX();
/* 120 */       this.invc4 = (float)this.invTransform.getTranslateY();
/* 121 */       this.invc5 = (float)this.invTransform.getShearY();
/* 122 */       this.invc6 = (float)this.invTransform.getScaleY();
/* 123 */     } catch (NoninvertibleTransformException e) {
/* 125 */       this.invTransform = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public WarpAffine(float[] xCoeffs, float[] yCoeffs) {
/* 139 */     this(xCoeffs, yCoeffs, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public WarpAffine(AffineTransform transform, float preScaleX, float preScaleY, float postScaleX, float postScaleY) {
/* 157 */     this(xCoeffsHelper(transform), yCoeffsHelper(transform), preScaleX, preScaleY, postScaleX, postScaleY);
/*     */   }
/*     */   
/*     */   public WarpAffine(AffineTransform transform) {
/* 169 */     this(transform, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public AffineTransform getTransform() {
/* 179 */     return new AffineTransform(this.c2, this.c5, this.c3, this.c6, this.c1, this.c4);
/*     */   }
/*     */   
/*     */   public float[] warpSparseRect(int x, int y, int width, int height, int periodX, int periodY, float[] destRect) {
/* 212 */     if (destRect == null)
/* 213 */       destRect = new float[(width + periodX - 1) / periodX * (height + periodY - 1) / periodY * 2]; 
/* 240 */     float px1 = periodX * this.preScaleX;
/* 242 */     float dx = this.c2 * px1 * this.postScaleX;
/* 243 */     float dy = this.c5 * px1 * this.postScaleY;
/* 245 */     float x1 = (x + 0.5F) * this.preScaleX;
/* 247 */     width += x;
/* 248 */     height += y;
/* 249 */     int index = 0;
/*     */     int j;
/* 251 */     for (j = y; j < height; j += periodY) {
/* 252 */       float y1 = (j + 0.5F) * this.preScaleY;
/* 255 */       float wx = (this.c1 + this.c2 * x1 + this.c3 * y1) * this.postScaleX - 0.5F;
/* 256 */       float wy = (this.c4 + this.c5 * x1 + this.c6 * y1) * this.postScaleY - 0.5F;
/*     */       int i;
/* 258 */       for (i = x; i < width; i += periodX) {
/* 259 */         destRect[index++] = wx;
/* 260 */         destRect[index++] = wy;
/* 262 */         wx += dx;
/* 263 */         wy += dy;
/*     */       } 
/*     */     } 
/* 267 */     return destRect;
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect) {
/* 286 */     if (destRect == null)
/* 287 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 290 */     int dx0 = destRect.x;
/* 291 */     int dx1 = destRect.x + destRect.width;
/* 292 */     int dy0 = destRect.y;
/* 293 */     int dy1 = destRect.y + destRect.height;
/* 298 */     float[] pt = mapDestPoint(dx0, dy0);
/* 299 */     float sx0 = pt[0];
/* 300 */     float sx1 = pt[0];
/* 301 */     float sy0 = pt[1];
/* 302 */     float sy1 = pt[1];
/* 304 */     pt = mapDestPoint(dx1, dy0);
/* 305 */     sx0 = Math.min(sx0, pt[0]);
/* 306 */     sx1 = Math.max(sx1, pt[0]);
/* 307 */     sy0 = Math.min(sy0, pt[1]);
/* 308 */     sy1 = Math.max(sy1, pt[1]);
/* 310 */     pt = mapDestPoint(dx0, dy1);
/* 311 */     sx0 = Math.min(sx0, pt[0]);
/* 312 */     sx1 = Math.max(sx1, pt[0]);
/* 313 */     sy0 = Math.min(sy0, pt[1]);
/* 314 */     sy1 = Math.max(sy1, pt[1]);
/* 316 */     pt = mapDestPoint(dx1, dy1);
/* 317 */     sx0 = Math.min(sx0, pt[0]);
/* 318 */     sx1 = Math.max(sx1, pt[0]);
/* 319 */     sy0 = Math.min(sy0, pt[1]);
/* 320 */     sy1 = Math.max(sy1, pt[1]);
/* 322 */     int x = (int)Math.floor(sx0);
/* 323 */     int y = (int)Math.floor(sy0);
/* 324 */     int w = (int)Math.ceil((sx1 - x));
/* 325 */     int h = (int)Math.ceil((sy1 - y));
/* 327 */     return new Rectangle(x, y, w, h);
/*     */   }
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle srcRect) {
/* 346 */     if (srcRect == null)
/* 347 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 354 */     if (this.invTransform == null)
/* 355 */       return null; 
/* 358 */     int sx0 = srcRect.x;
/* 359 */     int sx1 = srcRect.x + srcRect.width;
/* 360 */     int sy0 = srcRect.y;
/* 361 */     int sy1 = srcRect.y + srcRect.height;
/* 366 */     float[] pt = mapSrcPoint(sx0, sy0);
/* 367 */     float dx0 = pt[0];
/* 368 */     float dx1 = pt[0];
/* 369 */     float dy0 = pt[1];
/* 370 */     float dy1 = pt[1];
/* 372 */     pt = mapSrcPoint(sx1, sy0);
/* 373 */     dx0 = Math.min(dx0, pt[0]);
/* 374 */     dx1 = Math.max(dx1, pt[0]);
/* 375 */     dy0 = Math.min(dy0, pt[1]);
/* 376 */     dy1 = Math.max(dy1, pt[1]);
/* 378 */     pt = mapSrcPoint(sx0, sy1);
/* 379 */     dx0 = Math.min(dx0, pt[0]);
/* 380 */     dx1 = Math.max(dx1, pt[0]);
/* 381 */     dy0 = Math.min(dy0, pt[1]);
/* 382 */     dy1 = Math.max(dy1, pt[1]);
/* 384 */     pt = mapSrcPoint(sx1, sy1);
/* 385 */     dx0 = Math.min(dx0, pt[0]);
/* 386 */     dx1 = Math.max(dx1, pt[0]);
/* 387 */     dy0 = Math.min(dy0, pt[1]);
/* 388 */     dy1 = Math.max(dy1, pt[1]);
/* 390 */     int x = (int)Math.floor(dx0);
/* 391 */     int y = (int)Math.floor(dy0);
/* 392 */     int w = (int)Math.ceil((dx1 - x));
/* 393 */     int h = (int)Math.ceil((dy1 - y));
/* 395 */     return new Rectangle(x, y, w, h);
/*     */   }
/*     */   
/*     */   private float[] mapDestPoint(int x, int y) {
/* 408 */     float fx = (x + 0.5F) * this.preScaleX;
/* 409 */     float fy = (y + 0.5F) * this.preScaleY;
/* 411 */     float[] p = new float[2];
/* 412 */     p[0] = (this.c1 + this.c2 * fx + this.c3 * fy) * this.postScaleX - 0.5F;
/* 413 */     p[1] = (this.c4 + this.c5 * fx + this.c6 * fy) * this.postScaleY - 0.5F;
/* 415 */     return p;
/*     */   }
/*     */   
/*     */   private float[] mapSrcPoint(int x, int y) {
/* 428 */     float fx = (x + 0.5F) * this.preScaleX;
/* 429 */     float fy = (y + 0.5F) * this.preScaleY;
/* 431 */     float[] p = new float[2];
/* 432 */     p[0] = (this.invc1 + this.invc2 * fx + this.invc3 * fy) * this.postScaleX - 0.5F;
/* 433 */     p[1] = (this.invc4 + this.invc5 * fx + this.invc6 * fy) * this.postScaleY - 0.5F;
/* 435 */     return p;
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 465 */     if (destPt == null)
/* 466 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 469 */     double dx = (destPt.getX() + 0.5D) * this.preScaleX;
/* 470 */     double dy = (destPt.getY() + 0.5D) * this.preScaleY;
/* 472 */     Point2D pt = (Point2D)destPt.clone();
/* 474 */     pt.setLocation((this.c1 + this.c2 * dx + this.c3 * dy) * this.postScaleX - 0.5D, (this.c4 + this.c5 * dx + this.c6 * dy) * this.postScaleY - 0.5D);
/* 477 */     return pt;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 510 */     if (sourcePt == null)
/* 511 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 514 */     if (this.invTransform == null)
/* 515 */       return null; 
/* 518 */     double sx = (sourcePt.getX() + 0.5D) / this.postScaleX;
/* 519 */     double sy = (sourcePt.getY() + 0.5D) / this.postScaleY;
/* 521 */     Point2D pt = (Point2D)sourcePt.clone();
/* 523 */     pt.setLocation((this.invc1 + this.invc2 * sx + this.invc3 * sy) / this.preScaleX - 0.5D, (this.invc4 + this.invc5 * sx + this.invc6 * sy) / this.preScaleY - 0.5D);
/* 526 */     return pt;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WarpAffine.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
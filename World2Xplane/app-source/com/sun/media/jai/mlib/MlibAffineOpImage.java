/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.GeometricOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.util.ImagingException;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ class MlibAffineOpImage extends GeometricOpImage {
/*     */   protected double[] f_transform;
/*     */   
/*     */   protected double[] m_transform;
/*     */   
/*     */   protected double[] medialib_tr;
/*     */   
/*     */   protected AffineTransform transform;
/*     */   
/*     */   protected AffineTransform i_transform;
/*     */   
/*     */   protected Interpolation interp;
/*     */   
/*     */   private Rectangle srcimg;
/*     */   
/*     */   private Rectangle padimg;
/*     */   
/*     */   protected BorderExtender extender;
/*     */   
/*     */   private Rectangle theDest;
/*     */   
/*     */   private ImagingListener listener;
/*     */   
/*     */   public int lpad;
/*     */   
/*     */   public int rpad;
/*     */   
/*     */   public int tpad;
/*     */   
/*     */   public int bpad;
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, AffineTransform forward_tr) {
/*     */     ImageLayout newLayout;
/*     */     int lx0, ly0;
/*  82 */     if (layout != null) {
/*  83 */       newLayout = (ImageLayout)layout.clone();
/*     */     } else {
/*  85 */       newLayout = new ImageLayout();
/*     */     } 
/*  91 */     float sx0 = source.getMinX();
/*  92 */     float sy0 = source.getMinY();
/*  93 */     float sw = source.getWidth();
/*  94 */     float sh = source.getHeight();
/* 101 */     Point2D[] pts = new Point2D[4];
/* 102 */     pts[0] = new Point2D.Float(sx0, sy0);
/* 103 */     pts[1] = new Point2D.Float(sx0 + sw, sy0);
/* 104 */     pts[2] = new Point2D.Float(sx0 + sw, sy0 + sh);
/* 105 */     pts[3] = new Point2D.Float(sx0, sy0 + sh);
/* 108 */     forward_tr.transform(pts, 0, pts, 0, 4);
/* 110 */     float dx0 = Float.MAX_VALUE;
/* 111 */     float dy0 = Float.MAX_VALUE;
/* 112 */     float dx1 = -3.4028235E38F;
/* 113 */     float dy1 = -3.4028235E38F;
/* 114 */     for (int i = 0; i < 4; i++) {
/* 115 */       float px = (float)pts[i].getX();
/* 116 */       float py = (float)pts[i].getY();
/* 118 */       dx0 = Math.min(dx0, px);
/* 119 */       dy0 = Math.min(dy0, py);
/* 120 */       dx1 = Math.max(dx1, px);
/* 121 */       dy1 = Math.max(dy1, py);
/*     */     } 
/* 128 */     int lw = (int)(dx1 - dx0);
/* 129 */     int lh = (int)(dy1 - dy0);
/* 139 */     int i_dx0 = (int)Math.floor(dx0);
/* 140 */     if (Math.abs(dx0 - i_dx0) <= 0.5D) {
/* 141 */       lx0 = i_dx0;
/*     */     } else {
/* 143 */       lx0 = (int)Math.ceil(dx0);
/*     */     } 
/* 146 */     int i_dy0 = (int)Math.floor(dy0);
/* 147 */     if (Math.abs(dy0 - i_dy0) <= 0.5D) {
/* 148 */       ly0 = i_dy0;
/*     */     } else {
/* 150 */       ly0 = (int)Math.ceil(dy0);
/*     */     } 
/* 156 */     newLayout.setMinX(lx0);
/* 157 */     newLayout.setMinY(ly0);
/* 158 */     newLayout.setWidth(lw);
/* 159 */     newLayout.setHeight(lh);
/* 161 */     return newLayout;
/*     */   }
/*     */   
/*     */   public MlibAffineOpImage(RenderedImage source, ImageLayout layout, Map config, BorderExtender extender, AffineTransform transform, Interpolation interp, double[] backgroundValues) {
/* 186 */     super(vectorize(source), layoutHelper(layout, source, transform), config, true, extender, interp, backgroundValues);
/* 197 */     this.interp = interp;
/* 200 */     this.extender = extender;
/* 203 */     this.listener = ImageUtil.getImagingListener((RenderingHints)config);
/* 206 */     this.lpad = interp.getLeftPadding();
/* 207 */     this.rpad = interp.getRightPadding();
/* 208 */     this.tpad = interp.getTopPadding();
/* 209 */     this.bpad = interp.getBottomPadding();
/* 215 */     this.srcimg = new Rectangle(getSourceImage(0).getMinX(), getSourceImage(0).getMinY(), getSourceImage(0).getWidth(), getSourceImage(0).getHeight());
/* 219 */     this.padimg = new Rectangle(this.srcimg.x - this.lpad, this.srcimg.y - this.tpad, this.srcimg.width + this.lpad + this.rpad, this.srcimg.height + this.tpad + this.bpad);
/* 224 */     if (extender == null) {
/* 234 */       float sx0 = this.srcimg.x;
/* 235 */       float sy0 = this.srcimg.y;
/* 236 */       float sw = this.srcimg.width;
/* 237 */       float sh = this.srcimg.height;
/* 242 */       float f_lpad = this.lpad;
/* 243 */       float f_rpad = this.rpad;
/* 244 */       float f_tpad = this.tpad;
/* 245 */       float f_bpad = this.bpad;
/* 250 */       if (interp instanceof javax.media.jai.InterpolationBilinear || interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2) {
/* 253 */         f_lpad = (float)(f_lpad + 0.5D);
/* 254 */         f_tpad = (float)(f_tpad + 0.5D);
/* 255 */         f_rpad = (float)(f_rpad + 0.5D);
/* 256 */         f_bpad = (float)(f_bpad + 0.5D);
/*     */       } 
/* 263 */       sx0 += f_lpad;
/* 264 */       sy0 += f_tpad;
/* 265 */       sw -= f_lpad + f_rpad;
/* 266 */       sh -= f_tpad + f_bpad;
/* 272 */       Point2D[] pts = new Point2D[4];
/* 273 */       pts[0] = new Point2D.Float(sx0, sy0);
/* 274 */       pts[1] = new Point2D.Float(sx0 + sw, sy0);
/* 275 */       pts[2] = new Point2D.Float(sx0 + sw, sy0 + sh);
/* 276 */       pts[3] = new Point2D.Float(sx0, sy0 + sh);
/* 279 */       transform.transform(pts, 0, pts, 0, 4);
/* 281 */       float dx0 = Float.MAX_VALUE;
/* 282 */       float dy0 = Float.MAX_VALUE;
/* 283 */       float dx1 = -3.4028235E38F;
/* 284 */       float dy1 = -3.4028235E38F;
/* 285 */       for (int i = 0; i < 4; i++) {
/* 286 */         float px = (float)pts[i].getX();
/* 287 */         float py = (float)pts[i].getY();
/* 289 */         dx0 = Math.min(dx0, px);
/* 290 */         dy0 = Math.min(dy0, py);
/* 291 */         dx1 = Math.max(dx1, px);
/* 292 */         dy1 = Math.max(dy1, py);
/*     */       } 
/* 302 */       int lx0 = (int)Math.ceil(dx0);
/* 303 */       int ly0 = (int)Math.ceil(dy0);
/* 304 */       int lx1 = (int)Math.floor(dx1);
/* 305 */       int ly1 = (int)Math.floor(dy1);
/* 307 */       this.theDest = new Rectangle(lx0, ly0, lx1 - lx0, ly1 - ly0);
/*     */     } else {
/* 312 */       this.theDest = getBounds();
/*     */     } 
/*     */     try {
/* 317 */       this.i_transform = transform.createInverse();
/* 318 */     } catch (NoninvertibleTransformException e) {
/* 319 */       String message = JaiI18N.getString("MlibAffineOpImage0");
/* 320 */       this.listener.errorOccurred(message, (Throwable)new ImagingException(message, e), this, false);
/*     */     } 
/* 326 */     this.transform = (AffineTransform)transform.clone();
/* 333 */     this.f_transform = new double[6];
/* 334 */     transform.getMatrix(this.f_transform);
/* 341 */     this.medialib_tr = new double[6];
/* 342 */     this.medialib_tr[0] = this.f_transform[0];
/* 343 */     this.medialib_tr[1] = this.f_transform[2];
/* 344 */     this.medialib_tr[2] = this.f_transform[4];
/* 345 */     this.medialib_tr[3] = this.f_transform[1];
/* 346 */     this.medialib_tr[4] = this.f_transform[3];
/* 347 */     this.medialib_tr[5] = this.f_transform[5];
/* 352 */     this.m_transform = new double[6];
/* 353 */     this.m_transform[0] = this.f_transform[0];
/* 354 */     this.m_transform[1] = this.f_transform[2];
/* 355 */     this.m_transform[2] = this.f_transform[4];
/* 356 */     this.m_transform[3] = this.f_transform[1];
/* 357 */     this.m_transform[4] = this.f_transform[3];
/* 358 */     this.m_transform[5] = this.f_transform[5];
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 377 */     if (destPt == null)
/* 378 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 381 */     Point2D dpt = (Point2D)destPt.clone();
/* 382 */     dpt.setLocation(dpt.getX() + 0.5D, dpt.getY() + 0.5D);
/* 384 */     Point2D spt = this.i_transform.transform(dpt, null);
/* 385 */     spt.setLocation(spt.getX() - 0.5D, spt.getY() - 0.5D);
/* 387 */     return spt;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 405 */     if (sourcePt == null)
/* 406 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 409 */     Point2D spt = (Point2D)sourcePt.clone();
/* 410 */     spt.setLocation(spt.getX() + 0.5D, spt.getY() + 0.5D);
/* 412 */     Point2D dpt = this.transform.transform(spt, null);
/* 413 */     dpt.setLocation(dpt.getX() - 0.5D, dpt.getY() - 0.5D);
/* 415 */     return dpt;
/*     */   }
/*     */   
/*     */   protected Rectangle forwardMapRect(Rectangle sourceRect, int sourceIndex) {
/* 423 */     return this.transform.createTransformedShape(sourceRect).getBounds();
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 435 */     float dx0 = destRect.x;
/* 436 */     float dy0 = destRect.y;
/* 437 */     float dw = destRect.width;
/* 438 */     float dh = destRect.height;
/* 440 */     Point2D[] pts = new Point2D[4];
/* 441 */     pts[0] = new Point2D.Float(dx0, dy0);
/* 442 */     pts[1] = new Point2D.Float(dx0 + dw, dy0);
/* 443 */     pts[2] = new Point2D.Float(dx0 + dw, dy0 + dh);
/* 444 */     pts[3] = new Point2D.Float(dx0, dy0 + dh);
/* 446 */     this.i_transform.transform(pts, 0, pts, 0, 4);
/* 448 */     float f_sx0 = Float.MAX_VALUE;
/* 449 */     float f_sy0 = Float.MAX_VALUE;
/* 450 */     float f_sx1 = -3.4028235E38F;
/* 451 */     float f_sy1 = -3.4028235E38F;
/* 452 */     for (int i = 0; i < 4; i++) {
/* 453 */       float px = (float)pts[i].getX();
/* 454 */       float py = (float)pts[i].getY();
/* 456 */       f_sx0 = Math.min(f_sx0, px);
/* 457 */       f_sy0 = Math.min(f_sy0, py);
/* 458 */       f_sx1 = Math.max(f_sx1, px);
/* 459 */       f_sy1 = Math.max(f_sy1, py);
/*     */     } 
/* 462 */     int s_x0 = 0, s_y0 = 0, s_x1 = 0, s_y1 = 0;
/* 465 */     if (this.interp instanceof javax.media.jai.InterpolationNearest) {
/* 466 */       s_x0 = (int)Math.floor(f_sx0);
/* 467 */       s_y0 = (int)Math.floor(f_sy0);
/* 468 */       s_x1 = (int)Math.ceil(f_sx1);
/* 469 */       s_y1 = (int)Math.ceil(f_sy1);
/*     */     } else {
/* 471 */       s_x0 = (int)Math.floor(f_sx0 - 0.5D);
/* 472 */       s_y0 = (int)Math.floor(f_sy0 - 0.5D);
/* 473 */       s_x1 = (int)Math.ceil(f_sx1);
/* 474 */       s_y1 = (int)Math.ceil(f_sy1);
/*     */     } 
/* 480 */     return new Rectangle(s_x0, s_y0, s_x1 - s_x0, s_y1 - s_y0);
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 493 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 494 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 499 */     Rectangle rect = new Rectangle(org.x, org.y, this.tileWidth, this.tileHeight);
/* 509 */     Rectangle destRect = rect.intersection(this.theDest);
/* 510 */     Rectangle destRect1 = rect.intersection(getBounds());
/* 511 */     if (destRect.width <= 0 || destRect.height <= 0) {
/* 512 */       if (this.setBackground)
/* 513 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 516 */       return dest;
/*     */     } 
/* 522 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 523 */     if (this.extender == null) {
/* 524 */       srcRect = srcRect.intersection(this.srcimg);
/*     */     } else {
/* 526 */       srcRect = srcRect.intersection(this.padimg);
/*     */     } 
/* 529 */     if (srcRect.width <= 0 || srcRect.height <= 0) {
/* 531 */       if (this.setBackground)
/* 532 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 534 */       return dest;
/*     */     } 
/* 537 */     if (!destRect1.equals(destRect))
/* 539 */       ImageUtil.fillBordersWithBackgroundValues(destRect1, destRect, dest, this.backgroundValues); 
/* 542 */     Raster[] sources = new Raster[1];
/* 545 */     if (this.extender == null) {
/* 546 */       sources[0] = getSourceImage(0).getData(srcRect);
/*     */     } else {
/* 548 */       sources[0] = getSourceImage(0).getExtendedData(srcRect, this.extender);
/*     */     } 
/* 551 */     computeRect(sources, dest, destRect);
/* 554 */     if (getSourceImage(0).overlapsMultipleTiles(srcRect))
/* 555 */       recycleTile(sources[0]); 
/* 558 */     return dest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibAffineOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
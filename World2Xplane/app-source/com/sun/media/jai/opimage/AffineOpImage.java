/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
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
/*     */ class AffineOpImage extends GeometricOpImage {
/*     */   protected static final int USHORT_MAX = 65535;
/*     */   
/*     */   protected AffineTransform f_transform;
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
/*     */   protected static final int geom_frac_max = 1048576;
/*     */   
/*     */   double m00;
/*     */   
/*     */   double m10;
/*     */   
/*     */   double flr_m00;
/*     */   
/*     */   double flr_m10;
/*     */   
/*     */   double fracdx;
/*     */   
/*     */   double fracdx1;
/*     */   
/*     */   double fracdy;
/*     */   
/*     */   double fracdy1;
/*     */   
/*     */   int incx;
/*     */   
/*     */   int incx1;
/*     */   
/*     */   int incy;
/*     */   
/*     */   int incy1;
/*     */   
/*     */   int ifracdx;
/*     */   
/*     */   int ifracdx1;
/*     */   
/*     */   int ifracdy;
/*     */   
/*     */   int ifracdy1;
/*     */   
/*     */   public int lpad;
/*     */   
/*     */   public int rpad;
/*     */   
/*     */   public int tpad;
/*     */   
/*     */   public int bpad;
/*     */   
/*     */   protected static int floorRatio(long num, long denom) {
/* 112 */     if (denom < 0L) {
/* 113 */       denom = -denom;
/* 114 */       num = -num;
/*     */     } 
/* 117 */     if (num >= 0L)
/* 118 */       return (int)(num / denom); 
/* 120 */     return (int)((num - denom + 1L) / denom);
/*     */   }
/*     */   
/*     */   protected static int ceilRatio(long num, long denom) {
/* 129 */     if (denom < 0L) {
/* 130 */       denom = -denom;
/* 131 */       num = -num;
/*     */     } 
/* 134 */     if (num >= 0L)
/* 135 */       return (int)((num + denom - 1L) / denom); 
/* 137 */     return (int)(num / denom);
/*     */   }
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, AffineTransform forward_tr) {
/*     */     ImageLayout newLayout;
/*     */     int lx0, ly0;
/* 146 */     if (layout != null) {
/* 147 */       newLayout = (ImageLayout)layout.clone();
/*     */     } else {
/* 149 */       newLayout = new ImageLayout();
/*     */     } 
/* 155 */     float sx0 = source.getMinX();
/* 156 */     float sy0 = source.getMinY();
/* 157 */     float sw = source.getWidth();
/* 158 */     float sh = source.getHeight();
/* 165 */     Point2D[] pts = new Point2D[4];
/* 166 */     pts[0] = new Point2D.Float(sx0, sy0);
/* 167 */     pts[1] = new Point2D.Float(sx0 + sw, sy0);
/* 168 */     pts[2] = new Point2D.Float(sx0 + sw, sy0 + sh);
/* 169 */     pts[3] = new Point2D.Float(sx0, sy0 + sh);
/* 172 */     forward_tr.transform(pts, 0, pts, 0, 4);
/* 174 */     float dx0 = Float.MAX_VALUE;
/* 175 */     float dy0 = Float.MAX_VALUE;
/* 176 */     float dx1 = -3.4028235E38F;
/* 177 */     float dy1 = -3.4028235E38F;
/* 178 */     for (int i = 0; i < 4; i++) {
/* 179 */       float px = (float)pts[i].getX();
/* 180 */       float py = (float)pts[i].getY();
/* 182 */       dx0 = Math.min(dx0, px);
/* 183 */       dy0 = Math.min(dy0, py);
/* 184 */       dx1 = Math.max(dx1, px);
/* 185 */       dy1 = Math.max(dy1, py);
/*     */     } 
/* 192 */     int lw = (int)(dx1 - dx0);
/* 193 */     int lh = (int)(dy1 - dy0);
/* 203 */     int i_dx0 = (int)Math.floor(dx0);
/* 204 */     if (Math.abs(dx0 - i_dx0) <= 0.5D) {
/* 205 */       lx0 = i_dx0;
/*     */     } else {
/* 207 */       lx0 = (int)Math.ceil(dx0);
/*     */     } 
/* 210 */     int i_dy0 = (int)Math.floor(dy0);
/* 211 */     if (Math.abs(dy0 - i_dy0) <= 0.5D) {
/* 212 */       ly0 = i_dy0;
/*     */     } else {
/* 214 */       ly0 = (int)Math.ceil(dy0);
/*     */     } 
/* 220 */     newLayout.setMinX(lx0);
/* 221 */     newLayout.setMinY(ly0);
/* 222 */     newLayout.setWidth(lw);
/* 223 */     newLayout.setHeight(lh);
/* 225 */     return newLayout;
/*     */   }
/*     */   
/*     */   public AffineOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform transform, Interpolation interp, double[] backgroundValues) {
/* 250 */     super(vectorize(source), layoutHelper(layout, source, transform), config, true, extender, interp, backgroundValues);
/* 260 */     this.listener = ImageUtil.getImagingListener((RenderingHints)config);
/* 263 */     this.interp = interp;
/* 266 */     this.extender = extender;
/* 269 */     this.lpad = interp.getLeftPadding();
/* 270 */     this.rpad = interp.getRightPadding();
/* 271 */     this.tpad = interp.getTopPadding();
/* 272 */     this.bpad = interp.getBottomPadding();
/* 278 */     this.srcimg = new Rectangle(getSourceImage(0).getMinX(), getSourceImage(0).getMinY(), getSourceImage(0).getWidth(), getSourceImage(0).getHeight());
/* 282 */     this.padimg = new Rectangle(this.srcimg.x - this.lpad, this.srcimg.y - this.tpad, this.srcimg.width + this.lpad + this.rpad, this.srcimg.height + this.tpad + this.bpad);
/* 287 */     if (extender == null) {
/* 297 */       float sx0 = this.srcimg.x;
/* 298 */       float sy0 = this.srcimg.y;
/* 299 */       float sw = this.srcimg.width;
/* 300 */       float sh = this.srcimg.height;
/* 305 */       float f_lpad = this.lpad;
/* 306 */       float f_rpad = this.rpad;
/* 307 */       float f_tpad = this.tpad;
/* 308 */       float f_bpad = this.bpad;
/* 313 */       if (!(interp instanceof javax.media.jai.InterpolationNearest)) {
/* 314 */         f_lpad = (float)(f_lpad + 0.5D);
/* 315 */         f_tpad = (float)(f_tpad + 0.5D);
/* 316 */         f_rpad = (float)(f_rpad + 0.5D);
/* 317 */         f_bpad = (float)(f_bpad + 0.5D);
/*     */       } 
/* 324 */       sx0 += f_lpad;
/* 325 */       sy0 += f_tpad;
/* 326 */       sw -= f_lpad + f_rpad;
/* 327 */       sh -= f_tpad + f_bpad;
/* 333 */       Point2D[] pts = new Point2D[4];
/* 334 */       pts[0] = new Point2D.Float(sx0, sy0);
/* 335 */       pts[1] = new Point2D.Float(sx0 + sw, sy0);
/* 336 */       pts[2] = new Point2D.Float(sx0 + sw, sy0 + sh);
/* 337 */       pts[3] = new Point2D.Float(sx0, sy0 + sh);
/* 340 */       transform.transform(pts, 0, pts, 0, 4);
/* 342 */       float dx0 = Float.MAX_VALUE;
/* 343 */       float dy0 = Float.MAX_VALUE;
/* 344 */       float dx1 = -3.4028235E38F;
/* 345 */       float dy1 = -3.4028235E38F;
/* 346 */       for (int i = 0; i < 4; i++) {
/* 347 */         float px = (float)pts[i].getX();
/* 348 */         float py = (float)pts[i].getY();
/* 350 */         dx0 = Math.min(dx0, px);
/* 351 */         dy0 = Math.min(dy0, py);
/* 352 */         dx1 = Math.max(dx1, px);
/* 353 */         dy1 = Math.max(dy1, py);
/*     */       } 
/* 363 */       int lx0 = (int)Math.ceil(dx0);
/* 364 */       int ly0 = (int)Math.ceil(dy0);
/* 365 */       int lx1 = (int)Math.floor(dx1);
/* 366 */       int ly1 = (int)Math.floor(dy1);
/* 368 */       this.theDest = new Rectangle(lx0, ly0, lx1 - lx0, ly1 - ly0);
/*     */     } else {
/* 373 */       this.theDest = getBounds();
/*     */     } 
/*     */     try {
/* 378 */       this.i_transform = transform.createInverse();
/* 379 */     } catch (Exception e) {
/* 380 */       String message = JaiI18N.getString("AffineOpImage0");
/* 381 */       this.listener.errorOccurred(message, (Throwable)new ImagingException(message, e), this, false);
/*     */     } 
/* 386 */     this.f_transform = (AffineTransform)transform.clone();
/* 391 */     this.m00 = this.i_transform.getScaleX();
/* 392 */     this.flr_m00 = Math.floor(this.m00);
/* 393 */     this.fracdx = this.m00 - this.flr_m00;
/* 394 */     this.fracdx1 = 1.0D - this.fracdx;
/* 395 */     this.incx = (int)this.flr_m00;
/* 396 */     this.incx1 = this.incx + 1;
/* 397 */     this.ifracdx = (int)Math.round(this.fracdx * 1048576.0D);
/* 398 */     this.ifracdx1 = 1048576 - this.ifracdx;
/* 400 */     this.m10 = this.i_transform.getShearY();
/* 401 */     this.flr_m10 = Math.floor(this.m10);
/* 402 */     this.fracdy = this.m10 - this.flr_m10;
/* 403 */     this.fracdy1 = 1.0D - this.fracdy;
/* 404 */     this.incy = (int)this.flr_m10;
/* 405 */     this.incy1 = this.incy + 1;
/* 406 */     this.ifracdy = (int)Math.round(this.fracdy * 1048576.0D);
/* 407 */     this.ifracdy1 = 1048576 - this.ifracdy;
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 425 */     if (destPt == null)
/* 426 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 429 */     Point2D dpt = (Point2D)destPt.clone();
/* 430 */     dpt.setLocation(dpt.getX() + 0.5D, dpt.getY() + 0.5D);
/* 432 */     Point2D spt = this.i_transform.transform(dpt, null);
/* 433 */     spt.setLocation(spt.getX() - 0.5D, spt.getY() - 0.5D);
/* 435 */     return spt;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 453 */     if (sourcePt == null)
/* 454 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 457 */     Point2D spt = (Point2D)sourcePt.clone();
/* 458 */     spt.setLocation(spt.getX() + 0.5D, spt.getY() + 0.5D);
/* 460 */     Point2D dpt = this.f_transform.transform(spt, null);
/* 461 */     dpt.setLocation(dpt.getX() - 0.5D, dpt.getY() - 0.5D);
/* 463 */     return dpt;
/*     */   }
/*     */   
/*     */   protected Rectangle forwardMapRect(Rectangle sourceRect, int sourceIndex) {
/* 471 */     return this.f_transform.createTransformedShape(sourceRect).getBounds();
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 483 */     float dx0 = destRect.x;
/* 484 */     float dy0 = destRect.y;
/* 485 */     float dw = destRect.width;
/* 486 */     float dh = destRect.height;
/* 488 */     Point2D[] pts = new Point2D[4];
/* 489 */     pts[0] = new Point2D.Float(dx0, dy0);
/* 490 */     pts[1] = new Point2D.Float(dx0 + dw, dy0);
/* 491 */     pts[2] = new Point2D.Float(dx0 + dw, dy0 + dh);
/* 492 */     pts[3] = new Point2D.Float(dx0, dy0 + dh);
/* 494 */     this.i_transform.transform(pts, 0, pts, 0, 4);
/* 496 */     float f_sx0 = Float.MAX_VALUE;
/* 497 */     float f_sy0 = Float.MAX_VALUE;
/* 498 */     float f_sx1 = -3.4028235E38F;
/* 499 */     float f_sy1 = -3.4028235E38F;
/* 500 */     for (int i = 0; i < 4; i++) {
/* 501 */       float px = (float)pts[i].getX();
/* 502 */       float py = (float)pts[i].getY();
/* 504 */       f_sx0 = Math.min(f_sx0, px);
/* 505 */       f_sy0 = Math.min(f_sy0, py);
/* 506 */       f_sx1 = Math.max(f_sx1, px);
/* 507 */       f_sy1 = Math.max(f_sy1, py);
/*     */     } 
/* 510 */     int s_x0 = 0, s_y0 = 0, s_x1 = 0, s_y1 = 0;
/* 513 */     if (this.interp instanceof javax.media.jai.InterpolationNearest) {
/* 514 */       s_x0 = (int)Math.floor(f_sx0);
/* 515 */       s_y0 = (int)Math.floor(f_sy0);
/* 521 */       s_x1 = (int)Math.ceil(f_sx1 + 0.5D);
/* 522 */       s_y1 = (int)Math.ceil(f_sy1 + 0.5D);
/*     */     } else {
/* 524 */       s_x0 = (int)Math.floor(f_sx0 - 0.5D);
/* 525 */       s_y0 = (int)Math.floor(f_sy0 - 0.5D);
/* 526 */       s_x1 = (int)Math.ceil(f_sx1);
/* 527 */       s_y1 = (int)Math.ceil(f_sy1);
/*     */     } 
/* 533 */     return new Rectangle(s_x0, s_y0, s_x1 - s_x0, s_y1 - s_y0);
/*     */   }
/*     */   
/*     */   public void mapDestPoint(Point2D destPoint, Point2D srcPoint) {
/* 548 */     this.i_transform.transform(destPoint, srcPoint);
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 555 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 556 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 561 */     Rectangle rect = new Rectangle(org.x, org.y, this.tileWidth, this.tileHeight);
/* 571 */     Rectangle destRect = rect.intersection(this.theDest);
/* 572 */     Rectangle destRect1 = rect.intersection(getBounds());
/* 573 */     if (destRect.width <= 0 || destRect.height <= 0) {
/* 575 */       if (this.setBackground)
/* 576 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 578 */       return dest;
/*     */     } 
/* 584 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 585 */     if (this.extender == null) {
/* 586 */       srcRect = srcRect.intersection(this.srcimg);
/*     */     } else {
/* 588 */       srcRect = srcRect.intersection(this.padimg);
/*     */     } 
/* 591 */     if (srcRect.width <= 0 || srcRect.height <= 0) {
/* 592 */       if (this.setBackground)
/* 593 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 595 */       return dest;
/*     */     } 
/* 599 */     if (!destRect1.equals(destRect))
/* 601 */       ImageUtil.fillBordersWithBackgroundValues(destRect1, destRect, dest, this.backgroundValues); 
/* 605 */     Raster[] sources = new Raster[1];
/* 608 */     if (this.extender == null) {
/* 609 */       sources[0] = getSourceImage(0).getData(srcRect);
/*     */     } else {
/* 611 */       sources[0] = getSourceImage(0).getExtendedData(srcRect, this.extender);
/*     */     } 
/* 615 */     computeRect(sources, dest, destRect);
/* 618 */     if (getSourceImage(0).overlapsMultipleTiles(srcRect))
/* 619 */       recycleTile(sources[0]); 
/* 622 */     return dest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AffineOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
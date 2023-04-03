/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class WarpOpImage extends GeometricOpImage {
/*     */   protected Warp warp;
/*     */   
/*     */   private static ImageLayout getLayout(ImageLayout layout, RenderedImage source, Warp warp) {
/*  77 */     if (layout != null && layout.isValid(15))
/*  82 */       return layout; 
/*  86 */     Rectangle sourceBounds = new Rectangle(source.getMinX(), source.getMinY(), source.getWidth(), source.getHeight());
/*  91 */     Rectangle destBounds = warp.mapSourceRect(sourceBounds);
/*  94 */     if (destBounds == null) {
/*  95 */       Point[] srcPts = { new Point(sourceBounds.x, sourceBounds.y), new Point(sourceBounds.x + sourceBounds.width, sourceBounds.y), new Point(sourceBounds.x, sourceBounds.y + sourceBounds.height), new Point(sourceBounds.x + sourceBounds.width, sourceBounds.y + sourceBounds.height) };
/* 105 */       boolean verticesMapped = true;
/* 107 */       double xMin = Double.MAX_VALUE;
/* 108 */       double xMax = -1.7976931348623157E308D;
/* 109 */       double yMin = Double.MAX_VALUE;
/* 110 */       double yMax = -1.7976931348623157E308D;
/* 112 */       for (int i = 0; i < 4; i++) {
/* 113 */         Point2D destPt = warp.mapSourcePoint(srcPts[i]);
/* 114 */         if (destPt == null) {
/* 115 */           verticesMapped = false;
/*     */           break;
/*     */         } 
/* 119 */         double x = destPt.getX();
/* 120 */         double y = destPt.getY();
/* 121 */         if (x < xMin)
/* 122 */           xMin = x; 
/* 124 */         if (x > xMax)
/* 125 */           xMax = x; 
/* 127 */         if (y < yMin)
/* 128 */           yMin = y; 
/* 130 */         if (y > yMax)
/* 131 */           yMax = y; 
/*     */       } 
/* 136 */       if (verticesMapped) {
/* 137 */         destBounds = new Rectangle();
/* 138 */         destBounds.x = (int)Math.floor(xMin);
/* 139 */         destBounds.y = (int)Math.floor(yMin);
/* 140 */         destBounds.width = (int)Math.ceil(xMax - destBounds.x);
/* 141 */         destBounds.height = (int)Math.ceil(yMax - destBounds.y);
/*     */       } 
/*     */     } 
/* 149 */     if (destBounds == null && !(warp instanceof WarpAffine)) {
/* 150 */       Point[] destPts = { new Point(sourceBounds.x, sourceBounds.y), new Point(sourceBounds.x + sourceBounds.width, sourceBounds.y), new Point(sourceBounds.x, sourceBounds.y + sourceBounds.height), new Point(sourceBounds.x + sourceBounds.width, sourceBounds.y + sourceBounds.height) };
/* 160 */       float[] sourceCoords = new float[8];
/* 161 */       float[] destCoords = new float[8];
/* 162 */       int offset = 0;
/* 164 */       for (int i = 0; i < 4; i++) {
/* 165 */         Point2D dstPt = destPts[i];
/* 166 */         Point2D srcPt = warp.mapDestPoint(destPts[i]);
/* 167 */         destCoords[offset] = (float)dstPt.getX();
/* 168 */         destCoords[offset + 1] = (float)dstPt.getY();
/* 169 */         sourceCoords[offset] = (float)srcPt.getX();
/* 170 */         sourceCoords[offset + 1] = (float)srcPt.getY();
/* 171 */         offset += 2;
/*     */       } 
/* 175 */       WarpAffine wa = (WarpAffine)WarpPolynomial.createWarp(sourceCoords, 0, destCoords, 0, 8, 1.0F, 1.0F, 1.0F, 1.0F, 1);
/* 183 */       destBounds = wa.mapSourceRect(sourceBounds);
/*     */     } 
/* 188 */     if (destBounds != null)
/* 189 */       if (layout == null) {
/* 190 */         layout = new ImageLayout(destBounds.x, destBounds.y, destBounds.width, destBounds.height);
/*     */       } else {
/* 193 */         layout = (ImageLayout)layout.clone();
/* 194 */         layout.setMinX(destBounds.x);
/* 195 */         layout.setMinY(destBounds.y);
/* 196 */         layout.setWidth(destBounds.width);
/* 197 */         layout.setHeight(destBounds.height);
/*     */       }  
/* 201 */     return layout;
/*     */   }
/*     */   
/*     */   public WarpOpImage(RenderedImage source, ImageLayout layout, Map configuration, boolean cobbleSources, BorderExtender extender, Interpolation interp, Warp warp) {
/* 244 */     this(source, layout, configuration, cobbleSources, extender, interp, warp, (double[])null);
/*     */   }
/*     */   
/*     */   public WarpOpImage(RenderedImage source, ImageLayout layout, Map configuration, boolean cobbleSources, BorderExtender extender, Interpolation interp, Warp warp, double[] backgroundValues) {
/* 301 */     super(vectorize(source), getLayout(layout, source, warp), configuration, cobbleSources, extender, interp, backgroundValues);
/* 309 */     if (warp == null)
/* 310 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 313 */     this.warp = warp;
/* 315 */     if (cobbleSources && extender == null) {
/* 318 */       int l = (interp == null) ? 0 : interp.getLeftPadding();
/* 319 */       int r = (interp == null) ? 0 : interp.getRightPadding();
/* 320 */       int t = (interp == null) ? 0 : interp.getTopPadding();
/* 321 */       int b = (interp == null) ? 0 : interp.getBottomPadding();
/* 323 */       int x = getMinX() + l;
/* 324 */       int y = getMinY() + t;
/* 325 */       int w = Math.max(getWidth() - l - r, 0);
/* 326 */       int h = Math.max(getHeight() - t - b, 0);
/* 328 */       this.computableBounds = new Rectangle(x, y, w, h);
/*     */     } else {
/* 332 */       this.computableBounds = getBounds();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getLeftPadding() {
/* 344 */     return (this.interp == null) ? 0 : this.interp.getLeftPadding();
/*     */   }
/*     */   
/*     */   public int getRightPadding() {
/* 355 */     return (this.interp == null) ? 0 : this.interp.getRightPadding();
/*     */   }
/*     */   
/*     */   public int getTopPadding() {
/* 366 */     return (this.interp == null) ? 0 : this.interp.getTopPadding();
/*     */   }
/*     */   
/*     */   public int getBottomPadding() {
/* 377 */     return (this.interp == null) ? 0 : this.interp.getBottomPadding();
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt, int sourceIndex) {
/* 403 */     if (destPt == null)
/* 404 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 405 */     if (sourceIndex != 0)
/* 406 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/* 409 */     return this.warp.mapDestPoint(destPt);
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt, int sourceIndex) {
/* 435 */     if (sourcePt == null)
/* 436 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 437 */     if (sourceIndex != 0)
/* 438 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/* 441 */     return this.warp.mapSourcePoint(sourcePt);
/*     */   }
/*     */   
/*     */   protected Rectangle forwardMapRect(Rectangle sourceRect, int sourceIndex) {
/* 466 */     if (sourceRect == null)
/* 467 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 470 */     if (sourceIndex != 0)
/* 471 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 475 */     return this.warp.mapSourceRect(sourceRect);
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 498 */     if (destRect == null)
/* 499 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 502 */     if (sourceIndex != 0)
/* 503 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 507 */     Rectangle wrect = this.warp.mapDestRect(destRect);
/* 509 */     return (wrect == null) ? getSource(0).getBounds() : wrect;
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 535 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 538 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 541 */     Rectangle destRect = (new Rectangle(org.x, org.y, this.tileWidth, this.tileHeight)).intersection(this.computableBounds);
/* 544 */     if (destRect.isEmpty()) {
/* 545 */       if (this.setBackground)
/* 546 */         ImageUtil.fillBackground(dest, destRect, this.backgroundValues); 
/* 548 */       return dest;
/*     */     } 
/* 551 */     PlanarImage source = getSource(0);
/* 553 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 554 */     if (!srcRect.intersects(source.getBounds())) {
/* 555 */       if (this.setBackground)
/* 556 */         ImageUtil.fillBackground(dest, destRect, this.backgroundValues); 
/* 558 */       return dest;
/*     */     } 
/* 562 */     if (this.cobbleSources) {
/* 563 */       Raster[] srcs = new Raster[1];
/* 564 */       srcs[0] = (this.extender != null) ? source.getExtendedData(srcRect, this.extender) : source.getData(srcRect);
/* 569 */       computeRect(srcs, dest, destRect);
/* 572 */       if (source.overlapsMultipleTiles(srcRect))
/* 573 */         recycleTile(srcs[0]); 
/*     */     } else {
/* 576 */       PlanarImage[] srcs = { source };
/* 577 */       computeRect(srcs, dest, destRect);
/*     */     } 
/* 580 */     return dest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WarpOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
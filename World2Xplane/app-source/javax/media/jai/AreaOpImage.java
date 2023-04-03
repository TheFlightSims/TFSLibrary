/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class AreaOpImage extends OpImage {
/*     */   protected int leftPadding;
/*     */   
/*     */   protected int rightPadding;
/*     */   
/*     */   protected int topPadding;
/*     */   
/*     */   protected int bottomPadding;
/*     */   
/*  89 */   protected BorderExtender extender = null;
/*     */   
/*     */   private Rectangle theDest;
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source) {
/*  98 */     if (layout != null && source != null && (layout.getValidMask() & 0xF) != 0) {
/* 103 */       Rectangle sourceRect = new Rectangle(source.getMinX(), source.getMinY(), source.getWidth(), source.getHeight());
/* 110 */       Rectangle dstRect = new Rectangle(layout.getMinX(source), layout.getMinY(source), layout.getWidth(source), layout.getHeight(source));
/* 116 */       if (dstRect.intersection(sourceRect).isEmpty())
/* 117 */         throw new IllegalArgumentException(JaiI18N.getString("AreaOpImage0")); 
/*     */     } 
/* 122 */     return layout;
/*     */   }
/*     */   
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/* 129 */     if (configuration == null) {
/* 130 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.TRUE);
/*     */     } else {
/* 134 */       config = configuration;
/* 138 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/* 139 */         RenderingHints hints = new RenderingHints(null);
/* 141 */         hints.putAll(configuration);
/* 142 */         config = hints;
/* 143 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.TRUE);
/*     */       } 
/*     */     } 
/* 147 */     return config;
/*     */   }
/*     */   
/*     */   public AreaOpImage(RenderedImage source, ImageLayout layout, Map configuration, boolean cobbleSources, BorderExtender extender, int leftPadding, int rightPadding, int topPadding, int bottomPadding) {
/* 208 */     super(vectorize(source), layoutHelper(layout, source), configHelper(configuration), cobbleSources);
/* 213 */     this.extender = extender;
/* 214 */     this.leftPadding = leftPadding;
/* 215 */     this.rightPadding = rightPadding;
/* 216 */     this.topPadding = topPadding;
/* 217 */     this.bottomPadding = bottomPadding;
/* 219 */     if (extender == null) {
/* 221 */       int d_x0 = getMinX() + leftPadding;
/* 222 */       int d_y0 = getMinY() + topPadding;
/* 224 */       int d_w = getWidth() - leftPadding - rightPadding;
/* 225 */       d_w = Math.max(d_w, 0);
/* 227 */       int d_h = getHeight() - topPadding - bottomPadding;
/* 228 */       d_h = Math.max(d_h, 0);
/* 230 */       this.theDest = new Rectangle(d_x0, d_y0, d_w, d_h);
/*     */     } else {
/* 232 */       this.theDest = getBounds();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getLeftPadding() {
/* 242 */     return this.leftPadding;
/*     */   }
/*     */   
/*     */   public int getRightPadding() {
/* 251 */     return this.rightPadding;
/*     */   }
/*     */   
/*     */   public int getTopPadding() {
/* 259 */     return this.topPadding;
/*     */   }
/*     */   
/*     */   public int getBottomPadding() {
/* 267 */     return this.bottomPadding;
/*     */   }
/*     */   
/*     */   public BorderExtender getBorderExtender() {
/* 280 */     return this.extender;
/*     */   }
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/* 303 */     if (sourceRect == null)
/* 304 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 307 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 308 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 312 */     int lpad = getLeftPadding();
/* 313 */     int rpad = getRightPadding();
/* 314 */     int tpad = getTopPadding();
/* 315 */     int bpad = getBottomPadding();
/* 317 */     return new Rectangle(sourceRect.x + lpad, sourceRect.y + tpad, sourceRect.width - lpad - rpad, sourceRect.height - tpad - bpad);
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 341 */     if (destRect == null)
/* 342 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 345 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 346 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 350 */     int lpad = getLeftPadding();
/* 351 */     int rpad = getRightPadding();
/* 352 */     int tpad = getTopPadding();
/* 353 */     int bpad = getBottomPadding();
/* 355 */     return new Rectangle(destRect.x - lpad, destRect.y - tpad, destRect.width + lpad + rpad, destRect.height + tpad + bpad);
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 382 */     if (!this.cobbleSources)
/* 383 */       return super.computeTile(tileX, tileY); 
/* 387 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 388 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 391 */     Rectangle rect = new Rectangle(org.x, org.y, this.sampleModel.getWidth(), this.sampleModel.getHeight());
/* 395 */     Rectangle destRect = rect.intersection(this.theDest);
/* 396 */     if (destRect.width <= 0 || destRect.height <= 0)
/* 397 */       return dest; 
/* 401 */     PlanarImage s = getSource(0);
/* 409 */     destRect = destRect.intersection(s.getBounds());
/* 410 */     Rectangle srcRect = new Rectangle(destRect);
/* 411 */     srcRect.x -= getLeftPadding();
/* 412 */     srcRect.width += getLeftPadding() + getRightPadding();
/* 413 */     srcRect.y -= getTopPadding();
/* 414 */     srcRect.height += getTopPadding() + getBottomPadding();
/* 420 */     IntegerSequence srcXSplits = new IntegerSequence();
/* 421 */     IntegerSequence srcYSplits = new IntegerSequence();
/* 424 */     s.getSplits(srcXSplits, srcYSplits, srcRect);
/* 427 */     IntegerSequence xSplits = new IntegerSequence(destRect.x, destRect.x + destRect.width);
/* 430 */     xSplits.insert(destRect.x);
/* 431 */     xSplits.insert(destRect.x + destRect.width);
/* 433 */     srcXSplits.startEnumeration();
/* 434 */     while (srcXSplits.hasMoreElements()) {
/* 435 */       int xsplit = srcXSplits.nextElement();
/* 436 */       int lsplit = xsplit - getLeftPadding();
/* 437 */       int rsplit = xsplit + getRightPadding();
/* 438 */       xSplits.insert(lsplit);
/* 439 */       xSplits.insert(rsplit);
/*     */     } 
/* 443 */     IntegerSequence ySplits = new IntegerSequence(destRect.y, destRect.y + destRect.height);
/* 446 */     ySplits.insert(destRect.y);
/* 447 */     ySplits.insert(destRect.y + destRect.height);
/* 449 */     srcYSplits.startEnumeration();
/* 450 */     while (srcYSplits.hasMoreElements()) {
/* 451 */       int ysplit = srcYSplits.nextElement();
/* 452 */       int tsplit = ysplit - getBottomPadding();
/* 453 */       int bsplit = ysplit + getTopPadding();
/* 454 */       ySplits.insert(tsplit);
/* 455 */       ySplits.insert(bsplit);
/*     */     } 
/* 463 */     Raster[] sources = new Raster[1];
/* 465 */     ySplits.startEnumeration();
/*     */     int y1;
/* 466 */     for (y1 = ySplits.nextElement(); ySplits.hasMoreElements(); y1 = y2) {
/* 467 */       int y2 = ySplits.nextElement();
/* 469 */       int h = y2 - y1;
/* 470 */       int py1 = y1 - getTopPadding();
/* 471 */       int py2 = y2 + getBottomPadding();
/* 472 */       int ph = py2 - py1;
/* 474 */       xSplits.startEnumeration();
/* 475 */       int x1 = xSplits.nextElement();
/* 476 */       for (; xSplits.hasMoreElements(); 
/* 477 */         x1 = x2) {
/* 478 */         int x2 = xSplits.nextElement();
/* 480 */         int w = x2 - x1;
/* 481 */         int px1 = x1 - getLeftPadding();
/* 482 */         int px2 = x2 + getRightPadding();
/* 483 */         int pw = px2 - px1;
/* 486 */         Rectangle srcSubRect = new Rectangle(px1, py1, pw, ph);
/* 487 */         sources[0] = (this.extender != null) ? s.getExtendedData(srcSubRect, this.extender) : s.getData(srcSubRect);
/* 492 */         Rectangle dstSubRect = new Rectangle(x1, y1, w, h);
/* 493 */         computeRect(sources, dest, dstSubRect);
/* 496 */         if (s.overlapsMultipleTiles(srcSubRect))
/* 497 */           recycleTile(sources[0]); 
/*     */       } 
/*     */     } 
/* 501 */     return dest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\AreaOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
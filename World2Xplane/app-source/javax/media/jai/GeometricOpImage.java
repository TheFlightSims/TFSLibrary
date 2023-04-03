/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public abstract class GeometricOpImage extends OpImage {
/*     */   protected Interpolation interp;
/*     */   
/*  79 */   protected BorderExtender extender = null;
/*     */   
/*     */   protected Rectangle computableBounds;
/*     */   
/*     */   protected boolean setBackground;
/*     */   
/*     */   protected double[] backgroundValues;
/*     */   
/*     */   protected int[] intBackgroundValues;
/*     */   
/*     */   public GeometricOpImage(Vector sources, ImageLayout layout, Map configuration, boolean cobbleSources, BorderExtender extender, Interpolation interp) {
/* 156 */     this(sources, layout, configuration, cobbleSources, extender, interp, (double[])null);
/*     */   }
/*     */   
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/* 169 */     if (configuration == null) {
/* 170 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.TRUE);
/*     */     } else {
/* 174 */       config = configuration;
/* 178 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/* 180 */         RenderingHints hints = new RenderingHints(null);
/* 182 */         hints.putAll(configuration);
/* 183 */         config = hints;
/* 184 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.TRUE);
/*     */       } 
/*     */     } 
/* 188 */     return config;
/*     */   }
/*     */   
/*     */   public GeometricOpImage(Vector sources, ImageLayout layout, Map configuration, boolean cobbleSources, BorderExtender extender, Interpolation interp, double[] backgroundValues) {
/* 263 */     super(sources, layout, configHelper(configuration), cobbleSources);
/* 268 */     this.extender = extender;
/* 269 */     this.interp = (interp != null) ? interp : new InterpolationNearest();
/* 271 */     if (backgroundValues == null)
/* 272 */       backgroundValues = new double[] { 0.0D }; 
/* 274 */     this.setBackground = false;
/* 275 */     for (int i = 0; i < backgroundValues.length; i++) {
/* 276 */       if (backgroundValues[i] != 0.0D)
/* 277 */         this.setBackground = true; 
/*     */     } 
/* 279 */     this.backgroundValues = backgroundValues;
/* 280 */     int numBands = getSampleModel().getNumBands();
/* 281 */     if (backgroundValues.length < numBands) {
/* 282 */       this.backgroundValues = new double[numBands];
/* 283 */       for (int j = 0; j < numBands; j++)
/* 284 */         this.backgroundValues[j] = backgroundValues[0]; 
/*     */     } 
/* 287 */     if (this.sampleModel.getDataType() <= 3) {
/* 288 */       int length = this.backgroundValues.length;
/* 289 */       this.intBackgroundValues = new int[length];
/* 290 */       for (int j = 0; j < length; j++)
/* 291 */         this.intBackgroundValues[j] = (int)this.backgroundValues[j]; 
/*     */     } 
/* 295 */     this.computableBounds = getBounds();
/*     */   }
/*     */   
/*     */   public Interpolation getInterpolation() {
/* 305 */     return this.interp;
/*     */   }
/*     */   
/*     */   public BorderExtender getBorderExtender() {
/* 316 */     return this.extender;
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt, int sourceIndex) {
/* 356 */     if (destPt == null)
/* 357 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 358 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 359 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/* 362 */     Rectangle destRect = new Rectangle((int)destPt.getX(), (int)destPt.getY(), 1, 1);
/* 366 */     Rectangle sourceRect = backwardMapRect(destRect, sourceIndex);
/* 368 */     if (sourceRect == null)
/* 369 */       return null; 
/* 372 */     Point2D pt = (Point2D)destPt.clone();
/* 373 */     pt.setLocation(sourceRect.x + (sourceRect.width - 1.0D) / 2.0D, sourceRect.y + (sourceRect.height - 1.0D) / 2.0D);
/* 376 */     return pt;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt, int sourceIndex) {
/* 416 */     if (sourcePt == null)
/* 417 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 418 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 419 */       throw new IndexOutOfBoundsException(JaiI18N.getString("Generic1")); 
/* 422 */     Rectangle sourceRect = new Rectangle((int)sourcePt.getX(), (int)sourcePt.getY(), 1, 1);
/* 425 */     Rectangle destRect = forwardMapRect(sourceRect, sourceIndex);
/* 427 */     if (destRect == null)
/* 428 */       return null; 
/* 431 */     Point2D pt = (Point2D)sourcePt.clone();
/* 432 */     pt.setLocation(destRect.x + (destRect.width - 1.0D) / 2.0D, destRect.y + (destRect.height - 1.0D) / 2.0D);
/* 435 */     return pt;
/*     */   }
/*     */   
/*     */   protected abstract Rectangle forwardMapRect(Rectangle paramRectangle, int paramInt);
/*     */   
/*     */   protected abstract Rectangle backwardMapRect(Rectangle paramRectangle, int paramInt);
/*     */   
/*     */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/* 531 */     if (sourceRect == null)
/* 532 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 535 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 536 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 540 */     int lpad = this.interp.getLeftPadding();
/* 541 */     int tpad = this.interp.getTopPadding();
/* 544 */     Rectangle srcRect = (Rectangle)sourceRect.clone();
/* 545 */     srcRect.x += lpad;
/* 546 */     srcRect.y += tpad;
/* 547 */     srcRect.width -= lpad + this.interp.getRightPadding();
/* 548 */     srcRect.height -= tpad + this.interp.getBottomPadding();
/* 551 */     Rectangle destRect = forwardMapRect(srcRect, sourceIndex);
/* 554 */     return (destRect == null) ? getBounds() : destRect;
/*     */   }
/*     */   
/*     */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 582 */     if (destRect == null)
/* 583 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 586 */     if (sourceIndex < 0 || sourceIndex >= getNumSources())
/* 587 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 591 */     Rectangle sourceRect = backwardMapRect(destRect, sourceIndex);
/* 592 */     if (sourceRect == null)
/* 593 */       return getSource(sourceIndex).getBounds(); 
/* 597 */     int lpad = this.interp.getLeftPadding();
/* 598 */     int tpad = this.interp.getTopPadding();
/* 601 */     return new Rectangle(sourceRect.x - lpad, sourceRect.y - tpad, sourceRect.width + lpad + this.interp.getRightPadding(), sourceRect.height + tpad + this.interp.getBottomPadding());
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 633 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 636 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 639 */     Rectangle destRect = getTileRect(tileX, tileY).intersection(getBounds());
/* 642 */     if (destRect.isEmpty()) {
/* 643 */       if (this.setBackground)
/* 644 */         ImageUtil.fillBackground(dest, destRect, this.backgroundValues); 
/* 647 */       return dest;
/*     */     } 
/* 650 */     int numSources = getNumSources();
/* 651 */     if (this.cobbleSources) {
/* 652 */       Raster[] rasterSources = new Raster[numSources];
/*     */       int i;
/* 655 */       for (i = 0; i < numSources; i++) {
/* 656 */         PlanarImage source = getSource(i);
/* 658 */         Rectangle srcBounds = source.getBounds();
/* 659 */         Rectangle srcRect = mapDestRect(destRect, i);
/* 660 */         if (srcRect == null) {
/* 662 */           srcRect = srcBounds;
/*     */         } else {
/* 664 */           if (this.extender == null && !srcBounds.contains(srcRect))
/* 666 */             srcRect = srcBounds.intersection(srcRect); 
/* 668 */           if (!srcRect.intersects(srcBounds)) {
/* 670 */             if (this.setBackground)
/* 671 */               ImageUtil.fillBackground(dest, destRect, this.backgroundValues); 
/* 674 */             return dest;
/*     */           } 
/*     */         } 
/* 678 */         rasterSources[i] = (this.extender != null) ? source.getExtendedData(srcRect, this.extender) : source.getData(srcRect);
/*     */       } 
/* 683 */       computeRect(rasterSources, dest, destRect);
/* 685 */       for (i = 0; i < numSources; i++) {
/* 686 */         Raster sourceData = rasterSources[i];
/* 687 */         if (sourceData != null) {
/* 688 */           PlanarImage source = getSourceImage(i);
/* 691 */           if (source.overlapsMultipleTiles(sourceData.getBounds()))
/* 692 */             recycleTile(sourceData); 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 697 */       PlanarImage[] imageSources = new PlanarImage[numSources];
/* 699 */       for (int i = 0; i < numSources; i++)
/* 700 */         imageSources[i] = getSource(i); 
/* 702 */       computeRect(imageSources, dest, destRect);
/*     */     } 
/* 705 */     return dest;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\GeometricOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
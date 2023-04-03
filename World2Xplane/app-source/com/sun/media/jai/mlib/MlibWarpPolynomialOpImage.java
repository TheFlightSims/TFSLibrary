/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.Warp;
/*     */ import javax.media.jai.WarpOpImage;
/*     */ import javax.media.jai.WarpPolynomial;
/*     */ 
/*     */ final class MlibWarpPolynomialOpImage extends WarpOpImage {
/*     */   private double[] xCoeffs;
/*     */   
/*     */   private double[] yCoeffs;
/*     */   
/*     */   private int filter;
/*     */   
/*     */   private double preScaleX;
/*     */   
/*     */   private double preScaleY;
/*     */   
/*     */   private double postScaleX;
/*     */   
/*     */   private double postScaleY;
/*     */   
/*     */   public MlibWarpPolynomialOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, WarpPolynomial warp, Interpolation interp, int filter, double[] backgroundValues) {
/*  90 */     super(source, layout, config, true, extender, interp, (Warp)warp, backgroundValues);
/*  99 */     float[] xc = warp.getXCoeffs();
/* 100 */     float[] yc = warp.getYCoeffs();
/* 101 */     int size = xc.length;
/* 103 */     this.xCoeffs = new double[size];
/* 104 */     this.yCoeffs = new double[size];
/* 105 */     for (int i = 0; i < size; i++) {
/* 106 */       this.xCoeffs[i] = xc[i];
/* 107 */       this.yCoeffs[i] = yc[i];
/*     */     } 
/* 110 */     this.filter = filter;
/* 112 */     this.preScaleX = warp.getPreScaleX();
/* 113 */     this.preScaleY = warp.getPreScaleY();
/* 114 */     this.postScaleX = warp.getPostScaleX();
/* 115 */     this.postScaleY = warp.getPostScaleY();
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 137 */     Rectangle wrect = super.backwardMapRect(destRect, sourceIndex);
/* 143 */     wrect.setBounds(wrect.x - 1, wrect.y - 1, wrect.width + 2, wrect.height + 2);
/* 146 */     return wrect;
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 167 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 170 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 173 */     Rectangle rect = new Rectangle(org.x, org.y, this.tileWidth, this.tileHeight);
/* 174 */     Rectangle destRect = rect.intersection(this.computableBounds);
/* 175 */     Rectangle destRect1 = rect.intersection(getBounds());
/* 176 */     if (destRect.isEmpty()) {
/* 177 */       if (this.setBackground)
/* 178 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 180 */       return dest;
/*     */     } 
/* 184 */     Rectangle srcRect = backwardMapRect(destRect, 0).intersection(getSourceImage(0).getBounds());
/* 187 */     if (srcRect.isEmpty()) {
/* 188 */       if (this.setBackground)
/* 189 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 191 */       return dest;
/*     */     } 
/* 194 */     if (!destRect1.equals(destRect))
/* 196 */       ImageUtil.fillBordersWithBackgroundValues(destRect1, destRect, dest, this.backgroundValues); 
/* 200 */     int l = (this.interp == null) ? 0 : this.interp.getLeftPadding();
/* 201 */     int r = (this.interp == null) ? 0 : this.interp.getRightPadding();
/* 202 */     int t = (this.interp == null) ? 0 : this.interp.getTopPadding();
/* 203 */     int b = (this.interp == null) ? 0 : this.interp.getBottomPadding();
/* 205 */     srcRect = new Rectangle(srcRect.x - l, srcRect.y - t, srcRect.width + l + r, srcRect.height + t + b);
/* 211 */     Raster[] sources = new Raster[1];
/* 212 */     sources[0] = (getBorderExtender() != null) ? getSourceImage(0).getExtendedData(srcRect, this.extender) : getSourceImage(0).getData(srcRect);
/* 216 */     computeRect(sources, dest, destRect);
/* 219 */     if (getSourceImage(0).overlapsMultipleTiles(srcRect))
/* 220 */       recycleTile(sources[0]); 
/* 223 */     return dest;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int i;
/* 233 */     Raster source = sources[0];
/* 236 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 238 */     MediaLibAccessor srcMA = new MediaLibAccessor(source, source.getBounds(), formatTag);
/* 240 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 243 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/* 244 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 246 */     switch (dstMA.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 251 */         if (this.setBackground) {
/* 252 */           for (int j = 0; j < dstMLI.length; j++)
/* 253 */             Image.PolynomialWarp2(dstMLI[j], srcMLI[j], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.filter, 0, this.intBackgroundValues); 
/*     */           break;
/*     */         } 
/* 266 */         for (i = 0; i < dstMLI.length; i++) {
/* 267 */           Image.PolynomialWarp(dstMLI[i], srcMLI[i], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.filter, 0);
/* 277 */           MlibUtils.clampImage(dstMLI[i], getColorModel());
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 283 */         if (this.setBackground) {
/* 284 */           for (i = 0; i < dstMLI.length; i++)
/* 285 */             Image.PolynomialWarp2_Fp(dstMLI[i], srcMLI[i], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.filter, 0, this.backgroundValues); 
/*     */           break;
/*     */         } 
/* 298 */         for (i = 0; i < dstMLI.length; i++)
/* 299 */           Image.PolynomialWarp_Fp(dstMLI[i], srcMLI[i], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.filter, 0); 
/*     */         break;
/*     */       default:
/* 313 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 316 */     if (dstMA.isDataCopy()) {
/* 317 */       dstMA.clampDataArrays();
/* 318 */       dstMA.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibWarpPolynomialOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
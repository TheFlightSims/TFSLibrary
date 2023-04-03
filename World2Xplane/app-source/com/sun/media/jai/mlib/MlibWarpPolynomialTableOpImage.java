/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import com.sun.medialib.mlib.mediaLibImageInterpTable;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.InterpolationTable;
/*     */ import javax.media.jai.Warp;
/*     */ import javax.media.jai.WarpOpImage;
/*     */ import javax.media.jai.WarpPolynomial;
/*     */ 
/*     */ final class MlibWarpPolynomialTableOpImage extends WarpOpImage {
/*     */   private double[] xCoeffs;
/*     */   
/*     */   private double[] yCoeffs;
/*     */   
/*     */   private mediaLibImageInterpTable mlibInterpTableI;
/*     */   
/*     */   private mediaLibImageInterpTable mlibInterpTableF;
/*     */   
/*     */   private mediaLibImageInterpTable mlibInterpTableD;
/*     */   
/*     */   private double preScaleX;
/*     */   
/*     */   private double preScaleY;
/*     */   
/*     */   private double postScaleX;
/*     */   
/*     */   private double postScaleY;
/*     */   
/*     */   public MlibWarpPolynomialTableOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, WarpPolynomial warp, Interpolation interp, double[] backgroundValues) {
/*  90 */     super(source, layout, config, true, extender, interp, (Warp)warp, backgroundValues);
/*  99 */     this.mlibInterpTableI = null;
/* 100 */     this.mlibInterpTableF = null;
/* 101 */     this.mlibInterpTableD = null;
/* 103 */     float[] xc = warp.getXCoeffs();
/* 104 */     float[] yc = warp.getYCoeffs();
/* 105 */     int size = xc.length;
/* 107 */     this.xCoeffs = new double[size];
/* 108 */     this.yCoeffs = new double[size];
/* 109 */     for (int i = 0; i < size; i++) {
/* 110 */       this.xCoeffs[i] = xc[i];
/* 111 */       this.yCoeffs[i] = yc[i];
/*     */     } 
/* 114 */     this.preScaleX = warp.getPreScaleX();
/* 115 */     this.preScaleY = warp.getPreScaleY();
/* 116 */     this.postScaleX = warp.getPostScaleX();
/* 117 */     this.postScaleY = warp.getPostScaleY();
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 139 */     Rectangle wrect = super.backwardMapRect(destRect, sourceIndex);
/* 145 */     wrect.setBounds(wrect.x - 1, wrect.y - 1, wrect.width + 2, wrect.height + 2);
/* 148 */     return wrect;
/*     */   }
/*     */   
/*     */   public Raster computeTile(int tileX, int tileY) {
/* 169 */     Point org = new Point(tileXToX(tileX), tileYToY(tileY));
/* 172 */     WritableRaster dest = createWritableRaster(this.sampleModel, org);
/* 175 */     Rectangle rect = new Rectangle(org.x, org.y, this.tileWidth, this.tileHeight);
/* 176 */     Rectangle destRect = rect.intersection(this.computableBounds);
/* 177 */     Rectangle destRect1 = rect.intersection(getBounds());
/* 179 */     if (destRect.isEmpty()) {
/* 180 */       if (this.setBackground)
/* 181 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 183 */       return dest;
/*     */     } 
/* 187 */     Rectangle srcRect = backwardMapRect(destRect, 0).intersection(getSourceImage(0).getBounds());
/* 190 */     if (srcRect.isEmpty()) {
/* 191 */       if (this.setBackground)
/* 192 */         ImageUtil.fillBackground(dest, destRect1, this.backgroundValues); 
/* 194 */       return dest;
/*     */     } 
/* 197 */     if (!destRect1.equals(destRect))
/* 199 */       ImageUtil.fillBordersWithBackgroundValues(destRect1, destRect, dest, this.backgroundValues); 
/* 203 */     int l = (this.interp == null) ? 0 : this.interp.getLeftPadding();
/* 204 */     int r = (this.interp == null) ? 0 : this.interp.getRightPadding();
/* 205 */     int t = (this.interp == null) ? 0 : this.interp.getTopPadding();
/* 206 */     int b = (this.interp == null) ? 0 : this.interp.getBottomPadding();
/* 208 */     srcRect = new Rectangle(srcRect.x - l, srcRect.y - t, srcRect.width + l + r, srcRect.height + t + b);
/* 214 */     Raster[] sources = new Raster[1];
/* 215 */     sources[0] = (getBorderExtender() != null) ? getSourceImage(0).getExtendedData(srcRect, this.extender) : getSourceImage(0).getData(srcRect);
/* 219 */     computeRect(sources, dest, destRect);
/* 222 */     if (getSourceImage(0).overlapsMultipleTiles(srcRect))
/* 223 */       recycleTile(sources[0]); 
/* 226 */     return dest;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int i;
/* 236 */     Raster source = sources[0];
/* 239 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 241 */     MediaLibAccessor srcMA = new MediaLibAccessor(source, source.getBounds(), formatTag);
/* 243 */     MediaLibAccessor dstMA = new MediaLibAccessor(dest, destRect, formatTag);
/* 246 */     mediaLibImage[] srcMLI = srcMA.getMediaLibImages();
/* 247 */     mediaLibImage[] dstMLI = dstMA.getMediaLibImages();
/* 249 */     switch (dstMA.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 254 */         if (this.mlibInterpTableI == null) {
/* 255 */           InterpolationTable jtable = (InterpolationTable)this.interp;
/* 256 */           this.mlibInterpTableI = new mediaLibImageInterpTable(3, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableData(), jtable.getVerticalTableData());
/*     */         } 
/* 270 */         if (this.setBackground) {
/* 271 */           for (int j = 0; j < dstMLI.length; j++)
/* 272 */             Image.PolynomialWarpTable2(dstMLI[j], srcMLI[j], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.mlibInterpTableI, 0, this.intBackgroundValues); 
/*     */           break;
/*     */         } 
/* 285 */         for (i = 0; i < dstMLI.length; i++) {
/* 286 */           Image.PolynomialWarpTable(dstMLI[i], srcMLI[i], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.mlibInterpTableI, 0);
/* 296 */           MlibUtils.clampImage(dstMLI[i], getColorModel());
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 301 */         if (this.mlibInterpTableF == null) {
/* 302 */           InterpolationTable jtable = (InterpolationTable)this.interp;
/* 303 */           this.mlibInterpTableF = new mediaLibImageInterpTable(4, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableDataFloat(), jtable.getVerticalTableDataFloat());
/*     */         } 
/* 317 */         if (this.setBackground) {
/* 318 */           for (i = 0; i < dstMLI.length; i++)
/* 319 */             Image.PolynomialWarpTable2_Fp(dstMLI[i], srcMLI[i], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.mlibInterpTableD, 0, this.backgroundValues); 
/*     */           break;
/*     */         } 
/* 332 */         for (i = 0; i < dstMLI.length; i++)
/* 333 */           Image.PolynomialWarpTable_Fp(dstMLI[i], srcMLI[i], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.mlibInterpTableD, 0); 
/*     */         break;
/*     */       case 5:
/* 348 */         if (this.mlibInterpTableD == null) {
/* 349 */           InterpolationTable jtable = (InterpolationTable)this.interp;
/* 350 */           this.mlibInterpTableD = new mediaLibImageInterpTable(5, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableDataDouble(), jtable.getVerticalTableDataDouble());
/*     */         } 
/* 363 */         if (this.setBackground) {
/* 364 */           for (i = 0; i < dstMLI.length; i++)
/* 365 */             Image.PolynomialWarpTable2_Fp(dstMLI[i], srcMLI[i], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.mlibInterpTableD, 0, this.backgroundValues); 
/*     */           break;
/*     */         } 
/* 378 */         for (i = 0; i < dstMLI.length; i++)
/* 379 */           Image.PolynomialWarpTable_Fp(dstMLI[i], srcMLI[i], this.xCoeffs, this.yCoeffs, destRect.x, destRect.y, source.getMinX(), source.getMinY(), this.preScaleX, this.preScaleY, this.postScaleX, this.postScaleY, this.mlibInterpTableD, 0); 
/*     */         break;
/*     */       default:
/* 393 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 396 */     if (dstMA.isDataCopy()) {
/* 397 */       dstMA.clampDataArrays();
/* 398 */       dstMA.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibWarpPolynomialTableOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
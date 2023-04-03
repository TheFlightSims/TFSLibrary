/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterFactory;
/*     */ 
/*     */ final class MlibBandSelectOpImage extends PointOpImage {
/*  34 */   private int cmask = 0;
/*     */   
/*     */   public MlibBandSelectOpImage(RenderedImage source, Map config, ImageLayout layout, int[] bandIndices) {
/*  49 */     super(source, layout, config, true);
/*  51 */     int numBands = bandIndices.length;
/*  52 */     if (getSampleModel().getNumBands() != numBands) {
/*  54 */       this.sampleModel = RasterFactory.createComponentSampleModel(this.sampleModel, this.sampleModel.getDataType(), this.tileWidth, this.tileHeight, numBands);
/*  58 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/*  61 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */     } 
/*  67 */     int maxShift = source.getSampleModel().getNumBands() - 1;
/*  68 */     for (int i = 0; i < bandIndices.length; i++)
/*  69 */       this.cmask |= 1 << maxShift - bandIndices[i]; 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  85 */     Raster source = sources[0];
/*  86 */     Rectangle srcRect = mapDestRect(destRect, 0);
/*  88 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  90 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  92 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  95 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/*  96 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/*  98 */     for (int i = 0; i < dstML.length; i++)
/*  99 */       Image.ChannelExtract(dstML[i], srcML[i], this.cmask); 
/* 102 */     if (dstAccessor.isDataCopy()) {
/* 103 */       dstAccessor.clampDataArrays();
/* 104 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibBandSelectOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
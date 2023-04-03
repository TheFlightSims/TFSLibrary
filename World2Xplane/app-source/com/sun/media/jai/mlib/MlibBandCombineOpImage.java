/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterFactory;
/*     */ 
/*     */ final class MlibBandCombineOpImage extends PointOpImage {
/*  35 */   private double[] cmat = new double[9];
/*     */   
/*  38 */   private double[] offset = new double[3];
/*     */   
/*     */   private boolean isOffsetNonZero = false;
/*     */   
/*     */   public MlibBandCombineOpImage(RenderedImage source, Map config, ImageLayout layout, double[][] matrix) {
/*  56 */     super(source, layout, config, true);
/*  58 */     int numBands = matrix.length;
/*  59 */     if (getSampleModel().getNumBands() != numBands) {
/*  60 */       this.sampleModel = RasterFactory.createComponentSampleModel(this.sampleModel, this.sampleModel.getDataType(), this.tileWidth, this.tileHeight, numBands);
/*  64 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/*  67 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */     } 
/*  74 */     ComponentSampleModel csm = (ComponentSampleModel)source.getSampleModel();
/*  76 */     int[] bankIndices = csm.getBankIndices();
/*  77 */     int[] bandOffsets = csm.getBandOffsets();
/*  82 */     if (bankIndices[0] == bankIndices[1] && bankIndices[0] == bankIndices[2] && bandOffsets[0] > bandOffsets[2]) {
/*  85 */       for (int j = 0; j < 3; j++) {
/*  86 */         int k = 8 - 3 * j;
/*  87 */         for (int i = 0; i < 3; i++)
/*  88 */           this.cmat[k--] = matrix[j][i]; 
/*  90 */         this.offset[2 - j] = matrix[j][3];
/*  91 */         if (this.offset[j] != 0.0D)
/*  92 */           this.isOffsetNonZero = true; 
/*     */       } 
/*     */     } else {
/*  96 */       for (int j = 0; j < 3; j++) {
/*  97 */         int k = 3 * j;
/*  98 */         for (int i = 0; i < 3; i++)
/*  99 */           this.cmat[k++] = matrix[j][i]; 
/* 101 */         this.offset[j] = matrix[j][3];
/* 102 */         if (this.offset[j] != 0.0D)
/* 103 */           this.isOffsetNonZero = true; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int i;
/*     */     String className;
/* 121 */     Raster source = sources[0];
/* 122 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 124 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 126 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/* 128 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 131 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 132 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 134 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 139 */         for (i = 0; i < dstML.length; i++) {
/* 140 */           if (this.isOffsetNonZero) {
/* 141 */             Image.ColorConvert2(dstML[i], srcML[i], this.cmat, this.offset);
/*     */           } else {
/* 146 */             Image.ColorConvert1(dstML[i], srcML[i], this.cmat);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 4:
/*     */       case 5:
/* 155 */         for (i = 0; i < dstML.length; i++) {
/* 156 */           if (this.isOffsetNonZero) {
/* 157 */             Image.ColorConvert2_Fp(dstML[i], srcML[i], this.cmat, this.offset);
/*     */           } else {
/* 162 */             Image.ColorConvert1_Fp(dstML[i], srcML[i], this.cmat);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       default:
/* 170 */         className = getClass().getName();
/* 171 */         throw new RuntimeException(className + JaiI18N.getString("Generic2"));
/*     */     } 
/* 175 */     if (dstAccessor.isDataCopy()) {
/* 176 */       dstAccessor.clampDataArrays();
/* 177 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibBandCombineOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
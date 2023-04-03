/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.AreaOpImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.operator.MedianFilterDescriptor;
/*     */ import javax.media.jai.operator.MedianFilterShape;
/*     */ 
/*     */ final class MlibMedianFilterOpImage extends AreaOpImage {
/*     */   protected int maskType;
/*     */   
/*     */   protected int maskSize;
/*     */   
/*     */   public MlibMedianFilterOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, MedianFilterShape maskType, int maskSize) {
/*  62 */     super(source, layout, config, true, extender, (maskSize - 1) / 2, (maskSize - 1) / 2, maskSize / 2, maskSize / 2);
/*  71 */     this.maskType = mapToMlibMaskType(maskType);
/*  72 */     this.maskSize = maskSize;
/*     */   }
/*     */   
/*     */   private static int mapToMlibMaskType(MedianFilterShape maskType) {
/*  76 */     if (maskType.equals(MedianFilterDescriptor.MEDIAN_MASK_SQUARE))
/*  77 */       return 0; 
/*  78 */     if (maskType.equals(MedianFilterDescriptor.MEDIAN_MASK_PLUS))
/*  79 */       return 1; 
/*  80 */     if (maskType.equals(MedianFilterDescriptor.MEDIAN_MASK_X))
/*  81 */       return 2; 
/*  82 */     if (maskType.equals(MedianFilterDescriptor.MEDIAN_MASK_SQUARE_SEPARABLE))
/*  83 */       return 3; 
/*  85 */     throw new RuntimeException(JaiI18N.getString("MedianFilterOpImage0"));
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 100 */     Raster source = sources[0];
/* 101 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 103 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 105 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/* 107 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 109 */     int numBands = getSampleModel().getNumBands();
/* 112 */     int cmask = (1 << numBands) - 1;
/* 113 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 114 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 115 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/* 116 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 121 */           if (this.maskSize == 3) {
/* 123 */             Image.MedianFilter3x3(dstML[i], srcML[i], this.maskType, cmask, 0);
/*     */             break;
/*     */           } 
/* 128 */           if (this.maskSize == 5) {
/* 130 */             Image.MedianFilter5x5(dstML[i], srcML[i], this.maskType, cmask, 0);
/*     */             break;
/*     */           } 
/* 135 */           if (this.maskSize == 7) {
/* 137 */             Image.MedianFilter7x7(dstML[i], srcML[i], this.maskType, cmask, 0);
/*     */             break;
/*     */           } 
/* 144 */           Image.MedianFilterMxN(dstML[i], srcML[i], this.maskSize, this.maskSize, this.maskType, cmask, 0);
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 155 */           if (this.maskSize == 3) {
/* 157 */             Image.MedianFilter3x3_Fp(dstML[i], srcML[i], this.maskType, cmask, 0);
/*     */             break;
/*     */           } 
/* 162 */           if (this.maskSize == 5) {
/* 164 */             Image.MedianFilter5x5_Fp(dstML[i], srcML[i], this.maskType, cmask, 0);
/*     */             break;
/*     */           } 
/* 169 */           if (this.maskSize == 7) {
/* 171 */             Image.MedianFilter7x7_Fp(dstML[i], srcML[i], this.maskType, cmask, 0);
/*     */             break;
/*     */           } 
/* 178 */           Image.MedianFilterMxN_Fp(dstML[i], srcML[i], this.maskSize, this.maskSize, this.maskType, cmask, 0);
/*     */           break;
/*     */         default:
/* 188 */           className = getClass().getName();
/* 189 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 193 */     if (dstAccessor.isDataCopy())
/* 194 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMedianFilterOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
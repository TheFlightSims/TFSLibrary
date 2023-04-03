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
/*     */ import javax.media.jai.operator.MinFilterDescriptor;
/*     */ import javax.media.jai.operator.MinFilterShape;
/*     */ 
/*     */ final class MlibMinFilterOpImage extends AreaOpImage {
/*     */   protected int maskType;
/*     */   
/*     */   protected int maskSize;
/*     */   
/*     */   public MlibMinFilterOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, MinFilterShape maskType, int maskSize) {
/*  62 */     super(source, layout, config, true, extender, (maskSize - 1) / 2, (maskSize - 1) / 2, maskSize / 2, maskSize / 2);
/*  72 */     this.maskSize = maskSize;
/*     */   }
/*     */   
/*     */   private static int mapToMlibMaskType(MinFilterShape maskType) {
/*  76 */     if (maskType.equals(MinFilterDescriptor.MIN_MASK_SQUARE))
/*  77 */       return 0; 
/*  78 */     if (maskType.equals(MinFilterDescriptor.MIN_MASK_PLUS))
/*  79 */       return 1; 
/*  80 */     if (maskType.equals(MinFilterDescriptor.MIN_MASK_X))
/*  81 */       return 2; 
/*  82 */     if (maskType.equals(MinFilterDescriptor.MIN_MASK_SQUARE_SEPARABLE))
/*  84 */       return 3; 
/*  86 */     throw new RuntimeException(JaiI18N.getString("MinFilterOpImage0"));
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 101 */     Raster source = sources[0];
/* 102 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 104 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 106 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/* 108 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 110 */     int numBands = getSampleModel().getNumBands();
/* 113 */     int cmask = (1 << numBands) - 1;
/* 114 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 115 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 116 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/* 117 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 122 */           if (this.maskSize == 3) {
/* 124 */             Image.MinFilter3x3(dstML[i], srcML[i]);
/*     */             break;
/*     */           } 
/* 125 */           if (this.maskSize == 5) {
/* 127 */             Image.MinFilter5x5(dstML[i], srcML[i]);
/*     */             break;
/*     */           } 
/* 128 */           if (this.maskSize == 7)
/* 130 */             Image.MinFilter7x7(dstML[i], srcML[i]); 
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 136 */           if (this.maskSize == 3) {
/* 138 */             Image.MinFilter3x3_Fp(dstML[i], srcML[i]);
/*     */             break;
/*     */           } 
/* 139 */           if (this.maskSize == 5) {
/* 141 */             Image.MinFilter5x5_Fp(dstML[i], srcML[i]);
/*     */             break;
/*     */           } 
/* 142 */           if (this.maskSize == 7)
/* 144 */             Image.MinFilter7x7_Fp(dstML[i], srcML[i]); 
/*     */           break;
/*     */         default:
/* 148 */           className = getClass().getName();
/* 149 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 153 */     if (dstAccessor.isDataCopy())
/* 154 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMinFilterOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
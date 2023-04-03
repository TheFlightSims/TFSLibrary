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
/*     */ import javax.media.jai.operator.MaxFilterDescriptor;
/*     */ import javax.media.jai.operator.MaxFilterShape;
/*     */ 
/*     */ final class MlibMaxFilterOpImage extends AreaOpImage {
/*     */   protected int maskType;
/*     */   
/*     */   protected int maskSize;
/*     */   
/*     */   public MlibMaxFilterOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, MaxFilterShape maskType, int maskSize) {
/*  63 */     super(source, layout, config, true, extender, (maskSize - 1) / 2, (maskSize - 1) / 2, maskSize / 2, maskSize / 2);
/*  72 */     this.maskType = mapToMlibMaskType(maskType);
/*  73 */     this.maskSize = maskSize;
/*     */   }
/*     */   
/*     */   private static int mapToMlibMaskType(MaxFilterShape maskType) {
/*  77 */     if (maskType.equals(MaxFilterDescriptor.MAX_MASK_SQUARE))
/*  78 */       return 0; 
/*  79 */     if (maskType.equals(MaxFilterDescriptor.MAX_MASK_PLUS))
/*  80 */       return 1; 
/*  81 */     if (maskType.equals(MaxFilterDescriptor.MAX_MASK_X))
/*  82 */       return 2; 
/*  83 */     if (maskType.equals(MaxFilterDescriptor.MAX_MASK_SQUARE_SEPARABLE))
/*  84 */       return 3; 
/*  86 */     throw new RuntimeException(JaiI18N.getString("MaxFilterOpImage0"));
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
/* 117 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/* 118 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 123 */           if (this.maskSize == 3) {
/* 125 */             Image.MaxFilter3x3(dstML[i], srcML[i]);
/*     */             break;
/*     */           } 
/* 126 */           if (this.maskSize == 5) {
/* 128 */             Image.MaxFilter5x5(dstML[i], srcML[i]);
/*     */             break;
/*     */           } 
/* 129 */           if (this.maskSize == 7)
/* 131 */             Image.MaxFilter7x7(dstML[i], srcML[i]); 
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 137 */           if (this.maskSize == 3) {
/* 139 */             Image.MaxFilter3x3_Fp(dstML[i], srcML[i]);
/*     */             break;
/*     */           } 
/* 140 */           if (this.maskSize == 5) {
/* 142 */             Image.MaxFilter5x5_Fp(dstML[i], srcML[i]);
/*     */             break;
/*     */           } 
/* 143 */           if (this.maskSize == 7)
/* 145 */             Image.MaxFilter7x7_Fp(dstML[i], srcML[i]); 
/*     */           break;
/*     */         default:
/* 149 */           className = getClass().getName();
/* 150 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 154 */     if (dstAccessor.isDataCopy())
/* 155 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMaxFilterOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
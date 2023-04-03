/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.AreaOpImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ 
/*     */ final class MlibErode3PlusOpImage extends AreaOpImage {
/*     */   private static Map configHelper(Map configuration) {
/*     */     Map config;
/*  49 */     if (configuration == null) {
/*  51 */       config = new RenderingHints(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */     } else {
/*  56 */       config = configuration;
/*  61 */       if (!config.containsKey(JAI.KEY_REPLACE_INDEX_COLOR_MODEL)) {
/*  62 */         RenderingHints hints = (RenderingHints)configuration;
/*  63 */         config = (RenderingHints)hints.clone();
/*  64 */         config.put(JAI.KEY_REPLACE_INDEX_COLOR_MODEL, Boolean.FALSE);
/*     */       } 
/*     */     } 
/*  68 */     return config;
/*     */   }
/*     */   
/*     */   public MlibErode3PlusOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout) {
/*  90 */     super(source, layout, configHelper(config), true, extender, 1, 1, 1, 1);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 116 */     Raster source = sources[0];
/* 117 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 119 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/* 121 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag, true);
/* 123 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag, true);
/* 125 */     int numBands = getSampleModel().getNumBands();
/* 128 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 129 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 130 */     for (int i = 0; i < dstML.length; i++) {
/*     */       String className;
/* 131 */       switch (dstAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 136 */           Image.Erode4(dstML[i], srcML[i]);
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 140 */           Image.Erode4_Fp(dstML[i], srcML[i]);
/*     */           break;
/*     */         default:
/* 143 */           className = getClass().getName();
/* 144 */           throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */       } 
/*     */     } 
/* 148 */     if (dstAccessor.isDataCopy())
/* 149 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibErode3PlusOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
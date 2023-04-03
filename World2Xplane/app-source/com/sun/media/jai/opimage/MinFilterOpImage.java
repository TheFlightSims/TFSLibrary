/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.AreaOpImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ import javax.media.jai.operator.MinFilterShape;
/*     */ 
/*     */ abstract class MinFilterOpImage extends AreaOpImage {
/*     */   protected MinFilterShape maskType;
/*     */   
/*     */   protected int maskSize;
/*     */   
/*     */   public MinFilterOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, MinFilterShape maskType, int maskSize) {
/*  61 */     super(source, layout, config, true, extender, (maskSize - 1) / 2, (maskSize - 1) / 2, maskSize / 2, maskSize / 2);
/*  71 */     this.maskType = maskType;
/*  72 */     this.maskSize = maskSize;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  88 */     RasterFormatTag[] formatTags = getFormatTags();
/*  90 */     Raster source = sources[0];
/*  91 */     Rectangle srcRect = mapDestRect(destRect, 0);
/*  94 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSource(0).getColorModel());
/*  98 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 102 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 104 */         byteLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 2:
/* 107 */         shortLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 1:
/* 110 */         ushortLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 3:
/* 113 */         intLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 4:
/* 116 */         floatLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 5:
/* 119 */         doubleLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */     } 
/* 126 */     if (dstAccessor.isDataCopy()) {
/* 127 */       dstAccessor.clampDataArrays();
/* 128 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void byteLoop(RasterAccessor paramRasterAccessor1, RasterAccessor paramRasterAccessor2, int paramInt);
/*     */   
/*     */   protected abstract void shortLoop(RasterAccessor paramRasterAccessor1, RasterAccessor paramRasterAccessor2, int paramInt);
/*     */   
/*     */   protected abstract void ushortLoop(RasterAccessor paramRasterAccessor1, RasterAccessor paramRasterAccessor2, int paramInt);
/*     */   
/*     */   protected abstract void intLoop(RasterAccessor paramRasterAccessor1, RasterAccessor paramRasterAccessor2, int paramInt);
/*     */   
/*     */   protected abstract void floatLoop(RasterAccessor paramRasterAccessor1, RasterAccessor paramRasterAccessor2, int paramInt);
/*     */   
/*     */   protected abstract void doubleLoop(RasterAccessor paramRasterAccessor1, RasterAccessor paramRasterAccessor2, int paramInt);
/*     */   
/*     */   static int minFilter(int[] data) {
/* 164 */     if (data.length == 3) {
/* 165 */       int a = data[0];
/* 166 */       int b = data[1];
/* 167 */       int c = data[2];
/* 168 */       if (a < b)
/* 169 */         return (a < c) ? a : c; 
/* 171 */       return (b < c) ? b : c;
/*     */     } 
/* 175 */     int min = data[0];
/* 176 */     for (int i = 1; i < data.length; i++) {
/* 177 */       if (data[i] < min)
/* 178 */         min = data[i]; 
/*     */     } 
/* 180 */     return min;
/*     */   }
/*     */   
/*     */   static float minFilterFloat(float[] data) {
/* 185 */     if (data.length == 3) {
/* 186 */       float a = data[0];
/* 187 */       float b = data[1];
/* 188 */       float c = data[2];
/* 189 */       if (a < b)
/* 190 */         return (a < c) ? a : c; 
/* 192 */       return (b < c) ? b : c;
/*     */     } 
/* 196 */     float min = data[0];
/* 197 */     for (int i = 1; i < data.length; i++) {
/* 198 */       if (data[i] < min)
/* 199 */         min = data[i]; 
/*     */     } 
/* 201 */     return min;
/*     */   }
/*     */   
/*     */   static double minFilterDouble(double[] data) {
/* 208 */     if (data.length == 3) {
/* 209 */       double a = data[0];
/* 210 */       double b = data[1];
/* 211 */       double c = data[2];
/* 212 */       if (a < b)
/* 213 */         return (a < c) ? a : c; 
/* 215 */       return (b < c) ? b : c;
/*     */     } 
/* 219 */     double min = data[0];
/* 220 */     for (int i = 1; i < data.length; i++) {
/* 221 */       if (data[i] < min)
/* 222 */         min = data[i]; 
/*     */     } 
/* 224 */     return min;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MinFilterOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
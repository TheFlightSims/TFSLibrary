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
/*     */ import javax.media.jai.operator.MaxFilterShape;
/*     */ 
/*     */ abstract class MaxFilterOpImage extends AreaOpImage {
/*     */   protected MaxFilterShape maskType;
/*     */   
/*     */   protected int maskSize;
/*     */   
/*     */   public MaxFilterOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, MaxFilterShape maskType, int maskSize) {
/*  62 */     super(source, layout, config, true, extender, (maskSize - 1) / 2, (maskSize - 1) / 2, maskSize / 2, maskSize / 2);
/*  72 */     this.maskType = maskType;
/*  73 */     this.maskSize = maskSize;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  89 */     RasterFormatTag[] formatTags = getFormatTags();
/*  91 */     Raster source = sources[0];
/*  92 */     Rectangle srcRect = mapDestRect(destRect, 0);
/*  95 */     RasterAccessor srcAccessor = new RasterAccessor(source, srcRect, formatTags[0], getSourceImage(0).getColorModel());
/*  99 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 103 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 105 */         byteLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 2:
/* 108 */         shortLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 1:
/* 111 */         ushortLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 3:
/* 114 */         intLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 4:
/* 117 */         floatLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */       case 5:
/* 120 */         doubleLoop(srcAccessor, dstAccessor, this.maskSize);
/*     */         break;
/*     */     } 
/* 127 */     if (dstAccessor.isDataCopy()) {
/* 128 */       dstAccessor.clampDataArrays();
/* 129 */       dstAccessor.copyDataToRaster();
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
/*     */   static final int maxFilter(int[] data) {
/* 165 */     if (data.length == 3) {
/* 166 */       int a = data[0];
/* 167 */       int b = data[1];
/* 168 */       int c = data[2];
/* 169 */       if (a < b)
/* 170 */         return (b < c) ? c : b; 
/* 172 */       return (a < c) ? c : a;
/*     */     } 
/* 176 */     int max = data[0];
/* 177 */     for (int i = 1; i < data.length; i++) {
/* 178 */       if (max < data[i])
/* 179 */         max = data[i]; 
/*     */     } 
/* 180 */     return max;
/*     */   }
/*     */   
/*     */   static final float maxFilterFloat(float[] data) {
/* 186 */     if (data.length == 3) {
/* 187 */       float a = data[0];
/* 188 */       float b = data[1];
/* 189 */       float c = data[2];
/* 190 */       if (a < b)
/* 191 */         return (b < c) ? c : b; 
/* 193 */       return (a < c) ? c : a;
/*     */     } 
/* 197 */     float max = data[0];
/* 198 */     for (int i = 1; i < data.length; i++) {
/* 199 */       if (max < data[i])
/* 200 */         max = data[i]; 
/*     */     } 
/* 201 */     return max;
/*     */   }
/*     */   
/*     */   static final double maxFilterDouble(double[] data) {
/* 208 */     if (data.length == 3) {
/* 209 */       double a = data[0];
/* 210 */       double b = data[1];
/* 211 */       double c = data[2];
/* 212 */       if (a < b)
/* 213 */         return (b < c) ? c : b; 
/* 215 */       return (a < c) ? c : a;
/*     */     } 
/* 219 */     double max = data[0];
/* 220 */     for (int i = 1; i < data.length; i++) {
/* 221 */       if (max < data[i])
/* 222 */         max = data[i]; 
/*     */     } 
/* 223 */     return max;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MaxFilterOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
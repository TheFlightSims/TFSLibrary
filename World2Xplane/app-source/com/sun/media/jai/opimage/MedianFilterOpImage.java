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
/*     */ import javax.media.jai.operator.MedianFilterShape;
/*     */ 
/*     */ abstract class MedianFilterOpImage extends AreaOpImage {
/*     */   protected MedianFilterShape maskType;
/*     */   
/*     */   protected int maskSize;
/*     */   
/*     */   public MedianFilterOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, MedianFilterShape maskType, int maskSize) {
/*  62 */     super(source, layout, config, true, extender, (maskSize - 1) / 2, (maskSize - 1) / 2, maskSize / 2, maskSize / 2);
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
/*     */   protected int medianFilter(int[] data) {
/* 164 */     if (data.length == 3) {
/* 165 */       int a = data[0];
/* 166 */       int b = data[1];
/* 167 */       int c = data[2];
/* 168 */       if (a < b) {
/* 169 */         if (b < c)
/* 170 */           return b; 
/* 172 */         if (c > a)
/* 173 */           return c; 
/* 175 */         return a;
/*     */       } 
/* 179 */       if (a < c)
/* 180 */         return a; 
/* 182 */       if (b < c)
/* 183 */         return c; 
/* 185 */       return b;
/*     */     } 
/* 190 */     int left = 0;
/* 191 */     int right = data.length - 1;
/* 192 */     int target = data.length / 2;
/*     */     while (true) {
/* 195 */       int oleft = left;
/* 196 */       int oright = right;
/* 197 */       int mid = data[(left + right) / 2];
/*     */       while (true) {
/* 199 */         while (data[left] < mid)
/* 200 */           left++; 
/* 202 */         while (mid < data[right])
/* 203 */           right--; 
/* 205 */         if (left <= right) {
/* 206 */           int tmp = data[left];
/* 207 */           data[left] = data[right];
/* 208 */           data[right] = tmp;
/* 209 */           left++;
/* 210 */           right--;
/*     */         } 
/* 212 */         if (left > right) {
/* 213 */           if (oleft < right && right >= target) {
/* 214 */             left = oleft;
/*     */             continue;
/*     */           } 
/* 215 */           if (left < oright && left <= target)
/*     */             break; 
/* 218 */           return data[target];
/*     */         } 
/*     */       } 
/*     */       right = oright;
/*     */     } 
/* 218 */     return data[target];
/*     */   }
/*     */   
/*     */   protected float medianFilterFloat(float[] data) {
/* 225 */     if (data.length == 3) {
/* 226 */       float a = data[0];
/* 227 */       float b = data[1];
/* 228 */       float c = data[2];
/* 229 */       if (a < b) {
/* 230 */         if (b < c)
/* 231 */           return b; 
/* 233 */         if (c > a)
/* 234 */           return c; 
/* 236 */         return a;
/*     */       } 
/* 240 */       if (a < c)
/* 241 */         return a; 
/* 243 */       if (b < c)
/* 244 */         return c; 
/* 246 */       return b;
/*     */     } 
/* 251 */     int left = 0;
/* 252 */     int right = data.length - 1;
/* 253 */     int target = data.length / 2;
/*     */     while (true) {
/* 256 */       int oleft = left;
/* 257 */       int oright = right;
/* 258 */       float mid = data[(left + right) / 2];
/*     */       while (true) {
/* 260 */         while (data[left] < mid)
/* 261 */           left++; 
/* 263 */         while (mid < data[right])
/* 264 */           right--; 
/* 266 */         if (left <= right) {
/* 267 */           float tmp = data[left];
/* 268 */           data[left] = data[right];
/* 269 */           data[right] = tmp;
/* 270 */           left++;
/* 271 */           right--;
/*     */         } 
/* 273 */         if (left > right) {
/* 274 */           if (oleft < right && right >= target) {
/* 275 */             left = oleft;
/*     */             continue;
/*     */           } 
/* 276 */           if (left < oright && left <= target)
/*     */             break; 
/* 279 */           return data[target];
/*     */         } 
/*     */       } 
/*     */       right = oright;
/*     */     } 
/* 279 */     return data[target];
/*     */   }
/*     */   
/*     */   protected double medianFilterDouble(double[] data) {
/* 286 */     if (data.length == 3) {
/* 287 */       double a = data[0];
/* 288 */       double b = data[1];
/* 289 */       double c = data[2];
/* 290 */       if (a < b) {
/* 291 */         if (b < c)
/* 292 */           return b; 
/* 294 */         if (c > a)
/* 295 */           return c; 
/* 297 */         return a;
/*     */       } 
/* 301 */       if (a < c)
/* 302 */         return a; 
/* 304 */       if (b < c)
/* 305 */         return c; 
/* 307 */       return b;
/*     */     } 
/* 312 */     int left = 0;
/* 313 */     int right = data.length - 1;
/* 314 */     int target = data.length / 2;
/*     */     while (true) {
/* 317 */       int oleft = left;
/* 318 */       int oright = right;
/* 319 */       double mid = data[(left + right) / 2];
/*     */       while (true) {
/* 321 */         while (data[left] < mid)
/* 322 */           left++; 
/* 324 */         while (mid < data[right])
/* 325 */           right--; 
/* 327 */         if (left <= right) {
/* 328 */           double tmp = data[left];
/* 329 */           data[left] = data[right];
/* 330 */           data[right] = tmp;
/* 331 */           left++;
/* 332 */           right--;
/*     */         } 
/* 334 */         if (left > right) {
/* 335 */           if (oleft < right && right >= target) {
/* 336 */             left = oleft;
/*     */             continue;
/*     */           } 
/* 337 */           if (left < oright && left <= target)
/*     */             break; 
/* 340 */           return data[target];
/*     */         } 
/*     */       } 
/*     */       right = oright;
/*     */     } 
/* 340 */     return data[target];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MedianFilterOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
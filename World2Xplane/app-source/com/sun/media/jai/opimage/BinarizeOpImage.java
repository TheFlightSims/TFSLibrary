/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PackedImageData;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.UnpackedImageData;
/*     */ 
/*     */ final class BinarizeOpImage extends PointOpImage {
/*  53 */   private static byte[] byteTable = new byte[] { Byte.MIN_VALUE, 64, 32, 16, 8, 4, 2, 1 };
/*     */   
/*  62 */   private static int[] bitsOn = null;
/*     */   
/*     */   private double threshold;
/*     */   
/*     */   public BinarizeOpImage(RenderedImage source, Map config, ImageLayout layout, double threshold) {
/*  78 */     super(source, layoutHelper(source, layout, config), config, true);
/*  80 */     if (source.getSampleModel().getNumBands() != 1)
/*  81 */       throw new IllegalArgumentException(JaiI18N.getString("BinarizeOpImage0")); 
/*  84 */     this.threshold = threshold;
/*     */   }
/*     */   
/*     */   private static ImageLayout layoutHelper(RenderedImage source, ImageLayout il, Map config) {
/*  92 */     ImageLayout layout = (il == null) ? new ImageLayout() : (ImageLayout)il.clone();
/*  95 */     SampleModel sm = layout.getSampleModel(source);
/*  96 */     if (!ImageUtil.isBinary(sm)) {
/*  97 */       sm = new MultiPixelPackedSampleModel(0, layout.getTileWidth(source), layout.getTileHeight(source), 1);
/* 101 */       layout.setSampleModel(sm);
/*     */     } 
/* 104 */     ColorModel cm = layout.getColorModel(null);
/* 105 */     if (cm == null || !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 107 */       layout.setColorModel(ImageUtil.getCompatibleColorModel(sm, config)); 
/* 111 */     return layout;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 127 */     switch (sources[0].getSampleModel().getDataType()) {
/*     */       case 0:
/* 129 */         byteLoop(sources[0], dest, destRect);
/*     */         return;
/*     */       case 2:
/* 133 */         shortLoop(sources[0], dest, destRect);
/*     */         return;
/*     */       case 1:
/* 136 */         ushortLoop(sources[0], dest, destRect);
/*     */         return;
/*     */       case 3:
/* 139 */         intLoop(sources[0], dest, destRect);
/*     */         return;
/*     */       case 4:
/* 143 */         floatLoop(sources[0], dest, destRect);
/*     */         return;
/*     */       case 5:
/* 146 */         doubleLoop(sources[0], dest, destRect);
/*     */         return;
/*     */     } 
/* 150 */     throw new RuntimeException(JaiI18N.getString("BinarizeOpImage1"));
/*     */   }
/*     */   
/*     */   private void byteLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 158 */     if (this.threshold <= 0.0D) {
/* 160 */       setTo1(dest, destRect);
/*     */       return;
/*     */     } 
/* 162 */     if (this.threshold > 255.0D)
/*     */       return; 
/* 167 */     short thresholdI = (short)(int)Math.ceil(this.threshold);
/* 173 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 175 */     PixelAccessor pa = new PixelAccessor(dest.getSampleModel(), null);
/* 176 */     PackedImageData pid = pa.getPackedPixels(dest, destRect, true, false);
/* 177 */     int offset = pid.offset;
/* 178 */     PixelAccessor srcPa = new PixelAccessor(source.getSampleModel(), null);
/* 180 */     UnpackedImageData srcImD = srcPa.getPixels(source, srcRect, 0, false);
/* 181 */     int srcOffset = srcImD.bandOffsets[0];
/* 182 */     byte[] srcData = ((byte[][])srcImD.data)[0];
/* 183 */     int pixelStride = srcImD.pixelStride;
/* 185 */     int ind0 = pid.bitOffset;
/* 186 */     for (int h = 0; h < destRect.height; h++) {
/* 187 */       int indE = ind0 + destRect.width;
/*     */       int s;
/* 188 */       for (int b = ind0; b < indE; b++, s += pixelStride) {
/* 189 */         if ((srcData[s] & 0xFF) >= thresholdI)
/* 190 */           pid.data[offset + (b >> 3)] = (byte)(pid.data[offset + (b >> 3)] | byteTable[b % 8]); 
/*     */       } 
/* 193 */       offset += pid.lineStride;
/* 194 */       srcOffset += srcImD.lineStride;
/*     */     } 
/* 196 */     pa.setPackedPixels(pid);
/*     */   }
/*     */   
/*     */   private void shortLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 205 */     if (this.threshold <= -32768.0D) {
/* 207 */       setTo1(dest, destRect);
/*     */       return;
/*     */     } 
/* 209 */     if (this.threshold > 32767.0D)
/*     */       return; 
/* 214 */     short thresholdS = (short)(int)Math.ceil(this.threshold);
/* 220 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 222 */     PixelAccessor pa = new PixelAccessor(dest.getSampleModel(), null);
/* 223 */     PackedImageData pid = pa.getPackedPixels(dest, destRect, true, false);
/* 224 */     int offset = pid.offset;
/* 225 */     PixelAccessor srcPa = new PixelAccessor(source.getSampleModel(), null);
/* 227 */     UnpackedImageData srcImD = srcPa.getPixels(source, srcRect, 2, false);
/* 228 */     int srcOffset = srcImD.bandOffsets[0];
/* 229 */     short[] srcData = ((short[][])srcImD.data)[0];
/* 230 */     int pixelStride = srcImD.pixelStride;
/* 232 */     int ind0 = pid.bitOffset;
/* 233 */     for (int h = 0; h < destRect.height; h++) {
/* 234 */       int indE = ind0 + destRect.width;
/*     */       int s;
/* 235 */       for (int b = ind0; b < indE; b++, s += pixelStride) {
/* 236 */         if (srcData[s] >= thresholdS)
/* 237 */           pid.data[offset + (b >> 3)] = (byte)(pid.data[offset + (b >> 3)] | byteTable[b % 8]); 
/*     */       } 
/* 240 */       offset += pid.lineStride;
/* 241 */       srcOffset += srcImD.lineStride;
/*     */     } 
/* 243 */     pa.setPackedPixels(pid);
/*     */   }
/*     */   
/*     */   private void ushortLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 252 */     if (this.threshold <= 0.0D) {
/* 254 */       setTo1(dest, destRect);
/*     */       return;
/*     */     } 
/* 256 */     if (this.threshold > 65535.0D)
/*     */       return; 
/* 261 */     int thresholdI = (int)Math.ceil(this.threshold);
/* 267 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 269 */     PixelAccessor pa = new PixelAccessor(dest.getSampleModel(), null);
/* 270 */     PackedImageData pid = pa.getPackedPixels(dest, destRect, true, false);
/* 271 */     int offset = pid.offset;
/* 272 */     PixelAccessor srcPa = new PixelAccessor(source.getSampleModel(), null);
/* 274 */     UnpackedImageData srcImD = srcPa.getPixels(source, srcRect, 1, false);
/* 275 */     int srcOffset = srcImD.bandOffsets[0];
/* 276 */     short[] srcData = ((short[][])srcImD.data)[0];
/* 277 */     int pixelStride = srcImD.pixelStride;
/* 279 */     int ind0 = pid.bitOffset;
/* 280 */     for (int h = 0; h < destRect.height; h++) {
/* 281 */       int indE = ind0 + destRect.width;
/*     */       int s;
/* 282 */       for (int b = ind0; b < indE; b++, s += pixelStride) {
/* 283 */         if ((srcData[s] & 0xFFFF) >= thresholdI)
/* 284 */           pid.data[offset + (b >> 3)] = (byte)(pid.data[offset + (b >> 3)] | byteTable[b % 8]); 
/*     */       } 
/* 287 */       offset += pid.lineStride;
/* 288 */       srcOffset += srcImD.lineStride;
/*     */     } 
/* 290 */     pa.setPackedPixels(pid);
/*     */   }
/*     */   
/*     */   private void intLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 299 */     if (this.threshold <= -2.147483648E9D) {
/* 301 */       setTo1(dest, destRect);
/*     */       return;
/*     */     } 
/* 303 */     if (this.threshold > 2.147483647E9D)
/*     */       return; 
/* 310 */     int thresholdI = (int)Math.ceil(this.threshold);
/* 316 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 318 */     PixelAccessor pa = new PixelAccessor(dest.getSampleModel(), null);
/* 319 */     PackedImageData pid = pa.getPackedPixels(dest, destRect, true, false);
/* 320 */     int offset = pid.offset;
/* 321 */     PixelAccessor srcPa = new PixelAccessor(source.getSampleModel(), null);
/* 323 */     UnpackedImageData srcImD = srcPa.getPixels(source, srcRect, 3, false);
/* 324 */     int srcOffset = srcImD.bandOffsets[0];
/* 325 */     int[] srcData = ((int[][])srcImD.data)[0];
/* 326 */     int pixelStride = srcImD.pixelStride;
/* 328 */     int ind0 = pid.bitOffset;
/* 329 */     for (int h = 0; h < destRect.height; h++) {
/* 330 */       int indE = ind0 + destRect.width;
/*     */       int s;
/* 331 */       for (int b = ind0; b < indE; b++, s += pixelStride) {
/* 332 */         if (srcData[s] >= this.threshold)
/* 333 */           pid.data[offset + (b >> 3)] = (byte)(pid.data[offset + (b >> 3)] | byteTable[b % 8]); 
/*     */       } 
/* 336 */       offset += pid.lineStride;
/* 337 */       srcOffset += srcImD.lineStride;
/*     */     } 
/* 339 */     pa.setPackedPixels(pid);
/*     */   }
/*     */   
/*     */   private void floatLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 348 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 350 */     PixelAccessor pa = new PixelAccessor(dest.getSampleModel(), null);
/* 351 */     PackedImageData pid = pa.getPackedPixels(dest, destRect, true, false);
/* 352 */     int offset = pid.offset;
/* 353 */     PixelAccessor srcPa = new PixelAccessor(source.getSampleModel(), null);
/* 355 */     UnpackedImageData srcImD = srcPa.getPixels(source, srcRect, 4, false);
/* 356 */     int srcOffset = srcImD.bandOffsets[0];
/* 357 */     float[] srcData = ((float[][])srcImD.data)[0];
/* 358 */     int pixelStride = srcImD.pixelStride;
/* 360 */     int ind0 = pid.bitOffset;
/* 361 */     for (int h = 0; h < destRect.height; h++) {
/* 362 */       int indE = ind0 + destRect.width;
/*     */       int s;
/* 363 */       for (int b = ind0; b < indE; b++, s += pixelStride) {
/* 364 */         if (srcData[s] > this.threshold)
/* 365 */           pid.data[offset + (b >> 3)] = (byte)(pid.data[offset + (b >> 3)] | byteTable[b % 8]); 
/*     */       } 
/* 368 */       offset += pid.lineStride;
/* 369 */       srcOffset += srcImD.lineStride;
/*     */     } 
/* 371 */     pa.setPackedPixels(pid);
/*     */   }
/*     */   
/*     */   private void doubleLoop(Raster source, WritableRaster dest, Rectangle destRect) {
/* 379 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 381 */     PixelAccessor pa = new PixelAccessor(dest.getSampleModel(), null);
/* 382 */     PackedImageData pid = pa.getPackedPixels(dest, destRect, true, false);
/* 383 */     int offset = pid.offset;
/* 384 */     PixelAccessor srcPa = new PixelAccessor(source.getSampleModel(), null);
/* 386 */     UnpackedImageData srcImD = srcPa.getPixels(source, srcRect, 5, false);
/* 387 */     int srcOffset = srcImD.bandOffsets[0];
/* 388 */     double[] srcData = ((double[][])srcImD.data)[0];
/* 389 */     int pixelStride = srcImD.pixelStride;
/* 391 */     int ind0 = pid.bitOffset;
/* 392 */     for (int h = 0; h < destRect.height; h++) {
/* 393 */       int indE = ind0 + destRect.width;
/*     */       int s;
/* 394 */       for (int b = ind0; b < indE; b++, s += pixelStride) {
/* 395 */         if (srcData[s] > this.threshold)
/* 396 */           pid.data[offset + (b >> 3)] = (byte)(pid.data[offset + (b >> 3)] | byteTable[b % 8]); 
/*     */       } 
/* 399 */       offset += pid.lineStride;
/* 400 */       srcOffset += srcImD.lineStride;
/*     */     } 
/* 402 */     pa.setPackedPixels(pid);
/*     */   }
/*     */   
/*     */   private void setTo1(Raster dest, Rectangle destRect) {
/* 408 */     initBitsOn();
/* 409 */     PixelAccessor pa = new PixelAccessor(dest.getSampleModel(), null);
/* 410 */     PackedImageData pid = pa.getPackedPixels(dest, destRect, true, false);
/* 411 */     int offset = pid.offset;
/* 413 */     for (int h = 0; h < destRect.height; h++) {
/* 414 */       int ind0 = pid.bitOffset;
/* 415 */       int indE = ind0 + destRect.width - 1;
/* 416 */       if (indE < 8) {
/* 418 */         pid.data[offset] = (byte)(pid.data[offset] | bitsOn[indE]);
/*     */       } else {
/* 421 */         pid.data[offset] = (byte)(pid.data[offset] | bitsOn[7]);
/* 423 */         for (int b = offset + 1; b <= offset + (indE - 7) / 8; b++)
/* 424 */           pid.data[b] = -1; 
/* 428 */         int remBits = indE % 8;
/* 429 */         if (remBits % 8 != 7) {
/* 430 */           indE = offset + indE / 8;
/* 431 */           pid.data[indE] = (byte)(pid.data[indE] | bitsOn[remBits]);
/*     */         } 
/*     */       } 
/* 434 */       offset += pid.lineStride;
/*     */     } 
/* 436 */     pa.setPackedPixels(pid);
/*     */   }
/*     */   
/*     */   private static synchronized void initBitsOn() {
/* 443 */     if (bitsOn != null)
/*     */       return; 
/* 446 */     bitsOn = new int[64];
/* 447 */     for (int i = 0; i < 8; i++) {
/* 448 */       for (int j = i; j < 8; j++) {
/* 449 */         int bi = 255 >> i;
/* 450 */         int bj = 255 << 7 - j;
/* 451 */         bitsOn[j + (i << 3)] = bi & bj;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\BinarizeOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
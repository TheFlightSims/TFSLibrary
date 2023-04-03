/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class AddCollectionOpImage extends PointOpImage {
/*  42 */   private byte[][] byteTable = (byte[][])null;
/*     */   
/*     */   private synchronized void initByteTable() {
/*  46 */     if (this.byteTable != null)
/*     */       return; 
/*  51 */     this.byteTable = new byte[256][256];
/*  52 */     for (int j = 0; j < 256; j++) {
/*  53 */       byte[] t = this.byteTable[j];
/*  54 */       for (int i = 0; i < 256; i++)
/*  55 */         t[i] = ImageUtil.clampBytePositive(j + i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public AddCollectionOpImage(Collection sources, Map config, ImageLayout layout) {
/*  69 */     super(vectorize(sources), layout, config, true);
/*     */   }
/*     */   
/*     */   private static Vector vectorize(Collection sources) {
/*  74 */     if (sources instanceof Vector)
/*  75 */       return (Vector)sources; 
/*  77 */     Vector v = new Vector(sources.size());
/*  78 */     Iterator iter = sources.iterator();
/*  79 */     while (iter.hasNext())
/*  80 */       v.add(iter.next()); 
/*  82 */     return v;
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  98 */     RasterFormatTag[] formatTags = getFormatTags();
/* 100 */     int numSrcs = getNumSources();
/* 102 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[numSrcs], getColorModel());
/* 105 */     RasterAccessor[] srcs = new RasterAccessor[numSrcs];
/* 106 */     for (int i = 0; i < numSrcs; i++) {
/* 107 */       Rectangle srcRect = mapDestRect(destRect, i);
/* 108 */       srcs[i] = new RasterAccessor(sources[i], srcRect, formatTags[i], getSourceImage(i).getColorModel());
/*     */     } 
/* 113 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 115 */         computeRectByte(srcs, dst);
/*     */         break;
/*     */       case 1:
/* 118 */         computeRectUShort(srcs, dst);
/*     */         break;
/*     */       case 2:
/* 121 */         computeRectShort(srcs, dst);
/*     */         break;
/*     */       case 3:
/* 124 */         computeRectInt(srcs, dst);
/*     */         break;
/*     */       case 4:
/* 127 */         computeRectFloat(srcs, dst);
/*     */         break;
/*     */       case 5:
/* 130 */         computeRectDouble(srcs, dst);
/*     */         break;
/*     */     } 
/* 134 */     if (dst.needsClamping())
/* 136 */       dst.clampDataArrays(); 
/* 138 */     dst.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor[] srcs, RasterAccessor dst) {
/* 143 */     initByteTable();
/* 145 */     int dstWidth = dst.getWidth();
/* 146 */     int dstHeight = dst.getHeight();
/* 147 */     int dstBands = dst.getNumBands();
/* 149 */     int dstLineStride = dst.getScanlineStride();
/* 150 */     int dstPixelStride = dst.getPixelStride();
/* 151 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 152 */     byte[][] dstData = dst.getByteDataArrays();
/* 154 */     int numSrcs = getNumSources();
/* 156 */     for (int i = 0; i < numSrcs; i++) {
/* 157 */       RasterAccessor src = srcs[i];
/* 158 */       int srcLineStride = src.getScanlineStride();
/* 159 */       int srcPixelStride = src.getPixelStride();
/* 160 */       int[] srcBandOffsets = src.getBandOffsets();
/* 161 */       byte[][] srcData = src.getByteDataArrays();
/* 163 */       for (int b = 0; b < dstBands; b++) {
/* 164 */         int dstLineOffset = dstBandOffsets[b];
/* 165 */         int srcLineOffset = srcBandOffsets[b];
/* 167 */         byte[] d = dstData[b];
/* 168 */         byte[] s = srcData[b];
/* 170 */         for (int h = 0; h < dstHeight; h++) {
/* 171 */           int dstPixelOffset = dstLineOffset;
/* 172 */           int srcPixelOffset = srcLineOffset;
/* 174 */           dstLineOffset += dstLineStride;
/* 175 */           srcLineOffset += srcLineStride;
/* 177 */           for (int w = 0; w < dstWidth; w++) {
/* 178 */             d[dstPixelOffset] = this.byteTable[d[dstPixelOffset] & 0xFF][s[srcPixelOffset] & 0xFF];
/* 181 */             dstPixelOffset += dstPixelStride;
/* 182 */             srcPixelOffset += srcPixelStride;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor[] srcs, RasterAccessor dst) {
/* 191 */     int dstWidth = dst.getWidth();
/* 192 */     int dstHeight = dst.getHeight();
/* 193 */     int dstBands = dst.getNumBands();
/* 195 */     int dstLineStride = dst.getScanlineStride();
/* 196 */     int dstPixelStride = dst.getPixelStride();
/* 197 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 198 */     short[][] dstData = dst.getShortDataArrays();
/* 200 */     int numSrcs = getNumSources();
/* 202 */     for (int i = 0; i < numSrcs; i++) {
/* 203 */       RasterAccessor src = srcs[i];
/* 204 */       int srcLineStride = src.getScanlineStride();
/* 205 */       int srcPixelStride = src.getPixelStride();
/* 206 */       int[] srcBandOffsets = src.getBandOffsets();
/* 207 */       short[][] srcData = src.getShortDataArrays();
/* 209 */       for (int b = 0; b < dstBands; b++) {
/* 210 */         int dstLineOffset = dstBandOffsets[b];
/* 211 */         int srcLineOffset = srcBandOffsets[b];
/* 213 */         short[] d = dstData[b];
/* 214 */         short[] s = srcData[b];
/* 216 */         for (int h = 0; h < dstHeight; h++) {
/* 217 */           int dstPixelOffset = dstLineOffset;
/* 218 */           int srcPixelOffset = srcLineOffset;
/* 220 */           dstLineOffset += dstLineStride;
/* 221 */           srcLineOffset += srcLineStride;
/* 223 */           for (int w = 0; w < dstWidth; w++) {
/* 224 */             d[dstPixelOffset] = ImageUtil.clampUShortPositive((d[dstPixelOffset] & 0xFFFF) + (s[srcPixelOffset] & 0xFFFF));
/* 228 */             dstPixelOffset += dstPixelStride;
/* 229 */             srcPixelOffset += srcPixelStride;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor[] srcs, RasterAccessor dst) {
/* 238 */     int dstWidth = dst.getWidth();
/* 239 */     int dstHeight = dst.getHeight();
/* 240 */     int dstBands = dst.getNumBands();
/* 242 */     int dstLineStride = dst.getScanlineStride();
/* 243 */     int dstPixelStride = dst.getPixelStride();
/* 244 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 245 */     short[][] dstData = dst.getShortDataArrays();
/* 247 */     int numSrcs = getNumSources();
/* 249 */     for (int i = 0; i < numSrcs; i++) {
/* 250 */       RasterAccessor src = srcs[i];
/* 251 */       int srcLineStride = src.getScanlineStride();
/* 252 */       int srcPixelStride = src.getPixelStride();
/* 253 */       int[] srcBandOffsets = src.getBandOffsets();
/* 254 */       short[][] srcData = src.getShortDataArrays();
/* 256 */       for (int b = 0; b < dstBands; b++) {
/* 257 */         int dstLineOffset = dstBandOffsets[b];
/* 258 */         int srcLineOffset = srcBandOffsets[b];
/* 260 */         short[] d = dstData[b];
/* 261 */         short[] s = srcData[b];
/* 263 */         for (int h = 0; h < dstHeight; h++) {
/* 264 */           int dstPixelOffset = dstLineOffset;
/* 265 */           int srcPixelOffset = srcLineOffset;
/* 267 */           dstLineOffset += dstLineStride;
/* 268 */           srcLineOffset += srcLineStride;
/* 270 */           for (int w = 0; w < dstWidth; w++) {
/* 271 */             d[dstPixelOffset] = ImageUtil.clampShort(d[dstPixelOffset] + s[srcPixelOffset]);
/* 275 */             dstPixelOffset += dstPixelStride;
/* 276 */             srcPixelOffset += srcPixelStride;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor[] srcs, RasterAccessor dst) {
/* 285 */     int dstWidth = dst.getWidth();
/* 286 */     int dstHeight = dst.getHeight();
/* 287 */     int dstBands = dst.getNumBands();
/* 289 */     int dstLineStride = dst.getScanlineStride();
/* 290 */     int dstPixelStride = dst.getPixelStride();
/* 291 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 292 */     int[][] dstData = dst.getIntDataArrays();
/* 294 */     int numSrcs = getNumSources();
/* 296 */     for (int i = 0; i < numSrcs; i++) {
/* 297 */       RasterAccessor src = srcs[i];
/* 298 */       int srcLineStride = src.getScanlineStride();
/* 299 */       int srcPixelStride = src.getPixelStride();
/* 300 */       int[] srcBandOffsets = src.getBandOffsets();
/* 301 */       int[][] srcData = src.getIntDataArrays();
/* 303 */       for (int b = 0; b < dstBands; b++) {
/* 304 */         int dstLineOffset = dstBandOffsets[b];
/* 305 */         int srcLineOffset = srcBandOffsets[b];
/* 307 */         int[] d = dstData[b];
/* 308 */         int[] s = srcData[b];
/* 310 */         for (int h = 0; h < dstHeight; h++) {
/* 311 */           int dstPixelOffset = dstLineOffset;
/* 312 */           int srcPixelOffset = srcLineOffset;
/* 314 */           dstLineOffset += dstLineStride;
/* 315 */           srcLineOffset += srcLineStride;
/* 317 */           for (int w = 0; w < dstWidth; w++) {
/* 318 */             d[dstPixelOffset] = ImageUtil.clampInt(d[dstPixelOffset] + s[srcPixelOffset]);
/* 322 */             dstPixelOffset += dstPixelStride;
/* 323 */             srcPixelOffset += srcPixelStride;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor[] srcs, RasterAccessor dst) {
/* 332 */     int dstWidth = dst.getWidth();
/* 333 */     int dstHeight = dst.getHeight();
/* 334 */     int dstBands = dst.getNumBands();
/* 336 */     int dstLineStride = dst.getScanlineStride();
/* 337 */     int dstPixelStride = dst.getPixelStride();
/* 338 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 339 */     float[][] dstData = dst.getFloatDataArrays();
/* 341 */     int numSrcs = getNumSources();
/* 343 */     for (int i = 0; i < numSrcs; i++) {
/* 344 */       RasterAccessor src = srcs[i];
/* 345 */       int srcLineStride = src.getScanlineStride();
/* 346 */       int srcPixelStride = src.getPixelStride();
/* 347 */       int[] srcBandOffsets = src.getBandOffsets();
/* 348 */       float[][] srcData = src.getFloatDataArrays();
/* 350 */       for (int b = 0; b < dstBands; b++) {
/* 351 */         int dstLineOffset = dstBandOffsets[b];
/* 352 */         int srcLineOffset = srcBandOffsets[b];
/* 354 */         float[] d = dstData[b];
/* 355 */         float[] s = srcData[b];
/* 357 */         for (int h = 0; h < dstHeight; h++) {
/* 358 */           int dstPixelOffset = dstLineOffset;
/* 359 */           int srcPixelOffset = srcLineOffset;
/* 361 */           dstLineOffset += dstLineStride;
/* 362 */           srcLineOffset += srcLineStride;
/* 364 */           for (int w = 0; w < dstWidth; w++) {
/* 365 */             d[dstPixelOffset] = ImageUtil.clampFloat(d[dstPixelOffset] + s[srcPixelOffset]);
/* 369 */             dstPixelOffset += dstPixelStride;
/* 370 */             srcPixelOffset += srcPixelStride;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor[] srcs, RasterAccessor dst) {
/* 379 */     int dstWidth = dst.getWidth();
/* 380 */     int dstHeight = dst.getHeight();
/* 381 */     int dstBands = dst.getNumBands();
/* 383 */     int dstLineStride = dst.getScanlineStride();
/* 384 */     int dstPixelStride = dst.getPixelStride();
/* 385 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 386 */     double[][] dstData = dst.getDoubleDataArrays();
/* 388 */     int numSrcs = getNumSources();
/* 390 */     for (int i = 0; i < numSrcs; i++) {
/* 391 */       RasterAccessor src = srcs[i];
/* 392 */       int srcLineStride = src.getScanlineStride();
/* 393 */       int srcPixelStride = src.getPixelStride();
/* 394 */       int[] srcBandOffsets = src.getBandOffsets();
/* 395 */       double[][] srcData = src.getDoubleDataArrays();
/* 397 */       for (int b = 0; b < dstBands; b++) {
/* 398 */         int dstLineOffset = dstBandOffsets[b];
/* 399 */         int srcLineOffset = srcBandOffsets[b];
/* 401 */         double[] d = dstData[b];
/* 402 */         double[] s = srcData[b];
/* 404 */         for (int h = 0; h < dstHeight; h++) {
/* 405 */           int dstPixelOffset = dstLineOffset;
/* 406 */           int srcPixelOffset = srcLineOffset;
/* 408 */           dstLineOffset += dstLineStride;
/* 409 */           srcLineOffset += srcLineStride;
/* 411 */           for (int w = 0; w < dstWidth; w++) {
/* 412 */             d[dstPixelOffset] = d[dstPixelOffset] + s[srcPixelOffset];
/* 415 */             dstPixelOffset += dstPixelStride;
/* 416 */             srcPixelOffset += srcPixelStride;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AddCollectionOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
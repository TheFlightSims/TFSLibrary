/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ColormapOpImage;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class DivideIntoConstOpImage extends ColormapOpImage {
/*     */   protected double[] constants;
/*     */   
/*     */   public DivideIntoConstOpImage(RenderedImage source, Map config, ImageLayout layout, double[] constants) {
/*  68 */     super(source, layout, config, true);
/*  70 */     int numBands = getSampleModel().getNumBands();
/*  72 */     if (constants.length < numBands) {
/*  73 */       this.constants = new double[numBands];
/*  74 */       for (int i = 0; i < numBands; i++)
/*  75 */         this.constants[i] = constants[0]; 
/*     */     } else {
/*  78 */       this.constants = (double[])constants.clone();
/*     */     } 
/*  82 */     permitInPlaceOperation();
/*  85 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/*  92 */     for (int b = 0; b < 3; b++) {
/*  93 */       byte[] map = colormap[b];
/*  94 */       int mapSize = map.length;
/*  96 */       double c = (b < this.constants.length) ? this.constants[b] : this.constants[0];
/*  98 */       for (int i = 0; i < mapSize; i++)
/*  99 */         map[i] = ImageUtil.clampRoundByte(c / (map[i] & 0xFF)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 116 */     RasterFormatTag[] formatTags = getFormatTags();
/* 118 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 120 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 122 */     RasterAccessor src = new RasterAccessor(sources[0], srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 126 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 128 */         computeRectByte(src, dst);
/*     */         break;
/*     */       case 1:
/* 131 */         computeRectUShort(src, dst);
/*     */         break;
/*     */       case 2:
/* 134 */         computeRectShort(src, dst);
/*     */         break;
/*     */       case 3:
/* 137 */         computeRectInt(src, dst);
/*     */         break;
/*     */       case 4:
/* 140 */         computeRectFloat(src, dst);
/*     */         break;
/*     */       case 5:
/* 143 */         computeRectDouble(src, dst);
/*     */         break;
/*     */     } 
/* 147 */     if (dst.needsClamping())
/* 149 */       dst.clampDataArrays(); 
/* 151 */     dst.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 156 */     int dstWidth = dst.getWidth();
/* 157 */     int dstHeight = dst.getHeight();
/* 158 */     int dstBands = dst.getNumBands();
/* 160 */     int dstLineStride = dst.getScanlineStride();
/* 161 */     int dstPixelStride = dst.getPixelStride();
/* 162 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 163 */     byte[][] dstData = dst.getByteDataArrays();
/* 165 */     int srcLineStride = src.getScanlineStride();
/* 166 */     int srcPixelStride = src.getPixelStride();
/* 167 */     int[] srcBandOffsets = src.getBandOffsets();
/* 168 */     byte[][] srcData = src.getByteDataArrays();
/* 170 */     for (int b = 0; b < dstBands; b++) {
/* 171 */       double c = this.constants[b];
/* 172 */       byte[] d = dstData[b];
/* 173 */       byte[] s = srcData[b];
/* 175 */       int dstLineOffset = dstBandOffsets[b];
/* 176 */       int srcLineOffset = srcBandOffsets[b];
/* 178 */       for (int h = 0; h < dstHeight; h++) {
/* 179 */         int dstPixelOffset = dstLineOffset;
/* 180 */         int srcPixelOffset = srcLineOffset;
/* 182 */         dstLineOffset += dstLineStride;
/* 183 */         srcLineOffset += srcLineStride;
/* 185 */         for (int w = 0; w < dstWidth; w++) {
/* 186 */           double t = (s[srcPixelOffset] & 0xFF);
/* 187 */           d[dstPixelOffset] = ImageUtil.clampRoundByte(c / t);
/* 189 */           dstPixelOffset += dstPixelStride;
/* 190 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 198 */     int dstWidth = dst.getWidth();
/* 199 */     int dstHeight = dst.getHeight();
/* 200 */     int dstBands = dst.getNumBands();
/* 202 */     int dstLineStride = dst.getScanlineStride();
/* 203 */     int dstPixelStride = dst.getPixelStride();
/* 204 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 205 */     short[][] dstData = dst.getShortDataArrays();
/* 207 */     int srcLineStride = src.getScanlineStride();
/* 208 */     int srcPixelStride = src.getPixelStride();
/* 209 */     int[] srcBandOffsets = src.getBandOffsets();
/* 210 */     short[][] srcData = src.getShortDataArrays();
/* 212 */     for (int b = 0; b < dstBands; b++) {
/* 213 */       double c = this.constants[b];
/* 214 */       short[] d = dstData[b];
/* 215 */       short[] s = srcData[b];
/* 217 */       int dstLineOffset = dstBandOffsets[b];
/* 218 */       int srcLineOffset = srcBandOffsets[b];
/* 220 */       for (int h = 0; h < dstHeight; h++) {
/* 221 */         int dstPixelOffset = dstLineOffset;
/* 222 */         int srcPixelOffset = srcLineOffset;
/* 224 */         dstLineOffset += dstLineStride;
/* 225 */         srcLineOffset += srcLineStride;
/* 227 */         for (int w = 0; w < dstWidth; w++) {
/* 228 */           double t = (s[srcPixelOffset] & 0xFFFF);
/* 229 */           d[dstPixelOffset] = ImageUtil.clampRoundUShort(c / t);
/* 231 */           dstPixelOffset += dstPixelStride;
/* 232 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 240 */     int dstWidth = dst.getWidth();
/* 241 */     int dstHeight = dst.getHeight();
/* 242 */     int dstBands = dst.getNumBands();
/* 244 */     int dstLineStride = dst.getScanlineStride();
/* 245 */     int dstPixelStride = dst.getPixelStride();
/* 246 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 247 */     short[][] dstData = dst.getShortDataArrays();
/* 249 */     int srcLineStride = src.getScanlineStride();
/* 250 */     int srcPixelStride = src.getPixelStride();
/* 251 */     int[] srcBandOffsets = src.getBandOffsets();
/* 252 */     short[][] srcData = src.getShortDataArrays();
/* 254 */     for (int b = 0; b < dstBands; b++) {
/* 255 */       double c = this.constants[b];
/* 256 */       short[] d = dstData[b];
/* 257 */       short[] s = srcData[b];
/* 259 */       int dstLineOffset = dstBandOffsets[b];
/* 260 */       int srcLineOffset = srcBandOffsets[b];
/* 262 */       for (int h = 0; h < dstHeight; h++) {
/* 263 */         int dstPixelOffset = dstLineOffset;
/* 264 */         int srcPixelOffset = srcLineOffset;
/* 266 */         dstLineOffset += dstLineStride;
/* 267 */         srcLineOffset += srcLineStride;
/* 269 */         for (int w = 0; w < dstWidth; w++) {
/* 270 */           d[dstPixelOffset] = ImageUtil.clampRoundShort(c / s[srcPixelOffset]);
/* 272 */           dstPixelOffset += dstPixelStride;
/* 273 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 281 */     int dstWidth = dst.getWidth();
/* 282 */     int dstHeight = dst.getHeight();
/* 283 */     int dstBands = dst.getNumBands();
/* 285 */     int dstLineStride = dst.getScanlineStride();
/* 286 */     int dstPixelStride = dst.getPixelStride();
/* 287 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 288 */     int[][] dstData = dst.getIntDataArrays();
/* 290 */     int srcLineStride = src.getScanlineStride();
/* 291 */     int srcPixelStride = src.getPixelStride();
/* 292 */     int[] srcBandOffsets = src.getBandOffsets();
/* 293 */     int[][] srcData = src.getIntDataArrays();
/* 295 */     for (int b = 0; b < dstBands; b++) {
/* 296 */       double c = this.constants[b];
/* 297 */       int[] d = dstData[b];
/* 298 */       int[] s = srcData[b];
/* 300 */       int dstLineOffset = dstBandOffsets[b];
/* 301 */       int srcLineOffset = srcBandOffsets[b];
/* 303 */       for (int h = 0; h < dstHeight; h++) {
/* 304 */         int dstPixelOffset = dstLineOffset;
/* 305 */         int srcPixelOffset = srcLineOffset;
/* 307 */         dstLineOffset += dstLineStride;
/* 308 */         srcLineOffset += srcLineStride;
/* 310 */         for (int w = 0; w < dstWidth; w++) {
/* 311 */           d[dstPixelOffset] = ImageUtil.clampRoundInt(c / s[srcPixelOffset]);
/* 313 */           dstPixelOffset += dstPixelStride;
/* 314 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 322 */     int dstWidth = dst.getWidth();
/* 323 */     int dstHeight = dst.getHeight();
/* 324 */     int dstBands = dst.getNumBands();
/* 326 */     int dstLineStride = dst.getScanlineStride();
/* 327 */     int dstPixelStride = dst.getPixelStride();
/* 328 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 329 */     float[][] dstData = dst.getFloatDataArrays();
/* 331 */     int srcLineStride = src.getScanlineStride();
/* 332 */     int srcPixelStride = src.getPixelStride();
/* 333 */     int[] srcBandOffsets = src.getBandOffsets();
/* 334 */     float[][] srcData = src.getFloatDataArrays();
/* 336 */     for (int b = 0; b < dstBands; b++) {
/* 337 */       double c = this.constants[b];
/* 338 */       float[] d = dstData[b];
/* 339 */       float[] s = srcData[b];
/* 341 */       int dstLineOffset = dstBandOffsets[b];
/* 342 */       int srcLineOffset = srcBandOffsets[b];
/* 344 */       for (int h = 0; h < dstHeight; h++) {
/* 345 */         int dstPixelOffset = dstLineOffset;
/* 346 */         int srcPixelOffset = srcLineOffset;
/* 348 */         dstLineOffset += dstLineStride;
/* 349 */         srcLineOffset += srcLineStride;
/* 351 */         for (int w = 0; w < dstWidth; w++) {
/* 352 */           d[dstPixelOffset] = ImageUtil.clampFloat(c / s[srcPixelOffset]);
/* 354 */           dstPixelOffset += dstPixelStride;
/* 355 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 363 */     int dstWidth = dst.getWidth();
/* 364 */     int dstHeight = dst.getHeight();
/* 365 */     int dstBands = dst.getNumBands();
/* 367 */     int dstLineStride = dst.getScanlineStride();
/* 368 */     int dstPixelStride = dst.getPixelStride();
/* 369 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 370 */     double[][] dstData = dst.getDoubleDataArrays();
/* 372 */     int srcLineStride = src.getScanlineStride();
/* 373 */     int srcPixelStride = src.getPixelStride();
/* 374 */     int[] srcBandOffsets = src.getBandOffsets();
/* 375 */     double[][] srcData = src.getDoubleDataArrays();
/* 377 */     for (int b = 0; b < dstBands; b++) {
/* 378 */       double c = this.constants[b];
/* 379 */       double[] d = dstData[b];
/* 380 */       double[] s = srcData[b];
/* 382 */       int dstLineOffset = dstBandOffsets[b];
/* 383 */       int srcLineOffset = srcBandOffsets[b];
/* 385 */       for (int h = 0; h < dstHeight; h++) {
/* 386 */         int dstPixelOffset = dstLineOffset;
/* 387 */         int srcPixelOffset = srcLineOffset;
/* 389 */         dstLineOffset += dstLineStride;
/* 390 */         srcLineOffset += srcLineStride;
/* 392 */         for (int w = 0; w < dstWidth; w++) {
/* 393 */           d[dstPixelOffset] = c / s[srcPixelOffset];
/* 395 */           dstPixelOffset += dstPixelStride;
/* 396 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DivideIntoConstOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
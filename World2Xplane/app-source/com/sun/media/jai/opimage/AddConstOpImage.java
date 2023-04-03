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
/*     */ final class AddConstOpImage extends ColormapOpImage {
/*     */   protected double[] constants;
/*     */   
/*  56 */   private byte[][] byteTable = (byte[][])null;
/*     */   
/*     */   private synchronized void initByteTable() {
/*  60 */     if (this.byteTable != null)
/*     */       return; 
/*  64 */     int nbands = this.constants.length;
/*  66 */     this.byteTable = new byte[nbands][256];
/*  69 */     for (int band = 0; band < nbands; band++) {
/*  70 */       int k = ImageUtil.clampRoundInt(this.constants[band]);
/*  71 */       byte[] t = this.byteTable[band];
/*  72 */       for (int i = 0; i < 256; i++) {
/*  73 */         int sum = i + k;
/*  74 */         if (sum < 0) {
/*  75 */           t[i] = 0;
/*  76 */         } else if (sum > 255) {
/*  77 */           t[i] = -1;
/*     */         } else {
/*  79 */           t[i] = (byte)sum;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public AddConstOpImage(RenderedImage source, Map config, ImageLayout layout, double[] constants) {
/*  96 */     super(source, layout, config, true);
/*  98 */     int numBands = getSampleModel().getNumBands();
/* 100 */     if (constants.length < numBands) {
/* 101 */       this.constants = new double[numBands];
/* 102 */       for (int i = 0; i < numBands; i++)
/* 103 */         this.constants[i] = constants[0]; 
/*     */     } else {
/* 106 */       this.constants = (double[])constants.clone();
/*     */     } 
/* 110 */     permitInPlaceOperation();
/* 113 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/* 120 */     initByteTable();
/* 122 */     for (int b = 0; b < 3; b++) {
/* 123 */       byte[] map = colormap[b];
/* 124 */       byte[] luTable = this.byteTable[(b >= this.byteTable.length) ? 0 : b];
/* 125 */       int mapSize = map.length;
/* 127 */       for (int i = 0; i < mapSize; i++)
/* 128 */         map[i] = luTable[map[i] & 0xFF]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 146 */     RasterFormatTag[] formatTags = getFormatTags();
/* 148 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 150 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 152 */     RasterAccessor src = new RasterAccessor(sources[0], srcRect, formatTags[0], getSourceImage(0).getColorModel());
/* 155 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 157 */         computeRectByte(src, dst);
/*     */         break;
/*     */       case 1:
/* 160 */         computeRectUShort(src, dst);
/*     */         break;
/*     */       case 2:
/* 163 */         computeRectShort(src, dst);
/*     */         break;
/*     */       case 3:
/* 166 */         computeRectInt(src, dst);
/*     */         break;
/*     */       case 4:
/* 169 */         computeRectFloat(src, dst);
/*     */         break;
/*     */       case 5:
/* 172 */         computeRectDouble(src, dst);
/*     */         break;
/*     */     } 
/* 176 */     if (dst.needsClamping())
/* 178 */       dst.clampDataArrays(); 
/* 180 */     dst.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 185 */     initByteTable();
/* 187 */     int dstWidth = dst.getWidth();
/* 188 */     int dstHeight = dst.getHeight();
/* 189 */     int dstBands = dst.getNumBands();
/* 191 */     int dstLineStride = dst.getScanlineStride();
/* 192 */     int dstPixelStride = dst.getPixelStride();
/* 193 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 194 */     byte[][] dstData = dst.getByteDataArrays();
/* 196 */     int srcLineStride = src.getScanlineStride();
/* 197 */     int srcPixelStride = src.getPixelStride();
/* 198 */     int[] srcBandOffsets = src.getBandOffsets();
/* 199 */     byte[][] srcData = src.getByteDataArrays();
/* 201 */     for (int b = 0; b < dstBands; b++) {
/* 202 */       byte[] d = dstData[b];
/* 203 */       byte[] s = srcData[b];
/* 204 */       byte[] clamp = this.byteTable[b];
/* 206 */       int dstLineOffset = dstBandOffsets[b];
/* 207 */       int srcLineOffset = srcBandOffsets[b];
/* 209 */       for (int h = 0; h < dstHeight; h++) {
/* 210 */         int dstPixelOffset = dstLineOffset;
/* 211 */         int srcPixelOffset = srcLineOffset;
/* 213 */         dstLineOffset += dstLineStride;
/* 214 */         srcLineOffset += srcLineStride;
/* 216 */         int dstEnd = dstPixelOffset + dstWidth * dstPixelStride;
/* 217 */         while (dstPixelOffset < dstEnd) {
/* 218 */           d[dstPixelOffset] = clamp[s[srcPixelOffset] & 0xFF];
/* 220 */           dstPixelOffset += dstPixelStride;
/* 221 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 229 */     int dstWidth = dst.getWidth();
/* 230 */     int dstHeight = dst.getHeight();
/* 231 */     int dstBands = dst.getNumBands();
/* 233 */     int dstLineStride = dst.getScanlineStride();
/* 234 */     int dstPixelStride = dst.getPixelStride();
/* 235 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 236 */     short[][] dstData = dst.getShortDataArrays();
/* 238 */     int srcLineStride = src.getScanlineStride();
/* 239 */     int srcPixelStride = src.getPixelStride();
/* 240 */     int[] srcBandOffsets = src.getBandOffsets();
/* 241 */     short[][] srcData = src.getShortDataArrays();
/* 243 */     for (int b = 0; b < dstBands; b++) {
/* 244 */       int c = ImageUtil.clampRoundInt(this.constants[b]);
/* 245 */       short[] d = dstData[b];
/* 246 */       short[] s = srcData[b];
/* 248 */       int dstLineOffset = dstBandOffsets[b];
/* 249 */       int srcLineOffset = srcBandOffsets[b];
/* 251 */       for (int h = 0; h < dstHeight; h++) {
/* 252 */         int dstPixelOffset = dstLineOffset;
/* 253 */         int srcPixelOffset = srcLineOffset;
/* 255 */         dstLineOffset += dstLineStride;
/* 256 */         srcLineOffset += srcLineStride;
/* 258 */         for (int w = 0; w < dstWidth; w++) {
/* 259 */           d[dstPixelOffset] = ImageUtil.clampUShort((s[srcPixelOffset] & 0xFFFF) + c);
/* 262 */           dstPixelOffset += dstPixelStride;
/* 263 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 271 */     int dstWidth = dst.getWidth();
/* 272 */     int dstHeight = dst.getHeight();
/* 273 */     int dstBands = dst.getNumBands();
/* 275 */     int dstLineStride = dst.getScanlineStride();
/* 276 */     int dstPixelStride = dst.getPixelStride();
/* 277 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 278 */     short[][] dstData = dst.getShortDataArrays();
/* 280 */     int srcLineStride = src.getScanlineStride();
/* 281 */     int srcPixelStride = src.getPixelStride();
/* 282 */     int[] srcBandOffsets = src.getBandOffsets();
/* 283 */     short[][] srcData = src.getShortDataArrays();
/* 285 */     for (int b = 0; b < dstBands; b++) {
/* 286 */       int c = ImageUtil.clampRoundInt(this.constants[b]);
/* 287 */       short[] d = dstData[b];
/* 288 */       short[] s = srcData[b];
/* 290 */       int dstLineOffset = dstBandOffsets[b];
/* 291 */       int srcLineOffset = srcBandOffsets[b];
/* 293 */       for (int h = 0; h < dstHeight; h++) {
/* 294 */         int dstPixelOffset = dstLineOffset;
/* 295 */         int srcPixelOffset = srcLineOffset;
/* 297 */         dstLineOffset += dstLineStride;
/* 298 */         srcLineOffset += srcLineStride;
/* 300 */         for (int w = 0; w < dstWidth; w++) {
/* 301 */           d[dstPixelOffset] = ImageUtil.clampShort(s[srcPixelOffset] + c);
/* 303 */           dstPixelOffset += dstPixelStride;
/* 304 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 312 */     int dstWidth = dst.getWidth();
/* 313 */     int dstHeight = dst.getHeight();
/* 314 */     int dstBands = dst.getNumBands();
/* 316 */     int dstLineStride = dst.getScanlineStride();
/* 317 */     int dstPixelStride = dst.getPixelStride();
/* 318 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 319 */     int[][] dstData = dst.getIntDataArrays();
/* 321 */     int srcLineStride = src.getScanlineStride();
/* 322 */     int srcPixelStride = src.getPixelStride();
/* 323 */     int[] srcBandOffsets = src.getBandOffsets();
/* 324 */     int[][] srcData = src.getIntDataArrays();
/* 326 */     for (int b = 0; b < dstBands; b++) {
/* 327 */       long c = ImageUtil.clampRoundInt(this.constants[b]);
/* 328 */       int[] d = dstData[b];
/* 329 */       int[] s = srcData[b];
/* 331 */       int dstLineOffset = dstBandOffsets[b];
/* 332 */       int srcLineOffset = srcBandOffsets[b];
/* 334 */       for (int h = 0; h < dstHeight; h++) {
/* 335 */         int dstPixelOffset = dstLineOffset;
/* 336 */         int srcPixelOffset = srcLineOffset;
/* 338 */         dstLineOffset += dstLineStride;
/* 339 */         srcLineOffset += srcLineStride;
/* 341 */         for (int w = 0; w < dstWidth; w++) {
/* 342 */           d[dstPixelOffset] = ImageUtil.clampInt(s[srcPixelOffset] + c);
/* 344 */           dstPixelOffset += dstPixelStride;
/* 345 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 353 */     int dstWidth = dst.getWidth();
/* 354 */     int dstHeight = dst.getHeight();
/* 355 */     int dstBands = dst.getNumBands();
/* 357 */     int dstLineStride = dst.getScanlineStride();
/* 358 */     int dstPixelStride = dst.getPixelStride();
/* 359 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 360 */     float[][] dstData = dst.getFloatDataArrays();
/* 362 */     int srcLineStride = src.getScanlineStride();
/* 363 */     int srcPixelStride = src.getPixelStride();
/* 364 */     int[] srcBandOffsets = src.getBandOffsets();
/* 365 */     float[][] srcData = src.getFloatDataArrays();
/* 367 */     for (int b = 0; b < dstBands; b++) {
/* 368 */       double c = this.constants[b];
/* 369 */       float[] d = dstData[b];
/* 370 */       float[] s = srcData[b];
/* 372 */       int dstLineOffset = dstBandOffsets[b];
/* 373 */       int srcLineOffset = srcBandOffsets[b];
/* 375 */       for (int h = 0; h < dstHeight; h++) {
/* 376 */         int dstPixelOffset = dstLineOffset;
/* 377 */         int srcPixelOffset = srcLineOffset;
/* 379 */         dstLineOffset += dstLineStride;
/* 380 */         srcLineOffset += srcLineStride;
/* 382 */         for (int w = 0; w < dstWidth; w++) {
/* 383 */           d[dstPixelOffset] = ImageUtil.clampFloat(s[srcPixelOffset] + c);
/* 385 */           dstPixelOffset += dstPixelStride;
/* 386 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 394 */     int dstWidth = dst.getWidth();
/* 395 */     int dstHeight = dst.getHeight();
/* 396 */     int dstBands = dst.getNumBands();
/* 398 */     int dstLineStride = dst.getScanlineStride();
/* 399 */     int dstPixelStride = dst.getPixelStride();
/* 400 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 401 */     double[][] dstData = dst.getDoubleDataArrays();
/* 403 */     int srcLineStride = src.getScanlineStride();
/* 404 */     int srcPixelStride = src.getPixelStride();
/* 405 */     int[] srcBandOffsets = src.getBandOffsets();
/* 406 */     double[][] srcData = src.getDoubleDataArrays();
/* 408 */     for (int b = 0; b < dstBands; b++) {
/* 409 */       double c = this.constants[b];
/* 410 */       double[] d = dstData[b];
/* 411 */       double[] s = srcData[b];
/* 413 */       int dstLineOffset = dstBandOffsets[b];
/* 414 */       int srcLineOffset = srcBandOffsets[b];
/* 416 */       for (int h = 0; h < dstHeight; h++) {
/* 417 */         int dstPixelOffset = dstLineOffset;
/* 418 */         int srcPixelOffset = srcLineOffset;
/* 420 */         dstLineOffset += dstLineStride;
/* 421 */         srcLineOffset += srcLineStride;
/* 423 */         for (int w = 0; w < dstWidth; w++) {
/* 424 */           d[dstPixelOffset] = s[srcPixelOffset] + c;
/* 426 */           dstPixelOffset += dstPixelStride;
/* 427 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AddConstOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
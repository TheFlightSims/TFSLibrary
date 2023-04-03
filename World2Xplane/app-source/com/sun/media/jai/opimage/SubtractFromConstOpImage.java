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
/*     */ final class SubtractFromConstOpImage extends ColormapOpImage {
/*     */   protected double[] constants;
/*     */   
/*  57 */   private byte[][] byteTable = (byte[][])null;
/*     */   
/*     */   private synchronized void initByteTable() {
/*  61 */     if (this.byteTable != null)
/*     */       return; 
/*  64 */     int nbands = this.constants.length;
/*  66 */     this.byteTable = new byte[nbands][256];
/*  69 */     for (int band = 0; band < nbands; band++) {
/*  70 */       int k = ImageUtil.clampRoundInt(this.constants[band]);
/*  71 */       byte[] t = this.byteTable[band];
/*  72 */       for (int i = 0; i < 256; i++) {
/*  73 */         int diff = k - i;
/*  74 */         if (diff < 0) {
/*  75 */           t[i] = 0;
/*  76 */         } else if (diff > 255) {
/*  77 */           t[i] = -1;
/*     */         } else {
/*  79 */           t[i] = (byte)diff;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public SubtractFromConstOpImage(RenderedImage source, Map config, ImageLayout layout, double[] constants) {
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
/* 145 */     RasterFormatTag[] formatTags = getFormatTags();
/* 147 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 149 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 151 */     RasterAccessor src = new RasterAccessor(sources[0], srcRect, formatTags[0], getSource(0).getColorModel());
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
/* 202 */       int c = ImageUtil.clampRoundInt(this.constants[b]);
/* 203 */       byte[] d = dstData[b];
/* 204 */       byte[] s = srcData[b];
/* 205 */       byte[] clamp = this.byteTable[b];
/* 207 */       int dstLineOffset = dstBandOffsets[b];
/* 208 */       int srcLineOffset = srcBandOffsets[b];
/* 210 */       for (int h = 0; h < dstHeight; h++) {
/* 211 */         int dstPixelOffset = dstLineOffset;
/* 212 */         int srcPixelOffset = srcLineOffset;
/* 214 */         dstLineOffset += dstLineStride;
/* 215 */         srcLineOffset += srcLineStride;
/* 217 */         int dstEnd = dstPixelOffset + dstWidth * dstPixelStride;
/* 218 */         while (dstPixelOffset < dstEnd) {
/* 219 */           d[dstPixelOffset] = clamp[s[srcPixelOffset] & 0xFF];
/* 221 */           dstPixelOffset += dstPixelStride;
/* 222 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 230 */     int dstWidth = dst.getWidth();
/* 231 */     int dstHeight = dst.getHeight();
/* 232 */     int dstBands = dst.getNumBands();
/* 234 */     int dstLineStride = dst.getScanlineStride();
/* 235 */     int dstPixelStride = dst.getPixelStride();
/* 236 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 237 */     short[][] dstData = dst.getShortDataArrays();
/* 239 */     int srcLineStride = src.getScanlineStride();
/* 240 */     int srcPixelStride = src.getPixelStride();
/* 241 */     int[] srcBandOffsets = src.getBandOffsets();
/* 242 */     short[][] srcData = src.getShortDataArrays();
/* 244 */     for (int b = 0; b < dstBands; b++) {
/* 245 */       int c = ImageUtil.clampRoundInt(this.constants[b]);
/* 246 */       short[] d = dstData[b];
/* 247 */       short[] s = srcData[b];
/* 249 */       int dstLineOffset = dstBandOffsets[b];
/* 250 */       int srcLineOffset = srcBandOffsets[b];
/* 252 */       for (int h = 0; h < dstHeight; h++) {
/* 253 */         int dstPixelOffset = dstLineOffset;
/* 254 */         int srcPixelOffset = srcLineOffset;
/* 256 */         dstLineOffset += dstLineStride;
/* 257 */         srcLineOffset += srcLineStride;
/* 259 */         int dstEnd = dstPixelOffset + dstWidth * dstPixelStride;
/* 260 */         while (dstPixelOffset < dstEnd) {
/* 261 */           d[dstPixelOffset] = ImageUtil.clampUShort(c - (s[srcPixelOffset] & 0xFFFF));
/* 264 */           dstPixelOffset += dstPixelStride;
/* 265 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 273 */     int dstWidth = dst.getWidth();
/* 274 */     int dstHeight = dst.getHeight();
/* 275 */     int dstBands = dst.getNumBands();
/* 277 */     int dstLineStride = dst.getScanlineStride();
/* 278 */     int dstPixelStride = dst.getPixelStride();
/* 279 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 280 */     short[][] dstData = dst.getShortDataArrays();
/* 282 */     int srcLineStride = src.getScanlineStride();
/* 283 */     int srcPixelStride = src.getPixelStride();
/* 284 */     int[] srcBandOffsets = src.getBandOffsets();
/* 285 */     short[][] srcData = src.getShortDataArrays();
/* 287 */     for (int b = 0; b < dstBands; b++) {
/* 288 */       int c = ImageUtil.clampRoundInt(this.constants[b]);
/* 289 */       short[] d = dstData[b];
/* 290 */       short[] s = srcData[b];
/* 292 */       int dstLineOffset = dstBandOffsets[b];
/* 293 */       int srcLineOffset = srcBandOffsets[b];
/* 295 */       for (int h = 0; h < dstHeight; h++) {
/* 296 */         int dstPixelOffset = dstLineOffset;
/* 297 */         int srcPixelOffset = srcLineOffset;
/* 299 */         dstLineOffset += dstLineStride;
/* 300 */         srcLineOffset += srcLineStride;
/* 302 */         int dstEnd = dstPixelOffset + dstWidth * dstPixelStride;
/* 303 */         while (dstPixelOffset < dstEnd) {
/* 304 */           d[dstPixelOffset] = ImageUtil.clampShort(c - s[srcPixelOffset]);
/* 306 */           dstPixelOffset += dstPixelStride;
/* 307 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 315 */     int dstWidth = dst.getWidth();
/* 316 */     int dstHeight = dst.getHeight();
/* 317 */     int dstBands = dst.getNumBands();
/* 319 */     int dstLineStride = dst.getScanlineStride();
/* 320 */     int dstPixelStride = dst.getPixelStride();
/* 321 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 322 */     int[][] dstData = dst.getIntDataArrays();
/* 324 */     int srcLineStride = src.getScanlineStride();
/* 325 */     int srcPixelStride = src.getPixelStride();
/* 326 */     int[] srcBandOffsets = src.getBandOffsets();
/* 327 */     int[][] srcData = src.getIntDataArrays();
/* 329 */     for (int b = 0; b < dstBands; b++) {
/* 330 */       long c = ImageUtil.clampRoundInt(this.constants[b]);
/* 331 */       int[] d = dstData[b];
/* 332 */       int[] s = srcData[b];
/* 334 */       int dstLineOffset = dstBandOffsets[b];
/* 335 */       int srcLineOffset = srcBandOffsets[b];
/* 337 */       for (int h = 0; h < dstHeight; h++) {
/* 338 */         int dstPixelOffset = dstLineOffset;
/* 339 */         int srcPixelOffset = srcLineOffset;
/* 341 */         dstLineOffset += dstLineStride;
/* 342 */         srcLineOffset += srcLineStride;
/* 344 */         int dstEnd = dstPixelOffset + dstWidth * dstPixelStride;
/* 345 */         while (dstPixelOffset < dstEnd) {
/* 346 */           d[dstPixelOffset] = ImageUtil.clampInt(c - s[srcPixelOffset]);
/* 348 */           dstPixelOffset += dstPixelStride;
/* 349 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 357 */     int dstWidth = dst.getWidth();
/* 358 */     int dstHeight = dst.getHeight();
/* 359 */     int dstBands = dst.getNumBands();
/* 361 */     int dstLineStride = dst.getScanlineStride();
/* 362 */     int dstPixelStride = dst.getPixelStride();
/* 363 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 364 */     float[][] dstData = dst.getFloatDataArrays();
/* 366 */     int srcLineStride = src.getScanlineStride();
/* 367 */     int srcPixelStride = src.getPixelStride();
/* 368 */     int[] srcBandOffsets = src.getBandOffsets();
/* 369 */     float[][] srcData = src.getFloatDataArrays();
/* 371 */     for (int b = 0; b < dstBands; b++) {
/* 372 */       double c = this.constants[b];
/* 373 */       float[] d = dstData[b];
/* 374 */       float[] s = srcData[b];
/* 376 */       int dstLineOffset = dstBandOffsets[b];
/* 377 */       int srcLineOffset = srcBandOffsets[b];
/* 379 */       for (int h = 0; h < dstHeight; h++) {
/* 380 */         int dstPixelOffset = dstLineOffset;
/* 381 */         int srcPixelOffset = srcLineOffset;
/* 383 */         dstLineOffset += dstLineStride;
/* 384 */         srcLineOffset += srcLineStride;
/* 386 */         int dstEnd = dstPixelOffset + dstWidth * dstPixelStride;
/* 387 */         while (dstPixelOffset < dstEnd) {
/* 388 */           d[dstPixelOffset] = ImageUtil.clampFloat(c - s[srcPixelOffset]);
/* 391 */           dstPixelOffset += dstPixelStride;
/* 392 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 400 */     int dstWidth = dst.getWidth();
/* 401 */     int dstHeight = dst.getHeight();
/* 402 */     int dstBands = dst.getNumBands();
/* 404 */     int dstLineStride = dst.getScanlineStride();
/* 405 */     int dstPixelStride = dst.getPixelStride();
/* 406 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 407 */     double[][] dstData = dst.getDoubleDataArrays();
/* 409 */     int srcLineStride = src.getScanlineStride();
/* 410 */     int srcPixelStride = src.getPixelStride();
/* 411 */     int[] srcBandOffsets = src.getBandOffsets();
/* 412 */     double[][] srcData = src.getDoubleDataArrays();
/* 414 */     for (int b = 0; b < dstBands; b++) {
/* 415 */       double c = this.constants[b];
/* 416 */       double[] d = dstData[b];
/* 417 */       double[] s = srcData[b];
/* 419 */       int dstLineOffset = dstBandOffsets[b];
/* 420 */       int srcLineOffset = srcBandOffsets[b];
/* 422 */       for (int h = 0; h < dstHeight; h++) {
/* 423 */         int dstPixelOffset = dstLineOffset;
/* 424 */         int srcPixelOffset = srcLineOffset;
/* 426 */         dstLineOffset += dstLineStride;
/* 427 */         srcLineOffset += srcLineStride;
/* 429 */         int dstEnd = dstPixelOffset + dstWidth * dstPixelStride;
/* 430 */         while (dstPixelOffset < dstEnd) {
/* 431 */           d[dstPixelOffset] = c - s[srcPixelOffset];
/* 433 */           dstPixelOffset += dstPixelStride;
/* 434 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubtractFromConstOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
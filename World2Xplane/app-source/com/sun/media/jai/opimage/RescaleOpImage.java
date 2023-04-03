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
/*     */ final class RescaleOpImage extends ColormapOpImage {
/*     */   protected double[] constants;
/*     */   
/*     */   protected double[] offsets;
/*     */   
/*  61 */   private byte[][] byteTable = (byte[][])null;
/*     */   
/*     */   private synchronized void initByteTable() {
/*  65 */     if (this.byteTable != null)
/*     */       return; 
/*  69 */     int nbands = this.constants.length;
/*  71 */     this.byteTable = new byte[nbands][256];
/*  74 */     for (int band = 0; band < nbands; band++) {
/*  75 */       byte[] t = this.byteTable[band];
/*  76 */       double c = this.constants[band];
/*  77 */       double o = this.offsets[band];
/*  78 */       for (int i = 0; i < 256; i++)
/*  79 */         t[i] = ImageUtil.clampRoundByte(i * c + o); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RescaleOpImage(RenderedImage source, Map config, ImageLayout layout, double[] constants, double[] offsets) {
/* 102 */     super(source, layout, config, true);
/* 104 */     int numBands = getSampleModel().getNumBands();
/* 106 */     if (constants.length < numBands) {
/* 107 */       this.constants = new double[numBands];
/* 108 */       for (int i = 0; i < numBands; i++)
/* 109 */         this.constants[i] = constants[0]; 
/*     */     } else {
/* 112 */       this.constants = constants;
/*     */     } 
/* 115 */     if (offsets.length < numBands) {
/* 116 */       this.offsets = new double[numBands];
/* 117 */       for (int i = 0; i < numBands; i++)
/* 118 */         this.offsets[i] = offsets[0]; 
/*     */     } else {
/* 121 */       this.offsets = offsets;
/*     */     } 
/* 125 */     permitInPlaceOperation();
/* 128 */     initializeColormapOperation();
/*     */   }
/*     */   
/*     */   protected void transformColormap(byte[][] colormap) {
/* 135 */     for (int b = 0; b < 3; b++) {
/* 136 */       byte[] map = colormap[b];
/* 137 */       int mapSize = map.length;
/* 139 */       float c = (float)((b < this.constants.length) ? this.constants[b] : this.constants[0]);
/* 141 */       float o = (float)((b < this.constants.length) ? this.offsets[b] : this.offsets[0]);
/* 144 */       for (int i = 0; i < mapSize; i++)
/* 145 */         map[i] = ImageUtil.clampRoundByte((map[i] & 0xFF) * c + o); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 162 */     RasterFormatTag[] formatTags = getFormatTags();
/* 164 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 166 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 168 */     RasterAccessor src = new RasterAccessor(sources[0], srcRect, formatTags[0], getSource(0).getColorModel());
/* 172 */     switch (dst.getDataType()) {
/*     */       case 0:
/* 174 */         computeRectByte(src, dst);
/*     */         break;
/*     */       case 1:
/* 177 */         computeRectUShort(src, dst);
/*     */         break;
/*     */       case 2:
/* 180 */         computeRectShort(src, dst);
/*     */         break;
/*     */       case 3:
/* 183 */         computeRectInt(src, dst);
/*     */         break;
/*     */       case 4:
/* 186 */         computeRectFloat(src, dst);
/*     */         break;
/*     */       case 5:
/* 189 */         computeRectDouble(src, dst);
/*     */         break;
/*     */     } 
/* 193 */     if (dst.needsClamping())
/* 195 */       dst.clampDataArrays(); 
/* 197 */     dst.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 202 */     int dstWidth = dst.getWidth();
/* 203 */     int dstHeight = dst.getHeight();
/* 204 */     int dstBands = dst.getNumBands();
/* 206 */     int dstLineStride = dst.getScanlineStride();
/* 207 */     int dstPixelStride = dst.getPixelStride();
/* 208 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 209 */     byte[][] dstData = dst.getByteDataArrays();
/* 211 */     int srcLineStride = src.getScanlineStride();
/* 212 */     int srcPixelStride = src.getPixelStride();
/* 213 */     int[] srcBandOffsets = src.getBandOffsets();
/* 214 */     byte[][] srcData = src.getByteDataArrays();
/* 216 */     initByteTable();
/* 218 */     for (int b = 0; b < dstBands; b++) {
/* 219 */       byte[] d = dstData[b];
/* 220 */       byte[] s = srcData[b];
/* 222 */       int dstLineOffset = dstBandOffsets[b];
/* 223 */       int srcLineOffset = srcBandOffsets[b];
/* 225 */       byte[] clamp = this.byteTable[b];
/* 226 */       double c = this.constants[b];
/* 227 */       double o = this.offsets[b];
/* 229 */       for (int h = 0; h < dstHeight; h++) {
/* 230 */         int dstPixelOffset = dstLineOffset;
/* 231 */         int srcPixelOffset = srcLineOffset;
/* 233 */         dstLineOffset += dstLineStride;
/* 234 */         srcLineOffset += srcLineStride;
/* 236 */         for (int w = 0; w < dstWidth; w++) {
/* 237 */           d[dstPixelOffset] = clamp[s[srcPixelOffset] & 0xFF];
/* 239 */           dstPixelOffset += dstPixelStride;
/* 240 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 248 */     int dstWidth = dst.getWidth();
/* 249 */     int dstHeight = dst.getHeight();
/* 250 */     int dstBands = dst.getNumBands();
/* 252 */     int dstLineStride = dst.getScanlineStride();
/* 253 */     int dstPixelStride = dst.getPixelStride();
/* 254 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 255 */     short[][] dstData = dst.getShortDataArrays();
/* 257 */     int srcLineStride = src.getScanlineStride();
/* 258 */     int srcPixelStride = src.getPixelStride();
/* 259 */     int[] srcBandOffsets = src.getBandOffsets();
/* 260 */     short[][] srcData = src.getShortDataArrays();
/* 262 */     for (int b = 0; b < dstBands; b++) {
/* 263 */       float c = (float)this.constants[b];
/* 264 */       float o = (float)this.offsets[b];
/* 265 */       short[] d = dstData[b];
/* 266 */       short[] s = srcData[b];
/* 268 */       int dstLineOffset = dstBandOffsets[b];
/* 269 */       int srcLineOffset = srcBandOffsets[b];
/* 271 */       for (int h = 0; h < dstHeight; h++) {
/* 272 */         int dstPixelOffset = dstLineOffset;
/* 273 */         int srcPixelOffset = srcLineOffset;
/* 275 */         dstLineOffset += dstLineStride;
/* 276 */         srcLineOffset += srcLineStride;
/* 278 */         for (int w = 0; w < dstWidth; w++) {
/* 279 */           d[dstPixelOffset] = ImageUtil.clampRoundUShort((s[srcPixelOffset] & 0xFFFF) * c + o);
/* 282 */           dstPixelOffset += dstPixelStride;
/* 283 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 291 */     int dstWidth = dst.getWidth();
/* 292 */     int dstHeight = dst.getHeight();
/* 293 */     int dstBands = dst.getNumBands();
/* 295 */     int dstLineStride = dst.getScanlineStride();
/* 296 */     int dstPixelStride = dst.getPixelStride();
/* 297 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 298 */     short[][] dstData = dst.getShortDataArrays();
/* 300 */     int srcLineStride = src.getScanlineStride();
/* 301 */     int srcPixelStride = src.getPixelStride();
/* 302 */     int[] srcBandOffsets = src.getBandOffsets();
/* 303 */     short[][] srcData = src.getShortDataArrays();
/* 305 */     for (int b = 0; b < dstBands; b++) {
/* 306 */       float c = (float)this.constants[b];
/* 307 */       float o = (float)this.offsets[b];
/* 308 */       short[] d = dstData[b];
/* 309 */       short[] s = srcData[b];
/* 311 */       int dstLineOffset = dstBandOffsets[b];
/* 312 */       int srcLineOffset = srcBandOffsets[b];
/* 314 */       for (int h = 0; h < dstHeight; h++) {
/* 315 */         int dstPixelOffset = dstLineOffset;
/* 316 */         int srcPixelOffset = srcLineOffset;
/* 318 */         dstLineOffset += dstLineStride;
/* 319 */         srcLineOffset += srcLineStride;
/* 321 */         for (int w = 0; w < dstWidth; w++) {
/* 322 */           d[dstPixelOffset] = ImageUtil.clampRoundShort(s[srcPixelOffset] * c + o);
/* 324 */           dstPixelOffset += dstPixelStride;
/* 325 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 333 */     int dstWidth = dst.getWidth();
/* 334 */     int dstHeight = dst.getHeight();
/* 335 */     int dstBands = dst.getNumBands();
/* 337 */     int dstLineStride = dst.getScanlineStride();
/* 338 */     int dstPixelStride = dst.getPixelStride();
/* 339 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 340 */     int[][] dstData = dst.getIntDataArrays();
/* 342 */     int srcLineStride = src.getScanlineStride();
/* 343 */     int srcPixelStride = src.getPixelStride();
/* 344 */     int[] srcBandOffsets = src.getBandOffsets();
/* 345 */     int[][] srcData = src.getIntDataArrays();
/* 347 */     for (int b = 0; b < dstBands; b++) {
/* 348 */       double c = this.constants[b];
/* 349 */       double o = this.offsets[b];
/* 350 */       int[] d = dstData[b];
/* 351 */       int[] s = srcData[b];
/* 353 */       int dstLineOffset = dstBandOffsets[b];
/* 354 */       int srcLineOffset = srcBandOffsets[b];
/* 356 */       for (int h = 0; h < dstHeight; h++) {
/* 357 */         int dstPixelOffset = dstLineOffset;
/* 358 */         int srcPixelOffset = srcLineOffset;
/* 360 */         dstLineOffset += dstLineStride;
/* 361 */         srcLineOffset += srcLineStride;
/* 363 */         for (int w = 0; w < dstWidth; w++) {
/* 364 */           d[dstPixelOffset] = ImageUtil.clampRoundInt(s[srcPixelOffset] * c + o);
/* 366 */           dstPixelOffset += dstPixelStride;
/* 367 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 375 */     int dstWidth = dst.getWidth();
/* 376 */     int dstHeight = dst.getHeight();
/* 377 */     int dstBands = dst.getNumBands();
/* 379 */     int dstLineStride = dst.getScanlineStride();
/* 380 */     int dstPixelStride = dst.getPixelStride();
/* 381 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 382 */     float[][] dstData = dst.getFloatDataArrays();
/* 384 */     int srcLineStride = src.getScanlineStride();
/* 385 */     int srcPixelStride = src.getPixelStride();
/* 386 */     int[] srcBandOffsets = src.getBandOffsets();
/* 387 */     float[][] srcData = src.getFloatDataArrays();
/* 389 */     for (int b = 0; b < dstBands; b++) {
/* 390 */       double c = this.constants[b];
/* 391 */       double o = this.offsets[b];
/* 392 */       float[] d = dstData[b];
/* 393 */       float[] s = srcData[b];
/* 395 */       int dstLineOffset = dstBandOffsets[b];
/* 396 */       int srcLineOffset = srcBandOffsets[b];
/* 398 */       for (int h = 0; h < dstHeight; h++) {
/* 399 */         int dstPixelOffset = dstLineOffset;
/* 400 */         int srcPixelOffset = srcLineOffset;
/* 402 */         dstLineOffset += dstLineStride;
/* 403 */         srcLineOffset += srcLineStride;
/* 405 */         for (int w = 0; w < dstWidth; w++) {
/* 406 */           d[dstPixelOffset] = ImageUtil.clampFloat(s[srcPixelOffset] * c + o);
/* 408 */           dstPixelOffset += dstPixelStride;
/* 409 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 417 */     int dstWidth = dst.getWidth();
/* 418 */     int dstHeight = dst.getHeight();
/* 419 */     int dstBands = dst.getNumBands();
/* 421 */     int dstLineStride = dst.getScanlineStride();
/* 422 */     int dstPixelStride = dst.getPixelStride();
/* 423 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 424 */     double[][] dstData = dst.getDoubleDataArrays();
/* 426 */     int srcLineStride = src.getScanlineStride();
/* 427 */     int srcPixelStride = src.getPixelStride();
/* 428 */     int[] srcBandOffsets = src.getBandOffsets();
/* 429 */     double[][] srcData = src.getDoubleDataArrays();
/* 431 */     for (int b = 0; b < dstBands; b++) {
/* 432 */       double c = this.constants[b];
/* 433 */       double o = this.offsets[b];
/* 434 */       double[] d = dstData[b];
/* 435 */       double[] s = srcData[b];
/* 437 */       int dstLineOffset = dstBandOffsets[b];
/* 438 */       int srcLineOffset = srcBandOffsets[b];
/* 440 */       for (int h = 0; h < dstHeight; h++) {
/* 441 */         int dstPixelOffset = dstLineOffset;
/* 442 */         int srcPixelOffset = srcLineOffset;
/* 444 */         dstLineOffset += dstLineStride;
/* 445 */         srcLineOffset += srcLineStride;
/* 447 */         for (int w = 0; w < dstWidth; w++) {
/* 448 */           d[dstPixelOffset] = s[srcPixelOffset] * c + o;
/* 450 */           dstPixelOffset += dstPixelStride;
/* 451 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\RescaleOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
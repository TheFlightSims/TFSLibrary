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
/*     */ final class MultiplyConstOpImage extends ColormapOpImage {
/*     */   protected double[] constants;
/*     */   
/*     */   public MultiplyConstOpImage(RenderedImage source, Map config, ImageLayout layout, double[] constants) {
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
/*  99 */         map[i] = ImageUtil.clampRoundByte((map[i] & 0xFF) * c); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 116 */     RasterFormatTag[] formatTags = getFormatTags();
/* 118 */     Rectangle srcRect = mapDestRect(destRect, 0);
/* 120 */     RasterAccessor dst = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 122 */     RasterAccessor src = new RasterAccessor(sources[0], srcRect, formatTags[0], getSource(0).getColorModel());
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
/* 155 */     int dstWidth = dst.getWidth();
/* 156 */     int dstHeight = dst.getHeight();
/* 157 */     int dstBands = dst.getNumBands();
/* 159 */     int dstLineStride = dst.getScanlineStride();
/* 160 */     int dstPixelStride = dst.getPixelStride();
/* 161 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 162 */     byte[][] dstData = dst.getByteDataArrays();
/* 164 */     int srcLineStride = src.getScanlineStride();
/* 165 */     int srcPixelStride = src.getPixelStride();
/* 166 */     int[] srcBandOffsets = src.getBandOffsets();
/* 167 */     byte[][] srcData = src.getByteDataArrays();
/* 169 */     for (int b = 0; b < dstBands; b++) {
/* 170 */       float c = (float)this.constants[b];
/* 171 */       byte[] d = dstData[b];
/* 172 */       byte[] s = srcData[b];
/* 174 */       int dstLineOffset = dstBandOffsets[b];
/* 175 */       int srcLineOffset = srcBandOffsets[b];
/* 177 */       for (int h = 0; h < dstHeight; h++) {
/* 178 */         int dstPixelOffset = dstLineOffset;
/* 179 */         int srcPixelOffset = srcLineOffset;
/* 181 */         dstLineOffset += dstLineStride;
/* 182 */         srcLineOffset += srcLineStride;
/* 184 */         for (int w = 0; w < dstWidth; w++) {
/* 185 */           d[dstPixelOffset] = ImageUtil.clampRoundByte((s[srcPixelOffset] & 0xFF) * c);
/* 188 */           dstPixelOffset += dstPixelStride;
/* 189 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 197 */     int dstWidth = dst.getWidth();
/* 198 */     int dstHeight = dst.getHeight();
/* 199 */     int dstBands = dst.getNumBands();
/* 201 */     int dstLineStride = dst.getScanlineStride();
/* 202 */     int dstPixelStride = dst.getPixelStride();
/* 203 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 204 */     short[][] dstData = dst.getShortDataArrays();
/* 206 */     int srcLineStride = src.getScanlineStride();
/* 207 */     int srcPixelStride = src.getPixelStride();
/* 208 */     int[] srcBandOffsets = src.getBandOffsets();
/* 209 */     short[][] srcData = src.getShortDataArrays();
/* 211 */     for (int b = 0; b < dstBands; b++) {
/* 212 */       float c = (float)this.constants[b];
/* 213 */       short[] d = dstData[b];
/* 214 */       short[] s = srcData[b];
/* 216 */       int dstLineOffset = dstBandOffsets[b];
/* 217 */       int srcLineOffset = srcBandOffsets[b];
/* 219 */       for (int h = 0; h < dstHeight; h++) {
/* 220 */         int dstPixelOffset = dstLineOffset;
/* 221 */         int srcPixelOffset = srcLineOffset;
/* 223 */         dstLineOffset += dstLineStride;
/* 224 */         srcLineOffset += srcLineStride;
/* 226 */         for (int w = 0; w < dstWidth; w++) {
/* 227 */           d[dstPixelOffset] = ImageUtil.clampRoundUShort((s[srcPixelOffset] & 0xFFFF) * c);
/* 230 */           dstPixelOffset += dstPixelStride;
/* 231 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 239 */     int dstWidth = dst.getWidth();
/* 240 */     int dstHeight = dst.getHeight();
/* 241 */     int dstBands = dst.getNumBands();
/* 243 */     int dstLineStride = dst.getScanlineStride();
/* 244 */     int dstPixelStride = dst.getPixelStride();
/* 245 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 246 */     short[][] dstData = dst.getShortDataArrays();
/* 248 */     int srcLineStride = src.getScanlineStride();
/* 249 */     int srcPixelStride = src.getPixelStride();
/* 250 */     int[] srcBandOffsets = src.getBandOffsets();
/* 251 */     short[][] srcData = src.getShortDataArrays();
/* 253 */     for (int b = 0; b < dstBands; b++) {
/* 254 */       float c = (float)this.constants[b];
/* 255 */       short[] d = dstData[b];
/* 256 */       short[] s = srcData[b];
/* 258 */       int dstLineOffset = dstBandOffsets[b];
/* 259 */       int srcLineOffset = srcBandOffsets[b];
/* 261 */       for (int h = 0; h < dstHeight; h++) {
/* 262 */         int dstPixelOffset = dstLineOffset;
/* 263 */         int srcPixelOffset = srcLineOffset;
/* 265 */         dstLineOffset += dstLineStride;
/* 266 */         srcLineOffset += srcLineStride;
/* 268 */         for (int w = 0; w < dstWidth; w++) {
/* 269 */           d[dstPixelOffset] = ImageUtil.clampRoundShort(s[srcPixelOffset] * c);
/* 271 */           dstPixelOffset += dstPixelStride;
/* 272 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 280 */     int dstWidth = dst.getWidth();
/* 281 */     int dstHeight = dst.getHeight();
/* 282 */     int dstBands = dst.getNumBands();
/* 284 */     int dstLineStride = dst.getScanlineStride();
/* 285 */     int dstPixelStride = dst.getPixelStride();
/* 286 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 287 */     int[][] dstData = dst.getIntDataArrays();
/* 289 */     int srcLineStride = src.getScanlineStride();
/* 290 */     int srcPixelStride = src.getPixelStride();
/* 291 */     int[] srcBandOffsets = src.getBandOffsets();
/* 292 */     int[][] srcData = src.getIntDataArrays();
/* 294 */     for (int b = 0; b < dstBands; b++) {
/* 295 */       double c = this.constants[b];
/* 296 */       int[] d = dstData[b];
/* 297 */       int[] s = srcData[b];
/* 299 */       int dstLineOffset = dstBandOffsets[b];
/* 300 */       int srcLineOffset = srcBandOffsets[b];
/* 302 */       for (int h = 0; h < dstHeight; h++) {
/* 303 */         int dstPixelOffset = dstLineOffset;
/* 304 */         int srcPixelOffset = srcLineOffset;
/* 306 */         dstLineOffset += dstLineStride;
/* 307 */         srcLineOffset += srcLineStride;
/* 309 */         for (int w = 0; w < dstWidth; w++) {
/* 310 */           d[dstPixelOffset] = ImageUtil.clampRoundInt(s[srcPixelOffset] * c);
/* 312 */           dstPixelOffset += dstPixelStride;
/* 313 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 321 */     int dstWidth = dst.getWidth();
/* 322 */     int dstHeight = dst.getHeight();
/* 323 */     int dstBands = dst.getNumBands();
/* 325 */     int dstLineStride = dst.getScanlineStride();
/* 326 */     int dstPixelStride = dst.getPixelStride();
/* 327 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 328 */     float[][] dstData = dst.getFloatDataArrays();
/* 330 */     int srcLineStride = src.getScanlineStride();
/* 331 */     int srcPixelStride = src.getPixelStride();
/* 332 */     int[] srcBandOffsets = src.getBandOffsets();
/* 333 */     float[][] srcData = src.getFloatDataArrays();
/* 335 */     for (int b = 0; b < dstBands; b++) {
/* 336 */       double c = this.constants[b];
/* 337 */       float[] d = dstData[b];
/* 338 */       float[] s = srcData[b];
/* 340 */       int dstLineOffset = dstBandOffsets[b];
/* 341 */       int srcLineOffset = srcBandOffsets[b];
/* 343 */       for (int h = 0; h < dstHeight; h++) {
/* 344 */         int dstPixelOffset = dstLineOffset;
/* 345 */         int srcPixelOffset = srcLineOffset;
/* 347 */         dstLineOffset += dstLineStride;
/* 348 */         srcLineOffset += srcLineStride;
/* 350 */         for (int w = 0; w < dstWidth; w++) {
/* 351 */           d[dstPixelOffset] = ImageUtil.clampFloat(s[srcPixelOffset] * c);
/* 353 */           dstPixelOffset += dstPixelStride;
/* 354 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 362 */     int dstWidth = dst.getWidth();
/* 363 */     int dstHeight = dst.getHeight();
/* 364 */     int dstBands = dst.getNumBands();
/* 366 */     int dstLineStride = dst.getScanlineStride();
/* 367 */     int dstPixelStride = dst.getPixelStride();
/* 368 */     int[] dstBandOffsets = dst.getBandOffsets();
/* 369 */     double[][] dstData = dst.getDoubleDataArrays();
/* 371 */     int srcLineStride = src.getScanlineStride();
/* 372 */     int srcPixelStride = src.getPixelStride();
/* 373 */     int[] srcBandOffsets = src.getBandOffsets();
/* 374 */     double[][] srcData = src.getDoubleDataArrays();
/* 376 */     for (int b = 0; b < dstBands; b++) {
/* 377 */       double c = this.constants[b];
/* 378 */       double[] d = dstData[b];
/* 379 */       double[] s = srcData[b];
/* 381 */       int dstLineOffset = dstBandOffsets[b];
/* 382 */       int srcLineOffset = srcBandOffsets[b];
/* 384 */       for (int h = 0; h < dstHeight; h++) {
/* 385 */         int dstPixelOffset = dstLineOffset;
/* 386 */         int srcPixelOffset = srcLineOffset;
/* 388 */         dstLineOffset += dstLineStride;
/* 389 */         srcLineOffset += srcLineStride;
/* 391 */         for (int w = 0; w < dstWidth; w++) {
/* 392 */           d[dstPixelOffset] = s[srcPixelOffset] * c;
/* 394 */           dstPixelOffset += dstPixelStride;
/* 395 */           srcPixelOffset += srcPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MultiplyConstOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */